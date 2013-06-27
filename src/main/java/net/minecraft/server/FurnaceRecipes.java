package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.canarymod.api.inventory.recipes.SmeltRecipe;

public class FurnaceRecipes {

    private static final FurnaceRecipes a = new FurnaceRecipes();
    private Map b = new HashMap();
    private Map c = new HashMap();

    public static final FurnaceRecipes a() {
        return a;
    }

    private FurnaceRecipes() {
        this.a(Block.L.cz, new ItemStack(Item.p), 0.7F);
        this.a(Block.K.cz, new ItemStack(Item.q), 1.0F);
        this.a(Block.aA.cz, new ItemStack(Item.o), 1.0F);
        this.a(Block.I.cz, new ItemStack(Block.Q), 0.1F);
        this.a(Item.ar.cp, new ItemStack(Item.as), 0.35F);
        this.a(Item.bj.cp, new ItemStack(Item.bk), 0.35F);
        this.a(Item.bl.cp, new ItemStack(Item.bm), 0.35F);
        this.a(Item.aV.cp, new ItemStack(Item.aW), 0.35F);
        this.a(Block.A.cz, new ItemStack(Block.x), 0.1F);
        this.a(Item.aJ.cp, new ItemStack(Item.aI), 0.3F);
        this.a(Block.aZ.cz, new ItemStack(Item.aX, 1, 2), 0.2F);
        this.a(Block.N.cz, new ItemStack(Item.n, 1, 1), 0.15F);
        this.a(Block.bV.cz, new ItemStack(Item.bI), 1.0F);
        this.a(Item.bM.cp, new ItemStack(Item.bN), 0.35F);
        this.a(Block.bf.cz, new ItemStack(Item.bZ), 0.1F);
        this.a(Block.M.cz, new ItemStack(Item.n), 0.1F);
        this.a(Block.aR.cz, new ItemStack(Item.aD), 0.7F);
        this.a(Block.R.cz, new ItemStack(Item.aX, 1, 4), 0.2F);
        this.a(Block.ct.cz, new ItemStack(Item.ca), 0.2F);
    }

    public void a(int i0, ItemStack itemstack, float f0) {
        this.b.put(Integer.valueOf(i0), itemstack);
        this.c.put(Integer.valueOf(itemstack.c), Float.valueOf(f0));
    }

    public ItemStack b(int i0) {
        return (ItemStack) this.b.get(Integer.valueOf(i0));
    }

    public Map b() {
        return this.b;
    }

    public float c(int i0) {
        return this.c.containsKey(Integer.valueOf(i0)) ? ((Float) this.c.get(Integer.valueOf(i0))).floatValue() : 0.0F;
    }

    // CanaryMod
    public List<SmeltRecipe> getSmeltingRecipes() {
        List<SmeltRecipe> smelting_recipes = new ArrayList<SmeltRecipe>();
        for (Object key : this.b.keySet()) {
            int fromId = ((Integer) key).intValue();
            net.canarymod.api.inventory.Item result = ((ItemStack) this.b.get(key)).getCanaryItem();
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
