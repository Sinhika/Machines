package mod.alexndr.machines.datagen;

import java.util.function.Consumer;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.simplecorelib.api.datagen.ISimpleConditionBuilder;
import mod.alexndr.simplecorelib.api.helpers.TagUtils;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

public class MachinesRecipes extends RecipeProvider implements IConditionBuilder, ISimpleConditionBuilder 
{
 	public MachinesRecipes(PackOutput pOutput) {
		super(pOutput);
 	}

	@Override
	public ICondition flag(String arg0) {
		return impl_flag(Machines.MODID, MachinesConfig.INSTANCE, arg0);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) 
	{
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.mythril_blast_furnace.get())
			.define('#', Blocks.SMOOTH_STONE)
			.define('X', Blocks.FURNACE)
			.define('I', TagUtils.forgeTag("ingots/mythril"))
			.pattern("III")
			.pattern("IXI")
			.pattern("###")
			.unlockedBy("has_item", has(ModBlocks.mythril_furnace.get()))
			.save(consumer);
					
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.mythril_furnace.get())
			.define('X', Blocks.FURNACE)
			.define('I', TagUtils.forgeTag("ingots/mythril"))
			.pattern("III")
			.pattern("IXI")
			.pattern("III")
			.unlockedBy("has_item", has(TagUtils.forgeTag("ingots/mythril")))
			.save(consumer);					
				
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.mythril_smoker.get())
			.define('X', ModBlocks.mythril_furnace.get())
			.define('#', TagUtils.modTag("minecraft", "logs"))
			.pattern(" # ")
			.pattern("#X#")
			.pattern(" # ")
			.unlockedBy("has_item", has(ModBlocks.mythril_furnace.get()))
			.save(consumer);					
			
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.onyx_blast_furnace.get())
			.define('#', Blocks.SMOOTH_STONE)
			.define('X', Blocks.FURNACE)
			.define('I', TagUtils.forgeTag("gems/onyx"))
			.pattern("III")
			.pattern("IXI")
			.pattern("###")
			.unlockedBy("has_item", has(ModBlocks.onyx_furnace.get()))
			.save(consumer);
				
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.onyx_furnace.get())
			.define('X', Blocks.FURNACE)
			.define('I', TagUtils.forgeTag("gems/onyx"))
			.pattern("III")
			.pattern("IXI")
			.pattern("III")
			.unlockedBy("has_item", has(TagUtils.forgeTag("ingots/onyx")))
			.save(consumer);					
				
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.onyx_smoker.get())
			.define('X', ModBlocks.onyx_furnace.get())
			.define('#', TagUtils.modTag("minecraft", "logs"))
			.pattern(" # ")
			.pattern("#X#")
			.pattern(" # ")
			.unlockedBy("has_item", has(ModBlocks.onyx_furnace.get()))
			.save(consumer);					

	} // end buildRecipes()

} // end class
