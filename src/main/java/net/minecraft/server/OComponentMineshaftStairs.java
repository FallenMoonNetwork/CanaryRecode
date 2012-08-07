package net.minecraft.server;


import java.util.List;
import java.util.Random;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OStructureMineshaftPieces;
import net.minecraft.server.OWorld;


public class OComponentMineshaftStairs extends OStructureComponent {

    public OComponentMineshaftStairs(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.g = var3;
    }

    public static OStructureBoundingBox a(List var0, Random var1, int var2, int var3, int var4, int var5) {
        OStructureBoundingBox var6 = new OStructureBoundingBox(var2, var3 - 5, var4, var2, var3 + 2, var4);

        switch (var5) {
        case 0:
            var6.d = var2 + 2;
            var6.f = var4 + 8;
            break;

        case 1:
            var6.a = var2 - 8;
            var6.f = var4 + 2;
            break;

        case 2:
            var6.d = var2 + 2;
            var6.c = var4 - 8;
            break;

        case 3:
            var6.d = var2 + 8;
            var6.f = var4 + 2;
        }

        return OStructureComponent.a(var0, var6) != null ? null : var6;
    }

    @Override
    public void a(OStructureComponent var1, List var2, Random var3) {
        int var4 = this.c();

        switch (this.h) {
        case 0:
            OStructureMineshaftPieces.a(var1, var2, var3, this.g.a, this.g.b, this.g.f + 1, 0, var4);
            break;

        case 1:
            OStructureMineshaftPieces.a(var1, var2, var3, this.g.a - 1, this.g.b, this.g.c, 1, var4);
            break;

        case 2:
            OStructureMineshaftPieces.a(var1, var2, var3, this.g.a, this.g.b, this.g.c - 1, 2, var4);
            break;

        case 3:
            OStructureMineshaftPieces.a(var1, var2, var3, this.g.d + 1, this.g.b, this.g.c, 3, var4);
        }

    }

    @Override
    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.a(var1, var3)) {
            return false;
        } else {
            this.a(var1, var3, 0, 5, 0, 2, 7, 1, 0, 0, false);
            this.a(var1, var3, 0, 0, 7, 2, 2, 8, 0, 0, false);

            for (int var4 = 0; var4 < 5; ++var4) {
                this.a(var1, var3, 0, 5 - var4 - (var4 < 4 ? 1 : 0), 2 + var4, 2, 7 - var4, 2 + var4, 0, 0, false);
            }

            return true;
        }
    }
}
