package net.minecraft.server;

import java.util.Arrays;
import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.ItemType;

public class TileEntityFurnace extends TileEntity implements ISidedInventory {

    private static final int[] d = new int[] { 0};
    private static final int[] e = new int[] { 2, 1};
    private static final int[] f = new int[] { 1};
    private ItemStack[] g = new ItemStack[3];
    public int a = 0;
    public int b = 0;
    public int c = 0;
    private String h;
    private CanaryInventory inventory; // CanaryMod inventory instance

    public TileEntityFurnace() {
        this.inventory = new CanaryInventory(this); // CanaryMod: create once, use forever
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

            if (this.g[i0].a <= i1) {
                itemstack = this.g[i0];
                this.g[i0] = null;
                return itemstack;
            } else {
                itemstack = this.g[i0].a(i1);
                if (this.g[i0].a == 0) {
                    this.g[i0] = null;
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    public ItemStack b(int i0) {
        if (this.g[i0] != null) {
            ItemStack itemstack = this.g[i0];

            this.g[i0] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void a(int i0, ItemStack itemstack) {
        this.g[i0] = itemstack;
        if (itemstack != null && itemstack.a > this.d()) {
            itemstack.a = this.d();
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
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.b(i0);
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
        nbttagcompound.a("BurnTime", (short) this.a);
        nbttagcompound.a("CookTime", (short) this.c);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i0 = 0; i0 < this.g.length; ++i0) {
            if (this.g[i0] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                nbttagcompound1.a("Slot", (byte) i0);
                this.g[i0].b(nbttagcompound1);
                nbttaglist.a((NBTBase) nbttagcompound1);
            }
        }

        nbttagcompound.a("Items", (NBTBase) nbttaglist);
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
                        --this.g[1].a;
                        if (this.g[1].a == 0) {
                            Item item = this.g[1].b().s();

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
            } else {
                this.c = 0;
            }

            if (flag0 != this.a > 0) {
                flag1 = true;
                BlockFurnace.a(this.a > 0, this.k, this.l, this.m, this.n);
            }
        }

        if (flag1) {
            this.k_();
        }
    }

    private boolean u() {
        if (this.g[0] == null) {
            return false;
        } else {
            ItemStack itemstack = FurnaceRecipes.a().b(this.g[0].b().cp);

            return itemstack == null ? false : (this.g[2] == null ? true : (!this.g[2].a(itemstack) ? false : (this.g[2].a < this.d() && this.g[2].a < this.g[2].e() ? true : this.g[2].a < itemstack.e())));
        }
    }

    public void l() {
        if (this.u()) {
            ItemStack itemstack = FurnaceRecipes.a().b(this.g[0].b().cp);

            if (this.g[2] == null) {
                this.g[2] = itemstack.m();
            } else if (this.g[2].c == itemstack.c) {
                ++this.g[2].a;
            }

            --this.g[0].a;
            if (this.g[0].a <= 0) {
                this.g[0] = null;
            }
        }
    }

    public static int a(ItemStack itemstack) {
        if (itemstack == null) {
            return 0;
        } else {
            int i0 = itemstack.b().cp;
            Item item = itemstack.b();

            if (i0 < 256 && Block.r[i0] != null) {
                Block block = Block.r[i0];

                if (block == Block.bS) {
                    return 150;
                }

                if (block.cO == Material.d) {
                    return 300;
                }
            }

            return item instanceof ItemTool && ((ItemTool) item).g().equals("WOOD") ? 200 : (item instanceof ItemSword && ((ItemSword) item).h().equals("WOOD") ? 200 : (item instanceof ItemHoe && ((ItemHoe) item).g().equals("WOOD") ? 200 : (i0 == Item.E.cp ? 100 : (i0 == Item.n.cp ? 1600 : (i0 == Item.az.cp ? 20000 : (i0 == Block.C.cz ? 100 : (i0 == Item.bp.cp ? 2400 : 0)))))));
        }
    }

    public static boolean b(ItemStack itemstack) {
        return a(itemstack) > 0;
    }

    public boolean a(EntityPlayer entityplayer) {
        return this.k.r(this.l, this.m, this.n) != this ? false : entityplayer.e((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D) <= 64.0D;
    }

    public void f() {}

    public void g() {}

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
        return i1 != 0 || i0 != 1 || itemstack.c == Item.ax.cp;
    }

    // CanaryMod
    public CanaryInventory getInventory() {
        return inventory;
    }

    // CanaryMod: Container<ItemStack>
    @Override
    public ItemStack[] getContents() {
        return g;
    }

    @Override
    public void setContents(ItemStack[] items) {
        System.arraycopy(items, 0, getContents(), 0, items.length < getInventorySize() ? items.length : getInventorySize());
    }

    @Override
    public int getInventorySize() {
        return j_();
    }

    @Override
    public ItemStack getSlot(int index) {
        if (!(index < 0 || index > getInventorySize())) {
            return g[index];
        }
        return null;
    }

    @Override
    public void addItem(int itemId, int amount) {
        this.addItem(new CanaryItem(itemId, amount));
    }

    @Override
    public void addItem(net.canarymod.api.inventory.Item item) {
        if (item == null) {
            return;
        }

        int slot = item.getSlot();
        int size = getInventorySize();

        if (slot < size && slot >= 0) {
            if (item.getAmount() <= 0) {
                removeItem(slot);
            } else if (ItemType.fromId(item.getId()) != null) {
                setSlot(slot, ((CanaryItem) item).getHandle());
            }
        } else if (slot == -1) {
            int newSlot = getEmptySlot();

            if (newSlot != -1) {
                setSlot(newSlot, ((CanaryItem) item).getHandle());
                item.setSlot(newSlot);
            }
        }
    }

    @Override
    public int getEmptySlot() {
        int size = getInventorySize();

        for (int index = 0; size > index; index++) {
            if (getSlot(index) != null) {
                continue;
            }
            return index;
        }

        return -1;
    }

    @Override
    public void setSlot(int index, ItemStack value) {
        if (!(index < 0 || index > getInventorySize())) {
            this.g[index] = value;
        }
    }

    @Override
    public String getInventoryName() {
        return this.b();
    }

    @Override
    public void setInventoryName(String value) {
        this.h = value;
    }

    @Override
    public void clearContents() {
        Arrays.fill(g, null);
    }

    @Override
    public net.canarymod.api.inventory.Item getItem(int id, int amount) {
        for (ItemStack stack : getContents()) {
            if (stack != null && stack.c == id && stack.a == amount) {
                return stack.getCanaryItem();
            }
        }
        return null;
    }

    @Override
    public net.canarymod.api.inventory.Item getItem(int id) {
        for (ItemStack stack : getContents()) {
            if (stack != null && stack.c == id) {
                return stack.getCanaryItem();
            }
        }
        return null;
    }

    @Override
    public net.canarymod.api.inventory.Item removeItem(net.canarymod.api.inventory.Item item) {
        return removeItem(item.getId());
    }

    @Override
    public net.canarymod.api.inventory.Item removeItem(int id) {
        for (int index = 0; index < getInventorySize(); index++) {
            ItemStack toCheck = getSlot(index);
            if (toCheck != null && toCheck.c == id) {
                setSlot(index, null);
                return toCheck.getCanaryItem();
            }
        }
        return null;
    }

    @Override
    public ItemStack decreaseItemStackSize(int itemId, int amount) {
        return this.a(itemId, amount);
    }

    @Override
    public int getInventoryStackLimit() {
        return this.d();
    }

    @Override
    public boolean hasItemStack(ItemStack oItemStack) {
        for (int index = 0; index < getInventorySize(); index++) {
            ItemStack toCheck = getSlot(index);
            if (toCheck != null && toCheck.a(oItemStack)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        for (int index = 0; index < getInventorySize(); index++) {
            ItemStack toCheck = getSlot(index);
            if (toCheck != null && toCheck.c == itemId && toCheck.a == amount) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        for (int index = 0; index < getInventorySize(); index++) {
            ItemStack toCheck = getSlot(index);
            if (toCheck != null && toCheck.c == itemId && toCheck.a >= minAmount && toCheck.a <= maxAmount) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasItem(int itemId) {
        for (int index = 0; index < getInventorySize(); index++) {
            if (getSlot(index) != null && getSlot(index).c == itemId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update() {
        this.k_();
    }
    //
}
