package net.minecraft.server;

import net.canarymod.api.world.blocks.CanaryFurnace;
import net.canarymod.hook.world.SmeltHook;

public class TileEntityFurnace extends TileEntity implements ISidedInventory {

    private static final int[] d = new int[]{0};
    private static final int[] e = new int[]{2, 1};
    private static final int[] f = new int[]{1};
    public ItemStack[] g = new ItemStack[3]; // CanaryMod: private => public
    public int a = 0;
    public int b = 0;
    public int c = 0;
    private String h;

    public TileEntityFurnace() {
        this.complexBlock = new CanaryFurnace(this); // CanaryMod: create once, use forever
    }

    public int j_() {
        return this.g.length;
    }

    public ItemStack a(int i0) {
        return this.g[i0];
    }

    public ItemStack a(int i0, int i1) {
        if (this.g[i0] != null) {
            ItemStack itemstack;

            if (this.g[i0].b <= i1) {
                itemstack = this.g[i0];
                this.g[i0] = null;
                return itemstack;
            }
            else {
                itemstack = this.g[i0].a(i1);
                if (this.g[i0].b == 0) {
                    this.g[i0] = null;
                }

                return itemstack;
            }
        }
        else {
            return null;
        }
    }

    public ItemStack a_(int i0) {
        if (this.g[i0] != null) {
            ItemStack itemstack = this.g[i0];

            this.g[i0] = null;
            return itemstack;
        }
        else {
            return null;
        }
    }

    public void a(int i0, ItemStack itemstack) {
        this.g[i0] = itemstack;
        if (itemstack != null && itemstack.b > this.d()) {
            itemstack.b = this.d();
        }
    }

    public String b() {
        return this.c() ? this.h : "container.furnace";
    }

    public boolean c() {
        return this.h != null && this.h.length() > 0;
    }

    public void a(String s0) {
        this.h = s0;
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.m("Items");

        this.g = new ItemStack[this.j_()];

        for (int i0 = 0; i0 < nbttaglist.c(); ++i0) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.b(i0);
            byte b0 = nbttagcompound1.c("Slot");

            if (b0 >= 0 && b0 < this.g.length) {
                this.g[b0] = ItemStack.a(nbttagcompound1);
            }
        }

        this.a = nbttagcompound.d("BurnTime");
        this.c = nbttagcompound.d("CookTime");
        this.b = a(this.g[1]);
        if (nbttagcompound.b("CustomName")) {
            this.h = nbttagcompound.i("CustomName");
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("BurnTime", (short)this.a);
        nbttagcompound.a("CookTime", (short)this.c);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i0 = 0; i0 < this.g.length; ++i0) {
            if (this.g[i0] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                nbttagcompound1.a("Slot", (byte)i0);
                this.g[i0].b(nbttagcompound1);
                nbttaglist.a((NBTBase)nbttagcompound1);
            }
        }

        nbttagcompound.a("Items", (NBTBase)nbttaglist);
        if (this.c()) {
            nbttagcompound.a("CustomName", this.h);
        }
    }

    public int d() {
        return 64;
    }

    public boolean j() {
        return this.a > 0;
    }

    public void h() {
        boolean flag0 = this.a > 0;
        boolean flag1 = false;

        if (this.a > 0) {
            --this.a;
        }

        if (!this.k.I) {
            if (this.a == 0 && this.u()) {
                this.b = this.a = a(this.g[1]);
                if (this.a > 0) {
                    flag1 = true;
                    if (this.g[1] != null) {
                        --this.g[1].b;
                        if (this.g[1].b == 0) {
                            Item item = this.g[1].b().t();

                            this.g[1] = item != null ? new ItemStack(item) : null;
                        }
                    }
                }
            }

            if (this.j() && this.u()) {
                ++this.c;
                if (this.c == 200) {
                    this.c = 0;
                    this.l();
                    flag1 = true;
                }
            }
            else {
                this.c = 0;
            }

            if (flag0 != this.a > 0) {
                flag1 = true;
                BlockFurnace.a(this.a > 0, this.k, this.l, this.m, this.n);
            }
        }

        if (flag1) {
            this.e();
        }
    }

    private boolean u() {
        if (this.g[0] == null) {
            return false;
        }
        else {
            ItemStack itemstack = FurnaceRecipes.a().b(this.g[0].b().cv);

            return itemstack == null ? false : (this.g[2] == null ? true : (!this.g[2].a(itemstack) ? false : (this.g[2].b < this.d() && this.g[2].b < this.g[2].e() ? true : this.g[2].b < itemstack.e())));
        }
    }

    public void l() {
        if (this.u()) {
            ItemStack itemstack = FurnaceRecipes.a().b(this.g[0].b().cv);
            // CanaryMod: Smelt
            SmeltHook hook = (SmeltHook)new SmeltHook(getCanaryFurnace(), itemstack.getCanaryItem()).call();
            if (hook.isCanceled()) {
                return;
            }
            //

            if (this.g[2] == null) {
                this.g[2] = itemstack.m();
            }
            else if (this.g[2].d == itemstack.d) {
                ++this.g[2].b;
            }

            --this.g[0].b;
            if (this.g[0].b <= 0) {
                this.g[0] = null;
            }
        }
    }

    public static int a(ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        }
        else {
            int i0 = itemstack.b().cv;
            Item item = itemstack.b();

            if (i0 < 256 && Block.s[i0] != null) {
                Block block = Block.s[i0];

                if (block == Block.bT) {
                    return 150;
                }

                if (block.cU == Material.d) {
                    return 300;
                }

                if (block == Block.cE) {
                    return 16000;
                }
            }

            return item instanceof ItemTool && ((ItemTool)item).g().equals("WOOD") ? 200 : (item instanceof ItemSword && ((ItemSword)item).i().equals("WOOD") ? 200 : (item instanceof ItemHoe && ((ItemHoe)item).g().equals("WOOD") ? 200 : (i0 == Item.F.cv ? 100 : (i0 == Item.o.cv ? 1600 : (i0 == Item.aA.cv ? 20000 : (i0 == Block.D.cF ? 100 : (i0 == Item.bq.cv ? 2400 : 0)))))));
        }
    }

    public static boolean b(ItemStack itemstack) {
        return a(itemstack) > 0;
    }

    public boolean a(EntityPlayer entityplayer) {
        // CanaryMod: remote inventories
        if (getCanaryFurnace().canOpenRemote()) {
            return true;
        }
        //
        return this.k.r(this.l, this.m, this.n) != this ? false : entityplayer.e((double)this.l + 0.5D, (double)this.m + 0.5D, (double)this.n + 0.5D) <= 64.0D;
    }

    public void k_() {
    }

    public void g() {
    }

    public boolean b(int i0, ItemStack itemstack) {
        return i0 == 2 ? false : (i0 == 1 ? b(itemstack) : true);
    }

    public int[] c(int i0) {
        return i0 == 0 ? e : (i0 == 1 ? d : f);
    }

    public boolean a(int i0, ItemStack itemstack, int i1) {
        return this.b(i0, itemstack);
    }

    public boolean b(int i0, ItemStack itemstack, int i1) {
        return i1 != 0 || i0 != 1 || itemstack.d == Item.ay.cv;
    }

    // CanaryMod
    public CanaryFurnace getCanaryFurnace() {
        return (CanaryFurnace)complexBlock;
    }
}
