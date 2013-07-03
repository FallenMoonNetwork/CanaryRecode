package net.minecraft.server;


import java.util.List;
import net.canarymod.Canary;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.world.PistonExtendHook;
import net.canarymod.hook.world.PistonRetractHook;


public class BlockPistonBase extends Block {

    private final boolean a;
    private boolean attemptRetract; // CanaryMod: Used to signal wether to retract the block attached to the stick piston.

    public BlockPistonBase(int i0, boolean flag0) {
        super(i0, Material.G);
        this.a = flag0;
        this.a(k);
        this.c(0.5F);
        this.a(CreativeTabs.d);
    }

    public int d() {
        return 16;
    }

    public boolean c() {
        return false;
    }

    public boolean a(World world, int i0, int i1, int i2, EntityPlayer entityplayer, int i3, float f0, float f1, float f2) {
        return false;
    }

    public void a(World world, int i0, int i1, int i2, EntityLivingBase entitylivingbase, ItemStack itemstack) {
        int i3 = a(world, i0, i1, i2, entitylivingbase);

        world.b(i0, i1, i2, i3, 2);
        if (!world.I) {
            this.k(world, i0, i1, i2);
        }
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        if (!world.I) {
            this.k(world, i0, i1, i2);
        }
    }

    public void a(World world, int i0, int i1, int i2) {
        if (!world.I && world.r(i0, i1, i2) == null) {
            this.k(world, i0, i1, i2);
        }
    }

    private void k(World world, int i0, int i1, int i2) {
        int i3 = world.h(i0, i1, i2);
        int i4 = d(i3);

        if (i4 != 7) {
            boolean flag0 = this.d(world, i0, i1, i2, i4);

            // CanaryMod: Get Blocks
            CanaryBlock piston = new CanaryBlock((this.a ? BlockType.Piston.getId() : BlockType.StickyPiston.getId()), (byte) 0, i0, i1, i2, world.getCanaryWorld());
            CanaryBlock moving = new CanaryBlock((short) world.a(i0 + Facing.b[i4], i1 + Facing.c[i4], i2 + Facing.d[i4]), (byte) 0, (i0 + Facing.b[i4]), (i1 + Facing.c[i4]), (i2 + Facing.d[i4]), world.getCanaryWorld());

            //

            if (flag0 && !e(i3)) {
                if (e(world, i0, i1, i2, i4)) {
                    // CanaryMod: PistonExtend
                    PistonExtendHook hook = new PistonExtendHook(piston, moving);

                    Canary.hooks().callHook(hook);
                    if (!hook.isCanceled()) {
                        world.d(i0, i1, i2, this.cF, 0, i4);
                    }
                    //
                }
            } else if (!flag0 && e(i3)) {
                // CanaryMod: PistonRetract
                moving = new CanaryBlock((short) world.a(i0 + Facing.b[i4] * 2, i1 + Facing.c[i4] * 2, i2 + Facing.d[i4] * 2), (byte) 0, (i0 + Facing.b[i4]), (i1 + Facing.c[i4]), (i2 + Facing.d[i4]), world.getCanaryWorld());
                PistonRetractHook hook = new PistonRetractHook(piston, moving);

                Canary.hooks().callHook(hook);
                attemptRetract = !hook.isCanceled();
                //
                world.b(i0, i1, i2, i4, 2);
                world.d(i0, i1, i2, this.cF, 1, i4);
            }
        }
    }

    private boolean d(World world, int i0, int i1, int i2, int i3) {
        return i3 != 0 && world.k(i0, i1 - 1, i2, 0) ? true : (i3 != 1
                && world.k(i0, i1 + 1, i2, 1) ? true : (i3 != 2
                && world.k(i0, i1, i2 - 1, 2) ? true : (i3 != 3
                && world.k(i0, i1, i2 + 1, 3) ? true : (i3 != 5
                && world.k(i0 + 1, i1, i2, 5) ? true : (i3 != 4
                && world.k(i0 - 1, i1, i2, 4) ? true
                : (world.k(i0, i1, i2, 0) ? true
                        : (world.k(i0, i1 + 2, i2, 1) ? true : (world.k(i0,
                                i1 + 1, i2 - 1, 2) ? true : (world.k(i0,
                                i1 + 1, i2 + 1, 3) ? true : (world.k(i0 - 1,
                                i1 + 1, i2, 4) ? true : world.k(i0 + 1, i1 + 1,
                                i2, 5)))))))))));
    }

    public boolean b(World world, int i0, int i1, int i2, int i3, int i4) {
        if (!world.I) {
            boolean flag0 = this.d(world, i0, i1, i2, i4);

            if (flag0 && i3 == 1) {
                world.b(i0, i1, i2, i4 | 8, 2);
                return false;
            }

            if (!flag0 && i3 == 0) {
                return false;
            }
        }

        if (i3 == 0) {
            if (!this.f(world, i0, i1, i2, i4)) {
                return false;
            }

            world.b(i0, i1, i2, i4 | 8, 2);
            world.a((double) i0 + 0.5D, (double) i1 + 0.5D, (double) i2 + 0.5D, "tile.piston.out", 0.5F, world.s.nextFloat() * 0.25F + 0.6F);
        } else if (i3 == 1) {
            TileEntity tileentity = world.r(i0 + Facing.b[i4], i1 + Facing.c[i4], i2 + Facing.d[i4]);

            if (tileentity instanceof TileEntityPiston) {
                ((TileEntityPiston) tileentity).f();
            }

            world.f(i0, i1, i2, Block.ah.cF, i4, 3);
            world.a(i0, i1, i2, BlockPistonMoving.a(this.cF, i4, i4, false, true));
            if (this.a) {
                int i5 = i0 + Facing.b[i4] * 2;
                int i6 = i1 + Facing.c[i4] * 2;
                int i7 = i2 + Facing.d[i4] * 2;
                int i8 = world.a(i5, i6, i7);
                int i9 = world.h(i5, i6, i7);
                boolean flag1 = false;

                if (i8 == Block.ah.cF) {
                    TileEntity tileentity1 = world.r(i5, i6, i7);

                    if (tileentity1 instanceof TileEntityPiston) {
                        TileEntityPiston tileentitypiston = (TileEntityPiston) tileentity1;

                        if (tileentitypiston.c() == i4 && tileentitypiston.b()) {
                            tileentitypiston.f();
                            i8 = tileentitypiston.a();
                            i9 = tileentitypiston.p();
                            flag1 = true;
                        }
                    }
                }

                // CanaryMod: check attemptRetract
                if (attemptRetract 
                        && !flag1
                        && i8 > 0
                        && a(i8, world, i5, i6, i7, false)
                        && (Block.s[i8].h() == 0 || i8 == Block.ae.cF || i8 == Block.aa.cF)) {
                    i0 += Facing.b[i4];
                    i1 += Facing.c[i4];
                    i2 += Facing.d[i4];
                    world.f(i0, i1, i2, Block.ah.cF, i9, 3);
                    world.a(i0, i1, i2,
                            BlockPistonMoving.a(i8, i9, i4, false, false));
                    world.i(i5, i6, i7);
                } else if (!flag1) {
                    world.i(i0 + Facing.b[i4], i1 + Facing.c[i4], i2 + Facing.d[i4]);
                }
            } else {
                world.i(i0 + Facing.b[i4], i1 + Facing.c[i4], i2 + Facing.d[i4]);
            }

            world.a((double) i0 + 0.5D, (double) i1 + 0.5D, (double) i2 + 0.5D, "tile.piston.in", 0.5F, world.s.nextFloat() * 0.15F + 0.6F);
        }

        return true;
    }

    public void a(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        int i3 = iblockaccess.h(i0, i1, i2);

        if (e(i3)) {
            float f0 = 0.25F;

            switch (d(i3)) {
                case 0:
                    this.a(0.0F, 0.25F, 0.0F, 1.0F, 1.0F, 1.0F);
                    break;

                case 1:
                    this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
                    break;

                case 2:
                    this.a(0.0F, 0.0F, 0.25F, 1.0F, 1.0F, 1.0F);
                    break;

                case 3:
                    this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.75F);
                    break;

                case 4:
                    this.a(0.25F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                    break;

                case 5:
                    this.a(0.0F, 0.0F, 0.0F, 0.75F, 1.0F, 1.0F);
            }
        } else {
            this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        }
    }

    public void g() {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    public void a(World world, int i0, int i1, int i2, AxisAlignedBB axisalignedbb, List list, Entity entity) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        super.a(world, i0, i1, i2, axisalignedbb, list, entity);
    }

    public AxisAlignedBB b(World world, int i0, int i1, int i2) {
        this.a((IBlockAccess) world, i0, i1, i2);
        return super.b(world, i0, i1, i2);
    }

    public boolean b() {
        return false;
    }

    public static int d(int i0) {
        return i0 & 7;
    }

    public static boolean e(int i0) {
        return (i0 & 8) != 0;
    }

    public static int a(World world, int i0, int i1, int i2, EntityLivingBase entitylivingbase) {
        if (MathHelper.e((float) entitylivingbase.u - (float) i0) < 2.0F && MathHelper.e((float) entitylivingbase.w - (float) i2) < 2.0F) {
            double d0 = entitylivingbase.v + 1.82D - (double) entitylivingbase.N;

            if (d0 - (double) i1 > 2.0D) {
                return 1;
            }

            if ((double) i1 - d0 > 0.0D) {
                return 0;
            }
        }

        int i3 = MathHelper.c((double) (entitylivingbase.A * 4.0F / 360.0F) + 0.5D) & 3;

        return i3 == 0 ? 2 : (i3 == 1 ? 5 : (i3 == 2 ? 3 : (i3 == 3 ? 4 : 0)));
    }

    private static boolean a(int i0, World world, int i1, int i2, int i3, boolean flag0) {
        if (i0 == Block.au.cF) {
            return false;
        } else {
            if (i0 != Block.ae.cF && i0 != Block.aa.cF) {
                if (Block.s[i0].l(world, i1, i2, i3) == -1.0F) {
                    return false;
                }

                if (Block.s[i0].h() == 2) {
                    return false;
                }

                if (Block.s[i0].h() == 1) {
                    if (!flag0) {
                        return false;
                    }

                    return true;
                }
            } else if (e(world.h(i1, i2, i3))) {
                return false;
            }

            return !(Block.s[i0] instanceof ITileEntityProvider);
        }
    }

    private static boolean e(World world, int i0, int i1, int i2, int i3) {
        int i4 = i0 + Facing.b[i3];
        int i5 = i1 + Facing.c[i3];
        int i6 = i2 + Facing.d[i3];
        int i7 = 0;

        while (true) {
            if (i7 < 13) {
                if (i5 <= 0 || i5 >= 255) {
                    return false;
                }

                int i8 = world.a(i4, i5, i6);

                if (i8 != 0) {
                    if (!a(i8, world, i4, i5, i6, true)) {
                        return false;
                    }

                    if (Block.s[i8].h() != 1) {
                        if (i7 == 12) {
                            return false;
                        }

                        i4 += Facing.b[i3];
                        i5 += Facing.c[i3];
                        i6 += Facing.d[i3];
                        ++i7;
                        continue;
                    }
                }
            }

            return true;
        }
    }

    private boolean f(World world, int i0, int i1, int i2, int i3) {
        int i4 = i0 + Facing.b[i3];
        int i5 = i1 + Facing.c[i3];
        int i6 = i2 + Facing.d[i3];
        int i7 = 0;

        while (true) {
            int i8;

            if (i7 < 13) {
                if (i5 <= 0 || i5 >= 255) {
                    return false;
                }

                i8 = world.a(i4, i5, i6);
                if (i8 != 0) {
                    if (!a(i8, world, i4, i5, i6, true)) {
                        return false;
                    }

                    if (Block.s[i8].h() != 1) {
                        if (i7 == 12) {
                            return false;
                        }

                        i4 += Facing.b[i3];
                        i5 += Facing.c[i3];
                        i6 += Facing.d[i3];
                        ++i7;
                        continue;
                    }

                    Block.s[i8].c(world, i4, i5, i6, world.h(i4, i5, i6), 0);
                    world.i(i4, i5, i6);
                }
            }

            i7 = i4;
            i8 = i5;
            int i9 = i6;
            int i10 = 0;

            int[] aint;
            int i11;
            int i12;
            int i13;

            for (aint = new int[13]; i4 != i0 || i5 != i1 || i6 != i2; i6 = i13) {
                i11 = i4 - Facing.b[i3];
                i12 = i5 - Facing.c[i3];
                i13 = i6 - Facing.d[i3];
                int i14 = world.a(i11, i12, i13);
                int i15 = world.h(i11, i12, i13);

                if (i14 == this.cF && i11 == i0 && i12 == i1 && i13 == i2) {
                    world.f(i4, i5, i6, Block.ah.cF, i3 | (this.a ? 8 : 0), 4);
                    world.a(i4, i5, i6, BlockPistonMoving.a(Block.af.cF, i3 | (this.a ? 8 : 0), i3, true, false));
                } else {
                    world.f(i4, i5, i6, Block.ah.cF, i15, 4);
                    world.a(i4, i5, i6, BlockPistonMoving.a(i14, i15, i3, true, false));
                }

                aint[i10++] = i14;
                i4 = i11;
                i5 = i12;
            }

            i4 = i7;
            i5 = i8;
            i6 = i9;

            for (i10 = 0; i4 != i0 || i5 != i1 || i6 != i2; i6 = i13) {
                i11 = i4 - Facing.b[i3];
                i12 = i5 - Facing.c[i3];
                i13 = i6 - Facing.d[i3];
                world.f(i11, i12, i13, aint[i10++]);
                i4 = i11;
                i5 = i12;
            }

            return true;
        }
    }
}
