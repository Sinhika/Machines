package alexndr.plugins.machines.tiles;

import java.util.Random;

import alexndr.api.content.tiles.TileEntitySimpleFurnace;
import alexndr.api.helpers.game.FurnaceHelper;
import alexndr.plugins.machines.blocks.OnyxFurnace;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

public class OnyxFurnaceTileEntity extends TileEntitySimpleFurnace
{	
	public final static String tilename = "container.onyx_furnace";
	public final static String guiID = "machines:onyx_furnace_gui";
    private static int YieldChance = OnyxFurnace.YieldChance;
    private static int YieldAmount = OnyxFurnace.YieldAmount;
    private Random generator = new Random();
    
    public OnyxFurnaceTileEntity()
    {
        super(OnyxFurnaceTileEntity.tilename, 200, OnyxFurnaceTileEntity.guiID, 3);
    }

    public static int getItemBurnTime(ItemStack burnItem)
    {
        return TileEntitySimpleFurnace.getItemBurnTime(burnItem);
    }
    
    /**
     * Turn one item from the furnace source stack into the appropriate smelted
     * item in the furnace result stack. We don't care if you're trying to double dusts,
     * because Mekanism exists so who cares?  (Also scanning the ore list every single
     * operation is a bit extra).
     */
    @Override
    public void smeltItem()
    {
    	int k;  // additional yield amount
    	int r = generator.nextInt(100);
    	
    	if(r <= YieldChance)
    	{
    		k = YieldAmount;
    	}
       	else {
    		k = 0;
    	}
    	
        if (this.canSmelt())
        {
            ItemStack instack = (ItemStack)this.getStackInSlot(NDX_INPUT_SLOT);
            ItemStack result_stack = FurnaceRecipes.instance().getSmeltingResult(instack);
            ItemStack outstack = (ItemStack)this.getStackInSlot(NDX_OUTPUT_SLOT);

            if (outstack.isEmpty())
            {
                ItemStack extra_result = result_stack.copy();
                extra_result.grow(k);
                FurnaceHelper.SetInSlot(furnaceItemStacks, NDX_OUTPUT_SLOT, extra_result);
            }
            else if (ItemStack.areItemsEqual(outstack, result_stack))
            {
               	outstack.grow(result_stack.getCount() + k);
            }
            if (instack.getItem() == Item.getItemFromBlock(Blocks.SPONGE) 
                && instack.getMetadata() == 1 
                && !this.getStackInSlot(NDX_FUEL_SLOT).isEmpty() 
                && (this.getStackInSlot(NDX_FUEL_SLOT)).getItem() == Items.BUCKET)
            {
                FurnaceHelper.SetInSlot(furnaceItemStacks, NDX_FUEL_SLOT,
                                        new ItemStack(Items.WATER_BUCKET));
           }

            this.decrStackSize(NDX_INPUT_SLOT, 1);
        } // end-if thisCanSmelt
    } // end smeltItem
	
	
    @Override
    public void update() 
    {
        boolean flag = this.isBurning();
        boolean flag1 = false;
        int burnTime = 0;
        if (this.isBurning())
        {
            --this.furnaceBurnTime;
        }

        if (!this.getWorld().isRemote)
        {
            ItemStack itemstack = (ItemStack)this.getStackInSlot(NDX_FUEL_SLOT);
            if (!itemstack.isEmpty()) 
			{
                burnTime = OnyxFurnaceTileEntity.getItemBurnTime(itemstack);
            }
            flag1 = default_cooking_update(flag1, itemstack, burnTime);
            if (flag != this.isBurning())
            {
                flag1 = true;
                OnyxFurnace.setState(this.isBurning(), this.getWorld(), this.pos);
            } // end-if
        } // end-if

        if (flag1)
        {
            this.markDirty();
        }
    } // end update()
	
} // end class
