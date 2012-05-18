package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OComponentVillage;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OWorld;

public class OComponentVillageChurch extends OComponentVillage {

    private int a = -1;

    public OComponentVillageChurch(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.g = var3;
    }

    public void a(OStructureComponent var1, List var2, Random var3) {
    }

    public static OComponentVillageChurch a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, 0, 0, 0, 5, 12, 9, var5);
        return a(var7) && OStructureComponent.a(var0, var7) == null ? new OComponentVillageChurch(var6, var1, var7, var5) : null;
    }

    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.a < 0) {
            this.a = this.b(var1, var3);
            if (this.a < 0) {
                return true;
            }

            this.g.a(0, this.a - this.g.e + 12 - 1, 0);
        }

        this.a(var1, var3, 1, 1, 1, 3, 3, 7, 0, 0, false);
        this.a(var1, var3, 1, 5, 1, 3, 9, 3, 0, 0, false);
        this.a(var1, var3, 1, 0, 0, 3, 0, 8, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 1, 1, 0, 3, 10, 0, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 0, 1, 1, 0, 10, 3, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 4, 1, 1, 4, 10, 3, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 0, 0, 4, 0, 4, 7, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 4, 0, 4, 4, 4, 7, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 1, 1, 8, 3, 4, 8, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 1, 5, 4, 3, 10, 4, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 1, 5, 5, 3, 5, 7, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 0, 9, 0, 4, 9, 4, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 0, 4, 0, 4, 4, 4, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, OBlock.w.bO, 0, 0, 11, 2, var3);
        this.a(var1, OBlock.w.bO, 0, 4, 11, 2, var3);
        this.a(var1, OBlock.w.bO, 0, 2, 11, 0, var3);
        this.a(var1, OBlock.w.bO, 0, 2, 11, 4, var3);
        this.a(var1, OBlock.w.bO, 0, 1, 1, 6, var3);
        this.a(var1, OBlock.w.bO, 0, 1, 1, 7, var3);
        this.a(var1, OBlock.w.bO, 0, 2, 1, 7, var3);
        this.a(var1, OBlock.w.bO, 0, 3, 1, 6, var3);
        this.a(var1, OBlock.w.bO, 0, 3, 1, 7, var3);
        this.a(var1, OBlock.aH.bO, this.c(OBlock.aH.bO, 3), 1, 1, 5, var3);
        this.a(var1, OBlock.aH.bO, this.c(OBlock.aH.bO, 3), 2, 1, 6, var3);
        this.a(var1, OBlock.aH.bO, this.c(OBlock.aH.bO, 3), 3, 1, 5, var3);
        this.a(var1, OBlock.aH.bO, this.c(OBlock.aH.bO, 1), 1, 2, 7, var3);
        this.a(var1, OBlock.aH.bO, this.c(OBlock.aH.bO, 0), 3, 2, 7, var3);
        this.a(var1, OBlock.bq.bO, 0, 0, 2, 2, var3);
        this.a(var1, OBlock.bq.bO, 0, 0, 3, 2, var3);
        this.a(var1, OBlock.bq.bO, 0, 4, 2, 2, var3);
        this.a(var1, OBlock.bq.bO, 0, 4, 3, 2, var3);
        this.a(var1, OBlock.bq.bO, 0, 0, 6, 2, var3);
        this.a(var1, OBlock.bq.bO, 0, 0, 7, 2, var3);
        this.a(var1, OBlock.bq.bO, 0, 4, 6, 2, var3);
        this.a(var1, OBlock.bq.bO, 0, 4, 7, 2, var3);
        this.a(var1, OBlock.bq.bO, 0, 2, 6, 0, var3);
        this.a(var1, OBlock.bq.bO, 0, 2, 7, 0, var3);
        this.a(var1, OBlock.bq.bO, 0, 2, 6, 4, var3);
        this.a(var1, OBlock.bq.bO, 0, 2, 7, 4, var3);
        this.a(var1, OBlock.bq.bO, 0, 0, 3, 6, var3);
        this.a(var1, OBlock.bq.bO, 0, 4, 3, 6, var3);
        this.a(var1, OBlock.bq.bO, 0, 2, 3, 8, var3);
        this.a(var1, OBlock.aq.bO, 0, 2, 4, 7, var3);
        this.a(var1, OBlock.aq.bO, 0, 1, 4, 6, var3);
        this.a(var1, OBlock.aq.bO, 0, 3, 4, 6, var3);
        this.a(var1, OBlock.aq.bO, 0, 2, 4, 5, var3);
        int var4 = this.c(OBlock.aF.bO, 4);

        int var5;
        for (var5 = 1; var5 <= 9; ++var5) {
            this.a(var1, OBlock.aF.bO, var4, 3, var5, 3, var3);
        }

        this.a(var1, 0, 0, 2, 1, 0, var3);
        this.a(var1, 0, 0, 2, 2, 0, var3);
        this.a(var1, var3, var2, 2, 1, 0, this.c(OBlock.aE.bO, 1));
        if (this.a(var1, 2, 0, -1, var3) == 0 && this.a(var1, 2, -1, -1, var3) != 0) {
            this.a(var1, OBlock.aH.bO, this.c(OBlock.aH.bO, 3), 2, 0, -1, var3);
        }

        for (var5 = 0; var5 < 9; ++var5) {
            for (int var6 = 0; var6 < 5; ++var6) {
                this.b(var1, var6, 12, var5, var3);
                this.b(var1, OBlock.w.bO, 0, var6, -1, var5, var3);
            }
        }

        this.a(var1, var3, 2, 1, 2, 1);
        return true;
    }

    protected int a(int var1) {
        return 2;
    }
}
