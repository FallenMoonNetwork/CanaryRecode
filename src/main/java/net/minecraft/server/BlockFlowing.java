package net.minecraft.server;

import java.util.Random;
import net.canarymod.Canary;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.world.FlowHook;
import net.canarymod.hook.world.LiquidDestroyHook;

public class BlockFlowing extends BlockFluid {

    int a = 0;
    boolean[] b = new boolean[4];
    int[] c = new int[4];

    protected BlockFlowing(int i0, Material material) {
        super(i0, material);
    }

    private void k(World world, int i0, int i1, int i2) {
        int i3 = world.h(i0, i1, i2);

        world.f(i0, i1, i2, this.cz + 1, i3, 2);
    }

    public boolean b(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        return this.cO != Material.i;
    }

    public void a(World world, int i0, int i1, int i2, Random random) {

        // CanaryMod: Flow from
        CanaryBlock from = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2);
        //

        int i3 = this.k_(world, i0, i1, i2);
        byte b0 = 1;

        if (this.cO == Material.i && !world.t.e) {
            b0 = 2;
        }

        boolean flag0 = true;
        int i4;

        if (i3 > 0) {
            byte b1 = -100;

            this.a = 0;
            int i5 = this.d(world, i0 - 1, i1, i2, b1);

            i5 = this.d(world, i0 + 1, i1, i2, i5);
            i5 = this.d(world, i0, i1, i2 - 1, i5);
            i5 = this.d(world, i0, i1, i2 + 1, i5);
            i4 = i5 + b0;
            if (i4 >= 8 || i5 < 0) {
                i4 = -1;
            }

            if (this.k_(world, i0, i1 + 1, i2) >= 0) {
                int i6 = this.k_(world, i0, i1 + 1, i2);

                if (i6 >= 8) {
                    i4 = i6;
                } else {
                    i4 = i6 + 8;
                }
            }

            if (this.a >= 2 && this.cO == Material.h) {
                if (world.g(i0, i1 - 1, i2).a()) {
                    i4 = 0;
                } else if (world.g(i0, i1 - 1, i2) == this.cO && world.h(i0, i1 - 1, i2) == 0) {
                    i4 = 0;
                }
            }

            if (this.cO == Material.i && i3 < 8 && i4 < 8 && i4 > i3 && random.nextInt(4) != 0) {
                i4 = i3;
                flag0 = false;
            }

            if (i4 == i3) {
                if (flag0) {
                    this.k(world, i0, i1, i2);
                }
            } else {
                i3 = i4;
                if (i4 < 0) {
                    world.i(i0, i1, i2);
                } else {
                    world.b(i0, i1, i2, i4, 2);
                    world.a(i0, i1, i2, this.cz, this.a(world));
                    world.f(i0, i1, i2, this.cz);
                }
            }
        } else {
            this.k(world, i0, i1, i2);
        }

        if (this.o(world, i0, i1 - 1, i2)) {
            if (this.cO == Material.i && world.g(i0, i1 - 1, i2) == Material.h) {
                world.c(i0, i1 - 1, i2, Block.x.cz);
                this.j(world, i0, i1 - 1, i2);
                return;
            }

            // CanaryMod: Flow (down)
            CanaryBlock to = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1 - 1, i2);
            FlowHook hook = new FlowHook(from, to);
            Canary.hooks().callHook(hook);
            if (!hook.isCanceled()) {
                if (i3 >= 8) {
                    this.e(world, i0, i1 - 1, i2, i3);
                } else {
                    this.e(world, i0, i1 - 1, i2, i3 + 8);
                }
            }
            //
        } else if (i3 >= 0 && (i3 == 0 || this.n(world, i0, i1 - 1, i2))) {
            boolean[] aboolean = this.m(world, i0, i1, i2);

            i4 = i3 + b0;
            if (i3 >= 8) {
                i4 = 1;
            }

            if (i4 >= 8) {
                return;
            }

            if (aboolean[0]) {
                // CanaryMod: Flow
                CanaryBlock to = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0 - 1, i1, i2);
                FlowHook hook = new FlowHook(from, to);
                Canary.hooks().callHook(hook);
                if (!hook.isCanceled()) {
                    this.e(world, i0 - 1, i1, i2, i4);
                }
                //
            }

            if (aboolean[1]) {
                // CanaryMod: Flow
                CanaryBlock to = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0 + 1, i1, i2);
                FlowHook hook = new FlowHook(from, to);
                Canary.hooks().callHook(hook);
                if (!hook.isCanceled()) {
                    this.e(world, i0 + 1, i1, i2, i4);
                }
                //
            }

            if (aboolean[2]) {
                // CanaryMod: Flow
                CanaryBlock to = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2 - 1);
                FlowHook hook = new FlowHook(from, to);
                Canary.hooks().callHook(hook);
                if (!hook.isCanceled()) {
                    this.e(world, i0, i1, i2 - 1, i4);
                }
                //
            }

            if (aboolean[3]) {
                // CanaryMod: Flow
                CanaryBlock to = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2 + 1);
                FlowHook hook = new FlowHook(from, to);
                Canary.hooks().callHook(hook);
                if (!hook.isCanceled()) {
                    this.e(world, i0, i1, i2 + 1, i4);
                }
                //
            }
        }
    }

    private void e(World world, int i0, int i1, int i2, int i3) {
        if (this.o(world, i0, i1, i2)) {
            int i4 = world.a(i0, i1, i2);

            if (i4 > 0) {
                if (this.cO == Material.i) {
                    this.j(world, i0, i1, i2);
                } else {
                    Block.r[i4].c(world, i0, i1, i2, world.h(i0, i1, i2), 0);
                }
            }

            world.f(i0, i1, i2, this.cz, i3, 3);
        }
    }

    private int d(World world, int i0, int i1, int i2, int i3, int i4) {
        int i5 = 1000;

        for (int i6 = 0; i6 < 4; ++i6) {
            if ((i6 != 0 || i4 != 1) && (i6 != 1 || i4 != 0) && (i6 != 2 || i4 != 3) && (i6 != 3 || i4 != 2)) {
                int i7 = i0;
                int i8 = i2;

                if (i6 == 0) {
                    i7 = i0 - 1;
                }

                if (i6 == 1) {
                    ++i7;
                }

                if (i6 == 2) {
                    i8 = i2 - 1;
                }

                if (i6 == 3) {
                    ++i8;
                }

                if (!this.n(world, i7, i1, i8) && (world.g(i7, i1, i8) != this.cO || world.h(i7, i1, i8) != 0)) {
                    if (!this.n(world, i7, i1 - 1, i8)) {
                        return i3;
                    }

                    if (i3 < 4) {
                        int i9 = this.d(world, i7, i1, i8, i3 + 1, i6);

                        if (i9 < i5) {
                            i5 = i9;
                        }
                    }
                }
            }
        }

        return i5;
    }

    private boolean[] m(World world, int i0, int i1, int i2) {
        int i3;
        int i4;

        for (i3 = 0; i3 < 4; ++i3) {
            this.c[i3] = 1000;
            i4 = i0;
            int i5 = i2;

            if (i3 == 0) {
                i4 = i0 - 1;
            }

            if (i3 == 1) {
                ++i4;
            }

            if (i3 == 2) {
                i5 = i2 - 1;
            }

            if (i3 == 3) {
                ++i5;
            }

            if (!this.n(world, i4, i1, i5) && (world.g(i4, i1, i5) != this.cO || world.h(i4, i1, i5) != 0)) {
                if (this.n(world, i4, i1 - 1, i5)) {
                    this.c[i3] = this.d(world, i4, i1, i5, 1, i3);
                } else {
                    this.c[i3] = 0;
                }
            }
        }

        i3 = this.c[0];

        for (i4 = 1; i4 < 4; ++i4) {
            if (this.c[i4] < i3) {
                i3 = this.c[i4];
            }
        }

        for (i4 = 0; i4 < 4; ++i4) {
            this.b[i4] = this.c[i4] == i3;
        }

        return this.b;
    }

    private boolean n(World world, int i0, int i1, int i2) {
        int i3 = world.a(i0, i1, i2);

        if (i3 != Block.aI.cz && i3 != Block.aP.cz && i3 != Block.aH.cz && i3 != Block.aJ.cz && i3 != Block.bb.cz) {
            if (i3 == 0) {
                return false;
            } else {
                Material material = Block.r[i3].cO;

                return material == Material.C ? true : material.c();
            }
        } else {
            return true;
        }
    }

    protected int d(World world, int i0, int i1, int i2, int i3) {
        int i4 = this.k_(world, i0, i1, i2);

        if (i4 < 0) {
            return i3;
        } else {
            if (i4 == 0) {
                ++this.a;
            }

            if (i4 >= 8) {
                i4 = 0;
            }

            return i3 >= 0 && i4 >= i3 ? i3 : i4;
        }
    }

    private boolean o(World world, int i0, int i1, int i2) {
        // CanaryMod: LiquidDestroy
        CanaryBlock dest = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2);
        LiquidDestroyHook hook = new LiquidDestroyHook(dest);
        Canary.hooks().callHook(hook);
        if (hook.isForceDestroy()) {
            return true;
        } else if (hook.isCanceled()) {
            return false;
        }
        //

        Material material = world.g(i0, i1, i2);

        return material == this.cO ? false : (material == Material.i ? false : !this.n(world, i0, i1, i2));
    }

    public void a(World world, int i0, int i1, int i2) {
        super.a(world, i0, i1, i2);
        if (world.a(i0, i1, i2) == this.cz) {
            world.a(i0, i1, i2, this.cz, this.a(world));
        }
    }

    public boolean l() {
        return false;
    }
}
