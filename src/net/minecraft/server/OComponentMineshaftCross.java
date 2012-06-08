package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OStructureMineshaftPieces;
import net.minecraft.server.OWorld;

public class OComponentMineshaftCross extends OStructureComponent {

    private final int a;
    private final boolean b;

    public OComponentMineshaftCross(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.a = var4;
        this.g = var3;
        this.b = var3.c() > 3;
    }

    public static OStructureBoundingBox a(List var0, Random var1, int var2, int var3, int var4, int var5) {
        OStructureBoundingBox var6 = new OStructureBoundingBox(var2, var3, var4, var2, var3 + 2, var4);
        if (var1.nextInt(4) == 0) {
            var6.e += 4;
        }

        switch (var5) {
        case 0:
            var6.a = var2 - 1;
            var6.d = var2 + 3;
            var6.f = var4 + 4;
            break;
        case 1:
            var6.a = var2 - 4;
            var6.c = var4 - 1;
            var6.f = var4 + 3;
            break;
        case 2:
            var6.a = var2 - 1;
            var6.d = var2 + 3;
            var6.c = var4 - 4;
            break;
        case 3:
            var6.d = var2 + 4;
            var6.c = var4 - 1;
            var6.f = var4 + 3;
        }

        return OStructureComponent.a(var0, var6) != null ? null : var6;
    }

    @Override
    public void a(OStructureComponent var1, List var2, Random var3) {
        int var4 = this.c();
        switch (this.a) {
        case 0:
            OStructureMineshaftPieces.a(var1, var2, var3, this.g.a + 1, this.g.b, this.g.f + 1, 0, var4);
            OStructureMineshaftPieces.a(var1, var2, var3, this.g.a - 1, this.g.b, this.g.c + 1, 1, var4);
            OStructureMineshaftPieces.a(var1, var2, var3, this.g.d + 1, this.g.b, this.g.c + 1, 3, var4);
            break;
        case 1:
            OStructureMineshaftPieces.a(var1, var2, var3, this.g.a + 1, this.g.b, this.g.c - 1, 2, var4);
            OStructureMineshaftPieces.a(var1, var2, var3, this.g.a + 1, this.g.b, this.g.f + 1, 0, var4);
            OStructureMineshaftPieces.a(var1, var2, var3, this.g.a - 1, this.g.b, this.g.c + 1, 1, var4);
            break;
        case 2:
            OStructureMineshaftPieces.a(var1, var2, var3, this.g.a + 1, this.g.b, this.g.c - 1, 2, var4);
            OStructureMineshaftPieces.a(var1, var2, var3, this.g.a - 1, this.g.b, this.g.c + 1, 1, var4);
            OStructureMineshaftPieces.a(var1, var2, var3, this.g.d + 1, this.g.b, this.g.c + 1, 3, var4);
            break;
        case 3:
            OStructureMineshaftPieces.a(var1, var2, var3, this.g.a + 1, this.g.b, this.g.c - 1, 2, var4);
            OStructureMineshaftPieces.a(var1, var2, var3, this.g.a + 1, this.g.b, this.g.f + 1, 0, var4);
            OStructureMineshaftPieces.a(var1, var2, var3, this.g.d + 1, this.g.b, this.g.c + 1, 3, var4);
        }

        if (this.b) {
            if (var3.nextBoolean()) {
                OStructureMineshaftPieces.a(var1, var2, var3, this.g.a + 1, this.g.b + 3 + 1, this.g.c - 1, 2, var4);
            }

            if (var3.nextBoolean()) {
                OStructureMineshaftPieces.a(var1, var2, var3, this.g.a - 1, this.g.b + 3 + 1, this.g.c + 1, 1, var4);
            }

            if (var3.nextBoolean()) {
                OStructureMineshaftPieces.a(var1, var2, var3, this.g.d + 1, this.g.b + 3 + 1, this.g.c + 1, 3, var4);
            }

            if (var3.nextBoolean()) {
                OStructureMineshaftPieces.a(var1, var2, var3, this.g.a + 1, this.g.b + 3 + 1, this.g.f + 1, 0, var4);
            }
        }

    }

    @Override
    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.a(var1, var3)) {
            return false;
        } else {
            if (this.b) {
                this.a(var1, var3, this.g.a + 1, this.g.b, this.g.c, this.g.d - 1, this.g.b + 3 - 1, this.g.f, 0, 0, false);
                this.a(var1, var3, this.g.a, this.g.b, this.g.c + 1, this.g.d, this.g.b + 3 - 1, this.g.f - 1, 0, 0, false);
                this.a(var1, var3, this.g.a + 1, this.g.e - 2, this.g.c, this.g.d - 1, this.g.e, this.g.f, 0, 0, false);
                this.a(var1, var3, this.g.a, this.g.e - 2, this.g.c + 1, this.g.d, this.g.e, this.g.f - 1, 0, 0, false);
                this.a(var1, var3, this.g.a + 1, this.g.b + 3, this.g.c + 1, this.g.d - 1, this.g.b + 3, this.g.f - 1, 0, 0, false);
            } else {
                this.a(var1, var3, this.g.a + 1, this.g.b, this.g.c, this.g.d - 1, this.g.e, this.g.f, 0, 0, false);
                this.a(var1, var3, this.g.a, this.g.b, this.g.c + 1, this.g.d, this.g.e, this.g.f - 1, 0, 0, false);
            }

            this.a(var1, var3, this.g.a + 1, this.g.b, this.g.c + 1, this.g.a + 1, this.g.e, this.g.c + 1, OBlock.x.bO, 0, false);
            this.a(var1, var3, this.g.a + 1, this.g.b, this.g.f - 1, this.g.a + 1, this.g.e, this.g.f - 1, OBlock.x.bO, 0, false);
            this.a(var1, var3, this.g.d - 1, this.g.b, this.g.c + 1, this.g.d - 1, this.g.e, this.g.c + 1, OBlock.x.bO, 0, false);
            this.a(var1, var3, this.g.d - 1, this.g.b, this.g.f - 1, this.g.d - 1, this.g.e, this.g.f - 1, OBlock.x.bO, 0, false);

            for (int var4 = this.g.a; var4 <= this.g.d; ++var4) {
                for (int var5 = this.g.c; var5 <= this.g.f; ++var5) {
                    int var6 = this.a(var1, var4, this.g.b - 1, var5, var3);
                    if (var6 == 0) {
                        this.a(var1, OBlock.x.bO, 0, var4, this.g.b - 1, var5, var3);
                    }
                }
            }

            return true;
        }
    }
}
