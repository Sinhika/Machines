package mod.alexndr.machines.datagen;

import java.util.List;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.simplecorelib.datagen.MiningBlockTags;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTags extends MiningBlockTags
{
	public ModBlockTags(DataGenerator gen, ExistingFileHelper existingFileHelper)
	{
		super(gen, Machines.MODID, existingFileHelper);
	}

	@Override
	protected void addTags()
	{
        super.addTags();
	}

    protected void registerMiningTags()
    {			
    	// (mineable, stone, iron, diamond, netherite)
    	this.registerMineableTags(
    			List.of(ModBlocks.onyx_furnace.get(), ModBlocks.mythril_furnace.get(), ModBlocks.mythril_blast_furnace.get(), 
    					ModBlocks.onyx_blast_furnace.get(), ModBlocks.mythril_smoker.get(), ModBlocks.onyx_smoker.get()),  // mineable
    			List.of(), List.of(), List.of(), List.of());
    }

} // end class
