package alexndr.plugins.Machines;

import java.io.File;

import alexndr.api.config.Configuration;
import alexndr.api.config.types.ConfigBlock;
import alexndr.api.config.types.ConfigEntry;
import alexndr.api.config.types.ConfigValue;
import alexndr.api.logger.LogHelper;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Settings 
{
    private static Configuration settings = new Configuration();

    public static void createOrLoadSettings(FMLPreInitializationEvent event) 
    {
        settings.setModName(ModInfo.NAME);
        File configDir = new File(event.getModConfigurationDirectory(), "AleXndr");
        File settingsFile = new File(configDir, "Machines_Settings.xml");
        settings.setFile(settingsFile);

        LogHelper.verbose("Machines", "Loading Settings...");
        try {
            settings.load();
            settings.createHelpEntry(ModInfo.URL);
            
           //Toggles
            ConfigEntry toggles = new ConfigEntry("Machines Toggles", "Toggles");
            toggles.createNewValue("EnableColoredGUIs").setDataType("@B")
                            .setCurrentValue("true").setDefaultValue("true");
            toggles.createNewValue("EnableMythrilFurnace").setDataType("@B")
                            .setCurrentValue("true").setDefaultValue("true");
            toggles.createNewValue("EnableOnyxFurnace").setDataType("@B")
                            .setCurrentValue("true").setDefaultValue("true");
            toggles = settings.get(toggles);
            coloredGUIs = toggles.getValueByName("EnableColoredGUIs");
            enableMythrilFurnace = toggles.getValueByName("EnableMythrilFurnace");
            enableOnyxFurnace = toggles.getValueByName("EnableOnyxFurnace");

            // Blocks
            mythrilFurnace = settings.get(new ConfigBlock("Mythril Furnace", "Blocks")
                            .setHardness(3.5F).setResistance(10.0F).setLightValue(1.0F)
                            .setHarvestLevel(0).setHarvestTool("pickaxe")
                            .createNewValue("FuelMultiplier", "@I","2","2"))
                            .asConfigBlock();
            mythrilFurnaceFuelMultiplier = mythrilFurnace.getValueByName("FuelMultiplier");
            
            onyxFurnace = settings.get(new ConfigBlock("Onyx Furnace", "Blocks")
                            .setHardness(3.5F).setResistance(10.0F).setLightValue(1.0F)
                            .setHarvestLevel(0).setHarvestTool("pickaxe")
                             .createNewValue("YieldChance","@I","33","33")
                             .createNewValue("YieldAmount","@I","1","1")
                             ).asConfigBlock();
            onyxFurnaceYieldChance = onyxFurnace.getValueByName("YieldChance");
            onyxFurnaceYieldAmount = onyxFurnace.getValueByName("YieldAmount");
        } 
        catch(Exception e) {
            LogHelper.severe("Machines", "Settings failed to load correctly. The plugin may not function correctly.");
            e.printStackTrace();
        } 
        finally {
            settings.save();
            LogHelper.verbose("Machines", "Settings loaded successfully.");
        }
    }

    public static ConfigBlock mythrilFurnace, onyxFurnace;

    public static ConfigValue mythrilFurnaceFuelMultiplier, onyxFurnaceYieldAmount, onyxFurnaceYieldChance;

    public static ConfigValue coloredGUIs;
    public static ConfigValue enableMythrilFurnace, enableOnyxFurnace;
} // end class Settings
