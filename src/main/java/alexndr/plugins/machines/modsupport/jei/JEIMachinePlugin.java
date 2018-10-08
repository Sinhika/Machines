package alexndr.plugins.machines.modsupport.jei;

import alexndr.plugins.machines.Content;
import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class JEIMachinePlugin extends BlankModPlugin 
{

	@Override
	public void register(IModRegistry registry) {
        registry.addRecipeCatalyst(new ItemStack(Content.mythril_furnace), 
				VanillaRecipeCategoryUid.SMELTING);
        registry.addRecipeCatalyst(new ItemStack(Content.onyx_furnace), 
				VanillaRecipeCategoryUid.SMELTING);
	}

} // end class
