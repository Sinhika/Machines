package mod.alexndr.machines.content.tile;

import javax.annotation.Nonnull;

import mod.alexndr.machines.api.content.AbstractModFurnaceTileEntity;
import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.content.container.MythrilFurnaceContainer;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModTileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fmllegacy.network.NetworkHooks;

/**
 *
 */
public class MythrilFurnaceTileEntity extends AbstractModFurnaceTileEntity 
{
	public MythrilFurnaceTileEntity(BlockPos blockpos, BlockState blockstate) 
	{
		super(ModTileEntityTypes.mythril_furnace.get(), RecipeType.SMELTING, blockpos, blockstate);
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
    public AbstractContainerMenu createMenu(final int windowId, final Inventory inventory)
    {
        return new MythrilFurnaceContainer(windowId, inventory, this);
    }

    @Nonnull
    @Override
    public Component getDefaultName()
    {
        return new TranslatableComponent(ModBlocks.mythril_furnace.get().getDescriptionId());
    }

} // end class
