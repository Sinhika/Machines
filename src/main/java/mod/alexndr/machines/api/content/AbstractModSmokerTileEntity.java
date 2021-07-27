package mod.alexndr.machines.api.content;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.TileEntityType;

public abstract class AbstractModSmokerTileEntity extends AbstractModFurnaceTileEntity
{

    public AbstractModSmokerTileEntity(TileEntityType<?> tileEntityTypeIn)
    {
        super(tileEntityTypeIn, IRecipeType.SMOKING);
    }

    @Override
    protected int getBurnDuration(ItemStack fuelstack)
    {
        return super.getBurnDuration(fuelstack) / 2;
    }

} // end class
