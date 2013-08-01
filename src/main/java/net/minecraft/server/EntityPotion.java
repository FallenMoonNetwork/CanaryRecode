package net.minecraft.server;

import java.util.Iterator;
import java.util.List;
import net.canarymod.api.entity.throwable.CanaryEntityPotion;

public class EntityPotion extends EntityThrowable {

    private ItemStack c;

    public EntityPotion(World world) {
        super(world);
        // CanaryMod
        this.gravity = 0.05F;
        this.entity = new CanaryEntityPotion(this); // Wrap Entity
        //
    }

    public EntityPotion(World world, EntityLivingBase entitylivingbase, int i0) {
        this(world, entitylivingbase, new ItemStack(Item.bu, 1, i0));
    }

    public EntityPotion(World world, EntityLivingBase entitylivingbase, ItemStack itemstack) {
        super(world, entitylivingbase);
        this.c = itemstack;
        // CanaryMod
        this.gravity = 0.05F;
        this.entity = new CanaryEntityPotion(this); // Wrap Entity
        //
    }

    public EntityPotion(World world, double d0, double d1, double d2, ItemStack itemstack) {
        super(world, d0, d1, d2);
        this.c = itemstack;
        // CanaryMod
        this.entity = new CanaryEntityPotion(this); // Wrap Entity
        this.gravity = 0.05F;
        //
    }

    // CanaryMod: remove unneeded method override
    // protected float e() {
    // return 0.05F;
    // }
    //

    protected float c() {
        return 0.5F;
    }

    protected float d() {
        return -20.0F;
    }

    public void a(int i0) {
        if (this.c == null) {
            this.c = new ItemStack(Item.bu, 1, 0);
        }

        this.c.b(i0);
    }

    public int i() {
        if (this.c == null) {
            this.c = new ItemStack(Item.bu, 1, 0);
        }

        return this.c.k();
    }

    protected void a(MovingObjectPosition movingobjectposition) {
        if (!this.q.I) {
            List list = Item.bu.g(this.c);

            if (list != null && !list.isEmpty()) {
                AxisAlignedBB axisalignedbb = this.E.b(4.0D, 2.0D, 4.0D);
                List list1 = this.q.a(EntityLivingBase.class, axisalignedbb);

                if (list1 != null && !list1.isEmpty()) {
                    Iterator iterator = list1.iterator();

                    while (iterator.hasNext()) {
                        EntityLivingBase entitylivingbase = (EntityLivingBase) iterator.next();
                        double d0 = this.e(entitylivingbase);

                        if (d0 < 16.0D) {
                            double d1 = 1.0D - Math.sqrt(d0) / 4.0D;

                            if (entitylivingbase == movingobjectposition.g) {
                                d1 = 1.0D;
                            }

                            Iterator iterator1 = list.iterator();

                            while (iterator1.hasNext()) {
                                PotionEffect potioneffect = (PotionEffect) iterator1.next();
                                int i0 = potioneffect.a();

                                if (Potion.a[i0].b()) {
                                    Potion.a[i0].a(this.h(), entitylivingbase, potioneffect.c(), d1);
                                } else {
                                    int i1 = (int) (d1 * (double) potioneffect.b() + 0.5D);

                                    if (i1 > 20) {
                                        entitylivingbase.c(new PotionEffect(i0, i1, potioneffect.c()));
                                    }
                                }
                            }
                        }
                    }
                }
            }

            this.q.e(2002, (int) Math.round(this.u), (int) Math.round(this.v), (int) Math.round(this.w), this.i());
            this.w();
        }
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        if (nbttagcompound.b("Potion")) {
            this.c = ItemStack.a(nbttagcompound.l("Potion"));
        } else {
            this.a(nbttagcompound.e("potionValue"));
        }

        if (this.c == null) {
            this.w();
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        if (this.c != null) {
            nbttagcompound.a("Potion", this.c.b(new NBTTagCompound()));
        }
    }
}
