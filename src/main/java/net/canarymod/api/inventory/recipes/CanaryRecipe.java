package net.canarymod.api.inventory.recipes;

import net.canarymod.Canary;
import net.canarymod.api.inventory.CanaryPlayerCraftingMatrix;
import net.canarymod.api.inventory.CraftingMatrix;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.world.CanaryWorld;
import net.canarymod.api.world.blocks.CanaryWorkbench;
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

    public boolean matchesMatrix(CraftingMatrix matrix) {
        if (matrix instanceof CanaryPlayerCraftingMatrix) {
            if (getHandle().a(((CanaryPlayerCraftingMatrix) matrix).getHandle(), ((CanaryWorld) Canary.getServer().getDefaultWorld()).getHandle())) {
                return true;
            }
        }
        else if (matrix instanceof CanaryWorkbench) {
            if (getHandle().a(((CanaryWorkbench) matrix).getInventory(), ((CanaryWorld) Canary.getServer().getDefaultWorld()).getHandle())) {
                return true;
            }
        }
        return false;
    }

    public abstract IRecipe getHandle();

}
