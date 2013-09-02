package net.minecraft.server;

import java.util.List;
import java.util.Random;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.world.RedstoneChangeHook;

public abstract class BlockButton extends Block {

    private final boolean a;

    protected BlockButton(int i0, boolean flag0) {
        super(i0, Material.q);
        this.b(true);
        this.a(CreativeTabs.d);
        this.a = flag0;
    }

    public AxisAlignedBB b(World world, int i0, int i1, int i2) {
        return null;
    }

    public int a(World world) {
        return this.a ? 30 : 20;
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public boolean c(World world, int i0, int i1, int i2, int i3) {
        return i3 == 2 && world.u(i0, i1, i2 + 1) ? true : (i3 == 3 && world.u(i0, i1, i2 - 1) ? true : (i3 == 4 && world.u(i0 + 1, i1, i2) ? true : i3 == 5 && world.u(i0 - 1, i1, i2)));
    }

    public boolean c(World world, int i0, int i1, int i2) {
        return world.u(i0 - 1, i1, i2) ? true : (world.u(i0 + 1, i1, i2) ? true : (world.u(i0, i1, i2 - 1) ? true : world.u(i0, i1, i2 + 1)));
    }

    public int a(World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2, int i4) {
        int i5 = world.h(i0, i1, i2);
        int i6 = i5 & 8;

        i5 &= 7;
        if (i3 == 2 && world.u(i0, i1, i2 + 1)) {
            i5 = 4;
        } else if (i3 == 3 && world.u(i0, i1, i2 - 1)) {
            i5 = 3;
        } else if (i3 == 4 && world.u(i0 + 1, i1, i2)) {
            i5 = 2;
        } else if (i3 == 5 && world.u(i0 - 1, i1, i2)) {
            i5 = 1;
        } else {
            i5 = this.k(world, i0, i1, i2);
        }

        return i5 + i6;
    }

    private int k(World world, int i0, int i1, int i2) {
        return world.u(i0 - 1, i1, i2) ? 1 : (world.u(i0 + 1, i1, i2) ? 2 : (world.u(i0, i1, i2 - 1) ? 3 : (world.u(i0, i1, i2 + 1) ? 4 : 1)));
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        if (this.m(world, i0, i1, i2)) {
            int i4 = world.h(i0, i1, i2) & 7;
            boolean flag0 = false;

            if (!world.u(i0 - 1, i1, i2) && i4 == 1) {
                flag0 = true;
            }

            if (!world.u(i0 + 1, i1, i2) && i4 == 2) {
                flag0 = true;
            }

            if (!world.u(i0, i1, i2 - 1) && i4 == 3) {
                flag0 = true;
            }

            if (!world.u(i0, i1, i2 + 1) && i4 == 4) {
                flag0 = true;
            }

            if (flag0) {
                this.c(world, i0, i1, i2, world.h(i0, i1, i2), 0);
                world.i(i0, i1, i2);
            }
        }
    }

    private boolean m(World world, int i0, int i1, int i2) {
        if (!this.c(world, i0, i1, i2)) {
            this.c(world, i0, i1, i2, world.h(i0, i1, i2), 0);
            world.i(i0, i1, i2);
            return false;
        } else {
            return true;
        }
    }

    public void a(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        int i3 = iblockaccess.h(i0, i1, i2);

        this.d(i3);
    }

    private void d(int i0) {
        int i1 = i0 & 7;
        boolean flag0 = (i0 & 8) > 0;
        float f0 = 0.375F;
        float f1 = 0.625F;
        float f2 = 0.1875F;
        float f3 = 0.125F;

        if (flag0) {
            f3 = 0.0625F;
        }

        if (i1 == 1) {
            this.a(0.0F, f0, 0.5F - f2, f3, f1, 0.5F + f2);
        } else if (i1 == 2) {
            this.a(1.0F - f3, f0, 0.5F - f2, 1.0F, f1, 0.5F + f2);
        } else if (i1 == 3) {
            this.a(0.5F - f2, f0, 0.0F, 0.5F + f2, f1, f3);
        } else if (i1 == 4) {
            this.a(0.5F - f2, f0, 1.0F - f3, 0.5F + f2, f1, 1.0F);
        }
    }

    public void a(World world, int i0, int i1, int i2, EntityPlayer entityplayer) {}

    public boolean a(World world, int i0, int i1, int i2, EntityPlayer entityplayer, int i3, float f0, float f1, float f2) {
        int i4 = world.h(i0, i1, i2);
        int i5 = i4 & 7;
        int i6 = 8 - (i4 & 8);

        if (i6 == 0) {
            return true;
        }

        // CanaryMod: RedstoneChange; Button On via Click
        RedstoneChangeHook hook = (RedstoneChangeHook) new RedstoneChangeHook(world.getCanaryWorld().getBlockAt(i0, i1, i2), 0, 15).call();
        if (hook.isCanceled()) {
            return false;
            //
        } else {
            world.b(i0, i1, i2, i5 + i6, 3);
            world.g(i0, i1, i2, i0, i1, i2);
            world.a((double) i0 + 0.5D, (double) i1 + 0.5D, (double) i2 + 0.5D, "random.click", 0.3F, 0.6F);
            this.d(world, i0, i1, i2, i5);
            world.a(i0, i1, i2, this.cF, this.a(world));
            return true;
        }
    }

    public void a(World world, int i0, int i1, int i2, int i3, int i4) {
        if ((i4 & 8) > 0) {
            // CanaryMod: RedstoneChangeHook (Button broke)
            new RedstoneChangeHook(new CanaryBlock((short) this.cF, (short) i3, i0, i1, i2, world.getCanaryWorld()), 15, 0).call();
            //
            int i5 = i4 & 7;
            this.d(world, i0, i1, i2, i5);
        }

        super.a(world, i0, i1, i2, i3, i4);
    }

    public int b(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return (iblockaccess.h(i0, i1, i2) & 8) > 0 ? 15 : 0;
    }

    public int c(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        int i4 = iblockaccess.h(i0, i1, i2);

        if ((i4 & 8) == 0) {
            return 0;
        } else {
            int i5 = i4 & 7;

            return i5 == 5 && i3 == 1 ? 15 : (i5 == 4 && i3 == 2 ? 15 : (i5 == 3 && i3 == 3 ? 15 : (i5 == 2 && i3 == 4 ? 15 : (i5 == 1 && i3 == 5 ? 15 : 0))));
        }
    }

    public boolean f() {
        return true;
    }

    public void a(World world, int i0, int i1, int i2, Random random) {
        if (!world.I) {
            int i3 = world.h(i0, i1, i2);

            if ((i3 & 8) != 0) {
                if (this.a) {
                    this.n(world, i0, i1, i2);
                } else {
                    // CanaryMod: RedstoneChange; Stone Button off
                    RedstoneChangeHook hook = (RedstoneChangeHook) new RedstoneChangeHook(world.getCanaryWorld().getBlockAt(i0, i1, i2), 15, 0).call();
                    if (hook.isCanceled()) {
                        return;
                    }
                    //

                    world.b(i0, i1, i2, i3 & 7, 3);
                    int i4 = i3 & 7;

                    this.d(world, i0, i1, i2, i4);
                    world.a((double) i0 + 0.5D, (double) i1 + 0.5D, (double) i2 + 0.5D, "random.click", 0.3F, 0.5F);
                    world.g(i0, i1, i2, i0, i1, i2);
                }
            }
        }
    }

    public void g() {
        float f0 = 0.1875F;
        float f1 = 0.125F;
        float f2 = 0.125F;

        this.a(0.5F - f0, 0.5F - f1, 0.5F - f2, 0.5F + f0, 0.5F + f1, 0.5F + f2);
    }

    public void a(World world, int i0, int i1, int i2, Entity entity) {
        if (!world.I) {
            if (this.a) {
                if ((world.h(i0, i1, i2) & 8) == 0) {
                    this.n(world, i0, i1, i2);
                }
            }
        }
    }

    private void n(World world, int i0, int i1, int i2) {
        int i3 = world.h(i0, i1, i2);
        int i4 = i3 & 7;
        boolean flag0 = (i3 & 8) != 0;

        this.d(i3);
        List list = world.a(EntityArrow.class, AxisAlignedBB.a().a((double) i0 + this.cM, (double) i1 + this.cN, (double) i2 + this.cO, (double) i0 + this.cP, (double) i1 + this.cQ, (double) i2 + this.cR));
        boolean flag1 = !list.isEmpty();

        if (flag1 && !flag0) {
            // CanaryMod: RedstoneChange; Wood Button on
            RedstoneChangeHook hook = (RedstoneChangeHook) new RedstoneChangeHook(world.getCanaryWorld().getBlockAt(i0, i1, i2), 0, 15).call();
            if (hook.isCanceled()) {
                world.a(i0, i1, i2, this.cF, this.a(world)); // Reschedule
                return;
            }
            //
            world.b(i0, i1, i2, i4 | 8, 3);
            this.d(world, i0, i1, i2, i4);
            world.g(i0, i1, i2, i0, i1, i2);
            world.a((double) i0 + 0.5D, (double) i1 + 0.5D, (double) i2 + 0.5D, "random.click", 0.3F, 0.6F);
        }

        if (!flag1 && flag0) {
            // CanaryMod: RedstoneChange; Wood Button off
            RedstoneChangeHook hook = (RedstoneChangeHook) new RedstoneChangeHook(world.getCanaryWorld().getBlockAt(i0, i1, i2), 15, 0).call();
            if (hook.isCanceled()) {
                world.a(i0, i1, i2, this.cF, this.a(world));  // Reschedule
                return;
            }
            //
            world.b(i0, i1, i2, i4, 3);
            this.d(world, i0, i1, i2, i4);
            world.g(i0, i1, i2, i0, i1, i2);
            world.a((double) i0 + 0.5D, (double) i1 + 0.5D, (double) i2 + 0.5D, "random.click", 0.3F, 0.5F);
        }

        if (flag1) {
            world.a(i0, i1, i2, this.cF, this.a(world));
        }
    }

    private void d(World world, int i0, int i1, int i2, int i3) {
        world.f(i0, i1, i2, this.cF);
        if (i3 == 1) {
            world.f(i0 - 1, i1, i2, this.cF);
        } else if (i3 == 2) {
            world.f(i0 + 1, i1, i2, this.cF);
        } else if (i3 == 3) {
            world.f(i0, i1, i2 - 1, this.cF);
        } else if (i3 == 4) {
            world.f(i0, i1, i2 + 1, this.cF);
        } else {
            world.f(i0, i1 - 1, i2, this.cF);
        }
    }
}
