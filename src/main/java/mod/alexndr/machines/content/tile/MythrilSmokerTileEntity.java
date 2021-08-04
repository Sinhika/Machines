package mod.alexndr.machines.content.tile;

import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.content.container.MythrilSmokerContainer;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModTileEntityTypes;
import mod.alexndr.simplecorelib.content.VeryAbstractSmokerTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class MythrilSmokerTileEntity extends VeryAbstractSmokerTileEntity
{
    public MythrilSmokerTileEntity() 
    {
        super(ModTileEntityTypes.mythril_smoker.get());
        hasFuelMultiplier = true;
        fuelMultiplier = MachinesConfig.mythrilFurnaceFuelMultiplier; 
    }


    @Override
    public ITextComponent getDisplayName()
    {
        return new TranslationTextComponent(ModBlocks.mythril_smoker.get().getDescriptionId());
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory inventory, PlayerEntity player)
    {
        return new MythrilSmokerContainer(windowId, inventory, this);
    }

} // end-class
