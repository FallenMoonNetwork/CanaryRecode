package net.minecraft.server;


import net.minecraft.server.OEntityAIFollowParent;
import net.minecraft.server.OEntityAILookIdle;
import net.minecraft.server.OEntityAIMate;
import net.minecraft.server.OEntityAIPanic;
import net.minecraft.server.OEntityAISwimming;
import net.minecraft.server.OEntityAITempt;
import net.minecraft.server.OEntityAIWander;
import net.minecraft.server.OEntityAIWatchClosest;
import net.minecraft.server.OEntityAnimal;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;


public class OEntityChicken extends OEntityAnimal {

    public boolean a = false;
    public float b = 0.0F;
    public float c = 0.0F;
    public float g;
    public float h;
    public float i = 1.0F;
    public int j;

    public OEntityChicken(OWorld var1) {
        super(var1);
        this.ae = "/mob/chicken.png";
        this.b(0.3F, 0.7F);
        this.j = this.bS.nextInt(6000) + 6000;
        float var2 = 0.25F;

        this.aL.a(0, new OEntityAISwimming(this));
        this.aL.a(1, new OEntityAIPanic(this, 0.38F));
        this.aL.a(2, new OEntityAIMate(this, var2));
        this.aL.a(3, new OEntityAITempt(this, 0.25F, OItem.S.bP, false));
        this.aL.a(4, new OEntityAIFollowParent(this, 0.28F));
        this.aL.a(5, new OEntityAIWander(this, var2));
        this.aL.a(6, new OEntityAIWatchClosest(this, OEntityPlayer.class, 6.0F));
        this.aL.a(7, new OEntityAILookIdle(this));
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
        this.h = this.b;
        this.g = this.c;
        this.c = (float) (this.c + (this.bx ? -1 : 4) * 0.3D);
        if (this.c < 0.0F) {
            this.c = 0.0F;
        }

        if (this.c > 1.0F) {
            this.c = 1.0F;
        }

        if (!this.bx && this.i < 1.0F) {
            this.i = 1.0F;
        }

        this.i = (float) (this.i * 0.9D);
        if (!this.bx && this.bq < 0.0D) {
            this.bq *= 0.6D;
        }

        this.b += this.i * 2.0F;
        if (!this.aO() && !this.bi.F && --this.j <= 0) {
            this.bi.a(this, "mob.chickenplop", 1.0F, (this.bS.nextFloat() - this.bS.nextFloat()) * 0.2F + 1.0F);
            this.b(OItem.aO.bP, 1);
            this.j = this.bS.nextInt(6000) + 6000;
        }

    }

    @Override
    protected void a(float var1) {}

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
    }

    @Override
    protected String i() {
        return "mob.chicken";
    }

    @Override
    protected String j() {
        return "mob.chickenhurt";
    }

    @Override
    protected String k() {
        return "mob.chickenhurt";
    }

    @Override
    protected int f() {
        return OItem.K.bP;
    }

    @Override
    protected void a(boolean var1, int var2) {
        int var3 = this.bS.nextInt(3) + this.bS.nextInt(1 + var2);

        for (int var4 = 0; var4 < var3; ++var4) {
            this.b(OItem.K.bP, 1);
        }

        if (this.B_()) {
            this.b(OItem.bk.bP, 1);
        } else {
            this.b(OItem.bj.bP, 1);
        }

    }

    @Override
    public OEntityAnimal a(OEntityAnimal var1) {
        return new OEntityChicken(this.bi);
    }
}
