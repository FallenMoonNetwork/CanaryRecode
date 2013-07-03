package net.minecraft.server;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.canarymod.Canary;
import net.canarymod.hook.world.RedstoneChangeHook;


public class BlockRedstoneTorch extends BlockTorch {

    private boolean a;
    private static Map b = new HashMap();

    private boolean a(World world, int i0, int i1, int i2, boolean flag0) {
        if (!b.containsKey(world)) {
            b.put(world, new ArrayList());
        }

        List list = (List) b.get(world);

        if (flag0) {
            list.add(new RedstoneUpdateInfo(i0, i1, i2, world.I()));
        }

        int i3 = 0;

        for (int i4 = 0; i4 < list.size(); ++i4) {
            RedstoneUpdateInfo redstoneupdateinfo = (RedstoneUpdateInfo) list.get(i4);

            if (redstoneupdateinfo.a == i0 && redstoneupdateinfo.b == i1 && redstoneupdateinfo.c == i2) {
                ++i3;
                if (i3 >= 8) {
                    return true;
                }
            }
        }

        return false;
    }

    protected BlockRedstoneTorch(int i0, boolean flag0) {
        super(i0);
        this.a = flag0;
        this.b(true);
        this.a((CreativeTabs) null);
    }

    public int a(World world) {
        return 2;
    }

    public void a(World world, int i0, int i1, int i2) {
        if (world.h(i0, i1, i2) == 0) {
            super.a(world, i0, i1, i2);
        }

        if (this.a) {
            world.f(i0, i1 - 1, i2, this.cF);
            world.f(i0, i1 + 1, i2, this.cF);
            world.f(i0 - 1, i1, i2, this.cF);
            world.f(i0 + 1, i1, i2, this.cF);
            world.f(i0, i1, i2 - 1, this.cF);
            world.f(i0, i1, i2 + 1, this.cF);
        }
    }

    public void a(World world, int i0, int i1, int i2, int i3, int i4) {
        if (this.a) {
            world.f(i0, i1 - 1, i2, this.cF);
            world.f(i0, i1 + 1, i2, this.cF);
            world.f(i0 - 1, i1, i2, this.cF);
            world.f(i0 + 1, i1, i2, this.cF);
            world.f(i0, i1, i2 - 1, this.cF);
            world.f(i0, i1, i2 + 1, this.cF);
        }
    }

    public int b(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        if (!this.a) {
            return 0;
        } else {
            int i4 = iblockaccess.h(i0, i1, i2);

            return i4 == 5 && i3 == 1 ? 0 : (i4 == 3 && i3 == 3 ? 0 : (i4 == 4 && i3 == 2 ? 0 : (i4 == 1 && i3 == 5 ? 0 : (i4 == 2 && i3 == 4 ? 0 : 15))));
        }
    }

    private boolean m(World world, int i0, int i1, int i2) {
        int i3 = world.h(i0, i1, i2);

        return i3 == 5 && world.k(i0, i1 - 1, i2, 0) ? true : (i3 == 3 && world.k(i0, i1, i2 - 1, 2) ? true : (i3 == 4 && world.k(i0, i1, i2 + 1, 3) ? true : (i3 == 1 && world.k(i0 - 1, i1, i2, 4) ? true : i3 == 2 && world.k(i0 + 1, i1, i2, 5))));
    }

    public void a(World world, int i0, int i1, int i2, Random random) {
        boolean flag0 = this.m(world, i0, i1, i2);
        List list = (List) b.get(world);

        while (list != null && !list.isEmpty() && world.I() - ((RedstoneUpdateInfo) list.get(0)).d > 60L) {
            list.remove(0);
        }

        if (this.a) {
            if (flag0) {
                world.f(i0, i1, i2, Block.aU.cF, world.h(i0, i1, i2), 3);

                // CanaryMod: RedstoneChange
                RedstoneChangeHook hook = new RedstoneChangeHook(world.getCanaryWorld().getBlockAt(i0, i1, i2), 1, 0);

                Canary.hooks().callHook(hook);
                if (hook.isCanceled()) {
                    return;
                }
                //
                if (this.a(world, i0, i1, i2, true)) {
                    world.a((double) ((float) i0 + 0.5F),
                            (double) ((float) i1 + 0.5F),
                            (double) ((float) i2 + 0.5F),
                            "random.fizz",
                            0.5F,
                            2.6F + (world.s.nextFloat() - world.s.nextFloat()) * 0.8F);

                    for (int i3 = 0; i3 < 5; ++i3) {
                        double d0 = (double) i0 + random.nextDouble() * 0.6D + 0.2D;
                        double d1 = (double) i1 + random.nextDouble() * 0.6D + 0.2D;
                        double d2 = (double) i2 + random.nextDouble() * 0.6D + 0.2D;

                        world.a("smoke", d0, d1, d2, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        } else if (!flag0 && !this.a(world, i0, i1, i2, false)) {
            world.f(i0, i1, i2, Block.aV.cF, world.h(i0, i1, i2), 3);
        }
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        if (!this.d(world, i0, i1, i2, i3)) {
            boolean flag0 = this.m(world, i0, i1, i2);

            if (this.a && flag0 || !this.a && !flag0) {
                world.a(i0, i1, i2, this.cF, this.a(world));
            }
        }
    }

    public int c(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return i3 == 0 ? this.b(iblockaccess, i0, i1, i2, i3) : 0;
    }

    public int a(int i0, Random random, int i1) {
        return Block.aV.cF;
    }

    public boolean f() {
        return true;
    }

    public boolean i(int i0) {
        return i0 == Block.aU.cF || i0 == Block.aV.cF;
    }
}
