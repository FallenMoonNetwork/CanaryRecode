package net.minecraft.server;


public abstract class EntityMinecartContainer extends EntityMinecart implements IInventory {

    public ItemStack[] a = new ItemStack[36]; // CanaryMod: private -> public
    private boolean b = true;

    public EntityMinecartContainer(World world) {
        super(world);
    }

    public EntityMinecartContainer(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
    }

    public void a(DamageSource damagesource) {
        super.a(damagesource);

        for (int i0 = 0; i0 < this.j_(); ++i0) {
            ItemStack itemstack = this.a(i0);

            if (itemstack != null) {
                float f0 = this.ab.nextFloat() * 0.8F + 0.1F;
                float f1 = this.ab.nextFloat() * 0.8F + 0.1F;
                float f2 = this.ab.nextFloat() * 0.8F + 0.1F;

                while (itemstack.b > 0) {
                    int i1 = this.ab.nextInt(21) + 10;

                    if (i1 > itemstack.b) {
                        i1 = itemstack.b;
                    }

                    itemstack.b -= i1;
                    EntityItem entityitem = new EntityItem(this.q, this.u + (double) f0, this.v + (double) f1, this.w + (double) f2, new ItemStack(itemstack.d, i1, itemstack.k()));
                    float f3 = 0.05F;

                    entityitem.x = (double) ((float) this.ab.nextGaussian() * f3);
                    entityitem.y = (double) ((float) this.ab.nextGaussian() * f3 + 0.2F);
                    entityitem.z = (double) ((float) this.ab.nextGaussian() * f3);
                    this.q.d((Entity) entityitem);
                }
            }
        }
    }

    public ItemStack a(int i0) {
        return this.a[i0];
    }

    public ItemStack a(int i0, int i1) {
        if (this.a[i0] != null) {
            ItemStack itemstack;

            if (this.a[i0].b <= i1) {
                itemstack = this.a[i0];
                this.a[i0] = null;
                return itemstack;
            } else {
                itemstack = this.a[i0].a(i1);
                if (this.a[i0].b == 0) {
                    this.a[i0] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    public ItemStack a_(int i0) {
        if (this.a[i0] != null) {
            ItemStack itemstack = this.a[i0];

            this.a[i0] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void a(int i0, ItemStack itemstack) {
        this.a[i0] = itemstack;
        if (itemstack != null && itemstack.b > this.d()) {
            itemstack.b = this.d();
        }
    }

    public void e() {}

    public boolean a(EntityPlayer entityplayer) {
        return this.M ? false : entityplayer.e(this) <= 64.0D;
    }

    public void k_() {}

    public void g() {}

    public boolean b(int i0, ItemStack itemstack) {
        return true;
    }

    public String b() {
        return this.c() ? this.t() : "container.minecart";
    }

    public int d() {
        return 64;
    }

    public void b(int i0) {
        this.b = false;
        super.b(i0);
    }

    public void w() {
        if (this.b) {
            for (int i0 = 0; i0 < this.j_(); ++i0) {
                ItemStack itemstack = this.a(i0);

                if (itemstack != null) {
                    float f0 = this.ab.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.ab.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.ab.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.b > 0) {
                        int i1 = this.ab.nextInt(21) + 10;

                        if (i1 > itemstack.b) {
                            i1 = itemstack.b;
                        }

                        itemstack.b -= i1;
                        EntityItem entityitem = new EntityItem(this.q, this.u + (double) f0, this.v + (double) f1, this.w + (double) f2, new ItemStack(itemstack.d, i1, itemstack.k()));

                        if (itemstack.p()) {
                            entityitem.d().d((NBTTagCompound) itemstack.q().b());
                        }

                        float f3 = 0.05F;

                        entityitem.x = (double) ((float) this.ab.nextGaussian() * f3);
                        entityitem.y = (double) ((float) this.ab.nextGaussian() * f3 + 0.2F);
                        entityitem.z = (double) ((float) this.ab.nextGaussian() * f3);
                        this.q.d((Entity) entityitem);
                    }
                }
            }
        }

        super.w();
    }

    protected void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i0 = 0; i0 < this.a.length; ++i0) {
            if (this.a[i0] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                nbttagcompound1.a("Slot", (byte) i0);
                this.a[i0].b(nbttagcompound1);
                nbttaglist.a((NBTBase) nbttagcompound1);
            }
        }

        nbttagcompound.a("Items", (NBTBase) nbttaglist);
    }

    protected void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.m("Items");

        this.a = new ItemStack[this.j_()];

        for (int i0 = 0; i0 < nbttaglist.c(); ++i0) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.b(i0);
            int i1 = nbttagcompound1.c("Slot") & 255;

            if (i1 >= 0 && i1 < this.a.length) {
                this.a[i1] = ItemStack.a(nbttagcompound1);
            }
        }
    }

    public boolean c(EntityPlayer entityplayer) {
        if (!this.q.I) {
            entityplayer.a((IInventory) this);
        }

        return true;
    }

    protected void h() {
        int i0 = 15 - Container.b((IInventory) this);
        float f0 = 0.98F + (float) i0 * 0.001F;

        this.x *= (double) f0;
        this.y *= 0.0D;
        this.z *= (double) f0;
    }
}
