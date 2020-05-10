package mod.alexndr.machines.content.block;

import mod.alexndr.machines.api.content.AbstractModBlastFurnaceBlock;
import mod.alexndr.machines.content.tile.OnyxBlastFurnaceTileEntity;
import mod.alexndr.machines.init.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemStackHandler;

public class OnyxBlastFurnaceBlock extends AbstractModBlastFurnaceBlock
{
    // public static final Logger LOGGER = LogManager.getLogger(Machines.MODID);

    public OnyxBlastFurnaceBlock(Properties builder)
    {
        super(builder);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return ModTileEntityTypes.onyx_blast_furnace.get().create();
    }

    @Override
    public void onReplaced(BlockState oldState, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {
        if (oldState.getBlock() != newState.getBlock()) 
        {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof OnyxBlastFurnaceTileEntity) 
            {
                final ItemStackHandler inventory = ((OnyxBlastFurnaceTileEntity) tileEntity).inventory;
                for (int slot = 0; slot < inventory.getSlots(); ++slot)
                    InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
            }
        }
    } // end onReplaced

    @Override
    public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
            Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isRemote) 
        {
            final TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof OnyxBlastFurnaceTileEntity) 
            {
                NetworkHooks.openGui((ServerPlayerEntity) player, (OnyxBlastFurnaceTileEntity) tileEntity, pos);
                player.addStat(Stats.INTERACT_WITH_BLAST_FURNACE);
            }
        }
        return true;
    }

} // end class
