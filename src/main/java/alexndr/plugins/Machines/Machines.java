package alexndr.plugins.Machines;

import alexndr.api.logger.LogHelper;
import alexndr.api.registry.Plugin;
import alexndr.plugins.Machines.tiles.MythrilFurnaceTileEntity;
import alexndr.plugins.Machines.tiles.OnyxFurnaceTileEntity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, 
    acceptedMinecraftVersions=ModInfo.ACCEPTED_VERSIONS,
    dependencies=ModInfo.DEPENDENCIES, updateJSON=ModInfo.VERSIONURL )
public class Machines 
{
    @Mod.Instance
    public static Machines INSTANCE;
    
	@SidedProxy(clientSide = "alexndr.plugins.Machines.ProxyClient",
	            serverSide = "alexndr.plugins.Machines.ProxyCommon")
	public static ProxyCommon proxy;
	
    public static Plugin plugin = new Plugin(ModInfo.ID, ModInfo.NAME);

	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) 
	{
		LogHelper.info("Loading Machines...");
  //      proxy.PreInit(event);
		
		//Configuration
		Settings.createOrLoadSettings(event);
		
		//Content
		Content.preInitialize();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) 
	{
		//Registers
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, proxy);
		GameRegistry.registerTileEntity(MythrilFurnaceTileEntity.class, "mythrilFurnace");
		GameRegistry.registerTileEntity(OnyxFurnaceTileEntity.class, "onyxFurnace");
		
		//Content
		Recipes.initialize();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
		LogHelper.info("Machines loaded");
	}
} // end class Machines
