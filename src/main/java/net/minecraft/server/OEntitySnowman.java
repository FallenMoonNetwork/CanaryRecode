package net.minecraft.server;


import net.minecraft.server.OBlock;
import net.minecraft.server.ODamageSource;
import net.minecraft.server.OEntityAIArrowAttack;
import net.minecraft.server.OEntityAILookIdle;
import net.minecraft.server.OEntityAINearestAttackableTarget;
import net.minecraft.server.OEntityAIWander;
import net.minecraft.server.OEntityAIWatchClosest;
import net.minecraft.server.OEntityGolem;
import net.minecraft.server.OEntityMob;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OMathHelper;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;


public class OEntitySnowman extends OEntityGolem {

    public OEntitySnowman(OWorld var1) {
        super(var1);
        this.ae = "/mob/snowman.png";
        this.b(0.4F, 1.8F);
        this.al().a(true);
        this.aL.a(1, new OEntityAIArrowAttack(this, 0.25F, 2, 20));
        this.aL.a(2, new OEntityAIWander(this, 0.2F));
        this.aL.a(3, new OEntityAIWatchClosest(this, OEntityPlayer.class, 6.0F));
        this.aL.a(4, new OEntityAILookIdle(this));
        this.aM.a(1, new OEntityAINearestAttackableTarget(this, OEntityMob.class, 16.0F, 0, true));
    }

    @Override
    public boolean c_() {
        return true;
    }

    @Override
    public int d() {
        return 4;
    }

    @Override
    public void e() {
        super.e();
        if (this.aT()) {
            this.a(ODamageSource.f, 1);
        }

        int var1 = OMathHelper.b(this.bm);
        int var2 = OMathHelper.b(this.bo);

        if (this.bi.a(var1, var2).i() > 1.0F) {
            this.a(ODamageSource.c, 1);
        }

        for (var1 = 0; var1 < 4; ++var1) {
            var2 = OMathHelper.b(this.bm + ((var1 % 2 * 2 - 1) * 0.25F));
            int var3 = OMathHelper.b(this.bn);
            int var4 = OMathHelper.b(this.bo + ((var1 / 2 % 2 * 2 - 1) * 0.25F));

            if (this.bi.a(var2, var3, var4) == 0 && this.bi.a(var2, var4).i() < 0.8F && OBlock.aS.c(this.bi, var2, var3, var4)) {
                this.bi.e(var2, var3, var4, OBlock.aS.bO);
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
        return OItem.aC.bP;
    }

    @Override
    protected void a(boolean var1, int var2) {
        int var3 = this.bS.nextInt(16);

        for (int var4 = 0; var4 < var3; ++var4) {
            this.b(OItem.aC.bP, 1);
        }

    }
}
