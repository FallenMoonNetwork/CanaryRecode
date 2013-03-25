package net.minecraft.server;


import java.util.Random;


public class BlockComparator extends BlockRedstoneLogic implements ITileEntityProvider {

    public BlockComparator(int i0, boolean flag0) {
        super(i0, flag0);
        this.cF = true;
    }

    public int a(int i0, Random random, int i1) {
        return Item.bY.cp;
    }

    protected int i_(int i0) {
        return 2;
    }

    protected BlockRedstoneLogic i() {
        return Block.cq;
    }

    protected BlockRedstoneLogic j() {
        return Block.cp;
    }

    public int d() {
        return 37;
    }

    protected boolean c(int i0) {
        return this.a || (i0 & 8) != 0;
    }

    protected int d(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return this.a_(iblockaccess, i0, i1, i2).a();
    }

    private int m(World world, int i0, int i1, int i2, int i3) {
        return !this.d(i3) ? this.e(world, i0, i1, i2, i3) : Math.max(this.e(world, i0, i1, i2, i3) - this.f((IBlockAccess) world, i0, i1, i2, i3), 0); // CanaryMod: Cast World to IBlockAccess for method f
    }

    public boolean d(int i0) {
        return (i0 & 4) == 4;
    }

    protected boolean d(World world, int i0, int i1, int i2, int i3) {
        int i4 = this.e(world, i0, i1, i2, i3);

        if (i4 >= 15) {
            return true;
        } else if (i4 == 0) {
            return false;
        } else {
            int i5 = this.f((IBlockAccess) world, i0, i1, i2, i3); // CanaryMod: Cast World to IBlockAccess for method f

            return i5 == 0 ? true : i4 >= i5;
        }
    }

    protected int e(World world, int i0, int i1, int i2, int i3) {
        int i4 = super.e(world, i0, i1, i2, i3);
        int i5 = j(i3);
        int i6 = i0 + Direction.a[i5];
        int i7 = i2 + Direction.b[i5];
        int i8 = world.a(i6, i1, i7);

        if (i8 > 0) {
            if (Block.r[i8].q_()) {
                i4 = Block.r[i8].b_(world, i6, i1, i7, Direction.f[i5]);
            } else if (i4 < 15 && Block.l(i8)) {
                i6 += Direction.a[i5];
                i7 += Direction.b[i5];
                i8 = world.a(i6, i1, i7);
                if (i8 > 0 && Block.r[i8].q_()) {
                    i4 = Block.r[i8].b_(world, i6, i1, i7, Direction.f[i5]);
                }
            }
        }

        return i4;
    }

    public TileEntityComparator a_(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        return (TileEntityComparator) iblockaccess.r(i0, i1, i2);
    }

    public boolean a(World world, int i0, int i1, int i2, EntityPlayer entityplayer, int i3, float f0, float f1, float f2) {
        int i4 = world.h(i0, i1, i2);
        boolean flag0 = this.a | (i4 & 8) != 0;
        boolean flag1 = !this.d(i4);
        int i5 = flag1 ? 4 : 0;

        i5 |= flag0 ? 8 : 0;
        world.a((double) i0 + 0.5D, (double) i1 + 0.5D, (double) i2 + 0.5D, "random.click", 0.3F, flag1 ? 0.55F : 0.5F);
        world.b(i0, i1, i2, i5 | i4 & 3, 2);
        this.c(world, i0, i1, i2, world.s);
        return true;
    }

    protected void f(World world, int i0, int i1, int i2, int i3) {
        if (!world.a(i0, i1, i2, this.cz)) {
            int i4 = world.h(i0, i1, i2);
            int i5 = this.m(world, i0, i1, i2, i4);
            int i6 = this.a_(world, i0, i1, i2).a();

            if (i5 != i6 || this.c(i4) != this.d(world, i0, i1, i2, i4)) {
                if (this.h(world, i0, i1, i2, i4)) {
                    world.a(i0, i1, i2, this.cz, this.i_(0), -1);
                } else {
                    world.a(i0, i1, i2, this.cz, this.i_(0), 0);
                }
            }
        }
    }

    private void c(World world, int i0, int i1, int i2, Random random) {
        int i3 = world.h(i0, i1, i2);
        int i4 = this.m(world, i0, i1, i2, i3);
        int i5 = this.a_(world, i0, i1, i2).a();

        this.a_(world, i0, i1, i2).a(i4);
        if (i5 != i4 || !this.d(i3)) {
            boolean flag0 = this.d(world, i0, i1, i2, i3);
            boolean flag1 = this.a || (i3 & 8) != 0;

            if (flag1 && !flag0) {
                world.b(i0, i1, i2, i3 & -9, 2);
            } else if (!flag1 && flag0) {
                world.b(i0, i1, i2, i3 | 8, 2);
            }

            this.h_(world, i0, i1, i2);
        }
    }

    public void a(World world, int i0, int i1, int i2, Random random) {
        if (this.a) {
            int i3 = world.h(i0, i1, i2);

            world.f(i0, i1, i2, this.j().cz, i3 | 8, 4);
        }

        this.c(world, i0, i1, i2, random);
    }

    public void a(World world, int i0, int i1, int i2) {
        super.a(world, i0, i1, i2);
        world.a(i0, i1, i2, this.b(world));
    }

    public void a(World world, int i0, int i1, int i2, int i3, int i4) {
        super.a(world, i0, i1, i2, i3, i4);
        world.s(i0, i1, i2);
        this.h_(world, i0, i1, i2);
    }

    public boolean b(World world, int i0, int i1, int i2, int i3, int i4) {
        super.b(world, i0, i1, i2, i3, i4);
        TileEntity tileentity = world.r(i0, i1, i2);

        return tileentity != null ? tileentity.b(i3, i4) : false;
    }

    public TileEntity b(World world) {
        return new TileEntityComparator();
    }
}
