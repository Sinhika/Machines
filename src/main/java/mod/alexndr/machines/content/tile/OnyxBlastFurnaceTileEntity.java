package mod.alexndr.machines.content.tile;

import mod.alexndr.machines.api.content.AbstractModBlastFurnaceTileEntity;
import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.content.container.OnyxBlastFurnaceContainer;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModTileEntityTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

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
    public ITextComponent getDisplayName()
    {
        return new TranslationTextComponent(ModBlocks.onyx_blast_furnace.get().getDescriptionId());
    }

    @Override
    public Container createMenu(int windowId, PlayerInventory inventory, PlayerEntity player)
    {
        return new OnyxBlastFurnaceContainer(windowId, inventory, this);    
    }

} // end class
