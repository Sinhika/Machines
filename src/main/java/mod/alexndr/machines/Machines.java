package mod.alexndr.machines;

import mod.alexndr.machines.client.ClientModEventSubscriber;
import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModContainerTypes;
import mod.alexndr.machines.init.ModCreativeTabs;
import mod.alexndr.machines.init.ModTileEntityTypes;
import net.neoforged.bus.EventBus;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Cadiboo
 */
@Mod(Machines.MODID)
public final class Machines
{

	public static final String MODID = "simpleores_machines";

	public static final Logger LOGGER = LogManager.getLogger(MODID);

	public Machines(IEventBus modEventBus, ModContainer modContainer)
	{
		LOGGER.debug("Hello from Machines!");

		// register event listeners.
		modEventBus.addListener(ModEventSubscriber::onRegisterItems);
		modEventBus.addListener(MachinesConfig::onLoad);

		// client events
		modEventBus.addListener( RegisterMenuScreensEvent.class, ClientModEventSubscriber::onRegisterScreens);

		// Register Deferred Registers (Does not need to be before Configs)
		ModBlocks.BLOCKS.register(modEventBus);
		ModCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
		ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
		ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);

		// Register Configs (Does not need to be after Deferred Registers)
		modContainer.registerConfig(ModConfig.Type.STARTUP, MachinesConfig.SPEC);
	}

} // end class
