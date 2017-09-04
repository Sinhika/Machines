package alexndr.plugins.Machines;

import alexndr.api.config.ConfigHelper;
import alexndr.api.config.types.ConfigBlock;
import alexndr.api.logger.LogHelper;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Settings 
{
    private static Configuration settings = new Configuration();

    public static void createOrLoadSettings(FMLPreInitializationEvent event) 
    {
		settings = ConfigHelper.GetConfig(event, "AleXndr", ModInfo.ID + ".cfg");
		LogHelper.verbose(ModInfo.NAME, "loading settings...");
        try {
            settings.load();
			ConfigHelper.createHelpEntry(settings, ModInfo.URL);
            configureToggles();
            configureBlocks();
        } 
        catch(Exception e) {
            LogHelper.severe("Machines", "Settings failed to load correctly. The plugin may not function correctly.");
            e.printStackTrace();
        } 
        finally {
            settings.save();
            LogHelper.verbose("Machines", "Settings loaded successfully.");
        }
    } // end createOrLoadSettings()

    /**
     * create a "Toggles" category and populate it.
     */
    public static void configureToggles()
    {
    	settings.getBoolean("EnableColoredGUIs", Configuration.CATEGORY_GENERAL, true, 
    						"Show colored furnace backgrounds in GUI");
    } // end configureToggles()
    
    public static void configureBlocks()
    {
        // Blocks
        mythrilFurnace = new ConfigBlock("Mythril Furnace", ConfigHelper.CATEGORY_MACHINE)
                        .setHardness(3.5F).setResistance(10.0F).setLightValue(1.0F)
                        .setHarvestTool("pickaxe");
        mythrilFurnace.GetConfig(settings);
                        //.createNewValue("FuelMultiplier", "@I","2","2");
        
        mythrilFurnaceFuelMultiplier = settings.getInt("FuelMultiplier", mythrilFurnace.getSubCategory(), 
        									2, 1, 99, "Fuel burn time multiplier");
        
        onyxFurnace = new ConfigBlock("Onyx Furnace", "Blocks")
                        .setHardness(3.5F).setResistance(10.0F).setLightValue(1.0F)
                        .setHarvestTool("pickaxe");
        onyxFurnace.GetConfig(settings);
        onyxFurnaceYieldChance = settings.getInt("YieldChance", onyxFurnace.getSubCategory(), 
        		33, 1, 100, "Chance of smelting an extra item(s)");
        onyxFurnaceYieldAmount = settings.getInt("YieldAmount", onyxFurnace.getSubCategory(), 
        		1, 1, 64, "Number of extra items to get");
    } // end configureBlocks()
    
    public static ConfigBlock mythrilFurnace, onyxFurnace;
    public static int mythrilFurnaceFuelMultiplier, onyxFurnaceYieldAmount, onyxFurnaceYieldChance;
    public static boolean coloredGUIs;
} // end class Settings
