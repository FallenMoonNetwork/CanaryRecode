package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.server.OIRecipe;
import net.minecraft.server.OInventoryCrafting;
import net.minecraft.server.OItemStack;

public class OShapelessRecipes implements OIRecipe {

    private final OItemStack a;
    private final List b;

    public OShapelessRecipes(OItemStack var1, List var2) {
        super();
        this.a = var1;
        this.b = var2;
    }

    @Override
    public OItemStack b() {
        return this.a;
    }

    @Override
    public boolean a(OInventoryCrafting var1) {
        ArrayList var2 = new ArrayList(this.b);

        for (int var3 = 0; var3 < 3; ++var3) {
            for (int var4 = 0; var4 < 3; ++var4) {
                OItemStack var5 = var1.b(var4, var3);
                if (var5 != null) {
                    boolean var6 = false;
                    Iterator var7 = var2.iterator();

                    while (var7.hasNext()) {
                        OItemStack var8 = (OItemStack) var7.next();
                        if (var5.c == var8.c && (var8.h() == -1 || var5.h() == var8.h())) {
                            var6 = true;
                            var2.remove(var8);
                            break;
                        }
                    }

                    if (!var6) {
                        return false;
                    }
                }
            }
        }

        return var2.isEmpty();
    }

    @Override
    public OItemStack b(OInventoryCrafting var1) {
        return this.a.j();
    }

    @Override
    public int a() {
        return this.b.size();
    }
}
