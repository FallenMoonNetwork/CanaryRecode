package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OTileEntityChest;
import net.minecraft.server.OTileEntityMobSpawner;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenerator;

public class OWorldGenDungeons extends OWorldGenerator {

    public OWorldGenDungeons() {
        super();
    }

    @Override
    public boolean a(OWorld var1, Random var2, int var3, int var4, int var5) {
        byte var6 = 3;
        int var7 = var2.nextInt(2) + 2;
        int var8 = var2.nextInt(2) + 2;
        int var9 = 0;

        int var10;
        int var11;
        int var12;
        for (var10 = var3 - var7 - 1; var10 <= var3 + var7 + 1; ++var10) {
            for (var11 = var4 - 1; var11 <= var4 + var6 + 1; ++var11) {
                for (var12 = var5 - var8 - 1; var12 <= var5 + var8 + 1; ++var12) {
                    OMaterial var13 = var1.d(var10, var11, var12);
                    if (var11 == var4 - 1 && !var13.a()) {
                        return false;
                    }

                    if (var11 == var4 + var6 + 1 && !var13.a()) {
                        return false;
                    }

                    if ((var10 == var3 - var7 - 1 || var10 == var3 + var7 + 1 || var12 == var5 - var8 - 1 || var12 == var5 + var8 + 1) && var11 == var4 && var1.g(var10, var11, var12) && var1.g(var10, var11 + 1, var12)) {
                        ++var9;
                    }
                }
            }
        }

        if (var9 >= 1 && var9 <= 5) {
            for (var10 = var3 - var7 - 1; var10 <= var3 + var7 + 1; ++var10) {
                for (var11 = var4 + var6; var11 >= var4 - 1; --var11) {
                    for (var12 = var5 - var8 - 1; var12 <= var5 + var8 + 1; ++var12) {
                        if (var10 != var3 - var7 - 1 && var11 != var4 - 1 && var12 != var5 - var8 - 1 && var10 != var3 + var7 + 1 && var11 != var4 + var6 + 1 && var12 != var5 + var8 + 1) {
                            var1.e(var10, var11, var12, 0);
                        } else if (var11 >= 0 && !var1.d(var10, var11 - 1, var12).a()) {
                            var1.e(var10, var11, var12, 0);
                        } else if (var1.d(var10, var11, var12).a()) {
                            if (var11 == var4 - 1 && var2.nextInt(4) != 0) {
                                var1.e(var10, var11, var12, OBlock.ao.bO);
                            } else {
                                var1.e(var10, var11, var12, OBlock.w.bO);
                            }
                        }
                    }
                }
            }

            var10 = 0;

            while (var10 < 2) {
                var11 = 0;

                while (true) {
                    if (var11 < 3) {
                        label210: {
                            var12 = var3 + var2.nextInt(var7 * 2 + 1) - var7;
                            int var14 = var5 + var2.nextInt(var8 * 2 + 1) - var8;
                            if (var1.g(var12, var4, var14)) {
                                int var15 = 0;
                                if (var1.d(var12 - 1, var4, var14).a()) {
                                    ++var15;
                                }

                                if (var1.d(var12 + 1, var4, var14).a()) {
                                    ++var15;
                                }

                                if (var1.d(var12, var4, var14 - 1).a()) {
                                    ++var15;
                                }

                                if (var1.d(var12, var4, var14 + 1).a()) {
                                    ++var15;
                                }

                                if (var15 == 1) {
                                    var1.e(var12, var4, var14, OBlock.au.bO);
                                    OTileEntityChest var16 = (OTileEntityChest) var1.b(var12, var4, var14);
                                    if (var16 != null) {
                                        for (int var17 = 0; var17 < 8; ++var17) {
                                            OItemStack var18 = this.a(var2);
                                            if (var18 != null) {
                                                var16.setItemStackToSlot(var2.nextInt(var16.getInventorySize()), var18);
                                            }
                                        }
                                    }
                                    break label210;
                                }
                            }

                            ++var11;
                            continue;
                        }
                    }

                    ++var10;
                    break;
                }
            }

            var1.e(var3, var4, var5, OBlock.as.bO);
            OTileEntityMobSpawner var19 = (OTileEntityMobSpawner) var1.b(var3, var4, var5);
            if (var19 != null) {
                var19.a(this.b(var2));
            } else {
                System.err.println("Failed to fetch mob spawner entity at (" + var3 + ", " + var4 + ", " + var5 + ")");
            }

            return true;
        } else {
            return false;
        }
    }

    private OItemStack a(Random var1) {
        int var2 = var1.nextInt(11);
        return var2 == 0 ? new OItemStack(OItem.az) : (var2 == 1 ? new OItemStack(OItem.n, var1.nextInt(4) + 1) : (var2 == 2 ? new OItemStack(OItem.T) : (var2 == 3 ? new OItemStack(OItem.S, var1.nextInt(4) + 1) : (var2 == 4 ? new OItemStack(OItem.L, var1.nextInt(4) + 1) : (var2 == 5 ? new OItemStack(OItem.J, var1.nextInt(4) + 1) : (var2 == 6 ? new OItemStack(OItem.av) : (var2 == 7 && var1.nextInt(100) == 0 ? new OItemStack(OItem.as) : (var2 == 8 && var1.nextInt(2) == 0 ? new OItemStack(OItem.aB, var1.nextInt(4) + 1) : (var2 == 9 && var1.nextInt(10) == 0 ? new OItemStack(OItem.d[OItem.bE.bP + var1.nextInt(2)]) : (var2 == 10 ? new OItemStack(OItem.aV, 1, 3) : null))))))))));
    }

    private String b(Random var1) {
        int var2 = var1.nextInt(4);
        return var2 == 0 ? "Skeleton" : (var2 == 1 ? "Zombie" : (var2 == 2 ? "Zombie" : (var2 == 3 ? "Spider" : "")));
    }
}
