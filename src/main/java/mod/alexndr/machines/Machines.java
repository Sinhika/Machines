package mod.alexndr.machines;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mod.alexndr.machines.config.ConfigHolder;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModContainerTypes;
import mod.alexndr.machines.init.ModCreativeTabs;
import mod.alexndr.machines.init.ModTileEntityTypes;

/**
 * @author Cadiboo
 */
@Mod(Machines.MODID)
public final class Machines {

	public static final String MODID = "simple_machines";

	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public Machines() {
		LOGGER.debug("Hello from Machines!");

		final ModLoadingContext modLoadingContext = ModLoadingContext.get();
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

		// Register Deferred Registers (Does not need to be before Configs)
		ModBlocks.BLOCKS.register(modEventBus);
		ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
		ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
		ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);

		// Register Configs (Does not need to be after Deferred Registers)
		modLoadingContext.registerConfig(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);
	}

} // end class
