package net.minecraft.server;


import java.util.List;
import net.canarymod.api.world.blocks.CanaryBrewingStand;


public class TileEntityBrewingStand extends TileEntity implements ISidedInventory {

    private static final int[] a = new int[] { 3};
    private static final int[] b = new int[] { 0, 1, 2};
    public ItemStack[] c = new ItemStack[4]; // CanaryMod: private => public
    private int d;
    private int e;
    private int f;
    private String g;

    public TileEntityBrewingStand() {
        this.complexBlock = new CanaryBrewingStand(this); // CanaryMod: create once, use forever
    }

    public String b() {
        return this.c() ? this.g : "container.brewing";
    }

    public boolean c() {
        return this.g != null && this.g.length() > 0;
    }

    public void a(String s0) {
        this.g = s0;
    }

    public int j_() {
        return this.c.length;
    }

    public void h() {
        if (this.d > 0) {
            --this.d;
            if (this.d == 0) {
                this.u();
                this.k_();
            } else if (!this.l()) {
                this.d = 0;
                this.k_();
            } else if (this.f != this.c[3].c) {
                this.d = 0;
                this.k_();
            }
        } else if (this.l()) {
            this.d = 400;
            this.f = this.c[3].c;
        }

        int i0 = this.j();

        if (i0 != this.e) {
            this.e = i0;
            this.k.b(this.l, this.m, this.n, i0, 2);
        }

        super.h();
    }

    public int x_() {
        return this.d;
    }

    private boolean l() {
        if (this.c[3] != null && this.c[3].a > 0) {
            ItemStack itemstack = this.c[3];

            if (!Item.f[itemstack.c].w()) {
                return false;
            } else {
                boolean flag0 = false;

                for (int i0 = 0; i0 < 3; ++i0) {
                    if (this.c[i0] != null && this.c[i0].c == Item.bt.cp) {
                        int i1 = this.c[i0].k();
                        int i2 = this.c(i1, itemstack);

                        if (!ItemPotion.f(i1) && ItemPotion.f(i2)) {
                            flag0 = true;
                            break;
                        }

                        List list = Item.bt.c(i1);
                        List list1 = Item.bt.c(i2);

                        if ((i1 <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null) && i1 != i2) {
                            flag0 = true;
                            break;
                        }
                    }
                }

                return flag0;
            }
        } else {
            return false;
        }
    }

    private void u() {
        if (this.l()) {
            ItemStack itemstack = this.c[3];

            for (int i0 = 0; i0 < 3; ++i0) {
                if (this.c[i0] != null && this.c[i0].c == Item.bt.cp) {
                    int i1 = this.c[i0].k();
                    int i2 = this.c(i1, itemstack);
                    List list = Item.bt.c(i1);
                    List list1 = Item.bt.c(i2);

                    if ((i1 <= 0 || list != list1) && (list == null || !list.equals(list1) && list1 != null)) {
                        if (i1 != i2) {
                            this.c[i0].b(i2);
                        }
                    } else if (!ItemPotion.f(i1) && ItemPotion.f(i2)) {
                        this.c[i0].b(i2);
                    }
                }
            }

            if (Item.f[itemstack.c].t()) {
                this.c[3] = new ItemStack(Item.f[itemstack.c].s());
            } else {
                --this.c[3].a;
                if (this.c[3].a <= 0) {
                    this.c[3] = null;
                }
            }
        }
    }

    private int c(int i0, ItemStack itemstack) {
        return itemstack == null ? i0 : (Item.f[itemstack.c].w() ? PotionHelper.a(i0, Item.f[itemstack.c].v()) : i0);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.m("Items");

        this.c = new ItemStack[this.j_()];

        for (int i0 = 0; i0 < nbttaglist.c(); ++i0) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.b(i0);
            byte b0 = nbttagcompound1.c("Slot");

            if (b0 >= 0 && b0 < this.c.length) {
                this.c[b0] = ItemStack.a(nbttagcompound1);
            }
        }

        this.d = nbttagcompound.d("BrewTime");
        if (nbttagcompound.b("CustomName")) {
            this.g = nbttagcompound.i("CustomName");
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("BrewTime", (short) this.d);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i0 = 0; i0 < this.c.length; ++i0) {
            if (this.c[i0] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                nbttagcompound1.a("Slot", (byte) i0);
                this.c[i0].b(nbttagcompound1);
                nbttaglist.a((NBTBase) nbttagcompound1);
            }
        }

        nbttagcompound.a("Items", (NBTBase) nbttaglist);
        if (this.c()) {
            nbttagcompound.a("CustomName", this.g);
        }
    }

    public ItemStack a(int i0) {
        return i0 >= 0 && i0 < this.c.length ? this.c[i0] : null;
    }

    public ItemStack a(int i0, int i1) {
        if (i0 >= 0 && i0 < this.c.length) {
            ItemStack itemstack = this.c[i0];

            this.c[i0] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public ItemStack b(int i0) {
        if (i0 >= 0 && i0 < this.c.length) {
            ItemStack itemstack = this.c[i0];

            this.c[i0] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void a(int i0, ItemStack itemstack) {
        if (i0 >= 0 && i0 < this.c.length) {
            this.c[i0] = itemstack;
        }
    }

    public int d() {
        return 64;
    }

    public boolean a(EntityPlayer entityplayer) {
        return this.k.r(this.l, this.m, this.n) != this ? false : entityplayer.e((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D) <= 64.0D;
    }

    public void f() {}

    public void g() {}

    public boolean b(int i0, ItemStack itemstack) {
        return i0 == 3 ? Item.f[itemstack.c].w() : itemstack.c == Item.bt.cp || itemstack.c == Item.bu.cp;
    }

    public int j() {
        int i0 = 0;

        for (int i1 = 0; i1 < 3; ++i1) {
            if (this.c[i1] != null) {
                i0 |= 1 << i1;
            }
        }

        return i0;
    }

    public int[] c(int i0) {
        return i0 == 1 ? a : b;
    }

    public boolean a(int i0, ItemStack itemstack, int i1) {
        return this.b(i0, itemstack);
    }

    public boolean b(int i0, ItemStack itemstack, int i1) {
        return true;
    }

    // CanaryMod
    public CanaryBrewingStand getCanaryBrewingStand() {
        return (CanaryBrewingStand) complexBlock;
    }
}
