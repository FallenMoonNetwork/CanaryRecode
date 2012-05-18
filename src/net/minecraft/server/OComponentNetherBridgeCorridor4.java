package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OComponentNetherBridgePiece;
import net.minecraft.server.OComponentNetherBridgeStartPiece;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OWorld;

public class OComponentNetherBridgeCorridor4 extends OComponentNetherBridgePiece {

    public OComponentNetherBridgeCorridor4(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.g = var3;
    }

    public void a(OStructureComponent var1, List var2, Random var3) {
        byte var4 = 1;
        if (this.h == 1 || this.h == 2) {
            var4 = 5;
        }

        this.b((OComponentNetherBridgeStartPiece) var1, var2, var3, 0, var4, var3.nextInt(8) > 0);
        this.c((OComponentNetherBridgeStartPiece) var1, var2, var3, 0, var4, var3.nextInt(8) > 0);
    }

    public static OComponentNetherBridgeCorridor4 a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, -3, 0, 0, 9, 7, 9, var5);
        return a(var7) && OStructureComponent.a(var0, var7) == null ? new OComponentNetherBridgeCorridor4(var6, var1, var7, var5) : null;
    }

    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        this.a(var1, var3, 0, 0, 0, 8, 1, 8, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 0, 2, 0, 8, 5, 8, 0, 0, false);
        this.a(var1, var3, 0, 6, 0, 8, 6, 5, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 0, 2, 0, 2, 5, 0, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 6, 2, 0, 8, 5, 0, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 1, 3, 0, 1, 4, 0, OBlock.bB.bO, OBlock.bB.bO, false);
        this.a(var1, var3, 7, 3, 0, 7, 4, 0, OBlock.bB.bO, OBlock.bB.bO, false);
        this.a(var1, var3, 0, 2, 4, 8, 2, 8, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 1, 1, 4, 2, 2, 4, 0, 0, false);
        this.a(var1, var3, 6, 1, 4, 7, 2, 4, 0, 0, false);
        this.a(var1, var3, 0, 3, 8, 8, 3, 8, OBlock.bB.bO, OBlock.bB.bO, false);
        this.a(var1, var3, 0, 3, 6, 0, 3, 7, OBlock.bB.bO, OBlock.bB.bO, false);
        this.a(var1, var3, 8, 3, 6, 8, 3, 7, OBlock.bB.bO, OBlock.bB.bO, false);
        this.a(var1, var3, 0, 3, 4, 0, 5, 5, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 8, 3, 4, 8, 5, 5, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 1, 3, 5, 2, 5, 5, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 6, 3, 5, 7, 5, 5, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 1, 4, 5, 1, 5, 5, OBlock.bB.bO, OBlock.bB.bO, false);
        this.a(var1, var3, 7, 4, 5, 7, 5, 5, OBlock.bB.bO, OBlock.bB.bO, false);

        for (int var4 = 0; var4 <= 5; ++var4) {
            for (int var5 = 0; var5 <= 8; ++var5) {
                this.b(var1, OBlock.bA.bO, 0, var5, -1, var4, var3);
            }
        }

        return true;
    }
}
