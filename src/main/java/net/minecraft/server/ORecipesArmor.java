package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OCraftingManager;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;

public class ORecipesArmor {

    private String[][] a = new String[][] { { "XXX", "X X" }, { "X X", "XXX", "XXX" }, { "XXX", "X X", "X X" }, { "X X", "X X" } };
    private Object[][] b;

    public ORecipesArmor() {
        super();
        this.b = new Object[][] { { OItem.aE, OBlock.ar, OItem.n, OItem.m, OItem.o }, { OItem.U, OItem.Y, OItem.ac, OItem.ag, OItem.ak }, { OItem.V, OItem.Z, OItem.ad, OItem.ah, OItem.al }, { OItem.W, OItem.aa, OItem.ae, OItem.ai, OItem.am }, { OItem.X, OItem.ab, OItem.af, OItem.aj, OItem.an } };
    }

    public void a(OCraftingManager var1) {
        for (int var2 = 0; var2 < this.b[0].length; ++var2) {
            Object var3 = this.b[0][var2];

            for (int var4 = 0; var4 < this.b.length - 1; ++var4) {
                OItem var5 = (OItem) this.b[var4 + 1][var2];
                var1.a(new OItemStack(var5), new Object[] { this.a[var4], Character.valueOf('X'), var3 });
            }
        }

    }
}
