package net.minecraft.server;

import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;

public class InventoryLargeChest implements IInventory {

    private String a;
    public IInventory b; // CanaryMod: private => public
    public IInventory c; // CanaryMod: private => public

    public InventoryLargeChest(String s0, IInventory iinventory, IInventory iinventory1) {
        this.a = s0;
        if (iinventory == null) {
            iinventory = iinventory1;
        }

        if (iinventory1 == null) {
            iinventory1 = iinventory;
        }

        this.b = iinventory;
        this.c = iinventory1;
    }

    public int j_() {
        return this.b.j_() + this.c.j_();
    }

    public boolean a(IInventory iinventory) {
        return this.b == iinventory || this.c == iinventory;
    }

    public String b() {
        return this.b.c() ? this.b.b() : (this.c.c() ? this.c.b() : this.a);
    }

    public boolean c() {
        return this.b.c() || this.c.c();
    }

    public ItemStack a(int i0) {
        return i0 >= this.b.j_() ? this.c.a(i0 - this.b.j_()) : this.b.a(i0);
    }

    public ItemStack a(int i0, int i1) {
        return i0 >= this.b.j_() ? this.c.a(i0 - this.b.j_(), i1) : this.b.a(i0, i1);
    }

    public ItemStack b(int i0) {
        return i0 >= this.b.j_() ? this.c.b(i0 - this.b.j_()) : this.b.b(i0);
    }

    public void a(int i0, ItemStack itemstack) {
        if (i0 >= this.b.j_()) {
            this.c.a(i0 - this.b.j_(), itemstack);
        } else {
            this.b.a(i0, itemstack);
        }
    }

    public int d() {
        return this.b.d();
    }

    public void k_() {
        this.b.k_();
        this.c.k_();
    }

    public boolean a(EntityPlayer entityplayer) {
        return this.b.a(entityplayer) && this.c.a(entityplayer);
    }

    public void f() {
        this.b.f();
        this.c.f();
    }

    public void g() {
        this.b.g();
        this.c.g();
    }

    public boolean b(int i0, ItemStack itemstack) {
        return true;
    }

    // CanaryMod: Container<ItemStack>
    @Override
    public ItemStack[] getContents() {
        ItemStack[] combined = new ItemStack[getInventorySize()];
        System.arraycopy(b.getContents(), 0, combined, 0, b.getInventorySize());
        System.arraycopy(c.getContents(), 0, combined, b.getInventorySize(), c.getInventorySize());
        // !Magic
        return combined;
    }

    @Override
    public void setContents(ItemStack[] items) {
        System.arraycopy(items, 0, b.getContents(), 0, items.length < b.getInventorySize() ? items.length : b.getInventorySize());
        if (items.length > b.getInventorySize()) {
            System.arraycopy(items, b.getInventorySize(), c.getContents(), 0, items.length - b.getInventorySize() < c.getInventorySize() ? items.length : c.getInventorySize());
        }
    }

    @Override
    public int getInventorySize() {
        return j_();
    }

    @Override
    public ItemStack getSlot(int index) {
        if (!(index < 0 || index > getInventorySize())) {
            if (index < b.getInventorySize()) {
                return b.getSlot(index);
            } else {
                return c.getSlot(index - b.getInventorySize());
            }
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
            if (index < b.getInventorySize()) {
                b.setSlot(index, value);
            } else {
                c.setSlot(index - b.getInventorySize(), value);
            }
        }
    }

    @Override
    public String getInventoryName() {
        return this.b();
    }

    @Override
    public void setInventoryName(String value) {
        this.a = value;
    }

    @Override
    public void clearContents() {
        b.clearContents();
        c.clearContents();
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
