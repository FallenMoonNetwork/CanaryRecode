package net.minecraft.server;

import net.canarymod.api.entity.CanaryEntityItem;
import net.canarymod.hook.entity.EntityDespawnHook;
import net.canarymod.hook.entity.ItemTouchGroundHook;

import java.util.Iterator;

public class EntityItem extends Entity {

    public int a;
    public int b;
    public int d; // CanaryMod: private => public; health
    public float c;

    public EntityItem(World world, double d0, double d1, double d2) {
        super(world);
        this.d = 5;
        this.c = (float)(Math.random() * 3.141592653589793D * 2.0D);
        this.a(0.25F, 0.25F);
        this.N = this.P / 2.0F;
        this.b(d0, d1, d2);
        this.A = (float)(Math.random() * 360.0D);
        this.x = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
        this.y = 0.20000000298023224D;
        this.z = (double)((float)(Math.random() * 0.20000000298023224D - 0.10000000149011612D));
        this.entity = new CanaryEntityItem(this); // CanaryMod: Wrap Entity
    }

    public EntityItem(World world, double d0, double d1, double d2, ItemStack itemstack) {
        this(world, d0, d1, d2);
        this.a(itemstack);
    }

    protected boolean e_() {
        return false;
    }

    public EntityItem(World world) {
        super(world);
        this.d = 5;
        this.c = (float)(Math.random() * 3.141592653589793D * 2.0D);
        this.a(0.25F, 0.25F);
        this.N = this.P / 2.0F;
        this.entity = new CanaryEntityItem(this); // CanaryMod: Wrap Entity
    }

    protected void a() {
        this.v().a(10, 5);
    }

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
        boolean flag0 = (int)this.r != (int)this.u || (int)this.s != (int)this.v || (int)this.t != (int)this.w;

        if (flag0 || this.ac % 25 == 0) {
            if (this.q.g(MathHelper.c(this.u), MathHelper.c(this.v), MathHelper.c(this.w)) == Material.i) {
                this.y = 0.20000000298023224D;
                this.x = (double)((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F);
                this.z = (double)((this.ab.nextFloat() - this.ab.nextFloat()) * 0.2F);
                this.a("random.fizz", 0.4F, 2.0F + this.ab.nextFloat() * 0.4F);
            }

            if (!this.q.I) {
                this.e();
            }
        }

        float f0 = 0.98F;

        if (this.F) {
            f0 = 0.58800006F;
            int i0 = this.q.a(MathHelper.c(this.u), MathHelper.c(this.E.b) - 1, MathHelper.c(this.w));

            if (i0 > 0) {
                f0 = Block.s[i0].cV * 0.98F;
            }

            // CanaryMod: ItemTouchGround
            // It does touch the ground now, but didn't in last tick
            if (!tmpTouch) {
                ItemTouchGroundHook hook = (ItemTouchGroundHook)new ItemTouchGroundHook((net.canarymod.api.entity.EntityItem)getCanaryEntity()).call();
                if (hook.isCanceled()) {
                    this.x(); // kill the item
                }
            }
            //
        }

        this.x *= (double)f0;
        this.y *= 0.9800000190734863D;
        this.z *= (double)f0;
        if (this.F) {
            this.y *= -0.5D;
        }

        ++this.a;
        if (!this.q.I && this.a >= 6000) {
            // CanaryMod: EntityDespawn
            EntityDespawnHook hook = (EntityDespawnHook)new EntityDespawnHook(getCanaryEntity()).call();
            if (!hook.isCanceled()) {
                this.x();
            }
            else {
                this.a = 0; // Reset Age
            }
            //
        }
    }

    private void e() {
        Iterator iterator = this.q.a(EntityItem.class, this.E.b(0.5D, 0.0D, 0.5D)).iterator();

        while (iterator.hasNext()) {
            EntityItem entityitem = (EntityItem)iterator.next();

            this.a(entityitem);
        }
    }

    public boolean a(EntityItem entityitem) {
        if (entityitem == this) {
            return false;
        }
        else if (entityitem.T() && this.T()) {
            ItemStack itemstack = this.d();
            ItemStack itemstack1 = entityitem.d();

            if (itemstack1.b() != itemstack.b()) {
                return false;
            }
            else if (itemstack1.p() ^ itemstack.p()) {
                return false;
            }
            else if (itemstack1.p() && !itemstack1.q().equals(itemstack.q())) {
                return false;
            }
            else if (itemstack1.b().n() && itemstack1.k() != itemstack.k()) {
                return false;
            }
            else if (itemstack1.b < itemstack.b) {
                return entityitem.a(this);
            }
            else if (itemstack1.b + itemstack.b > itemstack1.e()) {
                return false;
            }
            else {
                itemstack1.b += itemstack.b;
                entityitem.b = Math.max(entityitem.b, this.b);
                entityitem.a = Math.min(entityitem.a, this.a);
                entityitem.a(itemstack1);
                this.x();
                return true;
            }
        }
        else {
            return false;
        }
    }

    public void c() {
        this.a = 4800;
    }

    public boolean I() {
        return this.q.a(this.E, Material.h, (Entity)this);
    }

    protected void e(int i0) {
        this.a(DamageSource.a, (float)i0);
    }

    public boolean a(DamageSource damagesource, float f0) {
        if (this.ar()) {
            return false;
        }
        else if (this.d() != null && this.d().d == Item.bU.cv && damagesource.c()) {
            return false;
        }
        else {
            this.K();
            this.d = (int)((float)this.d - f0);
            if (this.d <= 0) {
                this.x();
            }

            return false;
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Health", (short)((byte)this.d));
        nbttagcompound.a("Age", (short)this.a);
        if (this.d() != null) {
            nbttagcompound.a("Item", this.d().b(new NBTTagCompound()));
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.d = nbttagcompound.d("Health") & 255;
        this.a = nbttagcompound.d("Age");
        NBTTagCompound nbttagcompound1 = nbttagcompound.l("Item");

        this.a(ItemStack.a(nbttagcompound1));
        if (this.d() == null) {
            this.x();
        }
    }

    public void b_(EntityPlayer entityplayer) {
        if (!this.q.I) {
            ItemStack itemstack = this.d();
            int i0 = itemstack.b;

            if (this.b == 0 && entityplayer.bn.canPickup(this)) { // CanaryMod: simulate pickup first
                if (entityplayer.bn.a(itemstack)) {
                    if (itemstack.d == Block.O.cF) {
                        entityplayer.a((StatBase)AchievementList.g);
                    }

                    if (itemstack.d == Item.aH.cv) {
                        entityplayer.a((StatBase)AchievementList.t);
                    }

                    if (itemstack.d == Item.p.cv) {
                        entityplayer.a((StatBase)AchievementList.w);
                    }

                    if (itemstack.d == Item.bq.cv) {
                        entityplayer.a((StatBase)AchievementList.z);
                    }

                    this.a("random.pop", 0.2F, ((this.ab.nextFloat() - this.ab.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                    entityplayer.a((Entity)this, i0);
                    if (itemstack.b <= 0) {
                        this.x();
                    }
                }
            }
        }
    }

    public String an() {
        return StatCollector.a("item." + this.d().a());
    }

    public boolean aq() {
        return false;
    }

    public void b(int i0) {
        super.b(i0);
        if (!this.q.I) {
            this.e();
        }
    }

    public ItemStack d() {
        ItemStack itemstack = this.v().f(10);

        if (itemstack == null) {
            if (this.q != null) {
                this.q.Y().c("Item entity " + this.k + " has no item?!");
            }

            return new ItemStack(Block.y);
        }
        else {
            return itemstack;
        }
    }

    public void a(ItemStack itemstack) {
        this.v().b(10, itemstack);
        this.v().h(10);
    }

    public CanaryEntityItem getEntityItem() {
        return (CanaryEntityItem)entity;
    }
}
