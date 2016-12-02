package alexndr.plugins.Machines.tiles;

import alexndr.api.content.tiles.TileEntitySimpleFurnace;
import alexndr.plugins.Machines.blocks.MythrilFurnace;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.util.math.MathHelper;

public class MythrilFurnaceTileEntity extends TileEntitySimpleFurnace
{
    private static int fuelMultiplier = 2;

    public MythrilFurnaceTileEntity()
    {
        super("container.mythril_furnace", 200, "machines:mythril_furnace_gui", 3);
        fuelMultiplier = MythrilFurnace.FuelMultiplier;
    }

    public static boolean isItemFuel(ItemStack fuel)
    {
         return getItemBurnTime(fuel) > 0;
    }

     /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack burnItem)
    {
        if (burnItem == null)
        {
            return 0;
        }
        else
        {
            Item item = burnItem.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR)
            {
                Block block = Block.getBlockFromItem(item);

                if (block == Blocks.WOODEN_SLAB)
                {
                    return 150 * fuelMultiplier;
                }

                if (block.getDefaultState().getMaterial() == Material.WOOD)
                {
                    return 300 * fuelMultiplier;
                }

                if (block == Blocks.COAL_BLOCK)
                {
                    return 16000 * fuelMultiplier;
                }
            }

            if (item instanceof ItemTool && ((ItemTool)item).getToolMaterialName().equals("WOOD")) return 200 * fuelMultiplier;
            if (item instanceof ItemSword && ((ItemSword)item).getToolMaterialName().equals("WOOD")) return 200 * fuelMultiplier;
            if (item instanceof ItemHoe && ((ItemHoe)item).getMaterialName().equals("WOOD")) return 200 * fuelMultiplier;
            if (item == Items.STICK) return 100 * fuelMultiplier;
            if (item == Items.COAL) return 1600 * fuelMultiplier;
            if (item == Items.LAVA_BUCKET) return 20000 * fuelMultiplier;
            if (item == Item.getItemFromBlock(Blocks.SAPLING)) return 100 * fuelMultiplier;
            if (item == Items.BLAZE_ROD) return 2400 * fuelMultiplier;
            return net.minecraftforge.fml.common.registry.GameRegistry.getFuelValue(burnItem) * fuelMultiplier;
        } // end else
    } // end getItemBurnTime

    @Override
    public void update() 
    {
        boolean flag = this.isBurning();
        boolean flag1 = false;

        if (this.isBurning())
        {
            --this.furnaceBurnTime;
        }

        if (!this.worldObj.isRemote)
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
                MythrilFurnace.setState(this.isBurning(), this.worldObj, this.pos);
            } // end-if
        } // end-if

        if (flag1)
        {
            this.markDirty();
        }
    } // end update()
    
} // end class
