package mod.alexndr.machines.config;

import mod.alexndr.machines.Machines;
import net.minecraftforge.common.ForgeConfigSpec;

/**
 * For configuration settings that change the behaviour of code on the LOGICAL SERVER.
 * This can be moved to an inner class of MachinesConfig, but is separate because of personal preference and to keep the code organised
 *
 * @author Cadiboo
 */
final class ServerConfig {

    final ForgeConfigSpec.DoubleValue mythrilFurnaceFuelMultiplier;
    final ForgeConfigSpec.IntValue onyxFurnaceYieldChance;
    final ForgeConfigSpec.IntValue onyxFurnaceYieldAmount;


	ServerConfig(final ForgeConfigSpec.Builder builder) 
	{
		builder.push("general");
		mythrilFurnaceFuelMultiplier = builder
		     .comment("Multiplier for fuel duration in mythril furnace")
		    .translation(Machines.MODID + "config.mythrilFurnaceFuelMultiplier")
		    .defineInRange("mythrilFurnaceFuelMultiplier", 2.01, 1.00, 99.9);
		onyxFurnaceYieldChance = builder
		    .comment("Per cent (%) chance that onyx furnace will yield extra output")
		    .translation(Machines.MODID + "config.onyxFurnaceYieldChance")
		     .defineInRange("onyxFurnaceYieldChance", 33, 0, 100);
		onyxFurnaceYieldAmount = builder
		    .comment("Number of additional items yielded by onyx furnace")
		    .translation(Machines.MODID + "config.onyxFurnaceYieldAmount")
            .defineInRange("onyxFurnaceYieldAmount", 1, 0, 64);
		builder.pop();
	}

} // end class
