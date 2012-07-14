package net.minecraft.server;

import java.util.Arrays;

import net.canarymod.Logman;
import net.canarymod.api.entity.Player;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.minecraft.server.OContainer;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemStack;

public class OInventoryCrafting implements OIInventory {

    private OItemStack[] a;
    private int b;
    public OContainer c; // CanaryMod private -> public
    private String name = "container.crafting"; //CanaryMod


    public OInventoryCrafting(OContainer var1, int var2, int var3) {
        super();
        int var4 = var2 * var3;
        this.a = new OItemStack[var4];
        this.c = var1;
        this.b = var2;
    }

    public int c() {
        return this.a.length;
    }
    
    public OItemStack g_(int var1) {
        return var1 >= this.c()?null:this.a[var1];
    }

    public OItemStack b(int var1, int var2) {
        if(var1 >= 0 && var1 < this.b) {
            int var3 = var1 + var2 * this.b;
            return this.g_(var3);
        } else {
            return null;
        }
    }

    public String e() {
        return name;
    }

    /**
     * Get stack from slot
     */
    public OItemStack b(int var1) {
        if(this.a[var1] != null) {
            OItemStack var2 = this.a[var1];
            this.a[var1] = null;
            return var2;
        } else {
            return null;
        }
    }


    /**
     * decrease item stack size
     */
    public OItemStack a(int var1, int var2) {
        if(this.a[var1] != null) {
            OItemStack var3;
            if(this.a[var1].a <= var2) {
                var3 = this.a[var1];
                this.a[var1] = null;
                this.c.a((OIInventory)this);
                return var3;
            } else {
                var3 = this.a[var1].a(var2);
                if(this.a[var1].a == 0) {
                    this.a[var1] = null;
                }
                
                this.c.a((OIInventory)this);
                return var3;
            }
        } else {
            return null;
        }
    }
    
    public void a(int var1, OItemStack var2) {
        this.a[var1] = var2;
        this.c.a((OIInventory)this);
    }
    
    public int a() {
        return 64;
    }
    
    public void G_() {}
    
    public boolean a(OEntityPlayer var1) {
        return true;
    }
    
    public void f() {
    }
    
    public void g() {
    }

  //CanaryMod start - Container
    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(this.a, c());
    }

    @Override
    public void setContents(OItemStack[] values) {
        this.a = Arrays.copyOf(values, c());
    }

    @Override
    public OItemStack getSlot(int index) {
//        OItemStack stack = this.b(index);
        OItemStack stack = a[index];
        if (stack != null) {
            return stack;
        }
        return null;
    }

    @Override
    public void setSlot(int index, OItemStack value) {
        this.a(b, value);
    }

    @Override
    public int getInventorySize() {
        return this.c();
    }

    @Override
    public String getInventoryName() {
        return name;
    }

    @Override
    public void setInventoryName(String value) {
        this.name = value;
    }

    @Override
    public void update() {
        G_();
    }

    @Override
    public void clearContents() {
        Arrays.fill(a, (OItemStack)null);
    }

    @Override
    public Item getItem(int id, int amount) {
        for(OItemStack stack : getContents()) {
            if(stack.c == id && stack.a == amount) {
                return stack.getCanaryItem();
            }
        }
        return null;
    }

    @Override
    public Item getItem(int id) {
        for(OItemStack stack : getContents()) {
            if(stack != null && stack.c == id) {
                return stack.getCanaryItem();
            }
        }
        return null;
    }

    @Override
    public Item removeItem(Item item) {
        if(a[item.getSlot()] != null && a[item.getSlot()].c == item.getId()) {
            Item toRet = a[item.getSlot()].getCanaryItem();
            a[item.getSlot()] = null;
            return toRet;
        }
        return null;
    }

    @Override
    public Item removeItem(int id) {
        for(int index = 0; index < a.length; index++) {
            if(a[index].c == id) {
                Item toRet = a[index].getCanaryItem();
                a[index] = null;
                return toRet;
            }
        }
        return null;
    }

    @Override
    public boolean hasItemStack(OItemStack oItemStack) {
        for (int index = 0; index < this.a.length; ++index) {
            if (this.a[index] != null && this.a[index].c(oItemStack)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasItem(int itemId) {
        for (int var2 = 0; var2 < this.a.length; ++var2) {
            if (this.a[var2] != null && this.a[var2].c == itemId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public OItemStack decreaseItemStackSize(int arg0, int arg1) {
        OItemStack stack = a(arg0, arg1);
        if(stack != null) {
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
            // Do not allow stacking of enchantable items,
            // this is to prevent enchantment duping.
            //
            // Could do with a cleanup into a single function, 
            // but this works for now.
            if (((itemId >= 256 && itemId <= 258) || 
                 (itemId >= 267 && itemId <= 279) || 
                 (itemId >= 283 && itemId <= 286) ||
                 (itemId >= 298 && itemId <= 317) ||
                 (itemId == 261))) {
                int targetSlot = getEmptySlot();
                
                if (targetSlot == -1) {
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
        int var2;
        for (var2 = 0; var2 < this.a.length; ++var2) {
            if (this.a[var2] != null && this.a[var2].c == itemId && this.a[var2].a == amount) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        int var2;
        for (var2 = 0; var2 < this.a.length; ++var2) {
            if (this.a[var2] != null && this.a[var2].c == itemId && (this.a[var2].a >= minAmount || this.a[var2].a <= maxAmount)) {
                return true;
            }
        }
        return false;
    }
    //CanaryMod end - Container
}
