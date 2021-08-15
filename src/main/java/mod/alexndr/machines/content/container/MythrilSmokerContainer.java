package mod.alexndr.machines.content.container;

import java.util.Objects;

import mod.alexndr.machines.content.block.MythrilSmokerBlock;
import mod.alexndr.machines.content.tile.MythrilFurnaceTileEntity;
import mod.alexndr.machines.content.tile.MythrilSmokerTileEntity;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModContainerTypes;
import mod.alexndr.simplecorelib.content.VeryAbstractFurnaceContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.fml.network.IContainerFactory;

public class MythrilSmokerContainer extends VeryAbstractFurnaceContainer<MythrilSmokerBlock>
{
    /**
     * Logical-client-side constructor, called from {@link ContainerType#create(IContainerFactory)}
     * Calls the logical-server-side constructor with the TileEntity at the pos in the PacketBuffer
     */
    public MythrilSmokerContainer(final int windowId, final Inventory playerInventory, final FriendlyByteBuf data) 
    {
        this(windowId, playerInventory, MythrilSmokerContainer.getTileEntity(playerInventory, data));
    }

    /**
     * Constructor called logical-server-side from {@link MythrilFurnaceTileEntity#createMenu}
     * and logical-client-side from {@link #ModFurnaceContainer(int, PlayerInventory, PacketBuffer)}
     */
    public MythrilSmokerContainer(final int windowId, final Inventory playerInventory, final MythrilSmokerTileEntity tileEntity) 
    {
        super(ModContainerTypes.mythril_smoker.get(), windowId, playerInventory, tileEntity, ModBlocks.mythril_smoker);
    } // end-server-side ctor

    
    protected static MythrilSmokerTileEntity getTileEntity(final Inventory playerInventory, final FriendlyByteBuf data) 
    {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null!");
        Objects.requireNonNull(data, "data cannot be null!");
        final BlockEntity tileAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
        if (tileAtPos instanceof MythrilSmokerTileEntity)
            return (MythrilSmokerTileEntity) tileAtPos;
        throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
    }

} // end class
