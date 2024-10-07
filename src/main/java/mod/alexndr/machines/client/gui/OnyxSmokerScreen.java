package mod.alexndr.machines.client.gui;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.container.OnyxSmokerContainer;
import mod.alexndr.simplecorelib.api.client.gui.SomewhatAbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmokingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class OnyxSmokerScreen extends SomewhatAbstractFurnaceScreen<OnyxSmokerContainer>
{
   // private final static int name_color =  0x0ffffff;
	private static final ResourceLocation TEXTURE =
			new ResourceLocation(Machines.MODID, "textures/gui/container/onyx_furnace_gui.png");

	public OnyxSmokerScreen(final OnyxSmokerContainer container, final Inventory inventory, 
	                        final Component title) 
	{
		super(container, new SmokingRecipeBookComponent(), inventory, title, TEXTURE,
				SomewhatAbstractFurnaceScreen.SMOKER_LIT_PROGRESS_SPRITE,
				SomewhatAbstractFurnaceScreen.SMOKER_BURN_PROGRESS_SPRITE);
	}

} // end-class
