package mod.alexndr.machines.content.block;

import mod.alexndr.machines.content.container.MythrilFurnaceContainer;
import mod.alexndr.machines.content.tile.MythrilFurnaceTileEntity;
import mod.alexndr.machines.init.ModTileEntityTypes;
import mod.alexndr.simplecorelib.content.VeryAbstractFurnaceBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;

/**
 * @author Cadiboo
 */
public class MythrilFurnaceBlock extends VeryAbstractFurnaceBlock 
{
    private static final String DISPLAY_NAME = "block.simple_machines.mythril_furnace";

	public MythrilFurnaceBlock(final Properties properties) 
	{
		super(properties);
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
			if (tileEntity instanceof MythrilFurnaceTileEntity) 
			{
				final ItemStackHandler inventory = ((MythrilFurnaceTileEntity) tileEntity).inventory;
				for (int slot = 0; slot < inventory.getSlots(); ++slot)
					Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
			}
		}
		super.onRemove(oldState, worldIn, pos, newState, isMoving);
	}


	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState bstate, BlockEntityType<T> entityType)
	{
		return createFurnaceTicker(level, entityType, ModTileEntityTypes.mythril_furnace.get());
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos bpos, BlockState bstate)
	{
		return new MythrilFurnaceTileEntity(bpos, bstate);
	}

	@Override
	protected void openContainer(Level level, BlockPos bpos, Player player)
	{
        BlockEntity be = level.getBlockEntity(bpos);
        if (be instanceof MythrilFurnaceTileEntity) 
        {
            MenuProvider containerProvider = new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return new TranslatableComponent(DISPLAY_NAME);
                }
                
                @Override
                public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity)
                {
                    return new MythrilFurnaceContainer(windowId, playerInventory, bpos, playerEntity);
                }
            }; // end anonymous-class
            NetworkHooks.openGui((ServerPlayer) player, containerProvider, be.getBlockPos());
            player.awardStat(Stats.INTERACT_WITH_FURNACE);
        } // end-if
        else {
            throw new IllegalStateException("Our named container provider is missing!");
        }
	}
	
} // end class
