package net.minecraft.server;

public class ContainerMerchant extends Container {

    private IMerchant a;
    private InventoryMerchant f;
    private final World g;

    public ContainerMerchant(InventoryPlayer inventoryplayer, IMerchant imerchant, World world) {
        this.a = imerchant;
        this.g = world;
        this.f = new InventoryMerchant(inventoryplayer.d, imerchant);
        this.a(new Slot(this.f, 0, 36, 53));
        this.a(new Slot(this.f, 1, 62, 53));
        this.a((Slot)(new SlotMerchantResult(inventoryplayer.d, imerchant, this.f, 2, 120, 53)));

        int i0;

        for (i0 = 0; i0 < 3; ++i0) {
            for (int i1 = 0; i1 < 9; ++i1) {
                this.a(new Slot(inventoryplayer, i1 + i0 * 9 + 9, 8 + i1 * 18, 84 + i0 * 18));
            }
        }

        for (i0 = 0; i0 < 9; ++i0) {
            this.a(new Slot(inventoryplayer, i0, 8 + i0 * 18, 142));
        }
    }

    public InventoryMerchant e() {
        return this.f;
    }

    public void a(ICrafting icrafting) {
        super.a(icrafting);
    }

    public void b() {
        super.b();
    }

    public void a(IInventory iinventory) {
        this.f.h();
        super.a(iinventory);
    }

    public void e(int i0) {
        this.f.c(i0);
    }

    public boolean a(EntityPlayer entityplayer) {
        return this.a.m_() == entityplayer;
    }

    public ItemStack b(EntityPlayer entityplayer, int i0) {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.c.get(i0);

        if (slot != null && slot.e()) {
            ItemStack itemstack1 = slot.d();

            itemstack = itemstack1.m();
            if (i0 == 2) {
                if (!this.a(itemstack1, 3, 39, true)) {
                    return null;
                }

                slot.a(itemstack1, itemstack);
            }
            else if (i0 != 0 && i0 != 1) {
                if (i0 >= 3 && i0 < 30) {
                    if (!this.a(itemstack1, 30, 39, false)) {
                        return null;
                    }
                }
                else if (i0 >= 30 && i0 < 39 && !this.a(itemstack1, 3, 30, false)) {
                    return null;
                }
            }
            else if (!this.a(itemstack1, 3, 39, false)) {
                return null;
            }

            if (itemstack1.b == 0) {
                slot.c((ItemStack)null);
            }
            else {
                slot.f();
            }

            if (itemstack1.b == itemstack.b) {
                return null;
            }

            slot.a(entityplayer, itemstack1);
        }

        return itemstack;
    }

    public void b(EntityPlayer entityplayer) {
        super.b(entityplayer);
        this.a.a_((EntityPlayer)null);
        super.b(entityplayer);
        if (!this.g.I) {
            ItemStack itemstack = this.f.a_(0);

            if (itemstack != null) {
                entityplayer.b(itemstack);
            }

            itemstack = this.f.a_(1);
            if (itemstack != null) {
                entityplayer.b(itemstack);
            }
        }
    }
}
