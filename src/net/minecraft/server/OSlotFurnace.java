package net.minecraft.server;

import net.minecraft.server.OAchievementList;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OSlot;

public class OSlotFurnace extends OSlot {

    private OEntityPlayer a;
    private int f;

    public OSlotFurnace(OEntityPlayer var1, OIInventory var2, int var3, int var4, int var5) {
        super(var2, var3, var4, var5);
        this.a = var1;
    }

    @Override
    public boolean a(OItemStack var1) {
        return false;
    }

    @Override
    public OItemStack a(int var1) {
        if (this.c()) {
            this.f += Math.min(var1, this.b().a);
        }

        return super.a(var1);
    }

    @Override
    public void c(OItemStack var1) {
        this.b(var1);
        super.c(var1);
    }

    @Override
    protected void a(OItemStack var1, int var2) {
        this.f += var2;
        this.b(var1);
    }

    @Override
    protected void b(OItemStack var1) {
        var1.a(this.a.bi, this.a, this.f);
        this.f = 0;
        if (var1.c == OItem.n.bP) {
            this.a.a(OAchievementList.k, 1);
        }

        if (var1.c == OItem.aU.bP) {
            this.a.a(OAchievementList.p, 1);
        }

    }
}
