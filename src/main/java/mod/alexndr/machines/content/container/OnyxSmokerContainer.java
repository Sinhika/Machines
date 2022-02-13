package mod.alexndr.machines.content.container;

import mod.alexndr.machines.content.tile.OnyxFurnaceTileEntity;
import mod.alexndr.machines.content.tile.OnyxSmokerTileEntity;
import mod.alexndr.machines.init.ModContainerTypes;
import mod.alexndr.simplecorelib.content.VeryAbstractFurnaceMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.network.IContainerFactory;

public class OnyxSmokerContainer extends VeryAbstractFurnaceMenu
{
    /**
     * Logical-client-side constructor, called from {@link ContainerType#create(IContainerFactory)}
     * Calls the logical-server-side constructor with the TileEntity at the pos in the PacketBuffer
     */
    public OnyxSmokerContainer(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) 
    {
        super(ModContainerTypes.onyx_smoker.get(), RecipeType.SMOKING, windowId, playerInventory);
    }

    /**
     * Constructor called logical-server-side from {@link OnyxFurnaceTileEntity#createMenu}
     * and logical-client-side from {@link #ModFurnaceContainer(int, PlayerInventory, PacketBuffer)}
     */
    public OnyxSmokerContainer(final int windowId, final Inventory playerInventory, final OnyxSmokerTileEntity tileEntity) 
    {
        super(ModContainerTypes.onyx_smoker.get(),  RecipeType.SMOKING, windowId, playerInventory, tileEntity.inventory,
        		tileEntity.dataAccess, tileEntity);
    } // end-server-side ctor

    
} // end class
