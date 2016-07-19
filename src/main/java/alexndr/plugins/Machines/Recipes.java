package alexndr.plugins.Machines;

import net.minecraft.init.Blocks;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.oredict.ShapedOreRecipe;
import alexndr.api.core.LogHelper;

public class Recipes {
	
	public static void initialize() {
		try{addRecipes(); LogHelper.verboseInfo("Machines", "All recipes were added successfully.");}
		catch(Exception e){LogHelper.severe("Machines", "Recipes were not added successfully. This is a serious problem!"); e.printStackTrace();}
	}
	
	public static void addRecipes() {
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.mythril_furnace, true, new Object[]{
				"XXX", "XYX", "XXX", Character.valueOf('X'), "ingotMythril", Character.valueOf('Y'), Blocks.furnace}));
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(Content.onyx_furnace, true, new Object[]{
				"XXX", "XYX", "XXX", Character.valueOf('X'), "gemOnyx", Character.valueOf('Y'), Blocks.furnace}));
	}
}
