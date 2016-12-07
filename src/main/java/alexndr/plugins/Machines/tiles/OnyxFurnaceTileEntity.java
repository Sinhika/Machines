package alexndr.plugins.Machines.tiles;

import java.util.Random;

import alexndr.api.content.tiles.TileEntitySimpleFurnace;
import alexndr.plugins.Machines.blocks.OnyxFurnace;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.oredict.OreDictionary;

public class OnyxFurnaceTileEntity extends TileEntitySimpleFurnace
{	
    private static int YieldChance = OnyxFurnace.YieldChance;
    private static int YieldAmount = OnyxFurnace.YieldAmount;
    private Random generator = new Random();
    
    public OnyxFurnaceTileEntity()
    {
        super("container.onyx_furnace", 200, "machines:onyx_furnace_gui", 3);
    }

    public static boolean isItemFuel(ItemStack fuel)
    {
         return getItemBurnTime(fuel) > 0;
    }

    public static int getItemBurnTime(ItemStack burnItem)
    {
        return TileEntitySimpleFurnace.getItemBurnTime(burnItem);
    }
    
    /**
     * Turn one item from the furnace source stack into the appropriate smelted
     * item in the furnace result stack. Avoid doubling dusts because they have already
     * been doubled.
     */
    @Override
    public void smeltItem()
    {
    	int k;
    	int r = generator.nextInt(100);
    	
    	if(r <= YieldChance && !getDust(this.furnaceItemStacks[NDX_INPUT_SLOT]))
    	{
    		k = YieldAmount;
    	}
    	
       	else
    	{
    		k = 0;
    	}
    	
        if (this.canSmelt())
        {
            ItemStack itemstack = FurnaceRecipes.instance().getSmeltingResult(this.furnaceItemStacks[NDX_INPUT_SLOT]);

            if (this.furnaceItemStacks[NDX_OUTPUT_SLOT] == null)
            {
                this.furnaceItemStacks[NDX_OUTPUT_SLOT] = itemstack.copy();
                this.furnaceItemStacks[NDX_OUTPUT_SLOT].stackSize += k;
           }
            else if (this.furnaceItemStacks[NDX_OUTPUT_SLOT].getItem() == itemstack.getItem())
            {
                this.furnaceItemStacks[NDX_OUTPUT_SLOT].stackSize += itemstack.stackSize + k; // Forge BugFix: Results may have multiple items
            }
            // TODO rewrite to work with SimpleBucket
            if (this.furnaceItemStacks[NDX_INPUT_SLOT].getItem() == Item.getItemFromBlock(Blocks.SPONGE) 
                    && this.furnaceItemStacks[NDX_INPUT_SLOT].getMetadata() == 1 
                    && this.furnaceItemStacks[NDX_FUEL_SLOT] != null 
                    && this.furnaceItemStacks[NDX_FUEL_SLOT].getItem() == Items.BUCKET)
            {
                this.furnaceItemStacks[NDX_FUEL_SLOT] = new ItemStack(Items.WATER_BUCKET);
            }

            --this.furnaceItemStacks[NDX_INPUT_SLOT].stackSize;

            if (this.furnaceItemStacks[NDX_INPUT_SLOT].stackSize <= 0)
            {
                this.furnaceItemStacks[NDX_INPUT_SLOT] = null;
            }
        }
    } // end smeltItem

	
	public boolean getDust(ItemStack item)
	{
		for (String name : OreDictionary.getOreNames())
        {
			for (final ItemStack oreItem : OreDictionary.getOres(name))
            {
				if (oreItem.getItem() == item.getItem() && oreItem.getItemDamage() == item.getItemDamage())
				{
                    if (name.contains("dust"))
                    {
                    	return true;
                    }
                    return false;
                }
            }
        }
        return false;
	} // end getDust()
	
    @Override
    public void update() 
    {
        boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())
        {
            --this.furnaceBurnTime;
        }

        if (!this.getWorld().isRemote)
        {
            if (this.isBurning() 
                || this.furnaceItemStacks[1] != null && this.furnaceItemStacks[0] != null)
            {
                if (!this.isBurning() && this.canSmelt())
                {
                    this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);

                    if (this.isBurning())
                    {
                        flag1 = true;

                        if (this.furnaceItemStacks[1] != null)
                        {
                            --this.furnaceItemStacks[1].stackSize;

                            if (this.furnaceItemStacks[1].stackSize == 0)
                            {
                                this.furnaceItemStacks[1] = furnaceItemStacks[1].getItem().getContainerItem(furnaceItemStacks[1]);
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt())
                {
                    ++this.cookTime;

                    if (this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime(this.furnaceItemStacks[0]);
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.cookTime = 0;
                }
            }
            else if (!this.isBurning() && this.cookTime > 0)
            {
                this.cookTime = MathHelper.clamp_int(this.cookTime - 2, 0, this.totalCookTime);
            }

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
