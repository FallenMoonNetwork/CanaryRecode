package net.minecraft.server;

import java.util.List;
import java.util.Random;

public class BlockStairs extends Block {

    private static final int[][] a = new int[][]{ { 2, 6 }, { 3, 7 }, { 2, 3 }, { 6, 7 }, { 0, 4 }, { 1, 5 }, { 0, 1 }, { 4, 5 } };
    private final Block b;
    private final int c;
    private boolean d;
    private int e;

    protected BlockStairs(int i0, Block block, int i1) {
        super(i0, block.cU);
        this.b = block;
        this.c = i1;
        this.c(block.cG);
        this.b(block.cH / 3.0F);
        this.a(block.cS);
        this.k(255);
        this.a(CreativeTabs.b);
    }

    public void a(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        if (this.d) {
            this.a(0.5F * (float) (this.e % 2),
                    0.5F * (float) (this.e / 2 % 2),
                    0.5F * (float) (this.e / 4 % 2),
                    0.5F + 0.5F * (float) (this.e % 2),
                    0.5F + 0.5F * (float) (this.e / 2 % 2),
                    0.5F + 0.5F * (float) (this.e / 4 % 2));
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int d() {
        return 10;
    }

    public void d(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        int i3 = iblockaccess.h(i0, i1, i2);

        if ((i3 & 4) != 0) {
            this.a(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }
    }

    public static boolean d(int i0) {
        return i0 > 0 && Block.s[i0] instanceof BlockStairs;
    }

    private boolean f(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        int i4 = iblockaccess.a(i0, i1, i2);

        return d(i4) && iblockaccess.h(i0, i1, i2) == i3;
    }

    public boolean g(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        int i3 = iblockaccess.h(i0, i1, i2);
        int i4 = i3 & 3;
        float f0 = 0.5F;
        float f1 = 1.0F;

        if ((i3 & 4) != 0) {
            f0 = 0.0F;
            f1 = 0.5F;
        }

        float f2 = 0.0F;
        float f3 = 1.0F;
        float f4 = 0.0F;
        float f5 = 0.5F;
        boolean flag0 = true;
        int i5;
        int i6;
        int i7;

        if (i4 == 0) {
            f2 = 0.5F;
            f5 = 1.0F;
            i5 = iblockaccess.a(i0 + 1, i1, i2);
            i6 = iblockaccess.h(i0 + 1, i1, i2);
            if (d(i5) && (i3 & 4) == (i6 & 4)) {
                i7 = i6 & 3;
                if (i7 == 3 && !this.f(iblockaccess, i0, i1, i2 + 1, i3)) {
                    f5 = 0.5F;
                    flag0 = false;
                } else if (i7 == 2 && !this.f(iblockaccess, i0, i1, i2 - 1, i3)) {
                    f4 = 0.5F;
                    flag0 = false;
                }
            }
        } else if (i4 == 1) {
            f3 = 0.5F;
            f5 = 1.0F;
            i5 = iblockaccess.a(i0 - 1, i1, i2);
            i6 = iblockaccess.h(i0 - 1, i1, i2);
            if (d(i5) && (i3 & 4) == (i6 & 4)) {
                i7 = i6 & 3;
                if (i7 == 3 && !this.f(iblockaccess, i0, i1, i2 + 1, i3)) {
                    f5 = 0.5F;
                    flag0 = false;
                } else if (i7 == 2 && !this.f(iblockaccess, i0, i1, i2 - 1, i3)) {
                    f4 = 0.5F;
                    flag0 = false;
                }
            }
        } else if (i4 == 2) {
            f4 = 0.5F;
            f5 = 1.0F;
            i5 = iblockaccess.a(i0, i1, i2 + 1);
            i6 = iblockaccess.h(i0, i1, i2 + 1);
            if (d(i5) && (i3 & 4) == (i6 & 4)) {
                i7 = i6 & 3;
                if (i7 == 1 && !this.f(iblockaccess, i0 + 1, i1, i2, i3)) {
                    f3 = 0.5F;
                    flag0 = false;
                } else if (i7 == 0 && !this.f(iblockaccess, i0 - 1, i1, i2, i3)) {
                    f2 = 0.5F;
                    flag0 = false;
                }
            }
        } else if (i4 == 3) {
            i5 = iblockaccess.a(i0, i1, i2 - 1);
            i6 = iblockaccess.h(i0, i1, i2 - 1);
            if (d(i5) && (i3 & 4) == (i6 & 4)) {
                i7 = i6 & 3;
                if (i7 == 1 && !this.f(iblockaccess, i0 + 1, i1, i2, i3)) {
                    f3 = 0.5F;
                    flag0 = false;
                } else if (i7 == 0 && !this.f(iblockaccess, i0 - 1, i1, i2, i3)) {
                    f2 = 0.5F;
                    flag0 = false;
                }
            }
        }

        this.a(f2, f0, f4, f3, f1, f5);
        return flag0;
    }

    public boolean h(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        int i3 = iblockaccess.h(i0, i1, i2);
        int i4 = i3 & 3;
        float f0 = 0.5F;
        float f1 = 1.0F;

        if ((i3 & 4) != 0) {
            f0 = 0.0F;
            f1 = 0.5F;
        }

        float f2 = 0.0F;
        float f3 = 0.5F;
        float f4 = 0.5F;
        float f5 = 1.0F;
        boolean flag0 = false;
        int i5;
        int i6;
        int i7;

        if (i4 == 0) {
            i5 = iblockaccess.a(i0 - 1, i1, i2);
            i6 = iblockaccess.h(i0 - 1, i1, i2);
            if (d(i5) && (i3 & 4) == (i6 & 4)) {
                i7 = i6 & 3;
                if (i7 == 3 && !this.f(iblockaccess, i0, i1, i2 - 1, i3)) {
                    f4 = 0.0F;
                    f5 = 0.5F;
                    flag0 = true;
                } else if (i7 == 2 && !this.f(iblockaccess, i0, i1, i2 + 1, i3)) {
                    f4 = 0.5F;
                    f5 = 1.0F;
                    flag0 = true;
                }
            }
        } else if (i4 == 1) {
            i5 = iblockaccess.a(i0 + 1, i1, i2);
            i6 = iblockaccess.h(i0 + 1, i1, i2);
            if (d(i5) && (i3 & 4) == (i6 & 4)) {
                f2 = 0.5F;
                f3 = 1.0F;
                i7 = i6 & 3;
                if (i7 == 3 && !this.f(iblockaccess, i0, i1, i2 - 1, i3)) {
                    f4 = 0.0F;
                    f5 = 0.5F;
                    flag0 = true;
                } else if (i7 == 2 && !this.f(iblockaccess, i0, i1, i2 + 1, i3)) {
                    f4 = 0.5F;
                    f5 = 1.0F;
                    flag0 = true;
                }
            }
        } else if (i4 == 2) {
            i5 = iblockaccess.a(i0, i1, i2 - 1);
            i6 = iblockaccess.h(i0, i1, i2 - 1);
            if (d(i5) && (i3 & 4) == (i6 & 4)) {
                f4 = 0.0F;
                f5 = 0.5F;
                i7 = i6 & 3;
                if (i7 == 1 && !this.f(iblockaccess, i0 - 1, i1, i2, i3)) {
                    flag0 = true;
                } else if (i7 == 0 && !this.f(iblockaccess, i0 + 1, i1, i2, i3)) {
                    f2 = 0.5F;
                    f3 = 1.0F;
                    flag0 = true;
                }
            }
        } else if (i4 == 3) {
            i5 = iblockaccess.a(i0, i1, i2 + 1);
            i6 = iblockaccess.h(i0, i1, i2 + 1);
            if (d(i5) && (i3 & 4) == (i6 & 4)) {
                i7 = i6 & 3;
                if (i7 == 1 && !this.f(iblockaccess, i0 - 1, i1, i2, i3)) {
                    flag0 = true;
                } else if (i7 == 0 && !this.f(iblockaccess, i0 + 1, i1, i2, i3)) {
                    f2 = 0.5F;
                    f3 = 1.0F;
                    flag0 = true;
                }
            }
        }

        if (flag0) {
            this.a(f2, f0, f4, f3, f1, f5);
        }

        return flag0;
    }

    public void a(World world, int i0, int i1, int i2, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        this.d(world, i0, i1, i2);
        super.a(world, i0, i1, i2, axisalignedbb, list, entity);
        boolean flag0 = this.g((IBlockAccess) world, i0, i1, i2); // CanaryMod: Cast World to IBlockAccess for method g

        super.a(world, i0, i1, i2, axisalignedbb, list, entity);
        if (flag0 && this.h((IBlockAccess) world, i0, i1, i2)) { // CanaryMod: Cast World to IBlockAccess for method h
            super.a(world, i0, i1, i2, axisalignedbb, list, entity);
        }

        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void a(World world, int i0, int i1, int i2, EntityPlayer entityplayer) {
        this.b.a(world, i0, i1, i2, entityplayer);
    }

    public void g(World world, int i0, int i1, int i2, int i3) {
        this.b.g(world, i0, i1, i2, i3);
    }

    public float a(Entity entity) {
        return this.b.a(entity);
    }

    public int a(World world) {
        return this.b.a(world);
    }

    public void a(World world, int i0, int i1, int i2, Entity entity, Vec3 vec3) {
        this.b.a(world, i0, i1, i2, entity, vec3);
    }

    public boolean m() {
        return this.b.m();
    }

    public boolean a(int i0, boolean flag0) {
        return this.b.a(i0, flag0);
    }

    public boolean c(World world, int i0, int i1, int i2) {
        return this.b.c(world, i0, i1, i2);
    }

    public void a(World world, int i0, int i1, int i2) {
        this.a(world, i0, i1, i2, 0);
        this.b.a(world, i0, i1, i2);
    }

    public void a(World world, int i0, int i1, int i2, int i3, int i4) {
        this.b.a(world, i0, i1, i2, i3, i4);
    }

    public void b(World world, int i0, int i1, int i2, Entity entity) {
        this.b.b(world, i0, i1, i2, entity);
    }

    public void a(World world, int i0, int i1, int i2, Random random) {
        this.b.a(world, i0, i1, i2, random);
    }

    public boolean a(World world, int i0, int i1, int i2, EntityPlayer entityplayer, int i3, float f0, float f1, float f2) {
        return this.b.a(world, i0, i1, i2, entityplayer, 0, 0.0F, 0.0F, 0.0F);
    }

    public void a(World world, int i0, int i1, int i2, Explosion explosion) {
        this.b.a(world, i0, i1, i2, explosion);
    }

    public void a(World world, int i0, int i1, int i2, EntityLivingBase entitylivingbase, ItemStack itemstack) {
        int i3 = MathHelper.c((double) (entitylivingbase.A * 4.0F / 360.0F) + 0.5D) & 3;
        int i4 = world.h(i0, i1, i2) & 4;

        if (i3 == 0) {
            world.b(i0, i1, i2, 2 | i4, 2);
        }

        if (i3 == 1) {
            world.b(i0, i1, i2, 1 | i4, 2);
        }

        if (i3 == 2) {
            world.b(i0, i1, i2, 3 | i4, 2);
        }

        if (i3 == 3) {
            world.b(i0, i1, i2, 0 | i4, 2);
        }
    }

    public int a(World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2, int i4) {
        return i3 != 0 && (i3 == 1 || (double) f1 <= 0.5D) ? i4 : i4 | 4;
    }

    public MovingObjectPosition a(World world, int i0, int i1, int i2, Vec3 vec3, Vec3 vec31) {
        MovingObjectPosition[] amovingobjectposition = new MovingObjectPosition[8];
        int i3 = world.h(i0, i1, i2);
        int i4 = i3 & 3;
        boolean flag0 = (i3 & 4) == 4;
        int[] aint = a[i4 + (flag0 ? 4 : 0)];

        this.d = true;

        int i5;
        int i6;
        int i7;

        for (int i8 = 0; i8 < 8; ++i8) {
            this.e = i8;
            int[] aint1 = aint;

            i5 = aint.length;

            for (i6 = 0; i6 < i5; ++i6) {
                i7 = aint1[i6];
                if (i7 == i8) {
                    ;
                }
            }

            amovingobjectposition[i8] = super.a(world, i0, i1, i2, vec3, vec31);
        }

        int[] aint2 = aint;
        int i9 = aint.length;

        for (i5 = 0; i5 < i9; ++i5) {
            i6 = aint2[i5];
            amovingobjectposition[i6] = null;
        }

        MovingObjectPosition movingobjectposition = null;
        double d0 = 0.0D;
        MovingObjectPosition[] amovingobjectposition1 = amovingobjectposition;

        i7 = amovingobjectposition.length;

        for (int i10 = 0; i10 < i7; ++i10) {
            MovingObjectPosition movingobjectposition1 = amovingobjectposition1[i10];

            if (movingobjectposition1 != null) {
                double d1 = movingobjectposition1.f.e(vec31);

                if (d1 > d0) {
                    movingobjectposition = movingobjectposition1;
                    d0 = d1;
                }
            }
        }

        return movingobjectposition;
    }
}
