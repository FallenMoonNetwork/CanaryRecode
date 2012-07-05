package net.minecraft.server;

import net.canarymod.Canary;
import net.canarymod.api.inventory.ItemType;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.Hook;
import net.canarymod.hook.player.LeftClickHook;
import net.canarymod.hook.player.RightClickHook;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityPlayerMP;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OPacket53BlockChange;
import net.minecraft.server.OWorld;
import net.minecraft.server.OWorldServer;

public class OItemInWorldManager {

    public OWorld a;
    public OEntityPlayer b;
    private int c = -1;
    private float d = 0.0F;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private int n;

    public OItemInWorldManager(OWorld var1) {
        super();
        this.a = var1;
    }

    public void a(int var1) {
        this.c = var1;
        if (var1 == 0) {
            this.b.L.c = false;
            this.b.L.b = false;
            this.b.L.d = false;
            this.b.L.a = false;
        } else {
            this.b.L.c = true;
            this.b.L.d = true;
            this.b.L.a = true;
        }

        this.b.L();
    }

    public int a() {
        return this.c;
    }

    public boolean b() {
        return this.c == 1;
    }

    public void b(int var1) {
        if (this.c == -1) {
            this.c = var1;
        }

        this.a(this.c);
    }

    public void c() {
        ++this.i;
        if (this.j) {
            int var1 = this.i - this.n;
            int var2 = this.a.a(this.k, this.l, this.m);
            if (var2 != 0) {
                OBlock var3 = OBlock.m[var2];
                float var4 = var3.a(this.b) * (var1 + 1);
                if (var4 >= 1.0F) {
                    this.j = false;
                    this.c(this.k, this.l, this.m);
                }
            } else {
                this.j = false;
            }
        }

    }

    public void a(int var1, int var2, int var3, int var4) {
        if (this.b()) {
            if (!this.a.a((OEntityPlayer) null, var1, var2, var3, var4)) {
                this.c(var1, var2, var3);
            }

        } else {
            this.a.a((OEntityPlayer) null, var1, var2, var3, var4);
            this.e = this.i;
            int var5 = this.a.a(var1, var2, var3);
            if (var5 > 0) {
                OBlock.m[var5].b(this.a, var1, var2, var3, this.b);
            }

            if (var5 > 0 && OBlock.m[var5].a(this.b) >= 1.0F) {
                this.c(var1, var2, var3);
            } else {
                this.f = var1;
                this.g = var2;
                this.h = var3;
            }

        }
    }

    public void a(int var1, int var2, int var3) {
        if (var1 == this.f && var2 == this.g && var3 == this.h) {
            int var4 = this.i - this.e;
            int var5 = this.a.a(var1, var2, var3);
            if (var5 != 0) {
                OBlock var6 = OBlock.m[var5];
                float var7 = var6.a(this.b) * (var4 + 1);
                if (var7 >= 0.7F) {
                    this.c(var1, var2, var3);
                } else if (!this.j) {
                    this.j = true;
                    this.k = var1;
                    this.l = var2;
                    this.m = var3;
                    this.n = this.e;
                }
            }
        }

        this.d = 0.0F;
    }

    public boolean b(int var1, int var2, int var3) {
        OBlock var4 = OBlock.m[this.a.a(var1, var2, var3)];
        int var5 = this.a.c(var1, var2, var3);
        boolean var6 = this.a.e(var1, var2, var3, 0);
        if (var4 != null && var6) {
            var4.c(this.a, var1, var2, var3, var5);
        }

        return var6;
    }

    public boolean c(int var1, int var2, int var3) {
        // CanaryMod - Cancel block breaking.
        Block block = ((OEntityPlayerMP)b).getDimension().getBlockAt(var1, var2, var3);
        block.setStatus(1);// Block break status.
        LeftClickHook hook = new LeftClickHook(((OEntityPlayerMP)b).getPlayer(), block);
        Canary.hooks().callHook(hook);
        if (hook.isCanceled()) {
            return true;
        }
        // CanaryMod - end.
        int var4 = this.a.a(var1, var2, var3);
        int var5 = this.a.c(var1, var2, var3);
        this.a.a(this.b, 2001, var1, var2, var3, var4 + (this.a.c(var1, var2, var3) << 12));
        boolean var6 = this.b(var1, var2, var3);
        if (this.b()) {
            ((OEntityPlayerMP) this.b).a.b((new OPacket53BlockChange(var1, var2, var3, this.a)));
        } else {
            OItemStack var7 = this.b.U();
            boolean var8 = this.b.b(OBlock.m[var4]);
            if (var7 != null) {
                var7.a(var4, var1, var2, var3, this.b);
                if (var7.a == 0) {
                    var7.a(this.b);
                    this.b.V();
                }
            }

            if (var6 && var8) {
                OBlock.m[var4].a(this.a, this.b, var1, var2, var3, var5);
            }
        }

        return var6;
    }

    public boolean itemUsed(OEntityPlayer player, OWorld world, OItemStack item, Block blockplaced, Block blockclicked) {
        // CanaryMod: only call this hook if we're not using buckets/signs
        if (item != null) {
            if (item.a > 0 && item.c != ItemType.Sign.getId() && item.c != ItemType.Bucket.getId() && item.c != ItemType.WaterBucket.getId() && item.c != ItemType.LavaBucket.getId() && item.c != ItemType.MilkBucket.getId()) {
                if (player instanceof OEntityPlayerMP){
                    RightClickHook hook = new RightClickHook(((OEntityPlayerMP) player).getPlayer(), blockplaced, blockclicked, item.getCanaryItem(), null, Hook.Type.ITEM_USE);
                    Canary.hooks().callHook(hook);
                    if(hook.isCanceled()) {
                        return false;
                    }
                }
            }
        }
        return this.a(player, world, item);
    }
    
    public boolean a(OEntityPlayer var1, OWorld var2, OItemStack var3) {
        int var4 = var3.a;
        int var5 = var3.h();
        OItemStack var6 = var3.a(var2, var1);
        if(var6 == var3 && (var6 == null || var6.a == var4) && (var6 == null || var6.l() <= 0)) {
           return false;
        } else {
           var1.k.a[var1.k.c] = var6;
           if(this.b()) {
              var6.a = var4;
              var6.b(var5);
           }

           if(var6.a == 0) {
              var1.k.a[var1.k.c] = null;
           }

           return true;
        }
    }

    public boolean a(OEntityPlayer var1, OWorld var2, OItemStack var3, int var4, int var5, int var6, int var7) {
        int var8 = var2.a(var4, var5, var6);
        if (var8 > 0 && OBlock.m[var8].a(var2, var4, var5, var6, var1)) {
            return true;
        } else if (var3 == null) {
            return false;
        } else if (this.b()) {
            int var9 = var3.h();
            int var10 = var3.a;
            boolean var11 = var3.a(var1, var2, var4, var5, var6, var7);
            var3.b(var9);
            var3.a = var10;
            return var11;
        } else {
            return var3.a(var1, var2, var4, var5, var6, var7);
        }
    }

    public void a(OWorldServer var1) {
        this.a = var1;
    }
}
