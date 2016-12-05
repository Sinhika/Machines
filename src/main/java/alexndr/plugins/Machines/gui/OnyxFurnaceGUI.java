package alexndr.plugins.Machines.gui;

import java.awt.Color;

import alexndr.plugins.Machines.Settings;
import alexndr.plugins.Machines.inventory.OnyxFurnaceContainer;
import alexndr.plugins.Machines.tiles.OnyxFurnaceTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.ResourceLocation;

public class OnyxFurnaceGUI extends GuiContainer
{
    private static ResourceLocation defaultGui 
        = new ResourceLocation("textures/gui/container/furnace.png");
    private static ResourceLocation coloredGui 
        = new ResourceLocation(alexndr.plugins.Machines.ModInfo.ID,
                                "textures/gui/container/onyx_furnace_gui.png");
    private static final ResourceLocation furnaceGuiTextures = 
                    Settings.coloredGUIs.asBoolean() ? coloredGui : defaultGui;
    
    private OnyxFurnaceTileEntity tileFurnace;
    private final InventoryPlayer field_175383_v;
    private final Color textColor = new Color(1.0F, 1.0F, 1.0F);
    
    public OnyxFurnaceGUI(InventoryPlayer player, OnyxFurnaceTileEntity iinv)
    {
        super(new OnyxFurnaceContainer(player, iinv));
        this.field_175383_v = player;
        this.tileFurnace = iinv;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String s = this.tileFurnace.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s,
                        this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 
                        this.textColor.getRGB());
        this.fontRendererObj.drawString(this.field_175383_v.getDisplayName().getUnformattedText(),
                        8, this.ySize - 96 + 2, this.textColor.getRGB());
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        if (TileEntityFurnace.isBurning(this.tileFurnace)) {
            i1 = this.getScaledBurnTime(13);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
        }

        i1 = this.getScaledCookProgress(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    } // end drawGuiContainerBackgroundLayer
    
    private int getScaledCookProgress(int cookTime) {
        int j = this.tileFurnace.getField(2);
        int k = this.tileFurnace.getField(3);
        return k != 0 && j != 0 ? j * cookTime / k : 0;
    }
    
    private int getScaledBurnTime(int burnTime){
        int j = this.tileFurnace.getField(1);
        if (j == 0) j = 200;
        return this.tileFurnace.getField(0) * burnTime / j;
    }
 
} // end class
