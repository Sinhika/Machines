package mod.alexndr.machines.content.block;

import javax.annotation.Nullable;

import mod.alexndr.machines.api.content.AbstractModBlastFurnaceBlock;
import mod.alexndr.machines.content.tile.MythrilBlastFurnaceTileEntity;
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

public class MythrilBlastFurnaceBlock extends AbstractModBlastFurnaceBlock
{

    public MythrilBlastFurnaceBlock(Properties builder)
    {
        super(builder);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return ModTileEntityTypes.mythril_blast_furnace.get().create();
    }

    @Override
    public void onReplaced(BlockState oldState, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (oldState.getBlock() != newState.getBlock()) 
        {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof MythrilBlastFurnaceTileEntity) 
            {
                final ItemStackHandler inventory = ((MythrilBlastFurnaceTileEntity) tileEntity).inventory;
                for (int slot = 0; slot < inventory.getSlots(); ++slot)
                    InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
            }
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
            Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isRemote) 
        {
            final TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof MythrilBlastFurnaceTileEntity) 
            {
                NetworkHooks.openGui((ServerPlayerEntity) player, (MythrilBlastFurnaceTileEntity) tileEntity, pos);
                player.addStat(Stats.INTERACT_WITH_BLAST_FURNACE);
            }
        }
        return ActionResultType.SUCCESS;
    }

} // end-class
