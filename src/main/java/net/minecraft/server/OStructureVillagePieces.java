package net.minecraft.server;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OComponentVillage;
import net.minecraft.server.OComponentVillageChurch;
import net.minecraft.server.OComponentVillageField;
import net.minecraft.server.OComponentVillageField2;
import net.minecraft.server.OComponentVillageHall;
import net.minecraft.server.OComponentVillageHouse1;
import net.minecraft.server.OComponentVillageHouse2;
import net.minecraft.server.OComponentVillageHouse3;
import net.minecraft.server.OComponentVillageHouse4_Garden;
import net.minecraft.server.OComponentVillagePathGen;
import net.minecraft.server.OComponentVillageStartPiece;
import net.minecraft.server.OComponentVillageTorch;
import net.minecraft.server.OComponentVillageWoodHut;
import net.minecraft.server.OMapGenVillage;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OStructureVillagePieceWeight;

public class OStructureVillagePieces {

    public OStructureVillagePieces() {
        super();
    }

    public static ArrayList a(Random var0, int var1) {
        ArrayList var2 = new ArrayList();
        var2.add(new OStructureVillagePieceWeight(OComponentVillageHouse4_Garden.class, 4, OMathHelper.a(var0, 2 + var1, 4 + var1 * 2)));
        var2.add(new OStructureVillagePieceWeight(OComponentVillageChurch.class, 20, OMathHelper.a(var0, 0 + var1, 1 + var1)));
        var2.add(new OStructureVillagePieceWeight(OComponentVillageHouse1.class, 20, OMathHelper.a(var0, 0 + var1, 2 + var1)));
        var2.add(new OStructureVillagePieceWeight(OComponentVillageWoodHut.class, 3, OMathHelper.a(var0, 2 + var1, 5 + var1 * 3)));
        var2.add(new OStructureVillagePieceWeight(OComponentVillageHall.class, 15, OMathHelper.a(var0, 0 + var1, 2 + var1)));
        var2.add(new OStructureVillagePieceWeight(OComponentVillageField.class, 3, OMathHelper.a(var0, 1 + var1, 4 + var1)));
        var2.add(new OStructureVillagePieceWeight(OComponentVillageField2.class, 3, OMathHelper.a(var0, 2 + var1, 4 + var1 * 2)));
        var2.add(new OStructureVillagePieceWeight(OComponentVillageHouse2.class, 15, OMathHelper.a(var0, 0, 1 + var1)));
        var2.add(new OStructureVillagePieceWeight(OComponentVillageHouse3.class, 8, OMathHelper.a(var0, 0 + var1, 3 + var1 * 2)));
        Iterator var3 = var2.iterator();

        while (var3.hasNext()) {
            if (((OStructureVillagePieceWeight) var3.next()).d == 0) {
                var3.remove();
            }
        }

        return var2;
    }

    private static int a(ArrayList var0) {
        boolean var1 = false;
        int var2 = 0;

        OStructureVillagePieceWeight var4;
        for (Iterator var3 = var0.iterator(); var3.hasNext(); var2 += var4.b) {
            var4 = (OStructureVillagePieceWeight) var3.next();
            if (var4.d > 0 && var4.c < var4.d) {
                var1 = true;
            }
        }

        return var1 ? var2 : -1;
    }

    private static OComponentVillage a(OStructureVillagePieceWeight var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
        Class var8 = var0.a;
        Object var9 = null;
        if (var8 == OComponentVillageHouse4_Garden.class) {
            var9 = OComponentVillageHouse4_Garden.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var8 == OComponentVillageChurch.class) {
            var9 = OComponentVillageChurch.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var8 == OComponentVillageHouse1.class) {
            var9 = OComponentVillageHouse1.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var8 == OComponentVillageWoodHut.class) {
            var9 = OComponentVillageWoodHut.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var8 == OComponentVillageHall.class) {
            var9 = OComponentVillageHall.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var8 == OComponentVillageField.class) {
            var9 = OComponentVillageField.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var8 == OComponentVillageField2.class) {
            var9 = OComponentVillageField2.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var8 == OComponentVillageHouse2.class) {
            var9 = OComponentVillageHouse2.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var8 == OComponentVillageHouse3.class) {
            var9 = OComponentVillageHouse3.a(var1, var2, var3, var4, var5, var6, var7);
        }

        return (OComponentVillage) var9;
    }

    private static OComponentVillage c(OComponentVillageStartPiece var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
        int var8 = a(var0.d);
        if (var8 <= 0) {
            return null;
        } else {
            int var9 = 0;

            while (var9 < 5) {
                ++var9;
                int var10 = var2.nextInt(var8);
                Iterator var11 = var0.d.iterator();

                while (var11.hasNext()) {
                    OStructureVillagePieceWeight var12 = (OStructureVillagePieceWeight) var11.next();
                    var10 -= var12.b;
                    if (var10 < 0) {
                        if (!var12.a(var7) || var12 == var0.c && var0.d.size() > 1) {
                            break;
                        }

                        OComponentVillage var13 = a(var12, var1, var2, var3, var4, var5, var6, var7);
                        if (var13 != null) {
                            ++var12.c;
                            var0.c = var12;
                            if (!var12.a()) {
                                var0.d.remove(var12);
                            }

                            return var13;
                        }
                    }
                }
            }

            OStructureBoundingBox var14 = OComponentVillageTorch.a(var1, var2, var3, var4, var5, var6);
            if (var14 != null) {
                return new OComponentVillageTorch(var7, var2, var14, var6);
            } else {
                return null;
            }
        }
    }

    private static OStructureComponent d(OComponentVillageStartPiece var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
        if (var7 > 50) {
            return null;
        } else if (Math.abs(var3 - var0.b().a) <= 112 && Math.abs(var5 - var0.b().c) <= 112) {
            OComponentVillage var8 = c(var0, var1, var2, var3, var4, var5, var6, var7 + 1);
            if (var8 != null) {
                int var9 = (var8.g.a + var8.g.d) / 2;
                int var10 = (var8.g.c + var8.g.f) / 2;
                int var11 = var8.g.d - var8.g.a;
                int var12 = var8.g.f - var8.g.c;
                int var13 = var11 > var12 ? var11 : var12;
                if (var0.a().a(var9, var10, var13 / 2 + 4, OMapGenVillage.a)) {
                    var1.add(var8);
                    var0.e.add(var8);
                    return var8;
                }
            }

            return null;
        } else {
            return null;
        }
    }

    private static OStructureComponent e(OComponentVillageStartPiece var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
        if (var7 > 3 + var0.b) {
            return null;
        } else if (Math.abs(var3 - var0.b().a) <= 112 && Math.abs(var5 - var0.b().c) <= 112) {
            OStructureBoundingBox var8 = OComponentVillagePathGen.a(var0, var1, var2, var3, var4, var5, var6);
            if (var8 != null && var8.b > 10) {
                OComponentVillagePathGen var9 = new OComponentVillagePathGen(var7, var2, var8, var6);
                int var10 = (var9.g.a + var9.g.d) / 2;
                int var11 = (var9.g.c + var9.g.f) / 2;
                int var12 = var9.g.d - var9.g.a;
                int var13 = var9.g.f - var9.g.c;
                int var14 = var12 > var13 ? var12 : var13;
                if (var0.a().a(var10, var11, var14 / 2 + 4, OMapGenVillage.a)) {
                    var1.add(var9);
                    var0.f.add(var9);
                    return var9;
                }
            }

            return null;
        } else {
            return null;
        }
    }

    // $FF: synthetic method
    static OStructureComponent a(OComponentVillageStartPiece var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
        return d(var0, var1, var2, var3, var4, var5, var6, var7);
    }

    // $FF: synthetic method
    static OStructureComponent b(OComponentVillageStartPiece var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
        return e(var0, var1, var2, var3, var4, var5, var6, var7);
    }
}
