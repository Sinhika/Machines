package alexndr.plugins.machines.blocks;

import java.util.Random;

import alexndr.api.content.blocks.SimpleFurnace;
import alexndr.api.registry.ContentCategories;
import alexndr.plugins.machines.Content;
import alexndr.plugins.machines.Machines;
import alexndr.plugins.machines.Settings;
import alexndr.plugins.machines.helpers.FancyFurnaceGuiHandler;
import alexndr.plugins.machines.tiles.OnyxFurnaceTileEntity;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class OnyxFurnace extends SimpleFurnace
{
    public static int YieldChance = 33;
    public static int YieldAmount = 1;
    
    public OnyxFurnace(boolean is_active)
    {
        super(Machines.plugin, Material.IRON, ContentCategories.Block.MACHINE, is_active);
    } // end ctor


    @Override
    public void setAdditionalProperties()
    {
        super.setAdditionalProperties();
            OnyxFurnace.YieldChance = Settings.onyxFurnaceYieldChance;
            OnyxFurnace.YieldAmount = Settings.onyxFurnaceYieldAmount;
    } // end setAdditionalProperties


    /* must be customized for children of SimpleFurnace */
    public static SimpleFurnace getUnlit_furnace()
    {
        return (SimpleFurnace) Content.onyx_furnace;
    }


    /* must be customized for children of SimpleFurnace */
    public static SimpleFurnace getLit_furnace()
    {
        return (SimpleFurnace) Content.onyx_furnace_lit;
    }


    @Override
    public TileEntity createNewTileEntity(World arg0, int arg1)
    {
        return new OnyxFurnaceTileEntity();
    }


    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getItem(World arg0, BlockPos arg1, IBlockState arg2)
    {
        return new ItemStack(Item.getItemFromBlock(Content.onyx_furnace));
    }


    @Override
    public Item getItemDropped(IBlockState arg0, Random arg1, int arg2)
    {
        return Item.getItemFromBlock(Content.onyx_furnace);
    }


    @Override
    public boolean clOnBlockActivated(World worldIn, BlockPos pos, IBlockState arg2,
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
            worldIn.setBlockState(pos, getLit_furnace().getDefaultState().withProperty(FACING,
                            iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, getLit_furnace().getDefaultState().withProperty(FACING,
                            iblockstate.getValue(FACING)), 3);
        }
        else {
            worldIn.setBlockState(pos, getUnlit_furnace().getDefaultState().withProperty(FACING,
                            iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, getUnlit_furnace().getDefaultState().withProperty(FACING,
                            iblockstate.getValue(FACING)), 3);
        }

        keepInventory = false;

        if (tileentity != null) {
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    } // end setState()

} // end class OnyxFurnace
