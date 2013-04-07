package net.minecraft.server;


import net.canarymod.Canary;
import net.canarymod.hook.world.DispenseHook;


final class DispenserBehaviorFireworks extends BehaviorDefaultDispenseItem {

    DispenserBehaviorFireworks() {}

    public ItemStack b(IBlockSource iblocksource, ItemStack itemstack) {
        EnumFacing enumfacing = BlockDispenser.j_(iblocksource.h());
        double d0 = iblocksource.a() + (double) enumfacing.c();
        double d1 = (double) ((float) iblocksource.e() + 0.2F);
        double d2 = iblocksource.c() + (double) enumfacing.e();
        EntityFireworkRocket entityfireworkrocket = new EntityFireworkRocket(iblocksource.k(), d0, d1, d2, itemstack);
        // CanaryMod: Dispense
        DispenseHook hook = new DispenseHook(((TileEntityDispenser) iblocksource.j()).getCanaryDispenser(), entityfireworkrocket.getCanaryEntity());

        Canary.hooks().callHook(hook);
        if (!hook.isCanceled()) {
            iblocksource.k().d((Entity) entityfireworkrocket);
            itemstack.a(1);
        }
        //
        return itemstack;
    }

    protected void a(IBlockSource iblocksource) {
        iblocksource.k().e(1002, iblocksource.d(), iblocksource.e(), iblocksource.f(), 0);
    }
}
