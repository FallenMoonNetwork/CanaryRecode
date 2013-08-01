package net.minecraft.server;

import java.util.Random;
import net.canarymod.hook.world.TreeGrowHook;

public class BlockSapling extends BlockFlower {

    public static final String[] a = new String[]{ "oak", "spruce", "birch", "jungle" };
    private static final String[] b = new String[]{ "sapling", "sapling_spruce", "sapling_birch", "sapling_jungle" };

    protected BlockSapling(int i0) {
        super(i0);
        float f0 = 0.4F;

        this.a(0.5F - f0, 0.0F, 0.5F - f0, 0.5F + f0, f0 * 2.0F, 0.5F + f0);
        this.a(CreativeTabs.c);
    }

    public void a(World world, int i0, int i1, int i2, Random random) {
        if (!world.I) {
            super.a(world, i0, i1, i2, random);
            if (world.n(i0, i1 + 1, i2) >= 9 && random.nextInt(7) == 0) {
                this.c(world, i0, i1, i2, random);
            }
        }
    }

    public void c(World world, int i0, int i1, int i2, Random random) {
        int i3 = world.h(i0, i1, i2);

        if ((i3 & 8) == 0) {
            world.b(i0, i1, i2, i3 | 8, 4);
        } else {
            // CanaryMod: TreeGrow; If someone figures out how to get more information into this, let me know - darkdiplomat;
            TreeGrowHook hook = (TreeGrowHook) new TreeGrowHook(world.getCanaryWorld().getBlockAt(i0, i1, i2)).call();
            if (!hook.isCanceled()) {
                this.d(world, i0, i1, i2, random);
            }
            //
        }
    }

    public void d(World world, int i0, int i1, int i2, Random random) {
        int i3 = world.h(i0, i1, i2) & 3;
        Object object = null;
        int i4 = 0;
        int i5 = 0;
        boolean flag0 = false;

        if (i3 == 1) {
            object = new WorldGenTaiga2(true);
        } else if (i3 == 2) {
            object = new WorldGenForest(true);
        } else if (i3 == 3) {
            for (i4 = 0; i4 >= -1; --i4) {
                for (i5 = 0; i5 >= -1; --i5) {
                    if (this.d(world, i0 + i4, i1, i2 + i5, 3) && this.d(world, i0 + i4 + 1, i1, i2 + i5, 3) && this.d(world, i0 + i4, i1, i2 + i5 + 1, 3) && this.d(world, i0 + i4 + 1, i1, i2 + i5 + 1, 3)) {
                        object = new WorldGenHugeTrees(true, 10 + random.nextInt(20), 3, 3);
                        flag0 = true;
                        break;
                    }
                }

                if (object != null) {
                    break;
                }
            }

            if (object == null) {
                i5 = 0;
                i4 = 0;
                object = new WorldGenTrees(true, 4 + random.nextInt(7), 3, 3, false);
            }
        } else {
            object = new WorldGenTrees(true);
            if (random.nextInt(10) == 0) {
                object = new WorldGenBigTree(true);
            }
        }

        if (flag0) {
            world.f(i0 + i4, i1, i2 + i5, 0, 0, 4);
            world.f(i0 + i4 + 1, i1, i2 + i5, 0, 0, 4);
            world.f(i0 + i4, i1, i2 + i5 + 1, 0, 0, 4);
            world.f(i0 + i4 + 1, i1, i2 + i5 + 1, 0, 0, 4);
        } else {
            world.f(i0, i1, i2, 0, 0, 4);
        }

        if (!((WorldGenerator) object).a(world, random, i0 + i4, i1, i2 + i5)) {
            if (flag0) {
                world.f(i0 + i4, i1, i2 + i5, this.cF, i3, 4);
                world.f(i0 + i4 + 1, i1, i2 + i5, this.cF, i3, 4);
                world.f(i0 + i4, i1, i2 + i5 + 1, this.cF, i3, 4);
                world.f(i0 + i4 + 1, i1, i2 + i5 + 1, this.cF, i3, 4);
            } else {
                world.f(i0, i1, i2, this.cF, i3, 4);
            }
        }
    }

    public boolean d(World world, int i0, int i1, int i2, int i3) {
        return world.a(i0, i1, i2) == this.cF && (world.h(i0, i1, i2) & 3) == i3;
    }

    public int a(int i0) {
        return i0 & 3;
    }
}
