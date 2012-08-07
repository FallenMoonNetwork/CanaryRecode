package net.minecraft.server;


import java.util.Random;

import net.canarymod.api.entity.CanarySheep;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityAIEatGrass;
import net.minecraft.server.OEntityAIFollowParent;
import net.minecraft.server.OEntityAILookIdle;
import net.minecraft.server.OEntityAIMate;
import net.minecraft.server.OEntityAIPanic;
import net.minecraft.server.OEntityAISwimming;
import net.minecraft.server.OEntityAITempt;
import net.minecraft.server.OEntityAIWander;
import net.minecraft.server.OEntityAIWatchClosest;
import net.minecraft.server.OEntityAnimal;
import net.minecraft.server.OEntityItem;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemStack;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.OWorld;


public class OEntitySheep extends OEntityAnimal {

    public static final float[][] a = new float[][] { { 1.0F, 1.0F, 1.0F }, { 0.95F, 0.7F, 0.2F }, { 0.9F, 0.5F, 0.85F }, { 0.6F, 0.7F, 0.95F }, { 0.9F, 0.9F, 0.2F }, { 0.5F, 0.8F, 0.1F }, { 0.95F, 0.7F, 0.8F }, { 0.3F, 0.3F, 0.3F }, { 0.6F, 0.6F, 0.6F }, { 0.3F, 0.6F, 0.7F }, { 0.7F, 0.4F, 0.9F }, { 0.2F, 0.4F, 0.8F }, { 0.5F, 0.4F, 0.3F }, { 0.4F, 0.5F, 0.2F }, { 0.8F, 0.3F, 0.3F }, { 0.1F, 0.1F, 0.1F } };
    private int b;
    private OEntityAIEatGrass c = new OEntityAIEatGrass(this);

    // CanarMod sheep handler
    private CanarySheep canarySheep;
    
    public OEntitySheep(OWorld var1) {
        super(var1);
        this.ae = "/mob/sheep.png";
        this.b(0.9F, 1.3F);
        float var2 = 0.23F;

        this.al().a(true);
        this.aL.a(0, new OEntityAISwimming(this));
        this.aL.a(1, new OEntityAIPanic(this, 0.38F));
        this.aL.a(2, new OEntityAIMate(this, var2));
        this.aL.a(3, new OEntityAITempt(this, 0.25F, OItem.S.bP, false));
        this.aL.a(4, new OEntityAIFollowParent(this, 0.25F));
        this.aL.a(5, this.c);
        this.aL.a(6, new OEntityAIWander(this, var2));
        this.aL.a(7, new OEntityAIWatchClosest(this, OEntityPlayer.class, 6.0F));
        this.aL.a(8, new OEntityAILookIdle(this));
        
        canarySheep = new CanarySheep(this);
    }

    /**
     * CanaryMod Get Sheep handler
     * @return the canarySheep
     */
    public CanarySheep getCanarySheep() {
        return canarySheep;
    }

    @Override
    protected boolean c_() {
        return true;
    }

    @Override
    protected void z_() {
        this.b = this.c.f();
        super.z_();
    }

    @Override
    public void e() {
        if (this.bi.F) {
            this.b = Math.max(0, this.b - 1);
        }

        super.e();
    }

    @Override
    public int d() {
        return 8;
    }

    @Override
    protected void b() {
        super.b();
        this.bY.a(16, new Byte((byte) 0));
    }

    @Override
    protected void a(boolean var1, int var2) {
        if (!this.A_()) {
            this.a(new OItemStack(OBlock.ab.bO, 1, this.x()), 0.0F);
        }

    }

    @Override
    protected int f() {
        return OBlock.ab.bO;
    }

    @Override
    public boolean interact(OEntityPlayer var1) {
        OItemStack var2 = var1.k.d();

        if (var2 != null && var2.c == OItem.bd.bP && !this.A_() && !this.aO()) {
            if (!this.bi.F) {
                this.a(true);
                int var3 = 1 + this.bS.nextInt(3);

                for (int var4 = 0; var4 < var3; ++var4) {
                    OEntityItem var5 = this.a(new OItemStack(OBlock.ab.bO, 1, this.x()), 1.0F);

                    var5.bq += (this.bS.nextFloat() * 0.05F);
                    var5.bp += ((this.bS.nextFloat() - this.bS.nextFloat()) * 0.1F);
                    var5.br += ((this.bS.nextFloat() - this.bS.nextFloat()) * 0.1F);
                }
            }

            var2.a(1, var1);
        }

        return super.interact(var1);
    }

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("Sheared", this.A_());
        var1.a("Color", (byte) this.x());
    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
        this.a(var1.o("Sheared"));
        this.d_(var1.d("Color"));
    }

    @Override
    protected String i() {
        return "mob.sheep";
    }

    @Override
    protected String j() {
        return "mob.sheep";
    }

    @Override
    protected String k() {
        return "mob.sheep";
    }

    public int x() {
        return this.bY.a(16) & 15;
    }

    public void d_(int var1) {
        byte var2 = this.bY.a(16);

        this.bY.b(16, Byte.valueOf((byte) (var2 & 240 | var1 & 15)));
    }

    public boolean A_() {
        return (this.bY.a(16) & 16) != 0;
    }

    public void a(boolean var1) {
        byte var2 = this.bY.a(16);

        if (var1) {
            this.bY.b(16, Byte.valueOf((byte) (var2 | 16)));
        } else {
            this.bY.b(16, Byte.valueOf((byte) (var2 & -17)));
        }

    }

    public static int a(Random var0) {
        int var1 = var0.nextInt(100);

        return var1 < 5 ? 15 : (var1 < 10 ? 7 : (var1 < 15 ? 8 : (var1 < 18 ? 12 : (var0.nextInt(500) == 0 ? 6 : 0))));
    }

    @Override
    public OEntityAnimal a(OEntityAnimal var1) {
        OEntitySheep var2 = (OEntitySheep) var1;
        OEntitySheep var3 = new OEntitySheep(this.bi);

        if (this.bS.nextBoolean()) {
            var3.d_(this.x());
        } else {
            var3.d_(var2.x());
        }

        return var3;
    }

    @Override
    public void z() {
        this.a(false);
        if (this.aO()) {
            int var1 = this.K() + 1200;

            if (var1 > 0) {
                var1 = 0;
            }

            this.c(var1);
        }

    }

}
