package net.minecraft.server;


import net.minecraft.server.OBlock;
import net.minecraft.server.OItemBlock;
import net.minecraft.server.OItemStack;


public class OItemColored extends OItemBlock {

    private final OBlock a;
    private String[] b;

    public OItemColored(int var1, boolean var2) {
        super(var1);
        this.a = OBlock.m[this.a()];
        if (var2) {
            this.f(0);
            this.a(true);
        }

    }

    @Override
    public int a(int var1) {
        return var1;
    }

    public OItemColored a(String[] var1) {
        this.b = var1;
        return this;
    }

    @Override
    public String a(OItemStack var1) {
        if (this.b == null) {
            return super.a(var1);
        } else {
            int var2 = var1.h();

            return var2 >= 0 && var2 < this.b.length ? super.a(var1) + "." + this.b[var2] : super.a(var1);
        }
    }
}
