package net.minecraft.server;


import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenerator;


public class OWorldGenWaterlily extends OWorldGenerator {

    public OWorldGenWaterlily() {
        super();
    }

    @Override
    public boolean a(OWorld var1, Random var2, int var3, int var4, int var5) {
        for (int var6 = 0; var6 < 10; ++var6) {
            int var7 = var3 + var2.nextInt(8) - var2.nextInt(8);
            int var8 = var4 + var2.nextInt(4) - var2.nextInt(4);
            int var9 = var5 + var2.nextInt(8) - var2.nextInt(8);

            if (var1.g(var7, var8, var9) && OBlock.bz.c(var1, var7, var8, var9)) {
                var1.b(var7, var8, var9, OBlock.bz.bO);
            }
        }

        return true;
    }
}
