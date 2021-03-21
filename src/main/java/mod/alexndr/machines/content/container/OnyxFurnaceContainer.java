package mod.alexndr.machines.content.container;

import java.util.Objects;

import mod.alexndr.machines.api.content.AbstractModFurnaceContainer;
import mod.alexndr.machines.content.block.OnyxFurnaceBlock;
import mod.alexndr.machines.content.tile.OnyxFurnaceTileEntity;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModContainerTypes;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.SWindowPropertyPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IntReferenceHolder;
import net.minecraftforge.fml.network.IContainerFactory;

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
public class OnyxFurnaceContainer extends AbstractModFurnaceContainer<OnyxFurnaceBlock> 
{

	/**
	 * Logical-client-side constructor, called from {@link ContainerType#create(IContainerFactory)}
	 * Calls the logical-server-side constructor with the TileEntity at the pos in the PacketBuffer
	 */
	public OnyxFurnaceContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) 
	{
		this(windowId, playerInventory, OnyxFurnaceContainer.getTileEntity(playerInventory, data));
	}

	/**
	 * Constructor called logical-server-side from {@link OnyxFurnaceTileEntity#createMenu}
	 * and logical-client-side from {@link #ModFurnaceContainer(int, PlayerInventory, PacketBuffer)}
	 */
	public OnyxFurnaceContainer(final int windowId, final PlayerInventory playerInventory, final OnyxFurnaceTileEntity tileEntity) 
	{
		super(ModContainerTypes.onyx_furnace.get(), windowId, playerInventory, tileEntity, ModBlocks.onyx_furnace);
	} // end-server-side ctor

	protected static OnyxFurnaceTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) 
	{
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null!");
		Objects.requireNonNull(data, "data cannot be null!");
		final TileEntity tileAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
		if (tileAtPos instanceof OnyxFurnaceTileEntity)
			return (OnyxFurnaceTileEntity) tileAtPos;
		throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
	}

} // end class
