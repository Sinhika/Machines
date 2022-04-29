package mod.alexndr.machines.content.container;

import mod.alexndr.machines.content.tile.MythrilFurnaceTileEntity;
import mod.alexndr.machines.init.ModContainerTypes;
import mod.alexndr.simplecorelib.api.content.VeryAbstractFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.RecipeType;

public class MythrilSmokerContainer extends VeryAbstractFurnaceMenu
{
    /**
     * Constructor called logical-server-side from {@link MythrilFurnaceTileEntity#createMenu}
     * and logical-client-side from {@link #ModFurnaceContainer(int, PlayerInventory, PacketBuffer)}
     */
    public MythrilSmokerContainer(final int windowId, final Inventory playerInventory, final BlockPos pos, Player playerEntity) 
    {
        super(ModContainerTypes.mythril_smoker.get(), windowId, pos, playerInventory, playerEntity, RecipeType.SMOKING);
    } // end-server-side ctor

} // end class
