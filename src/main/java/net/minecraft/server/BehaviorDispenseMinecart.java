package net.minecraft.server;

import net.canarymod.hook.world.DispenseHook;

final class BehaviorDispenseMinecart extends BehaviorDefaultDispenseItem {

    private final BehaviorDefaultDispenseItem b = new BehaviorDefaultDispenseItem();

    BehaviorDispenseMinecart() {}

    public ItemStack b(IBlockSource iblocksource, ItemStack itemstack) {
        EnumFacing enumfacing = BlockDispenser.l_(iblocksource.h());
        World world = iblocksource.k();
        double d0 = iblocksource.a() + (double) ((float) enumfacing.c() * 1.125F);
        double d1 = iblocksource.b() + (double) ((float) enumfacing.d() * 1.125F);
        double d2 = iblocksource.c() + (double) ((float) enumfacing.e() * 1.125F);
        int i0 = iblocksource.d() + enumfacing.c();
        int i1 = iblocksource.e() + enumfacing.d();
        int i2 = iblocksource.f() + enumfacing.e();
        int i3 = world.a(i0, i1, i2);
        double d3;

        if (BlockRailBase.e_(i3)) {
            d3 = 0.0D;
        } else {
            if (i3 != 0 || !BlockRailBase.e_(world.a(i0, i1 - 1, i2))) {
                return this.b.a(iblocksource, itemstack);
            }

            d3 = -1.0D;
        }

        EntityMinecart entityminecart = EntityMinecart.a(world, d0, d1 + d3, d2, ((ItemMinecart) itemstack.b()).a);
        // MERGE: This seems to be new - Chris
        if (itemstack.u()) {
            entityminecart.a(itemstack.s());
        }
        // CanaryMod: Dispense
        DispenseHook hook = (DispenseHook) new DispenseHook(((TileEntityDispenser) iblocksource.j()).getCanaryDispenser(), entityminecart.getCanaryEntity()).call();
        if (!hook.isCanceled()) {
            world.d((Entity) entityminecart);
            itemstack.a(1);
        }
        //
        return itemstack;
    }

    protected void a(IBlockSource iblocksource) {
        iblocksource.k().e(1000, iblocksource.d(), iblocksource.e(), iblocksource.f(), 0);
    }
}
