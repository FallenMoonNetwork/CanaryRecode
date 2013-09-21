package net.minecraft.server;

import net.canarymod.api.CanaryDamageSource;
import net.canarymod.api.DamageType;
import net.canarymod.api.entity.hanging.CanaryLeashKnot;
import net.canarymod.api.entity.hanging.HangingEntity;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.hook.entity.HangingEntityDestroyHook;

import java.util.Iterator;
import java.util.List;

public class EntityLeashKnot extends EntityHanging {

    public EntityLeashKnot(World world) {
        super(world);
        this.entity = new CanaryLeashKnot(this); // CanaryMod: wrap entity
    }

    public EntityLeashKnot(World world, int i0, int i1, int i2) {
        super(world, i0, i1, i2, 0);
        this.b((double)i0 + 0.5D, (double)i1 + 0.5D, (double)i2 + 0.5D);
        this.entity = new CanaryLeashKnot(this); // CanaryMod: wrap entity
    }

    protected void a() {
        super.a();
    }

    public void a(int i0) {
    }

    public int d() {
        return 9;
    }

    public int e() {
        return 9;
    }

    //CanaryMod added logic to notify plugins of leash knots being destroyed
    public void b(Entity entity) {
        //CanaryMod start
        HangingEntityDestroyHook hook = null;
        if (entity instanceof EntityPlayer) {
            hook = (HangingEntityDestroyHook)new HangingEntityDestroyHook((HangingEntity)this.getCanaryEntity(), (Player)entity.getCanaryEntity(), CanaryDamageSource.getDamageSourceFromType(DamageType.GENERIC)).call();
        }
        else {
            hook = (HangingEntityDestroyHook)new HangingEntityDestroyHook((HangingEntity)this.getCanaryEntity(), null, CanaryDamageSource.getDamageSourceFromType(DamageType.GENERIC)).call();
        }
        //CanaryMod end
    }

    public boolean d(NBTTagCompound nbttagcompound) {
        return false;
    }

    public void b(NBTTagCompound nbttagcompound) {
    }

    public void a(NBTTagCompound nbttagcompound) {
    }

    public boolean c(EntityPlayer entityplayer) {
        ItemStack itemstack = entityplayer.aZ();
        boolean flag0 = false;
        double d0;
        List list;
        Iterator iterator;
        EntityLiving entityliving;

        if (itemstack != null && itemstack.d == Item.ch.cv && !this.q.I) {
            d0 = 7.0D;
            list = this.q.a(EntityLiving.class, AxisAlignedBB.a().a(this.u - d0, this.v - d0, this.w - d0, this.u + d0, this.v + d0, this.w + d0));
            if (list != null) {
                iterator = list.iterator();

                while (iterator.hasNext()) {
                    entityliving = (EntityLiving)iterator.next();
                    if (entityliving.bH() && entityliving.bI() == entityplayer) {
                        entityliving.b(this, true);
                        flag0 = true;
                    }
                }
            }
        }

        if (!this.q.I && !flag0) {
            this.x();
            if (entityplayer.bG.d) {
                d0 = 7.0D;
                list = this.q.a(EntityLiving.class, AxisAlignedBB.a().a(this.u - d0, this.v - d0, this.w - d0, this.u + d0, this.v + d0, this.w + d0));
                if (list != null) {
                    iterator = list.iterator();

                    while (iterator.hasNext()) {
                        entityliving = (EntityLiving)iterator.next();
                        if (entityliving.bH() && entityliving.bI() == this) {
                            entityliving.a(true, false);
                        }
                    }
                }
            }
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
        world.d((Entity)entityleashknot);
        return entityleashknot;
    }

    public static EntityLeashKnot b(World world, int i0, int i1, int i2) {
        List list = world.a(EntityLeashKnot.class, AxisAlignedBB.a().a((double)i0 - 1.0D, (double)i1 - 1.0D, (double)i2 - 1.0D, (double)i0 + 1.0D, (double)i1 + 1.0D, (double)i2 + 1.0D));
        Object object = null;

        if (list != null) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                EntityLeashKnot entityleashknot = (EntityLeashKnot)iterator.next();

                if (entityleashknot.b == i0 && entityleashknot.c == i1 && entityleashknot.d == i2) {
                    return entityleashknot;
                }
            }
        }

        return null;
    }
}
