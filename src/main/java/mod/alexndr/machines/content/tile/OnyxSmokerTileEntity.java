package mod.alexndr.machines.content.tile;

import mod.alexndr.machines.api.content.AbstractModSmokerTileEntity;
import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.content.container.OnyxSmokerContainer;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModTileEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class OnyxSmokerTileEntity extends AbstractModSmokerTileEntity
{

    public OnyxSmokerTileEntity()
    {
        super(ModTileEntityTypes.onyx_smoker.get());
        YieldChance = MachinesConfig.onyxFurnaceYieldChance;
        YieldAmount = MachinesConfig.onyxFurnaceYieldAmount;
     }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TranslationTextComponent(ModBlocks.onyx_smoker.get().getDescriptionId());
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory inventory, PlayerEntity player)
    {
        return new OnyxSmokerContainer(windowId, inventory, this);
    }

} // end class
