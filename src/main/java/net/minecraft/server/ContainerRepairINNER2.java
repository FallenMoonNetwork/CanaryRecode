package net.minecraft.server;

public class ContainerRepairINNER2 extends Slot { // CanaryMod: package => public

    final World a;

    final int b;

    final int c;

    final int d;

    final ContainerRepair e;

    ContainerRepairINNER2(ContainerRepair containerrepair, IInventory iinventory, int i0, int i1, int i2, World world, int i3, int i4, int i5) {
        super(iinventory, i0, i1, i2);
        this.e = containerrepair;
        this.a = world;
        this.b = i3;
        this.c = i4;
        this.d = i5;
    }

    public boolean a(ItemStack itemstack) {
        return false;
    }

    public boolean a(EntityPlayer entityplayer) {
        return (entityplayer.bG.d || entityplayer.bH >= this.e.a) && this.e.a > 0 && this.e();
    }

    public void a(EntityPlayer entityplayer, ItemStack itemstack) {
        if (!entityplayer.bG.d) {
            entityplayer.a(-this.e.a);
        }

        ContainerRepair.a(this.e).a(0, (ItemStack)null);
        if (ContainerRepair.b(this.e) > 0) {
            ItemStack itemstack1 = ContainerRepair.a(this.e).a(1);

            if (itemstack1 != null && itemstack1.b > ContainerRepair.b(this.e)) {
                itemstack1.b -= ContainerRepair.b(this.e);
                ContainerRepair.a(this.e).a(1, itemstack1);
            }
            else {
                ContainerRepair.a(this.e).a(1, (ItemStack)null);
            }
        }
        else {
            ContainerRepair.a(this.e).a(1, (ItemStack)null);
        }

        this.e.a = 0;
        if (!entityplayer.bG.d && !this.a.I && this.a.a(this.b, this.c, this.d) == Block.cm.cF && entityplayer.aD().nextFloat() < 0.12F) {
            int i0 = this.a.h(this.b, this.c, this.d);
            int i1 = i0 & 3;
            int i2 = i0 >> 2;

            ++i2;
            if (i2 > 2) {
                this.a.i(this.b, this.c, this.d);
                this.a.e(1020, this.b, this.c, this.d, 0);
            }
            else {
                this.a.b(this.b, this.c, this.d, i1 | i2 << 2, 2);
                this.a.e(1021, this.b, this.c, this.d, 0);
            }
        }
        else if (!this.a.I) {
            this.a.e(1021, this.b, this.c, this.d, 0);
        }
    }
}
