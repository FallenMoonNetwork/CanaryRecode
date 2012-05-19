package net.minecraft.server;

import net.canarymod.api.entity.CanaryPig;
import net.minecraft.server.OAchievementList;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAIFollowParent;
import net.minecraft.server.OEntityAILookIdle;
import net.minecraft.server.OEntityAIMate;
import net.minecraft.server.OEntityAIPanic;
import net.minecraft.server.OEntityAISwimming;
import net.minecraft.server.OEntityAITempt;
import net.minecraft.server.OEntityAIWander;
import net.minecraft.server.OEntityAIWatchClosest;
import net.minecraft.server.OEntityAnimal;
import net.minecraft.server.OEntityLightningBolt;
import net.minecraft.server.OEntityPigZombie;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OStatBase;
import net.minecraft.server.OWorld;

public class OEntityPig extends OEntityAnimal {

    //CanaryMod pig handler
    private CanaryPig canaryPig;
    
    public OEntityPig(OWorld var1) {
        super(var1);
        this.ae = "/mob/pig.png";
        this.b(0.9F, 0.9F);
        this.al().a(true);
        float var2 = 0.25F;
        this.aL.a(0, new OEntityAISwimming(this));
        this.aL.a(1, new OEntityAIPanic(this, 0.38F));
        this.aL.a(2, new OEntityAIMate(this, var2));
        this.aL.a(3, new OEntityAITempt(this, 0.25F, OItem.S.bP, false));
        this.aL.a(4, new OEntityAIFollowParent(this, 0.28F));
        this.aL.a(5, new OEntityAIWander(this, var2));
        this.aL.a(6, new OEntityAIWatchClosest(this, OEntityPlayer.class, 6.0F));
        this.aL.a(7, new OEntityAILookIdle(this));
        
        canaryPig = new CanaryPig(this);
    }
    /**
     * CanaryMod get the Pig handler
     * @return the canaryPig
     */
    public CanaryPig getCanaryPig() {
        return canaryPig;
    }

    public boolean c_() {
        return true;
    }

    public int d() {
        return 10;
    }

    protected void b() {
        super.b();
        this.bY.a(16, Byte.valueOf((byte) 0));
    }

    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("Saddle", this.A());
    }

    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.a(var1.o("Saddle"));
    }

    protected String i() {
        return "mob.pig";
    }

    protected String j() {
        return "mob.pig";
    }

    protected String k() {
        return "mob.pigdeath";
    }

    public boolean b(OEntityPlayer var1) {
        if (super.b(var1)) {
            return true;
        } else if (this.A() && !this.bi.F && (this.bg == null || this.bg == var1)) {
            var1.b((OEntity) this);
            return true;
        } else {
            return false;
        }
    }

    protected int f() {
        return this.B_() ? OItem.aq.bP : OItem.ap.bP;
    }

    public boolean A() {
        return (this.bY.a(16) & 1) != 0;
    }

    public void a(boolean var1) {
        if (var1) {
            this.bY.b(16, Byte.valueOf((byte) 1));
        } else {
            this.bY.b(16, Byte.valueOf((byte) 0));
        }

    }

    public void a(OEntityLightningBolt var1) {
        if (!this.bi.F) {
            OEntityPigZombie var2 = new OEntityPigZombie(this.bi);
            var2.c(this.bm, this.bn, this.bo, this.bs, this.bt);
            this.bi.b(var2);
            this.X();
        }
    }

    protected void a(float var1) {
        super.a(var1);
        if (var1 > 5.0F && this.bg instanceof OEntityPlayer) {
            ((OEntityPlayer) this.bg).a(OAchievementList.u);
        }

    }

    public OEntityAnimal a(OEntityAnimal var1) {
        return new OEntityPig(this.bi);
    }
}
