package mod.alexndr.machines.content.container;

import java.util.Objects;

import mod.alexndr.machines.api.content.AbstractModFurnaceContainer;
import mod.alexndr.machines.content.block.OnyxSmokerBlock;
import mod.alexndr.machines.content.tile.OnyxFurnaceTileEntity;
import mod.alexndr.machines.content.tile.OnyxSmokerTileEntity;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModContainerTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.network.IContainerFactory;

public class OnyxSmokerContainer extends AbstractModFurnaceContainer<OnyxSmokerBlock>
{
    /**
     * Logical-client-side constructor, called from {@link ContainerType#create(IContainerFactory)}
     * Calls the logical-server-side constructor with the TileEntity at the pos in the PacketBuffer
     */
    public OnyxSmokerContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) 
    {
        this(windowId, playerInventory, OnyxSmokerContainer.getTileEntity(playerInventory, data));
    }

    /**
     * Constructor called logical-server-side from {@link OnyxFurnaceTileEntity#createMenu}
     * and logical-client-side from {@link #ModFurnaceContainer(int, PlayerInventory, PacketBuffer)}
     */
    public OnyxSmokerContainer(final int windowId, final PlayerInventory playerInventory, final OnyxSmokerTileEntity tileEntity) 
    {
        super(ModContainerTypes.onyx_smoker.get(), windowId, playerInventory, tileEntity, ModBlocks.onyx_smoker);
    } // end-server-side ctor

    
    protected static OnyxSmokerTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) 
    {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null!");
        Objects.requireNonNull(data, "data cannot be null!");
        final TileEntity tileAtPos = playerInventory.player.world.getTileEntity(data.readBlockPos());
        if (tileAtPos instanceof OnyxSmokerTileEntity)
            return (OnyxSmokerTileEntity) tileAtPos;
        throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
    }

} // end class
