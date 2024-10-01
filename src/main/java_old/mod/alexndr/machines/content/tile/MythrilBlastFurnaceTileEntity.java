package mod.alexndr.machines.content.tile;

import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.init.ModTileEntityTypes;
import mod.alexndr.simplecorelib.api.content.VeryAbstractBlastFurnaceTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

public class MythrilBlastFurnaceTileEntity extends VeryAbstractBlastFurnaceTileEntity
{

    public MythrilBlastFurnaceTileEntity( BlockPos blockpos, BlockState blockstate)
    {
        super(ModTileEntityTypes.mythril_blast_furnace.get(), blockpos, blockstate);
        hasFuelMultiplier = true;
        fuelMultiplier = MachinesConfig.mythrilFurnaceFuelMultiplier;
    }


} // end class
