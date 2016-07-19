package alexndr.plugins.Machines;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class ProxyCommon implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity machine = world.getTileEntity(x, y, z);
		
        if(machine == null){
        	return null;
        }
            
        if(machine instanceof MythrilFurnaceTileEntity){
        	return new MythrilFurnaceContainer(player.inventory, (MythrilFurnaceTileEntity)machine);
        }
        
        else if(machine instanceof OnyxFurnaceTileEntity){
        	return new OnyxFurnaceContainer(player.inventory, (OnyxFurnaceTileEntity)machine);
        }
            
        return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity machine = world.getTileEntity(x, y, z);
		
        if(machine == null){
        	return null;
        }
            
        if(machine instanceof MythrilFurnaceTileEntity){
        	return new MythrilFurnaceGUI(player.inventory, (MythrilFurnaceTileEntity)machine);
        }
        
        else if(machine instanceof OnyxFurnaceTileEntity){
        	return new OnyxFurnaceGUI(player.inventory, (OnyxFurnaceTileEntity)machine);
        }
		
        return null;
	}

}
