package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OComponentStrongholdStairs2;
import net.minecraft.server.OEnumDoor;
import net.minecraft.server.OEnumDoorHelper;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OStructureStrongholdPieces;
import net.minecraft.server.OWorld;

abstract class OComponentStronghold extends OStructureComponent {

    protected OComponentStronghold(int var1) {
        super(var1);
    }

    protected void a(OWorld var1, Random var2, OStructureBoundingBox var3, OEnumDoor var4, int var5, int var6, int var7) {
        switch (OEnumDoorHelper.a[var4.ordinal()]) {
        case 1:
        default:
            this.a(var1, var3, var5, var6, var7, var5 + 3 - 1, var6 + 3 - 1, var7, 0, 0, false);
            break;
        case 2:
            this.a(var1, OBlock.bm.bO, 0, var5, var6, var7, var3);
            this.a(var1, OBlock.bm.bO, 0, var5, var6 + 1, var7, var3);
            this.a(var1, OBlock.bm.bO, 0, var5, var6 + 2, var7, var3);
            this.a(var1, OBlock.bm.bO, 0, var5 + 1, var6 + 2, var7, var3);
            this.a(var1, OBlock.bm.bO, 0, var5 + 2, var6 + 2, var7, var3);
            this.a(var1, OBlock.bm.bO, 0, var5 + 2, var6 + 1, var7, var3);
            this.a(var1, OBlock.bm.bO, 0, var5 + 2, var6, var7, var3);
            this.a(var1, OBlock.aE.bO, 0, var5 + 1, var6, var7, var3);
            this.a(var1, OBlock.aE.bO, 8, var5 + 1, var6 + 1, var7, var3);
            break;
        case 3:
            this.a(var1, 0, 0, var5 + 1, var6, var7, var3);
            this.a(var1, 0, 0, var5 + 1, var6 + 1, var7, var3);
            this.a(var1, OBlock.bp.bO, 0, var5, var6, var7, var3);
            this.a(var1, OBlock.bp.bO, 0, var5, var6 + 1, var7, var3);
            this.a(var1, OBlock.bp.bO, 0, var5, var6 + 2, var7, var3);
            this.a(var1, OBlock.bp.bO, 0, var5 + 1, var6 + 2, var7, var3);
            this.a(var1, OBlock.bp.bO, 0, var5 + 2, var6 + 2, var7, var3);
            this.a(var1, OBlock.bp.bO, 0, var5 + 2, var6 + 1, var7, var3);
            this.a(var1, OBlock.bp.bO, 0, var5 + 2, var6, var7, var3);
            break;
        case 4:
            this.a(var1, OBlock.bm.bO, 0, var5, var6, var7, var3);
            this.a(var1, OBlock.bm.bO, 0, var5, var6 + 1, var7, var3);
            this.a(var1, OBlock.bm.bO, 0, var5, var6 + 2, var7, var3);
            this.a(var1, OBlock.bm.bO, 0, var5 + 1, var6 + 2, var7, var3);
            this.a(var1, OBlock.bm.bO, 0, var5 + 2, var6 + 2, var7, var3);
            this.a(var1, OBlock.bm.bO, 0, var5 + 2, var6 + 1, var7, var3);
            this.a(var1, OBlock.bm.bO, 0, var5 + 2, var6, var7, var3);
            this.a(var1, OBlock.aL.bO, 0, var5 + 1, var6, var7, var3);
            this.a(var1, OBlock.aL.bO, 8, var5 + 1, var6 + 1, var7, var3);
            this.a(var1, OBlock.aR.bO, this.c(OBlock.aR.bO, 4), var5 + 2, var6 + 1, var7 + 1, var3);
            this.a(var1, OBlock.aR.bO, this.c(OBlock.aR.bO, 3), var5 + 2, var6 + 1, var7 - 1, var3);
        }

    }

    protected OEnumDoor a(Random var1) {
        int var2 = var1.nextInt(5);
        switch (var2) {
        case 0:
        case 1:
        default:
            return OEnumDoor.a;
        case 2:
            return OEnumDoor.b;
        case 3:
            return OEnumDoor.c;
        case 4:
            return OEnumDoor.d;
        }
    }

    protected OStructureComponent a(OComponentStrongholdStairs2 var1, List var2, Random var3, int var4, int var5) {
        switch (this.h) {
        case 0:
            return OStructureStrongholdPieces.a(var1, var2, var3, this.g.a + var4, this.g.b + var5, this.g.f + 1, this.h, this.c());
        case 1:
            return OStructureStrongholdPieces.a(var1, var2, var3, this.g.a - 1, this.g.b + var5, this.g.c + var4, this.h, this.c());
        case 2:
            return OStructureStrongholdPieces.a(var1, var2, var3, this.g.a + var4, this.g.b + var5, this.g.c - 1, this.h, this.c());
        case 3:
            return OStructureStrongholdPieces.a(var1, var2, var3, this.g.d + 1, this.g.b + var5, this.g.c + var4, this.h, this.c());
        default:
            return null;
        }
    }

    protected OStructureComponent b(OComponentStrongholdStairs2 var1, List var2, Random var3, int var4, int var5) {
        switch (this.h) {
        case 0:
            return OStructureStrongholdPieces.a(var1, var2, var3, this.g.a - 1, this.g.b + var4, this.g.c + var5, 1, this.c());
        case 1:
            return OStructureStrongholdPieces.a(var1, var2, var3, this.g.a + var5, this.g.b + var4, this.g.c - 1, 2, this.c());
        case 2:
            return OStructureStrongholdPieces.a(var1, var2, var3, this.g.a - 1, this.g.b + var4, this.g.c + var5, 1, this.c());
        case 3:
            return OStructureStrongholdPieces.a(var1, var2, var3, this.g.a + var5, this.g.b + var4, this.g.c - 1, 2, this.c());
        default:
            return null;
        }
    }

    protected OStructureComponent c(OComponentStrongholdStairs2 var1, List var2, Random var3, int var4, int var5) {
        switch (this.h) {
        case 0:
            return OStructureStrongholdPieces.a(var1, var2, var3, this.g.d + 1, this.g.b + var4, this.g.c + var5, 3, this.c());
        case 1:
            return OStructureStrongholdPieces.a(var1, var2, var3, this.g.a + var5, this.g.b + var4, this.g.f + 1, 0, this.c());
        case 2:
            return OStructureStrongholdPieces.a(var1, var2, var3, this.g.d + 1, this.g.b + var4, this.g.c + var5, 3, this.c());
        case 3:
            return OStructureStrongholdPieces.a(var1, var2, var3, this.g.a + var5, this.g.b + var4, this.g.f + 1, 0, this.c());
        default:
            return null;
        }
    }

    protected static boolean a(OStructureBoundingBox var0) {
        return var0 != null && var0.b > 10;
    }
}
