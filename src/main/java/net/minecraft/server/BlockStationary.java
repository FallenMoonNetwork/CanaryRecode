package net.minecraft.server;


import java.util.Random;
import net.canarymod.Canary;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.world.IgnitionHook;


public class BlockStationary extends BlockFluid {

    protected BlockStationary(int i0, Material material) {
        super(i0, material);
        this.b(false);
        if (material == Material.i) {
            this.b(true);
        }
    }

    public boolean b(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        return this.cU != Material.i;
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        super.a(world, i0, i1, i2, i3);
        if (world.a(i0, i1, i2) == this.cF) {
            this.k(world, i0, i1, i2);
        }
    }

    private void k(World world, int i0, int i1, int i2) {
        int i3 = world.h(i0, i1, i2);

        world.f(i0, i1, i2, this.cF - 1, i3, 2);
        world.a(i0, i1, i2, this.cF - 1, this.a(world));
    }

    public void a(World world, int i0, int i1, int i2, Random random) {
        if (this.cU == Material.i) {
            int i3 = random.nextInt(3);

            // CanaryMod: Ignition
            CanaryBlock ignited = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2);

            ignited.setStatus((byte) 1); // Lava Status 1
            IgnitionHook hook = new IgnitionHook(ignited, null);

            Canary.hooks().callHook(hook);
            if (hook.isCanceled()) {
                return;
            }
            //

            int i4;
            int i5;

            for (i4 = 0; i4 < i3; ++i4) {
                i0 += random.nextInt(3) - 1;
                ++i1;
                i2 += random.nextInt(3) - 1;
                i5 = world.a(i0, i1, i2);
                if (i5 == 0) {
                    if (this.m(world, i0 - 1, i1, i2)
                            || this.m(world, i0 + 1, i1, i2)
                            || this.m(world, i0, i1, i2 - 1)
                            || this.m(world, i0, i1, i2 + 1)
                            || this.m(world, i0, i1 - 1, i2)
                            || this.m(world, i0, i1 + 1, i2)) {
                        world.c(i0, i1, i2, Block.aw.cF);
                        return;
                    }
                } else if (Block.s[i5].cU.c()) {
                    return;
                }
            }

            if (i3 == 0) {
                i4 = i0;
                i5 = i2;

                for (int i6 = 0; i6 < 3; ++i6) {
                    i0 = i4 + random.nextInt(3) - 1;
                    i2 = i5 + random.nextInt(3) - 1;
                    if (world.c(i0, i1 + 1, i2) && this.m(world, i0, i1, i2)) {
                        world.c(i0, i1 + 1, i2, Block.aw.cF);
                    }
                }
            }
        }
    }

    private boolean m(World world, int i0, int i1, int i2) {
        return world.g(i0, i1, i2).h();
    }
}
