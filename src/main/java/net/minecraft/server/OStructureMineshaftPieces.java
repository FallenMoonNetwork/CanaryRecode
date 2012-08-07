package net.minecraft.server;


import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OComponentMineshaftCorridor;
import net.minecraft.server.OComponentMineshaftCross;
import net.minecraft.server.OComponentMineshaftStairs;
import net.minecraft.server.OItem;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OStructurePieceTreasure;


public class OStructureMineshaftPieces {

    private static final OStructurePieceTreasure[] a = new OStructurePieceTreasure[] { new OStructurePieceTreasure(OItem.n.bP, 0, 1, 5, 10), new OStructurePieceTreasure(OItem.o.bP, 0, 1, 3, 5), new OStructurePieceTreasure(OItem.aB.bP, 0, 4, 9, 5), new OStructurePieceTreasure(OItem.aV.bP, 4, 4, 9, 5), new OStructurePieceTreasure(OItem.m.bP, 0, 1, 2, 3), new OStructurePieceTreasure(OItem.l.bP, 0, 3, 8, 10), new OStructurePieceTreasure(OItem.T.bP, 0, 1, 3, 15), new OStructurePieceTreasure(OItem.f.bP, 0, 1, 1, 1), new OStructurePieceTreasure(OBlock.aG.bO, 0, 4, 8, 1), new OStructurePieceTreasure(OItem.bg.bP, 0, 2, 4, 10), new OStructurePieceTreasure(OItem.bf.bP, 0, 2, 4, 10) };

    public OStructureMineshaftPieces() {
        super();
    }

    private static OStructureComponent a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        int var7 = var1.nextInt(100);
        OStructureBoundingBox var8;

        if (var7 >= 80) {
            var8 = OComponentMineshaftCross.a(var0, var1, var2, var3, var4, var5);
            if (var8 != null) {
                return new OComponentMineshaftCross(var6, var1, var8, var5);
            }
        } else if (var7 >= 70) {
            var8 = OComponentMineshaftStairs.a(var0, var1, var2, var3, var4, var5);
            if (var8 != null) {
                return new OComponentMineshaftStairs(var6, var1, var8, var5);
            }
        } else {
            var8 = OComponentMineshaftCorridor.a(var0, var1, var2, var3, var4, var5);
            if (var8 != null) {
                return new OComponentMineshaftCorridor(var6, var1, var8, var5);
            }
        }

        return null;
    }

    private static OStructureComponent b(OStructureComponent var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
        if (var7 > 8) {
            return null;
        } else if (Math.abs(var3 - var0.b().a) <= 80 && Math.abs(var5 - var0.b().c) <= 80) {
            OStructureComponent var8 = a(var1, var2, var3, var4, var5, var6, var7 + 1);

            if (var8 != null) {
                var1.add(var8);
                var8.a(var0, var1, var2);
            }

            return var8;
        } else {
            return null;
        }
    }

    // $FF: synthetic method
    static OStructureComponent a(OStructureComponent var0, List var1, Random var2, int var3, int var4, int var5, int var6, int var7) {
        return b(var0, var1, var2, var3, var4, var5, var6, var7);
    }

    // $FF: synthetic method
    static OStructurePieceTreasure[] a() {
        return a;
    }

}
