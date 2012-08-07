package net.minecraft.server;


import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.server.OWeightedRandomChoice;


public class OWeightedRandom {

    public OWeightedRandom() {
        super();
    }

    public static int a(Collection var0) {
        int var1 = 0;

        OWeightedRandomChoice var3;

        for (Iterator var2 = var0.iterator(); var2.hasNext(); var1 += var3.d) {
            var3 = (OWeightedRandomChoice) var2.next();
        }

        return var1;
    }

    public static OWeightedRandomChoice a(Random var0, Collection var1, int var2) {
        if (var2 <= 0) {
            throw new IllegalArgumentException();
        } else {
            int var3 = var0.nextInt(var2);
            Iterator var4 = var1.iterator();

            OWeightedRandomChoice var5;

            do {
                if (!var4.hasNext()) {
                    return null;
                }

                var5 = (OWeightedRandomChoice) var4.next();
                var3 -= var5.d;
            } while (var3 >= 0);

            return var5;
        }
    }

    public static OWeightedRandomChoice a(Random var0, Collection var1) {
        return a(var0, var1, a(var1));
    }

    public static int a(OWeightedRandomChoice[] var0) {
        int var1 = 0;
        OWeightedRandomChoice[] var2 = var0;
        int var3 = var0.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            OWeightedRandomChoice var5 = var2[var4];

            var1 += var5.d;
        }

        return var1;
    }

    public static OWeightedRandomChoice a(Random var0, OWeightedRandomChoice[] var1, int var2) {
        if (var2 <= 0) {
            throw new IllegalArgumentException();
        } else {
            int var3 = var0.nextInt(var2);
            OWeightedRandomChoice[] var4 = var1;
            int var5 = var1.length;

            for (int var6 = 0; var6 < var5; ++var6) {
                OWeightedRandomChoice var7 = var4[var6];

                var3 -= var7.d;
                if (var3 < 0) {
                    return var7;
                }
            }

            return null;
        }
    }

    public static OWeightedRandomChoice a(Random var0, OWeightedRandomChoice[] var1) {
        return a(var0, var1, a(var1));
    }
}
