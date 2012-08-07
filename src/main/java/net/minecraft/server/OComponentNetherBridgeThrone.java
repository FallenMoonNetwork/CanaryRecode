package net.minecraft.server;


import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OComponentNetherBridgePiece;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OTileEntityMobSpawner;
import net.minecraft.server.OWorld;


public class OComponentNetherBridgeThrone extends OComponentNetherBridgePiece {

    private boolean a;

    public OComponentNetherBridgeThrone(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.g = var3;
    }

    @Override
    public void a(OStructureComponent var1, List var2, Random var3) {}

    public static OComponentNetherBridgeThrone a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, -2, 0, 0, 7, 8, 9, var5);

        return a(var7) && OStructureComponent.a(var0, var7) == null ? new OComponentNetherBridgeThrone(var6, var1, var7, var5) : null;
    }

    @Override
    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        this.a(var1, var3, 0, 2, 0, 6, 7, 7, 0, 0, false);
        this.a(var1, var3, 1, 0, 0, 5, 1, 7, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 1, 2, 1, 5, 2, 7, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 1, 3, 2, 5, 3, 7, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 1, 4, 3, 5, 4, 7, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 1, 2, 0, 1, 4, 2, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 5, 2, 0, 5, 4, 2, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 1, 5, 2, 1, 5, 3, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 5, 5, 2, 5, 5, 3, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 0, 5, 3, 0, 5, 8, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 6, 5, 3, 6, 5, 8, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 1, 5, 8, 5, 5, 8, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, OBlock.bB.bO, 0, 1, 6, 3, var3);
        this.a(var1, OBlock.bB.bO, 0, 5, 6, 3, var3);
        this.a(var1, var3, 0, 6, 3, 0, 6, 8, OBlock.bB.bO, OBlock.bB.bO, false);
        this.a(var1, var3, 6, 6, 3, 6, 6, 8, OBlock.bB.bO, OBlock.bB.bO, false);
        this.a(var1, var3, 1, 6, 8, 5, 7, 8, OBlock.bB.bO, OBlock.bB.bO, false);
        this.a(var1, var3, 2, 8, 8, 4, 8, 8, OBlock.bB.bO, OBlock.bB.bO, false);
        int var4;
        int var5;

        if (!this.a) {
            var4 = this.b(5);
            var5 = this.a(3, 5);
            int var6 = this.b(3, 5);

            if (var3.b(var5, var4, var6)) {
                this.a = true;
                var1.e(var5, var4, var6, OBlock.as.bO);
                OTileEntityMobSpawner var7 = (OTileEntityMobSpawner) var1.b(var5, var4, var6);

                if (var7 != null) {
                    var7.a("Blaze");
                }
            }
        }

        for (var4 = 0; var4 <= 6; ++var4) {
            for (var5 = 0; var5 <= 6; ++var5) {
                this.b(var1, OBlock.bA.bO, 0, var4, -1, var5, var3);
            }
        }

        return true;
    }
}
