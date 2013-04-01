package net.minecraft.server;

import java.util.List;
import net.canarymod.api.world.blocks.CanaryHopper;

public class TileEntityHopper extends TileEntity implements Hopper {

    private ItemStack[] a = new ItemStack[5];
    private String b;
    private int c = -1;
    private CanaryHopper hopper; // CanaryMod inventory instance

    public TileEntityHopper() {
        this.hopper = new CanaryHopper(this); // CanaryMod: create once, use forever
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.m("Items");

        this.a = new ItemStack[this.j_()];
        if (nbttagcompound.b("CustomName")) {
            this.b = nbttagcompound.i("CustomName");
        }

        this.c = nbttagcompound.e("TransferCooldown");

        for (int i0 = 0; i0 < nbttaglist.c(); ++i0) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.b(i0);
            byte b0 = nbttagcompound1.c("Slot");

            if (b0 >= 0 && b0 < this.a.length) {
                this.a[b0] = ItemStack.a(nbttagcompound1);
            }
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
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
        nbttagcompound.a("TransferCooldown", this.c);
        if (this.c()) {
            nbttagcompound.a("CustomName", this.b);
        }
    }

    public void k_() {
        super.k_();
    }

    public int j_() {
        return this.a.length;
    }

    public ItemStack a(int i0) {
        return this.a[i0];
    }

    public ItemStack a(int i0, int i1) {
        if (this.a[i0] != null) {
            ItemStack itemstack;

            if (this.a[i0].a <= i1) {
                itemstack = this.a[i0];
                this.a[i0] = null;
                return itemstack;
            } else {
                itemstack = this.a[i0].a(i1);
                if (this.a[i0].a == 0) {
                    this.a[i0] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    public ItemStack b(int i0) {
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
        if (itemstack != null && itemstack.a > this.d()) {
            itemstack.a = this.d();
        }
    }

    public String b() {
        return this.c() ? this.b : "container.hopper";
    }

    public boolean c() {
        return this.b != null && this.b.length() > 0;
    }

    public void a(String s0) {
        this.b = s0;
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
        return true;
    }

    public void h() {
        if (this.k != null && !this.k.I) {
            --this.c;
            if (!this.l()) {
                this.c(0);
                this.j();
            }
        }
    }

    public boolean j() {
        if (this.k != null && !this.k.I) {
            if (!this.l() && BlockHopper.d(this.p())) {
                boolean flag0 = this.u() | a((Hopper) this);

                if (flag0) {
                    this.c(8);
                    this.k_();
                    return true;
                }
            }

            return false;
        } else {
            return false;
        }
    }

    private boolean u() {
        IInventory iinventory = this.v();

        if (iinventory == null) {
            return false;
        } else {
            for (int i0 = 0; i0 < this.j_(); ++i0) {
                if (this.a(i0) != null) {
                    ItemStack itemstack = this.a(i0).m();
                    ItemStack itemstack1 = a(iinventory, this.a(i0, 1), Facing.a[BlockHopper.c(this.p())]);

                    if (itemstack1 == null || itemstack1.a == 0) {
                        iinventory.k_();
                        return true;
                    }

                    this.a(i0, itemstack);
                }
            }

            return false;
        }
    }

    public static boolean a(Hopper hopper) {
        IInventory iinventory = b(hopper);

        if (iinventory != null) {
            byte b0 = 0;

            if (iinventory instanceof ISidedInventory && b0 > -1) {
                ISidedInventory isidedinventory = (ISidedInventory) iinventory;
                int[] aint = isidedinventory.c(b0);

                for (int i0 = 0; i0 < aint.length; ++i0) {
                    if (a(hopper, iinventory, aint[i0], b0)) {
                        return true;
                    }
                }
            } else {
                int i1 = iinventory.j_();

                for (int i2 = 0; i2 < i1; ++i2) {
                    if (a(hopper, iinventory, i2, b0)) {
                        return true;
                    }
                }
            }
        } else {
            EntityItem entityitem = a(hopper.az(), hopper.aA(), hopper.aB() + 1.0D, hopper.aC());

            if (entityitem != null) {
                return a((IInventory) hopper, entityitem);
            }
        }

        return false;
    }

    private static boolean a(Hopper hopper, IInventory iinventory, int i0, int i1) {
        ItemStack itemstack = iinventory.a(i0);

        if (itemstack != null && b(iinventory, itemstack, i0, i1)) {
            ItemStack itemstack1 = itemstack.m();
            ItemStack itemstack2 = a(hopper, iinventory.a(i0, 1), -1);

            if (itemstack2 == null || itemstack2.a == 0) {
                iinventory.k_();
                return true;
            }

            iinventory.a(i0, itemstack1);
        }

        return false;
    }

    public static boolean a(IInventory iinventory, EntityItem entityitem) {
        boolean flag0 = false;

        if (entityitem == null) {
            return false;
        } else {
            ItemStack itemstack = entityitem.d().m();
            ItemStack itemstack1 = a(iinventory, itemstack, -1);

            if (itemstack1 != null && itemstack1.a != 0) {
                entityitem.a(itemstack1);
            } else {
                flag0 = true;
                entityitem.w();
            }

            return flag0;
        }
    }

    public static ItemStack a(IInventory iinventory, ItemStack itemstack, int i0) {
        if (iinventory instanceof ISidedInventory && i0 > -1) {
            ISidedInventory isidedinventory = (ISidedInventory) iinventory;
            int[] aint = isidedinventory.c(i0);

            for (int i1 = 0; i1 < aint.length && itemstack != null && itemstack.a > 0; ++i1) {
                itemstack = c(iinventory, itemstack, aint[i1], i0);
            }
        } else {
            int i2 = iinventory.j_();

            for (int i3 = 0; i3 < i2 && itemstack != null && itemstack.a > 0; ++i3) {
                itemstack = c(iinventory, itemstack, i3, i0);
            }
        }

        if (itemstack != null && itemstack.a == 0) {
            itemstack = null;
        }

        return itemstack;
    }

    private static boolean a(IInventory iinventory, ItemStack itemstack, int i0, int i1) {
        return !iinventory.b(i0, itemstack) ? false : !(iinventory instanceof ISidedInventory) || ((ISidedInventory) iinventory).a(i0, itemstack, i1);
    }

    private static boolean b(IInventory iinventory, ItemStack itemstack, int i0, int i1) {
        return !(iinventory instanceof ISidedInventory) || ((ISidedInventory) iinventory).b(i0, itemstack, i1);
    }

    private static ItemStack c(IInventory iinventory, ItemStack itemstack, int i0, int i1) {
        ItemStack itemstack1 = iinventory.a(i0);

        if (a(iinventory, itemstack, i0, i1)) {
            boolean flag0 = false;

            if (itemstack1 == null) {
                iinventory.a(i0, itemstack);
                itemstack = null;
                flag0 = true;
            } else if (a(itemstack1, itemstack)) {
                int i2 = itemstack.e() - itemstack1.a;
                int i3 = Math.min(itemstack.a, i2);

                itemstack.a -= i3;
                itemstack1.a += i3;
                flag0 = i3 > 0;
            }

            if (flag0) {
                if (iinventory instanceof TileEntityHopper) {
                    ((TileEntityHopper) iinventory).c(8);
                }

                iinventory.k_();
            }
        }

        return itemstack;
    }

    private IInventory v() {
        int i0 = BlockHopper.c(this.p());

        return b(this.az(), (double) (this.l + Facing.b[i0]), (double) (this.m + Facing.c[i0]), (double) (this.n + Facing.d[i0]));
    }

    public static IInventory b(Hopper hopper) {
        return b(hopper.az(), hopper.aA(), hopper.aB() + 1.0D, hopper.aC());
    }

    public static EntityItem a(World world, double d0, double d1, double d2) {
        List list = world.a(EntityItem.class, AxisAlignedBB.a().a(d0, d1, d2, d0 + 1.0D, d1 + 1.0D, d2 + 1.0D), IEntitySelector.a);

        return list.size() > 0 ? (EntityItem) list.get(0) : null;
    }

    public static IInventory b(World world, double d0, double d1, double d2) {
        IInventory iinventory = null;
        int i0 = MathHelper.c(d0);
        int i1 = MathHelper.c(d1);
        int i2 = MathHelper.c(d2);
        TileEntity tileentity = world.r(i0, i1, i2);

        if (tileentity != null && tileentity instanceof IInventory) {
            iinventory = (IInventory) tileentity;
            if (iinventory instanceof TileEntityChest) {
                int i3 = world.a(i0, i1, i2);
                Block block = Block.r[i3];

                if (block instanceof BlockChest) {
                    iinventory = ((BlockChest) block).g_(world, i0, i1, i2);
                }
            }
        }

        if (iinventory == null) {
            List list = world.a((Entity) null, AxisAlignedBB.a().a(d0, d1, d2, d0 + 1.0D, d1 + 1.0D, d2 + 1.0D), IEntitySelector.b);

            if (list != null && list.size() > 0) {
                iinventory = (IInventory) list.get(world.s.nextInt(list.size()));
            }
        }

        return iinventory;
    }

    private static boolean a(ItemStack itemstack, ItemStack itemstack1) {
        return itemstack.c != itemstack1.c ? false : (itemstack.k() != itemstack1.k() ? false : (itemstack.a > itemstack.e() ? false : ItemStack.a(itemstack, itemstack1)));
    }

    public double aA() {
        return (double) this.l;
    }

    public double aB() {
        return (double) this.m;
    }

    public double aC() {
        return (double) this.n;
    }

    public void c(int i0) {
        this.c = i0;
    }

    public boolean l() {
        return this.c > 0;
    }

    // CanaryMod
    public CanaryHopper getCanaryHopper() {
        return hopper;
    }
}
