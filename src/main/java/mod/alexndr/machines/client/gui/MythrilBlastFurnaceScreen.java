package mod.alexndr.machines.client.gui;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.container.MythrilBlastFurnaceContainer;
import mod.alexndr.simplecorelib.client.gui.VeryAbstractFurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class MythrilBlastFurnaceScreen extends VeryAbstractFurnaceScreen<MythrilBlastFurnaceContainer>
{
    private final static int name_color =  0x404040;

    public MythrilBlastFurnaceScreen(MythrilBlastFurnaceContainer screenContainer, PlayerInventory inv,
                                     ITextComponent titleIn)
    {
        super(screenContainer, inv, 
                new ResourceLocation(Machines.MODID, "textures/gui/container/mythril_furnace_gui.png"), 
                titleIn, name_color);
    }

} // end-class
