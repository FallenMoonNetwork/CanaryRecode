package net.minecraft.server;

import net.canarymod.hook.world.DispenseHook;

final class DispenserBehaviorFilledBucket extends BehaviorDefaultDispenseItem {

    private final BehaviorDefaultDispenseItem b = new BehaviorDefaultDispenseItem();

    DispenserBehaviorFilledBucket() {}

    public ItemStack b(IBlockSource iblocksource, ItemStack itemstack) {
        ItemBucket itembucket = (ItemBucket) itemstack.b();
        int i0 = iblocksource.d();
        int i1 = iblocksource.e();
        int i2 = iblocksource.f();
        EnumFacing enumfacing = BlockDispenser.l_(iblocksource.h());

        if (itembucket.a(iblocksource.k(), i0 + enumfacing.c(), i1 + enumfacing.d(), i2 + enumfacing.e())) {
            // CanaryMod: Dispense
            DispenseHook hook = (DispenseHook) new DispenseHook(((TileEntityDispenser) iblocksource.j()).getCanaryDispenser(), null).call();
            if (!hook.isCanceled()) {
                itemstack.d = Item.ay.cv;
                itemstack.b = 1;
            }
            //
            return itemstack;
        } else {
            return this.b.a(iblocksource, itemstack);
        }
    }
}
