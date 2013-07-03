package net.minecraft.server;


import java.util.concurrent.Callable;
import net.canarymod.Canary;
import net.canarymod.hook.player.ItemPickupHook;


public class InventoryPlayer implements IInventory {

    public ItemStack[] a = new ItemStack[36];
    public ItemStack[] b = new ItemStack[4];
    public int c = 0;
    public EntityPlayer d;
    private ItemStack g;
    public boolean e = false;
    private String name = "container.inventory"; // CanaryMod: custom inventory name

    public InventoryPlayer(EntityPlayer entityplayer) {
        this.d = entityplayer;
    }

    public ItemStack h() {
        return this.c < 9 && this.c >= 0 ? this.a[this.c] : null;
    }

    public static int i() {
        return 9;
    }

    private int g(int i0) {
        for (int i1 = 0; i1 < this.a.length; ++i1) {
            if (this.a[i1] != null && this.a[i1].d == i0) {
                return i1;
            }
        }

        return -1;
    }

    private int d(ItemStack itemstack) {
        for (int i0 = 0; i0 < this.a.length; ++i0) {
            if (this.a[i0] != null && this.a[i0].d == itemstack.d && this.a[i0].f() && this.a[i0].b < this.a[i0].e() && this.a[i0].b < this.d() && (!this.a[i0].h() || this.a[i0].k() == itemstack.k()) && ItemStack.a(this.a[i0], itemstack)) {
                return i0;
            }
        }

        return -1;
    }

    public int j() {
        for (int i0 = 0; i0 < this.a.length; ++i0) {
            if (this.a[i0] == null) {
                return i0;
            }
        }

        return -1;
    }

    public int b(int i0, int i1) {
        int i2 = 0;

        int i3;
        ItemStack itemstack;

        for (i3 = 0; i3 < this.a.length; ++i3) {
            itemstack = this.a[i3];
            if (itemstack != null && (i0 <= -1 || itemstack.d == i0) && (i1 <= -1 || itemstack.k() == i1)) {
                i2 += itemstack.b;
                this.a[i3] = null;
            }
        }

        for (i3 = 0; i3 < this.b.length; ++i3) {
            itemstack = this.b[i3];
            if (itemstack != null && (i0 <= -1 || itemstack.d == i0) && (i1 <= -1 || itemstack.k() == i1)) {
                i2 += itemstack.b;
                this.b[i3] = null;
            }
        }

        if (this.g != null) {
            if (i0 > -1 && this.g.d != i0) {
                return i2;
            }

            if (i1 > -1 && this.g.k() != i1) {
                return i2;
            }

            i2 += this.g.b;
            this.b((ItemStack) null);
        }

        return i2;
    }

    private int e(ItemStack itemstack) {
        int i0 = itemstack.d;
        int i1 = itemstack.b;
        int i2;

        if (itemstack.e() == 1) {
            i2 = this.j();
            if (i2 < 0) {
                return i1;
            } else {
                if (this.a[i2] == null) {
                    this.a[i2] = ItemStack.b(itemstack);
                }

                return 0;
            }
        } else {
            i2 = this.d(itemstack);
            if (i2 < 0) {
                i2 = this.j();
            }

            if (i2 < 0) {
                return i1;
            } else {
                if (this.a[i2] == null) {
                    this.a[i2] = new ItemStack(i0, 0, itemstack.k());
                    if (itemstack.p()) {
                        this.a[i2].d((NBTTagCompound) itemstack.q().b());
                    }
                }

                int i3 = i1;

                if (i1 > this.a[i2].e() - this.a[i2].b) {
                    i3 = this.a[i2].e() - this.a[i2].b;
                }

                if (i3 > this.d() - this.a[i2].b) {
                    i3 = this.d() - this.a[i2].b;
                }

                if (i3 == 0) {
                    return i1;
                } else {
                    i1 -= i3;
                    this.a[i2].b += i3;
                    this.a[i2].c = 5;
                    return i1;
                }
            }
        }
    }

    public void k() {
        for (int i0 = 0; i0 < this.a.length; ++i0) {
            if (this.a[i0] != null) {
                this.a[i0].a(this.d.q, this.d, i0, this.c == i0);
            }
        }
    }

    public boolean d(int i0) {
        int i1 = this.g(i0);

        if (i1 < 0) {
            return false;
        } else {
            if (--this.a[i1].b <= 0) {
                this.a[i1] = null;
            }

            return true;
        }
    }

    public boolean e(int i0) {
        int i1 = this.g(i0);

        return i1 >= 0;
    }

    // CanaryMod: Simulate Pickup (Its the same as a(ItemStack) but without altering the inventory
    public boolean canPickup(EntityItem entityitem) {
        ItemStack itemstack = entityitem.d();
        int i;

        if (itemstack.h()) {
            i = InventoryPlayer.i();
            if (i >= 0) {
                // CanaryMod: ItemPickUp
                ItemPickupHook hook = new ItemPickupHook(((EntityPlayerMP) this.d).getPlayer(), (net.canarymod.api.entity.EntityItem) entityitem.getCanaryEntity());

                Canary.hooks().callHook(hook);
                return !hook.isCanceled();
                //
            } else if (this.d.ce.d) {
                return true;
            } else {
                return false;
            }
        } else {
            int slot = 0;
            int left = itemstack.a;

            do {
                ItemStack itemstack1 = this.a[slot];
                int delta = 0;

                if (itemstack1 == null) {
                    delta = Math.min(64, left);
                } else if (itemstack1.a < 64 && itemstack.c == itemstack1.c && itemstack.e() == itemstack1.e()) {
                    delta = Math.min(64 - itemstack.a, left);
                }
                left -= delta;
                slot++;
            } while (left > 0 && slot < 36);
            if (itemstack.a - left > 0) {
                // CanaryMod: ItemPickUp
                ItemPickupHook hook = new ItemPickupHook(((EntityPlayerMP) this.d).getPlayer(), (net.canarymod.api.entity.EntityItem) entityitem.getCanaryEntity());

                Canary.hooks().callHook(hook);
                return !hook.isCanceled();
                //
            } else {
                return false;
            }
        }
    }

    public boolean a(ItemStack itemstack) {
        if (itemstack == null) {
            return false;
        } else if (itemstack.b == 0) {
            return false;
        } else {
            try {
                int i0;

                if (itemstack.i()) {
                    i0 = this.j();
                    if (i0 >= 0) {
                        this.a[i0] = ItemStack.b(itemstack);
                        this.a[i0].c = 5;
                        itemstack.b = 0;
                        return true;
                    } else if (this.d.bG.d) {
                        itemstack.b = 0;
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    do {
                        i0 = itemstack.b;
                        itemstack.b = this.e(itemstack);
                    }
                    while (itemstack.b > 0 && itemstack.b < i0);

                    if (itemstack.b == i0 && this.d.bG.d) {
                        itemstack.b = 0;
                        return true;
                    } else {
                        return itemstack.b < i0;
                    }
                }
            } catch (Throwable throwable) {
                CrashReport crashreport = CrashReport.a(throwable, "Adding item to inventory");
                CrashReportCategory crashreportcategory = crashreport.a("Item being added");

                crashreportcategory.a("Item ID", Integer.valueOf(itemstack.d));
                crashreportcategory.a("Item data", Integer.valueOf(itemstack.k()));
                crashreportcategory.a("Item name", (Callable) (new CallableItemName(this, itemstack)));
                throw new ReportedException(crashreport);
            }
        }
    }

    public ItemStack a(int i0, int i1) {
        ItemStack[] aitemstack = this.a;

        if (i0 >= this.a.length) {
            aitemstack = this.b;
            i0 -= this.a.length;
        }

        if (aitemstack[i0] != null) {
            ItemStack itemstack;

            if (aitemstack[i0].b <= i1) {
                itemstack = aitemstack[i0];
                aitemstack[i0] = null;
                return itemstack;
            } else {
                itemstack = aitemstack[i0].a(i1);
                if (aitemstack[i0].b == 0) {
                    aitemstack[i0] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    public ItemStack a_(int i0) {
        ItemStack[] aitemstack = this.a;

        if (i0 >= this.a.length) {
            aitemstack = this.b;
            i0 -= this.a.length;
        }

        if (aitemstack[i0] != null) {
            ItemStack itemstack = aitemstack[i0];

            aitemstack[i0] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void a(int i0, ItemStack itemstack) {
        ItemStack[] aitemstack = this.a;

        if (i0 >= aitemstack.length) {
            i0 -= aitemstack.length;
            aitemstack = this.b;
        }

        aitemstack[i0] = itemstack;
    }

    public float a(Block block) {
        float f0 = 1.0F;

        if (this.a[this.c] != null) {
            f0 *= this.a[this.c].a(block);
        }

        return f0;
    }

    public NBTTagList a(NBTTagList nbttaglist) {
        int i0;
        NBTTagCompound nbttagcompound;

        for (i0 = 0; i0 < this.a.length; ++i0) {
            if (this.a[i0] != null) {
                nbttagcompound = new NBTTagCompound();
                nbttagcompound.a("Slot", (byte) i0);
                this.a[i0].b(nbttagcompound);
                nbttaglist.a((NBTBase) nbttagcompound);
            }
        }

        for (i0 = 0; i0 < this.b.length; ++i0) {
            if (this.b[i0] != null) {
                nbttagcompound = new NBTTagCompound();
                nbttagcompound.a("Slot", (byte) (i0 + 100));
                this.b[i0].b(nbttagcompound);
                nbttaglist.a((NBTBase) nbttagcompound);
            }
        }

        return nbttaglist;
    }

    public void b(NBTTagList nbttaglist) {
        this.a = new ItemStack[36];
        this.b = new ItemStack[4];

        for (int i0 = 0; i0 < nbttaglist.c(); ++i0) {
            NBTTagCompound nbttagcompound = (NBTTagCompound) nbttaglist.b(i0);
            int i1 = nbttagcompound.c("Slot") & 255;
            ItemStack itemstack = ItemStack.a(nbttagcompound);

            if (itemstack != null) {
                if (i1 >= 0 && i1 < this.a.length) {
                    this.a[i1] = itemstack;
                }

                if (i1 >= 100 && i1 < this.b.length + 100) {
                    this.b[i1 - 100] = itemstack;
                }
            }
        }
    }

    public int j_() {
        return this.a.length + 4;
    }

    public ItemStack a(int i0) {
        ItemStack[] aitemstack = this.a;

        if (i0 >= aitemstack.length) {
            i0 -= aitemstack.length;
            aitemstack = this.b;
        }

        return aitemstack[i0];
    }

    public String b() {
        return name; // CanaryMod: return name
    }

    public boolean c() {
        return false;
    }

    public int d() {
        return 64;
    }

    public boolean b(Block block) {
        if (block.cU.l()) {
            return true;
        } else {
            ItemStack itemstack = this.a(this.c);

            return itemstack != null ? itemstack.b(block) : false;
        }
    }

    public ItemStack f(int i0) {
        return this.b[i0];
    }

    public int l() {
        int i0 = 0;

        for (int i1 = 0; i1 < this.b.length; ++i1) {
            if (this.b[i1] != null && this.b[i1].b() instanceof ItemArmor) {
                int i2 = ((ItemArmor) this.b[i1].b()).c;

                i0 += i2;
            }
        }

        return i0;
    }

    public void a(float f0) {
        f0 /= 4.0F;
        if (f0 < 1.0F) {
            f0 = 1.0F;
        }

        for (int i0 = 0; i0 < this.b.length; ++i0) {
            if (this.b[i0] != null && this.b[i0].b() instanceof ItemArmor) {
                this.b[i0].a((int) f0, (EntityLivingBase) this.d);
                if (this.b[i0].b == 0) {
                    this.b[i0] = null;
                }
            }
        }
    }

    public void m() {
        int i0;

        for (i0 = 0; i0 < this.a.length; ++i0) {
            if (this.a[i0] != null) {
                this.d.a(this.a[i0], true);
                this.a[i0] = null;
            }
        }

        for (i0 = 0; i0 < this.b.length; ++i0) {
            if (this.b[i0] != null) {
                this.d.a(this.b[i0], true);
                this.b[i0] = null;
            }
        }
    }

    public void e() {
        this.e = true;
    }

    public void b(ItemStack itemstack) {
        this.g = itemstack;
    }

    public ItemStack o() {
        return this.g;
    }

    public boolean a(EntityPlayer entityplayer) {
        return this.d.M ? false : entityplayer.e(this.d) <= 64.0D;
    }

    public boolean c(ItemStack itemstack) {
        int i0;

        for (i0 = 0; i0 < this.b.length; ++i0) {
            if (this.b[i0] != null && this.b[i0].a(itemstack)) {
                return true;
            }
        }

        for (i0 = 0; i0 < this.a.length; ++i0) {
            if (this.a[i0] != null && this.a[i0].a(itemstack)) {
                return true;
            }
        }

        return false;
    }

    public void k_() {}

    public void g() {}

    public boolean b(int i0, ItemStack itemstack) {
        return true;
    }

    public void b(InventoryPlayer inventoryplayer) {
        int i0;

        for (i0 = 0; i0 < this.a.length; ++i0) {
            this.a[i0] = ItemStack.b(inventoryplayer.a[i0]);
        }

        for (i0 = 0; i0 < this.b.length; ++i0) {
            this.b[i0] = ItemStack.b(inventoryplayer.b[i0]);
        }

        this.c = inventoryplayer.c;
    }

    // CanaryMod
    public void setName(String value) {
        this.name = value;
    }
}
