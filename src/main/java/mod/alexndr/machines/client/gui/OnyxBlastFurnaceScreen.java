package mod.alexndr.machines.client.gui;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.container.OnyxBlastFurnaceContainer;
import mod.alexndr.simplecorelib.api.client.gui.SomewhatAbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.BlastingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/**
 * @author Cadiboo
 */
public class OnyxBlastFurnaceScreen extends SomewhatAbstractFurnaceScreen<OnyxBlastFurnaceContainer>
{
//    public static final Logger LOGGER = LogManager.getLogger(Machines.MODID);
    private final static int name_color =  0x0ffffff;
	private static final ResourceLocation TEXTURE =
			new ResourceLocation(Machines.MODID, "textures/gui/container/onyx_furnace_gui.png");

	public OnyxBlastFurnaceScreen(final OnyxBlastFurnaceContainer container, final Inventory inventory, 
	                              final Component title) 
	{
		super(container, new BlastingRecipeBookComponent(), inventory, title, TEXTURE,
				SomewhatAbstractFurnaceScreen.BLAST_FURNACE_LIT_PROGRESS_SPRITE,
				SomewhatAbstractFurnaceScreen.BLAST_FURNACE_BURN_PROGRESS_SPRITE);
	}

} // end-class
