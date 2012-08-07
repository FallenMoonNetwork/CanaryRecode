package net.minecraft.server;


import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OComponentVillage;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OWorld;


public class OComponentVillageField extends OComponentVillage {

    private int a = -1;

    public OComponentVillageField(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.g = var3;
    }

    @Override
    public void a(OStructureComponent var1, List var2, Random var3) {}

    public static OComponentVillageField a(List var0, Random var1, int var2, int var3, int var4, int var5, int var6) {
        OStructureBoundingBox var7 = OStructureBoundingBox.a(var2, var3, var4, 0, 0, 0, 13, 4, 9, var5);

        return a(var7) && OStructureComponent.a(var0, var7) == null ? new OComponentVillageField(var6, var1, var7, var5) : null;
    }

    @Override
    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.a < 0) {
            this.a = this.b(var1, var3);
            if (this.a < 0) {
                return true;
            }

            this.g.a(0, this.a - this.g.e + 4 - 1, 0);
        }

        this.a(var1, var3, 0, 1, 0, 12, 4, 8, 0, 0, false);
        this.a(var1, var3, 1, 0, 1, 2, 0, 7, OBlock.aA.bO, OBlock.aA.bO, false);
        this.a(var1, var3, 4, 0, 1, 5, 0, 7, OBlock.aA.bO, OBlock.aA.bO, false);
        this.a(var1, var3, 7, 0, 1, 8, 0, 7, OBlock.aA.bO, OBlock.aA.bO, false);
        this.a(var1, var3, 10, 0, 1, 11, 0, 7, OBlock.aA.bO, OBlock.aA.bO, false);
        this.a(var1, var3, 0, 0, 0, 0, 0, 8, OBlock.J.bO, OBlock.J.bO, false);
        this.a(var1, var3, 6, 0, 0, 6, 0, 8, OBlock.J.bO, OBlock.J.bO, false);
        this.a(var1, var3, 12, 0, 0, 12, 0, 8, OBlock.J.bO, OBlock.J.bO, false);
        this.a(var1, var3, 1, 0, 0, 11, 0, 0, OBlock.J.bO, OBlock.J.bO, false);
        this.a(var1, var3, 1, 0, 8, 11, 0, 8, OBlock.J.bO, OBlock.J.bO, false);
        this.a(var1, var3, 3, 0, 1, 3, 0, 7, OBlock.A.bO, OBlock.A.bO, false);
        this.a(var1, var3, 9, 0, 1, 9, 0, 7, OBlock.A.bO, OBlock.A.bO, false);

        int var4;

        for (var4 = 1; var4 <= 7; ++var4) {
            this.a(var1, OBlock.az.bO, OMathHelper.a(var2, 2, 7), 1, 1, var4, var3);
            this.a(var1, OBlock.az.bO, OMathHelper.a(var2, 2, 7), 2, 1, var4, var3);
            this.a(var1, OBlock.az.bO, OMathHelper.a(var2, 2, 7), 4, 1, var4, var3);
            this.a(var1, OBlock.az.bO, OMathHelper.a(var2, 2, 7), 5, 1, var4, var3);
            this.a(var1, OBlock.az.bO, OMathHelper.a(var2, 2, 7), 7, 1, var4, var3);
            this.a(var1, OBlock.az.bO, OMathHelper.a(var2, 2, 7), 8, 1, var4, var3);
            this.a(var1, OBlock.az.bO, OMathHelper.a(var2, 2, 7), 10, 1, var4, var3);
            this.a(var1, OBlock.az.bO, OMathHelper.a(var2, 2, 7), 11, 1, var4, var3);
        }

        for (var4 = 0; var4 < 9; ++var4) {
            for (int var5 = 0; var5 < 13; ++var5) {
                this.b(var1, var5, 4, var4, var3);
                this.b(var1, OBlock.v.bO, 0, var5, -1, var4, var3);
            }
        }

        return true;
    }
}
