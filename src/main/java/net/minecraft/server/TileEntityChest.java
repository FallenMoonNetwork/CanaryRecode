package net.minecraft.server;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.canarymod.api.inventory.CanaryInventory;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;

public class TileEntityChest extends TileEntity implements IInventory {

    private ItemStack[] i = new ItemStack[36];
    public boolean a = false;
    public TileEntityChest b;
    public TileEntityChest c;
    public TileEntityChest d;
    public TileEntityChest e;
    public float f;
    public float g;
    public int h;
    private int j;
    private int r = -1;
    private String s;
    private CanaryInventory inventory; // CanaryMod inventory instance

    public TileEntityChest() {
        this.inventory = new CanaryInventory(this); // CanaryMod: create once, use forever
    }

    public int j_() {
        return 27;
    }

    public ItemStack a(int i0) {
        return this.i[i0];
    }

    public ItemStack a(int i0, int i1) {
        if (this.i[i0] != null) {
            ItemStack itemstack;

            if (this.i[i0].a <= i1) {
                itemstack = this.i[i0];
                this.i[i0] = null;
                this.k_();
                return itemstack;
            } else {
                itemstack = this.i[i0].a(i1);
                if (this.i[i0].a == 0) {
                    this.i[i0] = null;
                }

                this.k_();
                return itemstack;
            }
        } else {
            return null;
        }
    }

    public ItemStack b(int i0) {
        if (this.i[i0] != null) {
            ItemStack itemstack = this.i[i0];

            this.i[i0] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void a(int i0, ItemStack itemstack) {
        this.i[i0] = itemstack;
        if (itemstack != null && itemstack.a > this.d()) {
            itemstack.a = this.d();
        }

        this.k_();
    }

    public String b() {
        return this.c() ? this.s : "container.chest";
    }

    public boolean c() {
        return this.s != null && this.s.length() > 0;
    }

    public void a(String s0) {
        this.s = s0;
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.m("Items");

        this.i = new ItemStack[this.j_()];
        if (nbttagcompound.b("CustomName")) {
            this.s = nbttagcompound.i("CustomName");
        }

        for (int i0 = 0; i0 < nbttaglist.c(); ++i0) {
            NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttaglist.b(i0);
            int i1 = nbttagcompound1.c("Slot") & 255;

            if (i1 >= 0 && i1 < this.i.length) {
                this.i[i1] = ItemStack.a(nbttagcompound1);
            }
        }
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i0 = 0; i0 < this.i.length; ++i0) {
            if (this.i[i0] != null) {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();

                nbttagcompound1.a("Slot", (byte) i0);
                this.i[i0].b(nbttagcompound1);
                nbttaglist.a((NBTBase) nbttagcompound1);
            }
        }

        nbttagcompound.a("Items", (NBTBase) nbttaglist);
        if (this.c()) {
            nbttagcompound.a("CustomName", this.s);
        }
    }

    public int d() {
        return 64;
    }

    public boolean a(EntityPlayer entityplayer) {
        return this.k.r(this.l, this.m, this.n) != this ? false : entityplayer.e((double) this.l + 0.5D, (double) this.m + 0.5D, (double) this.n + 0.5D) <= 64.0D;
    }

    public void i() {
        super.i();
        this.a = false;
    }

    private void a(TileEntityChest tileentitychest, int i0) {
        if (tileentitychest.r()) {
            this.a = false;
        } else if (this.a) {
            switch (i0) {
                case 0:
                    if (this.e != tileentitychest) {
                        this.a = false;
                    }
                    break;

                case 1:
                    if (this.d != tileentitychest) {
                        this.a = false;
                    }
                    break;

                case 2:
                    if (this.b != tileentitychest) {
                        this.a = false;
                    }
                    break;

                case 3:
                    if (this.c != tileentitychest) {
                        this.a = false;
                    }
            }
        }
    }

    public void j() {
        if (!this.a) {
            this.a = true;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            if (this.a(this.l - 1, this.m, this.n)) {
                this.d = (TileEntityChest) this.k.r(this.l - 1, this.m, this.n);
            }

            if (this.a(this.l + 1, this.m, this.n)) {
                this.c = (TileEntityChest) this.k.r(this.l + 1, this.m, this.n);
            }

            if (this.a(this.l, this.m, this.n - 1)) {
                this.b = (TileEntityChest) this.k.r(this.l, this.m, this.n - 1);
            }

            if (this.a(this.l, this.m, this.n + 1)) {
                this.e = (TileEntityChest) this.k.r(this.l, this.m, this.n + 1);
            }

            if (this.b != null) {
                this.b.a(this, 0);
            }

            if (this.e != null) {
                this.e.a(this, 2);
            }

            if (this.c != null) {
                this.c.a(this, 1);
            }

            if (this.d != null) {
                this.d.a(this, 3);
            }
        }
    }

    private boolean a(int i0, int i1, int i2) {
        Block block = Block.r[this.k.a(i0, i1, i2)];

        return block != null && block instanceof BlockChest ? ((BlockChest) block).a == this.l() : false;
    }

    public void h() {
        super.h();
        this.j();
        ++this.j;
        float f0;

        if (!this.k.I && this.h != 0 && (this.j + this.l + this.m + this.n) % 200 == 0) {
            this.h = 0;
            f0 = 5.0F;
            List list = this.k.a(EntityPlayer.class, AxisAlignedBB.a().a((double) ((float) this.l - f0), (double) ((float) this.m - f0), (double) ((float) this.n - f0), (double) ((float) (this.l + 1) + f0), (double) ((float) (this.m + 1) + f0), (double) ((float) (this.n + 1) + f0)));
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                EntityPlayer entityplayer = (EntityPlayer) iterator.next();

                if (entityplayer.bM instanceof ContainerChest) {
                    IInventory iinventory = ((ContainerChest) entityplayer.bM).e();

                    if (iinventory == this || iinventory instanceof InventoryLargeChest && ((InventoryLargeChest) iinventory).a((IInventory) this)) {
                        ++this.h;
                    }
                }
            }
        }

        this.g = this.f;
        f0 = 0.1F;
        double d0;

        if (this.h > 0 && this.f == 0.0F && this.b == null && this.d == null) {
            double d1 = (double) this.l + 0.5D;

            d0 = (double) this.n + 0.5D;
            if (this.e != null) {
                d0 += 0.5D;
            }

            if (this.c != null) {
                d1 += 0.5D;
            }

            this.k.a(d1, (double) this.m + 0.5D, d0, "random.chestopen", 0.5F, this.k.s.nextFloat() * 0.1F + 0.9F);
        }

        if (this.h == 0 && this.f > 0.0F || this.h > 0 && this.f < 1.0F) {
            float f1 = this.f;

            if (this.h > 0) {
                this.f += f0;
            } else {
                this.f -= f0;
            }

            if (this.f > 1.0F) {
                this.f = 1.0F;
            }

            float f2 = 0.5F;

            if (this.f < f2 && f1 >= f2 && this.b == null && this.d == null) {
                d0 = (double) this.l + 0.5D;
                double d2 = (double) this.n + 0.5D;

                if (this.e != null) {
                    d2 += 0.5D;
                }

                if (this.c != null) {
                    d0 += 0.5D;
                }

                this.k.a(d0, (double) this.m + 0.5D, d2, "random.chestclosed", 0.5F, this.k.s.nextFloat() * 0.1F + 0.9F);
            }

            if (this.f < 0.0F) {
                this.f = 0.0F;
            }
        }
    }

    public boolean b(int i0, int i1) {
        if (i0 == 1) {
            this.h = i1;
            return true;
        } else {
            return super.b(i0, i1);
        }
    }

    public void f() {
        if (this.h < 0) {
            this.h = 0;
        }

        ++this.h;
        this.k.d(this.l, this.m, this.n, this.q().cz, 1, this.h);
        this.k.f(this.l, this.m, this.n, this.q().cz);
        this.k.f(this.l, this.m - 1, this.n, this.q().cz);
    }

    public void g() {
        if (this.q() != null && this.q() instanceof BlockChest) {
            --this.h;
            this.k.d(this.l, this.m, this.n, this.q().cz, 1, this.h);
            this.k.f(this.l, this.m, this.n, this.q().cz);
            this.k.f(this.l, this.m - 1, this.n, this.q().cz);
        }
    }

    public boolean b(int i0, ItemStack itemstack) {
        return true;
    }

    public void w_() {
        super.w_();
        this.i();
        this.j();
    }

    public int l() {
        if (this.r == -1) {
            if (this.k == null || !(this.q() instanceof BlockChest)) {
                return 0;
            }

            this.r = ((BlockChest) this.q()).a;
        }

        return this.r;
    }

    // CanaryMod
    public CanaryInventory getInventory() {
        return inventory;
    }

    // CanaryMod: Container<ItemStack>
    @Override
    public ItemStack[] getContents() {
        return i;
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
            return i[index];
        }
        return null;
    }

    @Override
    public void addItem(int itemId, int amount) {
        this.addItem(new CanaryItem(itemId, amount));
    }

    @Override
    public void addItem(Item item) {
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
            this.i[index] = value;
        }
    }

    @Override
    public String getInventoryName() {
        return this.b();
    }

    @Override
    public void setInventoryName(String value) {
        this.s = value;
    }

    @Override
    public void clearContents() {
        Arrays.fill(i, null);
    }

    @Override
    public Item getItem(int id, int amount) {
        for (ItemStack stack : getContents()) {
            if (stack != null && stack.c == id && stack.a == amount) {
                return stack.getCanaryItem();
            }
        }
        return null;
    }

    @Override
    public Item getItem(int id) {
        for (ItemStack stack : getContents()) {
            if (stack != null && stack.c == id) {
                return stack.getCanaryItem();
            }
        }
        return null;
    }

    @Override
    public Item removeItem(Item item) {
        return removeItem(item.getId());
    }

    @Override
    public Item removeItem(int id) {
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
