package mod.alexndr.machines.client.gui;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.content.container.MythrilSmokerContainer;
import mod.alexndr.simplecorelib.api.client.gui.VeryAbstractFurnaceScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class MythrilSmokerScreen extends VeryAbstractFurnaceScreen<MythrilSmokerContainer>
{
    private final static int name_color =  0x404040;
    
    public MythrilSmokerScreen(final MythrilSmokerContainer container, final Inventory inventory, final Component title) 
    {
        super(container, inventory, 
               new ResourceLocation(Machines.MODID, "textures/gui/container/mythril_furnace_gui.png"), 
               title, name_color);
    }

} // end-class
