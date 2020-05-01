package alexndr.plugins.machines.blocks;

import java.util.Random;

import alexndr.api.content.blocks.SimpleFurnace;
import alexndr.api.registry.ContentCategories;
import alexndr.plugins.machines.Machines;
import alexndr.plugins.machines.Settings;
import alexndr.plugins.machines.helpers.FancyFurnaceGuiHandler;
import alexndr.plugins.machines.tiles.OnyxFurnaceTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class OnyxFurnace extends SimpleFurnace<OnyxFurnaceTileEntity>
{
	// repeat for custom furnace classes
	private static Block unlit_furnace;
	private static Block lit_furnace;
	
    public static int YieldChance = 33;
    public static int YieldAmount = 1;
    
    public OnyxFurnace(String furnace_name, boolean is_active)
    {
        super(furnace_name, Machines.plugin, Material.IRON, ContentCategories.Block.MACHINE, is_active);
    } // end ctor

    /* (non-Javadoc)
 	 * @see alexndr.api.content.blocks.SimpleFurnace#getItemDropped(net.minecraft.block.state.IBlockState, java.util.Random, int)
 	 */
 	@Override
 	public Item getItemDropped(IBlockState state, Random rand, int fortune) 
 	{
 		return Item.getItemFromBlock(OnyxFurnace.unlit_furnace);
 	}

    @Override
    public void setAdditionalProperties()
    {
        super.setAdditionalProperties();
            OnyxFurnace.YieldChance = Settings.onyxFurnaceYieldChance;
            OnyxFurnace.YieldAmount = Settings.onyxFurnaceYieldAmount;
    } // end setAdditionalProperties


    /* must be customized for children of SimpleFurnace */
    public static Block getUnlit_furnace() {
        return OnyxFurnace.unlit_furnace;
    }

    /* must be customized for children of SimpleFurnace */
    public static Block getLit_furnace()  {
        return OnyxFurnace.lit_furnace;
    }

	public static void setUnlit_furnace(Block unlit_furnace) {
		OnyxFurnace.unlit_furnace = unlit_furnace;
	}

	public static void setLit_furnace(Block lit_furnace) {
		OnyxFurnace.lit_furnace = lit_furnace;
	}

    @Override
    public TileEntity createNewTileEntity(World arg0, int arg1)
    {
        return new OnyxFurnaceTileEntity();
    }

	@Override
	public Class<OnyxFurnaceTileEntity> getTileEntityClass() {
		return OnyxFurnaceTileEntity.class;
	}

	@Override
	public OnyxFurnaceTileEntity createTileEntity(World world, IBlockState state) {
        return new OnyxFurnaceTileEntity();
	}

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState arg2,
                    EntityPlayer playerIn, EnumHand arg4, EnumFacing arg6,
                    float arg7, float arg8, float arg9)
    {
        if (worldIn.isRemote) {
            return true;
        }
        else {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof OnyxFurnaceTileEntity) 
            {
                playerIn.openGui(Machines.INSTANCE, FancyFurnaceGuiHandler.ONYX_FURNACE_TILE_ID,
                                worldIn, pos.getX(), pos.getY(), pos.getZ());
                playerIn.addStat(StatList.FURNACE_INTERACTION);
            }
            return true;
        }
    } // end onBlockActivated


    /**
     * Mostly cut & pasted from BlockFurnace. This *MUST* be overridden for
     * custom classes...
     * 
     * @param active
     * @param worldIn
     * @param pos
     */
    public static void setState(boolean active, World worldIn, BlockPos pos)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);

        keepInventory = true;

        if (active) {
            worldIn.setBlockState(pos, OnyxFurnace.lit_furnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, OnyxFurnace.lit_furnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }
        else {
            worldIn.setBlockState(pos, OnyxFurnace.unlit_furnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, OnyxFurnace.unlit_furnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }

        keepInventory = false;

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    } // end setState()

} // end class OnyxFurnace
