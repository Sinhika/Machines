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
		
		// item models
		this.itemModels().withExistingParent("mythril_blast_furnace", 
				new ResourceLocation(Machines.MODID, "block/mythril_blast_furnace"));

		// blockstates
		this.buildFurnaceBlockState(ModBlocks.mythril_blast_furnace.get(), mythrilBlastFurnace, mythrilBlastFurnace_lit);

	} // end registerStatesAndModels()

} // end-class
