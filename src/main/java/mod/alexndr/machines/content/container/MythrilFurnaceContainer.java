package mod.alexndr.machines.content.container;

import java.util.Objects;

import mod.alexndr.machines.content.block.MythrilFurnaceBlock;
import mod.alexndr.machines.content.tile.MythrilFurnaceTileEntity;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModContainerTypes;
import mod.alexndr.simplecorelib.content.VeryAbstractFurnaceContainer;
import net.minecraft.client.network.play.ClientPlayNetHandler;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.play.server.SWindowPropertyPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
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
public class MythrilFurnaceContainer extends VeryAbstractFurnaceContainer<MythrilFurnaceBlock> 
{

	/**
	 * Logical-client-side constructor, called from {@link ContainerType#create(IContainerFactory)}
	 * Calls the logical-server-side constructor with the TileEntity at the pos in the PacketBuffer
	 */
	public MythrilFurnaceContainer(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) 
	{
		this(windowId, playerInventory, MythrilFurnaceContainer.getTileEntity(playerInventory, data));
	}

	/**
	 * Constructor called logical-server-side from {@link MythrilFurnaceTileEntity#createMenu}
	 * and logical-client-side from {@link #ModFurnaceContainer(int, PlayerInventory, PacketBuffer)}
	 */
	public MythrilFurnaceContainer(final int windowId, final Inventory playerInventory, final MythrilFurnaceTileEntity tileEntity) 
	{
		super(ModContainerTypes.mythril_furnace.get(), windowId, playerInventory, tileEntity, ModBlocks.mythril_furnace);
		// Add all the slots for the tileEntity's inventory and the playerInventory to this container

	} // end-server-side ctor

    protected static MythrilFurnaceTileEntity getTileEntity(final Inventory playerInventory, final FriendlyByteBuf data) 
	{
		Objects.requireNonNull(playerInventory, "playerInventory cannot be null!");
		Objects.requireNonNull(data, "data cannot be null!");
		final BlockEntity tileAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
		if (tileAtPos instanceof MythrilFurnaceTileEntity)
			return (MythrilFurnaceTileEntity) tileAtPos;
		throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
	}

} // end class
