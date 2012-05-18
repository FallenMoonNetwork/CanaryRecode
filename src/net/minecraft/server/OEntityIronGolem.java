package net.minecraft.server;

import net.canarymod.api.entity.CanaryIronGolem;
import net.minecraft.server.OBlock;
import net.minecraft.server.OChunkCoordinates;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityAIAttackOnCollide;
import net.minecraft.server.OEntityAIDefendVillage;
import net.minecraft.server.OEntityAIHurtByTarget;
import net.minecraft.server.OEntityAILookAtVillager;
import net.minecraft.server.OEntityAILookIdle;
import net.minecraft.server.OEntityAIMoveThroughVillage;
import net.minecraft.server.OEntityAIMoveTowardsTarget;
import net.minecraft.server.OEntityAIMoveTwardsRestriction;
import net.minecraft.server.OEntityAINearestAttackableTarget;
import net.minecraft.server.OEntityAIWander;
import net.minecraft.server.OEntityAIWatchClosest;
import net.minecraft.server.OEntityGolem;
import net.minecraft.server.OEntityLiving;
import net.minecraft.server.OEntityMob;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OVillage;
import net.minecraft.server.OWorld;

public class OEntityIronGolem extends OEntityGolem {

    private int b = 0;
    OVillage a = null;
    private int c;
    private int g;
    //CanaryMod golem handler
    private CanaryIronGolem canaryIronGolem;

    public OEntityIronGolem(OWorld var1) {
        super(var1);
        this.ae = "/mob/villager_golem.png";
        this.b(1.4F, 2.9F);
        this.al().a(true);
        this.aL.a(1, new OEntityAIAttackOnCollide(this, 0.25F, true));
        this.aL.a(2, new OEntityAIMoveTowardsTarget(this, 0.22F, 32.0F));
        this.aL.a(3, new OEntityAIMoveThroughVillage(this, 0.16F, true));
        this.aL.a(4, new OEntityAIMoveTwardsRestriction(this, 0.16F));
        this.aL.a(5, new OEntityAILookAtVillager(this));
        this.aL.a(6, new OEntityAIWander(this, 0.16F));
        this.aL.a(7, new OEntityAIWatchClosest(this, OEntityPlayer.class, 6.0F));
        this.aL.a(8, new OEntityAILookIdle(this));
        this.aM.a(1, new OEntityAIDefendVillage(this));
        this.aM.a(2, new OEntityAIHurtByTarget(this, false));
        this.aM.a(3, new OEntityAINearestAttackableTarget(this, OEntityMob.class, 16.0F, 0, false, true));
        canaryIronGolem = new CanaryIronGolem(this);
    }

    /**
     * CanaryMod Get canary golem handler
     * @return the canaryIronGolem
     */
    public CanaryIronGolem getCanaryIronGolem() {
        return canaryIronGolem;
    }

    protected void b() {
        super.b();
        this.bY.a(16, Byte.valueOf((byte) 0));
    }

    public boolean c_() {
        return true;
    }

    protected void g() {
        if (--this.b <= 0) {
            this.b = 70 + this.bS.nextInt(50);
            this.a = this.bi.A.a(OMathHelper.b(this.bm), OMathHelper.b(this.bn), OMathHelper.b(this.bo), 32);
            if (this.a == null) {
                this.ax();
            } else {
                OChunkCoordinates var1 = this.a.a();
                this.b(var1.a, var1.b, var1.c, this.a.b());
            }
        }

        super.g();
    }

    public int d() {
        return 100;
    }

    protected int b_(int var1) {
        return var1;
    }

    public void e() {
        super.e();
        if (this.c > 0) {
            --this.c;
        }

        if (this.g > 0) {
            --this.g;
        }

        if (this.bp * this.bp + this.br * this.br > 2.500000277905201E-7D && this.bS.nextInt(5) == 0) {
            int var1 = OMathHelper.b(this.bm);
            int var2 = OMathHelper.b(this.bn - 0.20000000298023224D - this.bF);
            int var3 = OMathHelper.b(this.bo);
            int var4 = this.bi.a(var1, var2, var3);
            if (var4 > 0) {
                this.bi.a("tilecrack_" + var4, this.bm + (this.bS.nextFloat() - 0.5D) * this.bG, this.bw.b + 0.1D, this.bo + (this.bS.nextFloat() - 0.5D) * this.bG, 4.0D * (this.bS.nextFloat() - 0.5D), 0.5D, (this.bS.nextFloat() - 0.5D) * 4.0D);
            }
        }

    }

    public boolean a(Class var1) {
        return this.n_() && OEntityPlayer.class.isAssignableFrom(var1) ? false : super.a(var1);
    }

    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("PlayerCreated", this.n_());
    }

    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.b(var1.o("PlayerCreated"));
    }

    public boolean a(OEntity var1) {
        this.c = 10;
        this.bi.a(this, (byte) 4);
        boolean var2 = var1.a(ODamageSource.a(this), 7 + this.bS.nextInt(15));
        if (var2) {
            var1.bq += 0.4000000059604645D;
        }

        this.bi.a(this, "mob.irongolem.throw", 1.0F, 1.0F);
        return var2;
    }

    public OVillage l_() {
        return this.a;
    }

    public void a(boolean var1) {
        this.g = var1 ? 400 : 0;
        this.bi.a(this, (byte) 11);
    }

    protected String i() {
        return "none";
    }

    protected String j() {
        return "mob.irongolem.hit";
    }

    protected String k() {
        return "mob.irongolem.death";
    }

    protected void a(int var1, int var2, int var3, int var4) {
        this.bi.a(this, "mob.irongolem.walk", 1.0F, 1.0F);
    }

    protected void a(boolean var1, int var2) {
        int var3 = this.bS.nextInt(3);

        int var4;
        for (var4 = 0; var4 < var3; ++var4) {
            this.b(OBlock.ae.bO, 1);
        }

        var4 = 3 + this.bS.nextInt(3);

        for (int var5 = 0; var5 < var4; ++var5) {
            this.b(OItem.n.bP, 1);
        }

    }

    public int m_() {
        return this.g;
    }

    public boolean n_() {
        return (this.bY.a(16) & 1) != 0;
    }

    public void b(boolean var1) {
        byte var2 = this.bY.a(16);
        if (var1) {
            this.bY.b(16, Byte.valueOf((byte) (var2 | 1)));
        } else {
            this.bY.b(16, Byte.valueOf((byte) (var2 & -2)));
        }

    }
}
