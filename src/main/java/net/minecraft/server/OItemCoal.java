package net.minecraft.server;


import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;


public class OItemCoal extends OItem {

    public OItemCoal(int var1) {
        super(var1);
        this.a(true);
        this.f(0);
    }

    @Override
    public String a(OItemStack var1) {
        return var1.h() == 1 ? "item.charcoal" : "item.coal";
    }
}
