package net.minecraft.server;

import net.canarymod.hook.world.DispenseHook;

final class DispenserBehaviorMobEgg extends BehaviorDefaultDispenseItem {

    DispenserBehaviorMobEgg() {
    }

    public ItemStack b(IBlockSource iblocksource, ItemStack itemstack) {
        EnumFacing enumfacing = BlockDispenser.l_(iblocksource.h());
        double d0 = iblocksource.a() + (double)enumfacing.c();
        double d1 = (double)((float)iblocksource.e() + 0.2F);
        double d2 = iblocksource.c() + (double)enumfacing.e();
        Entity entity = ItemMonsterPlacer.a(iblocksource.k(), itemstack.k(), d0, d1, d2, false); // Disallow spawn

        if (entity instanceof EntityLivingBase && itemstack.u()) {
            ((EntityLiving)entity).a(itemstack.s());
        }
        // CanaryMod: Dispense
        DispenseHook hook = (DispenseHook)new DispenseHook(((TileEntityDispenser)iblocksource.j()).getCanaryDispenser(), entity.getCanaryEntity()).call();
        if (!hook.isCanceled()) {
            iblocksource.k().d(entity);
            itemstack.a(1);
        }
        //
        return itemstack;
    }
}
