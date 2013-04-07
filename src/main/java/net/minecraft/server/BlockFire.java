package net.minecraft.server;


import java.util.Random;
import net.canarymod.Canary;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.world.IgnitionHook;


public class BlockFire extends Block {

    private int[] a = new int[256];
    private int[] b = new int[256];

    protected BlockFire(int i0) {
        super(i0, Material.o);
        this.b(true);
    }

    public void s_() {
        this.a(Block.B.cz, 5, 20);
        this.a(Block.bR.cz, 5, 20);
        this.a(Block.bS.cz, 5, 20);
        this.a(Block.bd.cz, 5, 20);
        this.a(Block.ax.cz, 5, 20);
        this.a(Block.cb.cz, 5, 20);
        this.a(Block.ca.cz, 5, 20);
        this.a(Block.cc.cz, 5, 20);
        this.a(Block.N.cz, 5, 5);
        this.a(Block.O.cz, 30, 60);
        this.a(Block.ar.cz, 30, 20);
        this.a(Block.aq.cz, 15, 100);
        this.a(Block.ab.cz, 60, 100);
        this.a(Block.af.cz, 30, 60);
        this.a(Block.by.cz, 15, 100);
    }

    private void a(int i0, int i1, int i2) {
        this.a[i0] = i1;
        this.b[i0] = i2;
    }

    public AxisAlignedBB b(World world, int i0, int i1, int i2) {
        return null;
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int d() {
        return 3;
    }

    public int a(Random random) {
        return 0;
    }

    public int a(World world) {
        return 30;
    }

    public void a(World world, int i0, int i1, int i2, Random random) {
        if (world.M().b("doFireTick")) {
            boolean flag0 = world.a(i0, i1 - 1, i2) == Block.bf.cz;

            if (world.t instanceof WorldProviderEnd && world.a(i0, i1 - 1, i2) == Block.D.cz) {
                flag0 = true;
            }

            if (!this.c(world, i0, i1, i2)) {
                world.i(i0, i1, i2);
            }

            if (!flag0 && world.O() && (world.F(i0, i1, i2) || world.F(i0 - 1, i1, i2) || world.F(i0 + 1, i1, i2) || world.F(i0, i1, i2 - 1) || world.F(i0, i1, i2 + 1))) {
                world.i(i0, i1, i2);
            } else {
                int i3 = world.h(i0, i1, i2);

                if (i3 < 15) {
                    world.b(i0, i1, i2, i3 + random.nextInt(3) / 2, 4);
                }

                world.a(i0, i1, i2, this.cz, this.a(world) + random.nextInt(10));
                if (!flag0 && !this.k(world, i0, i1, i2)) {
                    if (!world.w(i0, i1 - 1, i2) || i3 > 3) {
                        world.i(i0, i1, i2);
                    }
                } else if (!flag0 && !this.d(world, i0, i1 - 1, i2) && i3 == 15 && random.nextInt(4) == 0) {
                    world.i(i0, i1, i2);
                } else {
                    boolean flag1 = world.G(i0, i1, i2);
                    byte b0 = 0;

                    if (flag1) {
                        b0 = -50;
                    }

                    this.a(world, i0 + 1, i1, i2, 300 + b0, random, i3);
                    this.a(world, i0 - 1, i1, i2, 300 + b0, random, i3);
                    this.a(world, i0, i1 - 1, i2, 250 + b0, random, i3);
                    this.a(world, i0, i1 + 1, i2, 250 + b0, random, i3);
                    this.a(world, i0, i1, i2 - 1, 300 + b0, random, i3);
                    this.a(world, i0, i1, i2 + 1, 300 + b0, random, i3);

                    for (int i4 = i0 - 1; i4 <= i0 + 1; ++i4) {
                        for (int i5 = i2 - 1; i5 <= i2 + 1; ++i5) {
                            for (int i6 = i1 - 1; i6 <= i1 + 4; ++i6) {
                                if (i4 != i0 || i6 != i1 || i5 != i2) {
                                    int i7 = 100;

                                    if (i6 > i1 + 1) {
                                        i7 += (i6 - (i1 + 1)) * 100;
                                    }

                                    int i8 = this.m(world, i4, i6, i5);

                                    if (i8 > 0) {
                                        int i9 = (i8 + 40 + world.r * 7) / (i3 + 30);

                                        if (flag1) {
                                            i9 /= 2;
                                        }

                                        if (i9 > 0 && random.nextInt(i7) <= i9 && (!world.O() || !world.F(i4, i6, i5)) && !world.F(i4 - 1, i6, i2) && !world.F(i4 + 1, i6, i5) && !world.F(i4, i6, i5 - 1) && !world.F(i4, i6, i5 + 1)) {
                                            int i10 = i3 + random.nextInt(5) / 4;

                                            if (i10 > 15) {
                                                i10 = 15;
                                            }

                                            // CanaryMod: Ignition
                                            CanaryBlock ignited = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2);

                                            ignited.setStatus((byte) 3); // Spread Status 3
                                            IgnitionHook hook = new IgnitionHook(ignited, null);

                                            Canary.hooks().callHook(hook);
                                            if (!hook.isCanceled()) {
                                                world.f(i4, i6, i5, this.cz, i10, 3);
                                            }
                                            //
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean l() {
        return false;
    }

    private void a(World world, int i0, int i1, int i2, int i3, Random random, int i4) {
        int i5 = this.b[world.a(i0, i1, i2)];

        if (random.nextInt(i3) < i5) {
            boolean flag0 = world.a(i0, i1, i2) == Block.aq.cz;

            if (random.nextInt(i4 + 10) < 5 && !world.F(i0, i1, i2)) {
                int i6 = i4 + random.nextInt(5) / 4;

                if (i6 > 15) {
                    i6 = 15;
                }
                // CanaryMod: Ignition
                CanaryBlock ignited = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2);

                ignited.setStatus((byte) 3); // Spread Status 3
                IgnitionHook hook = new IgnitionHook(ignited, null);

                Canary.hooks().callHook(hook);
                if (!hook.isCanceled()) {
                    world.f(i0, i1, i2, this.cz, i6, 3);
                }
                //
            } else {
                // CanaryMod: Ignition
                CanaryBlock ignited = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2);

                ignited.setStatus((byte) 4); // Burned Up Status 4
                IgnitionHook hook = new IgnitionHook(ignited, null);

                Canary.hooks().callHook(hook);
                if (!hook.isCanceled()) {
                    world.i(i0, i1, i2);
                }
                //
            }

            if (flag0) {
                Block.aq.g(world, i0, i1, i2, 1);
            }
        }
    }

    private boolean k(World world, int i0, int i1, int i2) {
        return this.d(world, i0 + 1, i1, i2) ? true : (this.d(world, i0 - 1, i1, i2) ? true : (this.d(world, i0, i1 - 1, i2) ? true : (this.d(world, i0, i1 + 1, i2) ? true : (this.d(world, i0, i1, i2 - 1) ? true : this.d(world, i0, i1, i2 + 1)))));
    }

    private int m(World world, int i0, int i1, int i2) {
        byte b0 = 0;

        if (!world.c(i0, i1, i2)) {
            return 0;
        } else {
            int i3 = this.d(world, i0 + 1, i1, i2, b0);

            i3 = this.d(world, i0 - 1, i1, i2, i3);
            i3 = this.d(world, i0, i1 - 1, i2, i3);
            i3 = this.d(world, i0, i1 + 1, i2, i3);
            i3 = this.d(world, i0, i1, i2 - 1, i3);
            i3 = this.d(world, i0, i1, i2 + 1, i3);
            return i3;
        }
    }

    public boolean m() {
        return false;
    }

    public boolean d(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        return this.a[iblockaccess.a(i0, i1, i2)] > 0;
    }

    public int d(World world, int i0, int i1, int i2, int i3) {
        int i4 = this.a[world.a(i0, i1, i2)];

        return i4 > i3 ? i4 : i3;
    }

    public boolean c(World world, int i0, int i1, int i2) {
        return world.w(i0, i1 - 1, i2) || this.k(world, i0, i1, i2);
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        if (!world.w(i0, i1 - 1, i2) && !this.k(world, i0, i1, i2)) {
            world.i(i0, i1, i2);
        }
    }

    public void a(World world, int i0, int i1, int i2) {
        if (world.t.h > 0 || world.a(i0, i1 - 1, i2) != Block.at.cz || !Block.bi.n_(world, i0, i1, i2)) {
            if (!world.w(i0, i1 - 1, i2) && !this.k(world, i0, i1, i2)) {
                world.i(i0, i1, i2);
            } else {
                world.a(i0, i1, i2, this.cz, this.a(world) + world.s.nextInt(10));
            }
        }
    }
}
