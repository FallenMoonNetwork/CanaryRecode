package net.minecraft.server;

import net.canarymod.hook.world.BlockPhysicsHook;

import java.util.Random;

public class BlockSand extends Block {

    public static boolean c;

    public BlockSand(int i0) {
        super(i0, Material.p);
        this.a(CreativeTabs.b);
    }

    public BlockSand(int i0, Material material) {
        super(i0, material);
    }

    public void a(World world, int i0, int i1, int i2) {
        // CanaryMod: BlockPhysics
        if (world.getCanaryWorld() != null) {
            BlockPhysicsHook hook = (BlockPhysicsHook)new BlockPhysicsHook(world.getCanaryWorld().getBlockAt(i0, i1, i2), true).call();
            if (hook.isCanceled()) {
                return;
            }
        }
        //
        world.a(i0, i1, i2, this.cF, this.a(world));
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        // CanaryMod: BlockPhysics
        BlockPhysicsHook hook = (BlockPhysicsHook)new BlockPhysicsHook(world.getCanaryWorld().getBlockAt(i0, i1, i2), false).call();
        if (hook.isCanceled()) {
            return;
        }
        //
        world.a(i0, i1, i2, this.cF, this.a(world));
    }

    public void a(World world, int i0, int i1, int i2, Random random) {
        if (!world.I) {
            this.k(world, i0, i1, i2);
        }
    }

    private void k(World world, int i0, int i1, int i2) {
        if (a_(world, i0, i1 - 1, i2) && i1 >= 0) {
            byte b0 = 32;

            if (!c && world.e(i0 - b0, i1 - b0, i2 - b0, i0 + b0, i1 + b0, i2 + b0)) {
                if (!world.I) {
                    EntityFallingSand entityfallingsand = new EntityFallingSand(
                            world, (double)((float)i0 + 0.5F),
                            (double)((float)i1 + 0.5F),
                            (double)((float)i2 + 0.5F), this.cF, world.h(i0,
                            i1, i2));

                    this.a(entityfallingsand);
                    world.d((Entity)entityfallingsand);
                }
            }
            else {
                world.i(i0, i1, i2);

                while (a_(world, i0, i1 - 1, i2) && i1 > 0) {
                    --i1;
                }

                if (i1 > 0) {
                    world.c(i0, i1, i2, this.cF);
                }
            }
        }
    }

    protected void a(EntityFallingSand entityfallingsand) {
    }

    public int a(World world) {
        return 2;
    }

    public static boolean a_(World world, int i0, int i1, int i2) {
        int i3 = world.a(i0, i1, i2);

        if (i3 == 0) {
            return true;
        }
        else if (i3 == Block.aw.cF) {
            return true;
        }
        else {
            Material material = Block.s[i3].cU;

            return material == Material.h ? true : material == Material.i;
        }
    }

    public void a_(World world, int i0, int i1, int i2, int i3) {
    }
}
