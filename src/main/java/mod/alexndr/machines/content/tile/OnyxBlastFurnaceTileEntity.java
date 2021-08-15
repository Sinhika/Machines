package mod.alexndr.machines.content.tile;

import mod.alexndr.machines.api.content.AbstractModBlastFurnaceTileEntity;
import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.content.container.OnyxBlastFurnaceContainer;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModTileEntityTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class OnyxBlastFurnaceTileEntity extends AbstractModBlastFurnaceTileEntity
{
    // private static final Logger LOGGER = LogManager.getLogger(Machines.MODID);

    public OnyxBlastFurnaceTileEntity()
    {
        super(ModTileEntityTypes.onyx_blast_furnace.get());
        YieldChance = MachinesConfig.onyxFurnaceYieldChance;
        YieldAmount = MachinesConfig.onyxFurnaceYieldAmount;
    }

    @Override
    public Component getDisplayName()
    {
        return new TranslatableComponent(ModBlocks.onyx_blast_furnace.get().getDescriptionId());
    }

    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory inventory, Player player)
    {
        return new OnyxBlastFurnaceContainer(windowId, inventory, this);    
    }

} // end class
