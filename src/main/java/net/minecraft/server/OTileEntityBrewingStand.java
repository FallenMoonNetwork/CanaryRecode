package net.minecraft.server;

import java.util.Arrays;
import java.util.List;

import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItem;
import net.minecraft.server.OItemPotion;
import net.minecraft.server.OItemStack;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.ONBTTagList;
import net.minecraft.server.OPotionHelper;
import net.minecraft.server.OTileEntity;

public class OTileEntityBrewingStand extends OTileEntity implements OIInventory {

    private OItemStack[] a = new OItemStack[4];
    private int b;
    private int c;
    private int d;
    private String name = "container.brewing"; // CanaryMod

    public OTileEntityBrewingStand() {
        super();
     }

     public String e() {
        return name;
     }

     public int c() {
        return this.a.length;
     }

     public void q_() {
        if(this.b > 0) {
           --this.b;
           if(this.b == 0) {
              this.p();
              this.G_();
           } else if(!this.o()) {
              this.b = 0;
              this.G_();
           } else if(this.d != this.a[3].c) {
              this.b = 0;
              this.G_();
           }
        } else if(this.o()) {
           this.b = 400;
           this.d = this.a[3].c;
        }

        int var1 = this.n();
        if(var1 != this.c) {
           this.c = var1;
           this.k.c(this.l, this.m, this.n, var1);
        }

        super.q_();
     }

     public int i() {
        return this.b;
     }

     private boolean o() {
        if(this.a[3] != null && this.a[3].a > 0) {
           OItemStack var1 = this.a[3];
           if(!OItem.d[var1.c].n()) {
              return false;
           } else {
              boolean var2 = false;

              for(int var3 = 0; var3 < 3; ++var3) {
                 if(this.a[var3] != null && this.a[var3].c == OItem.br.bP) {
                    int var4 = this.a[var3].h();
                    int var5 = this.b(var4, var1);
                    if(!OItemPotion.c(var4) && OItemPotion.c(var5)) {
                       var2 = true;
                       break;
                    }

                    List var6 = OItem.br.b(var4);
                    List var7 = OItem.br.b(var5);
                    if((var4 <= 0 || var6 != var7) && (var6 == null || !var6.equals(var7) && var7 != null) && var4 != var5) {
                       var2 = true;
                       break;
                    }
                 }
              }

              return var2;
           }
        } else {
           return false;
        }
     }

     private void p() {
        if(this.o()) {
           OItemStack var1 = this.a[3];

           for(int var2 = 0; var2 < 3; ++var2) {
              if(this.a[var2] != null && this.a[var2].c == OItem.br.bP) {
                 int var3 = this.a[var2].h();
                 int var4 = this.b(var3, var1);
                 List var5 = OItem.br.b(var3);
                 List var6 = OItem.br.b(var4);
                 if((var3 <= 0 || var5 != var6) && (var5 == null || !var5.equals(var6) && var6 != null)) {
                    if(var3 != var4) {
                       this.a[var2].b(var4);
                    }
                 } else if(!OItemPotion.c(var3) && OItemPotion.c(var4)) {
                    this.a[var2].b(var4);
                 }
              }
           }

           if(OItem.d[var1.c].k()) {
              this.a[3] = new OItemStack(OItem.d[var1.c].j());
           } else {
              --this.a[3].a;
              if(this.a[3].a <= 0) {
                 this.a[3] = null;
              }
           }

        }
     }

     private int b(int var1, OItemStack var2) {
        return var2 == null?var1:(OItem.d[var2.c].n()?OPotionHelper.a(var1, OItem.d[var2.c].m()):var1);
     }

     public void a(ONBTTagCompound var1) {
        super.a(var1);
        ONBTTagList var2 = var1.n("Items");
        this.a = new OItemStack[this.c()];

        for(int var3 = 0; var3 < var2.d(); ++var3) {
           ONBTTagCompound var4 = (ONBTTagCompound)var2.a(var3);
           byte var5 = var4.d("Slot");
           if(var5 >= 0 && var5 < this.a.length) {
              this.a[var5] = OItemStack.a(var4);
           }
        }

        this.b = var1.e("BrewTime");
     }

     public void b(ONBTTagCompound var1) {
        super.b(var1);
        var1.a("BrewTime", (short)this.b);
        ONBTTagList var2 = new ONBTTagList();

        for(int var3 = 0; var3 < this.a.length; ++var3) {
           if(this.a[var3] != null) {
              ONBTTagCompound var4 = new ONBTTagCompound();
              var4.a("Slot", (byte)var3);
              this.a[var3].b(var4);
              var2.a((ONBTBase)var4);
           }
        }

        var1.a("Items", (ONBTBase)var2);
     }

     public OItemStack g_(int var1) {
        return var1 >= 0 && var1 < this.a.length?this.a[var1]:null;
     }

     public OItemStack a(int var1, int var2) {
        if(var1 >= 0 && var1 < this.a.length) {
           OItemStack var3 = this.a[var1];
           this.a[var1] = null;
           return var3;
        } else {
           return null;
        }
     }

     public OItemStack b(int var1) {
        if(var1 >= 0 && var1 < this.a.length) {
           OItemStack var2 = this.a[var1];
           this.a[var1] = null;
           return var2;
        } else {
           return null;
        }
     }

     public void a(int var1, OItemStack var2) {
        if(var1 >= 0 && var1 < this.a.length) {
           this.a[var1] = var2;
        }

     }

     public int a() {
        return 1;
     }

     public boolean a(OEntityPlayer var1) {
        return this.k.b(this.l, this.m, this.n) != this?false:var1.e((double)this.l + 0.5D, (double)this.m + 0.5D, (double)this.n + 0.5D) <= 64.0D;
     }

     public void f() {}

     public void g() {}

     public int n() {
        int var1 = 0;

        for(int var2 = 0; var2 < 3; ++var2) {
           if(this.a[var2] != null) {
              var1 |= 1 << var2;
           }
        }

        return var1;
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
                    this.k.getCanaryWorld().dropItem(l, m, n, itemId, amount, 0);
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
                        this.k.getCanaryWorld().dropItem(l, m, n, itemId, amount, 0);
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
