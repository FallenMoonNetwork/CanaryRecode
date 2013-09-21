package net.minecraft.server;

import net.canarymod.api.entity.living.monster.CanaryCreeper;

public class EntityCreeper extends EntityMob {

    private int bp;
    private int bq;
    public int br = 30; // CanaryMod: private -> public // Fuse
    public int bs = 3; // CanaryMod: private -> public // Power

    public EntityCreeper(World world) {
        super(world);
        this.c.a(1, new EntityAISwimming(this));
        this.c.a(2, new EntityAICreeperSwell(this));
        this.c.a(3, new EntityAIAvoidEntity(this, EntityOcelot.class, 6.0F, 1.0D, 1.2D));
        this.c.a(4, new EntityAIAttackOnCollide(this, 1.0D, false));
        this.c.a(5, new EntityAIWander(this, 0.8D));
        this.c.a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.c.a(6, new EntityAILookIdle(this));
        this.d.a(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.d.a(2, new EntityAIHurtByTarget(this, false));
        this.entity = new CanaryCreeper(this); // CanaryMod: Wrap Entity
    }

    protected void az() {
        super.az();
        this.a(SharedMonsterAttributes.d).a(0.25D);
    }

    public boolean bf() {
        return true;
    }

    public int as() {
        return this.m() == null ? 3 : 3 + (int)(this.aN() - 1.0F);
    }

    protected void b(float f0) {
        super.b(f0);
        this.bq = (int)((float)this.bq + f0 * 1.5F);
        if (this.bq > this.br - 5) {
            this.bq = this.br - 5;
        }
    }

    protected void a() {
        super.a();
        this.ah.a(16, Byte.valueOf((byte)-1));
        this.ah.a(17, Byte.valueOf((byte)0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.ah.a(17) == 1) {
            nbttagcompound.a("powered", true);
        }

        nbttagcompound.a("Fuse", (short)this.br);
        nbttagcompound.a("ExplosionRadius", (byte)this.bs);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.ah.b(17, Byte.valueOf((byte)(nbttagcompound.n("powered") ? 1 : 0)));
        if (nbttagcompound.b("Fuse")) {
            this.br = nbttagcompound.d("Fuse");
        }

        if (nbttagcompound.b("ExplosionRadius")) {
            this.bs = nbttagcompound.c("ExplosionRadius");
        }
    }

    public void l_() {
        if (this.T()) {
            this.bp = this.bq;
            int i0 = this.bV();

            if (i0 > 0 && this.bq == 0) {
                this.a("random.fuse", 1.0F, 0.5F);
            }

            this.bq += i0;
            if (this.bq < 0) {
                this.bq = 0;
            }

            if (this.bq >= this.br) {
                this.bq = this.br;
                if (!this.q.I) {
                    boolean flag0 = this.q.O().b("mobGriefing");

                    if (this.bT()) {
                        this.q.a(this, this.u, this.v, this.w, (float)(this.bs * 2), flag0);
                    }
                    else {
                        this.q.a(this, this.u, this.v, this.w, (float)this.bs, flag0);
                    }

                    this.x();
                }
            }
        }

        super.l_();
    }

    protected String aO() {
        return "mob.creeper.say";
    }

    protected String aP() {
        return "mob.creeper.death";
    }

    public void a(DamageSource damagesource) {
        super.a(damagesource);
        if (damagesource.i() instanceof EntitySkeleton) {
            int i0 = Item.cj.cv + this.ab.nextInt(Item.cu.cv - Item.cj.cv + 1);

            this.b(i0, 1);
        }
    }

    public boolean m(Entity entity) {
        return true;
    }

    public boolean bT() {
        return this.ah.a(17) == 1;
    }

    protected int s() {
        return Item.O.cv;
    }

    public int bV() {
        return this.ah.a(16);
    }

    public void a(int i0) {
        this.ah.b(16, Byte.valueOf((byte)i0));
    }

    public void a(EntityLightningBolt entitylightningbolt) {
        super.a(entitylightningbolt);
        this.ah.b(17, Byte.valueOf((byte)1));
    }

    // CanaryMod: Set Charge to Creeper
    public void setCharged(boolean charged) {
        this.ah.b(17, Byte.valueOf(charged ? (byte)1 : (byte)0));
    }
}
