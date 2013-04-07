package net.minecraft.server;


import net.canarymod.api.entity.living.animal.CanaryWolf;
import net.canarymod.hook.entity.EntityTameHook;


public class EntityWolf extends EntityTameable {

    private float e;
    private float f;
    private boolean g;
    private boolean h;
    private float i;
    private float j;

    public EntityWolf(World world) {
        super(world);
        this.aH = "/mob/wolf.png";
        this.a(0.6F, 0.8F);
        this.bI = 0.3F;
        this.aC().a(true);
        this.bo.a(1, new EntityAISwimming(this));
        this.bo.a(2, this.d);
        this.bo.a(3, new EntityAILeapAtTarget(this, 0.4F));
        this.bo.a(4, new EntityAIAttackOnCollide(this, this.bI, true));
        this.bo.a(5, new EntityAIFollowOwner(this, this.bI, 10.0F, 2.0F));
        this.bo.a(6, new EntityAIMate(this, this.bI));
        this.bo.a(7, new EntityAIWander(this, this.bI));
        this.bo.a(8, new EntityAIBeg(this, 8.0F));
        this.bo.a(9, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.bo.a(9, new EntityAILookIdle(this));
        this.bp.a(1, new EntityAIOwnerHurtByTarget(this));
        this.bp.a(2, new EntityAIOwnerHurtTarget(this));
        this.bp.a(3, new EntityAIHurtByTarget(this, true));
        this.bp.a(4, new EntityAITargetNonTamed(this, EntitySheep.class, 16.0F, 200, false));
        this.entity = new CanaryWolf(this); // CanaryMod: Wrap Entity
    }

    public boolean bh() {
        return true;
    }

    public void b(EntityLiving entityliving) {
        super.b(entityliving);
        if (entityliving instanceof EntityPlayer) {
            this.l(true);
        }
    }

    protected void bp() {
        this.ah.b(18, Integer.valueOf(this.aX()));
    }

    public int aW() { // TODO: Wild and Tame Max
        return this.m() ? 20 : 8;
    }

    protected void a() {
        super.a();
        this.ah.a(18, new Integer(this.aX()));
        this.ah.a(19, new Byte((byte) 0));
        this.ah.a(20, new Byte((byte) BlockCloth.g_(1)));
    }

    protected void a(int i0, int i1, int i2, int i3) {
        this.a("mob.wolf.step", 0.15F, 1.0F);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Angry", this.bU());
        nbttagcompound.a("CollarColor", (byte) this.bV());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.l(nbttagcompound.n("Angry"));
        if (nbttagcompound.b("CollarColor")) {
            this.s(nbttagcompound.c("CollarColor"));
        }
    }

    protected boolean bm() {
        return this.bU();
    }

    protected String bb() {
        return this.bU() ? "mob.wolf.growl" : (this.ab.nextInt(3) == 0 ? (this.m() && this.ah.c(18) < 10 ? "mob.wolf.whine" : "mob.wolf.panting") : "mob.wolf.bark");
    }

    protected String bc() {
        return "mob.wolf.hurt";
    }

    protected String bd() {
        return "mob.wolf.death";
    }

    protected float ba() {
        return 0.4F;
    }

    protected int be() {
        return -1;
    }

    public void c() {
        super.c();
        if (!this.q.I && this.g && !this.h && !this.k() && this.F) {
            this.h = true;
            this.i = 0.0F;
            this.j = 0.0F;
            this.q.a((Entity) this, (byte) 8);
        }
    }

    public void l_() {
        super.l_();
        this.f = this.e;
        if (this.bW()) {
            this.e += (1.0F - this.e) * 0.4F;
        } else {
            this.e += (0.0F - this.e) * 0.4F;
        }

        if (this.bW()) {
            this.bJ = 10;
        }

        if (this.F()) {
            this.g = true;
            this.h = false;
            this.i = 0.0F;
            this.j = 0.0F;
        } else if ((this.g || this.h) && this.h) {
            if (this.i == 0.0F) {
                this.a("mob.wolf.shake", this.ba(), (this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F);
            }

            this.j = this.i;
            this.i += 0.05F;
            if (this.j >= 2.0F) {
                this.g = false;
                this.h = false;
                this.j = 0.0F;
                this.i = 0.0F;
            }

            if (this.i > 0.4F) {
                float f0 = (float) this.E.b;
                int i0 = (int) (MathHelper.a((this.i - 0.4F) * 3.1415927F) * 7.0F);

                for (int i1 = 0; i1 < i0; ++i1) {
                    float f1 = (this.ab.nextFloat() * 2.0F - 1.0F) * this.O * 0.5F;
                    float f2 = (this.ab.nextFloat() * 2.0F - 1.0F) * this.O * 0.5F;

                    this.q.a("splash", this.u + (double) f1, (double) (f0 + 0.8F), this.w + (double) f2, this.x, this.y, this.z);
                }
            }
        }
    }

    public float e() {
        return this.P * 0.8F;
    }

    public int bs() {
        return this.n() ? 20 : super.bs();
    }

    public boolean a(DamageSource damagesource, int i0) {
        if (this.aq()) {
            return false;
        } else {
            Entity entity = damagesource.i();

            this.d.a(false);
            if (entity != null && !(entity instanceof EntityPlayer) && !(entity instanceof EntityArrow)) {
                i0 = (i0 + 1) / 2;
            }

            return super.a(damagesource, i0);
        }
    }

    public boolean m(Entity entity) {
        int i0 = this.m() ? 4 : 2;

        return entity.a(DamageSource.a((EntityLiving) this), i0);
    }

    public boolean a_(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.bK.h();

        if (this.m()) {
            if (itemstack != null) {
                if (Item.f[itemstack.c] instanceof ItemFood) {
                    ItemFood itemfood = (ItemFood) Item.f[itemstack.c];

                    if (itemfood.i() && this.ah.c(18) < 20) {
                        if (!entityplayer.ce.d) {
                            --itemstack.a;
                        }

                        this.j(itemfood.g());
                        if (itemstack.a <= 0) {
                            entityplayer.bK.a(entityplayer.bK.c, (ItemStack) null);
                        }

                        return true;
                    }
                } else if (itemstack.c == Item.aX.cp) {
                    int i0 = BlockCloth.g_(itemstack.k());

                    if (i0 != this.bV()) {
                        this.s(i0);
                        if (!entityplayer.ce.d && --itemstack.a <= 0) {
                            entityplayer.bK.a(entityplayer.bK.c, (ItemStack) null);
                        }

                        return true;
                    }
                }
            }

            if (entityplayer.bS.equalsIgnoreCase(this.o()) && !this.q.I && !this.c(itemstack)) {
                this.d.a(!this.n());
                this.bG = false;
                this.a((PathEntity) null);
            }
        } else if (itemstack != null && itemstack.c == Item.aY.cp && !this.bU()) {
            if (!entityplayer.ce.d) {
                --itemstack.a;
            }

            if (itemstack.a <= 0) {
                entityplayer.bK.a(entityplayer.bK.c, (ItemStack) null);
            }

            if (!this.q.I) {
                // CanaryMod: EntityTame
                EntityTameHook hook = new EntityTameHook((net.canarymod.api.entity.living.animal.EntityAnimal) this.getCanaryEntity(), ((EntityPlayerMP) entityplayer).getPlayer(), this.ab.nextInt(3) == 0);

                if (hook.isTamed() && !hook.isCanceled()) {
                    //
                    this.j(true);
                    this.a((PathEntity) null);
                    this.b((EntityLiving) null);
                    this.d.a(true);
                    this.b(20);
                    this.a(entityplayer.bS);
                    this.i(true);
                    this.q.a((Entity) this, (byte) 7);
                } else {
                    this.i(false);
                    this.q.a((Entity) this, (byte) 6);
                }
            }

            return true;
        }

        return super.a_(entityplayer);
    }

    public boolean c(ItemStack itemstack) {
        return itemstack == null ? false : (!(Item.f[itemstack.c] instanceof ItemFood) ? false : ((ItemFood) Item.f[itemstack.c]).i());
    }

    public int by() {
        return 8;
    }

    public boolean bU() {
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

    public int bV() {
        return this.ah.a(20) & 15;
    }

    public void s(int i0) {
        this.ah.b(20, Byte.valueOf((byte) (i0 & 15)));
    }

    public EntityWolf b(EntityAgeable entityageable) {
        EntityWolf entitywolf = new EntityWolf(this.q);
        String s0 = this.o();

        if (s0 != null && s0.trim().length() > 0) {
            entitywolf.a(s0);
            entitywolf.j(true);
        }

        return entitywolf;
    }

    public void m(boolean flag0) {
        byte b0 = this.ah.a(19);

        if (flag0) {
            this.ah.b(19, Byte.valueOf((byte) 1));
        } else {
            this.ah.b(19, Byte.valueOf((byte) 0));
        }
    }

    public boolean a(EntityAnimal entityanimal) {
        if (entityanimal == this) {
            return false;
        } else if (!this.m()) {
            return false;
        } else if (!(entityanimal instanceof EntityWolf)) {
            return false;
        } else {
            EntityWolf entitywolf = (EntityWolf) entityanimal;

            return !entitywolf.m() ? false : (entitywolf.n() ? false : this.r() && entitywolf.r());
        }
    }

    public boolean bW() {
        return this.ah.a(19) == 1;
    }

    public EntityAgeable a(EntityAgeable entityageable) {
        return this.b(entityageable);
    }
}
