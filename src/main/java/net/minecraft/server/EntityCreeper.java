package net.minecraft.server;


import net.canarymod.api.entity.living.monster.CanaryCreeper;


public class EntityCreeper extends EntityMob {

    private int d;
    private int e;
    public int f = 30; // CanaryMod: private => public // Fuse
    public int g = 3; // CanaryMod: private => public // Power

    public EntityCreeper(World world) {
        super(world);
        this.aH = "/mob/creeper.png";
        this.bo.a(1, new EntityAISwimming(this));
        this.bo.a(2, new EntityAICreeperSwell(this));
        this.bo.a(3, new EntityAIAvoidEntity(this, EntityOcelot.class, 6.0F, 0.25F, 0.3F));
        this.bo.a(4, new EntityAIAttackOnCollide(this, 0.25F, false));
        this.bo.a(5, new EntityAIWander(this, 0.2F));
        this.bo.a(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.bo.a(6, new EntityAILookIdle(this));
        this.bp.a(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
        this.bp.a(2, new EntityAIHurtByTarget(this, false));
        this.maxHealth = 20; // CanaryMod: initialize
        this.entity = new CanaryCreeper(this); // CanaryMod: Wrap Entity
    }

    public boolean bh() {
        return true;
    }

    public int ar() {
        return this.aJ() == null ? 3 : 3 + (this.aS - 1);
    }

    protected void a(float f0) {
        super.a(f0);
        this.e = (int) ((float) this.e + f0 * 1.5F);
        if (this.e > this.f - 5) {
            this.e = this.f - 5;
        }
    }

    public int aW() {
        return maxHealth; // CanaryMod: custom Max
    }

    protected void a() {
        super.a();
        this.ah.a(16, Byte.valueOf((byte) -1));
        this.ah.a(17, Byte.valueOf((byte) 0));
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.ah.a(17) == 1) {
            nbttagcompound.a("powered", true);
        }

        nbttagcompound.a("Fuse", (short) this.f);
        nbttagcompound.a("ExplosionRadius", (byte) this.g);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.ah.b(17, Byte.valueOf((byte) (nbttagcompound.n("powered") ? 1 : 0)));
        if (nbttagcompound.b("Fuse")) {
            this.f = nbttagcompound.d("Fuse");
        }

        if (nbttagcompound.b("ExplosionRadius")) {
            this.g = nbttagcompound.c("ExplosionRadius");
        }
    }

    public void l_() {
        if (this.R()) {
            this.d = this.e;
            int i0 = this.o();

            if (i0 > 0 && this.e == 0) {
                this.a("random.fuse", 1.0F, 0.5F);
            }

            this.e += i0;
            if (this.e < 0) {
                this.e = 0;
            }

            if (this.e >= this.f) {
                this.e = this.f;
                if (!this.q.I) {
                    boolean flag0 = this.q.M().b("mobGriefing");

                    if (this.m()) {
                        this.q.a(this, this.u, this.v, this.w, (float) (this.g * 2), flag0);
                    } else {
                        this.q.a(this, this.u, this.v, this.w, (float) this.g, flag0);
                    }

                    this.w();
                }
            }
        }

        super.l_();
    }

    protected String bc() {
        return "mob.creeper.say";
    }

    protected String bd() {
        return "mob.creeper.death";
    }

    public void a(DamageSource damagesource) {
        super.a(damagesource);
        if (damagesource.i() instanceof EntitySkeleton) {
            int i0 = Item.cd.cp + this.ab.nextInt(Item.co.cp - Item.cd.cp + 1);

            this.b(i0, 1);
        }
    }

    public boolean m(Entity entity) {
        return true;
    }

    public boolean m() {
        return this.ah.a(17) == 1;
    }

    protected int be() {
        return Item.N.cp;
    }

    public int o() {
        return this.ah.a(16);
    }

    public void a(int i0) {
        this.ah.b(16, Byte.valueOf((byte) i0));
    }

    public void a(EntityLightningBolt entitylightningbolt) {
        super.a(entitylightningbolt);
        this.ah.b(17, Byte.valueOf((byte) 1));
    }

    // CanaryMod: Set Charge to Creeper
    public void setCharged(boolean charged) {
        this.ah.b(17, Byte.valueOf(charged ? (byte) 1 : (byte) 0));
    }
}
