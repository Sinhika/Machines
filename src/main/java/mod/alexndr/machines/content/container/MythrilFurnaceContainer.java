package mod.alexndr.machines.content.container;

import mod.alexndr.machines.content.tile.MythrilFurnaceTileEntity;
import mod.alexndr.machines.init.ModContainerTypes;
import mod.alexndr.simplecorelib.api.content.VeryAbstractFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.RecipeType;

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
public class MythrilFurnaceContainer extends VeryAbstractFurnaceMenu 
{

	/**
	 * Constructor called logical-server-side from {@link MythrilFurnaceTileEntity#createMenu}
	 * and logical-client-side from {@link #ModFurnaceContainer(int, PlayerInventory, PacketBuffer)}
	 */
	public MythrilFurnaceContainer(final int windowId, final Inventory playerInventory, final BlockPos pos, Player playerEntity) 
	{
		super(ModContainerTypes.mythril_furnace.get(), windowId, pos, playerInventory, playerEntity, RecipeType.SMELTING);

	} // end-server-side ctor


} // end class
