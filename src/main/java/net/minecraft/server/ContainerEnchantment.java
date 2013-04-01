package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class ContainerEnchantment extends Container {

    public IInventory a = new SlotEnchantmentTable(this, "Enchant", true, 1);
    public World h; // CanaryMod: private => public
    public int i; // CanaryMod: private => public
    public int j; // CanaryMod: private => public
    public int k; // CanaryMod: private => public
    private Random l = new Random();
    public long f;
    public int[] g = new int[3];

    public ContainerEnchantment(InventoryPlayer inventoryplayer, World world, int i0, int i1, int i2) {
        this.h = world;
        this.i = i0;
        this.j = i1;
        this.k = i2;
        this.a((Slot) (new SlotEnchantment(this, this.a, 0, 25, 47)));

        int i3;

        for (i3 = 0; i3 < 3; ++i3) {
            for (int i4 = 0; i4 < 9; ++i4) {
                this.a(new Slot(inventoryplayer, i4 + i3 * 9 + 9, 8 + i4 * 18, 84 + i3 * 18));
            }
        }

        for (i3 = 0; i3 < 9; ++i3) {
            this.a(new Slot(inventoryplayer, i3, 8 + i3 * 18, 142));
        }
    }

    public void a(ICrafting icrafting) {
        super.a(icrafting);
        icrafting.a(this, 0, this.g[0]);
        icrafting.a(this, 1, this.g[1]);
        icrafting.a(this, 2, this.g[2]);
    }

    public void b() {
        super.b();

        for (int i0 = 0; i0 < this.e.size(); ++i0) {
            ICrafting icrafting = (ICrafting) this.e.get(i0);

            icrafting.a(this, 0, this.g[0]);
            icrafting.a(this, 1, this.g[1]);
            icrafting.a(this, 2, this.g[2]);
        }
    }

    public void a(IInventory iinventory) {
        if (iinventory == this.a) {
            ItemStack itemstack = iinventory.a(0);
            int i0;

            if (itemstack != null && itemstack.w()) {
                this.f = this.l.nextLong();
                if (!this.h.I) {
                    i0 = 0;

                    int i1;

                    for (i1 = -1; i1 <= 1; ++i1) {
                        for (int i2 = -1; i2 <= 1; ++i2) {
                            if ((i1 != 0 || i2 != 0) && this.h.c(this.i + i2, this.j, this.k + i1) && this.h.c(this.i + i2, this.j + 1, this.k + i1)) {
                                if (this.h.a(this.i + i2 * 2, this.j, this.k + i1 * 2) == Block.ar.cz) {
                                    ++i0;
                                }

                                if (this.h.a(this.i + i2 * 2, this.j + 1, this.k + i1 * 2) == Block.ar.cz) {
                                    ++i0;
                                }

                                if (i2 != 0 && i1 != 0) {
                                    if (this.h.a(this.i + i2 * 2, this.j, this.k + i1) == Block.ar.cz) {
                                        ++i0;
                                    }

                                    if (this.h.a(this.i + i2 * 2, this.j + 1, this.k + i1) == Block.ar.cz) {
                                        ++i0;
                                    }

                                    if (this.h.a(this.i + i2, this.j, this.k + i1 * 2) == Block.ar.cz) {
                                        ++i0;
                                    }

                                    if (this.h.a(this.i + i2, this.j + 1, this.k + i1 * 2) == Block.ar.cz) {
                                        ++i0;
                                    }
                                }
                            }
                        }
                    }

                    for (i1 = 0; i1 < 3; ++i1) {
                        this.g[i1] = EnchantmentHelper.a(this.l, i1, i0, itemstack);
                    }

                    this.b();
                }
            } else {
                for (i0 = 0; i0 < 3; ++i0) {
                    this.g[i0] = 0;
                }
            }
        }
    }

    public boolean a(EntityPlayer entityplayer, int i0) {
        ItemStack itemstack = this.a.a(0);

        if (this.g[i0] > 0 && itemstack != null && (entityplayer.cf >= this.g[i0] || entityplayer.ce.d)) {
            if (!this.h.I) {
                List list = EnchantmentHelper.b(this.l, itemstack, this.g[i0]);
                boolean flag0 = itemstack.c == Item.aM.cp;

                if (list != null) {
                    entityplayer.a(-this.g[i0]);
                    if (flag0) {
                        itemstack.c = Item.bX.cp;
                    }

                    int i1 = flag0 ? this.l.nextInt(list.size()) : -1;

                    for (int i2 = 0; i2 < list.size(); ++i2) {
                        EnchantmentData enchantmentdata = (EnchantmentData) list.get(i2);

                        if (!flag0 || i2 == i1) {
                            if (flag0) {
                                Item.bX.a(itemstack, enchantmentdata);
                            } else {
                                itemstack.a(enchantmentdata.b, enchantmentdata.c);
                            }
                        }
                    }

                    this.a(this.a);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    public void b(EntityPlayer entityplayer) {
        super.b(entityplayer);
        if (!this.h.I) {
            ItemStack itemstack = this.a.b(0);

            if (itemstack != null) {
                entityplayer.c(itemstack);
            }
        }
    }

    public boolean a(EntityPlayer entityplayer) {
        return this.h.a(this.i, this.j, this.k) != Block.bI.cz ? false : entityplayer.e((double) this.i + 0.5D, (double) this.j + 0.5D, (double) this.k + 0.5D) <= 64.0D;
    }

    public ItemStack b(EntityPlayer entityplayer, int i0) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.c.get(i0);

        if (slot != null && slot.d()) {
            ItemStack itemstack1 = slot.c();

            itemstack = itemstack1.m();
            if (i0 == 0) {
                if (!this.a(itemstack1, 1, 37, true)) {
                    return null;
                }
            } else {
                if (((Slot) this.c.get(0)).d() || !((Slot) this.c.get(0)).a(itemstack1)) {
                    return null;
                }

                if (itemstack1.p() && itemstack1.a == 1) {
                    ((Slot) this.c.get(0)).c(itemstack1.m());
                    itemstack1.a = 0;
                } else if (itemstack1.a >= 1) {
                    ((Slot) this.c.get(0)).c(new ItemStack(itemstack1.c, 1, itemstack1.k()));
                    --itemstack1.a;
                }
            }

            if (itemstack1.a == 0) {
                slot.c((ItemStack) null);
            } else {
                slot.e();
            }

            if (itemstack1.a == itemstack.a) {
                return null;
            }

            slot.a(entityplayer, itemstack1);
        }

        return itemstack;
    }
}
