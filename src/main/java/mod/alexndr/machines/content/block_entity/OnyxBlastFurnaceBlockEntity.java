package mod.alexndr.machines.content.block_entity;

import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.content.container.OnyxBlastFurnaceContainer;
import mod.alexndr.machines.init.ModTileEntityTypes;
import mod.alexndr.simplecorelib.api.content.block_entity.AbstractYieldEnhancingBlastFurnaceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class OnyxBlastFurnaceBlockEntity extends AbstractYieldEnhancingBlastFurnaceBlockEntity
{
    // private static final Logger LOGGER = LogManager.getLogger(Machines.MODID);
    private static final String DISPLAY_NAME = "block.simpleores_machines.onyx_blast_furnace";

    public OnyxBlastFurnaceBlockEntity(BlockPos blockpos, BlockState blockstate)
    {
        super(ModTileEntityTypes.onyx_blast_furnace.get(), blockpos, blockstate);
        YieldChance = MachinesConfig.onyxFurnaceYieldChance;
        YieldAmount = MachinesConfig.onyxFurnaceYieldAmount;
    }

    @Override protected @NotNull Component getDefaultName()
    {
        return Component.translatable(DISPLAY_NAME);
    }

    @Override protected AbstractContainerMenu createMenu(int containerId, Inventory inventory)
    {
        return new OnyxBlastFurnaceContainer(containerId, inventory, this, this.dataAccess);
    }

} // end class
