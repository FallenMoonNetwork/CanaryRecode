package net.minecraft.server;


import java.util.Random;
import net.canarymod.Canary;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.world.LeafDecayHook;


public class BlockLeaves extends BlockLeavesBase {

    public static final String[] a = new String[]{ "oak", "spruce", "birch",
            "jungle" };
    public static final String[][] b = new String[][]{
            { "leaves_oak", "leaves_spruce", "leaves_birch", "leaves_jungle" },
            { "leaves_oak_opaque", "leaves_spruce_opaque",
                    "leaves_birch_opaque", "leaves_jungle_opaque" } };
    private Icon[][] cX = new Icon[2][];
    int[] c;

    protected BlockLeaves(int i0) {
        super(i0, Material.j, false);
        this.b(true);
        this.a(CreativeTabs.c);
    }

    public void a(World world, int i0, int i1, int i2, int i3, int i4) {
        byte b0 = 1;
        int i5 = b0 + 1;

        if (world.e(i0 - i5, i1 - i5, i2 - i5, i0 + i5, i1 + i5, i2 + i5)) {
            for (int i6 = -b0; i6 <= b0; ++i6) {
                for (int i7 = -b0; i7 <= b0; ++i7) {
                    for (int i8 = -b0; i8 <= b0; ++i8) {
                        int i9 = world.a(i0 + i6, i1 + i7, i2 + i8);

                        if (i9 == Block.P.cF) {
                            int i10 = world.h(i0 + i6, i1 + i7, i2 + i8);

                            world.b(i0 + i6, i1 + i7, i2 + i8, i10 | 8, 4);
                        }
                    }
                }
            }
        }
    }

    public void a(World world, int i0, int i1, int i2, Random random) {
        if (!world.I) {
            int i3 = world.h(i0, i1, i2);

            if ((i3 & 8) != 0 && (i3 & 4) == 0) {
                byte b0 = 4;
                int i4 = b0 + 1;
                byte b1 = 32;
                int i5 = b1 * b1;
                int i6 = b1 / 2;

                if (this.c == null) {
                    this.c = new int[b1 * b1 * b1];
                }

                int i7;

                if (world.e(i0 - i4, i1 - i4, i2 - i4, i0 + i4, i1 + i4, i2 + i4)) {
                    int i8;
                    int i9;
                    int i10;

                    for (i7 = -b0; i7 <= b0; ++i7) {
                        for (i8 = -b0; i8 <= b0; ++i8) {
                            for (i9 = -b0; i9 <= b0; ++i9) {
                                i10 = world.a(i0 + i7, i1 + i8, i2 + i9);
                                if (i10 == Block.O.cF) {
                                    this.c[(i7 + i6) * i5 + (i8 + i6) * b1 + i9 + i6] = 0;
                                } else if (i10 == Block.P.cF) {
                                    this.c[(i7 + i6) * i5 + (i8 + i6) * b1 + i9 + i6] = -2;
                                } else {
                                    this.c[(i7 + i6) * i5 + (i8 + i6) * b1 + i9 + i6] = -1;
                                }
                            }
                        }
                    }

                    for (i7 = 1; i7 <= 4; ++i7) {
                        for (i8 = -b0; i8 <= b0; ++i8) {
                            for (i9 = -b0; i9 <= b0; ++i9) {
                                for (i10 = -b0; i10 <= b0; ++i10) {
                                    if (this.c[(i8 + i6) * i5 + (i9 + i6) * b1 + i10 + i6] == i7 - 1) {
                                        if (this.c[(i8 + i6 - 1) * i5 + (i9 + i6) * b1 + i10 + i6] == -2) {
                                            this.c[(i8 + i6 - 1) * i5 + (i9 + i6) * b1 + i10 + i6] = i7;
                                        }

                                        if (this.c[(i8 + i6 + 1) * i5 + (i9 + i6) * b1 + i10 + i6] == -2) {
                                            this.c[(i8 + i6 + 1) * i5 + (i9 + i6) * b1 + i10 + i6] = i7;
                                        }

                                        if (this.c[(i8 + i6) * i5 + (i9 + i6 - 1) * b1 + i10 + i6] == -2) {
                                            this.c[(i8 + i6) * i5 + (i9 + i6 - 1) * b1 + i10 + i6] = i7;
                                        }

                                        if (this.c[(i8 + i6) * i5 + (i9 + i6 + 1) * b1 + i10 + i6] == -2) {
                                            this.c[(i8 + i6) * i5 + (i9 + i6 + 1) * b1 + i10 + i6] = i7;
                                        }

                                        if (this.c[(i8 + i6) * i5 + (i9 + i6) * b1 + (i10 + i6 - 1)] == -2) {
                                            this.c[(i8 + i6) * i5 + (i9 + i6) * b1 + (i10 + i6 - 1)] = i7;
                                        }

                                        if (this.c[(i8 + i6) * i5 + (i9 + i6) * b1 + i10 + i6 + 1] == -2) {
                                            this.c[(i8 + i6) * i5 + (i9 + i6) * b1 + i10 + i6 + 1] = i7;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                i7 = this.c[i6 * i5 + i6 * b1 + i6];
                if (i7 >= 0) {
                    world.b(i0, i1, i2, i3 & -9, 4);
                } else {
                    this.k(world, i0, i1, i2);
                }
            }
        }
    }

    private void k(World world, int i0, int i1, int i2) {
        // CanaryMod: LeafDecay
        CanaryBlock leaves = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0, i1, i2);
        LeafDecayHook hook = new LeafDecayHook(leaves);

        Canary.hooks().callHook(hook);
        if (!hook.isCanceled()) {
            this.c(world, i0, i1, i2, world.h(i0, i1, i2), 0);
            world.i(i0, i1, i2);
        }
        //
    }

    public int a(Random random) {
        return random.nextInt(20) == 0 ? 1 : 0;
    }

    public int a(int i0, Random random, int i1) {
        return Block.D.cF;
    }

    public void a(World world, int i0, int i1, int i2, int i3, float f0, int i4) {
        if (!world.I) {
            int i5 = 20;

            if ((i3 & 3) == 3) {
                i5 = 40;
            }

            if (i4 > 0) {
                i5 -= 2 << i4;
                if (i5 < 10) {
                    i5 = 10;
                }
            }

            if (world.s.nextInt(i5) == 0) {
                int i6 = this.a(i3, world.s, i4);

                this.b(world, i0, i1, i2, new ItemStack(i6, 1, this.a(i3)));
            }

            i5 = 200;
            if (i4 > 0) {
                i5 -= 10 << i4;
                if (i5 < 40) {
                    i5 = 40;
                }
            }

            if ((i3 & 3) == 0 && world.s.nextInt(i5) == 0) {
                this.b(world, i0, i1, i2, new ItemStack(Item.l, 1, 0));
            }
        }
    }

    public void a(World world, EntityPlayer entityplayer, int i0, int i1, int i2, int i3) {
        if (!world.I && entityplayer.bt() != null && entityplayer.bt().d == Item.bg.cv) {
            entityplayer.a(StatList.C[this.cF], 1);
            this.b(world, i0, i1, i2, new ItemStack(Block.P.cF, 1, i3 & 3));
        } else {
            super.a(world, entityplayer, i0, i1, i2, i3);
        }
    }

    public int a(int i0) {
        return i0 & 3;
    }

    public boolean c() {
        return !this.d;
    }

    protected ItemStack d_(int i0) {
        return new ItemStack(this.cF, 1, i0 & 3);
    }
}
