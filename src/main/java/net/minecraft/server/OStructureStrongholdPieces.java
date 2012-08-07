package net.minecraft.server;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.server.OComponentStronghold;
import net.minecraft.server.OComponentStrongholdChestCorridor;
import net.minecraft.server.OComponentStrongholdCorridor;
import net.minecraft.server.OComponentStrongholdCrossing;
import net.minecraft.server.OComponentStrongholdLeftTurn;
import net.minecraft.server.OComponentStrongholdLibrary;
import net.minecraft.server.OComponentStrongholdPortalRoom;
import net.minecraft.server.OComponentStrongholdPrison;
import net.minecraft.server.OComponentStrongholdRightTurn;
import net.minecraft.server.OComponentStrongholdRoomCrossing;
import net.minecraft.server.OComponentStrongholdStairs;
import net.minecraft.server.OComponentStrongholdStairs2;
import net.minecraft.server.OComponentStrongholdStairsStraight;
import net.minecraft.server.OComponentStrongholdStraight;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OStructureStrongholdPieceWeight;
import net.minecraft.server.OStructureStrongholdPieceWeight2;
import net.minecraft.server.OStructureStrongholdPieceWeight3;
import net.minecraft.server.OStructureStrongholdStones;


public class OStructureStrongholdPieces {

    private static final OStructureStrongholdPieceWeight[] b = new OStructureStrongholdPieceWeight[] { new OStructureStrongholdPieceWeight(OComponentStrongholdStraight.class, 40, 0), new OStructureStrongholdPieceWeight(OComponentStrongholdPrison.class, 5, 5), new OStructureStrongholdPieceWeight(OComponentStrongholdLeftTurn.class, 20, 0), new OStructureStrongholdPieceWeight(OComponentStrongholdRightTurn.class, 20, 0), new OStructureStrongholdPieceWeight(OComponentStrongholdRoomCrossing.class, 10, 6), new OStructureStrongholdPieceWeight(OComponentStrongholdStairsStraight.class, 5, 5), new OStructureStrongholdPieceWeight(OComponentStrongholdStairs.class, 5, 5), new OStructureStrongholdPieceWeight(OComponentStrongholdCrossing.class, 5, 4), new OStructureStrongholdPieceWeight(OComponentStrongholdChestCorridor.class, 5, 4), new OStructureStrongholdPieceWeight2(OComponentStrongholdLibrary.class, 10, 2), new OStructureStrongholdPieceWeight3(OComponentStrongholdPortalRoom.class, 20, 1) };
    private static List c;
    private static Class d;
    static int a = 0;
    private static final OStructureStrongholdStones e = new OStructureStrongholdStones((OStructureStrongholdPieceWeight2) null);

    public OStructureStrongholdPieces() {
        super();
    }

    public static void a() {
        c = new ArrayList();
        OStructureStrongholdPieceWeight[] var0 = b;
        int var1 = var0.length;

        for (int var2 = 0; var2 < var1; ++var2) {
            OStructureStrongholdPieceWeight var3 = var0[var2];

            var3.c = 0;
            c.add(var3);
        }

        d = null;
    }

    private static boolean c() {
        boolean var0 = false;

        a = 0;

        OStructureStrongholdPieceWeight var2;

        for (Iterator var1 = c.iterator(); var1.hasNext(); a += var2.b) {
            var2 = (OStructureStrongholdPieceWeight) var1.next();
            if (var2.d > 0 && var2.c < var2.d) {
                var0 = true;
            }
        }

        return var0;
    }

    private static OComponentStronghold a(Class var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
        Object var8 = null;

        if (var0 == OComponentStrongholdStraight.class) {
            var8 = OComponentStrongholdStraight.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var0 == OComponentStrongholdPrison.class) {
            var8 = OComponentStrongholdPrison.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var0 == OComponentStrongholdLeftTurn.class) {
            var8 = OComponentStrongholdLeftTurn.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var0 == OComponentStrongholdRightTurn.class) {
            var8 = OComponentStrongholdLeftTurn.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var0 == OComponentStrongholdRoomCrossing.class) {
            var8 = OComponentStrongholdRoomCrossing.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var0 == OComponentStrongholdStairsStraight.class) {
            var8 = OComponentStrongholdStairsStraight.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var0 == OComponentStrongholdStairs.class) {
            var8 = OComponentStrongholdStairs.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var0 == OComponentStrongholdCrossing.class) {
            var8 = OComponentStrongholdCrossing.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var0 == OComponentStrongholdChestCorridor.class) {
            var8 = OComponentStrongholdChestCorridor.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var0 == OComponentStrongholdLibrary.class) {
            var8 = OComponentStrongholdLibrary.a(var1, var2, var3, var4, var5, var6, var7);
        } else if (var0 == OComponentStrongholdPortalRoom.class) {
            var8 = OComponentStrongholdPortalRoom.a(var1, var2, var3, var4, var5, var6, var7);
        }

        return (OComponentStronghold) var8;
    }

    private static OComponentStronghold b(OComponentStrongholdStairs2 var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
        if (!c()) {
            return null;
        } else {
            if (d != null) {
                OComponentStronghold var8 = a(d, var1, var2, var3, var4, var5, var6, var7);

                d = null;
                if (var8 != null) {
                    return var8;
                }
            }

            int var13 = 0;

            while (var13 < 5) {
                ++var13;
                int var9 = var2.nextInt(a);
                Iterator var10 = c.iterator();

                while (var10.hasNext()) {
                    OStructureStrongholdPieceWeight var11 = (OStructureStrongholdPieceWeight) var10.next();

                    var9 -= var11.b;
                    if (var9 < 0) {
                        if (!var11.a(var7) || var11 == var0.a) {
                            break;
                        }

                        OComponentStronghold var12 = a(var11.a, var1, var2, var3, var4, var5, var6, var7);

                        if (var12 != null) {
                            ++var11.c;
                            var0.a = var11;
                            if (!var11.a()) {
                                c.remove(var11);
                            }

                            return var12;
                        }
                    }
                }
            }

            OStructureBoundingBox var14 = OComponentStrongholdCorridor.a(var1, var2, var3, var4, var5, var6);

            if (var14 != null && var14.b > 1) {
                return new OComponentStrongholdCorridor(var7, var2, var14, var6);
            } else {
                return null;
            }
        }
    }

    private static OStructureComponent c(OComponentStrongholdStairs2 var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
        if (var7 > 50) {
            return null;
        } else if (Math.abs(var3 - var0.b().a) <= 112 && Math.abs(var5 - var0.b().c) <= 112) {
            OComponentStronghold var8 = b(var0, var1, var2, var3, var4, var5, var6, var7 + 1);

            if (var8 != null) {
                var1.add(var8);
                var0.c.add(var8);
            }

            return var8;
        } else {
            return null;
        }
    }

    // $FF: synthetic method
    static OStructureComponent a(OComponentStrongholdStairs2 var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
        return c(var0, var1, var2, var3, var4, var5, var6, var7);
    }

    // $FF: synthetic method
    static Class a(Class var0) {
        d = var0;
        return var0;
    }

    // $FF: synthetic method
    static OStructureStrongholdStones b() {
        return e;
    }

}
