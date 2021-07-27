package mod.alexndr.machines.api.content;

import java.util.Optional;
import java.util.Random;

import mod.alexndr.simplecorelib.content.VeryAbstractFurnaceTileEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.TileEntityType;

public abstract class AbstractModFurnaceTileEntity extends VeryAbstractFurnaceTileEntity
{

    protected int YieldChance = 0;
    protected int YieldAmount = 0;
    protected Random generator = new Random();

    public AbstractModFurnaceTileEntity(TileEntityType<?> tileEntityTypeIn, IRecipeType<? extends AbstractCookingRecipe> recipeTypeIn)
    {
        super(tileEntityTypeIn, recipeTypeIn);
    }

    @Override
    protected Optional<ItemStack> getResult(final ItemStack input)
    {
        // Due to vanilla's code we need to pass an IInventory into RecipeManager#getRecipe and
        // AbstractCookingRecipe#getCraftingResult() so we make one here.
        final Inventory dummyInventory = new Inventory(input);
        Optional<ItemStack> maybe_result = getRecipe(dummyInventory).map(recipe -> recipe.assemble(dummyInventory));
        
        // enhanced yield processing.
        if (YieldChance <= 0 || YieldAmount <= 0)
        {
            return maybe_result;
        }
        ItemStack result =  maybe_result.orElse(ItemStack.EMPTY);
        int r = generator.nextInt(100);
        if(r <= YieldChance && ! result.isEmpty()) 
        {
            int k = YieldAmount;
            if ((k + result.getCount()) < result.getMaxStackSize())
            result.grow(k);
        }
        return Optional.of(result);
    } // end getResult()

    
} // end class
