package mod.alexndr.machines.content.tile;

import mod.alexndr.machines.api.content.AbstractModBlastFurnaceTileEntity;
import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.content.container.OnyxBlastFurnaceContainer;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModTileEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class OnyxBlastFurnaceTileEntity extends AbstractModBlastFurnaceTileEntity
{
    // private static final Logger LOGGER = LogManager.getLogger(Machines.MODID);

    public OnyxBlastFurnaceTileEntity(BlockPos blockpos, BlockState blockstate)
    {
        super(ModTileEntityTypes.onyx_blast_furnace.get(), blockpos, blockstate);
        YieldChance = MachinesConfig.onyxFurnaceYieldChance;
        YieldAmount = MachinesConfig.onyxFurnaceYieldAmount;
    }

    @Override
    public Component getDefaultName()
    {
        return new TranslatableComponent(ModBlocks.onyx_blast_furnace.get().getDescriptionId());
    }

    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory inventory)
    {
        return new OnyxBlastFurnaceContainer(windowId, inventory, this);    
    }

} // end class
