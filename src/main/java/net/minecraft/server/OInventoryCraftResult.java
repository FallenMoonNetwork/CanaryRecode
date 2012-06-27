package net.minecraft.server;

import java.util.Arrays;

import net.canarymod.api.entity.Player;
import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemStack;

public class OInventoryCraftResult implements OIInventory {

    private OItemStack[] a = new OItemStack[1];
    private String name = "Result"; //CanaryMod
    
    public OInventoryCraftResult() {
        super();
    }

    public int c() {
        return 1;
    }

    public OItemStack g_(int var1) {
        return this.a[var1];
    }

    public String e() {
        return name;
    }

    public OItemStack a(int var1, int var2) {
        if(this.a[var1] != null) {
            OItemStack var3 = this.a[var1];
            this.a[var1] = null;
            return var3;
        } else {
            return null;
        }
    }

    public OItemStack b(int var1) {
        if(this.a[var1] != null) {
            OItemStack var2 = this.a[var1];
            this.a[var1] = null;
            return var2;
        } else {
            return null;
        }
    }
    
    public void a(int var1, OItemStack var2){
        this.a[var1] = var2;
    }

    public int a() {
        return 64;
    }

     public void G_() {}

     public boolean a(OEntityPlayer var1) {
         return true;
     }

     public void f() {}

     public void g() {}

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
         return this.b(index);
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
             if(stack.c == id) {
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
         return a(arg0, arg1);
     }

     @Override
     public int getInventoryStackLimit() {
         return a();
     }
     @Override
     public void addItem(int itemId, int amount) {
         int remaining = amount;

         do {
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
         int size = getInventorySize();

         for (int i = 0; size > i; i++) {
             if (getSlot(i) != null) {
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
