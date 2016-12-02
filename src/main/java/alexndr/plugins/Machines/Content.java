package alexndr.plugins.Machines;

import net.minecraft.block.Block;
import alexndr.api.core.ContentRegistry;
import alexndr.api.core.ContentTypes;
import alexndr.api.core.LogHelper;
import alexndr.plugins.Machines.blocks.MythrilFurnace;
import alexndr.plugins.Machines.blocks.OnyxFurnace;
import cpw.mods.fml.common.registry.GameRegistry;

public class Content {
	public static void preInitialize()
	{
		try{doBlocks(); LogHelper.verboseInfo("Machines", "All blocks were added successfully");}
			catch(Exception e){LogHelper.severe("Machines", "Blocks were not added successfully. This is a serious problem!"); e.printStackTrace();}
	}
	
	public static void doBlocks() {
		mythril_furnace = new MythrilFurnace(false).setConfigValues(Settings.mythrilFurnace).setBlockName("mythril_furnace");
		mythril_furnace_lit = new MythrilFurnace(true).setConfigValues(Settings.mythrilFurnace).setBlockName("mythril_furnace_lit");
		onyx_furnace = new OnyxFurnace(false).setConfigValues(Settings.onyxFurnace).setBlockName("onyx_furnace");
		onyx_furnace_lit = new OnyxFurnace(true).setConfigValues(Settings.onyxFurnace).setBlockName("onyx_furance_lit");
		
		GameRegistry.registerBlock(mythril_furnace, "mythril_furnace");
		GameRegistry.registerBlock(mythril_furnace_lit, "mythril_furnace_lit");
		GameRegistry.registerBlock(onyx_furnace, "onyx_furnace");
		GameRegistry.registerBlock(onyx_furnace_lit, "onyx_furnace_lit");
		
		ContentRegistry.registerBlock(mythril_furnace, "mythril_furnace", "machines", ContentTypes.Block.MACHINE);
		ContentRegistry.registerBlock(mythril_furnace_lit, "mythril_furnace_lit", "machines", ContentTypes.Block.MACHINE);
		ContentRegistry.registerBlock(onyx_furnace, "onyx_furnace", "machines", ContentTypes.Block.MACHINE);
		ContentRegistry.registerBlock(onyx_furnace_lit, "onyx_furnace_lit", "machines", ContentTypes.Block.MACHINE);
	}
	
	public static Block mythril_furnace, mythril_furnace_lit, onyx_furnace, onyx_furnace_lit;
}
