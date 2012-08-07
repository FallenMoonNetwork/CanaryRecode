package net.minecraft.server;


import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenerator;


public class OWorldGenPumpkin extends OWorldGenerator {

    public OWorldGenPumpkin() {
        super();
    }

    @Override
    public boolean a(OWorld var1, Random var2, int var3, int var4, int var5) {
        for (int var6 = 0; var6 < 64; ++var6) {
            int var7 = var3 + var2.nextInt(8) - var2.nextInt(8);
            int var8 = var4 + var2.nextInt(4) - var2.nextInt(4);
            int var9 = var5 + var2.nextInt(8) - var2.nextInt(8);

            if (var1.g(var7, var8, var9) && var1.a(var7, var8 - 1, var9) == OBlock.u.bO && OBlock.ba.c(var1, var7, var8, var9)) {
                var1.a(var7, var8, var9, OBlock.ba.bO, var2.nextInt(4));
            }
        }

        return true;
    }
}
