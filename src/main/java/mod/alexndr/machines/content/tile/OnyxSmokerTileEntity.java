package mod.alexndr.machines.content.tile;

import mod.alexndr.machines.api.content.AbstractModSmokerTileEntity;
import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.content.container.OnyxSmokerContainer;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModTileEntityTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class OnyxSmokerTileEntity extends AbstractModSmokerTileEntity
{

    public OnyxSmokerTileEntity()
    {
        super(ModTileEntityTypes.onyx_smoker.get());
        YieldChance = MachinesConfig.onyxFurnaceYieldChance;
        YieldAmount = MachinesConfig.onyxFurnaceYieldAmount;
     }

    @Override
    public Component getDisplayName()
    {
        return new TranslatableComponent(ModBlocks.onyx_smoker.get().getDescriptionId());
    }

    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory inventory, Player player)
    {
        return new OnyxSmokerContainer(windowId, inventory, this);
    }

} // end class
