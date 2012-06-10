package net.minecraft.server;

import java.util.Arrays;
import java.util.Random;

import net.canarymod.api.inventory.Item;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemStack;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.ONBTTagList;
import net.minecraft.server.OTileEntity;

public class OTileEntityDispenser extends OTileEntity implements OIInventory {

    private OItemStack[] a = new OItemStack[9];
    private Random b = new Random();
    private String name = "container.dispenser"; //CanaryMod

    public OTileEntityDispenser() {
        super();
    }

    @Override
    public int getInventorySize() {
        return 9;
    }

    @Override
    public OItemStack getStackFromSlot(int var1) {
        return this.a[var1];
    }

    @Override
    public OItemStack decreaseItemStackSize(int var1, int var2) {
        if (this.a[var1] != null) {
            OItemStack var3;
            if (this.a[var1].a <= var2) {
                var3 = this.a[var1];
                this.a[var1] = null;
                this.G_();
                return var3;
            } else {
                var3 = this.a[var1].a(var2);
                if (this.a[var1].a == 0) {
                    this.a[var1] = null;
                }

                this.G_();
                return var3;
            }
        } else {
            return null;
        }
    }

    @Override
    public OItemStack b(int var1) {
        if (this.a[var1] != null) {
            OItemStack var2 = this.a[var1];
            this.a[var1] = null;
            return var2;
        } else {
            return null;
        }
    }

    public OItemStack p_() {
        int var1 = -1;
        int var2 = 1;

        for (int var3 = 0; var3 < this.a.length; ++var3) {
            if (this.a[var3] != null && this.b.nextInt(var2++) == 0) {
                var1 = var3;
            }
        }

        if (var1 >= 0) {
            return this.decreaseItemStackSize(var1, 1);
        } else {
            return null;
        }
    }

    @Override
    public void setItemStackToSlot(int var1, OItemStack var2) {
        this.a[var1] = var2;
        if (var2 != null && var2.a > this.getInventoryStackLimit()) {
            var2.a = this.getInventoryStackLimit();
        }

        this.G_();
    }

    @Override
    public String getInventoryName() {
        return name;
    }

    @Override
    public void a(ONBTTagCompound var1) {
        super.a(var1);
        ONBTTagList var2 = var1.n("Items");
        this.a = new OItemStack[this.getInventorySize()];

        for (int var3 = 0; var3 < var2.d(); ++var3) {
            ONBTTagCompound var4 = (ONBTTagCompound) var2.a(var3);
            int var5 = var4.d("Slot") & 255;
            if (var5 >= 0 && var5 < this.a.length) {
                this.a[var5] = OItemStack.a(var4);
            }
        }

    }

    @Override
    public void b(ONBTTagCompound var1) {
        super.b(var1);
        ONBTTagList var2 = new ONBTTagList();

        for (int var3 = 0; var3 < this.a.length; ++var3) {
            if (this.a[var3] != null) {
                ONBTTagCompound var4 = new ONBTTagCompound();
                var4.a("Slot", (byte) var3);
                this.a[var3].b(var4);
                var2.a(var4);
            }
        }

        var1.a("Items", var2);
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean a(OEntityPlayer var1) {
        return this.k.b(this.l, this.m, this.n) != this ? false : var1.e(this.l + 0.5D, this.m + 0.5D, this.n + 0.5D) <= 64.0D;
    }

    @Override
    public void f() {
    }

    @Override
    public void g() {
    }

    //CanaryMod start container
    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(a, getSize());
    }

    @Override
    public void setContents(OItemStack[] values) {
        a = Arrays.copyOf(values, getSize());
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
        return name;
    }

    @Override
    public void setName(String value) {
        name = value;
    }
    
    @Override
    public void update(){
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
