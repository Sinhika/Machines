package alexndr.plugins.machines;

import alexndr.api.helpers.game.RenderItemHelper;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;

public class ProxyClient extends ProxyCommon
{
    RenderItemHelper renderHelper = new RenderItemHelper(Machines.plugin);

    @Override
    public void PreInit(FMLPreInitializationEvent event)
    {
        super.PreInit(event);
        if(event.getSide() == Side.CLIENT) 
        {
            renderHelper.renderItemsAndBlocks();
            renderHelper.renderItemStuff(event);
        }   
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
	
} // end class ProxyClient
