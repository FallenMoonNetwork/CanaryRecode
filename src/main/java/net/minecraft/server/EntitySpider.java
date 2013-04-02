package net.minecraft.server;


import net.canarymod.api.entity.living.monster.CanarySpider;


public class EntitySpider extends EntityMob {

    public EntitySpider(World world) {
        super(world);
        this.aH = "/mob/spider.png";
        this.a(1.4F, 0.9F);
        this.bI = 0.8F;
        this.maxHealth = 16; // CanaryMod: initialize
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

    public int aW() {
        return maxHealth; // CanaryMod: custom Max
    }

    public double W() {
        return (double) this.P * 0.75D - 0.5D;
    }

    protected Entity j() {
        float f0 = this.c(1.0F);

        if (f0 < 0.5F) {
            double d0 = 16.0D;

            return this.q.b(this, d0);
        } else {
            return null;
        }
    }

    protected String bb() {
        return "mob.spider.say";
    }

    protected String bc() {
        return "mob.spider.say";
    }

    protected String bd() {
        return "mob.spider.death";
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.spider.step", 0.15F, 1.0F);
    }

    protected void a(Entity entity, float f0) {
        float f1 = this.c(1.0F);

        if (f1 > 0.5F && this.ab.nextInt(100) == 0) {
            this.a_ = null;
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

    protected int be() {
        return Item.L.cp;
    }

    protected void a(boolean flag0, int i0) {
        super.a(flag0, i0);
        if (flag0 && (this.ab.nextInt(3) == 0 || this.ab.nextInt(1 + i0) > 0)) {
            this.b(Item.bv.cp, 1);
        }
    }

    public boolean g_() {
        return this.o();
    }

    public void al() {}

    public EnumCreatureAttribute bF() {
        return EnumCreatureAttribute.c;
    }

    public boolean e(PotionEffect potioneffect) {
        return potioneffect.a() == Potion.u.H ? false : super.e(potioneffect);
    }

    public boolean o() {
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

    public void bJ() {
        if (this.q.s.nextInt(100) == 0) {
            EntitySkeleton entityskeleton = new EntitySkeleton(this.q);

            entityskeleton.b(this.u, this.v, this.w, this.A, 0.0F);
            entityskeleton.bJ();
            this.q.d((Entity) entityskeleton);
            entityskeleton.a((Entity) this);
        }
    }
}
