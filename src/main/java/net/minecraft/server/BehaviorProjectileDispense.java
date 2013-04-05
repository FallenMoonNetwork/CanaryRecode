package net.minecraft.server;

import net.canarymod.Canary;
import net.canarymod.hook.world.DispenseHook;

public abstract class BehaviorProjectileDispense extends BehaviorDefaultDispenseItem {

    public BehaviorProjectileDispense() {}

    public ItemStack b(IBlockSource iblocksource, ItemStack itemstack) {
        World world = iblocksource.k();
        IPosition iposition = BlockDispenser.a(iblocksource);
        EnumFacing enumfacing = BlockDispenser.j_(iblocksource.h());
        IProjectile iprojectile = this.a(world, iposition);

        iprojectile.c((double) enumfacing.c(), (double) ((float) enumfacing.d() + 0.1F), (double) enumfacing.e(), this.b(), this.a());
        // CanaryMod: Dispense
        DispenseHook hook = new DispenseHook(((TileEntityDispenser) iblocksource.j()).getCanaryDispenser(), ((Entity) iprojectile).getCanaryEntity());
        Canary.hooks().callHook(hook);
        if (!hook.isCanceled()) {
            world.d((Entity) iprojectile);
            itemstack.a(1);
        }
        //
        return itemstack;
    }

    protected void a(IBlockSource iblocksource) {
        iblocksource.k().e(1002, iblocksource.d(), iblocksource.e(), iblocksource.f(), 0);
    }

    protected abstract IProjectile a(World world, IPosition iposition);

    protected float a() {
        return 6.0F;
    }

    protected float b() {
        return 1.1F;
    }
}
