package alexndr.plugins.machines.gui;

import java.awt.Color;

import alexndr.api.content.gui.SimpleFurnaceGui;
import alexndr.plugins.machines.Settings;
import alexndr.plugins.machines.inventory.OnyxFurnaceContainer;
import alexndr.plugins.machines.tiles.OnyxFurnaceTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class OnyxFurnaceGUI extends SimpleFurnaceGui
{
    private final Color textColor = new Color(1.0F, 1.0F, 1.0F);
    
    public OnyxFurnaceGUI(InventoryPlayer player, OnyxFurnaceTileEntity iinv)
    {
       super(new OnyxFurnaceContainer(player, iinv), 
        		(Settings.coloredGUIs 
        			? new ResourceLocation(alexndr.plugins.machines.ModInfo.ID, "textures/gui/container/onyx_furnace_gui.png")
        			:  new ResourceLocation("textures/gui/container/furnace.png")
        		), 
        		player, iinv);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
    {
        String s = this.tileFurnace.getDisplayName().getUnformattedText();
		this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, this.textColor.getRGB());
		this.fontRenderer.drawString(this.playerInv.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2,
				this.textColor.getRGB());
    }
  
} // end class
