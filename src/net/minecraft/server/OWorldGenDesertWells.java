package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenerator;

public class OWorldGenDesertWells extends OWorldGenerator {

    public OWorldGenDesertWells() {
        super();
    }

    public boolean a(OWorld var1, Random var2, int var3, int var4, int var5) {
        while (var1.g(var3, var4, var5) && var4 > 2) {
            --var4;
        }

        int var6 = var1.a(var3, var4, var5);
        if (var6 != OBlock.E.bO) {
            return false;
        } else {
            int var7;
            int var8;
            for (var7 = -2; var7 <= 2; ++var7) {
                for (var8 = -2; var8 <= 2; ++var8) {
                    if (var1.g(var3 + var7, var4 - 1, var5 + var8) && var1.g(var3 + var7, var4 - 2, var5 + var8)) {
                        return false;
                    }
                }
            }

            for (var7 = -1; var7 <= 0; ++var7) {
                for (var8 = -2; var8 <= 2; ++var8) {
                    for (int var9 = -2; var9 <= 2; ++var9) {
                        var1.b(var3 + var8, var4 + var7, var5 + var9, OBlock.Q.bO);
                    }
                }
            }

            var1.b(var3, var4, var5, OBlock.A.bO);
            var1.b(var3 - 1, var4, var5, OBlock.A.bO);
            var1.b(var3 + 1, var4, var5, OBlock.A.bO);
            var1.b(var3, var4, var5 - 1, OBlock.A.bO);
            var1.b(var3, var4, var5 + 1, OBlock.A.bO);

            for (var7 = -2; var7 <= 2; ++var7) {
                for (var8 = -2; var8 <= 2; ++var8) {
                    if (var7 == -2 || var7 == 2 || var8 == -2 || var8 == 2) {
                        var1.b(var3 + var7, var4 + 1, var5 + var8, OBlock.Q.bO);
                    }
                }
            }

            var1.a(var3 + 2, var4 + 1, var5, OBlock.ak.bO, 1);
            var1.a(var3 - 2, var4 + 1, var5, OBlock.ak.bO, 1);
            var1.a(var3, var4 + 1, var5 + 2, OBlock.ak.bO, 1);
            var1.a(var3, var4 + 1, var5 - 2, OBlock.ak.bO, 1);

            for (var7 = -1; var7 <= 1; ++var7) {
                for (var8 = -1; var8 <= 1; ++var8) {
                    if (var7 == 0 && var8 == 0) {
                        var1.b(var3 + var7, var4 + 4, var5 + var8, OBlock.Q.bO);
                    } else {
                        var1.a(var3 + var7, var4 + 4, var5 + var8, OBlock.ak.bO, 1);
                    }
                }
            }

            for (var7 = 1; var7 <= 3; ++var7) {
                var1.b(var3 - 1, var4 + var7, var5 - 1, OBlock.Q.bO);
                var1.b(var3 - 1, var4 + var7, var5 + 1, OBlock.Q.bO);
                var1.b(var3 + 1, var4 + var7, var5 - 1, OBlock.Q.bO);
                var1.b(var3 + 1, var4 + var7, var5 + 1, OBlock.Q.bO);
            }

            return true;
        }
    }
}
