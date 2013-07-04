package net.minecraft.server;

import net.canarymod.api.entity.vehicle.CanaryHopperMinecart;

public class ContainerHopper extends Container {

    private final IInventory a;

    public ContainerHopper(InventoryPlayer inventoryplayer, IInventory iinventory) {
        this.a = iinventory;
        iinventory.k_();
        byte b0 = 51;

        int i0;

        for (i0 = 0; i0 < iinventory.j_(); ++i0) {
            this.a(new Slot(iinventory, i0, 44 + i0 * 18, 20));
        }

        for (i0 = 0; i0 < 3; ++i0) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.a(new Slot(inventoryplayer, i1 + i0 * 9 + 9, 8 + i1 * 18, i0 * 18 + b0));
            }
        }

        for (i0 = 0; i0 < 9; ++i0) {
            this.a(new Slot(inventoryplayer, i0, 8 + i0 * 18, 58 + b0));
        }

        this.inventory = (this.a instanceof TileEntityHopper ? ((TileEntityHopper) this.a).getCanaryHopper() : (CanaryHopperMinecart) ((EntityMinecartHopper) this.a).getCanaryEntity()); // CanaryMod: Set inventory instance
    }

    public boolean a(EntityPlayer entityplayer) {
        return this.a.a(entityplayer);
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
            } else if (!this.a(itemstack1, 0, this.a.j_(), false)) {
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
}
