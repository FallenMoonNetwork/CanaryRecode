package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OComponentVillage;
import net.minecraft.server.OItem;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OStructurePieceTreasure;
import net.minecraft.server.OWorld;

public class OComponentVillageHouse2 extends OComponentVillage {

    private static final OStructurePieceTreasure[] a = new OStructurePieceTreasure[] { new OStructurePieceTreasure(OItem.m.bP, 0, 1, 3, 3), new OStructurePieceTreasure(OItem.n.bP, 0, 1, 5, 10), new OStructurePieceTreasure(OItem.o.bP, 0, 1, 3, 5), new OStructurePieceTreasure(OItem.T.bP, 0, 1, 3, 15), new OStructurePieceTreasure(OItem.i.bP, 0, 1, 3, 15), new OStructurePieceTreasure(OItem.f.bP, 0, 1, 1, 5), new OStructurePieceTreasure(OItem.p.bP, 0, 1, 1, 5), new OStructurePieceTreasure(OItem.ad.bP, 0, 1, 1, 5), new OStructurePieceTreasure(OItem.ac.bP, 0, 1, 1, 5), new OStructurePieceTreasure(OItem.ae.bP, 0, 1, 1, 5), new OStructurePieceTreasure(OItem.af.bP, 0, 1, 1, 5), new OStructurePieceTreasure(OBlock.ap.bO, 0, 3, 7, 5), new OStructurePieceTreasure(OBlock.y.bO, 0, 3, 7, 5) };
    private int b = -1;
    private boolean c;

    public OComponentVillageHouse2(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.g = var3;
    }

    @Override
    public void a(OStructureComponent var1, List var2, Random var3) {
    }

    public static OComponentVillageHouse2 a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, 0, 0, 0, 10, 6, 7, var5);
        return a(var7) && OStructureComponent.a(var0, var7) == null ? new OComponentVillageHouse2(var6, var1, var7, var5) : null;
    }

    @Override
    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.b < 0) {
            this.b = this.b(var1, var3);
            if (this.b < 0) {
                return true;
            }

            this.g.a(0, this.b - this.g.e + 6 - 1, 0);
        }

        this.a(var1, var3, 0, 1, 0, 9, 4, 6, 0, 0, false);
        this.a(var1, var3, 0, 0, 0, 9, 0, 6, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 0, 4, 0, 9, 4, 6, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 0, 5, 0, 9, 5, 6, OBlock.ak.bO, OBlock.ak.bO, false);
        this.a(var1, var3, 1, 5, 1, 8, 5, 5, 0, 0, false);
        this.a(var1, var3, 1, 1, 0, 2, 3, 0, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 0, 1, 0, 0, 4, 0, OBlock.J.bO, OBlock.J.bO, false);
        this.a(var1, var3, 3, 1, 0, 3, 4, 0, OBlock.J.bO, OBlock.J.bO, false);
        this.a(var1, var3, 0, 1, 6, 0, 4, 6, OBlock.J.bO, OBlock.J.bO, false);
        this.a(var1, OBlock.x.bO, 0, 3, 3, 1, var3);
        this.a(var1, var3, 3, 1, 2, 3, 3, 2, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 4, 1, 3, 5, 3, 3, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 0, 1, 1, 0, 3, 5, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 1, 1, 6, 5, 3, 6, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 5, 1, 0, 5, 3, 0, OBlock.aZ.bO, OBlock.aZ.bO, false);
        this.a(var1, var3, 9, 1, 0, 9, 3, 0, OBlock.aZ.bO, OBlock.aZ.bO, false);
        this.a(var1, var3, 6, 1, 4, 9, 4, 6, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, OBlock.C.bO, 0, 7, 1, 5, var3);
        this.a(var1, OBlock.C.bO, 0, 8, 1, 5, var3);
        this.a(var1, OBlock.bp.bO, 0, 9, 2, 5, var3);
        this.a(var1, OBlock.bp.bO, 0, 9, 2, 4, var3);
        this.a(var1, var3, 7, 2, 4, 8, 2, 5, 0, 0, false);
        this.a(var1, OBlock.w.bO, 0, 6, 1, 3, var3);
        this.a(var1, OBlock.aB.bO, 0, 6, 2, 3, var3);
        this.a(var1, OBlock.aB.bO, 0, 6, 3, 3, var3);
        this.a(var1, OBlock.aj.bO, 0, 8, 1, 1, var3);
        this.a(var1, OBlock.bq.bO, 0, 0, 2, 2, var3);
        this.a(var1, OBlock.bq.bO, 0, 0, 2, 4, var3);
        this.a(var1, OBlock.bq.bO, 0, 2, 2, 6, var3);
        this.a(var1, OBlock.bq.bO, 0, 4, 2, 6, var3);
        this.a(var1, OBlock.aZ.bO, 0, 2, 1, 4, var3);
        this.a(var1, OBlock.aM.bO, 0, 2, 2, 4, var3);
        this.a(var1, OBlock.x.bO, 0, 1, 1, 5, var3);
        this.a(var1, OBlock.at.bO, this.c(OBlock.at.bO, 3), 2, 1, 5, var3);
        this.a(var1, OBlock.at.bO, this.c(OBlock.at.bO, 1), 1, 1, 4, var3);
        int var4;
        int var5;
        if (!this.c) {
            var4 = this.b(1);
            var5 = this.a(5, 5);
            int var6 = this.b(5, 5);
            if (var3.b(var5, var4, var6)) {
                this.c = true;
                this.a(var1, var3, var2, 5, 1, 5, a, 3 + var2.nextInt(6));
            }
        }

        for (var4 = 6; var4 <= 8; ++var4) {
            if (this.a(var1, var4, 0, -1, var3) == 0 && this.a(var1, var4, -1, -1, var3) != 0) {
                this.a(var1, OBlock.aH.bO, this.c(OBlock.aH.bO, 3), var4, 0, -1, var3);
            }
        }

        for (var4 = 0; var4 < 7; ++var4) {
            for (var5 = 0; var5 < 10; ++var5) {
                this.b(var1, var5, 6, var4, var3);
                this.b(var1, OBlock.w.bO, 0, var5, -1, var4, var3);
            }
        }

        this.a(var1, var3, 7, 1, 1, 1);
        return true;
    }

    @Override
    protected int a(int var1) {
        return 3;
    }

}
