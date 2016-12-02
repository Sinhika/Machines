package alexndr.plugins.Machines;

import net.minecraftforge.fml.common.Mod;

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
