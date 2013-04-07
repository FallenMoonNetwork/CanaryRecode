package net.minecraft.server;


import java.util.Random;
import net.canarymod.Canary;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.world.PortalCreateHook;


public class BlockPortal extends BlockBreakable {

    public BlockPortal(int i0) {
        super(i0, "portal", Material.C, false);
        this.b(true);
    }

    public void a(World world, int i0, int i1, int i2, Random random) {
        super.a(world, i0, i1, i2, random);
        if (world.t.d() && random.nextInt(2000) < world.r) {
            int i3;

            for (i3 = i1; !world.w(i0, i3, i2) && i3 > 0; --i3) {
                ;
            }

            if (i3 > 0 && !world.u(i0, i3 + 1, i2)) {
                Entity entity = ItemMonsterPlacer.a(world, 57, (double) i0 + 0.5D, (double) i3 + 1.1D, (double) i2 + 0.5D);

                if (entity != null) {
                    entity.ao = entity.aa();
                }
            }
        }
    }

    public AxisAlignedBB b(World world, int i0, int i1, int i2) {
        return null;
    }

    public void a(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        float f0;
        float f1;

        if (iblockaccess.a(i0 - 1, i1, i2) != this.cz && iblockaccess.a(i0 + 1, i1, i2) != this.cz) {
            f0 = 0.125F;
            f1 = 0.5F;
            this.a(0.5F - f0, 0.0F, 0.5F - f1, 0.5F + f0, 1.0F, 0.5F + f1);
        } else {
            f0 = 0.5F;
            f1 = 0.125F;
            this.a(0.5F - f0, 0.0F, 0.5F - f1, 0.5F + f0, 1.0F, 0.5F + f1);
        }
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public boolean n_(World world, int i0, int i1, int i2) {
        byte b0 = 0;
        byte b1 = 0;

        if (world.a(i0 - 1, i1, i2) == Block.at.cz || world.a(i0 + 1, i1, i2) == Block.at.cz) {
            b0 = 1;
        }

        if (world.a(i0, i1, i2 - 1) == Block.at.cz || world.a(i0, i1, i2 + 1) == Block.at.cz) {
            b1 = 1;
        }

        if (b0 == b1) {
            return false;
        } else {
            if (world.a(i0 - b0, i1, i2 - b1) == 0) {
                i0 -= b0;
                i2 -= b1;
            }

            int i3;
            int i4;

            for (i3 = -1; i3 <= 2; ++i3) {
                for (i4 = -1; i4 <= 3; ++i4) {
                    boolean flag0 = i3 == -1 || i3 == 2 || i4 == -1 || i4 == 3;

                    if (i3 != -1 && i3 != 2 || i4 != -1 && i4 != 3) {
                        int i5 = world.a(i0 + b0 * i3, i1 + i4, i2 + b1 * i3);

                        if (flag0) {
                            if (i5 != Block.at.cz) {
                                return false;
                            }
                        } else if (i5 != 0 && i5 != Block.av.cz) {
                            return false;
                        }
                    }
                }
            }

            // CanaryMod: PortalCreate
            CanaryBlock[][] portalBlocks = new CanaryBlock[3][2];

            for (i4 = 0; i4 < 3; ++i4) {
                for (i3 = 0; i3 < 2; ++i3) {
                    portalBlocks[i4][i3] = (CanaryBlock) world.getCanaryWorld().getBlockAt(i0 + b0 * i3, i1 + 2 - i4, i2 + b1 * i3);
                }
            }
            PortalCreateHook hook = new PortalCreateHook(portalBlocks);

            Canary.hooks().callHook(hook);
            if (!hook.isCanceled()) {
                for (i3 = 0; i3 < 2; ++i3) {
                    for (i4 = 0; i4 < 3; ++i4) {
                        world.f(i0 + b0 * i3, i1 + i4, i2 + b1 * i3, Block.bi.cz, 0, 2);
                    }
                }
                return true;
            }
            //
            return false;
        }
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        byte b0 = 0;
        byte b1 = 1;

        if (world.a(i0 - 1, i1, i2) == this.cz || world.a(i0 + 1, i1, i2) == this.cz) {
            b0 = 1;
            b1 = 0;
        }

        int i4;

        for (i4 = i1; world.a(i0, i4 - 1, i2) == this.cz; --i4) {
            ;
        }

        if (world.a(i0, i4 - 1, i2) != Block.at.cz) {
            world.i(i0, i1, i2);
        } else {
            int i5;

            for (i5 = 1; i5 < 4 && world.a(i0, i4 + i5, i2) == this.cz; ++i5) {
                ;
            }

            if (i5 == 3 && world.a(i0, i4 + i5, i2) == Block.at.cz) {
                boolean flag0 = world.a(i0 - 1, i1, i2) == this.cz || world.a(i0 + 1, i1, i2) == this.cz;
                boolean flag1 = world.a(i0, i1, i2 - 1) == this.cz || world.a(i0, i1, i2 + 1) == this.cz;

                if (flag0 && flag1) {
                    world.i(i0, i1, i2);
                } else {
                    if ((world.a(i0 + b0, i1, i2 + b1) != Block.at.cz || world.a(i0 - b0, i1, i2 - b1) != this.cz) && (world.a(i0 - b0, i1, i2 - b1) != Block.at.cz || world.a(i0 + b0, i1, i2 + b1) != this.cz)) {
                        world.i(i0, i1, i2);
                    }
                }
            } else {
                world.i(i0, i1, i2);
            }
        }
    }

    public int a(Random random) {
        return 0;
    }

    public void a(World world, int i0, int i1, int i2, Entity entity) {
        if (entity.o == null && entity.n == null) {
            entity.Z();
        }
    }
}
