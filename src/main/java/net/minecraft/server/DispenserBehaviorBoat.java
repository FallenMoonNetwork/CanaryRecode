package net.minecraft.server;


import net.canarymod.Canary;
import net.canarymod.hook.world.DispenseHook;


final class DispenserBehaviorBoat extends BehaviorDefaultDispenseItem {

    private final BehaviorDefaultDispenseItem b = new BehaviorDefaultDispenseItem();

    DispenserBehaviorBoat() {}

    public ItemStack b(IBlockSource iblocksource, ItemStack itemstack) {
        EnumFacing enumfacing = BlockDispenser.l_(iblocksource.h());
        World world = iblocksource.k();
        double d0 = iblocksource.a() + (double) ((float) enumfacing.c() * 1.125F);
        double d1 = iblocksource.b() + (double) ((float) enumfacing.d() * 1.125F);
        double d2 = iblocksource.c() + (double) ((float) enumfacing.e() * 1.125F);
        int i0 = iblocksource.d() + enumfacing.c();
        int i1 = iblocksource.e() + enumfacing.d();
        int i2 = iblocksource.f() + enumfacing.e();
        Material material = world.g(i0, i1, i2);
        double d3;

        if (Material.h.equals(material)) {
            d3 = 1.0D;
        } else {
            if (!Material.a.equals(material) || !Material.h.equals(world.g(i0, i1 - 1, i2))) {
                return this.b.a(iblocksource, itemstack);
            }

            d3 = 0.0D;
        }

        EntityBoat entityboat = new EntityBoat(world, d0, d1 + d3, d2);
        // CanaryMod: Dispense
        DispenseHook hook = new DispenseHook(((TileEntityDispenser) iblocksource.j()).getCanaryDispenser(), entityboat.getCanaryEntity());

        Canary.hooks().callHook(hook);
        if (!hook.isCanceled()) {
            world.d((Entity) entityboat);
            itemstack.a(1);
        }
        //
        return itemstack;
    }

    protected void a(IBlockSource iblocksource) {
        iblocksource.k().e(1000, iblocksource.d(), iblocksource.e(), iblocksource.f(), 0);
    }
}
