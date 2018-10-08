package alexndr.plugins.machines.blocks;

import alexndr.api.content.blocks.SimpleFurnace;
import alexndr.api.registry.ContentCategories;
import alexndr.plugins.machines.Machines;
import alexndr.plugins.machines.Settings;
import alexndr.plugins.machines.helpers.FancyFurnaceGuiHandler;
import alexndr.plugins.machines.tiles.MythrilFurnaceTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class MythrilFurnace extends SimpleFurnace<MythrilFurnaceTileEntity>
{
	// repeat for custom furnace classes
	private static Block unlit_furnace;
	private static Block lit_furnace;
	
    public static int FuelMultiplier = 2;
    
    public MythrilFurnace(String furnace_name, boolean is_active)
    {
        super(furnace_name, Machines.plugin, Material.IRON, ContentCategories.Block.MACHINE, is_active);
    } // end ctor

    @Override
    public void setAdditionalProperties()
    {
        super.setAdditionalProperties();
        MythrilFurnace.FuelMultiplier = Settings.mythrilFurnaceFuelMultiplier;
    } // end setAdditionalProperties

    /* must be customized for children of SimpleFurnace */
    public static Block getUnlit_furnace() {
        return MythrilFurnace.unlit_furnace;
    }

    /* must be customized for children of SimpleFurnace */
    public static Block getLit_furnace()  {
        return MythrilFurnace.lit_furnace;
    }

	public static void setUnlit_furnace(Block unlit_furnace) {
		MythrilFurnace.unlit_furnace = unlit_furnace;
	}

	public static void setLit_furnace(Block lit_furnace) {
		MythrilFurnace.lit_furnace = lit_furnace;
	}

    @Override
    public TileEntity createNewTileEntity(World arg0, int arg1)
    {
        return new MythrilFurnaceTileEntity();
    }

    @Override
	public MythrilFurnaceTileEntity createTileEntity(World world, IBlockState state) {
        return new MythrilFurnaceTileEntity();
	}

	@Override
	public Class<MythrilFurnaceTileEntity> getTileEntityClass() {
		return MythrilFurnaceTileEntity.class;
	}

    /* cut & pasted from BlockFurnace & modified per CompatBlock */
   @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState arg2,
                    EntityPlayer playerIn, EnumHand hand, EnumFacing arg6,
                    float arg7, float arg8, float arg9)
    {
        if (worldIn.isRemote) {
            return true;
        }
        else {
            TileEntity tileentity = worldIn.getTileEntity(pos);

            if (tileentity instanceof MythrilFurnaceTileEntity) 
            {
                playerIn.openGui(Machines.INSTANCE, FancyFurnaceGuiHandler.MYTHRIL_FURNACE_TILE_ID,
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
            worldIn.setBlockState(pos, MythrilFurnace.lit_furnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, MythrilFurnace.lit_furnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }
        else {
            worldIn.setBlockState(pos, MythrilFurnace.unlit_furnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, MythrilFurnace.unlit_furnace.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }

        keepInventory = false;

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    } // end setState()

} // end class MythrilFurnace
