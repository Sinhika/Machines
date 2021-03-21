package mod.alexndr.machines.content.block;

import javax.annotation.Nullable;

import mod.alexndr.machines.api.content.AbstractModFurnaceBlock;
import mod.alexndr.machines.content.tile.MythrilFurnaceTileEntity;
import mod.alexndr.machines.init.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemStackHandler;

/**
 * @author Cadiboo
 */
public class MythrilFurnaceBlock extends AbstractModFurnaceBlock 
{

	public MythrilFurnaceBlock(final Properties properties) 
	{
		super(properties);
	}

	@Nullable
	@Override
	public TileEntity createTileEntity(final BlockState state, final IBlockReader world) 
	{
		// Always use TileEntityType#create to allow registry overrides to work.
		return ModTileEntityTypes.mythril_furnace.get().create();
	}

	/**
	 * Called on the logical server when a BlockState with a TileEntity is replaced by another BlockState.
	 * We use this method to drop all the items from our tile entity's inventory and update comparators near our block.
	 *
	 * @deprecated Call via {@link BlockState#onReplaced(World, BlockPos, BlockState, boolean)}
	 * Implementing/overriding is fine.
	 */
	@Override
	public void onRemove(BlockState oldState, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) 
	{
		if (oldState.getBlock() != newState.getBlock()) 
		{
			TileEntity tileEntity = worldIn.getBlockEntity(pos);
			if (tileEntity instanceof MythrilFurnaceTileEntity) 
			{
				final ItemStackHandler inventory = ((MythrilFurnaceTileEntity) tileEntity).inventory;
				for (int slot = 0; slot < inventory.getSlots(); ++slot)
					InventoryHelper.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
			}
		}
	}

	/**
	 * Called when a player right clicks our block.
	 * We use this method to open our gui.
	 *
	 * @deprecated Call via {@link BlockState#onBlockActivated(World, PlayerEntity, Hand, BlockRayTraceResult)} whenever possible.
	 * Implementing/overriding is fine.
	 */
	@Override
	public ActionResultType use(final BlockState state, final World worldIn, final BlockPos pos, final PlayerEntity player, final Hand handIn, final BlockRayTraceResult hit) 
	{
		if (!worldIn.isClientSide) 
		{
			final TileEntity tileEntity = worldIn.getBlockEntity(pos);
			if (tileEntity instanceof MythrilFurnaceTileEntity) 
			{
				NetworkHooks.openGui((ServerPlayerEntity) player, (MythrilFurnaceTileEntity) tileEntity, pos);
	            player.awardStat(Stats.INTERACT_WITH_FURNACE);
			}
		}
		return ActionResultType.SUCCESS;
	}
	
} // end class
