package net.minecraft.server;

import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.world.RedstoneChangeHook;

public class BlockLever extends Block {

    protected BlockLever(int i0) {
        super(i0, Material.q);
        this.a(CreativeTabs.d);
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
        return 12;
    }

    public boolean c(World world, int i0, int i1, int i2, int i3) {
        return i3 == 0 && world.u(i0, i1 + 1, i2) ? true : (i3 == 1 && world.w(i0, i1 - 1, i2) ? true : (i3 == 2 && world.u(i0, i1, i2 + 1) ? true : (i3 == 3 && world.u(i0, i1, i2 - 1) ? true : (i3 == 4 && world.u(i0 + 1, i1, i2) ? true : i3 == 5 && world.u(i0 - 1, i1, i2)))));
    }

    public boolean c(World world, int i0, int i1, int i2) {
        return world.u(i0 - 1, i1, i2) ? true : (world.u(i0 + 1, i1, i2) ? true : (world.u(i0, i1, i2 - 1) ? true : (world.u(i0, i1, i2 + 1) ? true : (world.w(i0, i1 - 1, i2) ? true : world.u(i0, i1 + 1, i2)))));
    }

    public int a(World world, int i0, int i1, int i2, int i3, float f0, float f1, float f2, int i4) {
        int i5 = i4 & 8;
        int i6 = i4 & 7;
        byte b0 = -1;

        if (i3 == 0 && world.u(i0, i1 + 1, i2)) {
            b0 = 0;
        }

        if (i3 == 1 && world.w(i0, i1 - 1, i2)) {
            b0 = 5;
        }

        if (i3 == 2 && world.u(i0, i1, i2 + 1)) {
            b0 = 4;
        }

        if (i3 == 3 && world.u(i0, i1, i2 - 1)) {
            b0 = 3;
        }

        if (i3 == 4 && world.u(i0 + 1, i1, i2)) {
            b0 = 2;
        }

        if (i3 == 5 && world.u(i0 - 1, i1, i2)) {
            b0 = 1;
        }

        return b0 + i5;
    }

    public void a(World world, int i0, int i1, int i2, EntityLivingBase entitylivingbase, ItemStack itemstack) {
        int i3 = world.h(i0, i1, i2);
        int i4 = i3 & 7;
        int i5 = i3 & 8;

        if (i4 == d(1)) {
            if ((MathHelper.c((double)(entitylivingbase.A * 4.0F / 360.0F) + 0.5D) & 1) == 0) {
                world.b(i0, i1, i2, 5 | i5, 2);
            }
            else {
                world.b(i0, i1, i2, 6 | i5, 2);
            }
        }
        else if (i4 == d(0)) {
            if ((MathHelper.c((double)(entitylivingbase.A * 4.0F / 360.0F) + 0.5D) & 1) == 0) {
                world.b(i0, i1, i2, 7 | i5, 2);
            }
            else {
                world.b(i0, i1, i2, 0 | i5, 2);
            }
        }
    }

    public static int d(int i0) {
        switch (i0) {
            case 0:
                return 0;

            case 1:
                return 5;

            case 2:
                return 4;

            case 3:
                return 3;

            case 4:
                return 2;

            case 5:
                return 1;

            default:
                return -1;
        }
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        if (this.k(world, i0, i1, i2)) {
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

            if (!world.w(i0, i1 - 1, i2) && i4 == 5) {
                flag0 = true;
            }

            if (!world.w(i0, i1 - 1, i2) && i4 == 6) {
                flag0 = true;
            }

            if (!world.u(i0, i1 + 1, i2) && i4 == 0) {
                flag0 = true;
            }

            if (!world.u(i0, i1 + 1, i2) && i4 == 7) {
                flag0 = true;
            }

            if (flag0) {
                this.c(world, i0, i1, i2, world.h(i0, i1, i2), 0);
                world.i(i0, i1, i2);
            }
        }
    }

    private boolean k(World world, int i0, int i1, int i2) {
        if (!this.c(world, i0, i1, i2)) {
            this.c(world, i0, i1, i2, world.h(i0, i1, i2), 0);
            world.i(i0, i1, i2);
            return false;
        }
        else {
            return true;
        }
    }

    public void a(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        int i3 = iblockaccess.h(i0, i1, i2) & 7;
        float f0 = 0.1875F;

        if (i3 == 1) {
            this.a(0.0F, 0.2F, 0.5F - f0, f0 * 2.0F, 0.8F, 0.5F + f0);
        }
        else if (i3 == 2) {
            this.a(1.0F - f0 * 2.0F, 0.2F, 0.5F - f0, 1.0F, 0.8F, 0.5F + f0);
        }
        else if (i3 == 3) {
            this.a(0.5F - f0, 0.2F, 0.0F, 0.5F + f0, 0.8F, f0 * 2.0F);
        }
        else if (i3 == 4) {
            this.a(0.5F - f0, 0.2F, 1.0F - f0 * 2.0F, 0.5F + f0, 0.8F, 1.0F);
        }
        else if (i3 != 5 && i3 != 6) {
            if (i3 == 0 || i3 == 7) {
                f0 = 0.25F;
                this.a(0.5F - f0, 0.4F, 0.5F - f0, 0.5F + f0, 1.0F, 0.5F + f0);
            }
        }
        else {
            f0 = 0.25F;
            this.a(0.5F - f0, 0.0F, 0.5F - f0, 0.5F + f0, 0.6F, 0.5F + f0);
        }
    }

    public boolean a(World world, int i0, int i1, int i2, EntityPlayer entityplayer, int i3, float f0, float f1, float f2) {
        if (world.I) {
            return true;
        }
        else {
            int i4 = world.h(i0, i1, i2);
            int i5 = i4 & 7;
            int i6 = 8 - (i4 & 8);

            // CanaryMod: RedstoneChange
            int newLvl = i6 == 0 ? 0 : 15; //
            RedstoneChangeHook hook = (RedstoneChangeHook)new RedstoneChangeHook(world.getCanaryWorld().getBlockAt(i0, i1, i2), ~newLvl & 15, newLvl).call();
            if (hook.isCanceled()) {
                return true;
            } // CanaryMod: end

            world.b(i0, i1, i2, i5 + i6, 3);
            world.a((double)i0 + 0.5D, (double)i1 + 0.5D, (double)i2 + 0.5D, "random.click", 0.3F, i6 > 0 ? 0.6F : 0.5F);
            world.f(i0, i1, i2, this.cF);
            if (i5 == 1) {
                world.f(i0 - 1, i1, i2, this.cF);
            }
            else if (i5 == 2) {
                world.f(i0 + 1, i1, i2, this.cF);
            }
            else if (i5 == 3) {
                world.f(i0, i1, i2 - 1, this.cF);
            }
            else if (i5 == 4) {
                world.f(i0, i1, i2 + 1, this.cF);
            }
            else if (i5 != 5 && i5 != 6) {
                if (i5 == 0 || i5 == 7) {
                    world.f(i0, i1 + 1, i2, this.cF);
                }
            }
            else {
                world.f(i0, i1 - 1, i2, this.cF);
            }

            return true;
        }
    }

    public void a(World world, int i0, int i1, int i2, int i3, int i4) {
        if ((i4 & 8) > 0) {
            // CanaryMod: RedstoneChange
            new RedstoneChangeHook(new CanaryBlock(BlockType.Lever.getId(), (short)i4, i0, i1, i2, world.canaryDimension), 15, 0).call();
            // Not sure if canceling here would be wise
            // CanaryMod: end
            world.f(i0, i1, i2, this.cF);
            int i5 = i4 & 7;

            if (i5 == 1) {
                world.f(i0 - 1, i1, i2, this.cF);
            }
            else if (i5 == 2) {
                world.f(i0 + 1, i1, i2, this.cF);
            }
            else if (i5 == 3) {
                world.f(i0, i1, i2 - 1, this.cF);
            }
            else if (i5 == 4) {
                world.f(i0, i1, i2 + 1, this.cF);
            }
            else if (i5 != 5 && i5 != 6) {
                if (i5 == 0 || i5 == 7) {
                    world.f(i0, i1 + 1, i2, this.cF);
                }
            }
            else {
                world.f(i0, i1 - 1, i2, this.cF);
            }
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
        }
        else {
            int i5 = i4 & 7;

            return i5 == 0 && i3 == 0 ? 15 : (i5 == 7 && i3 == 0 ? 15 : (i5 == 6 && i3 == 1 ? 15 : (i5 == 5 && i3 == 1 ? 15 : (i5 == 4 && i3 == 2 ? 15 : (i5 == 3 && i3 == 3 ? 15 : (i5 == 2 && i3 == 4 ? 15 : (i5 == 1 && i3 == 5 ? 15 : 0)))))));
        }
    }

    public boolean f() {
        return true;
    }
}
