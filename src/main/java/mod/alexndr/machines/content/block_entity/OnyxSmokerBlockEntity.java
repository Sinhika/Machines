package mod.alexndr.machines.content.block_entity;

import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.content.container.OnyxSmokerContainer;
import mod.alexndr.machines.init.ModTileEntityTypes;
import mod.alexndr.simplecorelib.api.content.block_entity.AbstractYieldEnhancingSmokerBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class OnyxSmokerBlockEntity extends AbstractYieldEnhancingSmokerBlockEntity
{
    private static final String DISPLAY_NAME = "block.simple_machines.onyx_smoker";

    public OnyxSmokerBlockEntity(BlockPos blockpos, BlockState blockstate)
    {
        super(ModTileEntityTypes.onyx_smoker.get(), blockpos, blockstate);
        YieldChance = MachinesConfig.onyxFurnaceYieldChance;
        YieldAmount = MachinesConfig.onyxFurnaceYieldAmount;
     }

    @Override protected @NotNull Component getDefaultName()
    {
        return Component.translatable(DISPLAY_NAME);
    }

    @Override protected AbstractContainerMenu createMenu(int containerId, Inventory inventory)
    {
        return new OnyxSmokerContainer(containerId, inventory, this, this.dataAccess);
    }

    @Override
    protected int getBurnDuration(ItemStack fuelstack)
    {
        return super.getBurnDuration(fuelstack)/2;
    }

} // end class
