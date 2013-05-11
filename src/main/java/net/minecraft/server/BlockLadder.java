package net.minecraft.server;

import java.util.Random;

public class BlockLadder extends Block {

    protected BlockLadder(int i0) {
        super(i0, Material.q);
        this.a(CreativeTabs.c);
    }

    public AxisAlignedBB b(World world, int i0, int i1, int i2) {
        this.a((IBlockAccess) world, i0, i1, i2); //CanaryMod: cast to IBlockAccess
        return super.b(world, i0, i1, i2);
    }

    public void a(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        this.c(iblockaccess.h(i0, i1, i2));
    }

    public void c(int i0) {
        float f0 = 0.125F;

        if (i0 == 2) {
            this.a(0.0F, 0.0F, 1.0F - f0, 1.0F, 1.0F, 1.0F);
        }

        if (i0 == 3) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f0);
        }

        if (i0 == 4) {
            this.a(1.0F - f0, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }

        if (i0 == 5) {
            this.a(0.0F, 0.0F, 0.0F, f0, 1.0F, 1.0F);
        }
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int d() {
        return 8;
    }

    public boolean c(World world, int i0, int i1, int i2) {
        return world.u(i0 - 1, i1, i2) ? true : (world.u(i0 + 1, i1, i2) ? true : (world.u(i0, i1, i2 - 1) ? true : world.u(i0, i1, i2 + 1)));
    }

    public int a(World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2, int i4) {
        int i5 = i4;

        if ((i4 == 0 || i3 == 2) && world.u(i0, i1, i2 + 1)) {
            i5 = 2;
        }

        if ((i5 == 0 || i3 == 3) && world.u(i0, i1, i2 - 1)) {
            i5 = 3;
        }

        if ((i5 == 0 || i3 == 4) && world.u(i0 + 1, i1, i2)) {
            i5 = 4;
        }

        if ((i5 == 0 || i3 == 5) && world.u(i0 - 1, i1, i2)) {
            i5 = 5;
        }

        return i5;
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        int i4 = world.h(i0, i1, i2);
        boolean flag0 = false;

        if (i4 == 2 && world.u(i0, i1, i2 + 1)) {
            flag0 = true;
        }

        if (i4 == 3 && world.u(i0, i1, i2 - 1)) {
            flag0 = true;
        }

        if (i4 == 4 && world.u(i0 + 1, i1, i2)) {
            flag0 = true;
        }

        if (i4 == 5 && world.u(i0 - 1, i1, i2)) {
            flag0 = true;
        }

        if (!flag0) {
            this.c(world, i0, i1, i2, i4, 0);
            world.i(i0, i1, i2);
        }

        super.a(world, i0, i1, i2, i3);
    }

    public int a(Random random) {
        return 1;
    }
}
