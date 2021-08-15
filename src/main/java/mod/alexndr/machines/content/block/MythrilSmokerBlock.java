package mod.alexndr.machines.content.block;

import mod.alexndr.machines.api.content.AbstractModSmokerBlock;
import mod.alexndr.machines.content.tile.MythrilSmokerTileEntity;
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

public class MythrilSmokerBlock extends AbstractModSmokerBlock
{

    public MythrilSmokerBlock(Properties builder)
    {
        super(builder);
    }

    @Override
    public BlockEntity createTileEntity(BlockState state, BlockGetter world)
    {
        // Always use TileEntityType#create to allow registry overrides to work.
        return ModTileEntityTypes.mythril_smoker.get().create();
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
           if (tileEntity instanceof MythrilSmokerTileEntity) 
           {
               final ItemStackHandler inventory = ((MythrilSmokerTileEntity) tileEntity).inventory;
               for (int slot = 0; slot < inventory.getSlots(); ++slot)
                   Containers.dropItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
           }
       }
       super.onRemove(oldState, worldIn, pos, newState, isMoving);
    }

   /**
    * Called when a player right clicks our block.
    * We use this method to open our gui.
    *
    * @deprecated Call via {@link BlockState#onBlockActivated(World, PlayerEntity, Hand, BlockRayTraceResult)} whenever possible.
    * Implementing/overriding is fine.
    */
    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player,
            InteractionHand handIn, BlockHitResult hit)
    {
        if (!worldIn.isClientSide) 
        {
            final BlockEntity tileEntity = worldIn.getBlockEntity(pos);
            if (tileEntity instanceof MythrilSmokerTileEntity)
            {
                NetworkHooks.openGui((ServerPlayer) player, (MythrilSmokerTileEntity) tileEntity, pos);
                player.awardStat(Stats.INTERACT_WITH_SMOKER);
            }
        }
        return InteractionResult.SUCCESS;
    }

} // end class
