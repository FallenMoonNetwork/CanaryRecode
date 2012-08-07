package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockCloth;
import net.minecraft.server.OCraftingManager;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;

public class ORecipesDyes {

    public ORecipesDyes() {
        super();
    }

    public void a(OCraftingManager var1) {
        for (int var2 = 0; var2 < 16; ++var2) {
            var1.b(new OItemStack(OBlock.ab, 1, OBlockCloth.e(var2)), new Object[] { new OItemStack(OItem.aV, 1, var2), new OItemStack(OItem.d[OBlock.ab.bO], 1, 0) });
        }

        var1.b(new OItemStack(OItem.aV, 2, 11), new Object[] { OBlock.ad });
        var1.b(new OItemStack(OItem.aV, 2, 1), new Object[] { OBlock.ae });
        var1.b(new OItemStack(OItem.aV, 3, 15), new Object[] { OItem.aW });
        var1.b(new OItemStack(OItem.aV, 2, 9), new Object[] { new OItemStack(OItem.aV, 1, 1), new OItemStack(OItem.aV, 1, 15) });
        var1.b(new OItemStack(OItem.aV, 2, 14), new Object[] { new OItemStack(OItem.aV, 1, 1), new OItemStack(OItem.aV, 1, 11) });
        var1.b(new OItemStack(OItem.aV, 2, 10), new Object[] { new OItemStack(OItem.aV, 1, 2), new OItemStack(OItem.aV, 1, 15) });
        var1.b(new OItemStack(OItem.aV, 2, 8), new Object[] { new OItemStack(OItem.aV, 1, 0), new OItemStack(OItem.aV, 1, 15) });
        var1.b(new OItemStack(OItem.aV, 2, 7), new Object[] { new OItemStack(OItem.aV, 1, 8), new OItemStack(OItem.aV, 1, 15) });
        var1.b(new OItemStack(OItem.aV, 3, 7), new Object[] { new OItemStack(OItem.aV, 1, 0), new OItemStack(OItem.aV, 1, 15), new OItemStack(OItem.aV, 1, 15) });
        var1.b(new OItemStack(OItem.aV, 2, 12), new Object[] { new OItemStack(OItem.aV, 1, 4), new OItemStack(OItem.aV, 1, 15) });
        var1.b(new OItemStack(OItem.aV, 2, 6), new Object[] { new OItemStack(OItem.aV, 1, 4), new OItemStack(OItem.aV, 1, 2) });
        var1.b(new OItemStack(OItem.aV, 2, 5), new Object[] { new OItemStack(OItem.aV, 1, 4), new OItemStack(OItem.aV, 1, 1) });
        var1.b(new OItemStack(OItem.aV, 2, 13), new Object[] { new OItemStack(OItem.aV, 1, 5), new OItemStack(OItem.aV, 1, 9) });
        var1.b(new OItemStack(OItem.aV, 3, 13), new Object[] { new OItemStack(OItem.aV, 1, 4), new OItemStack(OItem.aV, 1, 1), new OItemStack(OItem.aV, 1, 9) });
        var1.b(new OItemStack(OItem.aV, 4, 13), new Object[] { new OItemStack(OItem.aV, 1, 4), new OItemStack(OItem.aV, 1, 1), new OItemStack(OItem.aV, 1, 1), new OItemStack(OItem.aV, 1, 15) });
    }
}
