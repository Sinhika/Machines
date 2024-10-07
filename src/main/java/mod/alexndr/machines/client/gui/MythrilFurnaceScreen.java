package mod.alexndr.machines.client.gui;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.container.MythrilFurnaceContainer;
import mod.alexndr.simplecorelib.api.client.gui.SomewhatAbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * @author Cadiboo
 */
public class MythrilFurnaceScreen extends SomewhatAbstractFurnaceScreen<MythrilFurnaceContainer>
{
    // private final static int name_color =  0x404040;
	private static final ResourceLocation TEXTURE =
			new ResourceLocation(Machines.MODID, "textures/gui/container/mythril_furnace_gui.png");

	public MythrilFurnaceScreen(final MythrilFurnaceContainer container, final Inventory inventory, final Component title) 
	{
	    super(container, new SmeltingRecipeBookComponent(), inventory,  title, TEXTURE);
	}

} // end-class
