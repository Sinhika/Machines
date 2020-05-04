package mod.alexndr.machines.config;

import net.minecraftforge.fml.config.ModConfig;

/**
 * This bakes the config values to normal fields
 *
 */
public final class ConfigHelper {

	public static void bakeClient(final ModConfig config) {
	}

	public static void bakeServer(final ModConfig config) 
	{
	    MachinesConfig.mythrilFurnaceFuelMultiplier = ConfigHolder.SERVER.mythrilFurnaceFuelMultiplier.get();
	    MachinesConfig.onyxFurnaceYieldChance = ConfigHolder.SERVER.onyxFurnaceYieldChance.get();
	    MachinesConfig.onyxFurnaceYieldAmount = ConfigHolder.SERVER.onyxFurnaceYieldAmount.get();
	}

} // end-class
