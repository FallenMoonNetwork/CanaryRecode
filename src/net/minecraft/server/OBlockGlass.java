package net.minecraft.server;

import java.util.Random;
import net.minecraft.server.OBlockBreakable;
import net.minecraft.server.OMaterial;

public class OBlockGlass extends OBlockBreakable {

    public OBlockGlass(int var1, int var2, OMaterial var3, boolean var4) {
        super(var1, var2, var3, var4);
    }

    @Override
    public int a(Random var1) {
        return 0;
    }

    @Override
    public boolean a() {
        return false;
    }

    @Override
    public boolean b() {
        return false;
    }

    @Override
    protected boolean h() {
        return true;
    }
}
