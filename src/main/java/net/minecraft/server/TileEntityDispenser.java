package net.minecraft.server;

import java.util.Random;
import net.canarymod.api.world.blocks.CanaryDispenser;

public class TileEntityDispenser extends TileEntity implements IInventory {

    public ItemStack[] b = new ItemStack[9]; // CanaryMod: private => public
    private Random c = new Random();
    protected String a;

    public TileEntityDispenser() {
        this.complexBlock = new CanaryDispenser(this); // CanaryMod: create once, use forever
    }

    public int j_() {
        return 9;
    }

    public ItemStack a(int i0) {
        return this.b[i0];
    }

    public ItemStack a(int i0, int i1) {
        if (this.b[i0] != null) {
            ItemStack itemstack;

            if (this.b[i0].b <= i1) {
                itemstack = this.b[i0];
                this.b[i0] = null;
                this.e();
                return itemstack;
            } else {
                itemstack = this.b[i0].a(i1);
                if (this.b[i0].b == 0) {
                    this.b[i0] = null;
                }

                this.e();
                return itemstack;
            }
        } else {
            return null;
        }
    }

    public ItemStack a_(int i0) {
        if (this.b[i0] != null) {
            ItemStack itemstack = this.b[i0];

            this.b[i0] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public int j() {
        int i0 = -1;
        int i1 = 1;

        for (int i2 = 0; i2 < this.b.length; ++i2) {
            if (this.b[i2] != null && this.c.nextInt(i1++) == 0) {
                i0 = i2;
            }
        }

        return i0;
    }

    public void a(int i0, ItemStack itemstack) {
        this.b[i0] = itemstack;
        if (itemstack != null && itemstack.b > this.d()) {
            itemstack.b = this.d();
        }

        this.e();
    }

    public int a(ItemStack itemstack) {
        for (int i0 = 0; i0 < this.b.length; ++i0) {
            if (this.b[i0] == null || this.b[i0].d == 0) {
                this.a(i0, itemstack);
                return i0;
            }
        }

        return -1;
    }

    public String b() {
        return this.c() ? this.a : "container.dispenser";
    }

    public void a(String s0) {
        this.a = s0;
    }

    public boolean c() {
        return this.a != null;
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.m("Items");

        this.b = new ItemStack[this.j_()];

        for (int i0 = 0; i0 < nbttaglist.c(); ++i0) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.b(i0);
            int i1 = nbttagcompound1.c("Slot") & 255;

            if (i1 >= 0 && i1 < this.b.length) {
                this.b[i1] = ItemStack.a(nbttagcompound1);
            }
        }

        if (nbttagcompound.b("CustomName")) {
            this.a = nbttagcompound.i("CustomName");
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i0 = 0; i0 < this.b.length; ++i0) {
            if (this.b[i0] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                nbttagcompound1.a("Slot", (byte) i0);
                this.b[i0].b(nbttagcompound1);
                nbttaglist.a((NBTBase) nbttagcompound1);
            }
        }

        nbttagcompound.a("Items", (NBTBase) nbttaglist);
        if (this.c()) {
            nbttagcompound.a("CustomName", this.a);
        }
    }

    public int d() {
        return 64;
    }

    public boolean a(EntityPlayer entityplayer) {
        // CanaryMod: remote inventories
        if (getCanaryDispenser().canOpenRemote()) {
            return true;
        }
        //
        return this.k.r(this.l, this.m, this.n) != this ? false : entityplayer.e((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D) <= 64.0D;
    }

    public void k_() {}

    public void g() {}

    public boolean b(int i0, ItemStack itemstack) {
        return true;
    }

    // CanaryMod
    public CanaryDispenser getCanaryDispenser() {
        return (CanaryDispenser) complexBlock;
    }
    //
}
