package net.minecraft.server;

import java.util.Arrays;

import net.canarymod.api.entity.Player;
import net.canarymod.api.inventory.Item;
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
     //CanaryMod end - Container
}
