package net.minecraft.server;

import net.canarymod.api.inventory.recipes.SmeltRecipe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FurnaceRecipes {

    private static final FurnaceRecipes a = new FurnaceRecipes();
    private Map b = new HashMap();
    private Map c = new HashMap();

    public static final FurnaceRecipes a() {
        return a;
    }

    private FurnaceRecipes() {
        this.a(Block.M.cF, new ItemStack(Item.q), 0.7F);
        this.a(Block.L.cF, new ItemStack(Item.r), 1.0F);
        this.a(Block.aB.cF, new ItemStack(Item.p), 1.0F);
        this.a(Block.J.cF, new ItemStack(Block.R), 0.1F);
        this.a(Item.as.cv, new ItemStack(Item.at), 0.35F);
        this.a(Item.bk.cv, new ItemStack(Item.bl), 0.35F);
        this.a(Item.bm.cv, new ItemStack(Item.bn), 0.35F);
        this.a(Item.aW.cv, new ItemStack(Item.aX), 0.35F);
        this.a(Block.B.cF, new ItemStack(Block.y), 0.1F);
        this.a(Item.aK.cv, new ItemStack(Item.aJ), 0.3F);
        this.a(Block.bb.cF, new ItemStack(Block.cD), 0.35F);
        this.a(Block.ba.cF, new ItemStack(Item.aY, 1, 2), 0.2F);
        this.a(Block.O.cF, new ItemStack(Item.o, 1, 1), 0.15F);
        this.a(Block.bW.cF, new ItemStack(Item.bJ), 1.0F);
        this.a(Item.bN.cv, new ItemStack(Item.bO), 0.35F);
        this.a(Block.bg.cF, new ItemStack(Item.ca), 0.1F);
        this.a(Block.N.cF, new ItemStack(Item.o), 0.1F);
        this.a(Block.aS.cF, new ItemStack(Item.aE), 0.7F);
        this.a(Block.S.cF, new ItemStack(Item.aY, 1, 4), 0.2F);
        this.a(Block.cu.cF, new ItemStack(Item.cb), 0.2F);
    }

    public void a(int i0, ItemStack itemstack, float f0) {
        this.b.put(Integer.valueOf(i0), itemstack);
        this.c.put(Integer.valueOf(itemstack.d), Float.valueOf(f0));
    }

    public ItemStack b(int i0) {
        return (ItemStack)this.b.get(Integer.valueOf(i0));
    }

    public Map b() {
        return this.b;
    }

    public float c(int i0) {
        return this.c.containsKey(Integer.valueOf(i0)) ? ((Float)this.c.get(Integer.valueOf(i0))).floatValue() : 0.0F;
    }

    // CanaryMod
    public List<SmeltRecipe> getSmeltingRecipes() {
        List<SmeltRecipe> smelting_recipes = new ArrayList<SmeltRecipe>();
        for (Object key : this.b.keySet()) {
            int fromId = ((Integer)key).intValue();
            net.canarymod.api.inventory.Item result = ((ItemStack)this.b.get(key)).getCanaryItem();
            float xp = this.c(result.getId());
            smelting_recipes.add(new SmeltRecipe(fromId, result, xp));
        }
        return smelting_recipes;
    }

    public boolean removeSmeltingRecipe(SmeltRecipe recipe) {
        if (this.b.containsKey(Integer.valueOf(recipe.getItemIDFrom()))) {
            this.b.remove(Integer.valueOf(recipe.getItemIDFrom()));
            this.c.remove(Integer.valueOf(recipe.getResult().getId()));
            return true;
        }
        return false;
    }
    //
}
