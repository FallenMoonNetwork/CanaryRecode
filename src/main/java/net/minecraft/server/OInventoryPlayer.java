package net.minecraft.server;

import java.util.Arrays;

import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.CanaryPlayerInventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.minecraft.server.OBlock;
import net.minecraft.server.OEntity;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemArmor;
import net.minecraft.server.OItemStack;
import net.minecraft.server.ONBTBase;
import net.minecraft.server.ONBTTagCompound;
import net.minecraft.server.ONBTTagList;

public class OInventoryPlayer implements OIInventory {

   public OItemStack[] a = new OItemStack[36];
   public OItemStack[] b = new OItemStack[4];
   public int c = 0;
   public OEntityPlayer d;
   private OItemStack f;
   public boolean e = false;
   private String name = "container.inventory"; //CanaryMod
   private CanaryPlayerInventory inventory;


    public OInventoryPlayer(OEntityPlayer var1) {
        super();
        this.d = var1;
        inventory = new CanaryPlayerInventory(this);
    }

    public CanaryPlayerInventory getInventory() {
        return inventory;
    }

public OItemStack d() {
      return this.c < 9 && this.c >= 0 ? this.a[this.c] : null;
   }

   public static int h() {
      return 9;
   }

   private int f(int var1) {
      for(int var2 = 0; var2 < this.a.length; ++var2) {
         if(this.a[var2] != null && this.a[var2].c == var1) {
            return var2;
         }
      }

      return -1;
   }

   private int d(OItemStack var1) {
      for(int var2 = 0; var2 < this.a.length; ++var2) {
         if(this.a[var2] != null && this.a[var2].c == var1.c && this.a[var2].c() && this.a[var2].a < this.a[var2].b() && this.a[var2].a < this.a() && (!this.a[var2].e() || this.a[var2].h() == var1.h()) && OItemStack.a(this.a[var2], var1)) {
            return var2;
         }
      }

      return -1;
   }

   private int m() {
      for(int var1 = 0; var1 < this.a.length; ++var1) {
         if(this.a[var1] == null) {
            return var1;
         }
      }

      return -1;
   }

   private int e(OItemStack var1) {
      int var2 = var1.c;
      int var3 = var1.a;
      int var4;
      if(var1.b() == 1) {
         var4 = this.m();
         if(var4 < 0) {
            return var3;
         } else {
            if(this.a[var4] == null) {
               this.a[var4] = OItemStack.b(var1);
            }

            return 0;
         }
      } else {
         var4 = this.d(var1);
         if(var4 < 0) {
            var4 = this.m();
         }

         if(var4 < 0) {
            return var3;
         } else {
            if(this.a[var4] == null) {
               this.a[var4] = new OItemStack(var2, 0, var1.h());
               if(var1.n()) {
                  this.a[var4].d((ONBTTagCompound)var1.o().b());
               }
            }

            int var5 = var3;
            if(var3 > this.a[var4].b() - this.a[var4].a) {
               var5 = this.a[var4].b() - this.a[var4].a;
            }

            if(var5 > this.a() - this.a[var4].a) {
               var5 = this.a() - this.a[var4].a;
            }

            if(var5 == 0) {
               return var3;
            } else {
               var3 -= var5;
               this.a[var4].a += var5;
               this.a[var4].b = 5;
               return var3;
            }
         }
      }
   }

   public void i() {
      for(int var1 = 0; var1 < this.a.length; ++var1) {
         if(this.a[var1] != null) {
            this.a[var1].a(this.d.bi, this.d, var1, this.c == var1);
         }
      }

   }

   public boolean c(int var1) {
      int var2 = this.f(var1);
      if(var2 < 0) {
         return false;
      } else {
         if(--this.a[var2].a <= 0) {
            this.a[var2] = null;
         }

         return true;
      }
   }

   public boolean d(int var1) {
      int var2 = this.f(var1);
      return var2 >= 0;
   }

   public boolean a(OItemStack var1) {
      int var2;
      if(var1.f()) {
         var2 = this.m();
         if(var2 >= 0) {
            this.a[var2] = OItemStack.b(var1);
            this.a[var2].b = 5;
            var1.a = 0;
            return true;
         } else if(this.d.L.d) {
            var1.a = 0;
            return true;
         } else {
            return false;
         }
      } else {
         do {
            var2 = var1.a;
            var1.a = this.e(var1);
         } while(var1.a > 0 && var1.a < var2);

         if(var1.a == var2 && this.d.L.d) {
            var1.a = 0;
            return true;
         } else {
            return var1.a < var2;
         }
      }
   }

   public OItemStack a(int var1, int var2) {
      OItemStack[] var3 = this.a;
      if(var1 >= this.a.length) {
         var3 = this.b;
         var1 -= this.a.length;
      }

      if(var3[var1] != null) {
         OItemStack var4;
         if(var3[var1].a <= var2) {
            var4 = var3[var1];
            var3[var1] = null;
            return var4;
         } else {
            var4 = var3[var1].a(var2);
            if(var3[var1].a == 0) {
               var3[var1] = null;
            }

            return var4;
         }
      } else {
         return null;
      }
   }

   public OItemStack b(int var1) {
      OItemStack[] var2 = this.a;
      if(var1 >= this.a.length) {
         var2 = this.b;
         var1 -= this.a.length;
      }

      if(var2[var1] != null) {
         OItemStack var3 = var2[var1];
         var2[var1] = null;
         return var3;
      } else {
         return null;
      }
   }

   public void a(int var1, OItemStack var2) {
      OItemStack[] var3 = this.a;
      if(var1 >= var3.length) {
         var1 -= var3.length;
         var3 = this.b;
      }
      var3[var1] = var2;
   }
   public float a(OBlock var1) {
      float var2 = 1.0F;
      if(this.a[this.c] != null) {
         var2 *= this.a[this.c].a(var1);
      }

      return var2;
   }

   public ONBTTagList a(ONBTTagList var1) {
      int var2;
      ONBTTagCompound var3;
      for(var2 = 0; var2 < this.a.length; ++var2) {
         if(this.a[var2] != null) {
            var3 = new ONBTTagCompound();
            var3.a("Slot", (byte)var2);
            this.a[var2].b(var3);
            var1.a((ONBTBase)var3);
         }
      }

      for(var2 = 0; var2 < this.b.length; ++var2) {
         if(this.b[var2] != null) {
            var3 = new ONBTTagCompound();
            var3.a("Slot", (byte)(var2 + 100));
            this.b[var2].b(var3);
            var1.a((ONBTBase)var3);
         }
      }

      return var1;
   }

   public void b(ONBTTagList var1) {
      this.a = new OItemStack[36];
      this.b = new OItemStack[4];

      for(int var2 = 0; var2 < var1.d(); ++var2) {
         ONBTTagCompound var3 = (ONBTTagCompound)var1.a(var2);
         int var4 = var3.d("Slot") & 255;
         OItemStack var5 = OItemStack.a(var3);
         if(var5 != null) {
            if(var4 >= 0 && var4 < this.a.length) {
               this.a[var4] = var5;
            }

            if(var4 >= 100 && var4 < this.b.length + 100) {
               this.b[var4 - 100] = var5;
            }
         }
      }

   }

   public int c() {
      return this.a.length + 4;
   }

   public OItemStack g_(int var1) {
      OItemStack[] var2 = this.a;
      if(var1 >= var2.length) {
         var1 -= var2.length;
         var2 = this.b;
      }

      return var2[var1];
   }

   public String e() {
      return name;
   }

   public int a() {
      return 64;
   }

   public int a(OEntity var1) {
      OItemStack var2 = this.g_(this.c);
      return var2 != null?var2.a(var1):1;
   }

   public boolean b(OBlock var1) {
      if(var1.cd.k()) {
         return true;
      } else {
         OItemStack var2 = this.g_(this.c);
         return var2 != null?var2.b(var1):false;
      }
   }

   public int j() {
      int var1 = 0;

      for(int var2 = 0; var2 < this.b.length; ++var2) {
         if(this.b[var2] != null && this.b[var2].a() instanceof OItemArmor) {
            int var3 = ((OItemArmor)this.b[var2].a()).b;
            var1 += var3;
         }
      }

      return var1;
   }

   public void e(int var1) {
      var1 /= 4;
      if(var1 < 1) {
         var1 = 1;
      }

      for(int var2 = 0; var2 < this.b.length; ++var2) {
         if(this.b[var2] != null && this.b[var2].a() instanceof OItemArmor) {
            this.b[var2].a(var1, this.d);
            if(this.b[var2].a == 0) {
               this.b[var2].a(this.d);
               this.b[var2] = null;
            }
         }
      }

   }

   public void k() {
      int var1;
      for(var1 = 0; var1 < this.a.length; ++var1) {
         if(this.a[var1] != null) {
            this.d.a(this.a[var1], true);
            this.a[var1] = null;
         }
      }

      for(var1 = 0; var1 < this.b.length; ++var1) {
         if(this.b[var1] != null) {
            this.d.a(this.b[var1], true);
            this.b[var1] = null;
         }
      }

   }

   public void G_() {
      this.e = true;
   }

   public void b(OItemStack var1) {
      this.f = var1;
      this.d.a(var1);
   }

   public OItemStack l() {
      return this.f;
   }

   public boolean a(OEntityPlayer var1) {
      return this.d.bE?false:var1.j(this.d) <= 64.0D;
   }

   public boolean c(OItemStack var1) {
      int var2;
      for(var2 = 0; var2 < this.b.length; ++var2) {
         if(this.b[var2] != null && this.b[var2].c(var1)) {
            return true;
         }
      }

      for(var2 = 0; var2 < this.a.length; ++var2) {
         if(this.a[var2] != null && this.a[var2].c(var1)) {
            return true;
         }
      }

      return false;
   }

   public void f() {}

   public void g() {}

   public void a(OInventoryPlayer var1) {
      int var2;
      for(var2 = 0; var2 < this.a.length; ++var2) {
         this.a[var2] = OItemStack.b(var1.a[var2]);
      }

      for(var2 = 0; var2 < this.b.length; ++var2) {
         this.b[var2] = OItemStack.b(var1.b[var2]);
      }

   }

    //CanaryMod start - Container
    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(this.a, c());
    }
   
    @Override
    public void setContents(OItemStack[] values) {
        a = Arrays.copyOf(values, c());
    }
    
    @Override
    public OItemStack getSlot(int index) {
        OItemStack stack = this.b(index);
        if(stack != null) {
            return stack;
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
    public Item removeItem(Item item) {
        if(a[item.getSlot()] != null && a[item.getSlot()].c == item.getId()) {
            Item toRet = a[item.getSlot()].getCanaryItem();
            a[item.getSlot()] = null;
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
            if(stack == null) {
                continue;
            }
            if(stack.c == id && stack.a == amount) {
                return stack.getCanaryItem();
            }
        }
        return null;
    }
    
    @Override
    public void clearContents() {
        Arrays.fill(a, (OItemStack)null);
    }
    
    
    @Override
    public void setSlot(int index, OItemStack value) {
        a(index, value);
    }

    @Override
    public void setInventoryName(String value) {
        name = value;
    }
    
    @Override
    public void update(){
        d.F_();
    }
    
    public boolean hasItemStack(OItemStack var1) {
        int var2;
        for (var2 = 0; var2 < this.b.length; ++var2) {
            if (this.b[var2] != null && this.b[var2].c(var1)) {
                return true;
            }
        }

        for (var2 = 0; var2 < this.a.length; ++var2) {
            if (this.a[var2] != null && this.a[var2].c(var1)) {
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
    public String getInventoryName() {
        return e();
    }

    @Override
    public int getInventorySize() {
        return c();
    }

    @Override
    public int getInventoryStackLimit() {
        return a();
    }

    @Override
    public boolean hasItem(int itemId) {
        for (int var2 = 0; var2 < this.a.length; ++var2) {
            if (this.a[var2] != null && this.a[var2].c == itemId) {
                return true;
            }
        }
        for (int var2 = 0; var2 < this.b.length; ++var2) {
            if (this.b[var2] != null && this.b[var2].c == itemId) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean hasItemStack(int itemId, int amount) {
        int var2;
        for (var2 = 0; var2 < this.a.length; ++var2) {
            if (this.a[var2] != null && this.a[var2].c == itemId && this.a[var2].a == amount) {
                return true;
            }
        }
        for (var2 = 0; var2 < this.b.length; ++var2) {
            if (this.b[var2] != null && this.b[var2].c == itemId && this.b[var2].a == amount) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        int var2;
        for (var2 = 0; var2 < this.a.length; ++var2) {
            if (this.a[var2] != null && this.a[var2].c == itemId && (this.a[var2].a >= minAmount && this.a[var2].a <= maxAmount)) {
                return true;
            }
        }
        for (var2 = 0; var2 < this.b.length; ++var2) {
            if (this.b[var2] != null && this.b[var2].c == itemId && (this.b[var2].a >= minAmount && this.b[var2].a <= maxAmount)) {
                return true;
            }
        }
        return false;
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
                    // Drop whatever is left
                    ((OEntityPlayerMP) d).getPlayer().dropLoot(itemId, remaining);
                    remaining = 0;
                } else {
                    addItem(new CanaryItem(itemId, amount, targetSlot));
                    remaining--;
                }
            } 
            else {
                if (hasItemStack(itemId, 1, 63)) {
                    Item i = getItem(itemId, 63);
                    if (i != null) {
                        int freeSpace = 64 - i.getAmount();
                        int toAdd = 0;
                        if (remaining > freeSpace) {
                            toAdd = freeSpace;
                            remaining -= freeSpace;
                        } 
                        else {
                            toAdd = remaining;
                            remaining = 0;
                        }
                        i.setAmount(i.getAmount() + toAdd);
                        addItem(i);
                    }
                } 
                else {
                    
                    int targetSlot = getEmptySlot();
                    
                    if (targetSlot == -1) {
                        // Drop whatever is left
                        ((OEntityPlayerMP) d).getPlayer().dropLoot(itemId, remaining);
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
    
    
    //CanaryMod Container End

}
