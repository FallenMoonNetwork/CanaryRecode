package net.minecraft.server;


import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumMovingObjectType;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.OWorld;


public class OItemGlassBottle extends OItem {

    public OItemGlassBottle(int var1) {
        super(var1);
    }

    @Override
    public OItemStack a(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        OMovingObjectPosition var4 = this.a(var2, var3, true);

        if (var4 == null) {
            return var1;
        } else {
            if (var4.a == OEnumMovingObjectType.a) {
                int var5 = var4.b;
                int var6 = var4.c;
                int var7 = var4.d;

                if (!var2.a(var3, var5, var6, var7)) {
                    return var1;
                }

                if (!var3.d(var5, var6, var7)) {
                    return var1;
                }

                if (var2.d(var5, var6, var7) == OMaterial.g) {
                    --var1.a;
                    if (var1.a <= 0) {
                        return new OItemStack(OItem.br);
                    }

                    if (!var3.k.a(new OItemStack(OItem.br))) {
                        var3.b(new OItemStack(OItem.br.bP, 1, 0));
                    }
                }
            }

            return var1;
        }
    }
}
