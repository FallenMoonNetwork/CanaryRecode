package net.minecraft.server;


import net.minecraft.server.OPotion;


public class OPotionHealth extends OPotion {

    public OPotionHealth(int var1, boolean var2, int var3) {
        super(var1, var2, var3);
    }

    @Override
    public boolean b() {
        return true;
    }

    @Override
    public boolean b(int var1, int var2) {
        return var1 >= 1;
    }
}
