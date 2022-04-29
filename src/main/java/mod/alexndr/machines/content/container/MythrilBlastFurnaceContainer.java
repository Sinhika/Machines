package mod.alexndr.machines.content.container;

import mod.alexndr.machines.content.tile.MythrilFurnaceTileEntity;
import mod.alexndr.machines.init.ModContainerTypes;
import mod.alexndr.simplecorelib.api.content.VeryAbstractFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.RecipeType;

public class MythrilBlastFurnaceContainer extends VeryAbstractFurnaceMenu
{

    /**
     * Constructor called logical-server-side from {@link MythrilFurnaceTileEntity#createMenu}
     * and logical-client-side from {@link #ModFurnaceContainer(int, PlayerInventory, PacketBuffer)}
     */
    public MythrilBlastFurnaceContainer(final int windowId, final Inventory playerInventory, final BlockPos pos, Player playerEntity)
    {
        super(ModContainerTypes.mythril_blast_furnace.get(), windowId, pos, playerInventory, playerEntity, RecipeType.BLASTING);
    }

    
} // end-class
