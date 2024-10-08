package mod.alexndr.machines.datagen;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.simplecorelib.api.datagen.SimpleRecipeProvider;
import mod.alexndr.simplecorelib.api.helpers.TagUtils;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class MachinesRecipeProvider extends SimpleRecipeProvider
{


	public MachinesRecipeProvider(PackOutput pOutput,
								  CompletableFuture<HolderLookup.Provider> pRegistries)
	{
		super(pOutput, pRegistries, Machines.MODID);
	}

	@Override
	protected void buildRecipes(@NotNull RecipeOutput consumer)
	{
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.mythril_blast_furnace.get())
			.define('#', Blocks.SMOOTH_STONE)
			.define('X', Blocks.FURNACE)
			.define('I', TagUtils.cTag("ingots/mythril"))
			.pattern("III")
			.pattern("IXI")
			.pattern("###")
			.unlockedBy("has_item", has(ModBlocks.mythril_furnace.get()))
			.save(consumer);
					
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.mythril_furnace.get())
			.define('X', Blocks.FURNACE)
			.define('I', TagUtils.cTag("ingots/mythril"))
			.pattern("III")
			.pattern("IXI")
			.pattern("III")
			.unlockedBy("has_item", has(TagUtils.cTag("ingots/mythril")))
			.save(consumer);					
				
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.mythril_smoker.get())
			.define('X', ModBlocks.mythril_furnace.get())
			.define('#', ItemTags.LOGS)
			.pattern(" # ")
			.pattern("#X#")
			.pattern(" # ")
			.unlockedBy("has_item", has(ModBlocks.mythril_furnace.get()))
			.save(consumer);					
			
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.onyx_blast_furnace.get())
			.define('#', Blocks.SMOOTH_STONE)
			.define('X', Blocks.FURNACE)
			.define('I', TagUtils.cTag("gems/onyx"))
			.pattern("III")
			.pattern("IXI")
			.pattern("###")
			.unlockedBy("has_item", has(ModBlocks.onyx_furnace.get()))
			.save(consumer);
				
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.onyx_furnace.get())
			.define('X', Blocks.FURNACE)
			.define('I', TagUtils.cTag("gems/onyx"))
			.pattern("III")
			.pattern("IXI")
			.pattern("III")
			.unlockedBy("has_item", has(TagUtils.cTag("ingots/onyx")))
			.save(consumer);					
				
		ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.onyx_smoker.get())
			.define('X', ModBlocks.onyx_furnace.get())
			.define('#', ItemTags.LOGS)
			.pattern(" # ")
			.pattern("#X#")
			.pattern(" # ")
			.unlockedBy("has_item", has(ModBlocks.onyx_furnace.get()))
			.save(consumer);					

	} // end buildRecipes()

} // end class
