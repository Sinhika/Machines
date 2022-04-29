package mod.alexndr.machines.client.gui;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.container.MythrilBlastFurnaceContainer;
import mod.alexndr.simplecorelib.api.client.gui.VeryAbstractFurnaceScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MythrilBlastFurnaceScreen extends VeryAbstractFurnaceScreen<MythrilBlastFurnaceContainer>
{
    private final static int name_color =  0x404040;

    public MythrilBlastFurnaceScreen(MythrilBlastFurnaceContainer screenContainer, Inventory inv,
                                     Component titleIn)
    {
        super(screenContainer, inv, 
                new ResourceLocation(Machines.MODID, "textures/gui/container/mythril_furnace_gui.png"), 
                titleIn, name_color);
    }

} // end-class
