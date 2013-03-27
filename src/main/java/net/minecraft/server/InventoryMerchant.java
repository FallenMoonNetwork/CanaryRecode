package net.minecraft.server;

import java.util.Arrays;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;

public class InventoryMerchant implements IInventory {

    private final IMerchant a;
    private ItemStack[] b = new ItemStack[3];
    private final EntityPlayer c;
    private MerchantRecipe d;
    private int e;
    private String name = "mob.villager"; // CanaryMod: custom inventory name

    public InventoryMerchant(EntityPlayer entityplayer, IMerchant imerchant) {
        this.c = entityplayer;
        this.a = imerchant;
    }

    public int j_() {
        return this.b.length;
    }

    public ItemStack a(int i0) {
        return this.b[i0];
    }

    public ItemStack a(int i0, int i1) {
        if (this.b[i0] != null) {
            ItemStack itemstack;

            if (i0 == 2) {
                itemstack = this.b[i0];
                this.b[i0] = null;
                return itemstack;
            } else if (this.b[i0].a <= i1) {
                itemstack = this.b[i0];
                this.b[i0] = null;
                if (this.d(i0)) {
                    this.h();
                }

                return itemstack;
            } else {
                itemstack = this.b[i0].a(i1);
                if (this.b[i0].a == 0) {
                    this.b[i0] = null;
                }

                if (this.d(i0)) {
                    this.h();
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }

    private boolean d(int i0) {
        return i0 == 0 || i0 == 1;
    }

    public ItemStack b(int i0) {
        if (this.b[i0] != null) {
            ItemStack itemstack = this.b[i0];

            this.b[i0] = null;
            return itemstack;
        } else {
            return null;
        }
    }

    public void a(int i0, ItemStack itemstack) {
        this.b[i0] = itemstack;
        if (itemstack != null && itemstack.a > this.d()) {
            itemstack.a = this.d();
        }

        if (this.d(i0)) {
            this.h();
        }
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

    public boolean a(EntityPlayer entityplayer) {
        return this.a.m_() == entityplayer;
    }

    public void f() {}

    public void g() {}

    public boolean b(int i0, ItemStack itemstack) {
        return true;
    }

    public void k_() {
        this.h();
    }

    public void h() {
        this.d = null;
        ItemStack itemstack = this.b[0];
        ItemStack itemstack1 = this.b[1];

        if (itemstack == null) {
            itemstack = itemstack1;
            itemstack1 = null;
        }

        if (itemstack == null) {
            this.a(2, (ItemStack) null);
        } else {
            MerchantRecipeList merchantrecipelist = this.a.b(this.c);

            if (merchantrecipelist != null) {
                MerchantRecipe merchantrecipe = merchantrecipelist.a(itemstack, itemstack1, this.e);

                if (merchantrecipe != null && !merchantrecipe.g()) {
                    this.d = merchantrecipe;
                    this.a(2, merchantrecipe.d().m());
                } else if (itemstack1 != null) {
                    merchantrecipe = merchantrecipelist.a(itemstack1, itemstack, this.e);
                    if (merchantrecipe != null && !merchantrecipe.g()) {
                        this.d = merchantrecipe;
                        this.a(2, merchantrecipe.d().m());
                    } else {
                        this.a(2, (ItemStack) null);
                    }
                } else {
                    this.a(2, (ItemStack) null);
                }
            }
        }
    }

    public MerchantRecipe i() {
        return this.d;
    }

    public void c(int i0) {
        this.e = i0;
        this.h();
    }

    // CanaryMod: Container<ItemStack>
    @Override
    public ItemStack[] getContents() {
        return b;
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
            return b[index];
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
            this.b[index] = value;
        }
    }

    @Override
    public String getInventoryName() {
        return this.b();
    }

    @Override
    public void setInventoryName(String value) {
        this.name = value;
    }

    @Override
    public void clearContents() {
        Arrays.fill(b, null);
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
