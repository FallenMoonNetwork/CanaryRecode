package net.minecraft.server;


import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.ODirection;
import net.minecraft.server.OFacing;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenerator;


public class OWorldGenVines extends OWorldGenerator {

    public OWorldGenVines() {
        super();
    }

    @Override
    public boolean a(OWorld var1, Random var2, int var3, int var4, int var5) {
        int var6 = var3;

        for (int var7 = var5; var4 < 128; ++var4) {
            if (var1.g(var3, var4, var5)) {
                for (int var8 = 2; var8 <= 5; ++var8) {
                    if (OBlock.bu.b(var1, var3, var4, var5, var8)) {
                        var1.a(var3, var4, var5, OBlock.bu.bO, 1 << ODirection.d[OFacing.a[var8]]);
                        break;
                    }
                }
            } else {
                var3 = var6 + var2.nextInt(4) - var2.nextInt(4);
                var5 = var7 + var2.nextInt(4) - var2.nextInt(4);
            }
        }

        return true;
    }
}
