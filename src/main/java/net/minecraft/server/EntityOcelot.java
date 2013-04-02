package net.minecraft.server;

import net.canarymod.api.entity.living.animal.CanaryOcelot;

public class EntityOcelot extends EntityTameable {

    private EntityAITempt e;

    public EntityOcelot(World world) {
        super(world);
        this.aH = "/mob/ozelot.png";
        this.a(0.6F, 0.8F);
        this.aC().a(true);
        this.bo.a(1, new EntityAISwimming(this));
        this.bo.a(2, this.d);
        this.bo.a(3, this.e = new EntityAITempt(this, 0.18F, Item.aV.cp, true));
        this.bo.a(4, new EntityAIAvoidEntity(this, EntityPlayer.class, 16.0F, 0.23F, 0.4F));
        this.bo.a(5, new EntityAIFollowOwner(this, 0.3F, 10.0F, 5.0F));
        this.bo.a(6, new EntityAIOcelotSit(this, 0.4F));
        this.bo.a(7, new EntityAILeapAtTarget(this, 0.3F));
        this.bo.a(8, new EntityAIOcelotAttack(this));
        this.bo.a(9, new EntityAIMate(this, 0.23F));
        this.bo.a(10, new EntityAIWander(this, 0.23F));
        this.bo.a(11, new EntityAIWatchClosest(this, EntityPlayer.class, 10.0F));
        this.bp.a(1, new EntityAITargetNonTamed(this, EntityChicken.class, 14.0F, 750, false));
        this.entity = new CanaryOcelot(this); // CanaryMod: Wrap Entity
    }

    protected void a() {
        super.a();
        this.ah.a(18, Byte.valueOf((byte) 0));
    }

    public void bp() {
        if (this.aA().a()) {
            float f0 = this.aA().b();

            if (f0 == 0.18F) {
                this.b(true);
                this.c(false);
            } else if (f0 == 0.4F) {
                this.b(false);
                this.c(true);
            } else {
                this.b(false);
                this.c(false);
            }
        } else {
            this.b(false);
            this.c(false);
        }
    }

    protected boolean bm() {
        return !this.m();
    }

    public boolean bh() {
        return true;
    }

    public int aW() {
        return maxHealth == 0 ? 10 : maxHealth; // CanaryMod: custom Max Health
    }

    protected void a(float f0) {}

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("CatType", this.t());
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.s(nbttagcompound.e("CatType"));
    }

    protected String bb() {
        return this.m() ? (this.r() ? "mob.cat.purr" : (this.ab.nextInt(4) == 0 ? "mob.cat.purreow" : "mob.cat.meow")) : "";
    }

    protected String bc() {
        return "mob.cat.hitt";
    }

    protected String bd() {
        return "mob.cat.hitt";
    }

    protected float ba() {
        return 0.4F;
    }

    protected int be() {
        return Item.aG.cp;
    }

    public boolean m(Entity entity) {
        return entity.a(DamageSource.a((EntityLiving) this), 3);
    }

    public boolean a(DamageSource damagesource, int i0) {
        if (this.aq()) {
            return false;
        } else {
            this.d.a(false);
            return super.a(damagesource, i0);
        }
    }

    protected void a(boolean flag0, int i0) {}

    public boolean a_(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.bK.h();

        if (this.m()) {
            if (entityplayer.bS.equalsIgnoreCase(this.o()) && !this.q.I && !this.c(itemstack)) {
                this.d.a(!this.n());
            }
        } else if (this.e.f() && itemstack != null && itemstack.c == Item.aV.cp && entityplayer.e(this) < 9.0D) {
            if (!entityplayer.ce.d) {
                --itemstack.a;
            }

            if (itemstack.a <= 0) {
                entityplayer.bK.a(entityplayer.bK.c, (ItemStack) null);
            }

            if (!this.q.I) {
                if (this.ab.nextInt(3) == 0) {
                    this.j(true);
                    this.s(1 + this.q.s.nextInt(3));
                    this.a(entityplayer.bS);
                    this.i(true);
                    this.d.a(true);
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

    public EntityOcelot b(EntityAgeable entityageable) {
        EntityOcelot entityocelot = new EntityOcelot(this.q);

        if (this.m()) {
            entityocelot.a(this.o());
            entityocelot.j(true);
            entityocelot.s(this.t());
        }

        return entityocelot;
    }

    public boolean c(ItemStack itemstack) {
        return itemstack != null && itemstack.c == Item.aV.cp;
    }

    public boolean a(EntityAnimal entityanimal) {
        if (entityanimal == this) {
            return false;
        } else if (!this.m()) {
            return false;
        } else if (!(entityanimal instanceof EntityOcelot)) {
            return false;
        } else {
            EntityOcelot entityocelot = (EntityOcelot) entityanimal;

            return !entityocelot.m() ? false : this.r() && entityocelot.r();
        }
    }

    public int t() {
        return this.ah.a(18);
    }

    public void s(int i0) {
        this.ah.b(18, Byte.valueOf((byte) i0));
    }

    public boolean bv() {
        if (this.q.s.nextInt(3) == 0) {
            return false;
        } else {
            if (this.q.b(this.E) && this.q.a((Entity) this, this.E).isEmpty() && !this.q.d(this.E)) {
                int i0 = MathHelper.c(this.u);
                int i1 = MathHelper.c(this.E.b);
                int i2 = MathHelper.c(this.w);

                if (i1 < 63) {
                    return false;
                }

                int i3 = this.q.a(i0, i1 - 1, i2);

                if (i3 == Block.y.cz || i3 == Block.O.cz) {
                    return true;
                }
            }

            return false;
        }
    }

    public String am() {
        return this.bP() ? this.bO() : (this.m() ? "entity.Cat.name" : super.am());
    }

    public void bJ() {
        if (this.q.s.nextInt(7) == 0) {
            for (int i0 = 0; i0 < 2; ++i0) {
                EntityOcelot entityocelot = new EntityOcelot(this.q);

                entityocelot.b(this.u, this.v, this.w, this.A, 0.0F);
                entityocelot.a(-24000);
                this.q.d((Entity) entityocelot);
            }
        }
    }

    public EntityAgeable a(EntityAgeable entityageable) {
        return this.b(entityageable);
    }
}
