package mod.alexndr.machines.api.content;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.TileEntityType;

public abstract class AbstractModBlastFurnaceTileEntity extends AbstractModFurnaceTileEntity
{

    public AbstractModBlastFurnaceTileEntity(TileEntityType<?> tileEntityTypeIn)
    {
        super(tileEntityTypeIn, IRecipeType.BLASTING);
    }

    @Override
    protected int getBurnDuration(ItemStack fuelstack)
    {
        int retval = super.getBurnDuration(fuelstack) / 2;
//        LOGGER.debug("[" + getDisplayName().getString() + "]AbstractModBlastFurnaceTileEntity.getBurnDuration: returns " + retval + " for " + fuelstack.toString());
        return retval;
    }

    
} // end class
