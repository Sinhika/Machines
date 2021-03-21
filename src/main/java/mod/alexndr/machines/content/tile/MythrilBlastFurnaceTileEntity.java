package mod.alexndr.machines.content.tile;

import mod.alexndr.machines.api.content.AbstractModFurnaceTileEntity;
import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.content.container.MythrilBlastFurnaceContainer;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModTileEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class MythrilBlastFurnaceTileEntity extends AbstractModFurnaceTileEntity
{

    public MythrilBlastFurnaceTileEntity()
    {
        super(ModTileEntityTypes.mythril_blast_furnace.get(), IRecipeType.BLASTING);
        fuelMultiplier = MachinesConfig.mythrilFurnaceFuelMultiplier / 2.0; // because blast furnace.
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TranslationTextComponent(ModBlocks.mythril_blast_furnace.get().getDescriptionId());
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory inventory, PlayerEntity player)
    {
        return new MythrilBlastFurnaceContainer(windowId, inventory, this);    
    }

} // end class
