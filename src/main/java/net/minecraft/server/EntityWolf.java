package net.minecraft.server;

import net.canarymod.api.entity.living.animal.CanaryWolf;
import net.canarymod.hook.entity.EntityTameHook;

public class EntityWolf extends EntityTameable {

    private float bq;
    private float br;
    private boolean bs;
    private boolean bt;
    private float bu;
    private float bv;

    public EntityWolf(World world) {
        super(world);
        this.a(0.6F, 0.8F);
        this.k().a(true);
        this.c.a(1, new EntityAISwimming(this));
        this.c.a(2, this.bp);
        this.c.a(3, new EntityAILeapAtTarget(this, 0.4F));
        this.c.a(4, new EntityAIAttackOnCollide(this, 1.0D, true));
        this.c.a(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
        this.c.a(6, new EntityAIMate(this, 1.0D));
        this.c.a(7, new EntityAIWander(this, 1.0D));
        this.c.a(8, new EntityAIBeg(this, 8.0F));
        this.c.a(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.c.a(9, new EntityAILookIdle(this));
        this.d.a(1, new EntityAIOwnerHurtByTarget(this));
        this.d.a(2, new EntityAIOwnerHurtTarget(this));
        this.d.a(3, new EntityAIHurtByTarget(this, true));
        this.d.a(4, new EntityAITargetNonTamed(this, EntitySheep.class, 200, false));
        this.j(false);
        this.entity = new CanaryWolf(this); // CanaryMod: Wrap Entity
    }

    protected void ay() {
        super.ay();
        this.a(SharedMonsterAttributes.d).a(0.30000001192092896D);
        if (this.bT()) {
            this.a(SharedMonsterAttributes.a).a(20.0D);
        } else {
            this.a(SharedMonsterAttributes.a).a(8.0D);
        }
    }

    public boolean be() {
        return true;
    }

    public void d(EntityLivingBase entitylivingbase) {
        super.d(entitylivingbase);
        if (entitylivingbase == null) {
            this.l(false);
        } else if (!this.bT()) {
            this.l(true);
        }
    }

    protected void bj() {
        this.ah.b(18, Float.valueOf(this.aM()));
    }

    protected void a() {
        super.a();
        this.ah.a(18, new Float(this.aM()));
        this.ah.a(19, new Byte((byte) 0));
        this.ah.a(20, new Byte((byte) BlockColored.j_(1)));
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.wolf.step", 0.15F, 1.0F);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Angry", this.cc());
        nbttagcompound.a("CollarColor", (byte) this.cd());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.l(nbttagcompound.n("Angry"));
        if (nbttagcompound.b("CollarColor")) {
            this.p(nbttagcompound.c("CollarColor"));
        }
    }

    protected String r() {
        return this.cc() ? "mob.wolf.growl" : (this.ab.nextInt(3) == 0 ? (this.bT() && this.ah.d(18) < 10.0F ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark");
    }

    protected String aN() {
        return "mob.wolf.hurt";
    }

    protected String aO() {
        return "mob.wolf.death";
    }

    protected float aZ() {
        return 0.4F;
    }

    protected int s() {
        return -1;
    }

    public void c() {
        super.c();
        if (!this.q.I && this.bs && !this.bt && !this.bM() && this.F) {
            this.bt = true;
            this.bu = 0.0F;
            this.bv = 0.0F;
            this.q.a((Entity) this, (byte) 8);
        }
    }

    public void l_() {
        super.l_();
        this.br = this.bq;
        if (this.ce()) {
            this.bq += (1.0F - this.bq) * 0.4F;
        } else {
            this.bq += (0.0F - this.bq) * 0.4F;
        }

        if (this.ce()) {
            this.g = 10;
        }

        if (this.F()) {
            this.bs = true;
            this.bt = false;
            this.bu = 0.0F;
            this.bv = 0.0F;
        } else if ((this.bs || this.bt) && this.bt) {
            if (this.bu == 0.0F) {
                this.a("mob.wolf.shake", this.aZ(), (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F);
            }

            this.bv = this.bu;
            this.bu += 0.05F;
            if (this.bv >= 2.0F) {
                this.bs = false;
                this.bt = false;
                this.bv = 0.0F;
                this.bu = 0.0F;
            }

            if (this.bu > 0.4F) {
                float f0 = (float) this.E.b;
                int i0 = (int) (MathHelper.a((this.bu - 0.4F) * 3.1415927F) * 7.0F);

                for (int i1 = 0; i1 < i0; ++i1) {
                    float f1 = (this.ab.nextFloat() * 2.0F - 1.0F) * this.O * 0.5F;
                    float f2 = (this.ab.nextFloat() * 2.0F - 1.0F) * this.O * 0.5F;

                    this.q.a("splash", this.u + (double) f1, (double) (f0 + 0.8F), this.w + (double) f2, this.x, this.y, this.z);
                }
            }
        }
    }

    public float f() {
        return this.P * 0.8F;
    }

    public int bp() {
        return this.bU() ? 20 : super.bp();
    }

    public boolean a(DamageSource damagesource, float f0) {
        if (this.aq()) {
            return false;
        } else {
            Entity entity = damagesource.i();

            this.bp.a(false);
            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
                f0 = (f0 + 1.0F) / 2.0F;
            }

            return super.a(damagesource, f0);
        }
    }

    public boolean m(Entity entity) {
        int i0 = this.bT() ? 4 : 2;

        return entity.a(DamageSource.a((EntityLivingBase) this), (float) i0);
    }

    public void j(boolean flag0) {
        super.j(flag0);
        if (flag0) {
            this.a(SharedMonsterAttributes.a).a(20.0D);
        } else {
            this.a(SharedMonsterAttributes.a).a(8.0D);
        }
    }

    public boolean a(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.bn.h();

        if (this.bT()) {
            if (itemstack != null) {
                if (Item.g[itemstack.d] instanceof ItemFood) {
                    ItemFood itemfood = (ItemFood) Item.g[itemstack.d];

                    if (itemfood.j() && this.ah.d(18) < 20.0F) {
                        if (!entityplayer.bG.d) {
                            --itemstack.b;
                        }

                        this.f((float) itemfood.g());
                        if (itemstack.b <= 0) {
                            entityplayer.bn.a(entityplayer.bn.c, (ItemStack) null);
                        }

                        return true;
                    }
                } else if (itemstack.d == Item.aY.cv) {
                    int i0 = BlockColored.j_(itemstack.k());

                    if (i0 != this.cd()) {
                        this.p(i0);
                        if (!entityplayer.bG.d && --itemstack.b <= 0) {
                            entityplayer.bn.a(entityplayer.bn.c, (ItemStack) null);
                        }

                        return true;
                    }
                }
            }

            if (entityplayer.c_().equalsIgnoreCase(this.h_()) && !this.q.I && !this.c(itemstack)) {
                this.bp.a(!this.bU());
                this.bd = false;
                this.a((PathEntity) null);
                this.b((Entity) null);
                this.d((EntityLivingBase) null);
            }
        } else if (itemstack != null && itemstack.d == Item.aZ.cv && !this.cc()) {
            if (!entityplayer.bG.d) {
                --itemstack.b;
            }

            if (itemstack.b <= 0) {
                entityplayer.bn.a(entityplayer.bn.c, (ItemStack) null);
            }

            if (!this.q.I) {
                // CanaryMod: EntityTame
                EntityTameHook hook = new EntityTameHook((net.canarymod.api.entity.living.animal.EntityAnimal) this.getCanaryEntity(), ((EntityPlayerMP) entityplayer).getPlayer(), this.ab.nextInt(3) == 0);

                if (hook.isTamed() && !hook.isCanceled()) {
                    //
                    this.j(true);
                    this.a((PathEntity) null);
                    this.d((EntityLivingBase) null);
                    this.bp.a(true);
                    this.g(20.0F);
                    this.b(entityplayer.c_());
                    this.i(true);
                    this.q.a((Entity) this, (byte) 7);
                } else {
                    this.i(false);
                    this.q.a((Entity) this, (byte) 6);
                }
            }

            return true;
        }

        return super.a(entityplayer);
    }

    public boolean c(ItemStack itemstack) {
        return itemstack == null ? false : (!(Item.g[itemstack.d] instanceof ItemFood) ? false : ((ItemFood) Item.g[itemstack.d]).j());
    }

    public int bv() {
        return 8;
    }

    public boolean cc() {
        return (this.ah.a(16) & 2) != 0;
    }

    public void l(boolean flag0) {
        byte b0 = this.ah.a(16);

        if (flag0) {
            this.ah.b(16, Byte.valueOf((byte) (b0 | 2)));
        } else {
            this.ah.b(16, Byte.valueOf((byte) (b0 & -3)));
        }
    }

    public int cd() {
        return this.ah.a(20) & 15;
    }

    public void p(int i0) {
        this.ah.b(20, Byte.valueOf((byte) (i0 & 15)));
    }

    public EntityWolf b(EntityAgeable entityageable) {
        EntityWolf entitywolf = new EntityWolf(this.q);
        String s0 = this.h_();

        if (s0 != null && s0.trim().length() > 0) {
            entitywolf.b(s0);
            entitywolf.j(true);
        }

        return entitywolf;
    }

    public void m(boolean flag0) {
        if (flag0) {
            this.ah.b(19, Byte.valueOf((byte) 1));
        } else {
            this.ah.b(19, Byte.valueOf((byte) 0));
        }
    }

    public boolean a(EntityAnimal entityanimal) {
        if (entityanimal == this) {
            return false;
        } else if (!this.bT()) {
            return false;
        } else if (!(entityanimal instanceof EntityWolf)) {
            return false;
        } else {
            EntityWolf entitywolf = (EntityWolf) entityanimal;

            return !entitywolf.bT() ? false : (entitywolf.bU() ? false : this.bY() && entitywolf.bY());
        }
    }

    public boolean ce() {
        return this.ah.a(19) == 1;
    }

    protected boolean t() {
        return !this.bT() && this.ac > 2400;
    }

    public boolean a(EntityLivingBase entitylivingbase, EntityLivingBase entitylivingbase1) {
        if (!(entitylivingbase instanceof EntityCreeper) && !(entitylivingbase instanceof EntityGhast)) {
            if (entitylivingbase instanceof EntityWolf) {
                EntityWolf entitywolf = (EntityWolf) entitylivingbase;

                if (entitywolf.bT() && entitywolf.bV() == entitylivingbase1) {
                    return false;
                }
            }

            return entitylivingbase instanceof EntityPlayer && entitylivingbase1 instanceof EntityPlayer && !((EntityPlayer) entitylivingbase1).a((EntityPlayer) entitylivingbase) ? false : !(entitylivingbase instanceof EntityHorse) || !((EntityHorse) entitylivingbase).bW();
        } else {
            return false;
        }
    }

    public EntityAgeable a(EntityAgeable entityageable) {
        return this.b(entityageable);
    }
}
