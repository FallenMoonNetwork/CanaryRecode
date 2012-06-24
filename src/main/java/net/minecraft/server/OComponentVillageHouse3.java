package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OComponentVillage;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OWorld;

public class OComponentVillageHouse3 extends OComponentVillage {

    private int a = -1;

    public OComponentVillageHouse3(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.g = var3;
    }

    @Override
    public void a(OStructureComponent var1, List var2, Random var3) {
    }

    public static OComponentVillageHouse3 a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, 0, 0, 0, 9, 7, 12, var5);
        return a(var7) && OStructureComponent.a(var0, var7) == null ? new OComponentVillageHouse3(var6, var1, var7, var5) : null;
    }

    @Override
    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.a < 0) {
            this.a = this.b(var1, var3);
            if (this.a < 0) {
                return true;
            }

            this.g.a(0, this.a - this.g.e + 7 - 1, 0);
        }

        this.a(var1, var3, 1, 1, 1, 7, 4, 4, 0, 0, false);
        this.a(var1, var3, 2, 1, 6, 8, 4, 10, 0, 0, false);
        this.a(var1, var3, 2, 0, 5, 8, 0, 10, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 1, 0, 1, 7, 0, 4, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 0, 0, 0, 0, 3, 5, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 8, 0, 0, 8, 3, 10, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 1, 0, 0, 7, 2, 0, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 1, 0, 5, 2, 1, 5, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 2, 0, 6, 2, 3, 10, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 3, 0, 10, 7, 3, 10, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 1, 2, 0, 7, 3, 0, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 1, 2, 5, 2, 3, 5, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 0, 4, 1, 8, 4, 1, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 0, 4, 4, 3, 4, 4, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 0, 5, 2, 8, 5, 3, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, OBlock.x.bO, 0, 0, 4, 2, var3);
        this.a(var1, OBlock.x.bO, 0, 0, 4, 3, var3);
        this.a(var1, OBlock.x.bO, 0, 8, 4, 2, var3);
        this.a(var1, OBlock.x.bO, 0, 8, 4, 3, var3);
        this.a(var1, OBlock.x.bO, 0, 8, 4, 4, var3);
        int var4 = this.c(OBlock.at.bO, 3);
        int var5 = this.c(OBlock.at.bO, 2);

        int var6;
        int var7;
        for (var6 = -1; var6 <= 2; ++var6) {
            for (var7 = 0; var7 <= 8; ++var7) {
                this.a(var1, OBlock.at.bO, var4, var7, 4 + var6, var6, var3);
                if ((var6 > -1 || var7 <= 1) && (var6 > 0 || var7 <= 3) && (var6 > 1 || var7 <= 4 || var7 >= 6)) {
                    this.a(var1, OBlock.at.bO, var5, var7, 4 + var6, 5 - var6, var3);
                }
            }
        }

        this.a(var1, var3, 3, 4, 5, 3, 4, 10, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 7, 4, 2, 7, 4, 10, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 4, 5, 4, 4, 5, 10, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 6, 5, 4, 6, 5, 10, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 5, 6, 3, 5, 6, 10, OBlock.x.bO, OBlock.x.bO, false);
        var6 = this.c(OBlock.at.bO, 0);

        int var8;
        for (var7 = 4; var7 >= 1; --var7) {
            this.a(var1, OBlock.x.bO, 0, var7, 2 + var7, 7 - var7, var3);

            for (var8 = 8 - var7; var8 <= 10; ++var8) {
                this.a(var1, OBlock.at.bO, var6, var7, 2 + var7, var8, var3);
            }
        }

        var7 = this.c(OBlock.at.bO, 1);
        this.a(var1, OBlock.x.bO, 0, 6, 6, 3, var3);
        this.a(var1, OBlock.x.bO, 0, 7, 5, 4, var3);
        this.a(var1, OBlock.at.bO, var7, 6, 6, 4, var3);

        int var9;
        for (var8 = 6; var8 <= 8; ++var8) {
            for (var9 = 5; var9 <= 10; ++var9) {
                this.a(var1, OBlock.at.bO, var7, var8, 12 - var8, var9, var3);
            }
        }

        this.a(var1, OBlock.J.bO, 0, 0, 2, 1, var3);
        this.a(var1, OBlock.J.bO, 0, 0, 2, 4, var3);
        this.a(var1, OBlock.bq.bO, 0, 0, 2, 2, var3);
        this.a(var1, OBlock.bq.bO, 0, 0, 2, 3, var3);
        this.a(var1, OBlock.J.bO, 0, 4, 2, 0, var3);
        this.a(var1, OBlock.bq.bO, 0, 5, 2, 0, var3);
        this.a(var1, OBlock.J.bO, 0, 6, 2, 0, var3);
        this.a(var1, OBlock.J.bO, 0, 8, 2, 1, var3);
        this.a(var1, OBlock.bq.bO, 0, 8, 2, 2, var3);
        this.a(var1, OBlock.bq.bO, 0, 8, 2, 3, var3);
        this.a(var1, OBlock.J.bO, 0, 8, 2, 4, var3);
        this.a(var1, OBlock.x.bO, 0, 8, 2, 5, var3);
        this.a(var1, OBlock.J.bO, 0, 8, 2, 6, var3);
        this.a(var1, OBlock.bq.bO, 0, 8, 2, 7, var3);
        this.a(var1, OBlock.bq.bO, 0, 8, 2, 8, var3);
        this.a(var1, OBlock.J.bO, 0, 8, 2, 9, var3);
        this.a(var1, OBlock.J.bO, 0, 2, 2, 6, var3);
        this.a(var1, OBlock.bq.bO, 0, 2, 2, 7, var3);
        this.a(var1, OBlock.bq.bO, 0, 2, 2, 8, var3);
        this.a(var1, OBlock.J.bO, 0, 2, 2, 9, var3);
        this.a(var1, OBlock.J.bO, 0, 4, 4, 10, var3);
        this.a(var1, OBlock.bq.bO, 0, 5, 4, 10, var3);
        this.a(var1, OBlock.J.bO, 0, 6, 4, 10, var3);
        this.a(var1, OBlock.x.bO, 0, 5, 5, 10, var3);
        this.a(var1, 0, 0, 2, 1, 0, var3);
        this.a(var1, 0, 0, 2, 2, 0, var3);
        this.a(var1, OBlock.aq.bO, 0, 2, 3, 1, var3);
        this.a(var1, var3, var2, 2, 1, 0, this.c(OBlock.aE.bO, 1));
        this.a(var1, var3, 1, 0, -1, 3, 2, -1, 0, 0, false);
        if (this.a(var1, 2, 0, -1, var3) == 0 && this.a(var1, 2, -1, -1, var3) != 0) {
            this.a(var1, OBlock.aH.bO, this.c(OBlock.aH.bO, 3), 2, 0, -1, var3);
        }

        for (var8 = 0; var8 < 5; ++var8) {
            for (var9 = 0; var9 < 9; ++var9) {
                this.b(var1, var9, 7, var8, var3);
                this.b(var1, OBlock.w.bO, 0, var9, -1, var8, var3);
            }
        }

        for (var8 = 5; var8 < 11; ++var8) {
            for (var9 = 2; var9 < 9; ++var9) {
                this.b(var1, var9, 7, var8, var3);
                this.b(var1, OBlock.w.bO, 0, var9, -1, var8, var3);
            }
        }

        this.a(var1, var3, 4, 1, 2, 2);
        return true;
    }
}
