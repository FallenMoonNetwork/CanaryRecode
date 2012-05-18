package net.minecraft.server;

import net.minecraft.server.OBlock;
import net.minecraft.server.OBlockCloth;
import net.minecraft.server.OBlockCrops;
import net.minecraft.server.OBlockMushroom;
import net.minecraft.server.OBlockSapling;
import net.minecraft.server.OBlockStem;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntitySheep;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OWorld;

public class OItemDye extends OItem {

    public static final String[] a = new String[] { "black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "lightBlue", "magenta", "orange", "white" };
    public static final int[] b = new int[] { 1973019, 11743532, 3887386, 5320730, 2437522, 8073150, 2651799, 2651799, 4408131, 14188952, 4312372, 14602026, 6719955, 12801229, 15435844, 15790320 };

    public OItemDye(int var1) {
        super(var1);
        this.a(true);
        this.f(0);
    }

    public String a(OItemStack var1) {
        int var2 = OMathHelper.a(var1.h(), 0, 15);
        return super.b() + "." + a[var2];
    }

    public boolean a(OItemStack var1, OEntityPlayer var2, OWorld var3, int var4, int var5, int var6, int var7) {
        if (!var2.d(var4, var5, var6)) {
            return false;
        } else {
            if (var1.h() == 15) {
                int var8 = var3.a(var4, var5, var6);
                if (var8 == OBlock.y.bO) {
                    if (!var3.F) {
                        ((OBlockSapling) OBlock.y).b(var3, var4, var5, var6, var3.r);
                        --var1.a;
                    }

                    return true;
                }

                if (var8 == OBlock.af.bO || var8 == OBlock.ag.bO) {
                    if (!var3.F && ((OBlockMushroom) OBlock.m[var8]).b(var3, var4, var5, var6, var3.r)) {
                        --var1.a;
                    }

                    return true;
                }

                if (var8 == OBlock.bt.bO || var8 == OBlock.bs.bO) {
                    if (!var3.F) {
                        ((OBlockStem) OBlock.m[var8]).g(var3, var4, var5, var6);
                        --var1.a;
                    }

                    return true;
                }

                if (var8 == OBlock.az.bO) {
                    if (!var3.F) {
                        ((OBlockCrops) OBlock.az).g(var3, var4, var5, var6);
                        --var1.a;
                    }

                    return true;
                }

                if (var8 == OBlock.u.bO) {
                    if (!var3.F) {
                        --var1.a;

                        label73: for (int var9 = 0; var9 < 128; ++var9) {
                            int var10 = var4;
                            int var11 = var5 + 1;
                            int var12 = var6;

                            for (int var13 = 0; var13 < var9 / 16; ++var13) {
                                var10 += c.nextInt(3) - 1;
                                var11 += (c.nextInt(3) - 1) * c.nextInt(3) / 2;
                                var12 += c.nextInt(3) - 1;
                                if (var3.a(var10, var11 - 1, var12) != OBlock.u.bO || var3.e(var10, var11, var12)) {
                                    continue label73;
                                }
                            }

                            if (var3.a(var10, var11, var12) == 0) {
                                if (c.nextInt(10) != 0) {
                                    var3.b(var10, var11, var12, OBlock.X.bO, 1);
                                } else if (c.nextInt(3) != 0) {
                                    var3.e(var10, var11, var12, OBlock.ad.bO);
                                } else {
                                    var3.e(var10, var11, var12, OBlock.ae.bO);
                                }
                            }
                        }
                    }

                    return true;
                }
            }

            return false;
        }
    }

    public void a(OItemStack var1, OEntityLiving var2) {
        if (var2 instanceof OEntitySheep) {
            OEntitySheep var3 = (OEntitySheep) var2;
            int var4 = OBlockCloth.d(var1.h());
            if (!var3.A_() && var3.x() != var4) {
                var3.d_(var4);
                --var1.a;
            }
        }

    }

}
