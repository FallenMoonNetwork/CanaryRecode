package net.minecraft.server;


import java.util.Iterator;
import net.canarymod.Canary;
import net.canarymod.api.entity.CanaryEntityItem;
import net.canarymod.hook.entity.EntityDespawnHook;
import net.canarymod.hook.entity.ItemTouchGroundHook;


public class EntityItem extends Entity {

    public int a;
    public int b;
    private int d;
    public float c;

    public EntityItem(World world, double d0, double d1, double d2) {
        super(world);
        this.a = 0;
        this.d = 5;
        this.c = (float) (Math.random() * 3.141592653589793D * 2.0D);
        this.a(0.25F, 0.25F);
        this.N = this.P / 2.0F;
        this.b(d0, d1, d2);
        this.A = (float) (Math.random() * 360.0D);
        this.x = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
        this.y = 0.20000000298023224D;
        this.z = (double) ((float) (Math.random() * 0.20000000298023224D - 0.10000000149011612D));
        this.entity = new CanaryEntityItem(this); // CanaryMod: Wrap Entity
    }

    public EntityItem(World world, double d0, double d1, double d2, ItemStack itemstack) {
        this(world, d0, d1, d2);
        this.a(itemstack);
    }

    @Override
    protected boolean f_() {
        return false;
    }

    public EntityItem(World world) {
        super(world);
        this.a = 0;
        this.d = 5;
        this.c = (float) (Math.random() * 3.141592653589793D * 2.0D);
        this.a(0.25F, 0.25F);
        this.N = this.P / 2.0F;
        this.entity = new CanaryEntityItem(this); // CanaryMod: Wrap Entity
    }

    @Override
    protected void a() {
        this.u().a(10, 5);
    }

    @Override
    public void l_() {
        super.l_();
        if (this.b > 0) {
            --this.b;
        }

        boolean tmpTouch = this.I; // CanaryMod

        this.r = this.u;
        this.s = this.v;
        this.t = this.w;
        this.y -= 0.03999999910593033D;
        this.Z = this.i(this.u, (this.E.b + this.E.e) / 2.0D, this.w);
        this.d(this.x, this.y, this.z);
        boolean flag0 = (int) this.r != (int) this.u || (int) this.s != (int) this.v || (int) this.t != (int) this.w;

        if (flag0 || this.ac % 25 == 0) {
            if (this.q.g(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w)) == Material.i) {
                this.y = 0.20000000298023224D;
                this.x = (double) ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F);
                this.z = (double) ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F);
                this.a("random.fizz", 0.4F, 2.0F + this.ab.nextFloat() * 0.4F);
            }

            if (!this.q.I) {
                this.g();
            }
        }

        float f0 = 0.98F;

        if (this.F) {
            f0 = 0.58800006F;
            int i0 = this.q.a(MathHelper.c(this.u), MathHelper.c(this.E.b) - 1, MathHelper.c(this.w));

            if (i0 > 0) {
                f0 = Block.r[i0].cP * 0.98F;
            }

            // CanaryMod: ItemTouchGround
            // It does touch the ground now, but didn't in last tick
            if (!tmpTouch) {
                ItemTouchGroundHook hook = new ItemTouchGroundHook((net.canarymod.api.entity.EntityItem) getCanaryEntity());

                Canary.hooks().callHook(hook);
                if (hook.isCanceled()) {
                    this.w(); // kill the item
                }
            }
            //
        }

        this.x *= (double) f0;
        this.y *= 0.9800000190734863D;
        this.z *= (double) f0;
        if (this.F) {
            this.y *= -0.5D;
        }

        ++this.a;
        if (!this.q.I && this.a >= 6000) {
            // CanaryMod: EntityDespawn
            EntityDespawnHook hook = new EntityDespawnHook(getCanaryEntity());

            Canary.hooks().callHook(hook);
            if (!hook.isCanceled()) {
                this.w();
            } else {
                this.b = 0;
            }
            //
        }
    }

    private void g() {
        Iterator iterator = this.q.a(EntityItem.class, this.E.b(0.5D, 0.0D, 0.5D)).iterator();

        while (iterator.hasNext()) {
            EntityItem entityitem = (EntityItem) iterator.next();

            this.a(entityitem);
        }
    }

    public boolean a(EntityItem entityitem) {
        if (entityitem == this) {
            return false;
        } else if (entityitem.R() && this.R()) {
            ItemStack itemstack = this.d();
            ItemStack itemstack1 = entityitem.d();

            if (itemstack1.b() != itemstack.b()) {
                return false;
            } else if (itemstack1.p() ^ itemstack.p()) {
                return false;
            } else if (itemstack1.p() && !itemstack1.q().equals(itemstack.q())) {
                return false;
            } else if (itemstack1.b().m() && itemstack1.k() != itemstack.k()) {
                return false;
            } else if (itemstack1.a < itemstack.a) {
                return entityitem.a(this);
            } else if (itemstack1.a + itemstack.a > itemstack1.e()) {
                return false;
            } else {
                itemstack1.a += itemstack.a;
                entityitem.b = Math.max(entityitem.b, this.b);
                entityitem.a = Math.min(entityitem.a, this.a);
                entityitem.a(itemstack1);
                this.w();
                return true;
            }
        } else {
            return false;
        }
    }

    public void c() {
        this.a = 4800;
    }

    @Override
    public boolean H() {
        return this.q.a(this.E, Material.h, (Entity) this);
    }

    @Override
    protected void e(int i0) {
        this.a(DamageSource.a, i0);
    }

    @Override
    public boolean a(DamageSource damagesource, int i0) {
        if (this.aq()) {
            return false;
        } else if (this.d() != null && this.d().c == Item.bT.cp && damagesource.c()) {
            return false;
        } else {
            this.J();
            this.d -= i0;
            if (this.d <= 0) {
                this.w();
            }

            return false;
        }
    }

    @Override
    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Health", (short) ((byte) this.d));
        nbttagcompound.a("Age", (short) this.a);
        if (this.d() != null) {
            nbttagcompound.a("Item", this.d().b(new NBTTagCompound()));
        }
    }

    @Override
    public void a(NBTTagCompound nbttagcompound) {
        this.d = nbttagcompound.d("Health") & 255;
        this.a = nbttagcompound.d("Age");
        NBTTagCompound nbttagcompound1 = nbttagcompound.l("Item");

        this.a(ItemStack.a(nbttagcompound1));
        if (this.d() == null) {
            this.w();
        }
    }

    @Override
    public void b_(EntityPlayer entityplayer) {
        if (!this.q.I) {
            ItemStack itemstack = this.d();
            int i0 = itemstack.a;

            if (this.b == 0 && entityplayer.bK.canPickup(this)) { // CanaryMod: simulate pickup first
                if (entityplayer.bK.a(itemstack)) {
                    if (itemstack.c == Block.N.cz) {
                        entityplayer.a((StatBase) AchievementList.g);
                    }

                    if (itemstack.c == Item.aG.cp) {
                        entityplayer.a((StatBase) AchievementList.t);
                    }

                    if (itemstack.c == Item.o.cp) {
                        entityplayer.a((StatBase) AchievementList.w);
                    }

                    if (itemstack.c == Item.bp.cp) {
                        entityplayer.a((StatBase) AchievementList.z);
                    }

                    this.a("random.pop", 0.2F, ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    entityplayer.a((Entity) this, i0);
                    if (itemstack.a <= 0) {
                        this.w();
                    }
                }
            }
        }
    }

    @Override
    public String am() {
        return StatCollector.a("item." + this.d().a());
    }

    @Override
    public boolean ap() {
        return false;
    }

    @Override
    public void c(int i0) {
        super.c(i0);
        if (!this.q.I) {
            this.g();
        }
    }

    public ItemStack d() {
        ItemStack itemstack = this.u().f(10);

        if (itemstack == null) {
            if (this.q != null) {
                this.q.W().c("Item entity " + this.k + " has no item?!");
            }

            return new ItemStack(Block.x);
        } else {
            return itemstack;
        }
    }

    public void a(ItemStack itemstack) {
        this.u().b(10, itemstack);
        this.u().h(10);
    }

    public CanaryEntityItem getEntityItem() {
        return (CanaryEntityItem) entity;
    }
}
