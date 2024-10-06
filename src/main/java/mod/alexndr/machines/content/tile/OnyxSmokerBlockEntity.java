package mod.alexndr.machines.content.tile;

import mod.alexndr.machines.api.content.AbstractYieldEnhancingSmokerBlockEntity;
import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.init.ModTileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class OnyxSmokerBlockEntity extends AbstractYieldEnhancingSmokerBlockEntity
{

    public OnyxSmokerBlockEntity(BlockPos blockpos, BlockState blockstate)
    {
        super(ModTileEntityTypes.onyx_smoker.get(), blockpos, blockstate);
        YieldChance = MachinesConfig.onyxFurnaceYieldChance;
        YieldAmount = MachinesConfig.onyxFurnaceYieldAmount;
     }

} // end class
