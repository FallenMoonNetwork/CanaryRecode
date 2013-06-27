package net.canarymod.api.inventory.recipes;

import net.canarymod.api.inventory.Item;
import net.minecraft.server.IRecipe;

/**
 * Implementation of the IRecipe wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryRecipe implements Recipe {

    protected final IRecipe recipe;

    public CanaryRecipe(IRecipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public Item getResult() {
        return recipe.b().getCanaryItem();
    }

    @Override
    public int getRecipeSize() {
        return recipe.a();
    }

    public abstract IRecipe getHandle();

}
