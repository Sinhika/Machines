package mod.alexndr.machines.content.container;

import mod.alexndr.machines.content.tile.OnyxFurnaceTileEntity;
import mod.alexndr.machines.init.ModContainerTypes;
import mod.alexndr.simplecorelib.content.VeryAbstractFurnaceMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.fmllegacy.network.IContainerFactory;

/**
 * Smelt time is synced with
 * Server: Each tick {@link #detectAndSendChanges()} is called ({@link ServerPlayerEntity#tick()})
 * Server: The (tracked) value of the tile's energy is updated ({@link #updateProgressBar(int, int)})
 * Server: If the value is different from the value last sent to the client ({@link IntReferenceHolder#isDirty()}),
 * it is synced to the client ({@link ServerPlayerEntity#sendWindowProperty(Container, int, int)})
 * Client: The sync packet is received ({@link ClientPlayNetHandler#handleWindowProperty(SWindowPropertyPacket)})
 * and the tracked value of is updated ({@link Container#updateProgressBar(int, int)})
 * Client: The tile's data is set to the new value
 *
 * @author Cadiboo
 */
public class OnyxFurnaceContainer extends VeryAbstractFurnaceMenu 
{

	/**
	 * Logical-client-side constructor, called from {@link ContainerType#create(IContainerFactory)}
	 * Calls the logical-server-side constructor with the TileEntity at the pos in the PacketBuffer
	 */
	public OnyxFurnaceContainer(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) 
	{
		super(ModContainerTypes.onyx_furnace.get(), RecipeType.SMELTING, windowId, playerInventory);
	}

	/**
	 * Constructor called logical-server-side from {@link OnyxFurnaceTileEntity#createMenu}
	 * and logical-client-side from {@link #ModFurnaceContainer(int, PlayerInventory, PacketBuffer)}
	 */
	public OnyxFurnaceContainer(final int windowId, final Inventory playerInventory, final OnyxFurnaceTileEntity tileEntity) 
	{
		super(ModContainerTypes.onyx_furnace.get(), RecipeType.SMELTING, windowId, playerInventory, tileEntity.inventory,
				tileEntity.dataAccess, tileEntity);
	} // end-server-side ctor


} // end class
