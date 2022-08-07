package mod.alexndr.machines.client.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.registration.IRecipeTransferRegistration;
import mod.alexndr.machines.Machines;
import mod.alexndr.machines.client.gui.MythrilBlastFurnaceScreen;
import mod.alexndr.machines.client.gui.MythrilFurnaceScreen;
import mod.alexndr.machines.client.gui.MythrilSmokerScreen;
import mod.alexndr.machines.client.gui.OnyxBlastFurnaceScreen;
import mod.alexndr.machines.client.gui.OnyxFurnaceScreen;
import mod.alexndr.machines.client.gui.OnyxSmokerScreen;
import mod.alexndr.machines.content.container.MythrilBlastFurnaceContainer;
import mod.alexndr.machines.content.container.MythrilFurnaceContainer;
import mod.alexndr.machines.content.container.MythrilSmokerContainer;
import mod.alexndr.machines.content.container.OnyxBlastFurnaceContainer;
import mod.alexndr.machines.content.container.OnyxFurnaceContainer;
import mod.alexndr.machines.content.container.OnyxSmokerContainer;
import mod.alexndr.machines.init.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class JEIMachinePlugin implements IModPlugin 
{
    private static final ResourceLocation ID = new ResourceLocation(Machines.MODID, "main");

    
    /** 
     * Overridden so we can add info pages.
     */
    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        registration.addIngredientInfo(new ItemStack(ModBlocks.mythril_furnace.get().asItem()), VanillaTypes.ITEM_STACK, 
                Component.translatable("simple_machines.mythril_furnace.info"));
        registration.addIngredientInfo(new ItemStack(ModBlocks.mythril_blast_furnace.get().asItem()), VanillaTypes.ITEM_STACK, 
                Component.translatable("simple_machines.mythril_blast_furnace.info"));
        registration.addIngredientInfo(new ItemStack(ModBlocks.mythril_smoker.get().asItem()), VanillaTypes.ITEM_STACK, 
                Component.translatable("simple_machines.mythril_smoker.info"));
        
        registration.addIngredientInfo(new ItemStack(ModBlocks.onyx_furnace.get().asItem()), VanillaTypes.ITEM_STACK, 
                Component.translatable("simple_machines.onyx_furnace.info"));
        registration.addIngredientInfo(new ItemStack(ModBlocks.onyx_blast_furnace.get().asItem()), VanillaTypes.ITEM_STACK, 
                Component.translatable("simple_machines.onyx_blast_furnace.info"));
        registration.addIngredientInfo(new ItemStack(ModBlocks.onyx_smoker.get().asItem()), VanillaTypes.ITEM_STACK, 
                Component.translatable("simple_machines.onyx_smoker.info"));
        
    } // end registerRecipes()


    /**
     * Register recipe catalysts.
     * Recipe Catalysts are ingredients that are needed in order to craft other things.
     * Vanilla examples of Recipe Catalysts are the Crafting Table and Furnace.
     */
    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry)
    {
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.mythril_furnace.get()), 
                RecipeTypes.SMELTING, RecipeTypes.FUELING);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.mythril_blast_furnace.get()), 
               RecipeTypes.BLASTING, RecipeTypes.FUELING);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.mythril_smoker.get()), 
                RecipeTypes.SMOKING, RecipeTypes.FUELING);
        
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.onyx_furnace.get()), 
                RecipeTypes.SMELTING, RecipeTypes.FUELING);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.onyx_blast_furnace.get()), 
               RecipeTypes.BLASTING, RecipeTypes.FUELING);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.onyx_smoker.get()), 
                RecipeTypes.SMOKING, RecipeTypes.FUELING);
    }

    
    @Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		registration.addRecipeTransferHandler(MythrilFurnaceContainer.class, RecipeTypes.SMELTING, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(MythrilFurnaceContainer.class, RecipeTypes.FUELING, 1, 1, 3, 36);
		registration.addRecipeTransferHandler(MythrilSmokerContainer.class, RecipeTypes.SMOKING, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(MythrilSmokerContainer.class, RecipeTypes.FUELING, 1, 1, 3, 36);
		registration.addRecipeTransferHandler(MythrilBlastFurnaceContainer.class,RecipeTypes.BLASTING, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(MythrilBlastFurnaceContainer.class, RecipeTypes.FUELING, 1, 1, 3, 36);
		registration.addRecipeTransferHandler(OnyxFurnaceContainer.class, RecipeTypes.SMELTING, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(OnyxFurnaceContainer.class, RecipeTypes.FUELING, 1, 1, 3, 36);
		registration.addRecipeTransferHandler(OnyxSmokerContainer.class, RecipeTypes.SMOKING, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(OnyxSmokerContainer.class, RecipeTypes.FUELING, 1, 1, 3, 36);
		registration.addRecipeTransferHandler(OnyxBlastFurnaceContainer.class,RecipeTypes.BLASTING, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(OnyxBlastFurnaceContainer.class, RecipeTypes.FUELING, 1, 1, 3, 36);
	}


	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		registration.addRecipeClickArea(MythrilFurnaceScreen.class, 78, 32, 28, 23, RecipeTypes.SMELTING, RecipeTypes.FUELING);
		registration.addRecipeClickArea(MythrilSmokerScreen.class, 78, 32, 28, 23, RecipeTypes.SMOKING, RecipeTypes.FUELING);
		registration.addRecipeClickArea(MythrilBlastFurnaceScreen.class, 78, 32, 28, 23,RecipeTypes.BLASTING, RecipeTypes.FUELING);
		registration.addRecipeClickArea(OnyxFurnaceScreen.class, 78, 32, 28, 23, RecipeTypes.SMELTING, RecipeTypes.FUELING);
		registration.addRecipeClickArea(OnyxSmokerScreen.class, 78, 32, 28, 23, RecipeTypes.SMOKING, RecipeTypes.FUELING);
		registration.addRecipeClickArea(OnyxBlastFurnaceScreen.class, 78, 32, 28, 23,RecipeTypes.BLASTING, RecipeTypes.FUELING);
	}


	@Override
    public ResourceLocation getPluginUid()
    {
        return ID;
    }

} // end class
