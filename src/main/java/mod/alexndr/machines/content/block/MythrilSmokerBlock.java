package mod.alexndr.machines.content.block;

import java.util.Random;

import mod.alexndr.machines.api.content.AbstractModFurnaceBlock;
import mod.alexndr.machines.content.tile.MythrilSmokerTileEntity;
import mod.alexndr.machines.init.ModTileEntityTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.items.ItemStackHandler;

public class MythrilSmokerBlock extends AbstractModFurnaceBlock
{

    public MythrilSmokerBlock(Properties builder)
    {
        super(builder);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
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
    public void onReplaced(BlockState oldState, World worldIn, BlockPos pos, BlockState newState, boolean isMoving)
    {
       if (oldState.getBlock() != newState.getBlock()) 
       {
           TileEntity tileEntity = worldIn.getTileEntity(pos);
           if (tileEntity instanceof MythrilSmokerTileEntity) 
           {
               final ItemStackHandler inventory = ((MythrilSmokerTileEntity) tileEntity).inventory;
               for (int slot = 0; slot < inventory.getSlots(); ++slot)
                   InventoryHelper.spawnItemStack(worldIn, pos.getX(), pos.getY(), pos.getZ(), inventory.getStackInSlot(slot));
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
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player,
            Hand handIn, BlockRayTraceResult hit)
    {
        if (!worldIn.isRemote) 
        {
            final TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity instanceof MythrilSmokerTileEntity)
                NetworkHooks.openGui((ServerPlayerEntity) player, (MythrilSmokerTileEntity) tileEntity, pos);
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        if (stateIn.get(BURNING)) {
            double d0 = (double)pos.getX() + 0.5D;
            double d1 = (double)pos.getY();
            double d2 = (double)pos.getZ() + 0.5D;
            if (rand.nextDouble() < 0.1D) {
               worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_SMOKER_SMOKE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            worldIn.addParticle(ParticleTypes.SMOKE, d0, d1 + 1.1D, d2, 0.0D, 0.0D, 0.0D);
         }
     } // animateTick()

    @Override
    protected void interactWith(World worldIn, BlockPos pos, PlayerEntity player)
    {
        TileEntity tileentity = worldIn.getTileEntity(pos);
        if (tileentity instanceof MythrilSmokerTileEntity) {
           player.openContainer((INamedContainerProvider)tileentity);
           player.addStat(Stats.INTERACT_WITH_SMOKER);
        }
    }

} // end class
