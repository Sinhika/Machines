package mod.alexndr.machines.content.block;

import javax.annotation.Nullable;

import mod.alexndr.machines.content.tile.OnyxFurnaceTileEntity;
import mod.alexndr.machines.init.ModTileEntityTypes;
import mod.alexndr.simplecorelib.content.VeryAbstractFurnaceBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.stats.Stats;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemStackHandler;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

/**
 * @author Cadiboo
 */
public class OnyxFurnaceBlock extends VeryAbstractFurnaceBlock 
{

	public OnyxFurnaceBlock(final Properties properties) 
	{
		super(properties);
	}

	@Nullable
	@Override
    public BlockEntity createTileEntity(final BlockState state, final BlockGetter world)
    {
        // Always use TileEntityType#create to allow registry overrides to work.
        return ModTileEntityTypes.onyx_furnace.get().create();
    }

	/**
	 * Called on the logical server when a BlockState with a TileEntity is replaced by another BlockState.
	 * We use this method to drop all the items from our tile entity's inventory and update comparators near our block.
	 *
	 * @deprecated Call via {@link BlockState#onReplaced(World, BlockPos, BlockState, boolean)}
	 * Implementing/overriding is fine.
	 */
	@Override
	public void onRemove(BlockState oldState, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) 
	{
		if (oldState.getBlock() != newState.getBlock()) 
		{
			BlockEntity tileEntity = worldIn.getBlockEntity(pos);
			if (tileEntity instanceof OnyxFurnaceTileEntity) {
				final ItemStackHandler inventory = ((OnyxFurnaceTileEntity) tileEntity).inventory;
				for (int slot = 0; slot < inventory.getSlots(); ++slot)
					Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
			}
		}
		super.onRemove(oldState, worldIn, pos, newState, isMoving);
	} // end onReplaced()

	/**
	 * Called when a player right clicks our block.
	 * We use this method to open our gui.
	 *
	 * @deprecated Call via {@link BlockState#onBlockActivated(World, PlayerEntity, Hand, BlockRayTraceResult)} whenever possible.
	 * Implementing/overriding is fine.
	 */
	@Override
	public InteractionResult use(final BlockState state, final Level worldIn, final BlockPos pos, final Player player, final InteractionHand handIn, final BlockHitResult hit) 
	{
        if (!worldIn.isClientSide)
        {
            final BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof OnyxFurnaceTileEntity) 
            {
                NetworkHooks.openGui((ServerPlayer) player, (OnyxFurnaceTileEntity) tileEntity, pos);
                player.awardStat(Stats.INTERACT_WITH_FURNACE);
            }
        }
		return InteractionResult.SUCCESS;
	}
	
} // end class
