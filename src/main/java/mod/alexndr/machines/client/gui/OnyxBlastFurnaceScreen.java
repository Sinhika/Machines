package mod.alexndr.machines.client.gui;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.container.OnyxBlastFurnaceContainer;
import mod.alexndr.simplecorelib.client.gui.VeryAbstractFurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

/**
 * @author Cadiboo
 */
public class OnyxBlastFurnaceScreen extends VeryAbstractFurnaceScreen<OnyxBlastFurnaceContainer> 
{
//    public static final Logger LOGGER = LogManager.getLogger(Machines.MODID);
    private final static int name_color =  0x0ffffff;

	public OnyxBlastFurnaceScreen(final OnyxBlastFurnaceContainer container, final PlayerInventory inventory, 
	                              final ITextComponent title) 
	{
		super(container, inventory, 
		     new ResourceLocation(Machines.MODID, "textures/gui/container/onyx_furnace_gui.png"), 
		     title, name_color);
	}

} // end-class
