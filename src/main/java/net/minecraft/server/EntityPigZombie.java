package net.minecraft.server;


import java.util.List;
import net.canarymod.Canary;
import net.canarymod.api.entity.living.monster.CanaryPigZombie;
import net.canarymod.hook.entity.MobTargetHook;


public class EntityPigZombie extends EntityZombie {

    public int d = 0; // CanaryMod: private -> public
    private int e = 0;

    public EntityPigZombie(World world) {
        super(world);
        this.aH = "/mob/pigzombie.png";
        this.bI = 0.5F;
        this.ag = true;
        this.entity = new CanaryPigZombie(this); // CanaryMod: Wrap Entity
    }

    protected boolean bh() {
        return false;
    }

    public void l_() {
        this.bI = this.a_ != null ? 0.95F : 0.5F;
        if (this.e > 0 && --this.e == 0) {
            this.a("mob.zombiepig.zpigangry", this.ba() * 2.0F, ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F + 1.0F) * 1.8F);
        }

        super.l_();
    }

    public boolean bv() {
        return this.q.r > 0 && this.q.b(this.E) && this.q.a((Entity) this, this.E).isEmpty() && !this.q.d(this.E);
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Anger", (short) this.d);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.d = nbttagcompound.d("Anger");
    }

    protected Entity j() {
        return this.d == 0 ? null : super.j();
    }

    public boolean a(DamageSource damagesource, int i0) {
        if (this.aq()) {
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

                            entitypigzombie.p(entity);
                        }
                    }
                    this.p(entity);
                }
                //
            }

            return super.a(damagesource, i0);
        }
    }

    private void p(Entity entity) {
        this.a_ = entity;
        this.d = 400 + this.ab.nextInt(400);
        this.e = this.ab.nextInt(40);
    }

    protected String bb() {
        return "mob.zombiepig.zpig";
    }

    protected String bc() {
        return "mob.zombiepig.zpighurt";
    }

    protected String bd() {
        return "mob.zombiepig.zpigdeath";
    }

    protected void a(boolean flag0, int i0) {
        int i1 = this.ab.nextInt(2 + i0);

        int i2;

        for (i2 = 0; i2 < i1; ++i2) {
            this.b(Item.bn.cp, 1);
        }

        i1 = this.ab.nextInt(2 + i0);

        for (i2 = 0; i2 < i1; ++i2) {
            this.b(Item.br.cp, 1);
        }
    }

    public boolean a_(EntityPlayer entityplayer) {
        return false;
    }

    protected void l(int i0) {
        this.b(Item.q.cp, 1);
    }

    protected int be() {
        return Item.bn.cp;
    }

    protected void bH() {
        this.c(0, new ItemStack(Item.H));
    }

    public void bJ() {
        super.bJ();
        this.i(false);
    }

    public int c(Entity entity) {
        ItemStack itemstack = this.bG();
        int i0 = 5;

        if (itemstack != null) {
            i0 += itemstack.a((Entity) this);
        }

        return i0;
    }
}
