package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import net.canarymod.api.entity.hanging.CanaryLeashKnot;

public class EntityLeashKnot extends EntityHanging {

    public EntityLeashKnot(World world) {
        super(world);
        this.entity = new CanaryLeashKnot(this); // CanaryMod: wrap entity
    }

    public EntityLeashKnot(World world, int i0, int i1, int i2) {
        super(world, i0, i1, i2, 0);
        this.b((double) i0 + 0.5D, (double) i1 + 0.5D, (double) i2 + 0.5D);
        this.entity = new CanaryLeashKnot(this); // CanaryMod: wrap entity
    }

    protected void a() {
        super.a();
    }

    public void a(int i0) {}

    public int d() {
        return 9;
    }

    public int e() {
        return 9;
    }

    public void b(Entity entity) {}

    public boolean d(NBTTagCompound nbttagcompound) {
        return false;
    }

    public void b(NBTTagCompound nbttagcompound) {}

    public void a(NBTTagCompound nbttagcompound) {}

    public boolean c(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.aV();
        boolean flag0 = false;

        if (itemstack != null && itemstack.d == Item.ch.cv && !this.q.I) {
            double d0 = 7.0D;
            List list = this.q.a(EntityLiving.class, AxisAlignedBB.a().a(this.u - d0, this.v - d0, this.w - d0, this.u + d0, this.v + d0, this.w + d0));

            if (list != null) {
                Iterator iterator = list.iterator();

                while (iterator.hasNext()) {
                    EntityLiving entityliving = (EntityLiving) iterator.next();

                    if (entityliving.bD() && entityliving.bE() == entityplayer) {
                        entityliving.b(this, true);
                        flag0 = true;
                    }
                }
            }
        }

        if (!this.q.I && !flag0) {
            this.w();
        }

        return true;
    }

    public boolean c() {
        int i0 = this.q.a(this.b, this.c, this.d);

        return Block.s[i0] != null && Block.s[i0].d() == 11;
    }

    public static EntityLeashKnot a(World world, int i0, int i1, int i2) {
        EntityLeashKnot entityleashknot = new EntityLeashKnot(world, i0, i1, i2);

        entityleashknot.p = true;
        world.d((Entity) entityleashknot);
        return entityleashknot;
    }

    public static EntityLeashKnot b(World world, int i0, int i1, int i2) {
        List list = world.a(EntityLeashKnot.class, AxisAlignedBB.a().a((double) i0 - 1.0D, (double) i1 - 1.0D, (double) i2 - 1.0D, (double) i0 + 1.0D, (double) i1 + 1.0D, (double) i2 + 1.0D));
        Object object = null;

        if (list != null) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                EntityLeashKnot entityleashknot = (EntityLeashKnot) iterator.next();

                if (entityleashknot.b == i0 && entityleashknot.c == i1 && entityleashknot.d == i2) {
                    return entityleashknot;
                }
            }
        }

        return null;
    }
}
