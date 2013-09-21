package net.minecraft.server;

import net.canarymod.api.inventory.recipes.CanaryShapelessRecipe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShapelessRecipes implements IRecipe {

    private final ItemStack a;
    private final List b;
    private final CanaryShapelessRecipe canary_recipe;

    public ShapelessRecipes(ItemStack itemstack, List list) {
        this.a = itemstack;
        this.b = list;
        this.canary_recipe = new CanaryShapelessRecipe(this);
    }

    public ItemStack b() {
        return this.a;
    }

    public boolean a(InventoryCrafting inventorycrafting, World world) {
        ArrayList arraylist = new ArrayList(this.b);

        for (int i0 = 0; i0 < 3; ++i0) {
            for (int i1 = 0; i1 < 3; ++i1) {
                ItemStack itemstack = inventorycrafting.b(i1, i0);

                if (itemstack != null) {
                    boolean flag0 = false;
                    Iterator iterator = arraylist.iterator();

                    while (iterator.hasNext()) {
                        ItemStack itemstack1 = (ItemStack)iterator.next();

                        if (itemstack.d == itemstack1.d && (itemstack1.k() == 32767 || itemstack.k() == itemstack1.k())) {
                            flag0 = true;
                            arraylist.remove(itemstack1);
                            break;
                        }
                    }

                    if (!flag0) {
                        return false;
                    }
                }
            }
        }

        return arraylist.isEmpty();
    }

    public ItemStack a(InventoryCrafting inventorycrafting) {
        return this.a.m();
    }

    public int a() {
        return this.b.size();
    }

    // CanaryMod
    @SuppressWarnings("unchecked")
    public List<ItemStack> getRecipeItems() {
        return this.b;
    }

    public CanaryShapelessRecipe getCanaryRecipe() {
        return canary_recipe;
    }
    //
}
