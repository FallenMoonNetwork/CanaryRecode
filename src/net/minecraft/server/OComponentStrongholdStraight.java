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

public class OComponentStrongholdStraight extends OComponentStronghold {

    private final OEnumDoor a;
    private final boolean b;
    private final boolean c;

    public OComponentStrongholdStraight(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.a = this.a(var2);
        this.g = var3;
        this.b = var2.nextInt(2) == 0;
        this.c = var2.nextInt(2) == 0;
    }

    public void a(OStructureComponent var1, List var2, Random var3) {
        this.a((OComponentStrongholdStairs2) var1, var2, var3, 1, 1);
        if (this.b) {
            this.b((OComponentStrongholdStairs2) var1, var2, var3, 1, 2);
        }

        if (this.c) {
            this.c((OComponentStrongholdStairs2) var1, var2, var3, 1, 2);
        }

    }

    public static OComponentStrongholdStraight a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, -1, -1, 0, 5, 5, 7, var5);
        return a(var7) && OStructureComponent.a(var0, var7) == null ? new OComponentStrongholdStraight(var6, var1, var7, var5) : null;
    }

    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.a(var1, var3)) {
            return false;
        } else {
            this.a(var1, var3, 0, 0, 0, 4, 4, 6, true, var2, OStructureStrongholdPieces.b());
            this.a(var1, var2, var3, this.a, 1, 1, 0);
            this.a(var1, var2, var3, OEnumDoor.a, 1, 1, 6);
            this.a(var1, var3, var2, 0.1F, 1, 2, 1, OBlock.aq.bO, 0);
            this.a(var1, var3, var2, 0.1F, 3, 2, 1, OBlock.aq.bO, 0);
            this.a(var1, var3, var2, 0.1F, 1, 2, 5, OBlock.aq.bO, 0);
            this.a(var1, var3, var2, 0.1F, 3, 2, 5, OBlock.aq.bO, 0);
            if (this.b) {
                this.a(var1, var3, 0, 1, 2, 0, 3, 4, 0, 0, false);
            }

            if (this.c) {
                this.a(var1, var3, 4, 1, 2, 4, 3, 4, 0, 0, false);
            }

            return true;
        }
    }
}
