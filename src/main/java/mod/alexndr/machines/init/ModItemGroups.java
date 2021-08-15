package mod.alexndr.machines.init;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

import mod.alexndr.machines.Machines;

import java.util.function.Supplier;

/**
 * This class holds all our ItemGroups (Formerly called CreativeTabs).
 * Static initialisers are fine here.
 *
 * @author Cadiboo
 */
public final class ModItemGroups {

	public static final CreativeModeTab MOD_ITEM_GROUP = 
	        new ModItemGroup(Machines.MODID, 
	                        () -> new ItemStack(ModBlocks.mythril_furnace.get()));

	public static final class ModItemGroup extends CreativeModeTab {

		@Nonnull
		private final Supplier<ItemStack> iconSupplier;

		public ModItemGroup(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier) {
			super(name);
			this.iconSupplier = iconSupplier;
		}

		@Override
		@Nonnull
		public ItemStack makeIcon() {
			return iconSupplier.get();
		}
	}
} // end class
