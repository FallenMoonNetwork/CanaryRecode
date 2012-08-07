package net.minecraft.server;


import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OComponentStronghold;
import net.minecraft.server.OComponentStrongholdStairs2;
import net.minecraft.server.OEnumDoor;
import net.minecraft.server.OItem;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OStructurePieceTreasure;
import net.minecraft.server.OStructureStrongholdPieces;
import net.minecraft.server.OWorld;


public class OComponentStrongholdChestCorridor extends OComponentStronghold {

    private static final OStructurePieceTreasure[] a = new OStructurePieceTreasure[] { new OStructurePieceTreasure(OItem.bm.bP, 0, 1, 1, 10), new OStructurePieceTreasure(OItem.m.bP, 0, 1, 3, 3), new OStructurePieceTreasure(OItem.n.bP, 0, 1, 5, 10), new OStructurePieceTreasure(OItem.o.bP, 0, 1, 3, 5), new OStructurePieceTreasure(OItem.aB.bP, 0, 4, 9, 5), new OStructurePieceTreasure(OItem.T.bP, 0, 1, 3, 15), new OStructurePieceTreasure(OItem.i.bP, 0, 1, 3, 15), new OStructurePieceTreasure(OItem.f.bP, 0, 1, 1, 5), new OStructurePieceTreasure(OItem.p.bP, 0, 1, 1, 5), new OStructurePieceTreasure(OItem.ad.bP, 0, 1, 1, 5), new OStructurePieceTreasure(OItem.ac.bP, 0, 1, 1, 5), new OStructurePieceTreasure(OItem.ae.bP, 0, 1, 1, 5), new OStructurePieceTreasure(OItem.af.bP, 0, 1, 1, 5), new OStructurePieceTreasure(OItem.as.bP, 0, 1, 1, 1) };
    private final OEnumDoor b;
    private boolean c;

    public OComponentStrongholdChestCorridor(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.b = this.a(var2);
        this.g = var3;
    }

    @Override
    public void a(OStructureComponent var1, List var2, Random var3) {
        this.a((OComponentStrongholdStairs2) var1, var2, var3, 1, 1);
    }

    public static OComponentStrongholdChestCorridor a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, -1, -1, 0, 5, 5, 7, var5);

        return a(var7) && OStructureComponent.a(var0, var7) == null ? new OComponentStrongholdChestCorridor(var6, var1, var7, var5) : null;
    }

    @Override
    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.a(var1, var3)) {
            return false;
        } else {
            this.a(var1, var3, 0, 0, 0, 4, 4, 6, true, var2, OStructureStrongholdPieces.b());
            this.a(var1, var2, var3, this.b, 1, 1, 0);
            this.a(var1, var2, var3, OEnumDoor.a, 1, 1, 6);
            this.a(var1, var3, 3, 1, 2, 3, 1, 4, OBlock.bm.bO, OBlock.bm.bO, false);
            this.a(var1, OBlock.ak.bO, 5, 3, 1, 1, var3);
            this.a(var1, OBlock.ak.bO, 5, 3, 1, 5, var3);
            this.a(var1, OBlock.ak.bO, 5, 3, 2, 2, var3);
            this.a(var1, OBlock.ak.bO, 5, 3, 2, 4, var3);

            int var4;

            for (var4 = 2; var4 <= 4; ++var4) {
                this.a(var1, OBlock.ak.bO, 5, 2, 1, var4, var3);
            }

            if (!this.c) {
                var4 = this.b(2);
                int var5 = this.a(3, 3);
                int var6 = this.b(3, 3);

                if (var3.b(var5, var4, var6)) {
                    this.c = true;
                    this.a(var1, var3, var2, 3, 2, 3, a, 2 + var2.nextInt(2));
                }
            }

            return true;
        }
    }

}
