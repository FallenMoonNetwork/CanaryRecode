package net.minecraft.server;

import net.canarymod.api.inventory.recipes.CanaryShapedRecipe;

public class ShapedRecipes implements IRecipe {

    private int b;
    private int c;
    private ItemStack[] d;
    private ItemStack e;
    public final int a;
    private boolean f = false;
    private final CanaryShapedRecipe canary_recipe; // CanaryMod: recipe instance

    public ShapedRecipes(int i0, int i1, ItemStack[] aitemstack, ItemStack itemstack) {
        this.a = itemstack.d;
        this.b = i0;
        this.c = i1;
        this.d = aitemstack;
        this.e = itemstack;
        this.canary_recipe = new CanaryShapedRecipe(this); // CanaryMod: wrap recipe
    }

    public ItemStack b() {
        return this.e;
    }

    public boolean a(InventoryCrafting inventorycrafting, World world) {
        for (int i0 = 0; i0 <= 3 - this.b; ++i0) {
            for (int i1 = 0; i1 <= 3 - this.c; ++i1) {
                if (this.a(inventorycrafting, i0, i1, true)) {
                    return true;
                }

                if (this.a(inventorycrafting, i0, i1, false)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean a(InventoryCrafting inventorycrafting, int i0, int i1, boolean flag0) {
        for (int i2 = 0; i2 < 3; ++i2) {
            for (int i3 = 0; i3 < 3; ++i3) {
                int i4 = i2 - i0;
                int i5 = i3 - i1;
                ItemStack itemstack = null;

                if (i4 >= 0 && i5 >= 0 && i4 < this.b && i5 < this.c) {
                    if (flag0) {
                        itemstack = this.d[this.b - i4 - 1 + i5 * this.b];
                    } else {
                        itemstack = this.d[i4 + i5 * this.b];
                    }
                }

                ItemStack itemstack1 = inventorycrafting.b(i2, i3);

                if (itemstack1 != null || itemstack != null) {
                    if (itemstack1 == null && itemstack != null || itemstack1 != null && itemstack == null) {
                        return false;
                    }

                    if (itemstack.d != itemstack1.d) {
                        return false;
                    }

                    if (itemstack.k() != 32767 && itemstack.k() != itemstack1.k()) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public ItemStack a(InventoryCrafting inventorycrafting) {
        ItemStack itemstack = this.b().m();

        if (this.f) {
            for (int i0 = 0; i0 < inventorycrafting.j_(); ++i0) {
                ItemStack itemstack1 = inventorycrafting.a(i0);

                if (itemstack1 != null && itemstack1.p()) {
                    itemstack.d((NBTTagCompound) itemstack1.e.b());
                }
            }
        }

        return itemstack;
    }

    public int a() {
        return this.b * this.c;
    }

    public ShapedRecipes c() {
        this.f = true;
        return this;
    }

    // CanaryMod
    public int getWidth() {
        return this.b;
    }

    public int getHeight() {
        return this.c;
    }

    public ItemStack[] getRecipeItems() {
        return this.d;
    }

    public CanaryShapedRecipe getCanaryRecipe() {
        return canary_recipe;
    }
    //
}
