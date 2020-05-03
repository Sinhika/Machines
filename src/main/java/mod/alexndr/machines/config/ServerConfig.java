package mod.alexndr.machines.config;

import net.minecraftforge.common.ForgeConfigSpec;

/**
 * For configuration settings that change the behaviour of code on the LOGICAL SERVER.
 * This can be moved to an inner class of ExampleModConfig, but is separate because of personal preference and to keep the code organised
 *
 * @author Cadiboo
 */
final class ServerConfig {


	ServerConfig(final ForgeConfigSpec.Builder builder) {
		builder.push("general");
		builder.pop();
	}

} // end class
