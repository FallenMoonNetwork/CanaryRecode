package net.minecraft.server;


import java.util.Random;


public abstract class BlockFluid extends Block {

    protected BlockFluid(int i0, Material material) {
        super(i0, material);
        float f0 = 0.0F;
        float f1 = 0.0F;

        this.a(0.0F + f1, 0.0F + f0, 0.0F + f1, 1.0F + f1, 1.0F + f0, 1.0F + f1);
        this.b(true);
    }

    public boolean b(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        return this.cO != Material.i;
    }

    public static float d(int i0) {
        if (i0 >= 8) {
            i0 = 0;
        }

        return (float) (i0 + 1) / 9.0F;
    }

    protected int k_(World world, int i0, int i1, int i2) {
        return world.g(i0, i1, i2) == this.cO ? world.h(i0, i1, i2) : -1;
    }

    protected int d(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        if (iblockaccess.g(i0, i1, i2) != this.cO) {
            return -1;
        } else {
            int i3 = iblockaccess.h(i0, i1, i2);

            if (i3 >= 8) {
                i3 = 0;
            }

            return i3;
        }
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public boolean a(int i0, boolean flag0) {
        return flag0 && i0 == 0;
    }

    public boolean a_(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        Material material = iblockaccess.g(i0, i1, i2);

        return material == this.cO ? false : (i3 == 1 ? true : (material == Material.v ? false : super.a_(iblockaccess, i0, i1, i2, i3)));
    }

    public AxisAlignedBB b(World world, int i0, int i1, int i2) {
        return null;
    }

    public int d() {
        return 4;
    }

    public int a(int i0, Random random, int i1) {
        return 0;
    }

    public int a(Random random) {
        return 0;
    }

    private Vec3 g(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        Vec3 vec3 = iblockaccess.U().a(0.0D, 0.0D, 0.0D);
        int i3 = this.d(iblockaccess, i0, i1, i2);

        for (int i4 = 0; i4 < 4; ++i4) {
            int i5 = i0;
            int i6 = i2;

            if (i4 == 0) {
                i5 = i0 - 1;
            }

            if (i4 == 1) {
                i6 = i2 - 1;
            }

            if (i4 == 2) {
                ++i5;
            }

            if (i4 == 3) {
                ++i6;
            }

            int i7 = this.d(iblockaccess, i5, i1, i6);
            int i8;

            if (i7 < 0) {
                if (!iblockaccess.g(i5, i1, i6).c()) {
                    i7 = this.d(iblockaccess, i5, i1 - 1, i6);
                    if (i7 >= 0) {
                        i8 = i7 - (i3 - 8);
                        vec3 = vec3.c((double) ((i5 - i0) * i8), (double) ((i1 - i1) * i8), (double) ((i6 - i2) * i8));
                    }
                }
            } else if (i7 >= 0) {
                i8 = i7 - i3;
                vec3 = vec3.c((double) ((i5 - i0) * i8), (double) ((i1 - i1) * i8), (double) ((i6 - i2) * i8));
            }
        }

        if (iblockaccess.h(i0, i1, i2) >= 8) {
            boolean flag0 = false;

            if (flag0 || this.a_(iblockaccess, i0, i1, i2 - 1, 2)) {
                flag0 = true;
            }

            if (flag0 || this.a_(iblockaccess, i0, i1, i2 + 1, 3)) {
                flag0 = true;
            }

            if (flag0 || this.a_(iblockaccess, i0 - 1, i1, i2, 4)) {
                flag0 = true;
            }

            if (flag0 || this.a_(iblockaccess, i0 + 1, i1, i2, 5)) {
                flag0 = true;
            }

            if (flag0 || this.a_(iblockaccess, i0, i1 + 1, i2 - 1, 2)) {
                flag0 = true;
            }

            if (flag0 || this.a_(iblockaccess, i0, i1 + 1, i2 + 1, 3)) {
                flag0 = true;
            }

            if (flag0 || this.a_(iblockaccess, i0 - 1, i1 + 1, i2, 4)) {
                flag0 = true;
            }

            if (flag0 || this.a_(iblockaccess, i0 + 1, i1 + 1, i2, 5)) {
                flag0 = true;
            }

            if (flag0) {
                vec3 = vec3.a().c(0.0D, -6.0D, 0.0D);
            }
        }

        vec3 = vec3.a();
        return vec3;
    }

    public void a(World world, int i0, int i1, int i2, Entity entity, Vec3 vec3) {
        Vec3 vec31 = this.g((IBlockAccess) world, i0, i1, i2); // CanaryMod: Cast World to IBlockAccess for method g

        vec3.c += vec31.c;
        vec3.d += vec31.d;
        vec3.e += vec31.e;
    }

    public int a(World world) {
        return this.cO == Material.h ? 5 : (this.cO == Material.i ? (world.t.f ? 10 : 30) : 0);
    }

    public void a(World world, int i0, int i1, int i2) {
        this.k(world, i0, i1, i2);
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        this.k(world, i0, i1, i2);
    }

    private void k(World world, int i0, int i1, int i2) {
        if (world.a(i0, i1, i2) == this.cz) {
            if (this.cO == Material.i) {
                boolean flag0 = false;

                if (flag0 || world.g(i0, i1, i2 - 1) == Material.h) {
                    flag0 = true;
                }

                if (flag0 || world.g(i0, i1, i2 + 1) == Material.h) {
                    flag0 = true;
                }

                if (flag0 || world.g(i0 - 1, i1, i2) == Material.h) {
                    flag0 = true;
                }

                if (flag0 || world.g(i0 + 1, i1, i2) == Material.h) {
                    flag0 = true;
                }

                if (flag0 || world.g(i0, i1 + 1, i2) == Material.h) {
                    flag0 = true;
                }

                if (flag0) {
                    int i3 = world.h(i0, i1, i2);

                    if (i3 == 0) {
                        world.c(i0, i1, i2, Block.at.cz);
                    } else if (i3 <= 4) {
                        world.c(i0, i1, i2, Block.A.cz);
                    }

                    this.j(world, i0, i1, i2);
                }
            }
        }
    }

    protected void j(World world, int i0, int i1, int i2) {
        world.a((double) ((float) i0 + 0.5F), (double) ((float) i1 + 0.5F), (double) ((float) i2 + 0.5F), "random.fizz", 0.5F, 2.6F + (world.s.nextFloat() - world.s.nextFloat()) * 0.8F);

        for (int i3 = 0; i3 < 8; ++i3) {
            world.a("largesmoke", (double) i0 + Math.random(), (double) i1 + 1.2D, (double) i2 + Math.random(), 0.0D, 0.0D, 0.0D);
        }
    }
}
