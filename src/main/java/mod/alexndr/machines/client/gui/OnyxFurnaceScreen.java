package mod.alexndr.machines.client.gui;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.container.OnyxFurnaceContainer;
import mod.alexndr.simplecorelib.api.client.gui.SomewhatAbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * @author Cadiboo
 */
public class OnyxFurnaceScreen extends SomewhatAbstractFurnaceScreen<OnyxFurnaceContainer>
{
    private final static int name_color =  0x0ffffff;
	private static final ResourceLocation TEXTURE =new ResourceLocation(Machines.MODID, "textures/gui/container/onyx_furnace_gui.png");


	public OnyxFurnaceScreen(OnyxFurnaceContainer menu, Inventory playerInventory, Component title)
	{
		super(menu, new SmeltingRecipeBookComponent(), playerInventory, title, TEXTURE);
	}
} // end-class
