package mod.alexndr.machines.content.container;

import mod.alexndr.machines.content.tile.MythrilFurnaceTileEntity;
import mod.alexndr.machines.content.tile.MythrilSmokerTileEntity;
import mod.alexndr.machines.init.ModContainerTypes;
import mod.alexndr.simplecorelib.content.VeryAbstractFurnaceMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fmllegacy.network.IContainerFactory;

public class MythrilSmokerContainer extends VeryAbstractFurnaceMenu
{
    /**
     * Logical-client-side constructor, called from {@link ContainerType#create(IContainerFactory)}
     * Calls the logical-server-side constructor with the TileEntity at the pos in the PacketBuffer
     */
    public MythrilSmokerContainer(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) 
    {
    	super(ModContainerTypes.mythril_smoker.get(), RecipeType.SMOKING, windowId, playerInventory);
    }

    /**
     * Constructor called logical-server-side from {@link MythrilFurnaceTileEntity#createMenu}
     * and logical-client-side from {@link #ModFurnaceContainer(int, PlayerInventory, PacketBuffer)}
     */
    public MythrilSmokerContainer(final int windowId, final Inventory playerInventory, final MythrilSmokerTileEntity tileEntity) 
    {
        super(ModContainerTypes.mythril_smoker.get(), RecipeType.SMOKING, windowId, playerInventory, tileEntity.inventory,
        		tileEntity.dataAccess, tileEntity);
    } // end-server-side ctor

} // end class
