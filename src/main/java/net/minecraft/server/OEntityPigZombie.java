package net.minecraft.server;

import java.util.List;

import net.canarymod.Canary;
import net.canarymod.api.entity.CanaryPigZombie;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.entity.MobTargetHook;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEnchantmentHelper;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OEntityZombie;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public class OEntityPigZombie extends OEntityZombie {

    public int a = 0; //CanaryMod private -> public
    private int b = 0;
    private static final OItemStack g = new OItemStack(OItem.F, 1);
    
    private CanaryPigZombie canaryPigZombie;

    public OEntityPigZombie(OWorld var1) {
        super(var1);
        this.ae = "/mob/pigzombie.png";
        this.bb = 0.5F;
        this.c = 5;
        this.bX = true;
        
        canaryPigZombie = new CanaryPigZombie(this);
    }

    /**
     * CanaryMod get PigZombie handler
     * @return the canaryPigZombie
     */
    public CanaryPigZombie getCanaryPigZombie() {
        return canaryPigZombie;
    }

    @Override
    protected boolean c_() {
        return false;
    }

    @Override
    public void F_() {
        this.bb = this.d != null ? 0.95F : 0.5F;
        if (this.b > 0 && --this.b == 0) {
            this.bi.a(this, "mob.zombiepig.zpigangry", this.p() * 2.0F, ((this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.F_();
    }

    @Override
    public boolean l() {
        return this.bi.q > 0 && this.bi.a(this.bw) && this.bi.a(this, this.bw).size() == 0 && !this.bi.c(this.bw);
    }

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("Anger", (short) this.a);
    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.a = var1.e("Anger");
    }

    @Override
    protected OEntity o() {
        return this.a == 0 ? null : super.o();
    }

    @Override
    public void e() {
        super.e();
    }

    @Override
    public boolean a(ODamageSource var1, int var2) {
        OEntity var3 = var1.a();
        if (var3 instanceof OEntityPlayer) {
            //CanaryMod - onMobTarget
            MobTargetHook hook = new MobTargetHook(getCanaryEntityLiving(), ((OEntityLiving)var3).getCanaryEntityLiving().getPlayer());
            Canary.hooks().callHook(hook);
            if(!hook.isCanceled()){
                List var4 = this.bi.b(this, this.bw.b(32.0D, 32.0D, 32.0D));
    
                for (int var5 = 0; var5 < var4.size(); ++var5) {
                    OEntity var6 = (OEntity) var4.get(var5);
                    if (var6 instanceof OEntityPigZombie) {
                        OEntityPigZombie var7 = (OEntityPigZombie) var6;
                        var7.e(var3);
                    }
                }
    
                this.e(var3);
            }
        }

        return super.a(var1, var2);
    }

    private void e(OEntity var1) {
        this.d = var1;
        this.a = 400 + this.bS.nextInt(400);
        this.b = this.bS.nextInt(40);
    }

    @Override
    protected String i() {
        return "mob.zombiepig.zpig";
    }

    @Override
    protected String j() {
        return "mob.zombiepig.zpighurt";
    }

    @Override
    protected String k() {
        return "mob.zombiepig.zpigdeath";
    }

    @Override
    protected void a(boolean var1, int var2) {
        int var3 = this.bS.nextInt(2 + var2);

        int var4;
        for (var4 = 0; var4 < var3; ++var4) {
            this.b(OItem.bl.bP, 1);
        }

        var3 = this.bS.nextInt(2 + var2);

        for (var4 = 0; var4 < var3; ++var4) {
            this.b(OItem.bp.bP, 1);
        }

    }

    @Override
    protected void b(int var1) {
        if (var1 > 0) {
            OItemStack var2 = new OItemStack(OItem.F);
            OEnchantmentHelper.a(this.bS, var2, 5);
            this.a(var2, 0.0F);
        } else {
            int var3 = this.bS.nextInt(3);
            if (var3 == 0) {
                this.b(OItem.o.bP, 1);
            } else if (var3 == 1) {
                this.b(OItem.F.bP, 1);
            } else if (var3 == 2) {
                this.b(OItem.ak.bP, 1);
            }
        }

    }

    @Override
    protected int f() {
        return OItem.bl.bP;
    }

}
