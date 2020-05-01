package alexndr.plugins.machines.tiles;

import alexndr.api.content.tiles.TileEntitySimpleFurnace;
import alexndr.plugins.machines.blocks.MythrilFurnace;
import alexndr.plugins.machines.inventory.MythrilFurnaceContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;

public class MythrilFurnaceTileEntity extends TileEntitySimpleFurnace
{
	public final static String tilename = "container.mythril_furnace";
	public final static String guiID = "machines:mythril_furnace_gui";
    private static int fuelMultiplier = 2;

    public MythrilFurnaceTileEntity()
    {
        super(MythrilFurnaceTileEntity.tilename, 200, MythrilFurnaceTileEntity.guiID, 3);
        fuelMultiplier = MythrilFurnace.FuelMultiplier;
    }

	@Override
	public boolean isItemFuel(ItemStack fuel)
    {
        return MythrilFurnaceTileEntity.getItemBurnTime(fuel) > 0;
    }

	@Override
	public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) 
	{
        return new MythrilFurnaceContainer(playerInventory, this);	
    }

     /**
     * Returns the number of ticks that the supplied fuel item will keep the furnace burning, or 0 if the item isn't
     * fuel
     */
    public static int getItemBurnTime(ItemStack burnItem)
    {
		return TileEntitySimpleFurnace.getItemBurnTime(burnItem) * fuelMultiplier;
    } // end getItemBurnTime

    /**
     * called on tile update.
     */
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
            if (! itemstack.isEmpty())
			{
                burnTime = MythrilFurnaceTileEntity.getItemBurnTime(itemstack);
            }
            flag1 = default_cooking_update(flag1, itemstack, burnTime);
            if (flag != this.isBurning())
            {
                flag1 = true;
                MythrilFurnace.setState(this.isBurning(), this.getWorld(), this.pos);
            } // end-if
        } // end-if

        if (flag1)
        {
            this.markDirty();
        }
    } // end update()
    
} // end class
