package mod.alexndr.machines.client;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.client.gui.MythrilBlastFurnaceScreen;
import mod.alexndr.machines.client.gui.MythrilFurnaceScreen;
import mod.alexndr.machines.client.gui.MythrilSmokerScreen;
import mod.alexndr.machines.client.gui.OnyxBlastFurnaceScreen;
import mod.alexndr.machines.client.gui.OnyxFurnaceScreen;
import mod.alexndr.machines.client.gui.OnyxSmokerScreen;
import mod.alexndr.machines.init.ModContainerTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Subscribe to events from the MOD EventBus that should be handled on the PHYSICAL CLIENT side in this class
 *
 * @author Cadiboo
 */
@SuppressWarnings("deprecation")
@EventBusSubscriber(modid = Machines.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
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
	@SubscribeEvent
	public static void onFMLClientSetupEvent(final FMLClientSetupEvent event) 
	{
		// Register ContainerType Screens
		// ScreenManager.registerFactory is not safe to call during parallel mod loading so we queue it to run later
		DeferredWorkQueue.runLater(() -> {
            ScreenManager.registerFactory(ModContainerTypes.mythril_furnace.get(), MythrilFurnaceScreen::new);
            ScreenManager.registerFactory(ModContainerTypes.mythril_blast_furnace.get(), 
                                          MythrilBlastFurnaceScreen::new);
            ScreenManager.registerFactory(ModContainerTypes.mythril_smoker.get(), MythrilSmokerScreen::new);
            ScreenManager.registerFactory(ModContainerTypes.onyx_furnace.get(), OnyxFurnaceScreen::new);
            ScreenManager.registerFactory(ModContainerTypes.onyx_blast_furnace.get(), 
                                          OnyxBlastFurnaceScreen::new);
            ScreenManager.registerFactory(ModContainerTypes.onyx_smoker.get(), OnyxSmokerScreen::new);
			LOGGER.debug("Registered ContainerType Screens");
		});

	}

} // end-class
