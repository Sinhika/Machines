package mod.alexndr.machines.client.gui;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.container.MythrilFurnaceContainer;
import mod.alexndr.simplecorelib.client.gui.VeryAbstractFurnaceScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

/**
 * @author Cadiboo
 */
public class MythrilFurnaceScreen extends VeryAbstractFurnaceScreen<MythrilFurnaceContainer> 
{
    private final static int name_color =  0x404040;
    
	public MythrilFurnaceScreen(final MythrilFurnaceContainer container, final Inventory inventory, final Component title) 
	{
	    super(container, inventory, 
	           new ResourceLocation(Machines.MODID, "textures/gui/container/mythril_furnace_gui.png"), 
	           title, name_color);
	}

} // end-class
