package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

public class TileEntityBeacon extends TileEntity implements IInventory {

    public static final Potion[][] a = new Potion[][] { { Potion.c, Potion.e}, { Potion.m, Potion.j}, { Potion.g}, { Potion.l}};
    private boolean d;
    private int e = -1;
    private int f;
    private int g;
    private ItemStack h;
    private String i;

    public TileEntityBeacon() {}

    public void h() {
        if (this.k.G() % 80L == 0L) {
            this.v();
            this.u();
        }
    }

    private void u() {
        if (this.d && this.e > 0 && !this.k.I && this.f > 0) {
            double d0 = (double) (this.e * 10 + 10);
            byte b0 = 0;

            if (this.e >= 4 && this.f == this.g) {
                b0 = 1;
            }

            AxisAlignedBB axisalignedbb = AxisAlignedBB.a().a((double) this.l, (double) this.m, (double) this.n, (double) (this.l + 1), (double) (this.m + 1), (double) (this.n + 1)).b(d0, d0, d0);

            axisalignedbb.e = (double) this.k.P();
            List list = this.k.a(EntityPlayer.class, axisalignedbb);
            Iterator iterator = list.iterator();

            EntityPlayer entityplayer;

            while (iterator.hasNext()) {
                entityplayer = (EntityPlayer) iterator.next();
                entityplayer.d(new PotionEffect(this.f, 180, b0, true));
            }

            if (this.e >= 4 && this.f != this.g && this.g > 0) {
                iterator = list.iterator();

                while (iterator.hasNext()) {
                    entityplayer = (EntityPlayer) iterator.next();
                    entityplayer.d(new PotionEffect(this.g, 180, 0, true));
                }
            }
        }
    }

    private void v() {
        if (!this.k.l(this.l, this.m + 1, this.n)) {
            this.d = false;
            this.e = 0;
        } else {
            this.d = true;
            this.e = 0;

            for (int i0 = 1; i0 <= 4; this.e = i0++) {
                int i1 = this.m - i0;

                if (i1 < 0) {
                    break;
                }

                boolean flag0 = true;

                for (int i2 = this.l - i0; i2 <= this.l + i0 && flag0; ++i2) {
                    for (int i3 = this.n - i0; i3 <= this.n + i0; ++i3) {
                        int i4 = this.k.a(i2, i1, i3);

                        if (i4 != Block.bZ.cz && i4 != Block.al.cz && i4 != Block.aB.cz && i4 != Block.am.cz) {
                            flag0 = false;
                            break;
                        }
                    }
                }

                if (!flag0) {
                    break;
                }
            }

            if (this.e == 0) {
                this.d = false;
            }
        }
    }

    public int j() {
        return this.f;
    }

    public int k() {
        return this.g;
    }

    public int l() {
        return this.e;
    }

    public void d(int i0) {
        this.f = 0;

        for (int i1 = 0; i1 < this.e && i1 < 3; ++i1) {
            Potion[] apotion = a[i1];
            int i2 = apotion.length;

            for (int i3 = 0; i3 < i2; ++i3) {
                Potion potion = apotion[i3];

                if (potion.H == i0) {
                    this.f = i0;
                    return;
                }
            }
        }
    }

    public void e(int i0) {
        this.g = 0;
        if (this.e >= 4) {
            for (int i1 = 0; i1 < 4; ++i1) {
                Potion[] apotion = a[i1];
                int i2 = apotion.length;

                for (int i3 = 0; i3 < i2; ++i3) {
                    Potion potion = apotion[i3];

                    if (potion.H == i0) {
                        this.g = i0;
                        return;
                    }
                }
            }
        }
    }

    public Packet m() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        this.b(nbttagcompound);
        return new Packet132TileEntityData(this.l, this.m, this.n, 3, nbttagcompound);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.f = nbttagcompound.e("Primary");
        this.g = nbttagcompound.e("Secondary");
        this.e = nbttagcompound.e("Levels");
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Primary", this.f);
        nbttagcompound.a("Secondary", this.g);
        nbttagcompound.a("Levels", this.e);
    }

    public int j_() {
        return 1;
    }

    public ItemStack a(int i0) {
        return i0 == 0 ? this.h : null;
    }

    public ItemStack a(int i0, int i1) {
        if (i0 == 0 && this.h != null) {
            if (i1 >= this.h.a) {
                ItemStack itemstack = this.h;

                this.h = null;
                return itemstack;
            } else {
                this.h.a -= i1;
                return new ItemStack(this.h.c, i1, this.h.k());
            }
        } else {
            return null;
        }
    }

    public ItemStack b(int i0) {
        if (i0 == 0 && this.h != null) {
            ItemStack itemstack = this.h;

            this.h = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void a(int i0, ItemStack itemstack) {
        if (i0 == 0) {
            this.h = itemstack;
        }
    }

    public String b() {
        return this.c() ? this.i : "container.beacon";
    }

    public boolean c() {
        return this.i != null && this.i.length() > 0;
    }

    public void a(String s0) {
        this.i = s0;
    }

    public int d() {
        return 1;
    }

    public boolean a(EntityPlayer entityplayer) {
        return this.k.r(this.l, this.m, this.n) != this ? false : entityplayer.e((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D) <= 64.0D;
    }

    public void f() {}

    public void g() {}

    public boolean b(int i0, ItemStack itemstack) {
        return itemstack.c == Item.bI.cp || itemstack.c == Item.o.cp || itemstack.c == Item.q.cp || itemstack.c == Item.p.cp;
    }

    // CanaryMod: Container<ItemStack>
    // Going to have to do something different here. Not sure what yet though.
    @Override
    public ItemStack[] getContents() {
        return null;
    }

    @Override
    public void setContents(ItemStack[] items) {}

    @Override
    public ItemStack getSlot(int index) {
        return null;
    }

    @Override
    public void addItem(int itemId, int amount) {}

    @Override
    public void addItem(net.canarymod.api.inventory.Item item) {}

    @Override
    public int getEmptySlot() {
        return 0;
    }

    @Override
    public void setSlot(int index, ItemStack value) {}

    @Override
    public int getInventorySize() {
        return 0;
    }

    @Override
    public String getInventoryName() {
        return null;
    }

    @Override
    public void setInventoryName(String value) {}

    @Override
    public void clearContents() {}

    @Override
    public net.canarymod.api.inventory.Item getItem(int id, int amount) {
        return null;
    }

    @Override
    public net.canarymod.api.inventory.Item getItem(int id) {
        return null;
    }

    @Override
    public net.canarymod.api.inventory.Item removeItem(net.canarymod.api.inventory.Item item) {
        return null;
    }

    @Override
    public net.canarymod.api.inventory.Item removeItem(int id) {
        return null;
    }

    @Override
    public ItemStack decreaseItemStackSize(int itemId, int amount) {
        return null;
    }

    @Override
    public int getInventoryStackLimit() {
        return 0;
    }

    @Override
    public boolean hasItemStack(ItemStack oItemStack) {
        return false;
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return false;
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return false;
    }

    @Override
    public boolean hasItem(int itemId) {
        return false;
    }

    @Override
    public void update() {}

}
