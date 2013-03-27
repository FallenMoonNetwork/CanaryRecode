package net.minecraft.server;

import java.util.Arrays;
import java.util.List;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;

public class InventoryBasic implements IInventory {

    private String a;
    private int b;
    private ItemStack[] c;
    private List d;
    private boolean e;

    public InventoryBasic(String s0, boolean flag0, int i0) {
        this.a = s0;
        this.e = flag0;
        this.b = i0;
        this.c = new ItemStack[i0];
    }

    public ItemStack a(int i0) {
        return this.c[i0];
    }

    public ItemStack a(int i0, int i1) {
        if (this.c[i0] != null) {
            ItemStack itemstack;

            if (this.c[i0].a <= i1) {
                itemstack = this.c[i0];
                this.c[i0] = null;
                this.k_();
                return itemstack;
            } else {
                itemstack = this.c[i0].a(i1);
                if (this.c[i0].a == 0) {
                    this.c[i0] = null;
                }

                this.k_();
                return itemstack;
            }
        } else {
            return null;
        }
    }

    public ItemStack b(int i0) {
        if (this.c[i0] != null) {
            ItemStack itemstack = this.c[i0];

            this.c[i0] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void a(int i0, ItemStack itemstack) {
        this.c[i0] = itemstack;
        if (itemstack != null && itemstack.a > this.d()) {
            itemstack.a = this.d();
        }

        this.k_();
    }

    public int j_() {
        return this.b;
    }

    public String b() {
        return this.a;
    }

    public boolean c() {
        return this.e;
    }

    public int d() {
        return 64;
    }

    public void k_() {
        if (this.d != null) {
            for (int i0 = 0; i0 < this.d.size(); ++i0) {
                ((IInvBasic) this.d.get(i0)).a(this);
            }
        }
    }

    public boolean a(EntityPlayer entityplayer) {
        return true;
    }

    public void f() {}

    public void g() {}

    public boolean b(int i0, ItemStack itemstack) {
        return true;
    }

    // CanaryMod: Container<ItemStack>
    @Override
    public ItemStack[] getContents() {
        return c;
    }

    @Override
    public void setContents(ItemStack[] items) {
        System.arraycopy(items, 0, a, 0, items.length < getInventorySize() ? items.length : getInventorySize());
    }

    @Override
    public int getInventorySize() {
        return j_();
    }

    @Override
    public ItemStack getSlot(int index) {
        if (!(index < 0 || index > getInventorySize())) {
            return c[index];
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
            this.c[index] = value;
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
        Arrays.fill(c, null);
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
