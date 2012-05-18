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

public class OComponentStrongholdRoomCrossing extends OComponentStronghold {

    private static final OStructurePieceTreasure[] c = new OStructurePieceTreasure[] { new OStructurePieceTreasure(OItem.n.bP, 0, 1, 5, 10), new OStructurePieceTreasure(OItem.o.bP, 0, 1, 3, 5), new OStructurePieceTreasure(OItem.aB.bP, 0, 4, 9, 5), new OStructurePieceTreasure(OItem.l.bP, 0, 3, 8, 10), new OStructurePieceTreasure(OItem.T.bP, 0, 1, 3, 15), new OStructurePieceTreasure(OItem.i.bP, 0, 1, 3, 15), new OStructurePieceTreasure(OItem.f.bP, 0, 1, 1, 1) };
    protected final OEnumDoor a;
    protected final int b;

    public OComponentStrongholdRoomCrossing(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.a = this.a(var2);
        this.g = var3;
        this.b = var2.nextInt(5);
    }

    public void a(OStructureComponent var1, List var2, Random var3) {
        this.a((OComponentStrongholdStairs2) var1, var2, var3, 4, 1);
        this.b((OComponentStrongholdStairs2) var1, var2, var3, 1, 4);
        this.c((OComponentStrongholdStairs2) var1, var2, var3, 1, 4);
    }

    public static OComponentStrongholdRoomCrossing a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, -4, -1, 0, 11, 7, 11, var5);
        return a(var7) && OStructureComponent.a(var0, var7) == null ? new OComponentStrongholdRoomCrossing(var6, var1, var7, var5) : null;
    }

    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.a(var1, var3)) {
            return false;
        } else {
            this.a(var1, var3, 0, 0, 0, 10, 6, 10, true, var2, OStructureStrongholdPieces.b());
            this.a(var1, var2, var3, this.a, 4, 1, 0);
            this.a(var1, var3, 4, 1, 10, 6, 3, 10, 0, 0, false);
            this.a(var1, var3, 0, 1, 4, 0, 3, 6, 0, 0, false);
            this.a(var1, var3, 10, 1, 4, 10, 3, 6, 0, 0, false);
            int var4;
            switch (this.b) {
            case 0:
                this.a(var1, OBlock.bm.bO, 0, 5, 1, 5, var3);
                this.a(var1, OBlock.bm.bO, 0, 5, 2, 5, var3);
                this.a(var1, OBlock.bm.bO, 0, 5, 3, 5, var3);
                this.a(var1, OBlock.aq.bO, 0, 4, 3, 5, var3);
                this.a(var1, OBlock.aq.bO, 0, 6, 3, 5, var3);
                this.a(var1, OBlock.aq.bO, 0, 5, 3, 4, var3);
                this.a(var1, OBlock.aq.bO, 0, 5, 3, 6, var3);
                this.a(var1, OBlock.ak.bO, 0, 4, 1, 4, var3);
                this.a(var1, OBlock.ak.bO, 0, 4, 1, 5, var3);
                this.a(var1, OBlock.ak.bO, 0, 4, 1, 6, var3);
                this.a(var1, OBlock.ak.bO, 0, 6, 1, 4, var3);
                this.a(var1, OBlock.ak.bO, 0, 6, 1, 5, var3);
                this.a(var1, OBlock.ak.bO, 0, 6, 1, 6, var3);
                this.a(var1, OBlock.ak.bO, 0, 5, 1, 4, var3);
                this.a(var1, OBlock.ak.bO, 0, 5, 1, 6, var3);
                break;
            case 1:
                for (var4 = 0; var4 < 5; ++var4) {
                    this.a(var1, OBlock.bm.bO, 0, 3, 1, 3 + var4, var3);
                    this.a(var1, OBlock.bm.bO, 0, 7, 1, 3 + var4, var3);
                    this.a(var1, OBlock.bm.bO, 0, 3 + var4, 1, 3, var3);
                    this.a(var1, OBlock.bm.bO, 0, 3 + var4, 1, 7, var3);
                }

                this.a(var1, OBlock.bm.bO, 0, 5, 1, 5, var3);
                this.a(var1, OBlock.bm.bO, 0, 5, 2, 5, var3);
                this.a(var1, OBlock.bm.bO, 0, 5, 3, 5, var3);
                this.a(var1, OBlock.A.bO, 0, 5, 4, 5, var3);
                break;
            case 2:
                for (var4 = 1; var4 <= 9; ++var4) {
                    this.a(var1, OBlock.w.bO, 0, 1, 3, var4, var3);
                    this.a(var1, OBlock.w.bO, 0, 9, 3, var4, var3);
                }

                for (var4 = 1; var4 <= 9; ++var4) {
                    this.a(var1, OBlock.w.bO, 0, var4, 3, 1, var3);
                    this.a(var1, OBlock.w.bO, 0, var4, 3, 9, var3);
                }

                this.a(var1, OBlock.w.bO, 0, 5, 1, 4, var3);
                this.a(var1, OBlock.w.bO, 0, 5, 1, 6, var3);
                this.a(var1, OBlock.w.bO, 0, 5, 3, 4, var3);
                this.a(var1, OBlock.w.bO, 0, 5, 3, 6, var3);
                this.a(var1, OBlock.w.bO, 0, 4, 1, 5, var3);
                this.a(var1, OBlock.w.bO, 0, 6, 1, 5, var3);
                this.a(var1, OBlock.w.bO, 0, 4, 3, 5, var3);
                this.a(var1, OBlock.w.bO, 0, 6, 3, 5, var3);

                for (var4 = 1; var4 <= 3; ++var4) {
                    this.a(var1, OBlock.w.bO, 0, 4, var4, 4, var3);
                    this.a(var1, OBlock.w.bO, 0, 6, var4, 4, var3);
                    this.a(var1, OBlock.w.bO, 0, 4, var4, 6, var3);
                    this.a(var1, OBlock.w.bO, 0, 6, var4, 6, var3);
                }

                this.a(var1, OBlock.aq.bO, 0, 5, 3, 5, var3);

                for (var4 = 2; var4 <= 8; ++var4) {
                    this.a(var1, OBlock.x.bO, 0, 2, 3, var4, var3);
                    this.a(var1, OBlock.x.bO, 0, 3, 3, var4, var3);
                    if (var4 <= 3 || var4 >= 7) {
                        this.a(var1, OBlock.x.bO, 0, 4, 3, var4, var3);
                        this.a(var1, OBlock.x.bO, 0, 5, 3, var4, var3);
                        this.a(var1, OBlock.x.bO, 0, 6, 3, var4, var3);
                    }

                    this.a(var1, OBlock.x.bO, 0, 7, 3, var4, var3);
                    this.a(var1, OBlock.x.bO, 0, 8, 3, var4, var3);
                }

                this.a(var1, OBlock.aF.bO, this.c(OBlock.aF.bO, 4), 9, 1, 3, var3);
                this.a(var1, OBlock.aF.bO, this.c(OBlock.aF.bO, 4), 9, 2, 3, var3);
                this.a(var1, OBlock.aF.bO, this.c(OBlock.aF.bO, 4), 9, 3, 3, var3);
                this.a(var1, var3, var2, 3, 4, 8, c, 1 + var2.nextInt(4));
            }

            return true;
        }
    }

}
