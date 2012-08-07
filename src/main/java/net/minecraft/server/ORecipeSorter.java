package net.minecraft.server;


import java.util.Comparator;
import net.minecraft.server.OCraftingManager;
import net.minecraft.server.OIRecipe;
import net.minecraft.server.OShapedRecipes;
import net.minecraft.server.OShapelessRecipes;


class ORecipeSorter implements Comparator {

    // $FF: synthetic field
    final OCraftingManager a;

    ORecipeSorter(OCraftingManager var1) {
        super();
        this.a = var1;
    }

    public int a(OIRecipe var1, OIRecipe var2) {
        return var1 instanceof OShapelessRecipes && var2 instanceof OShapedRecipes ? 1 : (var2 instanceof OShapelessRecipes && var1 instanceof OShapedRecipes ? -1 : (var2.a() < var1.a() ? -1 : (var2.a() > var1.a() ? 1 : 0)));
    }

    // $FF: synthetic method
    // $FF: bridge method
    @Override
    public int compare(Object var1, Object var2) {
        return this.a((OIRecipe) var1, (OIRecipe) var2);
    }
}
