package net.minecraft.server;

import net.canarymod.hook.world.DispenseHook;

public class BehaviorDefaultDispenseItem implements IBehaviorDispenseItem {

    public BehaviorDefaultDispenseItem() {
    }

    public final ItemStack a(IBlockSource iblocksource, ItemStack itemstack) {
        ItemStack itemstack1 = this.b(iblocksource, itemstack);

        this.a(iblocksource);
        this.a(iblocksource, BlockDispenser.l_(iblocksource.h()));
        return itemstack1;
    }

    protected ItemStack b(IBlockSource iblocksource, ItemStack itemstack) {
        EnumFacing enumfacing = BlockDispenser.l_(iblocksource.h());
        IPosition iposition = BlockDispenser.a(iblocksource);

        // CanaryMod: Dispense
        EntityItem temp = new EntityItem(iblocksource.k(), iposition.a(), iposition.b() - 0.3D, iposition.c(), itemstack);
        DispenseHook hook = (DispenseHook)new DispenseHook(((TileEntityDispenser)iblocksource.j()).getCanaryDispenser(), temp.getCanaryEntity()).call();
        if (!hook.isCanceled()) {
            ItemStack itemstack1 = itemstack.a(1);

            a(iblocksource.k(), itemstack1, 6, enumfacing, iposition);
        }
        temp.x(); // Clear the temp EntityItem
        //
        return itemstack;
    }

    public static void a(World world, ItemStack itemstack, int i0, EnumFacing enumfacing, IPosition iposition) {
        double d0 = iposition.a();
        double d1 = iposition.b();
        double d2 = iposition.c();
        EntityItem entityitem = new EntityItem(world, d0, d1 - 0.3D, d2, itemstack);
        double d3 = world.s.nextDouble() * 0.1D + 0.2D;

        entityitem.x = (double)enumfacing.c() * d3;
        entityitem.y = 0.20000000298023224D;
        entityitem.z = (double)enumfacing.e() * d3;
        entityitem.x += world.s.nextGaussian() * 0.007499999832361937D * (double)i0;
        entityitem.y += world.s.nextGaussian() * 0.007499999832361937D * (double)i0;
        entityitem.z += world.s.nextGaussian() * 0.007499999832361937D * (double)i0;
        world.d((Entity)entityitem);
    }

    protected void a(IBlockSource iblocksource) {
        iblocksource.k().e(1000, iblocksource.d(), iblocksource.e(), iblocksource.f(), 0);
    }

    protected void a(IBlockSource iblocksource, EnumFacing enumfacing) {
        iblocksource.k().e(2000, iblocksource.d(), iblocksource.e(), iblocksource.f(), this.a(enumfacing));
    }

    private int a(EnumFacing enumfacing) {
        return enumfacing.c() + 1 + (enumfacing.e() + 1) * 3;
    }
}
