package net.minecraft.server;

import net.canarymod.api.entity.living.CanaryIronGolem;

public class EntityIronGolem extends EntityGolem {

    private int bq;
    public Village bp; // CanaryMod package -> public
    private int br;
    private int bs;

    public EntityIronGolem(World world) {
        super(world);
        this.a(1.4F, 2.9F);
        this.k().a(true);
        this.c.a(1, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.c.a(2, new EntityAIMoveTowardsTarget(this, 0.9D, 32.0F));
        this.c.a(3, new EntityAIMoveThroughVillage(this, 0.6D, true));
        this.c.a(4, new EntityAIMoveTwardsRestriction(this, 1.0D));
        this.c.a(5, new EntityAILookAtVillager(this));
        this.c.a(6, new EntityAIWander(this, 0.6D));
        this.c.a(7, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
        this.c.a(8, new EntityAILookIdle(this));
        this.d.a(1, new EntityAIDefendVillage(this));
        this.d.a(2, new EntityAIHurtByTarget(this, false));
        this.d.a(3, new EntityAINearestAttackableTarget(this, EntityLiving.class, 0, false, true, IMob.a));
        this.entity = new CanaryIronGolem(this); // CanaryMod: Warp Entity
    }

    protected void a() {
        super.a();
        this.ah.a(16, Byte.valueOf((byte) 0));
    }

    public boolean bb() {
        return true;
    }

    protected void bg() {
        if (--this.bq <= 0) {
            this.bq = 70 + this.ab.nextInt(50);
            this.bp = this.q.A.a(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w), 32);
            if (this.bp == null) {
                this.bN();
            } else {
                ChunkCoordinates chunkcoordinates = this.bp.a();

                this.b(chunkcoordinates.a, chunkcoordinates.b, chunkcoordinates.c, (int) ((float) this.bp.b() * 0.6F));
            }
        }

        super.bg();
    }

    protected void ax() {
        super.ax();
        this.a(SharedMonsterAttributes.a).a(100.0D);
        this.a(SharedMonsterAttributes.d).a(0.25D);
    }

    protected int h(int i0) {
        return i0;
    }

    protected void n(Entity entity) {
        if (entity instanceof IMob && this.aB().nextInt(20) == 0) {
            this.c((EntityLivingBase) entity);
        }

        super.n(entity);
    }

    public void c() {
        super.c();
        if (this.br > 0) {
            --this.br;
        }

        if (this.bs > 0) {
            --this.bs;
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
        return this.bS() && EntityPlayer.class.isAssignableFrom(oclass0) ? false : super.a(oclass0);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("PlayerCreated", this.bS());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.j(nbttagcompound.n("PlayerCreated"));
    }

    public boolean m(Entity entity) {
        this.br = 10;
        this.q.a((Entity) this, (byte) 4);
        boolean flag0 = entity.a(DamageSource.a((EntityLivingBase) this), (float) (7 + this.ab.nextInt(15)));

        if (flag0) {
            entity.y += 0.4000000059604645D;
        }

        this.a("mob.irongolem.throw", 1.0F, 1.0F);
        return flag0;
    }

    public Village bP() {
        return this.bp;
    }

    public void a(boolean flag0) {
        this.bs = flag0 ? 400 : 0;
        this.q.a((Entity) this, (byte) 11);
    }

    protected String r() {
        return "none";
    }

    protected String aK() {
        return "mob.irongolem.hit";
    }

    protected String aL() {
        return "mob.irongolem.death";
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.irongolem.walk", 1.0F, 1.0F);
    }

    protected void b(boolean flag0, int i0) {
        int i1 = this.ab.nextInt(3);

        int i2;

        for (i2 = 0; i2 < i1; ++i2) {
            this.b(Block.aj.cF, 1);
        }

        i2 = 3 + this.ab.nextInt(3);

        for (int i3 = 0; i3 < i2; ++i3) {
            this.b(Item.q.cv, 1);
        }
    }

    public int bR() {
        return this.bs;
    }

    public boolean bS() {
        return (this.ah.a(16) & 1) != 0;
    }

    public void j(boolean flag0) {
        byte b0 = this.ah.a(16);

        if (flag0) {
            this.ah.b(16, Byte.valueOf((byte) (b0 | 1)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (b0 & -2)));
        }
    }

    public void a(DamageSource damagesource) {
        if (!this.bS() && this.aS != null && this.bp != null) {
            this.bp.a(this.aS.c_(), -5);
        }

        super.a(damagesource);
    }
}
