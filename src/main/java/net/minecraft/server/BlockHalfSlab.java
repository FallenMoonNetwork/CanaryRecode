package net.minecraft.server;

import java.util.List;
import java.util.Random;

public abstract class BlockHalfSlab extends Block {

    protected final boolean a;

    public BlockHalfSlab(int i0, boolean flag0, Material material) {
        super(i0, material);
        this.a = flag0;
        if (flag0) {
            t[i0] = true;
        }
        else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }

        this.k(255);
    }

    public void a(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        if (this.a) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
        else {
            boolean flag0 = (iblockaccess.h(i0, i1, i2) & 8) != 0;

            if (flag0) {
                this.a(0.0F, 0.5F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
            else {
                this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
            }
        }
    }

    public void g() {
        if (this.a) {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
        else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
        }
    }

    public void a(World world, int i0, int i1, int i2, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        this.a((IBlockAccess)world, i0, i1, i2); // CanaryMod cast to IBlockAccess
        super.a(world, i0, i1, i2, axisalignedbb, list, entity);
    }

    public boolean c() {
        return this.a;
    }

    public int a(World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2, int i4) {
        return this.a ? i4 : (i3 != 0 && (i3 == 1 || (double)f1 <= 0.5D) ? i4 : i4 | 8);
    }

    public int a(Random random) {
        return this.a ? 2 : 1;
    }

    public int a(int i0) {
        return i0 & 7;
    }

    public boolean b() {
        return this.a;
    }

    public abstract String c(int i0);

    public int h(World world, int i0, int i1, int i2) {
        return super.h(world, i0, i1, i2) & 7;
    }
}
