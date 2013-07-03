package net.minecraft.server;


import java.util.List;
import java.util.UUID;
import net.canarymod.Canary;
import net.canarymod.api.entity.living.monster.CanaryPigZombie;
import net.canarymod.hook.entity.MobTargetHook;


public class EntityPigZombie extends EntityZombie {

    private static final UUID bq = UUID.fromString("49455A49-7EC5-45BA-B886-3B90B23A1718");
    private static final AttributeModifier br = (new AttributeModifier(bq, "Attacking speed boost", 0.45D, 0)).a(false);
    public int bs; // CanaryMod: private -> public
    private int bt;
    private Entity bu;

    public EntityPigZombie(World world) {
        super(world);
        this.ag = true;
        this.entity = new CanaryPigZombie(this); // CanaryMod: Wrap Entity
    }

    protected void ax() {
        super.ax();
        this.a(bp).a(0.0D);
        this.a(SharedMonsterAttributes.d).a(0.5D);
        this.a(SharedMonsterAttributes.e).a(5.0D);
    }

    protected boolean bb() {
        return false;
    }

    public void l_() {
        if (this.bu != this.j && !this.q.I) {
            AttributeInstance attributeinstance = this.a(SharedMonsterAttributes.d);

            attributeinstance.b(br);
            if (this.j != null) {
                attributeinstance.a(br);
            }
        }

        this.bu = this.j;
        if (this.bt > 0 && --this.bt == 0) {
            this.a("mob.zombiepig.zpigangry", this.aW() * 2.0F, ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.l_();
    }

    public boolean bo() {
        return this.q.r > 0 && this.q.b(this.E) && this.q.a((Entity) this, this.E).isEmpty() && !this.q.d(this.E);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Anger", (short) this.bs);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.bs = nbttagcompound.d("Anger");
    }

    protected Entity bH() {
        return this.bs == 0 ? null : super.bH();
    }

    public boolean a(DamageSource damagesource, float f0) {
        if (this.ap()) {
            return false;
        } else {
            Entity entity = damagesource.i();

            if (entity instanceof EntityPlayer) {
                // CanaryMod: MobTarget
                MobTargetHook hook = new MobTargetHook((net.canarymod.api.entity.living.EntityLiving) this.getCanaryEntity(), (net.canarymod.api.entity.living.EntityLiving) entity.getCanaryEntity());
                Canary.hooks().callHook(hook);
                if (!hook.isCanceled()) {

                    List list = this.q.b((Entity) this, this.E.b(32.0D, 32.0D, 32.0D));

                    for (int i1 = 0; i1 < list.size(); ++i1) {
                        Entity entity1 = (Entity) list.get(i1);

                        if (entity1 instanceof EntityPigZombie) {
                            EntityPigZombie entitypigzombie = (EntityPigZombie) entity1;
                            entitypigzombie.c(entity);
                        }
                    }
                    this.c(entity);
                }
                //
            }

            return super.a(damagesource, f0);
        }
    }

    private void c(Entity entity) {
        this.j = entity;
        this.bs = 400 + this.ab.nextInt(400);
        this.bt = this.ab.nextInt(40);
    }

    protected String r() {
        return "mob.zombiepig.zpig";
    }

    protected String aK() {
        return "mob.zombiepig.zpighurt";
    }

    protected String aL() {
        return "mob.zombiepig.zpigdeath";
    }

    protected void b(boolean flag0, int i0) {
        int i1 = this.ab.nextInt(2 + i0);

        int i2;

        for (i2 = 0; i2 < i1; ++i2) {
            this.b(Item.bo.cv, 1);
        }

        i1 = this.ab.nextInt(2 + i0);

        for (i2 = 0; i2 < i1; ++i2) {
            this.b(Item.bs.cv, 1);
        }
    }

    public boolean a(EntityPlayer entityplayer) {
        return false;
    }

    protected void l(int i0) {
        this.b(Item.r.cv, 1);
    }

    protected int s() {
        return Item.bo.cv;
    }

    protected void bs() {
        this.c(0, new ItemStack(Item.I));
    }

    public EntityLivingData a(EntityLivingData entitylivingdata) {
        super.a(entitylivingdata);
        this.j(false);
        return entitylivingdata;
    }
}
