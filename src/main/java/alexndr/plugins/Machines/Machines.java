package alexndr.plugins.Machines;

import alexndr.api.logger.LogHelper;
import alexndr.api.registry.Plugin;
import alexndr.plugins.Machines.helpers.FancyFurnaceGuiHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

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
        proxy.PreInit(event);
		
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event) 
	{
		//Registers
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new FancyFurnaceGuiHandler());
        proxy.Init(event);
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
        proxy.PostInit(event);
		LogHelper.info("Machines loaded");
	}
} // end class Machines
