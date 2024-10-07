package mod.alexndr.machines.content.container;

import mod.alexndr.machines.init.ModContainerTypes;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;

public class MythrilBlastFurnaceContainer extends AbstractFurnaceMenu
{
    public MythrilBlastFurnaceContainer(int containerId, Inventory playerInventory)
    {
        super(ModContainerTypes.mythril_blast_furnace.get(), RecipeType.BLASTING, RecipeBookType.BLAST_FURNACE,
                containerId, playerInventory);
    }

    public MythrilBlastFurnaceContainer(int containerId, Inventory playerInventory, Container container,
                                        ContainerData data)
    {
        super(ModContainerTypes.mythril_blast_furnace.get(), RecipeType.BLASTING, RecipeBookType.BLAST_FURNACE,
                containerId, playerInventory, container, data);
    }
} // end-class
