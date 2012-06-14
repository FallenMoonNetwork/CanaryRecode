package net.minecraft.server;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import net.canarymod.api.inventory.Item;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemStack;

public class OInventoryLargeChest implements OIInventory {

    private String a;
    public OIInventory b; //CanaryMod private -> public
    public OIInventory c; //CanaryMod private -> public


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
    
    //CanaryMod start - Container
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
        b.clearContents();
        c.clearContents();
    }

    @Override
    public Item getItem(int id, int amount) {
        Item toRet = b.getItem(id, amount);
        if(toRet == null){
            toRet = c.getItem(id, amount);
        }
        return toRet;
    }

    @Override
    public Item getItem(int id) {
        Item toRet = b.getItem(id);
        if(toRet == null){
            toRet = c.getItem(id);
        }
        return toRet;
    }

    @Override
    public Item removeItem(Item item) {
        Item toRet = b.removeItem(item);
        if(toRet == null){
            toRet = c.removeItem(item);
        }
        return toRet;
    }

    @Override
    public Item removeItem(int id) {
        Item toRet = b.removeItem(id);
        if(toRet == null){
            toRet = c.removeItem(id);
        }
        return toRet;
    }

    @Override
    public boolean hasItemStack(OItemStack oItemStack) {
        boolean toRet = b.hasItemStack(oItemStack);
        if(!toRet){
            toRet = c.hasItemStack(oItemStack);
        }
        return false;
    }

    @Override
    public boolean hasItem(int itemId) {
        boolean toRet = b.hasItem(itemId);
        if(!toRet){
            toRet = c.hasItem(itemId);
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
