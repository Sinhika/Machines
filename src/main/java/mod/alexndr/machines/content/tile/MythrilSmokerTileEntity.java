package mod.alexndr.machines.content.tile;

import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.init.ModTileEntityTypes;
import mod.alexndr.simplecorelib.content.VeryAbstractSmokerTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class MythrilSmokerTileEntity extends VeryAbstractSmokerTileEntity
{
    public MythrilSmokerTileEntity(BlockPos blockpos, BlockState blockstate) 
    {
        super(ModTileEntityTypes.mythril_smoker.get(), blockpos, blockstate);
        hasFuelMultiplier = true;
        fuelMultiplier = MachinesConfig.mythrilFurnaceFuelMultiplier; 
    }

} // end-class
