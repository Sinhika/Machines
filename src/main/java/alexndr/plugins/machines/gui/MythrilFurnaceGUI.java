package alexndr.plugins.machines.gui;

import alexndr.api.content.gui.SimpleFurnaceGui;
import alexndr.plugins.machines.Settings;
import alexndr.plugins.machines.inventory.MythrilFurnaceContainer;
import alexndr.plugins.machines.tiles.MythrilFurnaceTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class MythrilFurnaceGUI extends SimpleFurnaceGui
{
    private static ResourceLocation defaultGui 
        = new ResourceLocation("textures/gui/container/furnace.png");
    private static ResourceLocation coloredGui 
        = new ResourceLocation(alexndr.plugins.machines.ModInfo.ID,
                       "textures/gui/container/mythril_furnace_gui.png");

    private static final ResourceLocation furnaceGuiTextures = 
                    Settings.coloredGUIs ? coloredGui : defaultGui;

    public MythrilFurnaceGUI(InventoryPlayer player, MythrilFurnaceTileEntity iinv)
    {
        super(new MythrilFurnaceContainer(player, iinv), furnaceGuiTextures, player, iinv);
    }
 
} // end class
