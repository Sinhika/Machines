package mod.alexndr.machines.content.tile;

import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.content.container.MythrilSmokerContainer;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModTileEntityTypes;
import mod.alexndr.simplecorelib.content.VeryAbstractSmokerTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class MythrilSmokerTileEntity extends VeryAbstractSmokerTileEntity
{
    public MythrilSmokerTileEntity(BlockPos blockpos, BlockState blockstate) 
    {
        super(ModTileEntityTypes.mythril_smoker.get(), blockpos, blockstate);
        hasFuelMultiplier = true;
        fuelMultiplier = MachinesConfig.mythrilFurnaceFuelMultiplier; 
    }


    @Override
    public Component getDefaultName()
    {
        return new TranslatableComponent(ModBlocks.mythril_smoker.get().getDescriptionId());
    }

    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory inventory)
    {
        return new MythrilSmokerContainer(windowId, inventory, this);
    }

} // end-class
