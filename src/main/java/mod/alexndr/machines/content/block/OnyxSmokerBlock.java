package mod.alexndr.machines.content.block;

import mod.alexndr.machines.api.content.AbstractModSmokerBlock;
import mod.alexndr.machines.content.container.OnyxSmokerContainer;
import mod.alexndr.machines.content.tile.OnyxSmokerTileEntity;
import mod.alexndr.machines.init.ModTileEntityTypes;
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

public class OnyxSmokerBlock extends AbstractModSmokerBlock
{
    private static final String DISPLAY_NAME = "block.simple_machines.onyx_smoker";

    public OnyxSmokerBlock(Properties builder)
    {
        super(builder);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState oldState, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (oldState.getBlock() != newState.getBlock()) 
        {
            BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof OnyxSmokerTileEntity) 
            {
                final ItemStackHandler inventory = ((OnyxSmokerTileEntity) tileEntity).inventory;
                for (int slot = 0; slot < inventory.getSlots(); ++slot)
                    Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
            }
        }
        super.onRemove(oldState, worldIn, pos, newState, isMoving);
    } // end onReplaced

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState bstate, BlockEntityType<T> entityType) 
	{
		return createFurnaceTicker(level, entityType, ModTileEntityTypes.onyx_smoker.get());
	}


	@Override
	public BlockEntity newBlockEntity(BlockPos bpos, BlockState bstate) {
		return new OnyxSmokerTileEntity(bpos, bstate);
	}


	@Override
	protected void openContainer(Level level, BlockPos bpos, Player player) 
	{
        BlockEntity be = level.getBlockEntity(bpos);
        if (be instanceof OnyxSmokerTileEntity) 
        {
            MenuProvider containerProvider = new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return new TranslatableComponent(DISPLAY_NAME);
                }
                
                @Override
                public AbstractContainerMenu createMenu(int windowId, Inventory playerInventory, Player playerEntity)
                {
                    return new OnyxSmokerContainer(windowId, playerInventory, bpos, playerEntity);
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
