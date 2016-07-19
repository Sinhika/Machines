package alexndr.plugins.Machines;

import alexndr.api.core.LogHelper;
import alexndr.api.core.UpdateChecker;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, dependencies="required-after:simplecore")
public class Machines {
	@SidedProxy(clientSide = "alexndr.plugins.Machines.ProxyClient", serverSide = "alexndr.plugins.Machines.ProxyCommon")
	public static ProxyCommon proxy;
	public static Machines INSTANCE = new Machines();
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) {
		LogHelper.info("Loading Machines...");
		
		//Configuration
		ModInfo.setModInfoProperties(event);
		Settings.createOrLoadSettings(event);
		if(Settings.updateChecker.asBoolean()) {UpdateChecker updateChecker = new UpdateChecker(ModInfo.ID, ModInfo.VERSION, ModInfo.VERSIONURL);}
		
		//Content
		Content.preInitialize();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) {
		//Registers
		INSTANCE = this;
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, proxy);
		GameRegistry.registerTileEntity(MythrilFurnaceTileEntity.class, "mythrilFurnace");
		GameRegistry.registerTileEntity(OnyxFurnaceTileEntity.class, "onyxFurnace");
		
		//Content
		Recipes.initialize();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		LogHelper.info("Machines loaded");
	}
}
