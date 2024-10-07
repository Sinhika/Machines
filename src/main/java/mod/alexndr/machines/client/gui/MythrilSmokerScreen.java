package mod.alexndr.machines.client.gui;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.container.MythrilSmokerContainer;
import mod.alexndr.simplecorelib.api.client.gui.SomewhatAbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmokingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MythrilSmokerScreen extends SomewhatAbstractFurnaceScreen<MythrilSmokerContainer>
{
    // private final static int name_color =  0x404040;
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Machines.MODID, "textures/gui/container/mythril_furnace_gui.png");

    public MythrilSmokerScreen(final MythrilSmokerContainer container, final Inventory inventory, final Component title) 
    {
        super(container, new SmokingRecipeBookComponent(), inventory, title, TEXTURE,
                SomewhatAbstractFurnaceScreen.SMOKER_LIT_PROGRESS_SPRITE,
                SomewhatAbstractFurnaceScreen.SMOKER_BURN_PROGRESS_SPRITE);
    }

} // end-class
