package net.minecraft.server;

import net.canarymod.api.entity.CanaryBaseItem;
import net.canarymod.api.inventory.CanaryItem;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEnchantment;
import net.minecraft.server.OEnchantmentHelper;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumAction;
import net.minecraft.server.OItem;
import net.minecraft.server.ONBTBase;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.ONBTTagList;
import net.minecraft.server.OStatList;
import net.minecraft.server.OWorld;

public final class OItemStack {

    public int a; //stack size
    public int b; //animation ticks
    public int c; //item id
    public ONBTTagCompound d; //enchantment tag
    private int e; //damage

    public OItemStack(OBlock var1) {
        this(var1, 1);
    }

    public OItemStack(OBlock var1, int var2) {
        this(var1.bO, var2, 0);
    }

    public OItemStack(OBlock var1, int var2, int var3) {
        this(var1.bO, var2, var3);
    }

    public OItemStack(OItem var1) {
        this(var1.bP, 1, 0);
    }

    public OItemStack(OItem var1, int var2) {
        this(var1.bP, var2, 0);
    }

    public OItemStack(OItem var1, int var2, int var3) {
        this(var1.bP, var2, var3);
    }

    public OItemStack(int itemId, int amount, int damage) {
        super();
        this.c = itemId;
        this.a = amount;
        this.e = damage;
    }

    public static OItemStack a(ONBTTagCompound var0) {
        OItemStack var1 = new OItemStack();
        var1.c(var0);
        return var1.a() != null ? var1 : null;
    }

    private OItemStack() {
        super();
        this.a = 0;
    }
    
    //CanaryMod start
    /**
     * Get the CanaryMod item handler
     * @return
     */
    public CanaryItem getCanaryItem() {
        return new CanaryItem(this);
    }
   
    public CanaryBaseItem getBaseItem() {
        return this.a().getBaseItem();
    }
    //CanaryMod end

    public OItemStack a(int var1) {
        OItemStack var2 = new OItemStack(this.c, var1, this.e);
        if (this.d != null) {
            var2.d = (ONBTTagCompound) this.d.b();
        }

        this.a -= var1;
        return var2;
    }

    public OItem a() {
        return OItem.d[this.c];
    }

    public boolean a(OEntityPlayer var1, OWorld var2, int var3, int var4, int var5, int var6) {
        boolean var7 = this.a().a(this, var1, var2, var3, var4, var5, var6);
        if (var7) {
            var1.a(OStatList.E[this.c], 1);
        }

        return var7;
    }

    public float a(OBlock var1) {
        return this.a().a(this, var1);
    }

    public OItemStack a(OWorld var1, OEntityPlayer var2) {
        return this.a().a(this, var1, var2);
    }

    public OItemStack b(OWorld var1, OEntityPlayer var2) {
        return this.a().b(this, var1, var2);
    }

    public ONBTTagCompound b(ONBTTagCompound var1) {
        var1.a("id", (short) this.c);
        var1.a("Count", (byte) this.a);
        var1.a("Damage", (short) this.e);
        if (this.d != null) {
            var1.a("tag", (ONBTBase) this.d);
        }

        return var1;
    }

    public void c(ONBTTagCompound var1) {
        this.c = var1.e("id");
        this.a = var1.d("Count");
        this.e = var1.e("Damage");
        if (var1.c("tag")) {
            this.d = var1.m("tag");
        }

    }

    public int b() {
        return this.a().d();
    }

    public boolean c() {
        return this.b() > 1 && (!this.d() || !this.f());
    }

    public boolean d() {
        return OItem.d[this.c].f() > 0;
    }

    public boolean e() {
        return OItem.d[this.c].e();
    }

    public boolean f() {
        return this.d() && this.e > 0;
    }

    public int g() {
        return this.e;
    }

    public int h() {
        return this.e;
    }

    public void b(int var1) {
        this.e = var1;
    }

    public int i() {
        return OItem.d[this.c].f();
    }

    public void a(int var1, OEntityLiving var2) {
        if (this.d()) {
            if (var1 > 0 && var2 instanceof OEntityPlayer) {
                int var3 = OEnchantmentHelper.c(((OEntityPlayer) var2).k);
                if (var3 > 0 && var2.bi.r.nextInt(var3 + 1) > 0) {
                    return;
                }
            }

            this.e += var1;
            if (this.e > this.i()) {
                var2.c(this);
                if (var2 instanceof OEntityPlayer) {
                    ((OEntityPlayer) var2).a(OStatList.F[this.c], 1);
                }

                --this.a;
                if (this.a < 0) {
                    this.a = 0;
                }

                this.e = 0;
            }

        }
    }

    public void a(OEntityLiving var1, OEntityPlayer var2) {
        boolean var3 = OItem.d[this.c].a(this, var1, var2);
        if (var3) {
            var2.a(OStatList.E[this.c], 1);
        }

    }

    public void a(int var1, int var2, int var3, int var4, OEntityPlayer var5) {
        boolean var6 = OItem.d[this.c].a(this, var1, var2, var3, var4, var5);
        if (var6) {
            var5.a(OStatList.E[this.c], 1);
        }

    }

    public int a(OEntity var1) {
        return OItem.d[this.c].a(var1);
    }

    public boolean b(OBlock var1) {
        return OItem.d[this.c].a(var1);
    }

    public void a(OEntityPlayer var1) {
    }

    public void a(OEntityLiving var1) {
        OItem.d[this.c].a(this, var1);
    }

    public OItemStack j() {
        OItemStack var1 = new OItemStack(this.c, this.a, this.e);
        if (this.d != null) {
            var1.d = (ONBTTagCompound) this.d.b();
            if (!var1.d.equals(this.d)) {
                return var1;
            }
        }

        return var1;
    }

    public static boolean a(OItemStack var0, OItemStack var1) {
        return var0 == null && var1 == null ? true : (var0 != null && var1 != null ? (var0.d == null && var1.d != null ? false : var0.d == null || var0.d.equals(var1.d)) : false);
    }

    public static boolean b(OItemStack var0, OItemStack var1) {
        return var0 == null && var1 == null ? true : (var0 != null && var1 != null ? var0.d(var1) : false);
    }

    private boolean d(OItemStack var1) {
        return this.a != var1.a ? false : (this.c != var1.c ? false : (this.e != var1.e ? false : (this.d == null && var1.d != null ? false : this.d == null || this.d.equals(var1.d))));
    }

    public boolean a(OItemStack var1) {
        return this.c == var1.c && this.e == var1.e;
    }

    public String k() {
        return OItem.d[this.c].a(this);
    }

    public static OItemStack b(OItemStack var0) {
        return var0 == null ? null : var0.j();
    }

    @Override
    public String toString() {
        return this.a + "x" + OItem.d[this.c].b() + "@" + this.e;
    }

    public void a(OWorld var1, OEntity var2, int var3, boolean var4) {
        if (this.b > 0) {
            --this.b;
        }

        OItem.d[this.c].a(this, var1, var2, var3, var4);
    }

    public void a(OWorld var1, OEntityPlayer var2, int var3) {
        var2.a(OStatList.D[this.c], var3);
        OItem.d[this.c].d(this, var1, var2);
    }

    public boolean c(OItemStack var1) {
        return this.c == var1.c && this.a == var1.a && this.e == var1.e;
    }

    public int l() {
        return this.a().c(this);
    }

    public OEnumAction m() {
        return this.a().d(this);
    }

    public void b(OWorld var1, OEntityPlayer var2, int var3) {
        this.a().a(this, var1, var2, var3);
    }

    public boolean n() {
        return this.d != null;
    }

    public ONBTTagCompound o() {
        return this.d;
    }

    public ONBTTagList p() {
        return this.d == null ? null : (ONBTTagList) this.d.b("ench");
    }

    public void d(ONBTTagCompound var1) {
        this.d = var1;
    }

    public boolean q() {
        return !this.a().f(this) ? false : !this.r();
    }

    public void a(OEnchantment var1, int var2) {
        if (this.d == null) {
            this.d(new ONBTTagCompound());
        }

        if (!this.d.c("ench")) {
            this.d.a("ench", (new ONBTTagList("ench")));
        }

        ONBTTagList var3 = (ONBTTagList) this.d.b("ench");
        ONBTTagCompound var4 = new ONBTTagCompound();
        var4.a("id", (short) var1.x);
        var4.a("lvl", (short) ((byte) var2));
        var3.a(var4);
    }

    public boolean r() {
        return this.d != null && this.d.c("ench");
    }
}
