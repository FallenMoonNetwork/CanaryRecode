package net.minecraft.server;

import net.canarymod.Canary;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.entity.MobTargetHook;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityMob;
import net.minecraft.server.OEnumCreatureAttribute;
import net.minecraft.server.OItem;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OPotion;
import net.minecraft.server.OPotionEffect;
import net.minecraft.server.OWorld;

public class OEntitySpider extends OEntityMob {

    public OEntitySpider(OWorld var1) {
        super(var1);
        this.ae = "/mob/spider.png";
        this.b(1.4F, 0.9F);
        this.bb = 0.8F;
    }

    @Override
    protected void b() {
        super.b();
        this.bY.a(16, new Byte((byte) 0));
    }

    @Override
    public void e() {
        super.e();
    }

    @Override
    public void F_() {
        super.F_();
        if (!this.bi.F) {
            this.a(this.by);
        }

    }

    @Override
    public int d() {
        return 16;
    }

    @Override
    public double x_() {
        return this.bH * 0.75D - 0.5D;
    }

    @Override
    protected boolean g_() {
        return false;
    }

    @Override
    protected OEntity o() {
        float var1 = this.b(1.0F);
        double var2 = 16.0D;
        
        //CanaryMod start - onMobTarget
        OEntityPlayer entityplayer = this.bi.b(this, var2);
        if(entityplayer != null){
            MobTargetHook hook = new MobTargetHook(getCanaryEntityLiving(), ((OEntityPlayerMP) entityplayer).getPlayer());
            Canary.hooks().callHook(hook);
            if(hook.isCanceled()){
                return null;
            }
        }
        //CanaryMod end
        
        if (var1 < 0.5F) {
            return entityplayer;
        } else {
            return null;
        }
    }

    @Override
    protected String i() {
        return "mob.spider";
    }

    @Override
    protected String j() {
        return "mob.spider";
    }

    @Override
    protected String k() {
        return "mob.spiderdeath";
    }

    @Override
    protected void a(OEntity var1, float var2) {
        float var3 = this.b(1.0F);
        if (var3 > 0.5F && this.bS.nextInt(100) == 0) {
            this.d = null;
        } else {
            if (var2 > 2.0F && var2 < 6.0F && this.bS.nextInt(10) == 0) {
                if (this.bx) {
                    double var4 = var1.bm - this.bm;
                    double var6 = var1.bo - this.bo;
                    float var8 = OMathHelper.a(var4 * var4 + var6 * var6);
                    this.bp = var4 / var8 * 0.5D * 0.800000011920929D + this.bp * 0.20000000298023224D;
                    this.br = var6 / var8 * 0.5D * 0.800000011920929D + this.br * 0.20000000298023224D;
                    this.bq = 0.4000000059604645D;
                }
            } else {
                super.a(var1, var2);
            }

        }
    }

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
    }

    @Override
    protected int f() {
        return OItem.J.bP;
    }

    @Override
    protected void a(boolean var1, int var2) {
        super.a(var1, var2);
        if (var1 && (this.bS.nextInt(3) == 0 || this.bS.nextInt(1 + var2) > 0)) {
            this.b(OItem.bt.bP, 1);
        }

    }

    @Override
    public boolean t() {
        return this.w();
    }

    @Override
    public void u() {
    }

    @Override
    public OEnumCreatureAttribute v() {
        return OEnumCreatureAttribute.c;
    }

    @Override
    public boolean a(OPotionEffect var1) {
        return var1.a() == OPotion.u.H ? false : super.a(var1);
    }

    public boolean w() {
        return (this.bY.a(16) & 1) != 0;
    }

    public void a(boolean var1) {
        byte var2 = this.bY.a(16);
        if (var1) {
            var2 = (byte) (var2 | 1);
        } else {
            var2 &= -2;
        }

        this.bY.b(16, Byte.valueOf(var2));
    }
}
