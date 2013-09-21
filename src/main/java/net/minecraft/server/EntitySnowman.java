package net.minecraft.server;

import net.canarymod.api.entity.living.CanarySnowman;

public class EntitySnowman extends EntityGolem implements IRangedAttackMob {

    public EntitySnowman(World world) {
        super(world);
        this.a(0.4F, 1.8F);
        this.k().a(true);
        this.c.a(1, new EntityAIArrowAttack(this, 1.25D, 20, 10.0F));
        this.c.a(2, new EntityAIWander(this, 1.0D));
        this.c.a(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.c.a(4, new EntityAILookIdle(this));
        this.d.a(1, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, true, false, IMob.a));
        this.entity = new CanarySnowman(this); // CanaryMod: Wrap Entity
    }

    public boolean bf() {
        return true;
    }

    protected void az() {
        super.az();
        this.a(SharedMonsterAttributes.a).a(4.0D);
        this.a(SharedMonsterAttributes.d).a(0.20000000298023224D);
    }

    public void c() {
        super.c();
        if (this.G()) {
            this.a(DamageSource.e, 1.0F);
        }

        int i0 = MathHelper.c(this.u);
        int i1 = MathHelper.c(this.w);

        if (this.q.a(i0, i1).j() > 1.0F) {
            this.a(DamageSource.b, 1.0F);
        }

        for (i0 = 0; i0 < 4; ++i0) {
            i1 = MathHelper.c(this.u + (double)((float)(i0 % 2 * 2 - 1) * 0.25F));
            int i2 = MathHelper.c(this.v);
            int i3 = MathHelper.c(this.w + (double)((float)(i0 / 2 % 2 * 2 - 1) * 0.25F));

            if (this.q.a(i1, i2, i3) == 0 && this.q.a(i1, i3).j() < 0.8F && Block.aX.c(this.q, i1, i2, i3)) {
                this.q.c(i1, i2, i3, Block.aX.cF);
            }
        }
    }

    protected int s() {
        return Item.aF.cv;
    }

    protected void b(boolean flag0, int i0) {
        int i1 = this.ab.nextInt(16);

        for (int i2 = 0; i2 < i1; ++i2) {
            this.b(Item.aF.cv, 1);
        }
    }

    public void a(EntityLivingBase entitylivingbase, float f0) {
        EntitySnowball entitysnowball = new EntitySnowball(this.q, this);
        double d0 = entitylivingbase.u - this.u;
        double d1 = entitylivingbase.v + (double)entitylivingbase.f() - 1.100000023841858D - entitysnowball.v;
        double d2 = entitylivingbase.w - this.w;
        float f1 = MathHelper.a(d0 * d0 + d2 * d2) * 0.2F;

        entitysnowball.c(d0, d1 + (double)f1, d2, 1.6F, 12.0F);
        this.a("random.bow", 1.0F, 1.0F / (this.aD().nextFloat() * 0.4F + 0.8F));
        this.q.d((Entity)entitysnowball);
    }
}
