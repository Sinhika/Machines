package mod.alexndr.machines.content.container;

import mod.alexndr.machines.init.ModContainerTypes;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipeType;


public class OnyxFurnaceContainer extends AbstractFurnaceMenu
{
	public OnyxFurnaceContainer(int containerId, Inventory playerInventory)
	{
		super(ModContainerTypes.onyx_furnace.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, containerId, playerInventory);
	}

	public OnyxFurnaceContainer(int containerId, Inventory playerInventory, Container container, ContainerData data)
	{
		super(ModContainerTypes.onyx_furnace.get(), RecipeType.SMELTING, RecipeBookType.FURNACE, containerId,
				playerInventory, container, data);
	}


} // end class
