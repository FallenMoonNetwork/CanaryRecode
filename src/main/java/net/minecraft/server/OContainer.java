package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.canarymod.Canary;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.hook.player.InventoryHook;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OICrafting;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OInventoryPlayer;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OSlot;

public abstract class OContainer {

    public List d = new ArrayList();
    public List e = new ArrayList();
    public int f = 0;
    private short a = 0;
    protected List g = new ArrayList();
    private Set b = new HashSet();
    
    //CanaryMod: inventory - Used to know which inventory was passed to this container GUI.
    private Inventory inventory;

    public OContainer() {
        super();
    }

    protected void a(OSlot var1) {
        var1.c = this.e.size();
        this.e.add(var1);
        this.d.add((Object) null);
    }

    public void a(OICrafting var1) {
        if (this.g.contains(var1)) {
            throw new IllegalArgumentException("Listener already listening");
        } else {
            this.g.add(var1);
            var1.a(this, this.b());
            this.a();
        }
    }

    public List b() {
        ArrayList var1 = new ArrayList();

        for (int var2 = 0; var2 < this.e.size(); ++var2) {
            var1.add(((OSlot) this.e.get(var2)).b());
        }

        return var1;
    }

    public void a() {
        for (int var1 = 0; var1 < this.e.size(); ++var1) {
            OItemStack var2 = ((OSlot) this.e.get(var1)).b();
            OItemStack var3 = (OItemStack) this.d.get(var1);
            if (!OItemStack.b(var3, var2)) {
                var3 = var2 == null ? null : var2.j();
                this.d.set(var1, var3);

                for (int var4 = 0; var4 < this.g.size(); ++var4) {
                    ((OICrafting) this.g.get(var4)).a(this, var1, var3);
                }
            }
        }

    }

    public boolean a(OEntityPlayer var1, int var2) {
        return false;
    }

    public OSlot a(OIInventory var1, int var2) {
        for (int var3 = 0; var3 < this.e.size(); ++var3) {
            OSlot var4 = (OSlot) this.e.get(var3);
            if (var4.a(var1, var2)) {
                return var4;
            }
        }

        return null;
    }

    public OSlot b(int var1) {
        return (OSlot) this.e.get(var1);
    }

    public OItemStack a(int var1) {
        OSlot var2 = (OSlot) this.e.get(var1);
        return var2 != null ? var2.b() : null;
    }

    public OItemStack a(int var1, int var2, boolean var3, OEntityPlayer var4) {
        OItemStack var5 = null;
        if (var2 > 1) {
            return null;
        } else {
            if (var2 == 0 || var2 == 1) {
                OInventoryPlayer var6 = var4.k;
                if (var1 == -999) {
                    if (var6.l() != null && var1 == -999) {
                        if (var2 == 0) {
                            var4.b(var6.l());
                            var6.b((OItemStack) null);
                        }

                        if (var2 == 1) {
                            var4.b(var6.l().a(1));
                            if (var6.l().a == 0) {
                                var6.b((OItemStack) null);
                            }
                        }
                    }
                } else if (var3) {
                    OItemStack var7 = this.a(var1);
                    if (var7 != null) {
                        int var8 = var7.c;
                        var5 = var7.j();
                        OSlot var9 = (OSlot) this.e.get(var1);
                        if (var9 != null && var9.b() != null && var9.b().c == var8) {
                            this.b(var1, var2, var3, var4);
                        }
                    }
                } else {
                    if (var1 < 0) {
                        return null;
                    }

                    OSlot var12 = (OSlot) this.e.get(var1);
                    if (var12 != null) {
                        var12.d();
                        OItemStack var13 = var12.b();
                        OItemStack var14 = var6.l();
                        if (var13 != null) {
                            var5 = var13.j();
                        }

                        int var10;
                        if (var13 == null) {
                            if (var14 != null && var12.a(var14)) {
                                var10 = var2 == 0 ? var14.a : 1;
                                if (var10 > var12.a()) {
                                    var10 = var12.a();
                                }

                                var12.d(var14.a(var10));
                                if (var14.a == 0) {
                                    var6.b((OItemStack) null);
                                }
                            }
                        } else if (var14 == null) {
                            var10 = var2 == 0 ? var13.a : (var13.a + 1) / 2;
                            OItemStack var11 = var12.a(var10);
                            var6.b(var11);
                            if (var13.a == 0) {
                                var12.d((OItemStack) null);
                            }

                            var12.c(var6.l());
                        } else if (var12.a(var14)) {
                            if (var13.c == var14.c && (!var13.e() || var13.h() == var14.h()) && OItemStack.a(var13, var14)) {
                                var10 = var2 == 0 ? var14.a : 1;
                                if (var10 > var12.a() - var13.a) {
                                    var10 = var12.a() - var13.a;
                                }

                                if (var10 > var14.b() - var13.a) {
                                    var10 = var14.b() - var13.a;
                                }

                                var14.a(var10);
                                if (var14.a == 0) {
                                    var6.b((OItemStack) null);
                                }

                                var13.a += var10;
                            } else if (var14.a <= var12.a()) {
                                var12.d(var14);
                                var6.b(var13);
                            }
                        } else if (var13.c == var14.c && var14.b() > 1 && (!var13.e() || var13.h() == var14.h()) && OItemStack.a(var13, var14)) {
                            var10 = var13.a;
                            if (var10 > 0 && var10 + var14.a <= var14.b()) {
                                var14.a += var10;
                                var13 = var12.a(var10);
                                if (var13.a == 0) {
                                    var12.d((OItemStack) null);
                                }

                                var12.c(var6.l());
                            }
                        }
                    }
                }
            }

            return var5;
        }
    }

    protected void b(int var1, int var2, boolean var3, OEntityPlayer var4) {
        this.a(var1, var2, var3, var4);
    }

    public void onInventoryClose(OEntityPlayer var1) {
        Canary.hooks().callHook(new InventoryHook(((OEntityPlayerMP)var1).getPlayer(), this.inventory, true)); //CanaryMod - onCloseInventory
        OInventoryPlayer var2 = var1.k;
        if (var2.l() != null) {
            var1.b(var2.l());
            var2.b((OItemStack) null);
        }

    }

    public void a(OIInventory var1) {
        this.a();
    }

    public void a(int var1, OItemStack var2) {
        this.b(var1).d(var2);
    }

    public boolean c(OEntityPlayer var1) {
        return !this.b.contains(var1);
    }

    public void a(OEntityPlayer var1, boolean var2) {
        if (var2) {
            this.b.remove(var1);
        } else {
            this.b.add(var1);
        }

    }

    public abstract boolean b(OEntityPlayer var1);

    protected boolean a(OItemStack var1, int var2, int var3, boolean var4) {
        boolean var5 = false;
        int var6 = var2;
        if (var4) {
            var6 = var3 - 1;
        }

        OSlot var7;
        OItemStack var8;
        if (var1.c()) {
            while (var1.a > 0 && (!var4 && var6 < var3 || var4 && var6 >= var2)) {
                var7 = (OSlot) this.e.get(var6);
                var8 = var7.b();
                if (var8 != null && var8.c == var1.c && (!var1.e() || var1.h() == var8.h()) && OItemStack.a(var1, var8)) {
                    int var9 = var8.a + var1.a;
                    if (var9 <= var1.b()) {
                        var1.a = 0;
                        var8.a = var9;
                        var7.d();
                        var5 = true;
                    } else if (var8.a < var1.b()) {
                        var1.a -= var1.b() - var8.a;
                        var8.a = var1.b();
                        var7.d();
                        var5 = true;
                    }
                }

                if (var4) {
                    --var6;
                } else {
                    ++var6;
                }
            }
        }

        if (var1.a > 0) {
            if (var4) {
                var6 = var3 - 1;
            } else {
                var6 = var2;
            }

            while (!var4 && var6 < var3 || var4 && var6 >= var2) {
                var7 = (OSlot) this.e.get(var6);
                var8 = var7.b();
                if (var8 == null) {
                    var7.d(var1.j());
                    var7.d();
                    var1.a = 0;
                    var5 = true;
                    break;
                }

                if (var4) {
                    --var6;
                } else {
                    ++var6;
                }
            }
        }

        return var5;
    }
    
    // CanaryMod: get and set inventory passed to the GUI.
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return this.inventory;
    }
}
