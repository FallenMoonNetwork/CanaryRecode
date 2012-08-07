package net.minecraft.server;


import net.minecraft.server.OBlock;
import net.minecraft.server.OCraftingManager;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;


public class ORecipesFood {

    public ORecipesFood() {
        super();
    }

    public void a(OCraftingManager var1) {
        var1.b(new OItemStack(OItem.E), new Object[] { OBlock.af, OBlock.ag, OItem.D });
        var1.a(new OItemStack(OItem.bb, 8), new Object[] { "#X#", Character.valueOf('X'), new OItemStack(OItem.aV, 1, 3), Character.valueOf('#'), OItem.S });
        var1.a(new OItemStack(OBlock.br), new Object[] { "MMM", "MMM", "MMM", Character.valueOf('M'), OItem.be });
        var1.a(new OItemStack(OItem.bg), new Object[] { "M", Character.valueOf('M'), OItem.be });
        var1.a(new OItemStack(OItem.bf, 4), new Object[] { "M", Character.valueOf('M'), OBlock.ba });
        var1.b(new OItemStack(OItem.bu), new Object[] { OItem.bt, OBlock.af, OItem.aX });
        var1.b(new OItemStack(OItem.bA), new Object[] { OItem.be, OItem.bp });
        var1.b(new OItemStack(OItem.bv, 2), new Object[] { OItem.bn });
        var1.b(new OItemStack(OItem.bw), new Object[] { OItem.bv, OItem.aL });
    }
}
