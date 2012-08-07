package net.minecraft.server;


import net.minecraft.server.OBlock;
import net.minecraft.server.OCraftingManager;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;


public class ORecipesTools {

    private String[][] a = new String[][] { { "XXX", " # ", " # " }, { "X", "#", "#" }, { "XX", "X#", " #" }, { "XX", " #", " #" } };
    private Object[][] b;

    public ORecipesTools() {
        super();
        this.b = new Object[][] { { OBlock.x, OBlock.w, OItem.n, OItem.m, OItem.o }, { OItem.s, OItem.w, OItem.f, OItem.A, OItem.H }, { OItem.r, OItem.v, OItem.e, OItem.z, OItem.G }, { OItem.t, OItem.x, OItem.g, OItem.B, OItem.I }, { OItem.M, OItem.N, OItem.O, OItem.P, OItem.Q } };
    }

    public void a(OCraftingManager var1) {
        for (int var2 = 0; var2 < this.b[0].length; ++var2) {
            Object var3 = this.b[0][var2];

            for (int var4 = 0; var4 < this.b.length - 1; ++var4) {
                OItem var5 = (OItem) this.b[var4 + 1][var2];

                var1.a(new OItemStack(var5), new Object[] { this.a[var4], Character.valueOf('#'), OItem.C, Character.valueOf('X'), var3 });
            }
        }

        var1.a(new OItemStack(OItem.bd), new Object[] { " #", "# ", Character.valueOf('#'), OItem.n });
    }
}
