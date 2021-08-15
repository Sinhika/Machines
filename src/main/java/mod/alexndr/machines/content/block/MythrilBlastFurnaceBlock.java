package mod.alexndr.machines.content.block;

import javax.annotation.Nullable;

import mod.alexndr.machines.api.content.AbstractModBlastFurnaceBlock;
import mod.alexndr.machines.content.tile.MythrilBlastFurnaceTileEntity;
import mod.alexndr.machines.init.ModTileEntityTypes;
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

public class MythrilBlastFurnaceBlock extends AbstractModBlastFurnaceBlock
{

    public MythrilBlastFurnaceBlock(Properties builder)
    {
        super(builder);
    }

    @Nullable
    @Override
    public BlockEntity createTileEntity(BlockState state, BlockGetter world)
    {
        return ModTileEntityTypes.mythril_blast_furnace.get().create();
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState oldState, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (oldState.getBlock() != newState.getBlock()) 
        {
            BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof MythrilBlastFurnaceTileEntity) 
            {
                final ItemStackHandler inventory = ((MythrilBlastFurnaceTileEntity) tileEntity).inventory;
                for (int slot = 0; slot < inventory.getSlots(); ++slot)
                    Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
            }
        }
        super.onRemove(oldState, worldIn, pos, newState, isMoving);
    } // end onReplaced

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player,
            InteractionHand handIn, BlockHitResult hit)
    {
        if (!worldIn.isClientSide) 
        {
            final BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof MythrilBlastFurnaceTileEntity) 
            {
                NetworkHooks.openGui((ServerPlayer) player, (MythrilBlastFurnaceTileEntity) tileEntity, pos);
                player.awardStat(Stats.INTERACT_WITH_BLAST_FURNACE);
            }
        }
        return InteractionResult.SUCCESS;
    }

} // end-class
