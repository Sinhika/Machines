package alexndr.plugins.machines.inventory;

import alexndr.api.content.inventory.SimpleFurnaceContainer;
import alexndr.plugins.machines.tiles.OnyxFurnaceTileEntity;
import net.minecraft.entity.player.InventoryPlayer;

public class OnyxFurnaceContainer extends SimpleFurnaceContainer
{
    public OnyxFurnaceContainer(InventoryPlayer player, OnyxFurnaceTileEntity tileentity)
    {
		super(player, tileentity);
    } // end ctor

} // end class
