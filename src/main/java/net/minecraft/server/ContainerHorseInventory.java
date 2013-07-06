package net.minecraft.server;

import net.canarymod.api.inventory.Inventory;

public class ContainerHorseInventory extends Container {

    private IInventory a;
    private EntityHorse f;

    public ContainerHorseInventory(IInventory iinventory, IInventory iinventory1, EntityHorse entityhorse) {
        this.a = iinventory1;
        this.f = entityhorse;
        byte b0 = 3;

        iinventory1.k_();
        int i0 = (b0 - 4) * 18;

        this.a(new ContainerHorseInventorySlotSaddle(this, iinventory1, 0, 8, 18));
        this.a(new ContainerHorseInventorySlotArmor(this, iinventory1, 1, 8, 36, entityhorse));
        int i1;
        int i2;

        if (entityhorse.ca()) {
            for (i1 = 0; i1 < b0; ++i1) {
                for (i2 = 0; i2 < 5; ++i2) {
                    this.a(new Slot(iinventory1, 2 + i2 + i1 * 5, 80 + i2 * 18, 18 + i1 * 18));
                }
            }
        }

        for (i1 = 0; i1 < 3; ++i1) {
            for (i2 = 0; i2 < 9; ++i2) {
                this.a(new Slot(iinventory, i2 + i1 * 9 + 9, 8 + i2 * 18, 102 + i1 * 18 + i0));
            }
        }

        for (i1 = 0; i1 < 9; ++i1) {
            this.a(new Slot(iinventory, i1, 8 + i1 * 18, 160 + i0));
        }
    }

    public boolean a(EntityPlayer entityplayer) {
        return this.a.a(entityplayer) && this.f.d(entityplayer) < 8.0F;
    }

    public ItemStack b(EntityPlayer entityplayer, int i0) {
        ItemStack itemstack = null;
        Slot slot = (Slot) this.c.get(i0);

        if (slot != null && slot.e()) {
            ItemStack itemstack1 = slot.d();

            itemstack = itemstack1.m();
            if (i0 < this.a.j_()) {
                if (!this.a(itemstack1, this.a.j_(), this.c.size(), true)) {
                    return null;
                }
            } else if (this.a(1).a(itemstack1)) {
                if (!this.a(itemstack1, 1, 2, false)) {
                    return null;
                }
            } else if (this.a(0).a(itemstack1)) {
                if (!this.a(itemstack1, 0, 1, false)) {
                    return null;
                }
            } else if (this.a.j_() <= 2 || !this.a(itemstack1, 2, this.a.j_(), false)) {
                return null;
            }

            if (itemstack1.b == 0) {
                slot.c((ItemStack) null);
            } else {
                slot.f();
            }
        }

        return itemstack;
    }

    public void b(EntityPlayer entityplayer) {
        super.b(entityplayer);
        this.a.g();
    }

    // CanaryMod: Special setter to handle the difference in Chests
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
    //
}
