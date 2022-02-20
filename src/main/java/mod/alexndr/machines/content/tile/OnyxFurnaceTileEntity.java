package mod.alexndr.machines.content.tile;

import mod.alexndr.machines.api.content.AbstractModFurnaceTileEntity;
import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.init.ModTileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 
 */
public class OnyxFurnaceTileEntity extends AbstractModFurnaceTileEntity 
{

	public OnyxFurnaceTileEntity(BlockPos blockpos, BlockState blockstate) 
	{
		super(ModTileEntityTypes.onyx_furnace.get(), RecipeType.SMELTING, blockpos, blockstate);
	    YieldChance = MachinesConfig.onyxFurnaceYieldChance;
	    YieldAmount = MachinesConfig.onyxFurnaceYieldAmount;
	}

} // end class
