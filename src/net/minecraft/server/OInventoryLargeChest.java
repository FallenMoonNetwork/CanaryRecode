package net.minecraft.server;

import java.util.Arrays;

import net.canarymod.api.inventory.Item;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemStack;

public class OInventoryLargeChest implements OIInventory {

    private String a;
    private OIInventory b;
    private OIInventory c;


    public OInventoryLargeChest(String var1, OIInventory var2, OIInventory var3) {
        super();
        this.a = var1;
        if(var2 == null) {
            var2 = var3;
        }

        if(var3 == null) {
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
        return var1 >= this.b.c()?this.c.g_(var1 - this.b.c()):this.b.g_(var1);
    }

    public OItemStack a(int var1, int var2) {
        return var1 >= this.b.c()?this.c.a(var1 - this.b.c(), var2):this.b.a(var1, var2);
    }

    public OItemStack b(int var1) {
        return var1 >= this.b.c()?this.c.b(var1 - this.b.c()):this.b.b(var1);
    }

    public void a(int var1, OItemStack var2) {
        if(var1 >= this.b.c()) {
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
    
    //TODO 
    //CanaryMod start - Container
    @Override
    public OItemStack[] getContents() {
        return null;
    }

    @Override
    public void setContents(OItemStack[] values) {
        
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
        //Arrays.fill(b, (OItemStack)null);
        
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
     //   if(c[item.getSlot()] != null && c[item.getSlot()].c == item.getId()) {
     //       Item toRet = c[item.getSlot()].getCanaryItem();
     //       c[item.getSlot()] = null;
     //       return toRet;
     //   }
        return null;
    }

    @Override
    public Item removeItem(int id) {
    //    for(int index = 0; index < c.length; index++) {
    //        if(c[index].c == id) {
    //            Item toRet = c[index].getCanaryItem();
    //            c[index] = null;
    //            return toRet;
    //        }
    //    }
        return null;
    }

    @Override
    public boolean hasItemStack(OItemStack oItemStack) {
    //    for (int index = 0; index < this.c.length; ++index) {
    //        if (this.c[index] != null && this.c[index].c(oItemStack)) {
    //            return true;
    //        }
    //    }
        return false;
    }

    @Override
    public boolean hasItem(int itemId) {
    //    for (int var2 = 0; var2 < this.c.length; ++var2) {
    //        if (this.c[var2] != null && this.c[var2].c == itemId) {
    //            return true;
    //        }
    //    }
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
