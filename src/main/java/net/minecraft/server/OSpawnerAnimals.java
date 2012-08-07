package net.minecraft.server;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OBlock;
import net.minecraft.server.OChunk;
import net.minecraft.server.OChunkCoordIntPair;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.OChunkPosition;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityOcelot;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntitySheep;
import net.minecraft.server.OEntitySkeleton;
import net.minecraft.server.OEntitySpider;
import net.minecraft.server.OEntityZombie;
import net.minecraft.server.OEnumCreatureType;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OSpawnListEntry;
import net.minecraft.server.OWeightedRandom;
import net.minecraft.server.OWorld;

public final class OSpawnerAnimals {

    private static HashMap b = new HashMap();
    protected static final Class[] a = new Class[] { OEntitySpider.class, OEntityZombie.class, OEntitySkeleton.class };

    public OSpawnerAnimals() {
        super();
    }

    protected static OChunkPosition a(OWorld var0, int var1, int var2) {
        OChunk var3 = var0.d(var1, var2);
        int var4 = var1 * 16 + var0.r.nextInt(16);
        int var5 = var0.r.nextInt(var3 == null ? 128 : Math.max(128, var3.g()));
        int var6 = var2 * 16 + var0.r.nextInt(16);
        return new OChunkPosition(var4, var5, var6);
    }

    public static final int a(OWorld var0, boolean var1, boolean var2) {
        if (!var1 && !var2) {
            return 0;
        } else {
            b.clear();

            int var3;
            int var6;
            for (var3 = 0; var3 < var0.d.size(); ++var3) {
                OEntityPlayer var4 = (OEntityPlayer) var0.d.get(var3);
                int var5 = OMathHelper.b(var4.bm / 16.0D);
                var6 = OMathHelper.b(var4.bo / 16.0D);
                byte var7 = 8;

                for (int var8 = -var7; var8 <= var7; ++var8) {
                    for (int var9 = -var7; var9 <= var7; ++var9) {
                        boolean var10 = var8 == -var7 || var8 == var7 || var9 == -var7 || var9 == var7;
                        OChunkCoordIntPair var11 = new OChunkCoordIntPair(var8 + var5, var9 + var6);
                        if (!var10) {
                            b.put(var11, Boolean.valueOf(false));
                        } else if (!b.containsKey(var11)) {
                            b.put(var11, Boolean.valueOf(true));
                        }
                    }
                }
            }

            var3 = 0;
            OChunkCoordinates var31 = var0.p();
            OEnumCreatureType[] var32 = OEnumCreatureType.values();
            var6 = var32.length;

            for (int var33 = 0; var33 < var6; ++var33) {
                OEnumCreatureType var34 = var32[var33];
                if ((!var34.d() || var2) && (var34.d() || var1) && var0.a(var34.a()) <= var34.b() * b.size() / 256) {
                    Iterator var35 = b.keySet().iterator();

                    label108: while (var35.hasNext()) {
                        OChunkCoordIntPair var37 = (OChunkCoordIntPair) var35.next();
                        if (!((Boolean) b.get(var37)).booleanValue()) {
                            OChunkPosition var36 = a(var0, var37.a, var37.b);
                            int var12 = var36.a;
                            int var13 = var36.b;
                            int var14 = var36.c;
                            if (!var0.e(var12, var13, var14) && var0.d(var12, var13, var14) == var34.c()) {
                                int var15 = 0;
                                int var16 = 0;

                                while (var16 < 3) {
                                    int var17 = var12;
                                    int var18 = var13;
                                    int var19 = var14;
                                    byte var20 = 6;
                                    OSpawnListEntry var21 = null;
                                    int var22 = 0;

                                    while (true) {
                                        if (var22 < 4) {
                                            label101: {
                                                var17 += var0.r.nextInt(var20) - var0.r.nextInt(var20);
                                                var18 += var0.r.nextInt(1) - var0.r.nextInt(1);
                                                var19 += var0.r.nextInt(var20) - var0.r.nextInt(var20);
                                                if (a(var34, var0, var17, var18, var19)) {
                                                    float var23 = var17 + 0.5F;
                                                    float var24 = var18;
                                                    float var25 = var19 + 0.5F;
                                                    if (var0.a(var23, var24, var25, 24.0D) == null) {
                                                        float var26 = var23 - var31.a;
                                                        float var27 = var24 - var31.b;
                                                        float var28 = var25 - var31.c;
                                                        float var29 = var26 * var26 + var27 * var27 + var28 * var28;
                                                        if (var29 >= 576.0F) {
                                                            if (var21 == null) {
                                                                var21 = var0.a(var34, var17, var18, var19);
                                                                if (var21 == null) {
                                                                    break label101;
                                                                }
                                                            }

                                                            OEntityLiving var38;
                                                            try {
                                                                var38 = (OEntityLiving) var21.a.getConstructor(new Class[] { OWorld.class }).newInstance(new Object[] { var0 });
                                                            } catch (Exception var30) {
                                                                var30.printStackTrace();
                                                                return var3;
                                                            }

                                                            var38.c(var23, var24, var25, var0.r.nextFloat() * 360.0F, 0.0F);
                                                            if (var38.l()) {
                                                                ++var15;
                                                                var0.b(var38);
                                                                a(var38, var0, var23, var24, var25);
                                                                if (var15 >= var38.q()) {
                                                                    continue label108;
                                                                }
                                                            }

                                                            var3 += var15;
                                                        }
                                                    }
                                                }

                                                ++var22;
                                                continue;
                                            }
                                        }

                                        ++var16;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return var3;
        }
    }

    public static boolean a(OEnumCreatureType var0, OWorld var1, int var2, int var3, int var4) {
        if (var0.c() == OMaterial.g) {
            return var1.d(var2, var3, var4).d() && !var1.e(var2, var3 + 1, var4);
        } else {
            int var5 = var1.a(var2, var3 - 1, var4);
            return OBlock.g(var5) && var5 != OBlock.z.bO && !var1.e(var2, var3, var4) && !var1.d(var2, var3, var4).d() && !var1.e(var2, var3 + 1, var4);
        }
    }

    private static void a(OEntityLiving var0, OWorld var1, float var2, float var3, float var4) {
        if (var0 instanceof OEntitySpider && var1.r.nextInt(100) == 0) {
            OEntitySkeleton var7 = new OEntitySkeleton(var1);
            var7.c(var2, var3, var4, var0.bs, 0.0F);
            var1.b(var7);
            var7.b(var0);
        } else if (var0 instanceof OEntitySheep) {
            ((OEntitySheep) var0).d_(OEntitySheep.a(var1.r));
        } else if (var0 instanceof OEntityOcelot && var1.r.nextInt(7) == 0) {
            for (int var5 = 0; var5 < 2; ++var5) {
                OEntityOcelot var6 = new OEntityOcelot(var1);
                var6.c(var2, var3, var4, var0.bs, 0.0F);
                var6.c(-24000);
                var1.b(var6);
            }
        }

    }

    public static void a(OWorld var0, OBiomeGenBase var1, int var2, int var3, int var4, int var5, Random var6) {
        List var7 = var1.a(OEnumCreatureType.b);
        if (!var7.isEmpty()) {
            while (var6.nextFloat() < var1.f()) {
                OSpawnListEntry var8 = (OSpawnListEntry) OWeightedRandom.a(var0.r, var7);
                int var9 = var8.b + var6.nextInt(1 + var8.c - var8.b);
                int var10 = var2 + var6.nextInt(var4);
                int var11 = var3 + var6.nextInt(var5);
                int var12 = var10;
                int var13 = var11;

                for (int var14 = 0; var14 < var9; ++var14) {
                    boolean var15 = false;

                    for (int var16 = 0; !var15 && var16 < 4; ++var16) {
                        int var17 = var0.g(var10, var11);
                        if (a(OEnumCreatureType.b, var0, var10, var17, var11)) {
                            float var18 = var10 + 0.5F;
                            float var19 = var17;
                            float var20 = var11 + 0.5F;

                            OEntityLiving var21;
                            try {
                                var21 = (OEntityLiving) var8.a.getConstructor(new Class[] { OWorld.class }).newInstance(new Object[] { var0 });
                            } catch (Exception var23) {
                                var23.printStackTrace();
                                continue;
                            }

                            var21.c(var18, var19, var20, var6.nextFloat() * 360.0F, 0.0F);
                            var0.b(var21);
                            a(var21, var0, var18, var19, var20);
                            var15 = true;
                        }

                        var10 += var6.nextInt(5) - var6.nextInt(5);

                        for (var11 += var6.nextInt(5) - var6.nextInt(5); var10 < var2 || var10 >= var2 + var4 || var11 < var3 || var11 >= var3 + var4; var11 = var13 + var6.nextInt(5) - var6.nextInt(5)) {
                            var10 = var12 + var6.nextInt(5) - var6.nextInt(5);
                        }
                    }
                }
            }

        }
    }

}
