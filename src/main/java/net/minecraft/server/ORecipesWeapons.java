package net.minecraft.server;


import net.minecraft.server.OBlock;
import net.minecraft.server.OCraftingManager;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;


public class ORecipesWeapons {

    private String[][] a = new String[][] { { "X", "X", "#" } };
    private Object[][] b;

    public ORecipesWeapons() {
        super();
        this.b = new Object[][] { { OBlock.x, OBlock.w, OItem.n, OItem.m, OItem.o }, { OItem.q, OItem.u, OItem.p, OItem.y, OItem.F } };
    }

    public void a(OCraftingManager var1) {
        for (int var2 = 0; var2 < this.b[0].length; ++var2) {
            Object var3 = this.b[0][var2];

            for (int var4 = 0; var4 < this.b.length - 1; ++var4) {
                OItem var5 = (OItem) this.b[var4 + 1][var2];

                var1.a(new OItemStack(var5), new Object[] { this.a[var4], Character.valueOf('#'), OItem.C, Character.valueOf('X'), var3 });
            }
        }

        var1.a(new OItemStack(OItem.j, 1), new Object[] { " #X", "# X", " #X", Character.valueOf('X'), OItem.J, Character.valueOf('#'), OItem.C });
        var1.a(new OItemStack(OItem.k, 4), new Object[] { "X", "#", "Y", Character.valueOf('Y'), OItem.K, Character.valueOf('X'), OItem.ao, Character.valueOf('#'), OItem.C });
    }
}
