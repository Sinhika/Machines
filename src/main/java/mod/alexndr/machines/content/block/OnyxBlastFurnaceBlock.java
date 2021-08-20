package mod.alexndr.machines.content.block;

import mod.alexndr.machines.api.content.AbstractModBlastFurnaceBlock;
import mod.alexndr.machines.content.tile.OnyxBlastFurnaceTileEntity;
import mod.alexndr.machines.init.ModTileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.stats.Stats;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemStackHandler;

public class OnyxBlastFurnaceBlock extends AbstractModBlastFurnaceBlock
{
    // public static final Logger LOGGER = LogManager.getLogger(Machines.MODID);

    public OnyxBlastFurnaceBlock(Properties builder)
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
            if (tileEntity instanceof OnyxBlastFurnaceTileEntity) 
            {
                final ItemStackHandler inventory = ((OnyxBlastFurnaceTileEntity) tileEntity).inventory;
                for (int slot = 0; slot < inventory.getSlots(); ++slot)
                    Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
            }
        }
        super.onRemove(oldState, worldIn, pos, newState, isMoving);
    } // end onReplaced

	@Override
	public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState bstate, BlockEntityType<T> entityType) 
	{
		return createFurnaceTicker(level, entityType, ModTileEntityTypes.onyx_blast_furnace.get());
	}


	@Override
	public BlockEntity newBlockEntity(BlockPos bpos, BlockState bstate) {
		return new OnyxBlastFurnaceTileEntity(bpos, bstate);
	}


	@Override
	protected void openContainer(Level level, BlockPos bpos, Player player) 
	{
		BlockEntity blockentity = level.getBlockEntity(bpos);
		if (blockentity instanceof OnyxBlastFurnaceTileEntity)
		{
			player.openMenu((MenuProvider) blockentity);
			player.awardStat(Stats.INTERACT_WITH_FURNACE);
		}
	}
    

} // end class
