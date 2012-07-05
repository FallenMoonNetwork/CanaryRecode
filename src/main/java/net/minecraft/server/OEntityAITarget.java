package net.minecraft.server;

import net.canarymod.Canary;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.entity.MobTargetHook;
import net.minecraft.server.OEntityAIBase;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityTameable;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OPathEntity;
import net.minecraft.server.OPathPoint;

public abstract class OEntityAITarget extends OEntityAIBase {

    protected OEntityLiving c;
    protected float d;
    protected boolean e;
    private boolean a;
    private int b;
    private int f;
    private int g;

    public OEntityAITarget(OEntityLiving var1, float var2, boolean var3) {
        this(var1, var2, var3, false);
    }

    public OEntityAITarget(OEntityLiving var1, float var2, boolean var3, boolean var4) {
        super();
        this.b = 0;
        this.f = 0;
        this.g = 0;
        this.c = var1;
        this.d = var2;
        this.e = var3;
        this.a = var4;
    }

    @Override
    public boolean b() {
        OEntityLiving var1 = this.c.at();
        if (var1 == null) {
            return false;
        } else if (!var1.aE()) {
            return false;
        } else if (this.c.j(var1) > (this.d * this.d)) {
            return false;
        } else {
            if (this.e) {
                if (!this.c.am().a(var1)) {
                    if (++this.g > 60) {
                        return false;
                    }
                } else {
                    this.g = 0;
                }
            }

            return true;
        }
    }

    @Override
    public void c() {
        this.b = 0;
        this.f = 0;
        this.g = 0;
    }

    @Override
    public void d() {
        this.c.b((OEntityLiving) null);
    }

    protected boolean a(OEntityLiving var1, boolean var2) {
        if (var1 == null) {
            return false;
        } else if (var1 == this.c) {
            return false;
        } else if (!var1.aE()) {
            return false;
        } else if (var1.bw.e > this.c.bw.b && var1.bw.b < this.c.bw.e) {
            if (!this.c.a(var1.getClass())) {
                return false;
            } else {
                if (this.c instanceof OEntityTameable && ((OEntityTameable) this.c).u_()) {
                    if (var1 instanceof OEntityTameable && ((OEntityTameable) var1).u_()) {
                        return false;
                    }

                    if (var1 == ((OEntityTameable) this.c).w_()) {
                        return false;
                    }
                } else if (var1 instanceof OEntityPlayer && !var2 && ((OEntityPlayer) var1).L.a) {
                    return false;
                }

                if (!this.c.e(OMathHelper.b(var1.bm), OMathHelper.b(var1.bn), OMathHelper.b(var1.bo))) {
                    return false;
                } else if (this.e && !this.c.am().a(var1)) {
                    return false;
                } else {
                    if (this.a) {
                        if (--this.f <= 0) {
                            this.b = 0;
                        }

                        if (this.b == 0) {
                            this.b = this.a(var1) ? 1 : 2;
                        }

                        if (this.b == 2) {
                            return false;
                        }
                    }
                    
                    //CanaryMod - MOB_TARGET
                    if (var1.getCanaryEntityLiving().isPlayer()) {
                        MobTargetHook hook = new MobTargetHook(this.c.getCanaryEntityLiving(), var1.getCanaryEntityLiving().getPlayer());
                        Canary.hooks().callHook(hook);
                        if (hook.isCanceled()) {
                            return false;
                        }
                    }
                    //CanaryMod end

                    return true;
                }
            }
        } else {
            return false;
        }
    }

    private boolean a(OEntityLiving var1) {
        this.f = 10 + this.c.an().nextInt(5);
        OPathEntity var2 = this.c.al().a(var1);
        if (var2 == null) {
            return false;
        } else {
            OPathPoint var3 = var2.c();
            if (var3 == null) {
                return false;
            } else {
                int var4 = var3.a - OMathHelper.b(var1.bm);
                int var5 = var3.c - OMathHelper.b(var1.bo);
                return (var4 * var4 + var5 * var5) <= 2.25D;
            }
        }
    }
}
