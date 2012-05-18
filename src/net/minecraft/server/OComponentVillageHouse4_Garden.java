package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OComponentVillage;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OWorld;

public class OComponentVillageHouse4_Garden extends OComponentVillage {

    private int a = -1;
    private final boolean b;

    public OComponentVillageHouse4_Garden(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.g = var3;
        this.b = var2.nextBoolean();
    }

    public void a(OStructureComponent var1, List var2, Random var3) {
    }

    public static OComponentVillageHouse4_Garden a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, 0, 0, 0, 5, 6, 5, var5);
        return OStructureComponent.a(var0, var7) != null ? null : new OComponentVillageHouse4_Garden(var6, var1, var7, var5);
    }

    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.a < 0) {
            this.a = this.b(var1, var3);
            if (this.a < 0) {
                return true;
            }

            this.g.a(0, this.a - this.g.e + 6 - 1, 0);
        }

        this.a(var1, var3, 0, 0, 0, 4, 0, 4, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 0, 4, 0, 4, 4, 4, OBlock.J.bO, OBlock.J.bO, false);
        this.a(var1, var3, 1, 4, 1, 3, 4, 3, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, OBlock.w.bO, 0, 0, 1, 0, var3);
        this.a(var1, OBlock.w.bO, 0, 0, 2, 0, var3);
        this.a(var1, OBlock.w.bO, 0, 0, 3, 0, var3);
        this.a(var1, OBlock.w.bO, 0, 4, 1, 0, var3);
        this.a(var1, OBlock.w.bO, 0, 4, 2, 0, var3);
        this.a(var1, OBlock.w.bO, 0, 4, 3, 0, var3);
        this.a(var1, OBlock.w.bO, 0, 0, 1, 4, var3);
        this.a(var1, OBlock.w.bO, 0, 0, 2, 4, var3);
        this.a(var1, OBlock.w.bO, 0, 0, 3, 4, var3);
        this.a(var1, OBlock.w.bO, 0, 4, 1, 4, var3);
        this.a(var1, OBlock.w.bO, 0, 4, 2, 4, var3);
        this.a(var1, OBlock.w.bO, 0, 4, 3, 4, var3);
        this.a(var1, var3, 0, 1, 1, 0, 3, 3, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 4, 1, 1, 4, 3, 3, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 1, 1, 4, 3, 3, 4, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, OBlock.bq.bO, 0, 0, 2, 2, var3);
        this.a(var1, OBlock.bq.bO, 0, 2, 2, 4, var3);
        this.a(var1, OBlock.bq.bO, 0, 4, 2, 2, var3);
        this.a(var1, OBlock.x.bO, 0, 1, 1, 0, var3);
        this.a(var1, OBlock.x.bO, 0, 1, 2, 0, var3);
        this.a(var1, OBlock.x.bO, 0, 1, 3, 0, var3);
        this.a(var1, OBlock.x.bO, 0, 2, 3, 0, var3);
        this.a(var1, OBlock.x.bO, 0, 3, 3, 0, var3);
        this.a(var1, OBlock.x.bO, 0, 3, 2, 0, var3);
        this.a(var1, OBlock.x.bO, 0, 3, 1, 0, var3);
        if (this.a(var1, 2, 0, -1, var3) == 0 && this.a(var1, 2, -1, -1, var3) != 0) {
            this.a(var1, OBlock.aH.bO, this.c(OBlock.aH.bO, 3), 2, 0, -1, var3);
        }

        this.a(var1, var3, 1, 1, 1, 3, 3, 3, 0, 0, false);
        if (this.b) {
            this.a(var1, OBlock.aZ.bO, 0, 0, 5, 0, var3);
            this.a(var1, OBlock.aZ.bO, 0, 1, 5, 0, var3);
            this.a(var1, OBlock.aZ.bO, 0, 2, 5, 0, var3);
            this.a(var1, OBlock.aZ.bO, 0, 3, 5, 0, var3);
            this.a(var1, OBlock.aZ.bO, 0, 4, 5, 0, var3);
            this.a(var1, OBlock.aZ.bO, 0, 0, 5, 4, var3);
            this.a(var1, OBlock.aZ.bO, 0, 1, 5, 4, var3);
            this.a(var1, OBlock.aZ.bO, 0, 2, 5, 4, var3);
            this.a(var1, OBlock.aZ.bO, 0, 3, 5, 4, var3);
            this.a(var1, OBlock.aZ.bO, 0, 4, 5, 4, var3);
            this.a(var1, OBlock.aZ.bO, 0, 4, 5, 1, var3);
            this.a(var1, OBlock.aZ.bO, 0, 4, 5, 2, var3);
            this.a(var1, OBlock.aZ.bO, 0, 4, 5, 3, var3);
            this.a(var1, OBlock.aZ.bO, 0, 0, 5, 1, var3);
            this.a(var1, OBlock.aZ.bO, 0, 0, 5, 2, var3);
            this.a(var1, OBlock.aZ.bO, 0, 0, 5, 3, var3);
        }

        int var4;
        if (this.b) {
            var4 = this.c(OBlock.aF.bO, 3);
            this.a(var1, OBlock.aF.bO, var4, 3, 1, 3, var3);
            this.a(var1, OBlock.aF.bO, var4, 3, 2, 3, var3);
            this.a(var1, OBlock.aF.bO, var4, 3, 3, 3, var3);
            this.a(var1, OBlock.aF.bO, var4, 3, 4, 3, var3);
        }

        this.a(var1, OBlock.aq.bO, 0, 2, 3, 1, var3);

        for (var4 = 0; var4 < 5; ++var4) {
            for (int var5 = 0; var5 < 5; ++var5) {
                this.b(var1, var5, 6, var4, var3);
                this.b(var1, OBlock.w.bO, 0, var5, -1, var4, var3);
            }
        }

        this.a(var1, var3, 1, 1, 2, 1);
        return true;
    }
}
