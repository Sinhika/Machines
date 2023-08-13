package mod.alexndr.machines.api.content;

import java.util.Optional;
import java.util.Random;

import mod.alexndr.simplecorelib.api.content.VeryAbstractFurnaceTileEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class AbstractModFurnaceTileEntity extends VeryAbstractFurnaceTileEntity
{

    protected int YieldChance = 0;
    protected int YieldAmount = 0;
    protected Random generator = new Random();

    public AbstractModFurnaceTileEntity(BlockEntityType<?> tileEntityTypeIn, 
    		RecipeType<? extends AbstractCookingRecipe> recipeTypeIn, BlockPos blockpos, BlockState blockstate)
    {
        super(tileEntityTypeIn, blockpos, blockstate, recipeTypeIn);
    }

    @Override
    protected Optional<ItemStack> getResult(final ItemStack input)
    {
        // Due to vanilla's code we need to pass an IInventory into RecipeManager#getRecipe and
        // AbstractCookingRecipe#getCraftingResult() so we make one here.
        final SimpleContainer dummyInventory = new SimpleContainer(input);
        Optional<ItemStack> maybe_result = 
        		getRecipe(dummyInventory).map(recipe -> recipe.assemble(dummyInventory, level.registryAccess()));
        
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
            {
                result.grow(k);
            }
            else if ((result.getMaxStackSize() - result.getCount()) > 0)
            {
                result.grow(result.getMaxStackSize() - result.getCount());
            }
        }
        return Optional.of(result);
    } // end getResult()

    
} // end class
