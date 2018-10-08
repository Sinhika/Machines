package alexndr.plugins.machines.modsupport.jei;

import alexndr.plugins.machines.ModBlocks;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEIMachinePlugin implements IModPlugin 
{
	@Override
	public void register(IModRegistry registry) {
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.mythril_furnace), 
				VanillaRecipeCategoryUid.SMELTING);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.onyx_furnace), 
				VanillaRecipeCategoryUid.SMELTING);
	}

} // end class
