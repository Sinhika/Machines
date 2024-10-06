package mod.alexndr.machines.content.tile;

import mod.alexndr.machines.api.content.AbstractYieldEnhancingBlastFurnaceBlockEntity;
import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.init.ModTileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class OnyxBlastFurnaceBlockEntity extends AbstractYieldEnhancingBlastFurnaceBlockEntity
{
    // private static final Logger LOGGER = LogManager.getLogger(Machines.MODID);

    public OnyxBlastFurnaceBlockEntity(BlockPos blockpos, BlockState blockstate)
    {
        super(ModTileEntityTypes.onyx_blast_furnace.get(), blockpos, blockstate);
        YieldChance = MachinesConfig.onyxFurnaceYieldChance;
        YieldAmount = MachinesConfig.onyxFurnaceYieldAmount;
    }

} // end class
