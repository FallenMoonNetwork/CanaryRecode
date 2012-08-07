package net.minecraft.server;

import net.minecraft.server.OBlockCloth;
import net.minecraft.server.OItemBlock;
import net.minecraft.server.OItemDye;
import net.minecraft.server.OItemStack;

public class OItemCloth extends OItemBlock {

    public OItemCloth(int var1) {
        super(var1);
        this.f(0);
        this.a(true);
    }

    @Override
    public int a(int var1) {
        return var1;
    }

    @Override
    public String a(OItemStack var1) {
        return super.b() + "." + OItemDye.a[OBlockCloth.d(var1.h())];
    }
}
