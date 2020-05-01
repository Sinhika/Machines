package alexndr.plugins.machines.helpers;

import alexndr.plugins.machines.gui.MythrilFurnaceGUI;
import alexndr.plugins.machines.gui.OnyxFurnaceGUI;
import alexndr.plugins.machines.inventory.MythrilFurnaceContainer;
import alexndr.plugins.machines.inventory.OnyxFurnaceContainer;
import alexndr.plugins.machines.tiles.MythrilFurnaceTileEntity;
import alexndr.plugins.machines.tiles.OnyxFurnaceTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class FancyFurnaceGuiHandler implements IGuiHandler
{
    public static final int MYTHRIL_FURNACE_TILE_ID  = 1;
    public static final int ONYX_FURNACE_TILE_ID  = 2;

    /**
     * Returns a Server side Container to be displayed to the user.
     *
     * @param ID The Gui ID Number
     * @param player The player viewing the Gui
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @return A GuiScreen/Container to be displayed to the user, null if none.
     */
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
       if (ID != MYTHRIL_FURNACE_TILE_ID && ID != ONYX_FURNACE_TILE_ID) return null;
       TileEntity machine = world.getTileEntity(new BlockPos(x, y, z));
        
        if(machine == null){
            return null;
        }
            
        if(machine instanceof MythrilFurnaceTileEntity) {
            return new MythrilFurnaceContainer(player.inventory, (MythrilFurnaceTileEntity)machine);
        }
        
        else if(machine instanceof OnyxFurnaceTileEntity) {
            return new OnyxFurnaceContainer(player.inventory, (OnyxFurnaceTileEntity)machine);
        }
            
        return null;
    } // end getServerGuiElement


    /**
     * Returns a Container to be displayed to the user. On the client side, this
     * needs to return a instance of GuiScreen On the server side, this needs to
     * return a instance of Container
     *
     * @param ID The Gui ID Number
     * @param player The player viewing the Gui
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @return A GuiScreen/Container to be displayed to the user, null if none.
     */
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity machine = world.getTileEntity(new BlockPos(x, y, z));
        
        if(machine == null) {
            return null;
        }
            
        if(machine instanceof MythrilFurnaceTileEntity) {
            return new MythrilFurnaceGUI(player.inventory, (MythrilFurnaceTileEntity)machine);
        }
        
        else if(machine instanceof OnyxFurnaceTileEntity) {
            return new OnyxFurnaceGUI(player.inventory, (OnyxFurnaceTileEntity)machine);
        }
        
        return null;
    } // end getClientGuiElement()

} // end class FancyFurnaceGuiHandler
