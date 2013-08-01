package net.minecraft.server;

import net.canarymod.hook.world.DispenseHook;

final class DispenserBehaviorEmptyBucket extends BehaviorDefaultDispenseItem {

    private final BehaviorDefaultDispenseItem b = new BehaviorDefaultDispenseItem();

    DispenserBehaviorEmptyBucket() {}

    public ItemStack b(IBlockSource iblocksource, ItemStack itemstack) {
        EnumFacing enumfacing = BlockDispenser.l_(iblocksource.h());
        World world = iblocksource.k();
        int i0 = iblocksource.d() + enumfacing.c();
        int i1 = iblocksource.e() + enumfacing.d();
        int i2 = iblocksource.f() + enumfacing.e();
        Material material = world.g(i0, i1, i2);
        int i3 = world.h(i0, i1, i2);
        Item item;

        if (Material.h.equals(material) && i3 == 0) {
            item = Item.az;
        } else {
            if (!Material.i.equals(material) || i3 != 0) {
                return super.b(iblocksource, itemstack);
            }

            item = Item.aA;
        }

        world.i(i0, i1, i2);
        if (--itemstack.b == 0) {
            // CanaryMod: Dispense
            DispenseHook hook = (DispenseHook) new DispenseHook(((TileEntityDispenser) iblocksource.j()).getCanaryDispenser(), null).call();
            if (!hook.isCanceled()) {
                itemstack.d = item.cv;
                itemstack.b = 1;
            }
            //
        } else if (((TileEntityDispenser) iblocksource.j()).a(new ItemStack(item)) < 0) {
            this.b.a(iblocksource, new ItemStack(item));
        }

        return itemstack;
    }
}
