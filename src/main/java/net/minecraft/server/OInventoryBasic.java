package net.minecraft.server;

import java.util.Arrays;
import java.util.List;

import net.canarymod.api.inventory.Item;
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
    //CanaryMod end - Container
}
