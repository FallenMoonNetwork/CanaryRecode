package net.minecraft.server;

import java.util.List;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityBoat;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumMovingObjectType;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OMovingObjectPosition;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;

public class OItemBoat extends OItem {

    public OItemBoat(int var1) {
        super(var1);
        this.bQ = 1;
    }

    public OItemStack a(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        float var4 = 1.0F;
        float var5 = var3.bv + (var3.bt - var3.bv) * var4;
        float var6 = var3.bu + (var3.bs - var3.bu) * var4;
        double var7 = var3.bj + (var3.bm - var3.bj) * var4;
        double var9 = var3.bk + (var3.bn - var3.bk) * var4 + 1.62D - var3.bF;
        double var11 = var3.bl + (var3.bo - var3.bl) * var4;
        OVec3D var13 = OVec3D.b(var7, var9, var11);
        float var14 = OMathHelper.b(-var6 * 0.017453292F - 3.1415927F);
        float var15 = OMathHelper.a(-var6 * 0.017453292F - 3.1415927F);
        float var16 = -OMathHelper.b(-var5 * 0.017453292F);
        float var17 = OMathHelper.a(-var5 * 0.017453292F);
        float var18 = var15 * var16;
        float var20 = var14 * var16;
        double var21 = 5.0D;
        OVec3D var23 = var13.c(var18 * var21, var17 * var21, var20 * var21);
        OMovingObjectPosition var24 = var2.a(var13, var23, true);
        if (var24 == null) {
            return var1;
        } else {
            OVec3D var25 = var3.f(var4);
            boolean var26 = false;
            float var27 = 1.0F;
            List var28 = var2.b(var3, var3.bw.a(var25.a * var21, var25.b * var21, var25.c * var21).b(var27, var27, var27));

            for (int var29 = 0; var29 < var28.size(); ++var29) {
                OEntity var30 = (OEntity) var28.get(var29);
                if (var30.o_()) {
                    float var31 = var30.j_();
                    OAxisAlignedBB var32 = var30.bw.b(var31, var31, var31);
                    if (var32.a(var13)) {
                        var26 = true;
                    }
                }
            }

            if (var26) {
                return var1;
            } else {
                if (var24.a == OEnumMovingObjectType.a) {
                    int var33 = var24.b;
                    int var34 = var24.c;
                    int var35 = var24.d;
                    if (!var2.F) {
                        if (var2.a(var33, var34, var35) == OBlock.aS.bO) {
                            --var34;
                        }

                        var2.b((new OEntityBoat(var2, (var33 + 0.5F), (var34 + 1.0F), (var35 + 0.5F))));
                    }

                    if (!var3.L.d) {
                        --var1.a;
                    }
                }

                return var1;
            }
        }
    }
}
