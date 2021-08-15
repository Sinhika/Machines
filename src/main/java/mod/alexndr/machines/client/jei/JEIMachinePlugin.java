package mod.alexndr.machines.client.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mod.alexndr.machines.Machines;
import mod.alexndr.machines.init.ModBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class JEIMachinePlugin implements IModPlugin 
{
    private static final ResourceLocation ID = new ResourceLocation(Machines.MODID, "main");

    /**
     * Register recipe catalysts.
     * Recipe Catalysts are ingredients that are needed in order to craft other things.
     * Vanilla examples of Recipe Catalysts are the Crafting Table and Furnace.
     */
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry)
    {
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.mythril_furnace.get()), 
                VanillaRecipeCategoryUid.FURNACE);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.mythril_blast_furnace.get()), 
                VanillaRecipeCategoryUid.BLASTING);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.mythril_smoker.get()), 
                VanillaRecipeCategoryUid.SMOKING);
        
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.onyx_furnace.get()), 
                VanillaRecipeCategoryUid.FURNACE);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.onyx_blast_furnace.get()), 
                VanillaRecipeCategoryUid.BLASTING);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.onyx_smoker.get()), 
                VanillaRecipeCategoryUid.SMOKING);
    }

    @Override
    public ResourceLocation getPluginUid()
    {
        return ID;
    }

} // end class
