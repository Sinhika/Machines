package mod.alexndr.machines.content.tile;

import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.content.container.MythrilSmokerContainer;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModTileEntityTypes;
import mod.alexndr.simplecorelib.content.VeryAbstractSmokerTileEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

public class MythrilSmokerTileEntity extends VeryAbstractSmokerTileEntity
{
    public MythrilSmokerTileEntity() 
    {
        super(ModTileEntityTypes.mythril_smoker.get());
        hasFuelMultiplier = true;
        fuelMultiplier = MachinesConfig.mythrilFurnaceFuelMultiplier; 
    }


    @Override
    public Component getDisplayName()
    {
        return new TranslatableComponent(ModBlocks.mythril_smoker.get().getDescriptionId());
    }

    @Override
    public AbstractContainerMenu createMenu(int windowId, Inventory inventory, Player player)
    {
        return new MythrilSmokerContainer(windowId, inventory, this);
    }

} // end-class
