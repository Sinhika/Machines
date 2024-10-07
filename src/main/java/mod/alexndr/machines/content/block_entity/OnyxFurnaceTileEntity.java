package mod.alexndr.machines.content.block_entity;

import mod.alexndr.machines.config.MachinesConfig;
import mod.alexndr.machines.content.container.OnyxFurnaceContainer;
import mod.alexndr.machines.init.ModTileEntityTypes;
import mod.alexndr.simplecorelib.api.content.block_entity.AbstractYieldEnhancingFurnaceBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * 
 */
public class OnyxFurnaceTileEntity extends AbstractYieldEnhancingFurnaceBlockEntity
{
	private static final String DISPLAY_NAME = "block.simple_machines.onyx_furnace";

	public OnyxFurnaceTileEntity(BlockPos blockpos, BlockState blockstate) 
	{
		super(ModTileEntityTypes.onyx_furnace.get(), RecipeType.SMELTING, blockpos, blockstate);
	    YieldChance = MachinesConfig.onyxFurnaceYieldChance;
	    YieldAmount = MachinesConfig.onyxFurnaceYieldAmount;
	}

	@Override protected @NotNull Component getDefaultName()
	{
		return Component.translatable(DISPLAY_NAME);
	}

	@Override protected AbstractContainerMenu createMenu(int containerId, Inventory inventory)
	{
		return new OnyxFurnaceContainer(containerId, inventory, this, this.dataAccess);
	}
} // end class
