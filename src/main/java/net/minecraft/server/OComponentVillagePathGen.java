package net.minecraft.server;


import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OComponentVillageRoadPiece;
import net.minecraft.server.OComponentVillageStartPiece;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OStructureVillagePieces;
import net.minecraft.server.OWorld;


public class OComponentVillagePathGen extends OComponentVillageRoadPiece {

    private int a;

    public OComponentVillagePathGen(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.g = var3;
        this.a = Math.max(var3.b(), var3.d());
    }

    @Override
    public void a(OStructureComponent var1, List var2, Random var3) {
        boolean var4 = false;

        int var5;
        OStructureComponent var6;

        for (var5 = var3.nextInt(5); var5 < this.a - 8; var5 += 2 + var3.nextInt(5)) {
            var6 = this.a((OComponentVillageStartPiece) var1, var2, var3, 0, var5);
            if (var6 != null) {
                var5 += Math.max(var6.g.b(), var6.g.d());
                var4 = true;
            }
        }

        for (var5 = var3.nextInt(5); var5 < this.a - 8; var5 += 2 + var3.nextInt(5)) {
            var6 = this.b((OComponentVillageStartPiece) var1, var2, var3, 0, var5);
            if (var6 != null) {
                var5 += Math.max(var6.g.b(), var6.g.d());
                var4 = true;
            }
        }

        if (var4 && var3.nextInt(3) > 0) {
            switch (this.h) {
            case 0:
                OStructureVillagePieces.b((OComponentVillageStartPiece) var1, var2, var3, this.g.a - 1, this.g.b, this.g.f - 2, 1, this.c());
                break;

            case 1:
                OStructureVillagePieces.b((OComponentVillageStartPiece) var1, var2, var3, this.g.a, this.g.b, this.g.c - 1, 2, this.c());
                break;

            case 2:
                OStructureVillagePieces.b((OComponentVillageStartPiece) var1, var2, var3, this.g.a - 1, this.g.b, this.g.c, 1, this.c());
                break;

            case 3:
                OStructureVillagePieces.b((OComponentVillageStartPiece) var1, var2, var3, this.g.d - 2, this.g.b, this.g.c - 1, 2, this.c());
            }
        }

        if (var4 && var3.nextInt(3) > 0) {
            switch (this.h) {
            case 0:
                OStructureVillagePieces.b((OComponentVillageStartPiece) var1, var2, var3, this.g.d + 1, this.g.b, this.g.f - 2, 3, this.c());
                break;

            case 1:
                OStructureVillagePieces.b((OComponentVillageStartPiece) var1, var2, var3, this.g.a, this.g.b, this.g.f + 1, 0, this.c());
                break;

            case 2:
                OStructureVillagePieces.b((OComponentVillageStartPiece) var1, var2, var3, this.g.d + 1, this.g.b, this.g.c, 3, this.c());
                break;

            case 3:
                OStructureVillagePieces.b((OComponentVillageStartPiece) var1, var2, var3, this.g.d - 2, this.g.b, this.g.f + 1, 0, this.c());
            }
        }

    }

    public static OStructureBoundingBox a(OComponentVillageStartPiece var0, List var1, Random var2, int var3, int var4, int var5, int var6) {
        for (int var7 = 7 * OMathHelper.a(var2, 3, 5); var7 >= 7; var7 -= 7) {
            OStructureBoundingBox var8 = OStructureBoundingBox.a(var3, var4, var5, 0, 0, 0, 3, 3, var7, var6);

            if (OStructureComponent.a(var1, var8) == null) {
                return var8;
            }
        }

        return null;
    }

    @Override
    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        for (int var4 = this.g.a; var4 <= this.g.d; ++var4) {
            for (int var5 = this.g.c; var5 <= this.g.f; ++var5) {
                if (var3.b(var4, 64, var5)) {
                    int var6 = var1.g(var4, var5) - 1;

                    var1.b(var4, var6, var5, OBlock.F.bO);
                }
            }
        }

        return true;
    }
}
