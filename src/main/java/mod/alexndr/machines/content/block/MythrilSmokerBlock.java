package mod.alexndr.machines.content.block;

import com.mojang.serialization.MapCodec;
import mod.alexndr.machines.content.block_entity.MythrilSmokerTileEntity;
import mod.alexndr.machines.init.ModTileEntityTypes;
import mod.alexndr.simplecorelib.api.content.block.AbstractModSmokerBlock;
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

import javax.annotation.Nullable;

public class MythrilSmokerBlock extends AbstractModSmokerBlock
{
    public static final MapCodec<MythrilSmokerBlock> CODEC = simpleCodec(MythrilSmokerBlock::new);

    public MythrilSmokerBlock(Properties builder)
    {
        super(builder);
    }

    @Override protected MapCodec<? extends AbstractFurnaceBlock> codec()
    {
        return CODEC;
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState bstate, BlockEntityType<T> entityType)
    {
        return createMythrilFurnaceTicker(level, entityType, ModTileEntityTypes.mythril_smoker.get());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos bpos, BlockState bstate)
    {
        return new MythrilSmokerTileEntity(bpos, bstate);
    }

    @Override
    protected void openContainer(Level level, BlockPos bpos, Player player)
    {
        BlockEntity blockentity = level.getBlockEntity(bpos);
        if (blockentity instanceof MythrilSmokerTileEntity) {
            player.openMenu((MenuProvider)blockentity);
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    /**
     * custom createFurnaceTicker. If you don't restrict the client type to the exact BlockEntity you want,
     * type-erasure will bite you on the ass.
     *
     * @param level
     * @param serverType
     * @param clientType
     * @return
     * @param <T>
     */
    @Nullable
    protected static <T extends BlockEntity> BlockEntityTicker<T> createMythrilFurnaceTicker (
            Level level, BlockEntityType<T> serverType,
            BlockEntityType<? extends MythrilSmokerTileEntity> clientType)
    {
        return level.isClientSide
               ? null
               : createTickerHelper(serverType, clientType, MythrilSmokerTileEntity::serverTick);
    }

} // end class
