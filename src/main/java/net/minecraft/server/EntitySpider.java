package net.minecraft.server;

import net.canarymod.api.entity.living.monster.CanarySpider;

public class EntitySpider extends EntityMob {

    public EntitySpider(World world) {
        super(world);
        this.a(1.4F, 0.9F);
        this.entity = new CanarySpider(this); // CanaryMod: Wrap Entity
    }

    protected void a() {
        super.a();
        this.ah.a(16, new Byte((byte) 0));
    }

    public void l_() {
        super.l_();
        if (!this.q.I) {
            this.a(this.G);
        }
    }

    protected void ay() {
        super.ay();
        this.a(SharedMonsterAttributes.a).a(16.0D);
        this.a(SharedMonsterAttributes.d).a(0.800000011920929D);
    }

    protected Entity bL() {
        float f0 = this.d(1.0F);

        if (f0 < 0.5F) {
            double d0 = 16.0D;

            return this.q.b(this, d0);
        } else {
            return null;
        }
    }

    protected String r() {
        return "mob.spider.say";
    }

    protected String aN() {
        return "mob.spider.say";
    }

    protected String aO() {
        return "mob.spider.death";
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.spider.step", 0.15F, 1.0F);
    }

    protected void a(Entity entity, float f0) {
        float f1 = this.d(1.0F);

        if (f1 > 0.5F && this.ab.nextInt(100) == 0) {
            this.j = null;
        } else {
            if (f0 > 2.0F && f0 < 6.0F && this.ab.nextInt(10) == 0) {
                if (this.F) {
                    double d0 = entity.u - this.u;
                    double d1 = entity.w - this.w;
                    float f2 = MathHelper.a(d0 * d0 + d1 * d1);

                    this.x = d0 / (double) f2 * 0.5D * 0.800000011920929D + this.x * 0.20000000298023224D;
                    this.z = d1 / (double) f2 * 0.5D * 0.800000011920929D + this.z * 0.20000000298023224D;
                    this.y = 0.4000000059604645D;
                }
            } else {
                super.a(entity, f0);
            }
        }
    }

    protected int s() {
        return Item.M.cv;
    }

    protected void b(boolean flag0, int i0) {
        super.b(flag0, i0);
        if (flag0 && (this.ab.nextInt(3) == 0 || this.ab.nextInt(1 + i0) > 0)) {
            this.b(Item.bw.cv, 1);
        }
    }

    public boolean e() {
        return this.bT();
    }

    public void al() {}

    public EnumCreatureAttribute aX() {
        return EnumCreatureAttribute.c;
    }

    public boolean d(PotionEffect potioneffect) {
        return potioneffect.a() == Potion.u.H ? false : super.d(potioneffect);
    }

    public boolean bT() {
        return (this.ah.a(16) & 1) != 0;
    }

    public void a(boolean flag0) {
        byte b0 = this.ah.a(16);

        if (flag0) {
            b0 = (byte) (b0 | 1);
        } else {
            b0 &= -2;
        }

        this.ah.b(16, Byte.valueOf(b0));
    }

    public EntityLivingData a(EntityLivingData entitylivingdata) {
        Object object = super.a(entitylivingdata);

        if (this.q.s.nextInt(100) == 0) {
            EntitySkeleton entityskeleton = new EntitySkeleton(this.q);

            entityskeleton.b(this.u, this.v, this.w, this.A, 0.0F);
            entityskeleton.a((EntityLivingData) null);
            this.q.d((Entity) entityskeleton);
            entityskeleton.a((Entity) this);
        }

        if (object == null) {
            object = new SpiderEffectsGroupData();
            if (this.q.r > 2 && this.q.s.nextFloat() < 0.1F * this.q.b(this.u, this.v, this.w)) {
                ((SpiderEffectsGroupData) object).a(this.q.s);
            }
        }

        if (object instanceof SpiderEffectsGroupData) {
            int i0 = ((SpiderEffectsGroupData) object).a;

            if (i0 > 0 && Potion.a[i0] != null) {
                this.c(new PotionEffect(i0, Integer.MAX_VALUE));
            }
        }

        return (EntityLivingData) object;
    }
}
