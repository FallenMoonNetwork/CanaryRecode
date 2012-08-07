package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OMaterial;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenerator;

public class OWorldGenReed extends OWorldGenerator {

    public OWorldGenReed() {
        super();
    }

    @Override
    public boolean a(OWorld var1, Random var2, int var3, int var4, int var5) {
        for (int var6 = 0; var6 < 20; ++var6) {
            int var7 = var3 + var2.nextInt(4) - var2.nextInt(4);
            int var8 = var4;
            int var9 = var5 + var2.nextInt(4) - var2.nextInt(4);
            if (var1.g(var7, var4, var9) && (var1.d(var7 - 1, var4 - 1, var9) == OMaterial.g || var1.d(var7 + 1, var4 - 1, var9) == OMaterial.g || var1.d(var7, var4 - 1, var9 - 1) == OMaterial.g || var1.d(var7, var4 - 1, var9 + 1) == OMaterial.g)) {
                int var10 = 2 + var2.nextInt(var2.nextInt(3) + 1);

                for (int var11 = 0; var11 < var10; ++var11) {
                    if (OBlock.aX.f(var1, var7, var8 + var11, var9)) {
                        var1.b(var7, var8 + var11, var9, OBlock.aX.bO);
                    }
                }
            }
        }

        return true;
    }
}
