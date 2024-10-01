package mod.alexndr.machines.content.container;

import mod.alexndr.machines.content.tile.OnyxFurnaceTileEntity;
import mod.alexndr.machines.init.ModContainerTypes;
import mod.alexndr.simplecorelib.api.content.VeryAbstractFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.RecipeType;

public class OnyxBlastFurnaceContainer extends VeryAbstractFurnaceMenu
{
    //private static final Logger LOGGER = LogManager.getLogger(Machines.MODID);

    /**
     * Constructor called logical-server-side from {@link OnyxFurnaceTileEntity#createMenu}
     * and logical-client-side from {@link #ModFurnaceContainer(int, PlayerInventory, PacketBuffer)}
     */
    public OnyxBlastFurnaceContainer(final int windowId, final Inventory playerInventory, final BlockPos pos, Player playerEntity)
    {
        super(ModContainerTypes.onyx_blast_furnace.get(), windowId, pos, playerInventory, playerEntity, RecipeType.BLASTING);
    }

     
} // end-class
