package mod.alexndr.machines.content.tile;

import javax.annotation.Nonnull;

import mod.alexndr.machines.api.content.AbstractModFurnaceTileEntity;
import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.content.container.MythrilFurnaceContainer;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModTileEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 *
 */
public class MythrilFurnaceTileEntity extends AbstractModFurnaceTileEntity 
{
	public MythrilFurnaceTileEntity() 
	{
		super(ModTileEntityTypes.mythril_furnace.get(), IRecipeType.SMELTING);
        hasFuelMultiplier = true;
		fuelMultiplier = MachinesConfig.mythrilFurnaceFuelMultiplier;
	}

    /**
     * Called from {@link NetworkHooks#openGui}
     * (which is called from {@link ElectricFurnaceBlock#onBlockActivated} on the logical server)
     *
     * @return The logical-server-side Container for this TileEntity
     */
    @Nonnull
    @Override
    public Container createMenu(final int windowId, final PlayerInventory inventory, final PlayerEntity player)
    {
        return new MythrilFurnaceContainer(windowId, inventory, this);
    }

    @Nonnull
    @Override
    public ITextComponent getDisplayName()
    {
        return new TranslationTextComponent(ModBlocks.mythril_furnace.get().getDescriptionId());
    }

} // end class
