package net.minecraft.server;

import net.canarymod.Canary;
import net.canarymod.hook.world.DispenseHook;

final class DispenserBehaviorFilledBucket extends BehaviorDefaultDispenseItem {

    private final BehaviorDefaultDispenseItem b = new BehaviorDefaultDispenseItem();

    DispenserBehaviorFilledBucket() {}

    public ItemStack b(IBlockSource iblocksource, ItemStack itemstack) {
        ItemBucket itembucket = (ItemBucket) itemstack.b();
        int i0 = iblocksource.d();
        int i1 = iblocksource.e();
        int i2 = iblocksource.f();
        EnumFacing enumfacing = BlockDispenser.j_(iblocksource.h());

        if (itembucket.a(iblocksource.k(), (double) i0, (double) i1, (double) i2, i0 + enumfacing.c(), i1 + enumfacing.d(), i2 + enumfacing.e())) {
            // CanaryMod: Dispense
            DispenseHook hook = new DispenseHook(((TileEntityDispenser) iblocksource.j()).getCanaryDispenser(), null);
            Canary.hooks().callHook(hook);
            if (!hook.isCanceled()) {
                itemstack.c = Item.ax.cp;
                itemstack.a = 1;
            }
            //
            return itemstack;
        } else {
            return this.b.a(iblocksource, itemstack);
        }
    }
}
