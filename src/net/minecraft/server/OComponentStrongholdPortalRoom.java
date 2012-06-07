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
import net.minecraft.server.OTileEntityMobSpawner;
import net.minecraft.server.OWorld;

public class OComponentStrongholdPortalRoom extends OComponentStronghold {

    private boolean a;

    public OComponentStrongholdPortalRoom(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.g = var3;
    }

    @Override
    public void a(OStructureComponent var1, List var2, Random var3) {
        if (var1 != null) {
            ((OComponentStrongholdStairs2) var1).b = this;
        }

    }

    public static OComponentStrongholdPortalRoom a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, -4, -1, 0, 11, 8, 16, var5);
        return a(var7) && OStructureComponent.a(var0, var7) == null ? new OComponentStrongholdPortalRoom(var6, var1, var7, var5) : null;
    }

    @Override
    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        this.a(var1, var3, 0, 0, 0, 10, 7, 15, false, var2, OStructureStrongholdPieces.b());
        this.a(var1, var2, var3, OEnumDoor.c, 4, 1, 0);
        byte var4 = 6;
        this.a(var1, var3, 1, var4, 1, 1, var4, 14, false, var2, OStructureStrongholdPieces.b());
        this.a(var1, var3, 9, var4, 1, 9, var4, 14, false, var2, OStructureStrongholdPieces.b());
        this.a(var1, var3, 2, var4, 1, 8, var4, 2, false, var2, OStructureStrongholdPieces.b());
        this.a(var1, var3, 2, var4, 14, 8, var4, 14, false, var2, OStructureStrongholdPieces.b());
        this.a(var1, var3, 1, 1, 1, 2, 1, 4, false, var2, OStructureStrongholdPieces.b());
        this.a(var1, var3, 8, 1, 1, 9, 1, 4, false, var2, OStructureStrongholdPieces.b());
        this.a(var1, var3, 1, 1, 1, 1, 1, 3, OBlock.C.bO, OBlock.C.bO, false);
        this.a(var1, var3, 9, 1, 1, 9, 1, 3, OBlock.C.bO, OBlock.C.bO, false);
        this.a(var1, var3, 3, 1, 8, 7, 1, 12, false, var2, OStructureStrongholdPieces.b());
        this.a(var1, var3, 4, 1, 9, 6, 1, 11, OBlock.C.bO, OBlock.C.bO, false);

        int var5;
        for (var5 = 3; var5 < 14; var5 += 2) {
            this.a(var1, var3, 0, 3, var5, 0, 4, var5, OBlock.bp.bO, OBlock.bp.bO, false);
            this.a(var1, var3, 10, 3, var5, 10, 4, var5, OBlock.bp.bO, OBlock.bp.bO, false);
        }

        for (var5 = 2; var5 < 9; var5 += 2) {
            this.a(var1, var3, var5, 3, 15, var5, 4, 15, OBlock.bp.bO, OBlock.bp.bO, false);
        }

        var5 = this.c(OBlock.bx.bO, 3);
        this.a(var1, var3, 4, 1, 5, 6, 1, 7, false, var2, OStructureStrongholdPieces.b());
        this.a(var1, var3, 4, 2, 6, 6, 2, 7, false, var2, OStructureStrongholdPieces.b());
        this.a(var1, var3, 4, 3, 7, 6, 3, 7, false, var2, OStructureStrongholdPieces.b());

        for (int var6 = 4; var6 <= 6; ++var6) {
            this.a(var1, OBlock.bx.bO, var5, var6, 1, 4, var3);
            this.a(var1, OBlock.bx.bO, var5, var6, 2, 5, var3);
            this.a(var1, OBlock.bx.bO, var5, var6, 3, 6, var3);
        }

        byte var14 = 2;
        byte var7 = 0;
        byte var8 = 3;
        byte var9 = 1;
        switch (this.h) {
        case 0:
            var14 = 0;
            var7 = 2;
            break;
        case 1:
            var14 = 1;
            var7 = 3;
            var8 = 0;
            var9 = 2;
        case 2:
        default:
            break;
        case 3:
            var14 = 3;
            var7 = 1;
            var8 = 0;
            var9 = 2;
        }

        this.a(var1, OBlock.bI.bO, var14 + (var2.nextFloat() > 0.9F ? 4 : 0), 4, 3, 8, var3);
        this.a(var1, OBlock.bI.bO, var14 + (var2.nextFloat() > 0.9F ? 4 : 0), 5, 3, 8, var3);
        this.a(var1, OBlock.bI.bO, var14 + (var2.nextFloat() > 0.9F ? 4 : 0), 6, 3, 8, var3);
        this.a(var1, OBlock.bI.bO, var7 + (var2.nextFloat() > 0.9F ? 4 : 0), 4, 3, 12, var3);
        this.a(var1, OBlock.bI.bO, var7 + (var2.nextFloat() > 0.9F ? 4 : 0), 5, 3, 12, var3);
        this.a(var1, OBlock.bI.bO, var7 + (var2.nextFloat() > 0.9F ? 4 : 0), 6, 3, 12, var3);
        this.a(var1, OBlock.bI.bO, var8 + (var2.nextFloat() > 0.9F ? 4 : 0), 3, 3, 9, var3);
        this.a(var1, OBlock.bI.bO, var8 + (var2.nextFloat() > 0.9F ? 4 : 0), 3, 3, 10, var3);
        this.a(var1, OBlock.bI.bO, var8 + (var2.nextFloat() > 0.9F ? 4 : 0), 3, 3, 11, var3);
        this.a(var1, OBlock.bI.bO, var9 + (var2.nextFloat() > 0.9F ? 4 : 0), 7, 3, 9, var3);
        this.a(var1, OBlock.bI.bO, var9 + (var2.nextFloat() > 0.9F ? 4 : 0), 7, 3, 10, var3);
        this.a(var1, OBlock.bI.bO, var9 + (var2.nextFloat() > 0.9F ? 4 : 0), 7, 3, 11, var3);
        if (!this.a) {
            int var13 = this.b(3);
            int var10 = this.a(5, 6);
            int var11 = this.b(5, 6);
            if (var3.b(var10, var13, var11)) {
                this.a = true;
                var1.e(var10, var13, var11, OBlock.as.bO);
                OTileEntityMobSpawner var12 = (OTileEntityMobSpawner) var1.b(var10, var13, var11);
                if (var12 != null) {
                    var12.a("Silverfish");
                }
            }
        }

        return true;
    }
}
