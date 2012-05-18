package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBiomeGenBase;
import net.minecraft.server.OBlock;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenDesertWells;

public class OBiomeGenDesert extends OBiomeGenBase {

    public OBiomeGenDesert(int var1) {
        super(var1);
        this.K.clear();
        this.A = (byte) OBlock.E.bO;
        this.B = (byte) OBlock.E.bO;
        this.I.z = -999;
        this.I.C = 2;
        this.I.E = 50;
        this.I.F = 10;
    }

    public void a(OWorld var1, Random var2, int var3, int var4) {
        super.a(var1, var2, var3, var4);
        if (var2.nextInt(1000) == 0) {
            int var5 = var3 + var2.nextInt(16) + 8;
            int var6 = var4 + var2.nextInt(16) + 8;
            OWorldGenDesertWells var7 = new OWorldGenDesertWells();
            var7.a(var1, var2, var5, var1.e(var5, var6) + 1, var6);
        }

    }
}
