package mod.alexndr.machines.content.container;

import java.util.Objects;

import mod.alexndr.machines.api.content.AbstractModFurnaceContainer;
import mod.alexndr.machines.content.block.OnyxBlastFurnaceBlock;
import mod.alexndr.machines.content.tile.OnyxBlastFurnaceTileEntity;
import mod.alexndr.machines.content.tile.OnyxFurnaceTileEntity;
import mod.alexndr.machines.init.ModBlocks;
import mod.alexndr.machines.init.ModContainerTypes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.network.IContainerFactory;

public class OnyxBlastFurnaceContainer extends AbstractModFurnaceContainer<OnyxBlastFurnaceBlock>
{
    //private static final Logger LOGGER = LogManager.getLogger(Machines.MODID);

    /**
     * Logical-client-side constructor, called from {@link ContainerType#create(IContainerFactory)}
     * Calls the logical-server-side constructor with the TileEntity at the pos in the PacketBuffer
     */
    public OnyxBlastFurnaceContainer(final int windowId, final PlayerInventory playerInventory, final PacketBuffer data) 
    {
        this(windowId, playerInventory, OnyxBlastFurnaceContainer.getTileEntity(playerInventory, data));
    }
   
    /**
     * Constructor called logical-server-side from {@link OnyxFurnaceTileEntity#createMenu}
     * and logical-client-side from {@link #ModFurnaceContainer(int, PlayerInventory, PacketBuffer)}
     */
    public OnyxBlastFurnaceContainer(int id, PlayerInventory playerInventory, 
                                        OnyxBlastFurnaceTileEntity tileEntity)
    {
        super(ModContainerTypes.onyx_blast_furnace.get(), id, playerInventory, tileEntity,
              ModBlocks.onyx_blast_furnace);
    }

    protected static OnyxBlastFurnaceTileEntity getTileEntity(final PlayerInventory playerInventory, final PacketBuffer data) 
    {
        Objects.requireNonNull(playerInventory, "playerInventory cannot be null!");
        Objects.requireNonNull(data, "data cannot be null!");
        final TileEntity tileAtPos = playerInventory.player.level.getBlockEntity(data.readBlockPos());
        if (tileAtPos instanceof OnyxBlastFurnaceTileEntity)
            return (OnyxBlastFurnaceTileEntity) tileAtPos;
        throw new IllegalStateException("Tile entity is not correct! " + tileAtPos);
    }
    
} // end-class
