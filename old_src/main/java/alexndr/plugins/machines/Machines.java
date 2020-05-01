package alexndr.plugins.machines;

import alexndr.api.logger.LogHelper;
import alexndr.api.registry.Plugin;
import alexndr.plugins.machines.helpers.FancyFurnaceGuiHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION, 
    acceptedMinecraftVersions=ModInfo.ACCEPTED_VERSIONS,
    dependencies=ModInfo.DEPENDENCIES, updateJSON=ModInfo.VERSIONURL )
public class Machines 
{
    @Mod.Instance
    public static Machines INSTANCE;
    
	@SidedProxy(clientSide = "alexndr.plugins.machines.ProxyClient",
	            serverSide = "alexndr.plugins.machines.ProxyCommon")
	public static ProxyCommon proxy;
	
    public static Plugin plugin = new Plugin(ModInfo.ID, ModInfo.NAME);

	@Mod.EventHandler
	public void PreInit(FMLPreInitializationEvent event) 
	{
		LogHelper.info(ModInfo.ID, "Loading...");
        proxy.PreInit(event);
		
	}
	
	@Mod.EventHandler
	public void Init(FMLInitializationEvent event) 
	{
		//Registers
		NetworkRegistry.INSTANCE.registerGuiHandler(this, (IGuiHandler) new FancyFurnaceGuiHandler());
        proxy.Init(event);
		
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) 
	{
        proxy.PostInit(event);
		LogHelper.info(ModInfo.ID, "Loading Complete!");
	}
} // end class Machines
