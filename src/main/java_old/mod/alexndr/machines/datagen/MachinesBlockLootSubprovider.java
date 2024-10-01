package mod.alexndr.machines.datagen;

import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.simplecorelib.api.datagen.SimpleBlockLootSubProvider;

public class MachinesBlockLootSubprovider extends SimpleBlockLootSubProvider 
{

	@Override
	protected void generate() 
	{
		this.dropNameableBlockEntity(ModBlocks.mythril_furnace.get());
		this.dropNameableBlockEntity(ModBlocks.mythril_blast_furnace.get());
		this.dropNameableBlockEntity(ModBlocks.mythril_smoker.get());
		this.dropNameableBlockEntity(ModBlocks.onyx_furnace.get());
		this.dropNameableBlockEntity(ModBlocks.onyx_blast_furnace.get());
		this.dropNameableBlockEntity(ModBlocks.onyx_smoker.get());
	}

} // end class
