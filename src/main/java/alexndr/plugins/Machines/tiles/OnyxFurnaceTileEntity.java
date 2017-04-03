package alexndr.plugins.Machines.tiles;

import java.util.Random;

import alexndr.api.content.tiles.TileEntitySimpleFurnace;
import alexndr.api.helpers.game.FurnaceHelper;
import alexndr.plugins.Machines.blocks.OnyxFurnace;
import mcjty.lib.tools.ItemStackTools;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
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
    	int k;  // additional yield amount
    	int r = generator.nextInt(100);
    	
    	if(r <= YieldChance && !getDust(this.getStackInSlot(NDX_INPUT_SLOT)))
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

            if (ItemStackTools.isEmpty(outstack))
            {
                ItemStack extra_result = ItemStackTools.safeCopy(result_stack);
                ItemStackTools.incStackSize(extra_result, k);
                FurnaceHelper.SetInSlot(furnaceItemStacks, NDX_OUTPUT_SLOT, extra_result);
            }
            else if (outstack.getItem() == result_stack.getItem())
            {
                ItemStackTools.incStackSize(outstack, ItemStackTools.getStackSize(result_stack) + k);
            }
            if (instack.getItem() == Item.getItemFromBlock(Blocks.SPONGE) 
                && instack.getMetadata() == 1 
                && ItemStackTools.isValid(this.getStackInSlot(NDX_FUEL_SLOT)) 
                && (this.getStackInSlot(NDX_FUEL_SLOT)).getItem() == Items.BUCKET)
            {
                FurnaceHelper.SetInSlot(furnaceItemStacks, NDX_FUEL_SLOT,
                                        new ItemStack(Items.WATER_BUCKET));
           }

            ItemStackTools.incStackSize(instack, -1);
        } // end-if thisCanSmelt
    } // end smeltItem

	
	public boolean getDust(ItemStack item)
	{
		for (String name : OreDictionary.getOreNames())
        {
			for (final ItemStack oreItem : ItemStackTools.getOres(name))
            {
			    if (ItemStack.areItemsEqual(oreItem, item))
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
            flag1 = default_cooking_update(flag1);
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
