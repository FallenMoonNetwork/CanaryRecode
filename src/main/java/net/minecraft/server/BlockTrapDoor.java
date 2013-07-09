package net.minecraft.server;

public class BlockTrapDoor extends Block {

    protected BlockTrapDoor(int i0, Material material) {
        super(i0, material);
        float f0 = 0.5F;
        float f1 = 1.0F;

        this.a(0.5F - f0, 0.0F, 0.5F - f0, 0.5F + f0, f1, 0.5F + f0);
        this.a(CreativeTabs.d);
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public boolean b(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        return !f(iblockaccess.h(i0, i1, i2));
    }

    public int d() {
        return 0;
    }

    public AxisAlignedBB b(World world, int i0, int i1, int i2) {
        this.a((IBlockAccess) world, i0, i1, i2); // CanaryMod Cast to IBlockAccess
        return super.b(world, i0, i1, i2);
    }

    public void a(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        this.d(iblockaccess.h(i0, i1, i2));
    }

    public void g() {
        float f0 = 0.1875F;

        this.a(0.0F, 0.5F - f0 / 2.0F, 0.0F, 1.0F, 0.5F + f0 / 2.0F, 1.0F);
    }

    public void d(int i0) {
        float f0 = 0.1875F;

        if ((i0 & 8) != 0) {
            this.a(0.0F, 1.0F - f0, 0.0F, 1.0F, 1.0F, 1.0F);
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, f0, 1.0F);
        }

        if (f(i0)) {
            if ((i0 & 3) == 0) {
                this.a(0.0F, 0.0F, 1.0F - f0, 1.0F, 1.0F, 1.0F);
            }

            if ((i0 & 3) == 1) {
                this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f0);
            }

            if ((i0 & 3) == 2) {
                this.a(1.0F - f0, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }

            if ((i0 & 3) == 3) {
                this.a(0.0F, 0.0F, 0.0F, f0, 1.0F, 1.0F);
            }
        }
    }

    public void a(World world, int i0, int i1, int i2, EntityPlayer entityplayer) {}

    public boolean a(World world, int i0, int i1, int i2, EntityPlayer entityplayer, int i3, float f0, float f1, float f2) {
        if (this.cU == Material.f) {
            return true;
        } else {
            int i4 = world.h(i0, i1, i2);

            world.b(i0, i1, i2, i4 ^ 4, 2);
            world.a(entityplayer, 1003, i0, i1, i2, 0);
            return true;
        }
    }

    public void a(World world, int i0, int i1, int i2, boolean flag0) {
        int i3 = world.h(i0, i1, i2);
        boolean flag1 = (i3 & 4) > 0;

        if (flag1 != flag0) {
            world.b(i0, i1, i2, i3 ^ 4, 2);
            world.a((EntityPlayer) null, 1003, i0, i1, i2, 0);
        }
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        if (!world.I) {
            int i4 = world.h(i0, i1, i2);
            int i5 = i0;
            int i6 = i2;

            if ((i4 & 3) == 0) {
                i6 = i2 + 1;
            }

            if ((i4 & 3) == 1) {
                --i6;
            }

            if ((i4 & 3) == 2) {
                i5 = i0 + 1;
            }

            if ((i4 & 3) == 3) {
                --i5;
            }

            if (!g(world.a(i5, i1, i6))) {
                world.i(i0, i1, i2);
                this.c(world, i0, i1, i2, i4, 0);
            }

            boolean flag0 = world.C(i0, i1, i2);

            if (flag0 || i3 > 0 && Block.s[i3].f()) {
                this.a(world, i0, i1, i2, flag0);
            }
        }
    }

    public MovingObjectPosition a(World world, int i0, int i1, int i2, Vec3 vec3, Vec3 vec31) {
        this.a(world, i0, i1, i2);
        return super.a(world, i0, i1, i2, vec3, vec31);
    }

    public int a(World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2, int i4) {
        int i5 = 0;

        if (i3 == 2) {
            i5 = 0;
        }

        if (i3 == 3) {
            i5 = 1;
        }

        if (i3 == 4) {
            i5 = 2;
        }

        if (i3 == 5) {
            i5 = 3;
        }

        if (i3 != 1 && i3 != 0 && f1 > 0.5F) {
            i5 |= 8;
        }

        return i5;
    }

    public boolean c(World world, int i0, int i1, int i2, int i3) {
        if (i3 == 0) {
            return false;
        } else if (i3 == 1) {
            return false;
        } else {
            if (i3 == 2) {
                ++i2;
            }

            if (i3 == 3) {
                --i2;
            }

            if (i3 == 4) {
                ++i0;
            }

            if (i3 == 5) {
                --i0;
            }

            return g(world.a(i0, i1, i2));
        }
    }

    public static boolean f(int i0) {
        return (i0 & 4) != 0;
    }

    private static boolean g(int i0) {
        if (i0 <= 0) {
            return false;
        } else {
            Block block = Block.s[i0];

            return block != null && block.cU.k() && block.b()
                    || block == Block.bi || block instanceof BlockHalfSlab
                    || block instanceof BlockStairs;
        }
    }
}
