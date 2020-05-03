package mod.alexndr.machines.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

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

	public static final ItemGroup MOD_ITEM_GROUP = 
	        new ModItemGroup(Machines.MODID, 
	                        () -> new ItemStack(ModBlocks.mythril_furnace.get()));

	public static final class ModItemGroup extends ItemGroup {

		@Nonnull
		private final Supplier<ItemStack> iconSupplier;

		public ModItemGroup(@Nonnull final String name, @Nonnull final Supplier<ItemStack> iconSupplier) {
			super(name);
			this.iconSupplier = iconSupplier;
		}

		@Override
		@Nonnull
		public ItemStack createIcon() {
			return iconSupplier.get();
		}
	}
} // end class
