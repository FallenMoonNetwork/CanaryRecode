package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OComponentStronghold;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OWorld;

public class OComponentStrongholdCorridor extends OComponentStronghold {

    private final int a;

    public OComponentStrongholdCorridor(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.g = var3;
        this.a = var4 != 2 && var4 != 0 ? var3.b() : var3.d();
    }

    @Override
    public void a(OStructureComponent var1, List var2, Random var3) {
    }

    public static OStructureBoundingBox a(List var0, Random var1, int var2, int var3, int var4, int var5) {
        OStructureBoundingBox var6 = OStructureBoundingBox.a(var2, var3, var4, -1, -1, 0, 5, 5, 4, var5);
        OStructureComponent var7 = OStructureComponent.a(var0, var6);
        if (var7 == null) {
            return null;
        } else {
            if (var7.b().b == var6.b) {
                for (int var8 = 3; var8 >= 1; --var8) {
                    var6 = OStructureBoundingBox.a(var2, var3, var4, -1, -1, 0, 5, 5, var8 - 1, var5);
                    if (!var7.b().a(var6)) {
                        return OStructureBoundingBox.a(var2, var3, var4, -1, -1, 0, 5, 5, var8, var5);
                    }
                }
            }

            return null;
        }
    }

    @Override
    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.a(var1, var3)) {
            return false;
        } else {
            for (int var4 = 0; var4 < this.a; ++var4) {
                this.a(var1, OBlock.bm.bO, 0, 0, 0, var4, var3);
                this.a(var1, OBlock.bm.bO, 0, 1, 0, var4, var3);
                this.a(var1, OBlock.bm.bO, 0, 2, 0, var4, var3);
                this.a(var1, OBlock.bm.bO, 0, 3, 0, var4, var3);
                this.a(var1, OBlock.bm.bO, 0, 4, 0, var4, var3);

                for (int var5 = 1; var5 <= 3; ++var5) {
                    this.a(var1, OBlock.bm.bO, 0, 0, var5, var4, var3);
                    this.a(var1, 0, 0, 1, var5, var4, var3);
                    this.a(var1, 0, 0, 2, var5, var4, var3);
                    this.a(var1, 0, 0, 3, var5, var4, var3);
                    this.a(var1, OBlock.bm.bO, 0, 4, var5, var4, var3);
                }

                this.a(var1, OBlock.bm.bO, 0, 0, 4, var4, var3);
                this.a(var1, OBlock.bm.bO, 0, 1, 4, var4, var3);
                this.a(var1, OBlock.bm.bO, 0, 2, 4, var4, var3);
                this.a(var1, OBlock.bm.bO, 0, 3, 4, var4, var3);
                this.a(var1, OBlock.bm.bO, 0, 4, 4, var4, var3);
            }

            return true;
        }
    }
}
