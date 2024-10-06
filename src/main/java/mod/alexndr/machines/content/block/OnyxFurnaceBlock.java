package mod.alexndr.machines.content.block;

import com.mojang.serialization.MapCodec;
import mod.alexndr.machines.content.tile.OnyxFurnaceTileEntity;
import mod.alexndr.machines.init.ModTileEntityTypes;
import mod.alexndr.simplecorelib.api.content.SomewhatAbstractFurnaceBlock;
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

/**
 * @author Cadiboo
 */
public class OnyxFurnaceBlock extends SomewhatAbstractFurnaceBlock
{
    // private static final String DISPLAY_NAME = "block.simple_machines.onyx_furnace";
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
		return SomewhatAbstractFurnaceBlock.createCustomFurnaceTicker(level, entityType, ModTileEntityTypes.onyx_furnace.get());
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos bpos, BlockState bstate)
	{
		 return new OnyxFurnaceTileEntity(bpos, bstate);
	}

	@Override
	protected void openContainer(Level level, BlockPos bpos, Player player)
	{
		BlockEntity blockentity = pLevel.getBlockEntity(pPos);
		if (blockentity instanceof OnyxFurnaceTileEntity) {
			pPlayer.openMenu((MenuProvider)blockentity);
			pPlayer.awardStat(Stats.INTERACT_WITH_FURNACE);
		}
	}

} // end class
