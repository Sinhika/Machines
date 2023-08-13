package mod.alexndr.machines.init;

import mod.alexndr.machines.Machines;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/**
 * This class holds all our ItemGroups (Formerly called CreativeTabs).
 * Static initialisers are fine here.
 *
 * @author Cadiboo
 */
public final class ModCreativeTabs {

//	public static final CreativeModeTab MOD_ITEM_GROUP = 
//	        new ModItemGroup(Machines.MODID, 
//	                        () -> new ItemStack(ModBlocks.mythril_furnace.get()));
	// formerly MOD_ITEM_GROUP
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = 
			DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Machines.MODID);
	
	public static final RegistryObject<CreativeModeTab> MACHINES_TAB = CREATIVE_MODE_TABS.register("machines_tab",
			() -> CreativeModeTab.builder()
				.title(Component.translatable("item_group." + Machines.MODID + ".tab"))
				.icon(() -> new ItemStack(ModBlocks.mythril_furnace.get().asItem()))
				.displayItems((parameters, output) -> {
					output.acceptAll(ModBlocks.BLOCKS.getEntries().stream()
										.map(RegistryObject::get)
										.map(b -> (new ItemStack(b.asItem())))
										.toList()
										);
//					output.acceptAll(ModItems.ITEMS.getEntries().stream()
//							.map(RegistryObject::get)
//							.map(b -> (new ItemStack(b)))
//							.toList()
//							);
				}).build());

} // end class
