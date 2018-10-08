package alexndr.plugins.machines;

import alexndr.api.core.SimpleCoreAPI;
import alexndr.api.helpers.game.TabHelper;
import alexndr.api.logger.LogHelper;
import alexndr.plugins.machines.blocks.MythrilFurnace;
import alexndr.plugins.machines.blocks.OnyxFurnace;
import alexndr.plugins.machines.tiles.MythrilFurnaceTileEntity;
import alexndr.plugins.machines.tiles.OnyxFurnaceTileEntity;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Content 
{
	public static void preInitialize()
	{
        try {
            doBlocks();

            // register tile entities
            GameRegistry.registerTileEntity(MythrilFurnaceTileEntity.class, "mythril_furnace");
            GameRegistry.registerTileEntity(OnyxFurnaceTileEntity.class, "onyx_furnace");

            LogHelper.verbose("Machines: All blocks were added successfully");
        }
        catch (Exception e) {
            LogHelper.severe("Machines: " + 
                            "Blocks were not added successfully. This is a serious problem!");
            e.printStackTrace();
        }
	} // end preInitialize
	
	public static void doBlocks() 
	{
        mythril_furnace = new MythrilFurnace(false).setConfigEntry(Settings.mythrilFurnace)
                        .setUnlocalizedName("mythril_furnace")
                        .setCreativeTab(TabHelper.redstoneTab(SimpleCoreAPI.plugin));
        mythril_furnace_lit = new MythrilFurnace(true).setConfigEntry(Settings.mythrilFurnace)
                        .setUnlocalizedName("mythril_furnace_lit");
        onyx_furnace = new OnyxFurnace(false).setConfigEntry(Settings.onyxFurnace)
                        .setUnlocalizedName("onyx_furnace")
                        .setCreativeTab(TabHelper.redstoneTab(SimpleCoreAPI.plugin));
        onyx_furnace_lit = new OnyxFurnace(true).setConfigEntry(Settings.onyxFurnace)
                        .setUnlocalizedName("onyx_furnace_lit");
	} // end doBlocks()
	
	public static Block mythril_furnace, mythril_furnace_lit, onyx_furnace, onyx_furnace_lit;
} // end class Content
