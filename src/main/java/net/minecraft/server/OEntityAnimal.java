package net.minecraft.server;

import java.util.List;

import net.canarymod.Canary;
import net.canarymod.api.entity.Player;
import net.canarymod.hook.Hook;
import net.canarymod.hook.player.RightClickHook;
import net.minecraft.server.OBlock;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAgeable;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIAnimals;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;

public abstract class OEntityAnimal extends OEntityAgeable implements OIAnimals {

    private int a;
    private int b = 0;

    public OEntityAnimal(OWorld var1) {
        super(var1);
    }

    @Override
    protected void g() {
        if (this.K() != 0) {
            this.a = 0;
        }

        super.g();
    }

    @Override
    public void e() {
        super.e();
        if (this.K() != 0) {
            this.a = 0;
        }

        if (this.a > 0) {
            --this.a;
            String var1 = "heart";
            if (this.a % 10 == 0) {
                double var2 = this.bS.nextGaussian() * 0.02D;
                double var4 = this.bS.nextGaussian() * 0.02D;
                double var6 = this.bS.nextGaussian() * 0.02D;
                this.bi.a(var1, this.bm + (this.bS.nextFloat() * this.bG * 2.0F) - this.bG, this.bn + 0.5D + (this.bS.nextFloat() * this.bH), this.bo + (this.bS.nextFloat() * this.bG * 2.0F) - this.bG, var2, var4, var6);
            }
        } else {
            this.b = 0;
        }

    }

    @Override
    protected void a(OEntity var1, float var2) {
        if (var1 instanceof OEntityPlayer) {
            if (var2 < 3.0F) {
                double var3 = var1.bm - this.bm;
                double var5 = var1.bo - this.bo;
                this.bs = (float) (Math.atan2(var5, var3) * 180.0D / 3.1415927410125732D) - 90.0F;
                this.e = true;
            }

            OEntityPlayer var7 = (OEntityPlayer) var1;
            if (var7.U() == null || !this.a(var7.U())) {
                this.d = null;
            }
        } else if (var1 instanceof OEntityAnimal) {
            OEntityAnimal var8 = (OEntityAnimal) var1;
            if (this.K() > 0 && var8.K() < 0) {
                if (var2 < 2.5D) {
                    this.e = true;
                }
            } else if (this.a > 0 && var8.a > 0) {
                if (var8.d == null) {
                    var8.d = this;
                }

                if (var8.d == this && var2 < 3.5D) {
                    ++var8.a;
                    ++this.a;
                    ++this.b;
                    if (this.b % 4 == 0) {
                        this.bi.a("heart", this.bm + (this.bS.nextFloat() * this.bG * 2.0F) - this.bG, this.bn + 0.5D + (this.bS.nextFloat() * this.bH), this.bo + (this.bS.nextFloat() * this.bG * 2.0F) - this.bG, 0.0D, 0.0D, 0.0D);
                    }

                    if (this.b == 60) {
                        this.c((OEntityAnimal) var1);
                    }
                } else {
                    this.b = 0;
                }
            } else {
                this.b = 0;
                this.d = null;
            }
        }

    }

    private void c(OEntityAnimal var1) {
        OEntityAnimal var2 = this.a(var1);
        if (var2 != null) {
            this.c(6000);
            var1.c(6000);
            this.a = 0;
            this.b = 0;
            this.d = null;
            var1.d = null;
            var1.b = 0;
            var1.a = 0;
            var2.c(-24000);
            var2.c(this.bm, this.bn, this.bo, this.bs, this.bt);

            for (int var3 = 0; var3 < 7; ++var3) {
                double var4 = this.bS.nextGaussian() * 0.02D;
                double var6 = this.bS.nextGaussian() * 0.02D;
                double var8 = this.bS.nextGaussian() * 0.02D;
                this.bi.a("heart", this.bm + (this.bS.nextFloat() * this.bG * 2.0F) - this.bG, this.bn + 0.5D + (this.bS.nextFloat() * this.bH), this.bo + (this.bS.nextFloat() * this.bG * 2.0F) - this.bG, var4, var6, var8);
            }

            this.bi.b(var2);
        }

    }

    public abstract OEntityAnimal a(OEntityAnimal var1);

    @Override
    protected void b(OEntity var1, float var2) {
    }

    @Override
    public boolean a(ODamageSource var1, int var2) {
        this.f = 60;
        this.d = null;
        this.a = 0;
        return super.a(var1, var2);
    }

    @Override
    public float a(int var1, int var2, int var3) {
        return this.bi.a(var1, var2 - 1, var3) == OBlock.u.bO ? 10.0F : this.bi.p(var1, var2, var3) - 0.5F;
    }

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("InLove", this.a);
    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.a = var1.f("InLove");
    }

    @Override
    protected OEntity o() {
        if (this.f > 0) {
            return null;
        } else {
            float var1 = 8.0F;
            List var2;
            int var3;
            OEntityAnimal var4;
            if (this.a > 0) {
                var2 = this.bi.a(this.getClass(), this.bw.b(var1, var1, var1));

                for (var3 = 0; var3 < var2.size(); ++var3) {
                    var4 = (OEntityAnimal) var2.get(var3);
                    if (var4 != this && var4.a > 0) {
                        return var4;
                    }
                }
            } else if (this.K() == 0) {
                var2 = this.bi.a(OEntityPlayer.class, this.bw.b(var1, var1, var1));

                for (var3 = 0; var3 < var2.size(); ++var3) {
                    OEntityPlayer var5 = (OEntityPlayer) var2.get(var3);
                    if (var5.U() != null && this.a(var5.U())) {
                        return var5;
                    }
                }
            } else if (this.K() > 0) {
                var2 = this.bi.a(this.getClass(), this.bw.b(var1, var1, var1));

                for (var3 = 0; var3 < var2.size(); ++var3) {
                    var4 = (OEntityAnimal) var2.get(var3);
                    if (var4 != this && var4.K() < 0) {
                        return var4;
                    }
                }
            }

            return null;
        }
    }

    @Override
    public boolean l() {
        int var1 = OMathHelper.b(this.bm);
        int var2 = OMathHelper.b(this.bw.b);
        int var3 = OMathHelper.b(this.bo);
        return this.bi.a(var1, var2 - 1, var3) == OBlock.u.bO && this.bi.m(var1, var2, var3) > 8 && super.l();
    }

    @Override
    public int m() {
        return 120;
    }

    @Override
    protected boolean n() {
        return false;
    }

    @Override
    protected int a(OEntityPlayer var1) {
        return 1 + this.bi.r.nextInt(3);
    }

    public boolean a(OItemStack var1) {
        return var1.c == OItem.S.bP;
    }

    @Override
    public boolean interact(OEntityPlayer var1) {
        OItemStack var2 = var1.k.l();
        if (var2 != null && this.a(var2) && this.K() == 0) {
            //CanaryMod Breed hook
            Player tmp = ((OEntityPlayerMP) var1).getPlayer();
            RightClickHook hook = new RightClickHook(tmp, null, null, tmp.getItemHeld(), getCanaryEntityLiving(), Hook.Type.BREED);
            Canary.hooks().callHook(hook);
            if(hook.isCanceled()) {
                return false;
            }
            if (!var1.L.d) {
                --var2.a;
                if (var2.a <= 0) {
                    var1.k.a(var1.k.c, (OItemStack) null);
                }
            }

            this.a = 600;
            this.d = null;

            for (int var3 = 0; var3 < 7; ++var3) {
                double var4 = this.bS.nextGaussian() * 0.02D;
                double var6 = this.bS.nextGaussian() * 0.02D;
                double var8 = this.bS.nextGaussian() * 0.02D;
                this.bi.a("heart", this.bm + (this.bS.nextFloat() * this.bG * 2.0F) - this.bG, this.bn + 0.5D + (this.bS.nextFloat() * this.bH), this.bo + (this.bS.nextFloat() * this.bG * 2.0F) - this.bG, var4, var6, var8);
            }

            return true;
        } else {
            return super.interact(var1);
        }
    }

    public boolean r_() {
        return this.a > 0;
    }

    public void s_() {
        this.a = 0;
    }

    public boolean b(OEntityAnimal var1) {
        return var1 == this ? false : (var1.getClass() != this.getClass() ? false : this.r_() && var1.r_());
    }
}
