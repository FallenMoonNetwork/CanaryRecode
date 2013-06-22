package net.minecraft.server;

public class ContainerFurnace extends Container {

    private TileEntityFurnace a;
    private int f = 0;
    private int g = 0;
    private int h = 0;

    public ContainerFurnace(InventoryPlayer inventoryplayer, TileEntityFurnace tileentityfurnace) {
        this.a = tileentityfurnace;
        this.a(new Slot(tileentityfurnace, 0, 56, 17));
        this.a(new Slot(tileentityfurnace, 1, 56, 53));
        this.a((Slot) (new SlotFurnace(inventoryplayer.d, tileentityfurnace, 2, 116, 35)));

        int i0;

        for (i0 = 0; i0 < 3; ++i0) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.a(new Slot(inventoryplayer, i1 + i0 * 9 + 9, 8 + i1 * 18, 84 + i0 * 18));
            }
        }

        for (i0 = 0; i0 < 9; ++i0) {
            this.a(new Slot(inventoryplayer, i0, 8 + i0 * 18, 142));
        }

        this.inventory = a.getCanaryFurnace(); // CanaryMod: Set inventory instance
    }

    public void a(ICrafting icrafting) {
        super.a(icrafting);
        icrafting.a(this, 0, this.a.c);
        icrafting.a(this, 1, this.a.a);
        icrafting.a(this, 2, this.a.b);
    }

    public void b() {
        super.b();

        for (int i0 = 0; i0 < this.e.size(); ++i0) {
            ICrafting icrafting = (ICrafting) this.e.get(i0);

            if (this.f != this.a.c) {
                icrafting.a(this, 0, this.a.c);
            }

            if (this.g != this.a.a) {
                icrafting.a(this, 1, this.a.a);
            }

            if (this.h != this.a.b) {
                icrafting.a(this, 2, this.a.b);
            }
        }

        this.f = this.a.c;
        this.g = this.a.a;
        this.h = this.a.b;
    }

    public boolean a(EntityPlayer entityplayer) {
        return this.a.a(entityplayer);
    }

    public ItemStack b(EntityPlayer entityplayer, int i0) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.c.get(i0);

        if (slot != null && slot.d()) {
            ItemStack itemstack1 = slot.c();

            itemstack = itemstack1.m();
            if (i0 == 2) {
                if (!this.a(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.a(itemstack1, itemstack);
            } else if (i0 != 1 && i0 != 0) {
                if (FurnaceRecipes.a().b(itemstack1.b().cp) != null) {
                    if (!this.a(itemstack1, 0, 1, false)) {
                        return null;
                    }
                } else if (TileEntityFurnace.b(itemstack1)) {
                    if (!this.a(itemstack1, 1, 2, false)) {
                        return null;
                    }
                } else if (i0 >= 3 && i0 < 30) {
                    if (!this.a(itemstack1, 30, 39, false)) {
                        return null;
                    }
                } else if (i0 >= 30 && i0 < 39 && !this.a(itemstack1, 3, 30, false)) {
                    return null;
                }
            } else if (!this.a(itemstack1, 3, 39, false)) {
                return null;
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
