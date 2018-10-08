package alexndr.plugins.machines;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ProxyClient extends ProxyCommon
{
    @Override
    public void PreInit(FMLPreInitializationEvent event)
    {
        super.PreInit(event);
    }

    @Override
    public void Init(FMLInitializationEvent event)
    {
        super.Init(event);
    }

    @Override
    public void PostInit(FMLPostInitializationEvent event)
    {
        super.PostInit(event);
    }

	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) 
	{
    	ModBlocks.registerModels();
	}

} // end class ProxyClient
