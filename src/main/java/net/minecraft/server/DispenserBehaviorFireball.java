package net.minecraft.server;

import net.canarymod.hook.world.DispenseHook;

import java.util.Random;

final class DispenserBehaviorFireball extends BehaviorDefaultDispenseItem {

    DispenserBehaviorFireball() {
    }

    public ItemStack b(IBlockSource iblocksource, ItemStack itemstack) {
        EnumFacing enumfacing = BlockDispenser.l_(iblocksource.h());
        IPosition iposition = BlockDispenser.a(iblocksource);
        double d0 = iposition.a() + (double)((float)enumfacing.c() * 0.3F);
        double d1 = iposition.b() + (double)((float)enumfacing.c() * 0.3F);
        double d2 = iposition.c() + (double)((float)enumfacing.e() * 0.3F);
        World world = iblocksource.k();
        Random random = world.s;
        double d3 = random.nextGaussian() * 0.05D + (double)enumfacing.c();
        double d4 = random.nextGaussian() * 0.05D + (double)enumfacing.d();
        double d5 = random.nextGaussian() * 0.05D + (double)enumfacing.e();

        // CanaryMod: Dispense
        Entity fireball = new EntitySmallFireball(world, d0, d1, d2, d3, d4, d5);
        DispenseHook hook = (DispenseHook)new DispenseHook(((TileEntityDispenser)iblocksource.j()).getCanaryDispenser(), fireball.getCanaryEntity()).call();
        if (!hook.isCanceled()) {
            world.d(fireball);
            itemstack.a(1);
        }
        //
        return itemstack;
    }

    protected void a(IBlockSource iblocksource) {
        iblocksource.k().e(1009, iblocksource.d(), iblocksource.e(), iblocksource.f(), 0);
    }
}
