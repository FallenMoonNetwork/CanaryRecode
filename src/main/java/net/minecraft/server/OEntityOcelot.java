package net.minecraft.server;

import net.canarymod.api.entity.CanaryOcelot;
import net.minecraft.server.OBlock;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAIAvoidEntity;
import net.minecraft.server.OEntityAIFollowOwner;
import net.minecraft.server.OEntityAILeapAtTarget;
import net.minecraft.server.OEntityAIMate;
import net.minecraft.server.OEntityAIOcelotAttack;
import net.minecraft.server.OEntityAIOcelotSit;
import net.minecraft.server.OEntityAISwimming;
import net.minecraft.server.OEntityAITargetNonTamed;
import net.minecraft.server.OEntityAITempt;
import net.minecraft.server.OEntityAIWander;
import net.minecraft.server.OEntityAIWatchClosest;
import net.minecraft.server.OEntityAnimal;
import net.minecraft.server.OEntityChicken;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityTameable;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public class OEntityOcelot extends OEntityTameable {

    private OEntityAITempt b;
    //CanaryMod ocelot handler
    private CanaryOcelot canaryOcelot;

    public OEntityOcelot(OWorld var1) {
        super(var1);
        this.ae = "/mob/ozelot.png";
        this.b(0.6F, 0.8F);
        this.al().a(true);
        this.aL.a(1, new OEntityAISwimming(this));
        this.aL.a(2, this.a);
        this.aL.a(3, this.b = new OEntityAITempt(this, 0.18F, OItem.aT.bP, true));
        this.aL.a(4, new OEntityAIAvoidEntity(this, OEntityPlayer.class, 16.0F, 0.23F, 0.4F));
        this.aL.a(5, new OEntityAIFollowOwner(this, 0.3F, 10.0F, 5.0F));
        this.aL.a(6, new OEntityAIOcelotSit(this, 0.4F));
        this.aL.a(7, new OEntityAILeapAtTarget(this, 0.3F));
        this.aL.a(8, new OEntityAIOcelotAttack(this));
        this.aL.a(9, new OEntityAIMate(this, 0.23F));
        this.aL.a(10, new OEntityAIWander(this, 0.23F));
        this.aL.a(11, new OEntityAIWatchClosest(this, OEntityPlayer.class, 10.0F));
        this.aM.a(1, new OEntityAITargetNonTamed(this, OEntityChicken.class, 14.0F, 750, false));
        
        canaryOcelot = new CanaryOcelot(this);
    }

    /**
     * CanaryMod get the Ocelot handler
     * @return the canaryOcelot
     */
    public CanaryOcelot getCanaryOcelot() {
        return canaryOcelot;
    }

    @Override
    protected void b() {
        super.b();
        this.bY.a(18, Byte.valueOf((byte) 0));
    }

    @Override
    public void g() {
        if (!this.aj().a()) {
            this.g(false);
            this.h(false);
        } else {
            float var1 = this.aj().b();
            if (var1 == 0.18F) {
                this.g(true);
                this.h(false);
            } else if (var1 == 0.4F) {
                this.g(false);
                this.h(true);
            } else {
                this.g(false);
                this.h(false);
            }
        }

    }

    @Override
    protected boolean n() {
        return !this.u_();
    }

    @Override
    public boolean c_() {
        return true;
    }

    @Override
    public int d() {
        return 10;
    }

    @Override
    protected void a(float var1) {
    }

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("CatType", this.r());
    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.c_(var1.f("CatType"));
    }

    @Override
    protected String i() {
        return this.u_() ? (this.r_() ? "mob.cat.purr" : (this.bS.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow")) : "";
    }

    @Override
    protected String j() {
        return "mob.cat.hitt";
    }

    @Override
    protected String k() {
        return "mob.cat.hitt";
    }

    @Override
    protected float p() {
        return 0.4F;
    }

    @Override
    protected int f() {
        return OItem.aE.bP;
    }

    @Override
    public boolean a(OEntity var1) {
        return var1.a(ODamageSource.a(this), 3);
    }

    @Override
    public boolean a(ODamageSource var1, int var2) {
        this.a.a(false);
        return super.a(var1, var2);
    }

    @Override
    protected void a(boolean var1, int var2) {
    }

    @Override
    public boolean interact(OEntityPlayer var1) {
        OItemStack var2 = var1.k.d();
        if (!this.u_()) {
            if (this.b.f() && var2 != null && var2.c == OItem.aT.bP && var1.j(this) < 9.0D) {
                --var2.a;
                if (var2.a <= 0) {
                    var1.k.a(var1.k.c, (OItemStack) null);
                }

                if (!this.bi.F) {
                    if (this.bS.nextInt(3) == 0) {
                        this.b(true);
                        this.c_(1 + this.bi.r.nextInt(3));
                        this.a(var1.v);
                        this.a(true);
                        this.a.a(true);
                        this.bi.a(this, (byte) 7);
                    } else {
                        this.a(false);
                        this.bi.a(this, (byte) 6);
                    }
                }
            }

            return true;
        } else {
            if (var1.v.equalsIgnoreCase(this.A()) && !this.bi.F && !this.a(var2)) {
                this.a.a(!this.v_());
            }

            return super.interact(var1);
        }
    }

    @Override
    public OEntityAnimal a(OEntityAnimal var1) {
        OEntityOcelot var2 = new OEntityOcelot(this.bi);
        if (this.u_()) {
            var2.a(this.A());
            var2.b(true);
            var2.c_(this.r());
        }

        return var2;
    }

    @Override
    public boolean a(OItemStack var1) {
        return var1 != null && var1.c == OItem.aT.bP;
    }

    @Override
    public boolean b(OEntityAnimal var1) {
        if (var1 == this) {
            return false;
        } else if (!this.u_()) {
            return false;
        } else if (!(var1 instanceof OEntityOcelot)) {
            return false;
        } else {
            OEntityOcelot var2 = (OEntityOcelot) var1;
            return !var2.u_() ? false : this.r_() && var2.r_();
        }
    }

    public int r() {
        return this.bY.a(18);
    }

    public void c_(int var1) {
        this.bY.b(18, Byte.valueOf((byte) var1));
    }

    @Override
    public boolean l() {
        if (this.bi.r.nextInt(3) == 0) {
            return false;
        } else {
            if (this.bi.a(this.bw) && this.bi.a(this, this.bw).size() == 0 && !this.bi.c(this.bw)) {
                int var1 = OMathHelper.b(this.bm);
                int var2 = OMathHelper.b(this.bw.b);
                int var3 = OMathHelper.b(this.bo);
                if (var2 < 63) {
                    return false;
                }

                int var4 = this.bi.a(var1, var2 - 1, var3);
                if (var4 == OBlock.u.bO || var4 == OBlock.K.bO) {
                    return true;
                }
            }

            return false;
        }
    }

    @Override
    public String s() {
        return this.u_() ? "entity.Cat.name" : super.s();
    }
}
