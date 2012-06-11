package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.canarymod.Canary;
import net.canarymod.api.CanaryDamageSource;
import net.canarymod.api.entity.CanaryEntityLiving;
import net.canarymod.api.entity.Entity;
import net.canarymod.api.entity.EntityLiving;
import net.canarymod.api.world.blocks.Block;
import net.canarymod.hook.CancelableHook;
import net.canarymod.hook.Hook;
import net.canarymod.hook.entity.DamageHook;
import net.canarymod.hook.world.ExplosionHook;
import net.minecraft.server.OAxisAlignedBB;
import net.minecraft.server.OBlock;
import net.minecraft.server.OChunkPosition;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.OVec3D;
import net.minecraft.server.OWorld;

public class OExplosion {

    public boolean a = false;
    private Random h = new Random();
    private OWorld i;
    public double b;
    public double c;
    public double d;
    public OEntity e;
    public float f;
    public Set g = new HashSet();
    
    //CanaryMod Start
    protected boolean toRet;
    protected List<Block> blocks = new ArrayList<Block>();
    //CanaryMod End

    public OExplosion(OWorld var1, OEntity var2, double var3, double var5, double var7, float var9) {
        super();
        this.i = var1;
        this.e = var2;
        this.f = var9;
        this.b = var3;
        this.c = var5;
        this.d = var7;
    }

    public void a() {
        Block base = i.getCanaryDimension().getBlockAt((int) Math.floor(b), (int) Math.floor(c), (int) Math.floor(d));
        float var1 = this.f;
        byte var2 = 16;

        int var3;
        int var4;
        int var5;
        double var15;
        double var17;
        double var19;
        for (var3 = 0; var3 < var2; ++var3) {
            for (var4 = 0; var4 < var2; ++var4) {
                for (var5 = 0; var5 < var2; ++var5) {
                    if (var3 == 0 || var3 == var2 - 1 || var4 == 0 || var4 == var2 - 1 || var5 == 0 || var5 == var2 - 1) {
                        double var6 = (var3 / (var2 - 1.0F) * 2.0F - 1.0F);
                        double var8 = (var4 / (var2 - 1.0F) * 2.0F - 1.0F);
                        double var10 = (var5 / (var2 - 1.0F) * 2.0F - 1.0F);
                        double var12 = Math.sqrt(var6 * var6 + var8 * var8 + var10 * var10);
                        var6 /= var12;
                        var8 /= var12;
                        var10 /= var12;
                        float var14 = this.f * (0.7F + this.i.r.nextFloat() * 0.6F);
                        var15 = this.b;
                        var17 = this.c;
                        var19 = this.d;

                        for (float var21 = 0.3F; var14 > 0.0F; var14 -= var21 * 0.75F) {
                            int var22 = OMathHelper.b(var15);
                            int var23 = OMathHelper.b(var17);
                            int var24 = OMathHelper.b(var19);
                            int var25 = this.i.a(var22, var23, var24);
                            if (var25 > 0) {
                                var14 -= (OBlock.m[var25].a(this.e) + 0.3F) * var21;
                            }

                            if (var14 > 0.0F) {
                                this.g.add(new OChunkPosition(var22, var23, var24));
                                
                                //CanaryMod - Build blocks list
                                Block block = i.getCanaryDimension().getBlockAt(var22, var23, var24);
                                if(var25 != 0 && !blocks.contains(block)){
                                    blocks.add(block);
                                }
                                //CanaryMod end
                                
                            }

                            var15 += var6 * var21;
                            var17 += var8 * var21;
                            var19 += var10 * var21;
                        }
                    }
                }
            }
        }

        CancelableHook explodehook = (CancelableHook) Canary.hooks().callCancelableHook(new ExplosionHook(base, e.getCanaryEntity(), blocks));
        toRet = explodehook.isCancelled();
        this.f *= 2.0F;
        var3 = OMathHelper.b(this.b - this.f - 1.0D);
        var4 = OMathHelper.b(this.b + this.f + 1.0D);
        var5 = OMathHelper.b(this.c - this.f - 1.0D);
        int var26 = OMathHelper.b(this.c + this.f + 1.0D);
        int var27 = OMathHelper.b(this.d - this.f - 1.0D);
        int var28 = OMathHelper.b(this.d + this.f + 1.0D);
        List var29 = this.i.b(this.e, OAxisAlignedBB.b(var3, var5, var27, var4, var26, var28));
        OVec3D var30 = OVec3D.b(this.b, this.c, this.d);

        for (int var31 = 0; var31 < var29.size(); ++var31) {
            OEntity var32 = (OEntity) var29.get(var31);
            double var33 = var32.f(this.b, this.c, this.d) / this.f;
            if (var33 <= 1.0D) {
                var15 = var32.bm - this.b;
                var17 = var32.bn - this.c;
                var19 = var32.bo - this.d;
                double var35 = OMathHelper.a(var15 * var15 + var17 * var17 + var19 * var19);
                var15 /= var35;
                var17 /= var35;
                var19 /= var35;
                double var37 = this.i.a(var30, var32.bw);
                double var39 = (1.0D - var33) * var37;
                // CanaryMod - explosion damage.
                int damage = (int) ((var39 * var39 + var39) / 2.0D * 8.0D * f + 1.0D);
                EntityLiving attacker = null;
                if (e instanceof OEntityCreeper) {
                    attacker = ((OEntityLiving) e).getCanaryEntityLiving();
                }
                CancelableHook hook = (CancelableHook) Canary.hooks().callCancelableHook(new DamageHook(attacker, ((OEntityLiving) var32).getCanaryEntityLiving(), new CanaryDamageSource(ODamageSource.l), damage));
                if (!hook.isCancelled()) {
                    var32.a(ODamageSource.l, damage);
                }
                // CanaryMod - end.
                var32.bp += var15 * var39;
                var32.bq += var17 * var39;
                var32.br += var19 * var39;
            }
        }

        this.f = var1;
        ArrayList var43 = new ArrayList();
        var43.addAll(this.g);
    }

    public void a(boolean var1) {
        this.i.a(this.b, this.c, this.d, "random.explode", 4.0F, (1.0F + (this.i.r.nextFloat() - this.i.r.nextFloat()) * 0.2F) * 0.7F);
        this.i.a("hugeexplosion", this.b, this.c, this.d, 0.0D, 0.0D, 0.0D);
        ArrayList var2 = new ArrayList();
        var2.addAll(this.g);

        //CanaryMod - cancel explosions
        if (this.toRet) {
            this.g = new HashSet();
            return;
        }
        
        int var3;
        OChunkPosition var4;
        int var5;
        int var6;
        int var7;
        int var8;
        for (var3 = var2.size() - 1; var3 >= 0; --var3) {
            var4 = (OChunkPosition) var2.get(var3);
            var5 = var4.a;
            var6 = var4.b;
            var7 = var4.c;
            var8 = this.i.a(var5, var6, var7);
            if (var1) {
                double var9 = (var5 + this.i.r.nextFloat());
                double var11 = (var6 + this.i.r.nextFloat());
                double var13 = (var7 + this.i.r.nextFloat());
                double var15 = var9 - this.b;
                double var17 = var11 - this.c;
                double var19 = var13 - this.d;
                double var21 = OMathHelper.a(var15 * var15 + var17 * var17 + var19 * var19);
                var15 /= var21;
                var17 /= var21;
                var19 /= var21;
                double var23 = 0.5D / (var21 / this.f + 0.1D);
                var23 *= (this.i.r.nextFloat() * this.i.r.nextFloat() + 0.3F);
                var15 *= var23;
                var17 *= var23;
                var19 *= var23;
                this.i.a("explode", (var9 + this.b * 1.0D) / 2.0D, (var11 + this.c * 1.0D) / 2.0D, (var13 + this.d * 1.0D) / 2.0D, var15, var17, var19);
                this.i.a("smoke", var9, var11, var13, var15, var17, var19);
            }

            if (var8 > 0) {
                OBlock.m[var8].a(this.i, var5, var6, var7, this.i.c(var5, var6, var7), 0.3F, 0);
                this.i.e(var5, var6, var7, 0);
                OBlock.m[var8].a_(this.i, var5, var6, var7);
            }
        }

        if (this.a) {
            for (var3 = var2.size() - 1; var3 >= 0; --var3) {
                var4 = (OChunkPosition) var2.get(var3);
                var5 = var4.a;
                var6 = var4.b;
                var7 = var4.c;
                var8 = this.i.a(var5, var6, var7);
                int var25 = this.i.a(var5, var6 - 1, var7);
                if (var8 == 0 && OBlock.n[var25] && this.h.nextInt(3) == 0) {
                    this.i.e(var5, var6, var7, OBlock.ar.bO);
                }
            }
        }

    }
}
