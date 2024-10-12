package mod.alexndr.machines.content.block;

import com.mojang.serialization.MapCodec;
import mod.alexndr.machines.api.content.AbstractModFurnaceBlock;
import mod.alexndr.machines.content.block_entity.OnyxFurnaceTileEntity;
import mod.alexndr.machines.init.ModTileEntityTypes;
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

/**
 * @author Cadiboo
 */
public class OnyxFurnaceBlock extends AbstractModFurnaceBlock
{
    // private static final String DISPLAY_NAME = "block.simpleores_machines.onyx_furnace";
	public static final MapCodec<OnyxFurnaceBlock> CODEC = simpleCodec(OnyxFurnaceBlock::new);

	public OnyxFurnaceBlock(final Properties properties) 
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
		return OnyxFurnaceBlock.createOnyxFurnaceTicker(level, entityType, ModTileEntityTypes.onyx_furnace.get());
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos bpos, BlockState bstate)
	{
		 return new OnyxFurnaceTileEntity(bpos, bstate);
	}

	@Override
	protected void openContainer(Level level, BlockPos bpos, Player player)
	{
		BlockEntity blockentity = level.getBlockEntity(bpos);
		if (blockentity instanceof OnyxFurnaceTileEntity) {
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
	protected static <T extends BlockEntity> BlockEntityTicker<T> createOnyxFurnaceTicker(
			Level level, BlockEntityType<T> serverType,
			BlockEntityType<? extends OnyxFurnaceTileEntity> clientType)
	{
		return level.isClientSide
			   ? null
			   : createTickerHelper(serverType, clientType, OnyxFurnaceTileEntity::serverTick);
	}
} // end class
