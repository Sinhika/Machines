package mod.alexndr.machines.content.container;

import mod.alexndr.machines.content.tile.MythrilBlastFurnaceTileEntity;
import mod.alexndr.machines.content.tile.MythrilFurnaceTileEntity;
import mod.alexndr.machines.init.ModContainerTypes;
import mod.alexndr.simplecorelib.content.VeryAbstractFurnaceMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fmllegacy.network.IContainerFactory;

public class MythrilBlastFurnaceContainer extends VeryAbstractFurnaceMenu
{

    /**
     * Logical-client-side constructor, called from {@link ContainerType#create(IContainerFactory)}
     * Calls the logical-server-side constructor with the TileEntity at the pos in the PacketBuffer
     */
    public MythrilBlastFurnaceContainer(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) 
    {
        super(ModContainerTypes.mythril_blast_furnace.get(), RecipeType.BLASTING, windowId, playerInventory);
    }
   
    /**
     * Constructor called logical-server-side from {@link MythrilFurnaceTileEntity#createMenu}
     * and logical-client-side from {@link #ModFurnaceContainer(int, PlayerInventory, PacketBuffer)}
     */
    public MythrilBlastFurnaceContainer(int id, Inventory playerInventory, MythrilBlastFurnaceTileEntity tileEntity)
    {
        super(ModContainerTypes.mythril_blast_furnace.get(), RecipeType.BLASTING, id, playerInventory, 
        		tileEntity.inventory, tileEntity.dataAccess, tileEntity);
    }

    
} // end-class
