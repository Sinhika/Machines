package mod.alexndr.machines.client.gui;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.container.MythrilBlastFurnaceContainer;
import mod.alexndr.simplecorelib.api.client.gui.SomewhatAbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.BlastingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MythrilBlastFurnaceScreen extends SomewhatAbstractFurnaceScreen<MythrilBlastFurnaceContainer>
{
    // private final static int name_color =  0x404040;
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Machines.MODID, "textures/gui/container/mythril_furnace_gui.png");

    public MythrilBlastFurnaceScreen(MythrilBlastFurnaceContainer screenContainer, Inventory inv,
                                     Component titleIn)
    {
        super(screenContainer, new BlastingRecipeBookComponent(), inv, titleIn, TEXTURE,
                SomewhatAbstractFurnaceScreen.BLAST_FURNACE_LIT_PROGRESS_SPRITE,
                SomewhatAbstractFurnaceScreen.BLAST_FURNACE_BURN_PROGRESS_SPRITE);
    }

} // end-class
