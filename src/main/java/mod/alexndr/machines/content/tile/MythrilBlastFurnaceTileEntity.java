package mod.alexndr.machines.content.tile;

import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.content.container.MythrilBlastFurnaceContainer;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModTileEntityTypes;
import mod.alexndr.simplecorelib.content.VeryAbstractBlastFurnaceTileEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class MythrilBlastFurnaceTileEntity extends VeryAbstractBlastFurnaceTileEntity
{

    public MythrilBlastFurnaceTileEntity( BlockPos blockpos, BlockState blockstate)
    {
        super(ModTileEntityTypes.mythril_blast_furnace.get(), blockpos, blockstate);
        hasFuelMultiplier = true;
        fuelMultiplier = MachinesConfig.mythrilFurnaceFuelMultiplier;
    }

    @Override
    public Component getDisplayName()
    {
        return new TranslatableComponent(ModBlocks.mythril_blast_furnace.get().getDescriptionId());
    }

    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory inventory)
    {
        return new MythrilBlastFurnaceContainer(windowId, inventory, this);    
    }

	@Override
	protected Component getDefaultName()
	{
		return new TranslatableComponent(ModBlocks.mythril_blast_furnace.get().getDescriptionId());
	}

} // end class
