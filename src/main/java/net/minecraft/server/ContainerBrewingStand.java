package net.minecraft.server;

public class ContainerBrewingStand extends Container {

    private TileEntityBrewingStand a;
    private final Slot f;
    private int g = 0;

    public ContainerBrewingStand(InventoryPlayer inventoryplayer, TileEntityBrewingStand tileentitybrewingstand) {
        this.a = tileentitybrewingstand;
        this.a((Slot) (new SlotBrewingStandPotion(inventoryplayer.d, tileentitybrewingstand, 0, 56, 46)));
        this.a((Slot) (new SlotBrewingStandPotion(inventoryplayer.d, tileentitybrewingstand, 1, 79, 53)));
        this.a((Slot) (new SlotBrewingStandPotion(inventoryplayer.d, tileentitybrewingstand, 2, 102, 46)));
        this.f = this.a((Slot) (new SlotBrewingStandIngredient(this, tileentitybrewingstand, 3, 79, 17)));

        int i0;

        for (i0 = 0; i0 < 3; ++i0) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.a(new Slot(inventoryplayer, i1 + i0 * 9 + 9, 8 + i1 * 18, 84 + i0 * 18));
            }
        }

        for (i0 = 0; i0 < 9; ++i0) {
            this.a(new Slot(inventoryplayer, i0, 8 + i0 * 18, 142));
        }

        this.inventory = a.getCanaryBrewingStand(); // CanaryMod: Set inventory instance
    }

    public void a(ICrafting icrafting) {
        super.a(icrafting);
        icrafting.a(this, 0, this.a.x_());
    }

    public void b() {
        super.b();

        for (int i0 = 0; i0 < this.e.size(); ++i0) {
            ICrafting icrafting = (ICrafting) this.e.get(i0);

            if (this.g != this.a.x_()) {
                icrafting.a(this, 0, this.a.x_());
            }
        }

        this.g = this.a.x_();
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
            if ((i0 < 0 || i0 > 2) && i0 != 3) {
                if (!this.f.e() && this.f.a(itemstack1)) {
                    if (!this.a(itemstack1, 3, 4, false)) {
                        return null;
                    }
                } else if (SlotBrewingStandPotion.b_(itemstack)) {
                    if (!this.a(itemstack1, 0, 3, false)) {
                        return null;
                    }
                } else if (i0 >= 4 && i0 < 31) {
                    if (!this.a(itemstack1, 31, 40, false)) {
                        return null;
                    }
                } else if (i0 >= 31 && i0 < 40) {
                    if (!this.a(itemstack1, 4, 31, false)) {
                        return null;
                    }
                } else if (!this.a(itemstack1, 4, 40, false)) {
                    return null;
                }
            } else {
                if (!this.a(itemstack1, 4, 40, true)) {
                    return null;
                }

                slot.a(itemstack1, itemstack);
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
