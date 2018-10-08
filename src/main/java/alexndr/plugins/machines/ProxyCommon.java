package alexndr.plugins.machines;

import alexndr.api.registry.ContentRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ProxyCommon
{
    public void PreInit(FMLPreInitializationEvent event)
    {   
        //Configuration
        ContentRegistry.registerPlugin(Machines.plugin);
        Settings.createOrLoadSettings(event);
        
        //Content
        Content.preInitialize();
    } // end PreInit
    
    public void Init(FMLInitializationEvent event)
    {
        //Content
        Recipes.initialize();
    } // end Init

    public void PostInit(FMLPostInitializationEvent event)
    {
    } // end PostInit
} // end class ProxyCommon

