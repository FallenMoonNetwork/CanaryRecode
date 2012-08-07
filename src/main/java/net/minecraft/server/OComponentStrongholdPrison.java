package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OComponentStronghold;
import net.minecraft.server.OComponentStrongholdStairs2;
import net.minecraft.server.OEnumDoor;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OStructureStrongholdPieces;
import net.minecraft.server.OWorld;

public class OComponentStrongholdPrison extends OComponentStronghold {

    protected final OEnumDoor a;

    public OComponentStrongholdPrison(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.a = this.a(var2);
        this.g = var3;
    }

    @Override
    public void a(OStructureComponent var1, List var2, Random var3) {
        this.a((OComponentStrongholdStairs2) var1, var2, var3, 1, 1);
    }

    public static OComponentStrongholdPrison a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, -1, -1, 0, 9, 5, 11, var5);
        return a(var7) && OStructureComponent.a(var0, var7) == null ? new OComponentStrongholdPrison(var6, var1, var7, var5) : null;
    }

    @Override
    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.a(var1, var3)) {
            return false;
        } else {
            this.a(var1, var3, 0, 0, 0, 8, 4, 10, true, var2, OStructureStrongholdPieces.b());
            this.a(var1, var2, var3, this.a, 1, 1, 0);
            this.a(var1, var3, 1, 1, 10, 3, 3, 10, 0, 0, false);
            this.a(var1, var3, 4, 1, 1, 4, 3, 1, false, var2, OStructureStrongholdPieces.b());
            this.a(var1, var3, 4, 1, 3, 4, 3, 3, false, var2, OStructureStrongholdPieces.b());
            this.a(var1, var3, 4, 1, 7, 4, 3, 7, false, var2, OStructureStrongholdPieces.b());
            this.a(var1, var3, 4, 1, 9, 4, 3, 9, false, var2, OStructureStrongholdPieces.b());
            this.a(var1, var3, 4, 1, 4, 4, 3, 6, OBlock.bp.bO, OBlock.bp.bO, false);
            this.a(var1, var3, 5, 1, 5, 7, 3, 5, OBlock.bp.bO, OBlock.bp.bO, false);
            this.a(var1, OBlock.bp.bO, 0, 4, 3, 2, var3);
            this.a(var1, OBlock.bp.bO, 0, 4, 3, 8, var3);
            this.a(var1, OBlock.aL.bO, this.c(OBlock.aL.bO, 3), 4, 1, 2, var3);
            this.a(var1, OBlock.aL.bO, this.c(OBlock.aL.bO, 3) + 8, 4, 2, 2, var3);
            this.a(var1, OBlock.aL.bO, this.c(OBlock.aL.bO, 3), 4, 1, 8, var3);
            this.a(var1, OBlock.aL.bO, this.c(OBlock.aL.bO, 3) + 8, 4, 2, 8, var3);
            return true;
        }
    }
}
