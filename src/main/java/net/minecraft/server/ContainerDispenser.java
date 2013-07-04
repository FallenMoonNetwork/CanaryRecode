package net.minecraft.server;

public class ContainerDispenser extends Container {

    private TileEntityDispenser a;

    public ContainerDispenser(IInventory iinventory, TileEntityDispenser tileentitydispenser) {
        this.a = tileentitydispenser;

        int i0;
        int i1;

        for (i0 = 0; i0 < 3; ++i0) {
            for (i1 = 0; i1 < 3; ++i1) {
                this.a(new Slot(tileentitydispenser, i1 + i0 * 3, 62 + i1 * 18, 17 + i0 * 18));
            }
        }

        for (i0 = 0; i0 < 3; ++i0) {
            for (i1 = 0; i1 < 9; ++i1) {
                this.a(new Slot(iinventory, i1 + i0 * 9 + 9, 8 + i1 * 18, 84 + i0 * 18));
            }
        }

        for (i0 = 0; i0 < 9; ++i0) {
            this.a(new Slot(iinventory, i0, 8 + i0 * 18, 142));
        }

        this.inventory = a.getCanaryDispenser(); // CanaryMod: Set inventory instance
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
            if (i0 < 9) {
                if (!this.a(itemstack1, 9, 45, true)) {
                    return null;
                }
            } else if (!this.a(itemstack1, 0, 9, false)) {
                return null;
            }

            if (itemstack1.b == 0) {
                slot.c((ItemStack) null);
            } else {
                slot.f();
            }

            if (itemstack1.b == itemstack.b) {
                return null;
            }

            slot.a(entityplayer, itemstack1);
        }

        return itemstack;
    }
}
