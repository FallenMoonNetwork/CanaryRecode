package net.minecraft.server;

public class ContainerBeacon extends Container {

    private TileEntityBeacon a;
    private final SlotBeacon f;
    private int g;
    private int h;
    private int i;

    public ContainerBeacon(InventoryPlayer inventoryplayer, TileEntityBeacon tileentitybeacon) {
        this.a = tileentitybeacon;
        this.a((Slot) (this.f = new SlotBeacon(this, tileentitybeacon, 0, 136, 110)));
        byte b0 = 36;
        short short1 = 137;

        int i0;

        for (i0 = 0; i0 < 3; ++i0) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.a(new Slot(inventoryplayer, i1 + i0 * 9 + 9, b0 + i1 * 18, short1 + i0 * 18));
            }
        }

        for (i0 = 0; i0 < 9; ++i0) {
            this.a(new Slot(inventoryplayer, i0, b0 + i0 * 18, 58 + short1));
        }

        this.g = tileentitybeacon.l();
        this.h = tileentitybeacon.j();
        this.i = tileentitybeacon.k();

        this.inventory = a.getCanaryBeacon(); // CanaryMod: Set inventory instance
    }

    public void a(ICrafting icrafting) {
        super.a(icrafting);
        icrafting.a(this, 0, this.g);
        icrafting.a(this, 1, this.h);
        icrafting.a(this, 2, this.i);
    }

    public TileEntityBeacon e() {
        return this.a;
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
            if (i0 == 0) {
                if (!this.a(itemstack1, 1, 37, true)) {
                    return null;
                }

                slot.a(itemstack1, itemstack);
            } else if (!this.f.e() && this.f.a(itemstack1) && itemstack1.b == 1) {
                if (!this.a(itemstack1, 0, 1, false)) {
                    return null;
                }
            } else if (i0 >= 1 && i0 < 28) {
                if (!this.a(itemstack1, 28, 37, false)) {
                    return null;
                }
            } else if (i0 >= 28 && i0 < 37) {
                if (!this.a(itemstack1, 1, 28, false)) {
                    return null;
                }
            } else if (!this.a(itemstack1, 1, 37, false)) {
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
