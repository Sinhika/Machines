package alexndr.plugins.Machines;

import java.io.File;

import alexndr.api.config.Configuration;
import alexndr.api.config.types.ConfigBlock;
import alexndr.api.config.types.ConfigEntry;
import alexndr.api.config.types.ConfigGeneric;
import alexndr.api.config.types.ConfigValue;
import alexndr.api.core.LogHelper;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class Settings {

		private static Configuration settings = new Configuration();
		
		public static void createOrLoadSettings(FMLPreInitializationEvent event) {
			settings.setModName("Machines");
			File configDir = new File(event.getModConfigurationDirectory(), "AleXndr");
			File settingsFile = new File(configDir, "Machines Settings.xml");
			settings.setFile(settingsFile);
			
			LogHelper.verboseInfo("Machines", "Loading Settings...");
			try {
				//Toggles
				ConfigEntry toggles = settings.add(new ConfigGeneric("Machines Toggles", "ModSettings"));
					updateChecker = toggles.createNewValue("EnableUpdateChecker").setCurrentValue("true").setDefaultValue("true").setComment("Enables the update checker for Machines.").setCommentIndentNumber(3);
					coloredGUIs = toggles.createNewValue("EnableColoredGUIs").setCurrentValue("true").setDefaultValue("true").setComment("Enables colored GUIs for Machines.");
				
				ConfigEntry contentToggles = settings.add(new ConfigGeneric("Content Toggles", "ModSettings"));
					enableMythrilFurnace = contentToggles.createNewValue("EnableMythrilFurnace").setCurrentValue("true").setDefaultValue("true").setComment("Enables the Mythril Furnace.").setCommentIndentNumber(2);
					enableOnyxFurnace = contentToggles.createNewValue("EnableOnyxFurnace").setCurrentValue("true").setDefaultValue("true").setComment("Enables the Onyx Furnace.");
				
				//Blocks
				mythrilFurnace = settings.add(new ConfigBlock("Mythril Furnace", "Blocks").setHardness(3.5F).setResistance(10.0F).setLightValue(1.0F).setHarvestTool("pickaxe"));
					mythrilFurnaceFuelMultiplier = mythrilFurnace.createNewValue("FuelMultiplier").setCurrentValue("2").setDefaultValue("2").setMaximumValue("0").setMaximumValue("255")
							.setComment("Fuel length multiplier for the Mythril Furnace.").setCommentIndentNumber(3);
				onyxFurnace = settings.add(new ConfigBlock("Onyx Furnace", "Blocks").setHardness(3.5F).setResistance(10.0F).setLightValue(1.0F).setHarvestTool("pickaxe"));
					onyxFurnaceYieldChance = onyxFurnace.createNewValue("YieldChance").setCurrentValue("33").setDefaultValue("33").setMinimumValue("0").setMaximumValue("100")
							.setComment("Chance for the Onyx Furnace to yield an extra result.");
					onyxFurnaceYieldAmount = onyxFurnace.createNewValue("YieldAmount").setCurrentValue("1").setDefaultValue("1").setMinimumValue("0").setMaximumValue("63")
							.setComment("Number of extra results the Onyx Furnace can yield");
				
				settings.load();
			} catch(Exception e) {
				LogHelper.severe("Machines", "Settings failed to load correctly. The plugin may not function correctly.");
				e.printStackTrace();
			} finally {
				settings.save();
				LogHelper.verboseInfo("Machines", "Settings loaded successfully.");
			}
		}
		
		public static ConfigBlock mythrilFurnace, onyxFurnace;
		
		public static ConfigValue mythrilFurnaceFuelMultiplier, onyxFurnaceYieldAmount, onyxFurnaceYieldChance;
		
		public static ConfigValue updateChecker, coloredGUIs;
		public static ConfigValue enableMythrilFurnace, enableOnyxFurnace;
}
