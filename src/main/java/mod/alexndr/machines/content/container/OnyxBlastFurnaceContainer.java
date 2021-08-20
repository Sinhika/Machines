package mod.alexndr.machines.content.container;

import mod.alexndr.machines.content.tile.OnyxBlastFurnaceTileEntity;
import mod.alexndr.machines.content.tile.OnyxFurnaceTileEntity;
import mod.alexndr.machines.init.ModContainerTypes;
import mod.alexndr.simplecorelib.content.VeryAbstractFurnaceMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fmllegacy.network.IContainerFactory;

public class OnyxBlastFurnaceContainer extends VeryAbstractFurnaceMenu
{
    //private static final Logger LOGGER = LogManager.getLogger(Machines.MODID);

    /**
     * Logical-client-side constructor, called from {@link ContainerType#create(IContainerFactory)}
     * Calls the logical-server-side constructor with the TileEntity at the pos in the PacketBuffer
     */
    public OnyxBlastFurnaceContainer(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) 
    {
    	super(ModContainerTypes.onyx_blast_furnace.get(), RecipeType.BLASTING, windowId, playerInventory);
    }
   
    /**
     * Constructor called logical-server-side from {@link OnyxFurnaceTileEntity#createMenu}
     * and logical-client-side from {@link #ModFurnaceContainer(int, PlayerInventory, PacketBuffer)}
     */
    public OnyxBlastFurnaceContainer(int id, Inventory playerInventory, 
                                        OnyxBlastFurnaceTileEntity tileEntity)
    {
        super(ModContainerTypes.onyx_blast_furnace.get(), RecipeType.BLASTING, id, playerInventory, tileEntity.inventory,
        		tileEntity.dataAccess, tileEntity);
    }

     
} // end-class
