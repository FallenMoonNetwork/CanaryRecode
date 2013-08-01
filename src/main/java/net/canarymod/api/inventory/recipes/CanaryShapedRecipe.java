package net.canarymod.api.inventory.recipes;

import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.ShapedRecipes;

public class CanaryShapedRecipe extends CanaryRecipe implements ShapedRecipe {

    public CanaryShapedRecipe(ShapedRecipes recipe) {
        super(recipe);
    }

    @Override
    public int getWidth() {
        return getHandle().getWidth();
    }

    @Override
    public int getHeight() {
        return getHandle().getHeight();
    }

    @Override
    public Item[] getRecipeItems() {
        return CanaryItem.stackArrayToItemArray(getHandle().getRecipeItems());
    }

    @Override
    public ShapedRecipes getHandle() {
        return (ShapedRecipes) recipe;
    }

    @Override
    public boolean isShapeless() {
        return false;
    }

    @Override
    public boolean isShaped() {
        return true;
    }
}
