package mod.alexndr.machines.content.block;

import com.mojang.serialization.MapCodec;
import mod.alexndr.machines.content.block_entity.MythrilBlastFurnaceTileEntity;
import mod.alexndr.machines.init.ModTileEntityTypes;
import mod.alexndr.simplecorelib.api.content.block.AbstractModBlastFurnaceBlock;
import mod.alexndr.simplecorelib.api.content.block.SomewhatAbstractFurnaceBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class MythrilBlastFurnaceBlock extends AbstractModBlastFurnaceBlock
{
   //  private static final String DISPLAY_NAME = "block.simple_machines.mythril_blast_furnace";
   public static final MapCodec<MythrilBlastFurnaceBlock> CODEC = simpleCodec(MythrilBlastFurnaceBlock::new);

    public MythrilBlastFurnaceBlock(final Properties properties)
    {
        super(properties);
    }

    @Override protected MapCodec<? extends AbstractFurnaceBlock> codec()
    {
        return CODEC;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState bstate, BlockEntityType<T> entityType)
    {
        return SomewhatAbstractFurnaceBlock.createCustomFurnaceTicker(level, entityType,
                ModTileEntityTypes.mythril_blast_furnace.get());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos bpos, BlockState bstate)
    {
        return new MythrilBlastFurnaceTileEntity(bpos, bstate);
    }

    @Override
    protected void openContainer(Level level, BlockPos bpos, Player player)
    {
        BlockEntity blockentity = level.getBlockEntity(bpos);
        if (blockentity instanceof MythrilBlastFurnaceTileEntity) {
            player.openMenu((MenuProvider)blockentity);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

} // end-class
