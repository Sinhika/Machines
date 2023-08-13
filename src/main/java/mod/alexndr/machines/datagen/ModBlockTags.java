package mod.alexndr.machines.datagen;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import mod.alexndr.machines.Machines;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.simplecorelib.api.datagen.MiningBlockTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTags extends MiningBlockTags
{
	public ModBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
			ExistingFileHelper existingFileHelper)
	{
		super(output, lookupProvider, Machines.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(Provider pProvider)
	{
        super.addTags(pProvider);
	}

    @Override
    protected void registerMiningTags()
    {			
    	// (mineable, stone, iron, diamond, netherite)
    	this.registerMineableTags(
    			List.of(ModBlocks.onyx_furnace.get(), ModBlocks.mythril_furnace.get(), ModBlocks.mythril_blast_furnace.get(), 
    					ModBlocks.onyx_blast_furnace.get(), ModBlocks.mythril_smoker.get(), ModBlocks.onyx_smoker.get()),  // mineable
    			List.of(), List.of(), List.of(), List.of());
    }

} // end class
