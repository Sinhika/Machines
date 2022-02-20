package mod.alexndr.machines.content.container;

import mod.alexndr.machines.content.tile.OnyxFurnaceTileEntity;
import mod.alexndr.machines.init.ModContainerTypes;
import mod.alexndr.simplecorelib.content.VeryAbstractFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.RecipeType;

public class OnyxSmokerContainer extends VeryAbstractFurnaceMenu
{

    /**
     * Constructor called logical-server-side from {@link OnyxFurnaceTileEntity#createMenu}
     * and logical-client-side from {@link #ModFurnaceContainer(int, PlayerInventory, PacketBuffer)}
     */
    public OnyxSmokerContainer(final int windowId, final Inventory playerInventory, final BlockPos pos, Player playerEntity) 
    {
        super(ModContainerTypes.onyx_smoker.get(), windowId, pos, playerInventory, playerEntity, RecipeType.SMOKING);
    } // end-server-side ctor

    
} // end class
