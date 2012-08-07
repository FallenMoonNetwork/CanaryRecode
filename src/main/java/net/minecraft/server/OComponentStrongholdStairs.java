package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OComponentStronghold;
import net.minecraft.server.OComponentStrongholdCrossing;
import net.minecraft.server.OComponentStrongholdStairs2;
import net.minecraft.server.OEnumDoor;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OStructureStrongholdPieces;
import net.minecraft.server.OWorld;

public class OComponentStrongholdStairs extends OComponentStronghold {

    private final boolean a;
    private final OEnumDoor b;

    public OComponentStrongholdStairs(int var1, Random var2, int var3, int var4) {
        super(var1);
        this.a = true;
        this.h = var2.nextInt(4);
        this.b = OEnumDoor.a;
        switch (this.h) {
        case 0:
        case 2:
            this.g = new OStructureBoundingBox(var3, 64, var4, var3 + 5 - 1, 74, var4 + 5 - 1);
            break;
        default:
            this.g = new OStructureBoundingBox(var3, 64, var4, var3 + 5 - 1, 74, var4 + 5 - 1);
        }

    }

    public OComponentStrongholdStairs(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.a = false;
        this.h = var4;
        this.b = this.a(var2);
        this.g = var3;
    }

    @Override
    public void a(OStructureComponent var1, List var2, Random var3) {
        if (this.a) {
            OStructureStrongholdPieces.a(OComponentStrongholdCrossing.class);
        }

        this.a((OComponentStrongholdStairs2) var1, var2, var3, 1, 1);
    }

    public static OComponentStrongholdStairs a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, -1, -7, 0, 5, 11, 5, var5);
        return a(var7) && OStructureComponent.a(var0, var7) == null ? new OComponentStrongholdStairs(var6, var1, var7, var5) : null;
    }

    @Override
    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.a(var1, var3)) {
            return false;
        } else {
            if (this.a) {
                ;
            }

            this.a(var1, var3, 0, 0, 0, 4, 10, 4, true, var2, OStructureStrongholdPieces.b());
            this.a(var1, var2, var3, this.b, 1, 7, 0);
            this.a(var1, var2, var3, OEnumDoor.a, 1, 1, 4);
            this.a(var1, OBlock.bm.bO, 0, 2, 6, 1, var3);
            this.a(var1, OBlock.bm.bO, 0, 1, 5, 1, var3);
            this.a(var1, OBlock.ak.bO, 0, 1, 6, 1, var3);
            this.a(var1, OBlock.bm.bO, 0, 1, 5, 2, var3);
            this.a(var1, OBlock.bm.bO, 0, 1, 4, 3, var3);
            this.a(var1, OBlock.ak.bO, 0, 1, 5, 3, var3);
            this.a(var1, OBlock.bm.bO, 0, 2, 4, 3, var3);
            this.a(var1, OBlock.bm.bO, 0, 3, 3, 3, var3);
            this.a(var1, OBlock.ak.bO, 0, 3, 4, 3, var3);
            this.a(var1, OBlock.bm.bO, 0, 3, 3, 2, var3);
            this.a(var1, OBlock.bm.bO, 0, 3, 2, 1, var3);
            this.a(var1, OBlock.ak.bO, 0, 3, 3, 1, var3);
            this.a(var1, OBlock.bm.bO, 0, 2, 2, 1, var3);
            this.a(var1, OBlock.bm.bO, 0, 1, 1, 1, var3);
            this.a(var1, OBlock.ak.bO, 0, 1, 2, 1, var3);
            this.a(var1, OBlock.bm.bO, 0, 1, 1, 2, var3);
            this.a(var1, OBlock.ak.bO, 0, 1, 1, 3, var3);
            return true;
        }
    }
}
