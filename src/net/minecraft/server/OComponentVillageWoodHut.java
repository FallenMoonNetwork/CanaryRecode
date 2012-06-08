package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OComponentVillage;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OWorld;

public class OComponentVillageWoodHut extends OComponentVillage {

    private int a = -1;
    private final boolean b;
    private final int c;

    public OComponentVillageWoodHut(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.g = var3;
        this.b = var2.nextBoolean();
        this.c = var2.nextInt(3);
    }

    @Override
    public void a(OStructureComponent var1, List var2, Random var3) {
    }

    public static OComponentVillageWoodHut a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, 0, 0, 0, 4, 6, 5, var5);
        return a(var7) && OStructureComponent.a(var0, var7) == null ? new OComponentVillageWoodHut(var6, var1, var7, var5) : null;
    }

    @Override
    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.a < 0) {
            this.a = this.b(var1, var3);
            if (this.a < 0) {
                return true;
            }

            this.g.a(0, this.a - this.g.e + 6 - 1, 0);
        }

        this.a(var1, var3, 1, 1, 1, 3, 5, 4, 0, 0, false);
        this.a(var1, var3, 0, 0, 0, 3, 0, 4, OBlock.w.bO, OBlock.w.bO, false);
        this.a(var1, var3, 1, 0, 1, 2, 0, 3, OBlock.v.bO, OBlock.v.bO, false);
        if (this.b) {
            this.a(var1, var3, 1, 4, 1, 2, 4, 3, OBlock.J.bO, OBlock.J.bO, false);
        } else {
            this.a(var1, var3, 1, 5, 1, 2, 5, 3, OBlock.J.bO, OBlock.J.bO, false);
        }

        this.a(var1, OBlock.J.bO, 0, 1, 4, 0, var3);
        this.a(var1, OBlock.J.bO, 0, 2, 4, 0, var3);
        this.a(var1, OBlock.J.bO, 0, 1, 4, 4, var3);
        this.a(var1, OBlock.J.bO, 0, 2, 4, 4, var3);
        this.a(var1, OBlock.J.bO, 0, 0, 4, 1, var3);
        this.a(var1, OBlock.J.bO, 0, 0, 4, 2, var3);
        this.a(var1, OBlock.J.bO, 0, 0, 4, 3, var3);
        this.a(var1, OBlock.J.bO, 0, 3, 4, 1, var3);
        this.a(var1, OBlock.J.bO, 0, 3, 4, 2, var3);
        this.a(var1, OBlock.J.bO, 0, 3, 4, 3, var3);
        this.a(var1, var3, 0, 1, 0, 0, 3, 0, OBlock.J.bO, OBlock.J.bO, false);
        this.a(var1, var3, 3, 1, 0, 3, 3, 0, OBlock.J.bO, OBlock.J.bO, false);
        this.a(var1, var3, 0, 1, 4, 0, 3, 4, OBlock.J.bO, OBlock.J.bO, false);
        this.a(var1, var3, 3, 1, 4, 3, 3, 4, OBlock.J.bO, OBlock.J.bO, false);
        this.a(var1, var3, 0, 1, 1, 0, 3, 3, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 3, 1, 1, 3, 3, 3, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 1, 1, 0, 2, 3, 0, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, var3, 1, 1, 4, 2, 3, 4, OBlock.x.bO, OBlock.x.bO, false);
        this.a(var1, OBlock.bq.bO, 0, 0, 2, 2, var3);
        this.a(var1, OBlock.bq.bO, 0, 3, 2, 2, var3);
        if (this.c > 0) {
            this.a(var1, OBlock.aZ.bO, 0, this.c, 1, 3, var3);
            this.a(var1, OBlock.aM.bO, 0, this.c, 2, 3, var3);
        }

        this.a(var1, 0, 0, 1, 1, 0, var3);
        this.a(var1, 0, 0, 1, 2, 0, var3);
        this.a(var1, var3, var2, 1, 1, 0, this.c(OBlock.aE.bO, 1));
        if (this.a(var1, 1, 0, -1, var3) == 0 && this.a(var1, 1, -1, -1, var3) != 0) {
            this.a(var1, OBlock.aH.bO, this.c(OBlock.aH.bO, 3), 1, 0, -1, var3);
        }

        for (int var4 = 0; var4 < 5; ++var4) {
            for (int var5 = 0; var5 < 4; ++var5) {
                this.b(var1, var5, 6, var4, var3);
                this.b(var1, OBlock.w.bO, 0, var5, -1, var4, var3);
            }
        }

        this.a(var1, var3, 1, 1, 2, 1);
        return true;
    }
}
