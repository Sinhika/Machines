package mod.alexndr.machines.content.tile;

import mod.alexndr.machines.api.content.AbstractModSmokerTileEntity;
import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.content.container.OnyxSmokerContainer;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModTileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class OnyxSmokerTileEntity extends AbstractModSmokerTileEntity
{

    public OnyxSmokerTileEntity(BlockPos blockpos, BlockState blockstate)
    {
        super(ModTileEntityTypes.onyx_smoker.get(), blockpos, blockstate);
        YieldChance = MachinesConfig.onyxFurnaceYieldChance;
        YieldAmount = MachinesConfig.onyxFurnaceYieldAmount;
     }

    @Override
    public Component getDefaultName()
    {
        return new TranslatableComponent(ModBlocks.onyx_smoker.get().getDescriptionId());
    }

    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory inventory)
    {
        return new OnyxSmokerContainer(windowId, inventory, this);
    }

} // end class
