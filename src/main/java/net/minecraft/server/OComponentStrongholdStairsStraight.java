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


public class OComponentStrongholdStairsStraight extends OComponentStronghold {

    private final OEnumDoor a;

    public OComponentStrongholdStairsStraight(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.a = this.a(var2);
        this.g = var3;
    }

    @Override
    public void a(OStructureComponent var1, List var2, Random var3) {
        this.a((OComponentStrongholdStairs2) var1, var2, var3, 1, 1);
    }

    public static OComponentStrongholdStairsStraight a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, -1, -7, 0, 5, 11, 8, var5);

        return a(var7) && OStructureComponent.a(var0, var7) == null ? new OComponentStrongholdStairsStraight(var6, var1, var7, var5) : null;
    }

    @Override
    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.a(var1, var3)) {
            return false;
        } else {
            this.a(var1, var3, 0, 0, 0, 4, 10, 7, true, var2, OStructureStrongholdPieces.b());
            this.a(var1, var2, var3, this.a, 1, 7, 0);
            this.a(var1, var2, var3, OEnumDoor.a, 1, 1, 7);
            int var4 = this.c(OBlock.aH.bO, 2);

            for (int var5 = 0; var5 < 6; ++var5) {
                this.a(var1, OBlock.aH.bO, var4, 1, 6 - var5, 1 + var5, var3);
                this.a(var1, OBlock.aH.bO, var4, 2, 6 - var5, 1 + var5, var3);
                this.a(var1, OBlock.aH.bO, var4, 3, 6 - var5, 1 + var5, var3);
                if (var5 < 5) {
                    this.a(var1, OBlock.bm.bO, 0, 1, 5 - var5, 1 + var5, var3);
                    this.a(var1, OBlock.bm.bO, 0, 2, 5 - var5, 1 + var5, var3);
                    this.a(var1, OBlock.bm.bO, 0, 3, 5 - var5, 1 + var5, var3);
                }
            }

            return true;
        }
    }
}
