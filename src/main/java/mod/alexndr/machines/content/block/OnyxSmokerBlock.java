package mod.alexndr.machines.content.block;

import com.mojang.serialization.MapCodec;
import mod.alexndr.machines.content.block_entity.OnyxSmokerBlockEntity;
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

public class OnyxSmokerBlock extends AbstractModSmokerBlock
{
    public static final MapCodec<OnyxSmokerBlock> CODEC = simpleCodec(OnyxSmokerBlock::new);

    public OnyxSmokerBlock(Properties builder)
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
        return OnyxSmokerBlock.createOnyxFurnaceTicker(level, entityType,
                ModTileEntityTypes.onyx_smoker.get());
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos bpos, BlockState bstate)
    {
        return new OnyxSmokerBlockEntity(bpos, bstate);
    }

    @Override
    protected void openContainer(Level level, BlockPos bpos, Player player)
    {
        BlockEntity blockentity = level.getBlockEntity(bpos);
        if (blockentity instanceof OnyxSmokerBlockEntity) {
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
    protected static <T extends BlockEntity> BlockEntityTicker<T> createOnyxFurnaceTicker (
            Level level, BlockEntityType<T> serverType,
            BlockEntityType<? extends OnyxSmokerBlockEntity> clientType)
    {
        return level.isClientSide
               ? null
               : createTickerHelper(serverType, clientType, OnyxSmokerBlockEntity::serverTick);
    }

} // end class
