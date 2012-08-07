package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OCraftingManager;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;

public class ORecipesIngots {

    private Object[][] a;

    public ORecipesIngots() {
        super();
        this.a = new Object[][] { { OBlock.ah, new OItemStack(OItem.o, 9) }, { OBlock.ai, new OItemStack(OItem.n, 9) }, { OBlock.ax, new OItemStack(OItem.m, 9) }, { OBlock.O, new OItemStack(OItem.aV, 9, 4) } };
    }

    public void a(OCraftingManager var1) {
        for (int var2 = 0; var2 < this.a.length; ++var2) {
            OBlock var3 = (OBlock) this.a[var2][0];
            OItemStack var4 = (OItemStack) this.a[var2][1];
            var1.a(new OItemStack(var3), new Object[] { "###", "###", "###", Character.valueOf('#'), var4 });
            var1.a(var4, new Object[] { "#", Character.valueOf('#'), var3 });
        }

        var1.a(new OItemStack(OItem.o), new Object[] { "###", "###", "###", Character.valueOf('#'), OItem.bp });
        var1.a(new OItemStack(OItem.bp, 9), new Object[] { "#", Character.valueOf('#'), OItem.o });
    }
}
