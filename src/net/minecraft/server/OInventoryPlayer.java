package net.minecraft.server;

import net.canarymod.api.inventory.Item;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemArmor;
import net.minecraft.server.OItemStack;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.ONBTTagList;

public class OInventoryPlayer implements OIInventory {

    public OItemStack[] backpack = new OItemStack[36];
    public OItemStack[] armorySlots = new OItemStack[4];
    public int c = 0;
    public OEntityPlayer d;
    private OItemStack f;
    public boolean e = false;
    private String name = "container.inventory"; //CanaryMod
    
    public OInventoryPlayer(OEntityPlayer var1) {
        super();
        this.d = var1;
    }

    public OItemStack getItemInHand() {
        return this.c < 9 && this.c >= 0 ? this.backpack[this.c] : null;
    }

    public static int h() {
        return 9; 
    }

    private int f(int var1) {
        for (int var2 = 0; var2 < this.backpack.length; ++var2) {
            if (this.backpack[var2] != null && this.backpack[var2].c == var1) {
                return var2;
            }
        }

        return -1;
    }

    private int d(OItemStack var1) {
        for (int var2 = 0; var2 < this.backpack.length; ++var2) {
            if (this.backpack[var2] != null && this.backpack[var2].c == var1.c && this.backpack[var2].c() && this.backpack[var2].a < this.backpack[var2].b() && this.backpack[var2].a < this.getInventoryStackLimit() && (!this.backpack[var2].e() || this.backpack[var2].h() == var1.h()) && OItemStack.a(this.backpack[var2], var1)) {
                return var2;
            }
        }

        return -1;
    }

    private int m() {
        for (int var1 = 0; var1 < this.backpack.length; ++var1) {
            if (this.backpack[var1] == null) {
                return var1;
            }
        }

        return -1;
    }

    private int e(OItemStack var1) {
        int var2 = var1.c;
        int var3 = var1.a;
        int var4;
        if (var1.b() == 1) {
            var4 = this.m();
            if (var4 < 0) {
                return var3;
            } else {
                if (this.backpack[var4] == null) {
                    this.backpack[var4] = OItemStack.b(var1);
                }

                return 0;
            }
        } else {
            var4 = this.d(var1);
            if (var4 < 0) {
                var4 = this.m();
            }

            if (var4 < 0) {
                return var3;
            } else {
                if (this.backpack[var4] == null) {
                    this.backpack[var4] = new OItemStack(var2, 0, var1.h());
                    if (var1.n()) {
                        this.backpack[var4].d((ONBTTagCompound) var1.o().b());
                    }
                }

                int var5 = var3;
                if (var3 > this.backpack[var4].b() - this.backpack[var4].a) {
                    var5 = this.backpack[var4].b() - this.backpack[var4].a;
                }

                if (var5 > this.getInventoryStackLimit() - this.backpack[var4].a) {
                    var5 = this.getInventoryStackLimit() - this.backpack[var4].a;
                }

                if (var5 == 0) {
                    return var3;
                } else {
                    var3 -= var5;
                    this.backpack[var4].a += var5;
                    this.backpack[var4].b = 5;
                    return var3;
                }
            }
        }
    }

    public void i() {
        for (int var1 = 0; var1 < this.backpack.length; ++var1) {
            if (this.backpack[var1] != null) {
                this.backpack[var1].a(this.d.bi, this.d, var1, this.c == var1);
            }
        }

    }

    public boolean c(int var1) {
        int var2 = this.f(var1);
        if (var2 < 0) {
            return false;
        } else {
            if (--this.backpack[var2].a <= 0) {
                this.backpack[var2] = null;
            }

            return true;
        }
    }

    public boolean hasItem(int var1) {
        int var2 = this.f(var1);
        return var2 >= 0;
    }

    public boolean addItemToBackPack(OItemStack var1) {
        int var2;
        if (var1.f()) {
            var2 = this.m();
            if (var2 >= 0) {
                this.backpack[var2] = OItemStack.b(var1);
                this.backpack[var2].b = 5;
                var1.a = 0;
                return true;
            } else if (this.d.L.d) {
                var1.a = 0;
                return true;
            } else {
                return false;
            }
        } else {
            do {
                var2 = var1.a;
                var1.a = this.e(var1);
            } while (var1.a > 0 && var1.a < var2);

            if (var1.a == var2 && this.d.L.d) {
                var1.a = 0;
                return true;
            } else {
                return var1.a < var2;
            }
        }
    }

    /**
     * decrease Item stack size. 
     * @param var1 slot id
     * @param var2 amount to remove
     */
    @Override
    public OItemStack decreaseItemStackSize(int var1, int var2) {
        OItemStack[] var3 = this.backpack;
        if (var1 >= this.backpack.length) {
            var3 = this.armorySlots;
            var1 -= this.backpack.length;
        }

        if (var3[var1] != null) {
            OItemStack var4;
            if (var3[var1].a <= var2) {
                var4 = var3[var1];
                var3[var1] = null;
                return var4;
            } else {
                var4 = var3[var1].a(var2);
                if (var3[var1].a == 0) {
                    var3[var1] = null;
                }

                return var4;
            }
        } else {
            return null;
        }
    }

    @Override
    public OItemStack b(int var1) {
        OItemStack[] var2 = this.backpack;
        if (var1 >= this.backpack.length) {
            var2 = this.armorySlots;
            var1 -= this.backpack.length;
        }

        if (var2[var1] != null) {
            OItemStack var3 = var2[var1];
            var2[var1] = null;
            return var3;
        } else {
            return null;
        }
    }

    @Override
    public void setItemStackToSlot(int var1, OItemStack var2) {
        OItemStack[] var3 = this.backpack;
        if (var1 >= var3.length) {
            var1 -= var3.length;
            var3 = this.armorySlots;
        }

        var3[var1] = var2;
    }

    public float getDamageVsBlock(OBlock var1) {
        float var2 = 1.0F;
        if (this.backpack[this.c] != null) {
            var2 *= this.backpack[this.c].a(var1);
        }

        return var2;
    }

    public ONBTTagList a(ONBTTagList var1) {
        int var2;
        ONBTTagCompound var3;
        for (var2 = 0; var2 < this.backpack.length; ++var2) {
            if (this.backpack[var2] != null) {
                var3 = new ONBTTagCompound();
                var3.a("Slot", (byte) var2);
                this.backpack[var2].b(var3);
                var1.a(var3);
            }
        }

        for (var2 = 0; var2 < this.armorySlots.length; ++var2) {
            if (this.armorySlots[var2] != null) {
                var3 = new ONBTTagCompound();
                var3.a("Slot", (byte) (var2 + 100));
                this.armorySlots[var2].b(var3);
                var1.a(var3);
            }
        }

        return var1;
    }

    public void b(ONBTTagList var1) {
        this.backpack = new OItemStack[36];
        this.armorySlots = new OItemStack[4];

        for (int var2 = 0; var2 < var1.d(); ++var2) {
            ONBTTagCompound var3 = (ONBTTagCompound) var1.a(var2);
            int var4 = var3.d("Slot") & 255;
            OItemStack var5 = OItemStack.a(var3);
            if (var5 != null) {
                if (var4 >= 0 && var4 < this.backpack.length) {
                    this.backpack[var4] = var5;
                }

                if (var4 >= 100 && var4 < this.armorySlots.length + 100) {
                    this.armorySlots[var4 - 100] = var5;
                }
            }
        }

    }

    @Override
    public int getInventorySize() {
        return this.backpack.length + 4;
    }

    @Override
    public OItemStack getStackFromSlot(int var1) {
        OItemStack[] var2 = this.backpack;
        if (var1 >= var2.length) {
            var1 -= var2.length;
            var2 = this.armorySlots;
        }

        return var2[var1];
    }

    @Override
    public String getInventoryName() {
        return name;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    public int getDamageVsEntity(OEntity var1) {
        OItemStack var2 = this.getStackFromSlot(this.c);
        return var2 != null ? var2.a(var1) : 1;
    }

    public boolean canHarvestBlock(OBlock var1) {
        if (var1.cd.k()) {
            return true;
        } else {
            OItemStack var2 = this.getStackFromSlot(this.c);
            return var2 != null ? var2.b(var1) : false;
        }
    }

    public int j() {
        int var1 = 0;

        for (int var2 = 0; var2 < this.armorySlots.length; ++var2) {
            if (this.armorySlots[var2] != null && this.armorySlots[var2].a() instanceof OItemArmor) {
                int var3 = ((OItemArmor) this.armorySlots[var2].a()).b;
                var1 += var3;
            }
        }

        return var1;
    }

    public void e(int var1) {
        var1 /= 4;
        if (var1 < 1) {
            var1 = 1;
        }

        for (int var2 = 0; var2 < this.armorySlots.length; ++var2) {
            if (this.armorySlots[var2] != null && this.armorySlots[var2].a() instanceof OItemArmor) {
                this.armorySlots[var2].a(var1, this.d);
                if (this.armorySlots[var2].a == 0) {
                    this.armorySlots[var2].a(this.d);
                    this.armorySlots[var2] = null;
                }
            }
        }

    }

    public void dropAllItems() {
        int var1;
        for (var1 = 0; var1 < this.backpack.length; ++var1) {
            if (this.backpack[var1] != null) {
                this.d.a(this.backpack[var1], true);
                this.backpack[var1] = null;
            }
        }

        for (var1 = 0; var1 < this.armorySlots.length; ++var1) {
            if (this.armorySlots[var1] != null) {
                this.d.a(this.armorySlots[var1], true);
                this.armorySlots[var1] = null;
            }
        }

    }

    @Override
    public void G_() {
        this.e = true;
    }

    public void setItemStack(OItemStack var1) {
        this.f = var1;
        this.d.a(var1);
    }

    public OItemStack getItemStack() {
        return this.f;
    }

    @Override
    public boolean a(OEntityPlayer var1) {
        return this.d.bE ? false : var1.j(this.d) <= 64.0D;
    }

    public boolean hasItemStack(OItemStack var1) {
        int var2;
        for (var2 = 0; var2 < this.armorySlots.length; ++var2) {
            if (this.armorySlots[var2] != null && this.armorySlots[var2].c(var1)) {
                return true;
            }
        }

        for (var2 = 0; var2 < this.backpack.length; ++var2) {
            if (this.backpack[var2] != null && this.backpack[var2].c(var1)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void f() {
    }

    @Override
    public void g() {
    }

    public void copyInventory(OInventoryPlayer var1) {
        int var2;
        for (var2 = 0; var2 < this.backpack.length; ++var2) {
            this.backpack[var2] = OItemStack.b(var1.backpack[var2]);
        }

        for (var2 = 0; var2 < this.armorySlots.length; ++var2) {
            this.armorySlots[var2] = OItemStack.b(var1.armorySlots[var2]);
        }

    }

    //CanaryMod Container
    @Override
    public OItemStack[] getContents() {
        return backpack;
    }
   
    @Override
    public void setContents(OItemStack[] values) {
        backpack = values;
    }
    
    @Override
    public OItemStack getSlot(int index) {
        return getStackFromSlot(index);
    }

    @Override
    public Item removeItem(int id) {
        for(int index = 0; index < backpack.length; index++) {
            if(backpack[index].c == id) {
                Item toRet = backpack[index].getCanaryItem();
                backpack[index] = null;
                return toRet;
            }
        }
        return null;
    }
    
    @Override
    public Item removeItem(Item item) {
        if(backpack[item.getSlot()] != null && backpack[item.getSlot()].c == item.getId()) {
            Item toRet = backpack[item.getSlot()].getCanaryItem();
            backpack[item.getSlot()] = null;
            return toRet;
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
    public Item getItem(int id, int amount) {
        for(OItemStack stack : getContents()) {
            if(stack.c == id && stack.a == amount) {
                return stack.getCanaryItem();
            }
        }
        return null;
    }
    
    @Override
    public void clearContents() {
        for(int i = 0; i < backpack.length; i++) {
            backpack[i] = null;
        }
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
}
