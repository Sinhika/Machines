package mod.alexndr.machines.content;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemHandlerHelper;

public abstract class AbstractModFurnaceBlock extends HorizontalBlock
{

    public static final BooleanProperty BURNING = BooleanProperty.create("lit");

    public AbstractModFurnaceBlock(Properties builder)
    {
        super(builder);
        
        // Set the default values for our blockstate properties
        this.setDefaultState(this.getDefaultState()
                .with(HORIZONTAL_FACING, Direction.NORTH)
                .with(BURNING, false)
        );
    }

    @Override
    public boolean hasTileEntity(final BlockState state)
    {
    	return true;
    }

    @Nullable
    @Override
    public abstract TileEntity createTileEntity(final BlockState state, final IBlockReader world);

    /**
     * Amount of light emitted
     *
     * @deprecated Call via {@link BlockState#getLightValue())}
     * Implementing/overriding is fine.
     */
    public int getLightValue(BlockState state)
    {
    	return state.get(BURNING) ? super.getLightValue(state) : 0;
    }

    /**
     * Called on the logical server when a BlockState with a TileEntity is replaced by another BlockState.
     * We use this method to drop all the items from our tile entity's inventory and update comparators near our block.
     *
     * @deprecated Call via {@link BlockState#onReplaced(World, BlockPos, BlockState, boolean)}
     * Implementing/overriding is fine.
     */
    @Override
    public abstract void onReplaced(BlockState oldState, World worldIn, BlockPos pos, BlockState newState, boolean isMoving);

    /**
     * Called when a player right clicks our block.
     * We use this method to open our gui.
     *
     * @deprecated Call via {@link BlockState#onBlockActivated(World, PlayerEntity, Hand, BlockRayTraceResult)} whenever possible.
     * Implementing/overriding is fine.
     */
    @Override
    public abstract ActionResultType onBlockActivated(final BlockState state, final World worldIn, final BlockPos pos, final PlayerEntity player, final Hand handIn, final BlockRayTraceResult hit);

    /**
     * Makes the block face the player when placed
     */
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context)
    {
    	return this.getDefaultState().with(HORIZONTAL_FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    /**
     * We return the redstone calculated from our inventory
     *
     * @deprecated call via {@link BlockState#getComparatorInputOverride(World, BlockPos)} whenever possible.
     * Implementing/overriding is fine.
     */
    @Override
    public int getComparatorInputOverride(BlockState blockState, World worldIn, BlockPos pos)
    {
    	final TileEntity tileEntity = worldIn.getTileEntity(pos);
    	if (tileEntity instanceof MythrilFurnaceTileEntity)
    		return ItemHandlerHelper.calcRedstoneFromInventory(((MythrilFurnaceTileEntity) tileEntity).inventory);
    	return super.getComparatorInputOverride(blockState, worldIn, pos);
    }

    /**
     * Called from inside the constructor {@link Block#Block(Properties)} to add all the properties to our blockstate
     */
    @Override
    protected void fillStateContainer(final StateContainer.Builder<Block, BlockState> builder)
    {
    	super.fillStateContainer(builder);
    	builder.add(HORIZONTAL_FACING);
    	builder.add(BURNING);
    }

    /**
     * Returns the blockstate with the given rotation from the passed blockstate.
     * If inapplicable, returns the passed blockstate.
     *
     * @deprecated call via {@link BlockState#rotate(Rotation)} whenever possible. Implementing/overriding is fine.
     */
    @Override
    public BlockState rotate(BlockState state, Rotation rot)
    {
    	return state.with(HORIZONTAL_FACING, rot.rotate(state.get(HORIZONTAL_FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate.
     * If inapplicable, returns the passed blockstate.
     *
     * @deprecated call via {@link BlockState#mirror(Mirror)} whenever possible. Implementing/overriding is fine.
     */
    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn)
    {
    	return state.rotate(mirrorIn.toRotation(state.get(HORIZONTAL_FACING)));
    }

    /**
        * Called periodically clientside on blocks near the player to show effects (like furnace fire particles). Note that
        * this method is unrelated to {@link randomTick} and {@link #needsRandomTick}, and will always be called regardless
        * of whether the block can receive random update ticks
        */
    @OnlyIn(Dist.CLIENT)
    @Override
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
    	      if (stateIn.get(BURNING)) {
    	         double d0 = (double)pos.getX() + 0.5D;
    	         double d1 = (double)pos.getY();
    	         double d2 = (double)pos.getZ() + 0.5D;
    	         if (rand.nextDouble() < 0.1D) {
    	            worldIn.playSound(d0, d1, d2, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
    	         }
    
    	         Direction direction = stateIn.get(HORIZONTAL_FACING);
    	         Direction.Axis direction$axis = direction.getAxis();
    //	         double d3 = 0.52D;
    	         double d4 = rand.nextDouble() * 0.6D - 0.3D;
    	         double d5 = direction$axis == Direction.Axis.X ? (double)direction.getXOffset() * 0.52D : d4;
    	         double d6 = rand.nextDouble() * 6.0D / 16.0D;
    	         double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getZOffset() * 0.52D : d4;
    	         worldIn.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
    	         worldIn.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
    	      }
    	   } // end animateTick()

}