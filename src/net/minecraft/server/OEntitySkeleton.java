package net.minecraft.server;

import net.canarymod.api.entity.CanarySkeleton;
import net.minecraft.server.OAchievementList;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEnchantmentHelper;
import net.minecraft.server.OEntityAIArrowAttack;
import net.minecraft.server.OEntityAIFleeSun;
import net.minecraft.server.OEntityAIHurtByTarget;
import net.minecraft.server.OEntityAILookIdle;
import net.minecraft.server.OEntityAINearestAttackableTarget;
import net.minecraft.server.OEntityAIRestrictSun;
import net.minecraft.server.OEntityAISwimming;
import net.minecraft.server.OEntityAIWander;
import net.minecraft.server.OEntityAIWatchClosest;
import net.minecraft.server.OEntityArrow;
import net.minecraft.server.OEntityMob;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEnumCreatureAttribute;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OWorld;

public class OEntitySkeleton extends OEntityMob {

    private static final OItemStack a = new OItemStack(OItem.j, 1);
    private CanarySkeleton canarySkeleton; 

    public OEntitySkeleton(OWorld var1) {
        super(var1);
        this.ae = "/mob/skeleton.png";
        this.bb = 0.25F;
        this.aL.a(1, new OEntityAISwimming(this));
        this.aL.a(2, new OEntityAIRestrictSun(this));
        this.aL.a(3, new OEntityAIFleeSun(this, this.bb));
        this.aL.a(4, new OEntityAIArrowAttack(this, this.bb, 1, 60));
        this.aL.a(5, new OEntityAIWander(this, this.bb));
        this.aL.a(6, new OEntityAIWatchClosest(this, OEntityPlayer.class, 8.0F));
        this.aL.a(6, new OEntityAILookIdle(this));
        this.aM.a(1, new OEntityAIHurtByTarget(this, false));
        this.aM.a(2, new OEntityAINearestAttackableTarget(this, OEntityPlayer.class, 16.0F, 0, true));
        canarySkeleton = new CanarySkeleton(this);
    }

    /**
     * CanaryMod Get skeleton handler
     * @return the canarySkeleton
     */
    public CanarySkeleton getCanarySkeleton() {
        return canarySkeleton;
    }
    @Override
    public boolean c_() {
        return true;
    }

    @Override
    public int d() {
        return 20;
    }

    @Override
    protected String i() {
        return "mob.skeleton";
    }

    @Override
    protected String j() {
        return "mob.skeletonhurt";
    }

    @Override
    protected String k() {
        return "mob.skeletonhurt";
    }

    @Override
    public OEnumCreatureAttribute v() {
        return OEnumCreatureAttribute.b;
    }

    @Override
    public void e() {
        if (this.bi.e() && !this.bi.F) {
            float var1 = this.b(1.0F);
            if (var1 > 0.5F && this.bi.l(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo)) && this.bS.nextFloat() * 30.0F < (var1 - 0.4F) * 2.0F) {
                this.i(8);
            }
        }

        super.e();
    }

    @Override
    public void a(ODamageSource var1) {
        super.a(var1);
        if (var1.b() instanceof OEntityArrow && var1.a() instanceof OEntityPlayer) {
            OEntityPlayer var2 = (OEntityPlayer) var1.a();
            double var3 = var2.bm - this.bm;
            double var5 = var2.bo - this.bo;
            if (var3 * var3 + var5 * var5 >= 2500.0D) {
                var2.a(OAchievementList.v);
            }
        }

    }

    @Override
    protected int f() {
        return OItem.k.bP;
    }

    @Override
    protected void a(boolean var1, int var2) {
        int var3 = this.bS.nextInt(3 + var2);

        int var4;
        for (var4 = 0; var4 < var3; ++var4) {
            this.b(OItem.k.bP, 1);
        }

        var3 = this.bS.nextInt(3 + var2);

        for (var4 = 0; var4 < var3; ++var4) {
            this.b(OItem.aW.bP, 1);
        }

    }

    @Override
    protected void b(int var1) {
        if (var1 > 0) {
            OItemStack var2 = new OItemStack(OItem.j);
            OEnchantmentHelper.a(this.bS, var2, 5);
            this.a(var2, 0.0F);
        } else {
            this.b(OItem.j.bP, 1);
        }

    }

}
