package net.minecraft.server;


import net.canarymod.api.entity.living.CanaryIronGolem;


public class EntityIronGolem extends EntityGolem {

    private int e = 0;
    public Village d = null; // CanaryMod package => public
    private int f;
    private int g;

    public EntityIronGolem(World world) {
        super(world);
        this.aH = "/mob/villager_golem.png";
        this.a(1.4F, 2.9F);
        this.aC().a(true);
        this.bo.a(1, new EntityAIAttackOnCollide(this, 0.25F, true));
        this.bo.a(2, new EntityAIMoveTowardsTarget(this, 0.22F, 32.0F));
        this.bo.a(3, new EntityAIMoveThroughVillage(this, 0.16F, true));
        this.bo.a(4, new EntityAIMoveTwardsRestriction(this, 0.16F));
        this.bo.a(5, new EntityAILookAtVillager(this));
        this.bo.a(6, new EntityAIWander(this, 0.16F));
        this.bo.a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.bo.a(8, new EntityAILookIdle(this));
        this.bp.a(1, new EntityAIDefendVillage(this));
        this.bp.a(2, new EntityAIHurtByTarget(this, false));
        this.bp.a(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 16.0F, 0, false, true, IMob.a));
        this.entity = new CanaryIronGolem(this); // CanaryMod: Warp Entity
    }

    protected void a() {
        super.a();
        this.ah.a(16, Byte.valueOf((byte) 0));
    }

    public boolean bh() {
        return true;
    }

    protected void bp() {
        if (--this.e <= 0) {
            this.e = 70 + this.ab.nextInt(50);
            this.d = this.q.A.a(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w), 32);
            if (this.d == null) {
                this.aO();
            } else {
                ChunkCoordinates chunkcoordinates = this.d.a();

                this.b(chunkcoordinates.a, chunkcoordinates.b, chunkcoordinates.c, (int) ((float) this.d.b() * 0.6F));
            }
        }

        super.bp();
    }

    public int aW() {
        return maxHealth == 0 ? 100 : maxHealth; // CanaryMod: custom Max Health
    }

    protected int h(int i0) {
        return i0;
    }

    protected void o(Entity entity) {
        if (entity instanceof IMob && this.aE().nextInt(20) == 0) {
            this.b((EntityLiving) entity);
        }

        super.o(entity);
    }

    public void c() {
        super.c();
        if (this.f > 0) {
            --this.f;
        }

        if (this.g > 0) {
            --this.g;
        }

        if (this.x * this.x + this.z * this.z > 2.500000277905201E-7D && this.ab.nextInt(5) == 0) {
            int i0 = MathHelper.c(this.u);
            int i1 = MathHelper.c(this.v - 0.20000000298023224D - (double) this.N);
            int i2 = MathHelper.c(this.w);
            int i3 = this.q.a(i0, i1, i2);

            if (i3 > 0) {
                this.q.a("tilecrack_" + i3 + "_" + this.q.h(i0, i1, i2), this.u + ((double) this.ab.nextFloat() - 0.5D) * (double) this.O, this.E.b + 0.1D, this.w + ((double) this.ab.nextFloat() - 0.5D) * (double) this.O, 4.0D * ((double) this.ab.nextFloat() - 0.5D), 0.5D, ((double) this.ab.nextFloat() - 0.5D) * 4.0D);
            }
        }
    }

    public boolean a(Class oclass0) {
        return this.p() && EntityPlayer.class.isAssignableFrom(oclass0) ? false : super.a(oclass0);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("PlayerCreated", this.p());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.i(nbttagcompound.n("PlayerCreated"));
    }

    public boolean m(Entity entity) {
        this.f = 10;
        this.q.a((Entity) this, (byte) 4);
        boolean flag0 = entity.a(DamageSource.a((EntityLiving) this), 7 + this.ab.nextInt(15));

        if (flag0) {
            entity.y += 0.4000000059604645D;
        }

        this.a("mob.irongolem.throw", 1.0F, 1.0F);
        return flag0;
    }

    public Village m() {
        return this.d;
    }

    public void a(boolean flag0) {
        this.g = flag0 ? 400 : 0;
        this.q.a((Entity) this, (byte) 11);
    }

    protected String bb() {
        return "none";
    }

    protected String bc() {
        return "mob.irongolem.hit";
    }

    protected String bd() {
        return "mob.irongolem.death";
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.irongolem.walk", 1.0F, 1.0F);
    }

    protected void a(boolean flag0, int i0) {
        int i1 = this.ab.nextInt(3);

        int i2;

        for (i2 = 0; i2 < i1; ++i2) {
            this.b(Block.ai.cz, 1);
        }

        i2 = 3 + this.ab.nextInt(3);

        for (int i3 = 0; i3 < i2; ++i3) {
            this.b(Item.p.cp, 1);
        }
    }

    public int o() {
        return this.g;
    }

    public boolean p() {
        return (this.ah.a(16) & 1) != 0;
    }

    public void i(boolean flag0) {
        byte b0 = this.ah.a(16);

        if (flag0) {
            this.ah.b(16, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (b0 & -2)));
        }
    }

    public void a(DamageSource damagesource) {
        if (!this.p() && this.bk != null && this.d != null) {
            this.d.a(this.bk.c_(), -5);
        }

        super.a(damagesource);
    }
}
