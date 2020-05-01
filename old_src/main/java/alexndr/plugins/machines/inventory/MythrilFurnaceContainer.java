package alexndr.plugins.machines.inventory;

import alexndr.api.content.inventory.SimpleFurnaceContainer;
import alexndr.plugins.machines.tiles.MythrilFurnaceTileEntity;
import net.minecraft.entity.player.InventoryPlayer;

public class MythrilFurnaceContainer extends SimpleFurnaceContainer
{
    public MythrilFurnaceContainer(InventoryPlayer player, MythrilFurnaceTileEntity tileentity)
    {
		super(player, tileentity);
    } // end ctor

} // end class
