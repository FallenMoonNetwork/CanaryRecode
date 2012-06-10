package net.minecraft.server;

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
        if (var2 == null) {
            var2 = var3;
        }

        if (var3 == null) {
            var3 = var2;
        }

        this.b = var2;
        this.c = var3;
    }

    @Override
    public int getInventorySize() {
        return this.b.getInventorySize() + this.c.getInventorySize();
    }

    @Override
    public String getInventoryName() {
        return this.a;
    }

    @Override
    public OItemStack getStackFromSlot(int var1) {
        return var1 >= this.b.getInventorySize() ? this.c.getStackFromSlot(var1 - this.b.getInventorySize()) : this.b.getStackFromSlot(var1);
    }

    @Override
    public OItemStack decreaseItemStackSize(int var1, int var2) {
        return var1 >= this.b.getInventorySize() ? this.c.decreaseItemStackSize(var1 - this.b.getInventorySize(), var2) : this.b.decreaseItemStackSize(var1, var2);
    }

    @Override
    public OItemStack b(int var1) {
        return var1 >= this.b.getInventorySize() ? this.c.b(var1 - this.b.getInventorySize()) : this.b.b(var1);
    }

    @Override
    public void setItemStackToSlot(int var1, OItemStack var2) {
        if (var1 >= this.b.getInventorySize()) {
            this.c.setItemStackToSlot(var1 - this.b.getInventorySize(), var2);
        } else {
            this.b.setItemStackToSlot(var1, var2);
        }

    }

    @Override
    public int getInventoryStackLimit() {
        return this.b.getInventoryStackLimit();
    }

    @Override
    public void G_() {
        this.b.G_();
        this.c.G_();
    }

    @Override
    public boolean a(OEntityPlayer var1) {
        return this.b.a(var1) && this.c.a(var1);
    }

    @Override
    public void f() {
        this.b.f();
        this.c.f();
    }

    @Override
    public void g() {
        this.b.g();
        this.c.g();
    }

    //CanaryMod start container
    @Override
    public OItemStack[] getContents() {
        int size = getSize();
        OItemStack[] result = new OItemStack[size];

        for (int i = 0; i < size; i++) {
            result[i] = getSlot(i);
        }
        return result;
    }

    @Override
    public void setContents(OItemStack[] values) {
        int size = getSize();

        for (int i = 0; i < size; i++) {
            setSlot(i, values[i]);
        }
    }

    @Override
    public OItemStack getSlot(int index) {
        return getStackFromSlot(index);
    }

    @Override
    public void setSlot(int index, OItemStack value) {
        setItemStackToSlot(index, value);
    }

    @Override
    public int getSize() {
        return getInventorySize();
    }

    @Override
    public String getName() {
        return a;
    }

    @Override
    public void setName(String value) {
        a = value;
    }

    @Override
    public void update() {
        G_();
    }
    //CanaryMod end container

    @Override
    public void clearContents() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Item getItem(int id, int amount) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item getItem(int id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item removeItem(Item item) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Item removeItem(int id) {
        // TODO Auto-generated method stub
        return null;
    }
}
