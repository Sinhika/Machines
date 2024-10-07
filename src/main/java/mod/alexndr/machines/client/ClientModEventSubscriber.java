package mod.alexndr.machines.client;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.client.gui.*;
import mod.alexndr.machines.init.ModContainerTypes;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Subscribe to events from the MOD EventBus that should be handled on the PHYSICAL CLIENT side in this class
 *
 */
public final class ClientModEventSubscriber {

	private static final Logger LOGGER = LogManager.getLogger(Machines.MODID + " Client Mod Event Subscriber");

	/**
	 * We need to register our renderers on the client because rendering code does not exist on the server
	 * and trying to use it on a dedicated server will crash the game.
	 * <p>
	 * This method will be called by Forge when it is time for the mod to do its client-side setup
	 * This method will always be called after the Registry events.
	 * This means that all Blocks, Items, TileEntityTypes, etc. will all have been registered already
	 */
	public static void onRegisterScreens(RegisterMenuScreensEvent event)
	{
		event.register(ModContainerTypes.mythril_furnace.get(), MythrilFurnaceScreen::new);
		event.register(ModContainerTypes.mythril_blast_furnace.get(),
                                          MythrilBlastFurnaceScreen::new);
		event.register(ModContainerTypes.mythril_smoker.get(), MythrilSmokerScreen::new);
		event.register(ModContainerTypes.onyx_furnace.get(), OnyxFurnaceScreen::new);
		event.register(ModContainerTypes.onyx_blast_furnace.get(),
                                          OnyxBlastFurnaceScreen::new);
		event.register(ModContainerTypes.onyx_smoker.get(), OnyxSmokerScreen::new);
//			LOGGER.debug("Registered ContainerType Screens");

	}

} // end-class
