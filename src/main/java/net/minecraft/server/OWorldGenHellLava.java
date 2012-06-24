package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlock;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldGenerator;

public class OWorldGenHellLava extends OWorldGenerator {

    private int a;

    public OWorldGenHellLava(int var1) {
        super();
        this.a = var1;
    }

    @Override
    public boolean a(OWorld var1, Random var2, int var3, int var4, int var5) {
        if (var1.a(var3, var4 + 1, var5) != OBlock.bb.bO) {
            return false;
        } else if (var1.a(var3, var4, var5) != 0 && var1.a(var3, var4, var5) != OBlock.bb.bO) {
            return false;
        } else {
            int var6 = 0;
            if (var1.a(var3 - 1, var4, var5) == OBlock.bb.bO) {
                ++var6;
            }

            if (var1.a(var3 + 1, var4, var5) == OBlock.bb.bO) {
                ++var6;
            }

            if (var1.a(var3, var4, var5 - 1) == OBlock.bb.bO) {
                ++var6;
            }

            if (var1.a(var3, var4, var5 + 1) == OBlock.bb.bO) {
                ++var6;
            }

            if (var1.a(var3, var4 - 1, var5) == OBlock.bb.bO) {
                ++var6;
            }

            int var7 = 0;
            if (var1.g(var3 - 1, var4, var5)) {
                ++var7;
            }

            if (var1.g(var3 + 1, var4, var5)) {
                ++var7;
            }

            if (var1.g(var3, var4, var5 - 1)) {
                ++var7;
            }

            if (var1.g(var3, var4, var5 + 1)) {
                ++var7;
            }

            if (var1.g(var3, var4 - 1, var5)) {
                ++var7;
            }

            if (var6 == 4 && var7 == 1) {
                var1.e(var3, var4, var5, this.a);
                var1.a = true;
                OBlock.m[this.a].a(var1, var3, var4, var5, var2);
                var1.a = false;
            }

            return true;
        }
    }
}
