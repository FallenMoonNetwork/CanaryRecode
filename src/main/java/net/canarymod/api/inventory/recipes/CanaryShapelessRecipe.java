package net.canarymod.api.inventory.recipes;

import java.util.ArrayList;
import java.util.List;
import net.canarymod.api.inventory.Item;
import net.minecraft.server.ItemStack;
import net.minecraft.server.ShapelessRecipes;

/**
 * Implementation of the ShapelessRecipes wrapper
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryShapelessRecipe extends CanaryRecipe implements ShapelessRecipe {

    public CanaryShapelessRecipe(ShapelessRecipes shapeless) {
        super(shapeless);
    }

    @Override
    public List<Item> getRecipeItems() {
        ArrayList<Item> items = new ArrayList<Item>();
        for (ItemStack itemstack : getHandle().getRecipeItems()) {
            items.add(itemstack.getCanaryItem());
        }
        return items;
    }

    @Override
    public boolean isShapeless() {
        return true;
    }

    @Override
    public boolean isShaped() {
        return false;
    }

    @Override
    public ShapelessRecipes getHandle() {
        return (ShapelessRecipes) recipe;
    }
}
