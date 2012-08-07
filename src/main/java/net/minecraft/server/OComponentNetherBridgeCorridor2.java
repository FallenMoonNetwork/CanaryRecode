package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OComponentNetherBridgePiece;
import net.minecraft.server.OComponentNetherBridgeStartPiece;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OWorld;

public class OComponentNetherBridgeCorridor2 extends OComponentNetherBridgePiece {

    public OComponentNetherBridgeCorridor2(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.g = var3;
    }

    @Override
    public void a(OStructureComponent var1, List var2, Random var3) {
        this.c((OComponentNetherBridgeStartPiece) var1, var2, var3, 0, 1, true);
    }

    public static OComponentNetherBridgeCorridor2 a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, -1, 0, 0, 5, 7, 5, var5);
        return a(var7) && OStructureComponent.a(var0, var7) == null ? new OComponentNetherBridgeCorridor2(var6, var1, var7, var5) : null;
    }

    @Override
    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        this.a(var1, var3, 0, 0, 0, 4, 1, 4, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 0, 2, 0, 4, 5, 4, 0, 0, false);
        this.a(var1, var3, 0, 2, 0, 0, 5, 4, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 0, 3, 1, 0, 4, 1, OBlock.bB.bO, OBlock.bB.bO, false);
        this.a(var1, var3, 0, 3, 3, 0, 4, 3, OBlock.bB.bO, OBlock.bB.bO, false);
        this.a(var1, var3, 4, 2, 0, 4, 5, 0, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 1, 2, 4, 4, 5, 4, OBlock.bA.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 1, 3, 4, 1, 4, 4, OBlock.bB.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 3, 3, 4, 3, 4, 4, OBlock.bB.bO, OBlock.bA.bO, false);
        this.a(var1, var3, 0, 6, 0, 4, 6, 4, OBlock.bA.bO, OBlock.bA.bO, false);

        for (int var4 = 0; var4 <= 4; ++var4) {
            for (int var5 = 0; var5 <= 4; ++var5) {
                this.b(var1, OBlock.bA.bO, 0, var4, -1, var5, var3);
            }
        }

        return true;
    }
}
