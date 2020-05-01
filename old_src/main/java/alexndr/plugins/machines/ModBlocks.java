package alexndr.plugins.machines;

import alexndr.api.core.SimpleCoreAPI;
import alexndr.api.helpers.game.TabHelper;
import alexndr.plugins.machines.blocks.MythrilFurnace;
import alexndr.plugins.machines.blocks.OnyxFurnace;
import alexndr.plugins.machines.tiles.MythrilFurnaceTileEntity;
import alexndr.plugins.machines.tiles.OnyxFurnaceTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks 
{
	// MACHINES
	
	public static MythrilFurnace mythril_furnace = new MythrilFurnace("mythril_furnace", false);
	public static MythrilFurnace mythril_furnace_lit = new MythrilFurnace("mythril_furnace_lit", true);
	public static OnyxFurnace onyx_furnace = new OnyxFurnace("onyx_furnace", false);
	public static OnyxFurnace onyx_furnace_lit = new OnyxFurnace("onyx_furnace_lit", true);

	/**
	 * configure mod blocks from config settings.
	 */
	public static void configureBlocks() 
	{
		if (Settings.mythrilFurnace.isEnabled()) 
		{
			mythril_furnace.setConfigEntry(Settings.mythrilFurnace).setCreativeTab(TabHelper.redstoneTab(SimpleCoreAPI.plugin));
			mythril_furnace_lit.setConfigEntry(Settings.mythrilFurnace).setDropItem(true)
				.setItemToDrop(mythril_furnace.getItemToDrop());
			MythrilFurnace.setUnlit_furnace(mythril_furnace);
			MythrilFurnace.setLit_furnace(mythril_furnace_lit);
		}
		if (Settings.onyxFurnace.isEnabled())
		{
			onyx_furnace.setConfigEntry(Settings.onyxFurnace).setCreativeTab(TabHelper.redstoneTab(SimpleCoreAPI.plugin));
			onyx_furnace_lit.setConfigEntry(Settings.onyxFurnace).setDropItem(true)
				.setItemToDrop(onyx_furnace.getItemToDrop());
			OnyxFurnace.setUnlit_furnace(onyx_furnace);
			OnyxFurnace.setLit_furnace(onyx_furnace_lit);
		}
	} // end configureBlocks()

	/**
	 * Register blocks with Forge.
	 * 
	 * @param registry Forge block registry interface.
	 */
	public static void register(IForgeRegistry<Block> registry) 
	{
		if (Settings.mythrilFurnace.isEnabled()) {
			registry.register(mythril_furnace);
			registry.register(mythril_furnace_lit);
			TileEntity.register(MythrilFurnaceTileEntity.tilename, MythrilFurnaceTileEntity.class);
		}
		if (Settings.onyxFurnace.isEnabled()) {
			registry.register(onyx_furnace);
			registry.register(onyx_furnace_lit);
			TileEntity.register(OnyxFurnaceTileEntity.tilename, OnyxFurnaceTileEntity.class);
		}
	} // end register()
	
	/**
	 * register ItemBlocks with Forge.
	 * 
	 * @param registry Forge item registry interface.
	 */
	public static void registerItemBlocks(IForgeRegistry<Item> registry) 
	{
		if (Settings.mythrilFurnace.isEnabled()) {
			registry.register(mythril_furnace.createItemBlock());
		}
		if (Settings.onyxFurnace.isEnabled()) {
			registry.register(onyx_furnace.createItemBlock());
		}
	} // end registerItemBlocks()

	/**
	 * register models of ItemBlocks with Forge.
	 */
	public static void registerModels() 
	{
		if (Settings.mythrilFurnace.isEnabled()) {
			mythril_furnace.registerItemModel(Item.getItemFromBlock(mythril_furnace));
		}
		if (Settings.onyxFurnace.isEnabled()) {
			onyx_furnace.registerItemModel(Item.getItemFromBlock(onyx_furnace));
		}
	} // end registerModels()

} // end class
