package mod.alexndr.machines.client.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaRecipeCategoryUid;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

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
                VanillaRecipeCategoryUid.FURNACE, VanillaRecipeCategoryUid.FUEL);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.mythril_blast_furnace.get()), 
                VanillaRecipeCategoryUid.BLASTING, VanillaRecipeCategoryUid.FUEL);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.mythril_smoker.get()), 
                VanillaRecipeCategoryUid.SMOKING, VanillaRecipeCategoryUid.FUEL);
        
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.onyx_furnace.get()), 
                VanillaRecipeCategoryUid.FURNACE, VanillaRecipeCategoryUid.FUEL);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.onyx_blast_furnace.get()), 
                VanillaRecipeCategoryUid.BLASTING, VanillaRecipeCategoryUid.FUEL);
        registry.addRecipeCatalyst(new ItemStack(ModBlocks.onyx_smoker.get()), 
                VanillaRecipeCategoryUid.SMOKING, VanillaRecipeCategoryUid.FUEL);
    }

    
    @Override
	public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration)
	{
		registration.addRecipeTransferHandler(MythrilFurnaceContainer.class, VanillaRecipeCategoryUid.FURNACE, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(MythrilFurnaceContainer.class, VanillaRecipeCategoryUid.FUEL, 1, 1, 3, 36);
		registration.addRecipeTransferHandler(MythrilSmokerContainer.class, VanillaRecipeCategoryUid.SMOKING, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(MythrilSmokerContainer.class, VanillaRecipeCategoryUid.FUEL, 1, 1, 3, 36);
		registration.addRecipeTransferHandler(MythrilBlastFurnaceContainer.class, VanillaRecipeCategoryUid.BLASTING, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(MythrilBlastFurnaceContainer.class, VanillaRecipeCategoryUid.FUEL, 1, 1, 3, 36);
		registration.addRecipeTransferHandler(OnyxFurnaceContainer.class, VanillaRecipeCategoryUid.FURNACE, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(OnyxFurnaceContainer.class, VanillaRecipeCategoryUid.FUEL, 1, 1, 3, 36);
		registration.addRecipeTransferHandler(OnyxSmokerContainer.class, VanillaRecipeCategoryUid.SMOKING, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(OnyxSmokerContainer.class, VanillaRecipeCategoryUid.FUEL, 1, 1, 3, 36);
		registration.addRecipeTransferHandler(OnyxBlastFurnaceContainer.class, VanillaRecipeCategoryUid.BLASTING, 0, 1, 3, 36);
		registration.addRecipeTransferHandler(OnyxBlastFurnaceContainer.class, VanillaRecipeCategoryUid.FUEL, 1, 1, 3, 36);
	}


	@Override
	public void registerGuiHandlers(IGuiHandlerRegistration registration)
	{
		registration.addRecipeClickArea(MythrilFurnaceScreen.class, 78, 32, 28, 23, VanillaRecipeCategoryUid.FURNACE, VanillaRecipeCategoryUid.FUEL);
		registration.addRecipeClickArea(MythrilSmokerScreen.class, 78, 32, 28, 23, VanillaRecipeCategoryUid.SMOKING, VanillaRecipeCategoryUid.FUEL);
		registration.addRecipeClickArea(MythrilBlastFurnaceScreen.class, 78, 32, 28, 23, VanillaRecipeCategoryUid.BLASTING, VanillaRecipeCategoryUid.FUEL);
		registration.addRecipeClickArea(OnyxFurnaceScreen.class, 78, 32, 28, 23, VanillaRecipeCategoryUid.FURNACE, VanillaRecipeCategoryUid.FUEL);
		registration.addRecipeClickArea(OnyxSmokerScreen.class, 78, 32, 28, 23, VanillaRecipeCategoryUid.SMOKING, VanillaRecipeCategoryUid.FUEL);
		registration.addRecipeClickArea(OnyxBlastFurnaceScreen.class, 78, 32, 28, 23, VanillaRecipeCategoryUid.BLASTING, VanillaRecipeCategoryUid.FUEL);
	}


	@Override
    public ResourceLocation getPluginUid()
    {
        return ID;
    }

} // end class
