package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OCraftingManager;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;

public class ORecipesCrafting {

    public ORecipesCrafting() {
        super();
    }

    public void a(OCraftingManager var1) {
        var1.a(new OItemStack(OBlock.au), new Object[] { "###", "# #", "###", Character.valueOf('#'), OBlock.x });
        var1.a(new OItemStack(OBlock.aB), new Object[] { "###", "# #", "###", Character.valueOf('#'), OBlock.w });
        var1.a(new OItemStack(OBlock.ay), new Object[] { "##", "##", Character.valueOf('#'), OBlock.x });
        var1.a(new OItemStack(OBlock.Q), new Object[] { "##", "##", Character.valueOf('#'), OBlock.E });
        var1.a(new OItemStack(OBlock.Q, 4, 2), new Object[] { "##", "##", Character.valueOf('#'), OBlock.Q });
        var1.a(new OItemStack(OBlock.Q, 1, 1), new Object[] { "#", "#", Character.valueOf('#'), new OItemStack(OBlock.ak, 1, 1) });
        var1.a(new OItemStack(OBlock.bm, 4), new Object[] { "##", "##", Character.valueOf('#'), OBlock.t });
        var1.a(new OItemStack(OBlock.bp, 16), new Object[] { "###", "###", Character.valueOf('#'), OItem.n });
        var1.a(new OItemStack(OBlock.bq, 16), new Object[] { "###", "###", Character.valueOf('#'), OBlock.M });
        var1.a(new OItemStack(OBlock.bL, 1), new Object[] { " R ", "RGR", " R ", Character.valueOf('R'), OItem.aB, Character.valueOf('G'), OBlock.bd });
    }
}
