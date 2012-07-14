package net.minecraft.server;

import net.minecraft.server.OAchievementList;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OSlot;

public class OSlotCrafting extends OSlot {

    private final OIInventory a;
    private OEntityPlayer f;
    private int g;

    public OSlotCrafting(OEntityPlayer var1, OIInventory var2, OIInventory var3, int var4, int var5, int var6) {
        super(var3, var4, var5, var6);
        this.f = var1;
        this.a = var2;
    }

    @Override
    public boolean a(OItemStack var1) {
        return false;
    }

    @Override
    public OItemStack a(int var1) {
        if (this.c()) {
            this.g += Math.min(var1, this.b().a);
        }

        return super.a(var1);
    }

    @Override
    protected void a(OItemStack var1, int var2) {
        this.g += var2;
        this.b(var1);
    }

    @Override
    protected void b(OItemStack var1) {
        var1.a(this.f.bi, this.f, this.g);
        this.g = 0;
        if (var1.c == OBlock.ay.bO) {
            this.f.a(OAchievementList.h, 1);
        } else if (var1.c == OItem.s.bP) {
            this.f.a(OAchievementList.i, 1);
        } else if (var1.c == OBlock.aB.bO) {
            this.f.a(OAchievementList.j, 1);
        } else if (var1.c == OItem.M.bP) {
            this.f.a(OAchievementList.l, 1);
        } else if (var1.c == OItem.T.bP) {
            this.f.a(OAchievementList.m, 1);
        } else if (var1.c == OItem.aY.bP) {
            this.f.a(OAchievementList.n, 1);
        } else if (var1.c == OItem.w.bP) {
            this.f.a(OAchievementList.o, 1);
        } else if (var1.c == OItem.q.bP) {
            this.f.a(OAchievementList.r, 1);
        } else if (var1.c == OBlock.bE.bO) {
            this.f.a(OAchievementList.D, 1);
        } else if (var1.c == OBlock.an.bO) {
            this.f.a(OAchievementList.F, 1);
        }

    }

    @Override
    public void c(OItemStack var1) {
        this.b(var1);
        for (int var2 = 0; var2 < this.a.getInventorySize(); ++var2) {
            OItemStack var3 = this.a.getSlot(var2); //CanaryMod fixed deobfuscation failure
            if (var3 != null) {
                
                this.a.a(var2, 1); //Decrease item stack size
                
                if (var3.a().k()) {
                    OItemStack var4 = new OItemStack(var3.a().j());
                    if (!var3.a().e(var3) || !this.f.k.a(var4)) {
                        if (this.a.getSlot(var2) == null) {
                            this.a.a(var2, var4); //CanaryMod fixed deobfuscation failure
                        } else {
                            this.f.b(var4);
                        }
                    }
                }
            }
        }

    }
}
