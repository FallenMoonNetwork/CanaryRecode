package net.minecraft.server;

import net.canarymod.api.entity.CanaryItemFrame;

public class EntityItemFrame extends EntityHanging {

    public float e = 1.0F; // CanaryMod: private => public

    public EntityItemFrame(World world) {
        super(world);
        this.entity = new CanaryItemFrame(this); // CanaryMod: Wrap Entity
    }

    public EntityItemFrame(World world, int i0, int i1, int i2, int i3) {
        super(world, i0, i1, i2, i3);
        this.a(i3);
        this.entity = new CanaryItemFrame(this); // CanaryMod: Wrap Entity
    }

    protected void a() {
        this.u().a(2, 5);
        this.u().a(3, Byte.valueOf((byte) 0));
    }

    public int d() {
        return 9;
    }

    public int g() {
        return 9;
    }

    public void h() {
        this.a(new ItemStack(Item.bJ), 0.0F);
        ItemStack itemstack = this.i();

        if (itemstack != null && this.ab.nextFloat() < this.e) {
            itemstack = itemstack.m();
            itemstack.a((EntityItemFrame) null);
            this.a(itemstack, 0.0F);
        }
    }

    public ItemStack i() {
        return this.u().f(2);
    }

    public void a(ItemStack itemstack) {
        itemstack = itemstack.m();
        itemstack.a = 1;
        itemstack.a(this);
        this.u().b(2, itemstack);
        this.u().h(2);
    }

    public int j() {
        return this.u().a(3);
    }

    public void b(int i0) {
        this.u().b(3, Byte.valueOf((byte) (i0 % 4)));
    }

    public void b(NBTTagCompound nbttagcompound) {
        if (this.i() != null) {
            nbttagcompound.a("Item", this.i().b(new NBTTagCompound()));
            nbttagcompound.a("ItemRotation", (byte) this.j());
            nbttagcompound.a("ItemDropChance", this.e);
        }

        super.b(nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        NBTTagCompound nbttagcompound1 = nbttagcompound.l("Item");

        if (nbttagcompound1 != null && !nbttagcompound1.d()) {
            this.a(ItemStack.a(nbttagcompound1));
            this.b(nbttagcompound.c("ItemRotation"));
            if (nbttagcompound.b("ItemDropChance")) {
                this.e = nbttagcompound.g("ItemDropChance");
            }
        }

        super.a(nbttagcompound);
    }

    public boolean a_(EntityPlayer entityplayer) {
        if (this.i() == null) {
            ItemStack itemstack = entityplayer.bG();

            if (itemstack != null && !this.q.I) {
                this.a(itemstack);
                if (!entityplayer.ce.d && --itemstack.a <= 0) {
                    entityplayer.bK.a(entityplayer.bK.c, (ItemStack) null);
                }
            }
        } else if (!this.q.I) {
            this.b(this.j() + 1);
        }

        return true;
    }
}
