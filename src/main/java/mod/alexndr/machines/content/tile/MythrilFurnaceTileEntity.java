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
public class MythrilFurnaceTileEntity extends AbstractModFurnaceTileEntity 
{
	public MythrilFurnaceTileEntity(BlockPos blockpos, BlockState blockstate) 
	{
		super(ModTileEntityTypes.mythril_furnace.get(), RecipeType.SMELTING, blockpos, blockstate);
        hasFuelMultiplier = true;
		fuelMultiplier = MachinesConfig.mythrilFurnaceFuelMultiplier;
	}

} // end class
