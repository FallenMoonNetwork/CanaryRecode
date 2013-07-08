package net.minecraft.server;

import java.util.Random;
import net.canarymod.hook.world.RedstoneChangeHook;

public abstract class BlockRedstoneLogic extends BlockDirectional {

    protected final boolean a;

    protected BlockRedstoneLogic(int i0, boolean flag0) {
        super(i0, Material.q);
        this.a = flag0;
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
    }

    public boolean b() {
        return false;
    }

    public boolean c(World world, int i0, int i1, int i2) {
        return !world.w(i0, i1 - 1, i2) ? false : super.c(world, i0, i1, i2);
    }

    public boolean f(World world, int i0, int i1, int i2) {
        return !world.w(i0, i1 - 1, i2) ? false : super.f(world, i0, i1, i2);
    }

    public void a(World world, int i0, int i1, int i2, Random random) {
        int i3 = world.h(i0, i1, i2);

        if (!this.e((IBlockAccess) world, i0, i1, i2, i3)) {
            boolean flag0 = this.d(world, i0, i1, i2, i3);

            if (this.a && !flag0) {
                // CanaryMod: RedstoneChange; turning off
                RedstoneChangeHook hook = (RedstoneChangeHook) new RedstoneChangeHook(world.getCanaryWorld().getBlockAt(i0, i1, i2), 15, 0).call();
                if (hook.isCanceled()) {
                    return;
                }
                //
                world.f(i0, i1, i2, this.j().cF, i3, 2);
            } else if (!this.a) {
                // CanaryMod: RedstoneChange; turning on
                RedstoneChangeHook hook = (RedstoneChangeHook) new RedstoneChangeHook(world.getCanaryWorld().getBlockAt(i0, i1, i2), 0, 15).call();
                if (hook.isCanceled()) {
                    return;
                }
                //
                world.f(i0, i1, i2, this.i().cF, i3, 2);
                if (!flag0) {
                    world.a(i0, i1, i2, this.i().cF, this.h(i3), -1);
                }
            }
        }
    }

    public int d() {
        return 36;
    }

    protected boolean c(int i0) {
        return this.a;
    }

    public int c(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return this.b(iblockaccess, i0, i1, i2, i3);
    }

    public int b(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        int i4 = iblockaccess.h(i0, i1, i2);

        if (!this.c(i4)) {
            return 0;
        } else {
            int i5 = j(i4);

            return i5 == 0 && i3 == 3 ? this.d(iblockaccess, i0, i1, i2, i4) : (i5 == 1 && i3 == 4 ? this.d(iblockaccess, i0, i1, i2, i4) : (i5 == 2 && i3 == 2 ? this.d(iblockaccess, i0, i1, i2, i4) : (i5 == 3 && i3 == 5 ? this.d(iblockaccess, i0, i1, i2, i4) : 0)));
        }
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        if (!this.f(world, i0, i1, i2)) {
            this.c(world, i0, i1, i2, world.h(i0, i1, i2), 0);
            world.i(i0, i1, i2);
            world.f(i0 + 1, i1, i2, this.cF);
            world.f(i0 - 1, i1, i2, this.cF);
            world.f(i0, i1, i2 + 1, this.cF);
            world.f(i0, i1, i2 - 1, this.cF);
            world.f(i0, i1 - 1, i2, this.cF);
            world.f(i0, i1 + 1, i2, this.cF);
        } else {
            this.f(world, i0, i1, i2, i3);
        }
    }

    protected void f(World world, int i0, int i1, int i2, int i3) {
        int i4 = world.h(i0, i1, i2);

        if (!this.e((IBlockAccess) world, i0, i1, i2, i4)) {
            boolean flag0 = this.d(world, i0, i1, i2, i4);

            if ((this.a && !flag0 || !this.a && flag0) && !world.a(i0, i1, i2, this.cF)) {
                byte b0 = -1;

                if (this.h(world, i0, i1, i2, i4)) {
                    b0 = -3;
                } else if (this.a) {
                    b0 = -2;
                }

                world.a(i0, i1, i2, this.cF, this.k_(i4), b0);
            }
        }
    }

    public boolean e(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return false;
    }

    protected boolean d(World world, int i0, int i1, int i2, int i3) {
        return this.e(world, i0, i1, i2, i3) > 0;
    }

    protected int e(World world, int i0, int i1, int i2, int i3) {
        int i4 = j(i3);
        int i5 = i0 + Direction.a[i4];
        int i6 = i2 + Direction.b[i4];
        int i7 = world.l(i5, i1, i6, Direction.d[i4]);

        return i7 >= 15 ? i7 : Math.max(i7, world.a(i5, i1, i6) == Block.aA.cF ? world.h(i5, i1, i6) : 0);
    }

    protected int f(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        int i4 = j(i3);

        switch (i4) {
            case 0:
            case 2:
                return Math.max(this.g(iblockaccess, i0 - 1, i1, i2, 4), this.g(iblockaccess, i0 + 1, i1, i2, 5));

            case 1:
            case 3:
                return Math.max(this.g(iblockaccess, i0, i1, i2 + 1, 3), this.g(iblockaccess, i0, i1, i2 - 1, 2));

            default:
                return 0;
        }
    }

    protected int g(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        int i4 = iblockaccess.a(i0, i1, i2);

        return this.e(i4) ? (i4 == Block.aA.cF ? iblockaccess.h(i0, i1, i2) : iblockaccess.j(i0, i1, i2, i3)) : 0;
    }

    public boolean f() {
        return true;
    }

    public void a(World world, int i0, int i1, int i2, EntityLivingBase entitylivingbase, ItemStack itemstack) {
        int i3 = ((MathHelper.c((double) (entitylivingbase.A * 4.0F / 360.0F) + 0.5D) & 3) + 2) % 4;

        world.b(i0, i1, i2, i3, 3);
        boolean flag0 = this.d(world, i0, i1, i2, i3);

        if (flag0) {
            world.a(i0, i1, i2, this.cF, 1);
        }
    }

    public void a(World world, int i0, int i1, int i2) {
        this.h_(world, i0, i1, i2);
    }

    protected void h_(World world, int i0, int i1, int i2) {
        int i3 = j(world.h(i0, i1, i2));

        if (i3 == 1) {
            world.g(i0 + 1, i1, i2, this.cF);
            world.c(i0 + 1, i1, i2, this.cF, 4);
        }

        if (i3 == 3) {
            world.g(i0 - 1, i1, i2, this.cF);
            world.c(i0 - 1, i1, i2, this.cF, 5);
        }

        if (i3 == 2) {
            world.g(i0, i1, i2 + 1, this.cF);
            world.c(i0, i1, i2 + 1, this.cF, 2);
        }

        if (i3 == 0) {
            world.g(i0, i1, i2 - 1, this.cF);
            world.c(i0, i1, i2 - 1, this.cF, 3);
        }
    }

    public void g(World world, int i0, int i1, int i2, int i3) {
        if (this.a) {
            world.f(i0 + 1, i1, i2, this.cF);
            world.f(i0 - 1, i1, i2, this.cF);
            world.f(i0, i1, i2 + 1, this.cF);
            world.f(i0, i1, i2 - 1, this.cF);
            world.f(i0, i1 - 1, i2, this.cF);
            world.f(i0, i1 + 1, i2, this.cF);
        }

        super.g(world, i0, i1, i2, i3);
    }

    public boolean c() {
        return false;
    }

    protected boolean e(int i0) {
        Block block = Block.s[i0];

        return block != null && block.f();
    }

    protected int d(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return 15;
    }

    public static boolean f(int i0) {
        return Block.bm.g(i0) || Block.cq.g(i0);
    }

    public boolean g(int i0) {
        return i0 == this.i().cF || i0 == this.j().cF;
    }

    public boolean h(World world, int i0, int i1, int i2, int i3) {
        int i4 = j(i3);

        if (f(world.a(i0 - Direction.a[i4], i1, i2 - Direction.b[i4]))) {
            int i5 = world.h(i0 - Direction.a[i4], i1, i2 - Direction.b[i4]);
            int i6 = j(i5);

            return i6 != i4;
        } else {
            return false;
        }
    }

    protected int h(int i0) {
        return this.k_(i0);
    }

    protected abstract int k_(int i0);

    protected abstract BlockRedstoneLogic i();

    protected abstract BlockRedstoneLogic j();

    public boolean i(int i0) {
        return this.g(i0);
    }
}
