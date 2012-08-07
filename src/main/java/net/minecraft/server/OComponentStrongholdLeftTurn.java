package net.minecraft.server;


import java.util.List;
import java.util.Random;
import net.minecraft.server.OComponentStronghold;
import net.minecraft.server.OComponentStrongholdStairs2;
import net.minecraft.server.OEnumDoor;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OStructureStrongholdPieces;
import net.minecraft.server.OWorld;


public class OComponentStrongholdLeftTurn extends OComponentStronghold {

    protected final OEnumDoor a;

    public OComponentStrongholdLeftTurn(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.a = this.a(var2);
        this.g = var3;
    }

    @Override
    public void a(OStructureComponent var1, List var2, Random var3) {
        if (this.h != 2 && this.h != 3) {
            this.c((OComponentStrongholdStairs2) var1, var2, var3, 1, 1);
        } else {
            this.b((OComponentStrongholdStairs2) var1, var2, var3, 1, 1);
        }

    }

    public static OComponentStrongholdLeftTurn a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, -1, -1, 0, 5, 5, 5, var5);

        return a(var7) && OStructureComponent.a(var0, var7) == null ? new OComponentStrongholdLeftTurn(var6, var1, var7, var5) : null;
    }

    @Override
    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.a(var1, var3)) {
            return false;
        } else {
            this.a(var1, var3, 0, 0, 0, 4, 4, 4, true, var2, OStructureStrongholdPieces.b());
            this.a(var1, var2, var3, this.a, 1, 1, 0);
            if (this.h != 2 && this.h != 3) {
                this.a(var1, var3, 4, 1, 1, 4, 3, 3, 0, 0, false);
            } else {
                this.a(var1, var3, 0, 1, 1, 0, 3, 3, 0, 0, false);
            }

            return true;
        }
    }
}
