package net.minecraft.server;

import net.canarymod.api.entity.CanaryFireworkRocket;
import net.canarymod.api.entity.FireworkRocket;
import net.canarymod.hook.world.FireworkExplodeHook;

public class EntityFireworkRocket extends Entity {

    public int a; // CanaryMod: private => public; life
    public int b; // CanaryMod: private => public; life max

    public EntityFireworkRocket(World world) {
        super(world);
        this.a(0.25F, 0.25F);
        this.entity = new CanaryFireworkRocket(this); // CanaryMod: Wrap Entity
    }

    protected void a() {
        this.ah.a(8, 5);
    }

    public EntityFireworkRocket(World world, double d0, double d1, double d2, ItemStack itemstack) {
        super(world);
        this.a = 0;
        this.a(0.25F, 0.25F);
        this.b(d0, d1, d2);
        this.N = 0.0F;
        int i0 = 1;

        if (itemstack != null && itemstack.p()) {
            this.ah.b(8, itemstack);
            NBTTagCompound nbttagcompound = itemstack.q();
            NBTTagCompound nbttagcompound1 = nbttagcompound.l("Fireworks");

            if (nbttagcompound1 != null) {
                i0 += nbttagcompound1.c("Flight");
            }
        }

        this.x = this.ab.nextGaussian() * 0.001D;
        this.z = this.ab.nextGaussian() * 0.001D;
        this.y = 0.05D;
        this.b = 10 * i0 + this.ab.nextInt(6) + this.ab.nextInt(7);
        this.entity = new CanaryFireworkRocket(this); // CanaryMod: Wrap Entity
    }

    public void l_() {
        this.U = this.u;
        this.V = this.v;
        this.W = this.w;
        super.l_();
        this.x *= 1.15D;
        this.z *= 1.15D;
        this.y += 0.04D;
        this.d(this.x, this.y, this.z);
        float f0 = MathHelper.a(this.x * this.x + this.z * this.z);

        this.A = (float)(Math.atan2(this.x, this.z) * 180.0D / 3.1415927410125732D);

        for (this.B = (float)(Math.atan2(this.y, (double)f0) * 180.0D / 3.1415927410125732D); this.B - this.D < -180.0F; this.D -= 360.0F) {
            ;
        }

        while (this.B - this.D >= 180.0F) {
            this.D += 360.0F;
        }

        while (this.A - this.C < -180.0F) {
            this.C -= 360.0F;
        }

        while (this.A - this.C >= 180.0F) {
            this.C += 360.0F;
        }

        this.B = this.D + (this.B - this.D) * 0.2F;
        this.A = this.C + (this.A - this.C) * 0.2F;
        if (this.a == 0) {
            this.q.a((Entity)this, "fireworks.launch", 3.0F, 1.0F);
        }

        ++this.a;
        if (this.q.I && this.a % 2 < 2) {
            this.q.a("fireworksSpark", this.u, this.v - 0.3D, this.w, this.ab.nextGaussian() * 0.05D, -this.y * 0.5D, this.ab.nextGaussian() * 0.05D);
        }

        if (!this.q.I && this.a > this.b) {
            // CanaryMod: FireworkExplode
            FireworkExplodeHook hook = (FireworkExplodeHook)new FireworkExplodeHook((FireworkRocket)this.getCanaryEntity()).call();
            if (!hook.isCanceled()) {
                this.q.a((Entity)this, (byte)17);
                this.x();
            }
            //
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        nbttagcompound.a("Life", this.a);
        nbttagcompound.a("LifeTime", this.b);
        ItemStack itemstack = this.ah.f(8);

        if (itemstack != null) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();

            itemstack.b(nbttagcompound1);
            nbttagcompound.a("FireworksItem", nbttagcompound1);
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.a = nbttagcompound.e("Life");
        this.b = nbttagcompound.e("LifeTime");
        NBTTagCompound nbttagcompound1 = nbttagcompound.l("FireworksItem");

        if (nbttagcompound1 != null) {
            ItemStack itemstack = ItemStack.a(nbttagcompound1);

            if (itemstack != null) {
                this.ah.b(8, itemstack);
            }
        }
    }

    public float d(float f0) {
        return super.d(f0);
    }

    public boolean aq() {
        return false;
    }

    public ItemStack getItemStack() {
        return this.ah.f(8);
    }

    public void setItemStack(ItemStack stack) {
        this.ah.b(8, stack);
        // Update Flight information
        NBTTagCompound nbttagcompound = stack.q();
        NBTTagCompound nbttagcompound1 = nbttagcompound.l("Fireworks");
        if (nbttagcompound1 != null) {
            this.b = 10 * nbttagcompound1.c("Flight") + this.ab.nextInt(6) + this.ab.nextInt(7);
        }
    }
}
