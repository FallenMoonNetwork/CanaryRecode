package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumMovingObjectType;
import net.minecraft.server.OItemColored;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.OWorld;

public class OItemLilyPad extends OItemColored {

    public OItemLilyPad(int var1) {
        super(var1, false);
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

                if (var2.d(var5, var6, var7) == OMaterial.g && var2.c(var5, var6, var7) == 0 && var2.g(var5, var6 + 1, var7)) {
                    var2.e(var5, var6 + 1, var7, OBlock.bz.bO);
                    if (!var3.L.d) {
                        --var1.a;
                    }
                }
            }

            return var1;
        }
    }
}
