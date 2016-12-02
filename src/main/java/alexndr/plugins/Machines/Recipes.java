package alexndr.plugins.Machines;

import alexndr.api.logger.LogHelper;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Recipes {
	
	public static void initialize() 
	{
        try {
            addRecipes();
            LogHelper.verbose("Machines", "All recipes were added successfully.");
        }
        catch (Exception e) {
            LogHelper.severe("Machines",
                            "Recipes were not added successfully. This is a serious problem!");
            e.printStackTrace();
        }
	}
	
	public static void addRecipes() 
	{
	    GameRegistry.addRecipe(new ShapedOreRecipe(Content.mythril_furnace, true,
                                        new Object[]
        { "XXX", "XYX", "XXX", Character.valueOf('X'), "ingotMythril", Character.valueOf('Y'),
                        Blocks.FURNACE }));
	    GameRegistry.addRecipe(new ShapedOreRecipe(Content.onyx_furnace, true,
                                        new Object[]
        { "XXX", "XYX", "XXX", Character.valueOf('X'), "gemOnyx", Character.valueOf('Y'),
                        Blocks.FURNACE }));
	}
} // end class Recipes
