package net.minecraft.server;

import java.util.Arrays;
import java.util.List;

import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.minecraft.server.OEntityPlayer;
import net.minecraft.server.OIInvBasic;
import net.minecraft.server.OIInventory;
import net.minecraft.server.OItemStack;

public class OInventoryBasic implements OIInventory {

    private String a;
    private int b;
    private OItemStack[] c;
    private List d;


     public OInventoryBasic(String var1, int var2) {
         super();
         this.a = var1;
         this.b = var2;
         this.c = new OItemStack[var2];
     }

      public OItemStack g_(int var1) {
          return this.c[var1];
      }

      public OItemStack a(int var1, int var2) {
          if(this.c[var1] != null) {
              OItemStack var3;
              if(this.c[var1].a <= var2) {
                  var3 = this.c[var1];
                  this.c[var1] = null;
                  this.G_();
                  return var3;
              } else {
                  var3 = this.c[var1].a(var2);
                  if(this.c[var1].a == 0) {
                      this.c[var1] = null;
                  }

                  this.G_();
                  return var3;
              }
          } else {
              return null;
          }
      }

      public OItemStack b(int var1) {
          if(this.c[var1] != null) {
              OItemStack var2 = this.c[var1];
              this.c[var1] = null;
                  return var2;
          } else {
              return null;
          }
      }

      public void a(int var1, OItemStack var2) {
          this.c[var1] = var2;
          if(var2 != null && var2.a > this.a()) {
              var2.a = this.a();
          }

          this.G_();
      }

      public int c() {
          return this.b;
      }

      public String e() {
          return this.a;
      }

      public int a() {
          return 64;
      }

      public void G_() {
           if(this.d != null) {
               for(int var1 = 0; var1 < this.d.size(); ++var1) {
                   ((OIInvBasic)this.d.get(var1)).a(this);
               }
           }
      }

      public boolean a(OEntityPlayer var1) {
          return true;
      }

      public void f() {
      }

      public void g() {
      }
   
    //CanaryMod start - Container
    @Override
    public OItemStack[] getContents() {
        return Arrays.copyOf(this.c, c());
    }

    @Override
    public void setContents(OItemStack[] values) {
        this.c = Arrays.copyOf(values, c());
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
        Arrays.fill(c, (OItemStack)null);
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
        if(c[item.getSlot()] != null && c[item.getSlot()].c == item.getId()) {
            Item toRet = c[item.getSlot()].getCanaryItem();
            c[item.getSlot()] = null;
            return toRet;
        }
        return null;
    }

    @Override
    public Item removeItem(int id) {
        for(int index = 0; index < c.length; index++) {
            if(c[index].c == id) {
                Item toRet = c[index].getCanaryItem();
                c[index] = null;
                return toRet;
            }
        }
        return null;
    }

    @Override
    public boolean hasItemStack(OItemStack oItemStack) {
        for (int index = 0; index < this.c.length; ++index) {
            if (this.c[index] != null && this.c[index].c(oItemStack)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasItem(int itemId) {
        for (int var2 = 0; var2 < this.c.length; ++var2) {
            if (this.c[var2] != null && this.c[var2].c == itemId) {
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
        for (var2 = 0; var2 < this.c.length; ++var2) {
            if (this.c[var2] != null && this.c[var2].c == itemId && this.c[var2].a == amount) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        int var2;
        for (var2 = 0; var2 < this.c.length; ++var2) {
            if (this.c[var2] != null && this.c[var2].c == itemId && (this.c[var2].a >= minAmount || this.c[var2].a <= maxAmount)) {
                return true;
            }
        }
        return false;
    }
    //CanaryMod end - Container
}
