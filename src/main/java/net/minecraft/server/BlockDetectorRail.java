package net.minecraft.server;

import net.canarymod.hook.world.RedstoneChangeHook;

import java.util.List;
import java.util.Random;

public class BlockDetectorRail extends BlockRailBase {

    public BlockDetectorRail(int i0) {
        super(i0, true);
        this.b(true);
    }

    public int a(World world) {
        return 20;
    }

    public boolean f() {
        return true;
    }

    public void a(World world, int i0, int i1, int i2, Entity entity) {
        if (!world.I) {
            int i3 = world.h(i0, i1, i2);

            if ((i3 & 8) == 0) {
                this.d(world, i0, i1, i2, i3);
            }
        }
    }

    public void a(World world, int i0, int i1, int i2, Random random) {
        if (!world.I) {
            int i3 = world.h(i0, i1, i2);

            if ((i3 & 8) != 0) {
                this.d(world, i0, i1, i2, i3);
            }
        }
    }

    public int b(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return (iblockaccess.h(i0, i1, i2) & 8) != 0 ? 15 : 0;
    }

    public int c(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return (iblockaccess.h(i0, i1, i2) & 8) == 0 ? 0 : (i3 == 1 ? 15 : 0);
    }

    private void d(World world, int i0, int i1, int i2, int i3) {
        boolean flag0 = (i3 & 8) != 0;
        boolean flag1 = false;
        float f0 = 0.125F;
        List list = world.a(EntityMinecart.class, AxisAlignedBB.a().a((double)((float)i0 + f0), (double)i1, (double)((float)i2 + f0), (double)((float)(i0 + 1) - f0), (double)((float)(i1 + 1) - f0), (double)((float)(i2 + 1) - f0)));

        if (!list.isEmpty()) {
            flag1 = true;
        }

        if (flag1 && !flag0) {
            // CanaryMod: RedstoneChange; Rails on
            RedstoneChangeHook hook = (RedstoneChangeHook)new RedstoneChangeHook(world.getCanaryWorld().getBlockAt(i0, i1, i2), 0, 15).call();
            if (hook.isCanceled()) {
                return;
            }
            //
            world.b(i0, i1, i2, i3 | 8, 3);
            world.f(i0, i1, i2, this.cF);
            world.f(i0, i1 - 1, i2, this.cF);
            world.g(i0, i1, i2, i0, i1, i2);
        }

        if (!flag1 && flag0) {
            // CanaryMod: RedstoneChange; Rails off
            RedstoneChangeHook hook = (RedstoneChangeHook)new RedstoneChangeHook(world.getCanaryWorld().getBlockAt(i0, i1, i2), 15, 0).call();
            if (hook.isCanceled()) {
                return;
            }
            //
            world.b(i0, i1, i2, i3 & 7, 3);
            world.f(i0, i1, i2, this.cF);
            world.f(i0, i1 - 1, i2, this.cF);
            world.g(i0, i1, i2, i0, i1, i2);
        }

        if (flag1) {
            world.a(i0, i1, i2, this.cF, this.a(world));
        }

        world.m(i0, i1, i2, this.cF);
    }

    public void a(World world, int i0, int i1, int i2) {
        super.a(world, i0, i1, i2);
        this.d(world, i0, i1, i2, world.h(i0, i1, i2));
    }

    public boolean q_() {
        return true;
    }

    public int b_(World world, int i0, int i1, int i2, int i3) {
        if ((world.h(i0, i1, i2) & 8) > 0) {
            float f0 = 0.125F;
            List list = world.a(EntityMinecart.class, AxisAlignedBB.a().a((double)((float)i0 + f0), (double)i1, (double)((float)i2 + f0), (double)((float)(i0 + 1) - f0), (double)((float)(i1 + 1) - f0), (double)((float)(i2 + 1) - f0)), IEntitySelector.b);

            if (list.size() > 0) {
                return Container.b((IInventory)list.get(0));
            }
        }

        return 0;
    }
}
