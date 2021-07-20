package mod.alexndr.machines.api.content;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.block.BlockState;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RangedWrapper;

public abstract class AbstractModFurnaceTileEntity extends TileEntity  implements ITickableTileEntity, INamedContainerProvider
{

    public static final int FUEL_SLOT = 0;
    public static final int INPUT_SLOT = 1;
    public static final int OUTPUT_SLOT = 2;
    
    private static final String INVENTORY_TAG = "inventory";
    private static final String SMELT_TIME_LEFT_TAG = "smeltTimeLeft";
    private static final String MAX_SMELT_TIME_TAG = "maxSmeltTime";
    private static final String FUEL_BURN_TIME_LEFT_TAG = "fuelBurnTimeLeft";
    private static final String MAX_FUEL_BURN_TIME_TAG = "maxFuelBurnTime";
    
    protected final IRecipeType<? extends AbstractCookingRecipe> recipeType;
    private final Map<ResourceLocation, Integer> recipe2xp_map = Maps.newHashMap();

    // implement recipe-caching like all the cool kids.
    protected AbstractCookingRecipe cachedRecipe;
    protected ItemStack failedMatch = ItemStack.EMPTY;
    
    protected double fuelMultiplier = 1.0;
    protected int YieldChance = 0;
    protected int YieldAmount = 0;
    protected Random generator = new Random();

    public short smeltTimeLeft = -1;
    public short maxSmeltTime = -1;
    public int fuelBurnTimeLeft = -1;
    public int maxFuelBurnTime = -1;
    private boolean lastBurning = false;
    
    public final ItemStackHandler inventory = new ItemStackHandler(3) 
    {
    		@Override
    		public boolean isItemValid(final int slot, @Nonnull final ItemStack stack) 
    		{
    			switch (slot) {
    				case FUEL_SLOT:
    					return FurnaceTileEntity.isFuel(stack);
    				case INPUT_SLOT:
    					return isInput(stack);
    				case OUTPUT_SLOT:
    					return isOutput(stack);
    				default:
    					return false;
    			}
    		} // end ItemStackHander(3).isItemValid()
    
    		@Override
    		protected void onContentsChanged(final int slot) {
    			super.onContentsChanged(slot);
    			// Mark the tile entity as having changed whenever its inventory changes.
    			// "markDirty" tells vanilla that the chunk containing the tile entity has
    			// changed and means the game will save the chunk to disk later.
    			AbstractModFurnaceTileEntity.this.setChanged();
    		} // end ()
    }; // end ItemStackHandler(3)
    
    private final LazyOptional<ItemStackHandler> inventoryCapabilityExternal = LazyOptional.of(() -> this.inventory);
    private final LazyOptional<IItemHandlerModifiable> inventoryCapabilityExternalUp = LazyOptional.of(() -> new RangedWrapper(this.inventory, INPUT_SLOT, INPUT_SLOT + 1));
    private final LazyOptional<IItemHandlerModifiable> inventoryCapabilityExternalDown = LazyOptional.of(() -> new RangedWrapper(this.inventory, OUTPUT_SLOT, OUTPUT_SLOT + 1));
    private final LazyOptional<IItemHandlerModifiable> inventoryCapabilityExternalSides = LazyOptional.of(() -> new RangedWrapper(this.inventory, FUEL_SLOT, INPUT_SLOT + 1));

    public AbstractModFurnaceTileEntity(TileEntityType<?> tileEntityTypeIn, IRecipeType<? extends AbstractCookingRecipe> recipeTypeIn)
    {
        super(tileEntityTypeIn);
        this.recipeType = recipeTypeIn;
    }

    /**
     * @return If the stack is not empty and has a smelting recipe associated with it
     */
    private boolean isInput(final ItemStack stack)
    {
    	if (stack.isEmpty())
    		return false;
    	return getRecipe(stack).isPresent();
    }

    /**
     * @return If the stack's item is equal to the result of smelting our input
     */
    private boolean isOutput(final ItemStack stack)
    {
    	final Optional<ItemStack> result = getResult(inventory.getStackInSlot(INPUT_SLOT));
    	return result.isPresent() && ItemStack.isSame(result.get(), stack);
    }

    /**
     * @return The smelting recipe for the input stack
     */
    private Optional<AbstractCookingRecipe> getRecipe(final ItemStack input)
    {
        if (input.isEmpty() || input == failedMatch) {
            return Optional.empty();
        }
    	// Due to vanilla's code we need to pass an IInventory into RecipeManager#getRecipe so we make one here.
    	return getRecipe(new Inventory(input));
    }

    /**
     * @return The smelting recipe for the inventory; implements recipe caching.
     */
    @SuppressWarnings("unchecked")
    private Optional<AbstractCookingRecipe> getRecipe(final IInventory inventory)
    {
        if (cachedRecipe != null && cachedRecipe.matches(inventory, level))
        {
            return Optional.of(cachedRecipe);
        }
        else
        {
            AbstractCookingRecipe rec 
                = level.getRecipeManager().getRecipeFor((IRecipeType<AbstractCookingRecipe>) recipeType, inventory, level).orElse(null);
            if (rec == null) {
                failedMatch = inventory.getItem(0); // i.e., input.
            }
            else {
                failedMatch = ItemStack.EMPTY;
            }
            cachedRecipe = rec;
            return Optional.ofNullable(rec);
        } // end else
    } // end getRecipe()

    
    protected Optional<ItemStack> getResult(final ItemStack input)
    {
        // Due to vanilla's code we need to pass an IInventory into RecipeManager#getRecipe and
        // AbstractCookingRecipe#getCraftingResult() so we make one here.
        final Inventory dummyInventory = new Inventory(input);
        Optional<ItemStack> maybe_result = 
                getRecipe(dummyInventory).map(recipe -> recipe.assemble(dummyInventory));
        
        // enhanced yield processing.
        if (YieldChance <= 0 || YieldAmount <= 0)
        {
            return maybe_result;
        }
        ItemStack result =  maybe_result.orElse(ItemStack.EMPTY);
        int r = generator.nextInt(100);
        if(r <= YieldChance && ! result.isEmpty()) 
        {
            int k = YieldAmount;
            if ((k + result.getCount()) < result.getMaxStackSize())
            result.grow(k);
        }
        return Optional.of(result);
    }


    /**
     * Called every tick to update our tile entity
     */
    @Override
    public void tick()
    {
        // Fuel burning code
        boolean hasFuel = false;
        if (isBurning()) {
            hasFuel = true;
            --fuelBurnTimeLeft;
        }
        
    	if (level == null || level.isClientSide)
    		return;
    
    	// Smelting code
    	final ItemStack input = inventory.getStackInSlot(INPUT_SLOT).copy();
    	final ItemStack result = getResult(input).orElse(ItemStack.EMPTY);
    
        if (!result.isEmpty() && isInput(input))
        {
            final boolean canInsertResultIntoOutput 
                = inventory.insertItem(OUTPUT_SLOT, result, true).isEmpty();
            if (canInsertResultIntoOutput)
            {
                if (!hasFuel) { 
                    if (burnFuel()) hasFuel = true; 
                 }
                if (hasFuel)
                {
                    if (smeltTimeLeft == -1)
                    { // Item has not been smelted before
                        smeltTimeLeft = maxSmeltTime = getSmeltTime(input);
                    }
                    else
                    { // Item was already being smelted
                        --smeltTimeLeft;
                        if (smeltTimeLeft == 0)
                        {
                            inventory.insertItem(OUTPUT_SLOT, result, false);
                            
                            this.setRecipeUsed(getRecipe(input).get()); // move to BEFORE we shrink.
                            
                            if (input.hasContainerItem()) {
                                inventory.setStackInSlot(INPUT_SLOT, input.getContainerItem());
                            }
                            else
                            {
                                input.shrink(1);
                                inventory.setStackInSlot(INPUT_SLOT, input); // Update the data
                            }
                            
                            smeltTimeLeft = -1; // Set to -1 so we smelt the next stack on the next tick
                        } // end-if
                    } // end-else
                }
                else // No fuel -> add to smelt time left to simulate cooling
                {
                    if (smeltTimeLeft < maxSmeltTime) ++smeltTimeLeft;
                }
            }
        } // end-if
    	else // We have an invalid input stack (somehow)
    	{
    		smeltTimeLeft = maxSmeltTime = -1;
    	}
    	
    	// Syncing code
    	// If the burning state has changed.
    	if (lastBurning != hasFuel) { 
    	    // We use hasFuel because the current fuel may be all burnt out but we have more that will be used next tick
    
    		// "markDirty" tells vanilla that the chunk containing the tile entity has
    		// changed and means the game will save the chunk to disk later.
    		this.setChanged();
    
    		final BlockState newState = this.getBlockState()
    				.setValue(AbstractModFurnaceBlock.BURNING, hasFuel);
    
    		// Flag 2: Send the change to clients
    		level.setBlock(worldPosition, newState, 2);
    
    		// Update the last synced burning state to the current burning state
    		lastBurning = hasFuel;
    	} // end-if
    } // end tick()

    /**
     * Mimics the code in {@link AbstractFurnaceTileEntity#getTotalCookTime()}
     *
     * @return The custom smelt time or 200 if there is no recipe for the input
     */
    protected short getSmeltTime(final ItemStack input)
    {
    	return getRecipe(input)
    			.map(AbstractCookingRecipe::getCookingTime)
    			.orElse(200)
    			.shortValue();
    }

    public void setRecipeUsed(@Nullable IRecipe<?> recipe)
    {
        if (recipe != null)
        {
            this.recipe2xp_map.compute(recipe.getId(), (p_214004_0_, p_214004_1_) -> {
                return 1 + (p_214004_1_ == null ? 0 : p_214004_1_);
            });
        }
    } // end setRecipeUsed()
    
    /**
     * @return If the fuel was burnt
     */
    protected boolean burnFuel()
    {
    	final ItemStack fuelStack = inventory.getStackInSlot(FUEL_SLOT).copy();
    	if (!fuelStack.isEmpty()) 
    	{
    	    // improved fuel efficiency processing here.
    		final int burnTime = (int) Math.ceil(ForgeHooks.getBurnTime(fuelStack, IRecipeType.SMELTING) * fuelMultiplier);
    		if (burnTime > 0) {
    			fuelBurnTimeLeft = maxFuelBurnTime = burnTime;
    			if (fuelStack.hasContainerItem())
    				inventory.setStackInSlot(FUEL_SLOT, fuelStack.getContainerItem());
    			else {
    				fuelStack.shrink(1);
    				inventory.setStackInSlot(FUEL_SLOT, fuelStack); // Update the data
    			}
    			return true;
    		}
    	}
    	fuelBurnTimeLeft = maxFuelBurnTime = -1;
    	return false;
    }

    public boolean isBurning()
    {
    	return this.fuelBurnTimeLeft > 0;
    }

    /**
     * Retrieves the Optional handler for the capability requested on the specific side.
     *
     * @param cap  The capability to check
     * @param side The Direction to check from. CAN BE NULL! Null is defined to represent 'internal' or 'self'
     * @return The requested an optional holding the requested capability.
     */
    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull final Capability<T> cap, @Nullable final Direction side)
    {
    	if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
    		if (side == null)
    			return inventoryCapabilityExternal.cast();
    		switch (side) {
    			case DOWN:
    				return inventoryCapabilityExternalDown.cast();
    			case UP:
    				return inventoryCapabilityExternalUp.cast();
    			case NORTH:
    			case SOUTH:
    			case WEST:
    			case EAST:
    				return inventoryCapabilityExternalSides.cast();
    		}
    	}
    	return super.getCapability(cap, side);
    }

    @Override
    public void onLoad()
    {
    	super.onLoad();
    	// We set this in onLoad instead of the constructor so that TileEntities
    	// constructed from NBT (saved tile entities) have this set to the proper value
    	if (level != null && !level.isClientSide)
    		lastBurning = isBurning();
    }

    /**
     * Read saved data from disk into the tile.
     */
    @Override
    public void load(BlockState stateIn, final CompoundNBT compound)
    {
    	super.load(stateIn, compound);
    	this.inventory.deserializeNBT(compound.getCompound(INVENTORY_TAG));
    	this.smeltTimeLeft = compound.getShort(SMELT_TIME_LEFT_TAG);
    	this.maxSmeltTime = compound.getShort(MAX_SMELT_TIME_TAG);
    	this.fuelBurnTimeLeft = compound.getInt(FUEL_BURN_TIME_LEFT_TAG);
    	this.maxFuelBurnTime = compound.getInt(MAX_FUEL_BURN_TIME_TAG);

        // We set this in read() instead of the constructor so that TileEntities
        // constructed from NBT (saved tile entities) have this set to the proper value
        if (this.hasLevel() && !this.level.isClientSide) {
            lastBurning = this.isBurning();
        }

    	// get recipe2xp map
        int ii = compound.getShort("RecipesUsedSize");
        for(int jj = 0; jj < ii; ++jj) {
           ResourceLocation resourcelocation 
               = new ResourceLocation(compound.getString("RecipeLocation" + jj));
           int kk = compound.getInt("RecipeAmount" + jj);
           this.recipe2xp_map.put(resourcelocation, kk);
        }
        
        // blockstate?
        if (this.hasLevel()) {
            this.level.setBlockAndUpdate(getBlockPos(), this.getBlockState()
                                            .setValue(AbstractModFurnaceBlock.BURNING, 
                                                    Boolean.valueOf(this.isBurning())));
        }
        
    } // end read()

    /**
     * Write data from the tile into a compound tag for saving to disk.
     */
    @Nonnull
    @Override
    public CompoundNBT save(final CompoundNBT compound)
    {
    	super.save(compound);
    	compound.put(INVENTORY_TAG, this.inventory.serializeNBT());
    	compound.putShort(SMELT_TIME_LEFT_TAG, this.smeltTimeLeft);
    	compound.putShort(MAX_SMELT_TIME_TAG, this.maxSmeltTime);
    	compound.putInt(FUEL_BURN_TIME_LEFT_TAG, this.fuelBurnTimeLeft);
    	compound.putInt(MAX_FUEL_BURN_TIME_TAG, this.maxFuelBurnTime);
    	
    	// write recipe2xp map
        compound.putShort("RecipesUsedSize", (short)this.recipe2xp_map.size());
        int ii = 0;
        for(Entry<ResourceLocation, Integer> entry : this.recipe2xp_map.entrySet()) 
        {
           compound.putString("RecipeLocation" + ii, entry.getKey().toString());
           compound.putInt("RecipeAmount" + ii, entry.getValue());
           ++ii;
        }
    	return compound;
    }

    /**
     * Get an NBT compound to sync to the client with SPacketChunkData, used for initial loading of the
     * chunk or when many blocks change at once.
     * This compound comes back to you client-side in {@link #handleUpdateTag}
     * The default implementation ({@link TileEntity#handleUpdateTag}) calls {@link #writeInternal)}
     * which doesn't save any of our extra data so we override it to call {@link #write} instead
     */
    @Nonnull
    public CompoundNBT getUpdateTag()
    {
    	return this.save(new CompoundNBT());
    }

    /**
     * Called when the chunk's TE update tag, gotten from {@link #getUpdateTag()}, is received on the client.
     * Used to handle this tag in a special way. By default this simply calls {@link #readFromNBT(NBTTagCompound)}.
     *
     * @param tag The {@link NBTTagCompound} sent from {@link #getUpdateTag()}
     */
    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag)
    {
        this.load(state, tag);
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket()
    {
        CompoundNBT nbtTag = new CompoundNBT();
        return new SUpdateTileEntityPacket(getBlockPos(), -1, save(nbtTag));
    }

    
    /**
     * Called when you receive a TileEntityData packet for the location this
     * TileEntity is currently in. On the client, the NetworkManager will always
     * be the remote server. On the server, it will be whomever is responsible for
     * sending the packet.
     *
     * @param net The NetworkManager the packet originated from
     * @param pkt The data packet
     */
    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt)
    {
        CompoundNBT nbtTag = pkt.getTag();
        this.load(getBlockState(), nbtTag);
    }


    @Override
    protected void invalidateCaps()
    {
        super.invalidateCaps();
        // We need to invalidate our capability references so that any cached references (by other mods) don't
        // continue to reference our capabilities and try to use them and/or prevent them from being garbage collected
        inventoryCapabilityExternal.invalidate();
        inventoryCapabilityExternalUp.invalidate();
        inventoryCapabilityExternalDown.invalidate();
        inventoryCapabilityExternalSides.invalidate();
    }

    @Nonnull
    @Override
    public abstract ITextComponent getDisplayName();

    /**
     * Called from {@link NetworkHooks#openGui}
     * (which is called from {@link ElectricFurnaceBlock#onBlockActivated} on the logical server)
     *
     * @return The logical-server-side Container for this TileEntity
     */
    @Nonnull
    @Override
    public abstract Container createMenu(final int windowId, final PlayerInventory inventory, final PlayerEntity player);

    public void grantExperience(PlayerEntity player)
    {
        List<IRecipe<?>> list = Lists.newArrayList();

        for (Entry<ResourceLocation, Integer> entry : this.recipe2xp_map.entrySet())
        {
            player.level.getRecipeManager().byKey(entry.getKey()).ifPresent((p_213993_3_) -> {
                list.add(p_213993_3_);
                spawnExpOrbs(player, entry.getValue(), ((AbstractCookingRecipe) p_213993_3_).getExperience());
            });
        }
        player.awardRecipes(list);
        this.recipe2xp_map.clear();
    }
    
    private static void spawnExpOrbs(PlayerEntity player, int pCount, float experience)
    {
        if (experience == 0.0F) {
            pCount = 0;
        }
        else if (experience < 1.0F)
        {
            int i = MathHelper.floor((float) pCount * experience);
            if (i < MathHelper.ceil((float) pCount * experience)
                    && Math.random() < (double) ((float) pCount * experience - (float) i))
            {
                ++i;
            }
            pCount = i;
        }

        while (pCount > 0)
        {
            int j = ExperienceOrbEntity.getExperienceValue(pCount);
            pCount -= j;
            player.level.addFreshEntity(new ExperienceOrbEntity(player.level, player.getX(), player.getY() + 0.5D,
                    player.getZ() + 0.5D, j));
        }
    } // end spawnExpOrbs()

} // end class
