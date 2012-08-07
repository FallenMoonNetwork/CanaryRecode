package net.minecraft.server;


import java.util.ArrayList;
import java.util.Collections;

import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemStack;


public class OInventoryLargeChest implements OIInventory {

    private String a;
    public OIInventory b; // CanaryMod private -> public
    public OIInventory c; // CanaryMod private -> public

    public OInventoryLargeChest(String var1, OIInventory var2, OIInventory var3) {
        super();
        this.a = var1;
        if (var2 == null) {
            var2 = var3;
        }

        if (var3 == null) {
            var3 = var2;
        }

        this.b = var2;
        this.c = var3;
    }

    public int c() {
        return this.b.c() + this.c.c();
    }

    public String e() {
        return this.a;
    }

    public OItemStack g_(int var1) {
        return var1 >= this.b.c() ? this.c.g_(var1 - this.b.c()) : this.b.g_(var1);
    }

    public OItemStack a(int var1, int var2) {
        return var1 >= this.b.c() ? this.c.a(var1 - this.b.c(), var2) : this.b.a(var1, var2);
    }

    public OItemStack b(int var1) {
        return var1 >= this.b.c() ? this.c.b(var1 - this.b.c()) : this.b.b(var1);
    }

    public void a(int var1, OItemStack var2) {
        if (var1 >= this.b.c()) {
            this.c.a(var1 - this.b.c(), var2);
        } else {
            this.b.a(var1, var2);
        }

    }

    public int a() {
        return this.b.a();
    }

    public void G_() {
        this.b.G_();
        this.c.G_();
    }

    public boolean a(OEntityPlayer var1) {
        return this.b.a(var1) && this.c.a(var1);
    }

    public void f() {
        this.b.f();
        this.c.f();
    }

    public void g() {
        this.b.g();
        this.c.g();
    }
    
    // CanaryMod start - Container
    @Override
    public OItemStack[] getContents() {
        ArrayList<OItemStack> fullinv = new ArrayList<OItemStack>();

        Collections.addAll(fullinv, b.getContents());
        Collections.addAll(fullinv, c.getContents());
        OItemStack[] toRet = new OItemStack[fullinv.size()];

        toRet = fullinv.toArray(toRet);
        return toRet;
    }

    @Override
    public void setContents(OItemStack[] values) {
        OItemStack[] contentsA = new OItemStack[b.getInventorySize()];
        OItemStack[] contentsB = new OItemStack[c.getInventorySize()];

        System.arraycopy(values, 0, contentsA, 0, contentsA.length);
        System.arraycopy(values, contentsA.length, contentsB, 0, contentsB.length);

        b.setContents(contentsA);
        c.setContents(contentsB);
    }

    @Override
    public OItemStack getSlot(int index) {
        OItemStack stack = this.b(index);

        if (stack != null) {
            return stack;
        }
        return null;
    }

    @Override
    public void setSlot(int index, OItemStack value) {
        this.a(index, value);
    }

    @Override
    public int getInventorySize() {
        return this.c();
    }

    @Override
    public String getInventoryName() {
        return this.a;
    }

    @Override
    public void setInventoryName(String value) {
        this.a = value;
    }

    @Override
    public void update() {
        G_();
    }

    @Override
    public void clearContents() {
        b.clearContents();
        c.clearContents();
    }

    @Override
    public Item getItem(int id, int amount) {
        Item toRet = b.getItem(id, amount);

        if (toRet == null) {
            toRet = c.getItem(id, amount);
        }
        return toRet;
    }

    @Override
    public Item getItem(int id) {
        Item toRet = b.getItem(id);

        if (toRet == null) {
            toRet = c.getItem(id);
        }
        return toRet;
    }

    @Override
    public Item removeItem(Item item) {
        Item toRet = b.removeItem(item);

        if (toRet == null) {
            toRet = c.removeItem(item);
        }
        return toRet;
    }

    @Override
    public Item removeItem(int id) {
        Item toRet = b.removeItem(id);

        if (toRet == null) {
            toRet = c.removeItem(id);
        }
        return toRet;
    }

    @Override
    public boolean hasItemStack(OItemStack oItemStack) {
        boolean toRet = b.hasItemStack(oItemStack);

        if (!toRet) {
            toRet = c.hasItemStack(oItemStack);
        }
        return false;
    }

    @Override
    public boolean hasItem(int itemId) {
        boolean toRet = b.hasItem(itemId);

        if (!toRet) {
            toRet = c.hasItem(itemId);
        }
        return false;
    }

    @Override
    public OItemStack decreaseItemStackSize(int arg0, int arg1) {
        OItemStack stack = a(arg0, arg1);

        if (stack != null) {
            return stack;
        }
        return null;
    }

    @Override
    public int getInventoryStackLimit() {
        return a();
    }

    @Override
    public void addItem(int itemId, int amount) {
        int remaining = amount;

        do {
            if (((itemId >= 256 && itemId <= 258) || (itemId >= 267 && itemId <= 279) || (itemId >= 283 && itemId <= 286) || (itemId >= 298 && itemId <= 317) || (itemId == 261))) {
                int targetSlot = getEmptySlot();
                
                if (targetSlot == -1) {
                    OTileEntity chest = ((OTileEntity) b);

                    chest.k.getCanaryWorld().dropItem(chest.l, chest.m, chest.n, itemId, amount, 0);
                    remaining = 0;
                } else {
                    addItem(new CanaryItem(itemId, 1, targetSlot));
                    remaining--;
                }
            } else {
                if (hasItemStack(itemId, 1, 63)) {
                    Item i = getItem(itemId, 63);
                    
                    if (i != null) {
                        int freeSpace = 64 - i.getAmount();
                        int toAdd = 0;

                        if (remaining > freeSpace) {
                            toAdd = freeSpace;
                            remaining -= freeSpace;
                        } else {
                            toAdd = remaining;
                            remaining = 0;
                        }
                        i.setAmount(i.getAmount() + toAdd);
                        addItem(i);
                    }
                } else {
                    int targetSlot = getEmptySlot();
                    
                    if (targetSlot == -1) {
                        OTileEntity chest = ((OTileEntity) b);

                        chest.k.getCanaryWorld().dropItem(chest.l, chest.m, chest.n, itemId, amount, 0);
                        remaining = 0;
                    } else {
                        if (remaining > 64) {
                            addItem(new CanaryItem(itemId, 64, targetSlot));
                            remaining -= 64;
                        } else {
                            addItem(new CanaryItem(itemId, remaining, targetSlot));
                            remaining = 0;
                        }
                    }
                }
            }
            
        } while (remaining > 0);
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
                setSlot(slot, new OItemStack(item.getId(), item.getAmount(), item.getDamage()));
            }
        } else if (slot == -1) {
            int newSlot = getEmptySlot();

            if (newSlot != -1) {
                setSlot(newSlot, new OItemStack(item.getId(), item.getAmount(), item.getDamage()));
                item.setSlot(newSlot);
            }
        }
    }

    @Override
    public int getEmptySlot() {
        OItemStack[] contents = getContents();

        for (int i = 0; contents.length > i; i++) {
            if (contents[i] != null) {
                continue;
            }
            return i;
        }

        return -1;
    }

    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return b.hasItemStack(itemId, amount) || c.hasItemStack(itemId, amount);
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        return b.hasItemStack(itemId, minAmount, maxAmount) || c.hasItemStack(itemId, minAmount, maxAmount);
    }
    // CanaryMod end - Container
}
