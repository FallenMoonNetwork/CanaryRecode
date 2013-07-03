package net.minecraft.server;

import net.canarymod.api.inventory.Inventory;


public class ContainerChest extends Container {

    private IInventory a;
    private int f;

    public ContainerChest(IInventory iinventory, IInventory iinventory1) {
        this.a = iinventory1;
        this.f = iinventory1.j_() / 9;
        iinventory1.f();
        int i0 = (this.f - 4) * 18;

        int i1;
        int i2;

        for (i1 = 0; i1 < this.f; ++i1) {
            for (i2 = 0; i2 < 9; ++i2) {
                this.a(new Slot(iinventory1, i2 + i1 * 9, 8 + i2 * 18, 18 + i1 * 18));
            }
        }

        for (i1 = 0; i1 < 3; ++i1) {
            for (i2 = 0; i2 < 9; ++i2) {
                this.a(new Slot(iinventory, i2 + i1 * 9 + 9, 8 + i2 * 18, 103 + i1 * 18 + i0));
            }
        }

        for (i1 = 0; i1 < 9; ++i1) {
            this.a(new Slot(iinventory, i1, 8 + i1 * 18, 161 + i0));
        }
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
            if (i0 < this.f * 9) {
                if (!this.a(itemstack1, this.f * 9, this.c.size(), true)) {
                    return null;
                }
            } else if (!this.a(itemstack1, 0, this.f * 9, false)) {
                return null;
            }

            if (itemstack1.a == 0) {
                slot.c((ItemStack) null);
            } else {
                slot.e();
            }
        }

        return itemstack;
    }

    public void b(EntityPlayer entityplayer) {
        super.b(entityplayer);
        this.a.g();
    }

    public IInventory e() {
        return this.a;
    }

    // CanaryMod: Special setter to handle the difference in Chests
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    //
}
