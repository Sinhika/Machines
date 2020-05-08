package mod.alexndr.machines.client.gui;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.api.client.gui.AbstractModFurnaceScreen;
import mod.alexndr.machines.content.container.MythrilSmokerContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class MythrilSmokerScreen extends AbstractModFurnaceScreen<MythrilSmokerContainer>
{
    private final static int name_color =  0x404040;
    
    public MythrilSmokerScreen(final MythrilSmokerContainer container, final PlayerInventory inventory, final ITextComponent title) 
    {
        super(container, inventory, 
               new ResourceLocation(Machines.MODID, "textures/gui/container/mythril_furnace_gui.png"), 
               title, name_color);
    }

} // end-class
