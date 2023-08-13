package mod.alexndr.machines.datagen;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.simplecorelib.api.datagen.SimpleBlockStateProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class MachinesBlockStateProvider extends SimpleBlockStateProvider 
{

	public MachinesBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, Machines.MODID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() 
	{
		// block models
		ModelFile mythrilBlastFurnace = this.models().orientable("mythril_blast_furnace",
				new ResourceLocation(Machines.MODID, "block/mythril_blast_furnace_side"),
				new ResourceLocation(Machines.MODID, "block/mythril_blast_furnace_front"),
				new ResourceLocation(Machines.MODID, "block/mythril_blast_furnace_top"));
		
		ModelFile mythrilBlastFurnace_lit = this.models().orientable("mythril_blast_furnace_lit",
				new ResourceLocation(Machines.MODID, "block/mythril_blast_furnace_side"),
				new ResourceLocation(Machines.MODID, "block/mythril_blast_furnace_front_on"),
				new ResourceLocation(Machines.MODID, "block/mythril_blast_furnace_top"));

		ModelFile mythrilFurnace = this.models().orientable("mythril_furnace",
				new ResourceLocation(Machines.MODID, "block/mythril_furnace_side"),
				new ResourceLocation(Machines.MODID, "block/mythril_furnace_front"),
				new ResourceLocation(Machines.MODID, "block/mythril_furnace_top"));

		ModelFile mythrilFurnace_lit = this.models().orientable("mythril_furnace_lit",
				new ResourceLocation(Machines.MODID, "block/mythril_furnace_side"),
				new ResourceLocation(Machines.MODID, "block/mythril_furnace_front_lit"),
				new ResourceLocation(Machines.MODID, "block/mythril_furnace_top"));
		
		ModelFile mythrilSmoker = this.models().orientable("mythril_smoker",
				new ResourceLocation(Machines.MODID, "block/mythril_smoker_side"),
				new ResourceLocation(Machines.MODID, "block/mythril_smoker_front"),
				new ResourceLocation(Machines.MODID, "block/mythril_smoker_top"));

		ModelFile mythrilSmoker_lit = this.models().orientable("mythril_smoker_lit",
				new ResourceLocation(Machines.MODID, "block/mythril_smoker_side"),
				new ResourceLocation(Machines.MODID, "block/mythril_smoker_front_on"),
				new ResourceLocation(Machines.MODID, "block/mythril_smoker_top"));

		ModelFile onyxBlastFurnace = this.models().orientable("onyx_blast_furnace",
				new ResourceLocation(Machines.MODID, "block/onyx_blast_furnace_side"),
				new ResourceLocation(Machines.MODID, "block/onyx_blast_furnace_front"),
				new ResourceLocation(Machines.MODID, "block/onyx_blast_furnace_top"));
		
		ModelFile onyxBlastFurnace_lit = this.models().orientable("onyx_blast_furnace_lit",
				new ResourceLocation(Machines.MODID, "block/onyx_blast_furnace_side"),
				new ResourceLocation(Machines.MODID, "block/onyx_blast_furnace_front_on"),
				new ResourceLocation(Machines.MODID, "block/onyx_blast_furnace_top"));

		ModelFile onyxFurnace = this.models().orientable("onyx_furnace",
				new ResourceLocation(Machines.MODID, "block/onyx_furnace_side"),
				new ResourceLocation(Machines.MODID, "block/onyx_furnace_front"),
				new ResourceLocation(Machines.MODID, "block/onyx_furnace_top"));

		ModelFile onyxFurnace_lit = this.models().orientable("onyx_furnace_lit",
				new ResourceLocation(Machines.MODID, "block/onyx_furnace_side"),
				new ResourceLocation(Machines.MODID, "block/onyx_furnace_front_lit"),
				new ResourceLocation(Machines.MODID, "block/onyx_furnace_top"));
		
		ModelFile onyxSmoker = this.models().orientable("onyx_smoker",
				new ResourceLocation(Machines.MODID, "block/onyx_smoker_side"),
				new ResourceLocation(Machines.MODID, "block/onyx_smoker_front"),
				new ResourceLocation(Machines.MODID, "block/onyx_smoker_top"));

		ModelFile onyxSmoker_lit = this.models().orientable("onyx_smoker_lit",
				new ResourceLocation(Machines.MODID, "block/onyx_smoker_side"),
				new ResourceLocation(Machines.MODID, "block/onyx_smoker_front_on"),
				new ResourceLocation(Machines.MODID, "block/onyx_smoker_top"));

		
		// item models
		this.itemModels().withExistingParent("mythril_blast_furnace", 
				new ResourceLocation(Machines.MODID, "block/mythril_blast_furnace"));
		this.itemModels().withExistingParent("mythril_furnace", 
				new ResourceLocation(Machines.MODID, "block/mythril_furnace"));
		this.itemModels().withExistingParent("mythril_smoker", 
				new ResourceLocation(Machines.MODID, "block/mythril_smoker"));

		this.itemModels().withExistingParent("onyx_blast_furnace", 
				new ResourceLocation(Machines.MODID, "block/onyx_blast_furnace"));
		this.itemModels().withExistingParent("onyx_furnace", 
				new ResourceLocation(Machines.MODID, "block/onyx_furnace"));
		this.itemModels().withExistingParent("onyx_smoker", 
				new ResourceLocation(Machines.MODID, "block/onyx_smoker"));

		// blockstates
		this.buildFurnaceBlockState(ModBlocks.mythril_blast_furnace.get(), mythrilBlastFurnace, mythrilBlastFurnace_lit);
		this.buildFurnaceBlockState(ModBlocks.mythril_furnace.get(), mythrilFurnace, mythrilFurnace_lit);
		this.buildFurnaceBlockState(ModBlocks.mythril_smoker.get(), mythrilSmoker, mythrilSmoker_lit);
		this.buildFurnaceBlockState(ModBlocks.onyx_blast_furnace.get(), onyxBlastFurnace, onyxBlastFurnace_lit);
		this.buildFurnaceBlockState(ModBlocks.onyx_furnace.get(), onyxFurnace, onyxFurnace_lit);
		this.buildFurnaceBlockState(ModBlocks.onyx_smoker.get(), onyxSmoker, onyxSmoker_lit);

	} // end registerStatesAndModels()

} // end-class
