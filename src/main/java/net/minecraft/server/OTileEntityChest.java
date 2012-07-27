package net.minecraft.server;

import java.util.Arrays;

import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.canarymod.api.world.blocks.CanaryChest;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemStack;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.ONBTTagList;
import net.minecraft.server.OTileEntity;

public class OTileEntityChest extends OTileEntity implements OIInventory {

    private OItemStack[] i = new OItemStack[36];
    public boolean a = false;
    public OTileEntityChest b;
    public OTileEntityChest c;
    public OTileEntityChest d;
    public OTileEntityChest e;
    public float f;
    public float g;
    public int h;
    private int j;
    private String name = "container.chest"; //CanaryMod
    private CanaryChest chest;

    public OTileEntityChest() {
        super();
        this.chest = new CanaryChest(this);
     }

     public int c() {
        return 27;
     }

     public OItemStack g_(int var1) {
        return this.i[var1];
     }

     public OItemStack a(int var1, int var2) {
        if(this.i[var1] != null) {
           OItemStack var3;
           if(this.i[var1].a <= var2) {
              var3 = this.i[var1];
              this.i[var1] = null;
              this.G_();
              return var3;
           } else {
              var3 = this.i[var1].a(var2);
              if(this.i[var1].a == 0) {
                 this.i[var1] = null;
              }

              this.G_();
              return var3;
           }
        } else {
           return null;
        }
     }

     public OItemStack b(int var1) {
        if(this.i[var1] != null) {
           OItemStack var2 = this.i[var1];
           this.i[var1] = null;
           return var2;
        } else {
           return null;
        }
     }

     public void a(int var1, OItemStack var2) {
        this.i[var1] = var2;
        if(var2 != null && var2.a > this.a()) {
           var2.a = this.a();
        }

        this.G_();
     }

     public String e() {
        return "container.chest";
     }

     public void a(ONBTTagCompound var1) {
        super.a(var1);
        ONBTTagList var2 = var1.n("Items");
        this.i = new OItemStack[this.c()];

        for(int var3 = 0; var3 < var2.d(); ++var3) {
           ONBTTagCompound var4 = (ONBTTagCompound)var2.a(var3);
           int var5 = var4.d("Slot") & 255;
           if(var5 >= 0 && var5 < this.i.length) {
              this.i[var5] = OItemStack.a(var4);
           }
        }

     }

     public void b(ONBTTagCompound var1) {
        super.b(var1);
        ONBTTagList var2 = new ONBTTagList();

        for(int var3 = 0; var3 < this.i.length; ++var3) {
           if(this.i[var3] != null) {
              ONBTTagCompound var4 = new ONBTTagCompound();
              var4.a("Slot", (byte)var3);
              this.i[var3].b(var4);
              var2.a((ONBTBase)var4);
           }
        }

        var1.a("Items", (ONBTBase)var2);
     }

     public int a() {
        return 64;
     }

     public boolean a(OEntityPlayer var1) {
        return this.k.b(this.l, this.m, this.n) != this?false:var1.e((double)this.l + 0.5D, (double)this.m + 0.5D, (double)this.n + 0.5D) <= 64.0D;
     }

     public void h() {
        super.h();
        this.a = false;
     }

     public void i() {
        if(!this.a) {
           this.a = true;
           this.b = null;
           this.c = null;
           this.d = null;
           this.e = null;
           if(this.k.a(this.l - 1, this.m, this.n) == OBlock.au.bO) {
              this.d = (OTileEntityChest)this.k.b(this.l - 1, this.m, this.n);
           }

           if(this.k.a(this.l + 1, this.m, this.n) == OBlock.au.bO) {
              this.c = (OTileEntityChest)this.k.b(this.l + 1, this.m, this.n);
           }

           if(this.k.a(this.l, this.m, this.n - 1) == OBlock.au.bO) {
              this.b = (OTileEntityChest)this.k.b(this.l, this.m, this.n - 1);
           }

           if(this.k.a(this.l, this.m, this.n + 1) == OBlock.au.bO) {
              this.e = (OTileEntityChest)this.k.b(this.l, this.m, this.n + 1);
           }

           if(this.b != null) {
              this.b.h();
           }

           if(this.e != null) {
              this.e.h();
           }

           if(this.c != null) {
              this.c.h();
           }

           if(this.d != null) {
              this.d.h();
           }

        }
     }

     public void q_() {
        super.q_();
        this.i();
        if(++this.j % 20 * 4 == 0) {
           this.k.e(this.l, this.m, this.n, 1, this.h);
        }

        this.g = this.f;
        float var1 = 0.1F;
        double var4;
        if(this.h > 0 && this.f == 0.0F && this.b == null && this.d == null) {
           double var2 = (double)this.l + 0.5D;
           var4 = (double)this.n + 0.5D;
           if(this.e != null) {
              var4 += 0.5D;
           }

           if(this.c != null) {
              var2 += 0.5D;
           }

           this.k.a(var2, (double)this.m + 0.5D, var4, "random.chestopen", 0.5F, this.k.r.nextFloat() * 0.1F + 0.9F);
        }

        if(this.h == 0 && this.f > 0.0F || this.h > 0 && this.f < 1.0F) {
           float var6 = this.f;
           if(this.h > 0) {
              this.f += var1;
           } else {
              this.f -= var1;
           }

           if(this.f > 1.0F) {
              this.f = 1.0F;
           }

           float var7 = 0.5F;
           if(this.f < var7 && var6 >= var7 && this.b == null && this.d == null) {
              var4 = (double)this.l + 0.5D;
              double var8 = (double)this.n + 0.5D;
              if(this.e != null) {
                 var8 += 0.5D;
              }

              if(this.c != null) {
                 var4 += 0.5D;
              }

              this.k.a(var4, (double)this.m + 0.5D, var8, "random.chestclosed", 0.5F, this.k.r.nextFloat() * 0.1F + 0.9F);
           }

           if(this.f < 0.0F) {
              this.f = 0.0F;
           }
        }

     }

     public void b(int var1, int var2) {
        if(var1 == 1) {
           this.h = var2;
        }

     }

     public void f() {
        ++this.h;
        this.k.e(this.l, this.m, this.n, 1, this.h);
     }

     public void g() {
        --this.h;
        this.k.e(this.l, this.m, this.n, 1, this.h);
     }

     public void j() {
        this.h();
        this.i();
        super.j();
     }

    //CanaryMod start - Container
    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(this.i, c());
    }

    @Override
    public void setContents(OItemStack[] values) {
        this.i = Arrays.copyOf(values, c());
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
        Arrays.fill(i, (OItemStack)null);
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
        if(i[item.getSlot()] != null && i[item.getSlot()].c == item.getId()) {
            Item toRet = i[item.getSlot()].getCanaryItem();
            i[item.getSlot()] = null;
            return toRet;
        }
        return null;
    }

    @Override
    public Item removeItem(int id) {
        for(int index = 0; index < i.length; index++) {
            if(i[index].c == id) {
                Item toRet = i[index].getCanaryItem();
                i[index] = null;
                return toRet;
            }
        }
        return null;
    }

    @Override
    public boolean hasItemStack(OItemStack oItemStack) {
        for (int index = 0; index < this.i.length; ++index) {
            if (this.i[index] != null && this.i[index].c(oItemStack)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasItem(int itemId) {
        for (int var2 = 0; var2 < this.i.length; ++var2) {
            if (this.i[var2] != null && this.i[var2].c == itemId) {
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
    
    /**
     * CanaryMod get this chest handler
     * @return
     */
    public CanaryChest getChest(){
        return chest;
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
        for (var2 = 0; var2 < this.i.length; ++var2) {
            if (this.i[var2] != null && this.i[var2].c == itemId && this.i[var2].a == amount) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        int var2;
        for (var2 = 0; var2 < this.i.length; ++var2) {
            if (this.i[var2] != null && this.i[var2].c == itemId && (this.i[var2].a >= minAmount || this.i[var2].a <= maxAmount)) {
                return true;
            }
        }
        return false;
    }
    //CanaryMod end - Container
}
