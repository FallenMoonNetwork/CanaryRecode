package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OComponentVillage;
import net.minecraft.server.OStructureBoundingBox;
import net.minecraft.server.OStructureComponent;
import net.minecraft.server.OWorld;

public class OComponentVillageTorch extends OComponentVillage {

    private int a = -1;

    public OComponentVillageTorch(int var1, Random var2, OStructureBoundingBox var3, int var4) {
        super(var1);
        this.h = var4;
        this.g = var3;
    }

    public void a(OStructureComponent var1, List var2, Random var3) {
    }

    public static OStructureBoundingBox a(List var0, Random var1, int var2, int var3, int var4, int var5) {
        OStructureBoundingBox var6 = OStructureBoundingBox.a(var2, var3, var4, 0, 0, 0, 3, 4, 2, var5);
        return OStructureComponent.a(var0, var6) != null ? null : var6;
    }

    public boolean a(OWorld var1, Random var2, OStructureBoundingBox var3) {
        if (this.a < 0) {
            this.a = this.b(var1, var3);
            if (this.a < 0) {
                return true;
            }

            this.g.a(0, this.a - this.g.e + 4 - 1, 0);
        }

        this.a(var1, var3, 0, 0, 0, 2, 3, 1, 0, 0, false);
        this.a(var1, OBlock.aZ.bO, 0, 1, 0, 0, var3);
        this.a(var1, OBlock.aZ.bO, 0, 1, 1, 0, var3);
        this.a(var1, OBlock.aZ.bO, 0, 1, 2, 0, var3);
        this.a(var1, OBlock.ab.bO, 15, 1, 3, 0, var3);
        this.a(var1, OBlock.aq.bO, 15, 0, 3, 0, var3);
        this.a(var1, OBlock.aq.bO, 15, 1, 3, 1, var3);
        this.a(var1, OBlock.aq.bO, 15, 2, 3, 0, var3);
        this.a(var1, OBlock.aq.bO, 15, 1, 3, -1, var3);
        return true;
    }
}
