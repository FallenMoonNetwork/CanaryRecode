package net.minecraft.server;

import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemFood;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OWorld;

public class OItemSoup extends OItemFood {

    public OItemSoup(int var1, int var2) {
        super(var1, var2, false);
        this.e(1);
    }

    public OItemStack b(OItemStack var1, OWorld var2, OEntityPlayer var3) {
        super.b(var1, var2, var3);
        return new OItemStack(OItem.D);
    }
}
