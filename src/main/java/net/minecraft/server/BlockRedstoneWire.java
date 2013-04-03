package net.minecraft.server;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import net.canarymod.Canary;
import net.canarymod.hook.world.RedstoneChangeHook;

public class BlockRedstoneWire extends Block {

    private boolean a = true;
    private Set b = new HashSet();

    public BlockRedstoneWire(int i0) {
        super(i0, Material.q);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.0625F, 1.0F);
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
        return 5;
    }

    public boolean c(World world, int i0, int i1, int i2) {
        return world.w(i0, i1 - 1, i2) || world.a(i0, i1 - 1, i2) == Block.bh.cz;
    }

    private void k(World world, int i0, int i1, int i2) {
        this.a(world, i0, i1, i2, i0, i1, i2);
        ArrayList arraylist = new ArrayList(this.b);

        this.b.clear();

        for (int i3 = 0; i3 < arraylist.size(); ++i3) {
            ChunkPosition chunkposition = (ChunkPosition) arraylist.get(i3);

            world.f(chunkposition.a, chunkposition.b, chunkposition.c, this.cz);
        }
    }

    private void a(World world, int i0, int i1, int i2, int i3, int i4, int i5) {
        int i6 = world.h(i0, i1, i2);
        byte b0 = 0;
        int i7 = this.d(world, i3, i4, i5, b0);

        this.a = false;
        int i8 = world.D(i0, i1, i2);

        this.a = true;
        if (i8 > 0 && i8 > i7 - 1) {
            i7 = i8;
        }

        int i9 = 0;

        for (int i10 = 0; i10 < 4; ++i10) {
            int i11 = i0;
            int i12 = i2;

            if (i10 == 0) {
                i11 = i0 - 1;
            }

            if (i10 == 1) {
                ++i11;
            }

            if (i10 == 2) {
                i12 = i2 - 1;
            }

            if (i10 == 3) {
                ++i12;
            }

            if (i11 != i3 || i12 != i5) {
                i9 = this.d(world, i11, i1, i12, i9);
            }

            if (world.u(i11, i1, i12) && !world.u(i0, i1 + 1, i2)) {
                if ((i11 != i3 || i12 != i5) && i1 >= i4) {
                    i9 = this.d(world, i11, i1 + 1, i12, i9);
                }
            } else if (!world.u(i11, i1, i12) && (i11 != i3 || i12 != i5) && i1 <= i4) {
                i9 = this.d(world, i11, i1 - 1, i12, i9);
            }
        }

        if (i9 > i7) {
            i7 = i9 - 1;
        } else if (i7 > 0) {
            --i7;
        } else {
            i7 = 0;
        }

        if (i8 > i7 - 1) {
            i7 = i8;
        }

        // CanaryMod: RedstoneChange
        if (i6 != i7) {
            RedstoneChangeHook hook = new RedstoneChangeHook(world.getCanaryWorld().getBlockAt(i0, i1, i2), i6, i7);
            Canary.hooks().callHook(hook);
            if (hook.isCanceled()) {
                return;
            }
        }
        //

        if (i6 != i7) {
            world.b(i0, i1, i2, i7, 2);
            this.b.add(new ChunkPosition(i0, i1, i2));
            this.b.add(new ChunkPosition(i0 - 1, i1, i2));
            this.b.add(new ChunkPosition(i0 + 1, i1, i2));
            this.b.add(new ChunkPosition(i0, i1 - 1, i2));
            this.b.add(new ChunkPosition(i0, i1 + 1, i2));
            this.b.add(new ChunkPosition(i0, i1, i2 - 1));
            this.b.add(new ChunkPosition(i0, i1, i2 + 1));
        }
    }

    private void m(World world, int i0, int i1, int i2) {
        if (world.a(i0, i1, i2) == this.cz) {
            world.f(i0, i1, i2, this.cz);
            world.f(i0 - 1, i1, i2, this.cz);
            world.f(i0 + 1, i1, i2, this.cz);
            world.f(i0, i1, i2 - 1, this.cz);
            world.f(i0, i1, i2 + 1, this.cz);
            world.f(i0, i1 - 1, i2, this.cz);
            world.f(i0, i1 + 1, i2, this.cz);
        }
    }

    public void a(World world, int i0, int i1, int i2) {
        super.a(world, i0, i1, i2);
        if (!world.I) {
            this.k(world, i0, i1, i2);
            world.f(i0, i1 + 1, i2, this.cz);
            world.f(i0, i1 - 1, i2, this.cz);
            this.m(world, i0 - 1, i1, i2);
            this.m(world, i0 + 1, i1, i2);
            this.m(world, i0, i1, i2 - 1);
            this.m(world, i0, i1, i2 + 1);
            if (world.u(i0 - 1, i1, i2)) {
                this.m(world, i0 - 1, i1 + 1, i2);
            } else {
                this.m(world, i0 - 1, i1 - 1, i2);
            }

            if (world.u(i0 + 1, i1, i2)) {
                this.m(world, i0 + 1, i1 + 1, i2);
            } else {
                this.m(world, i0 + 1, i1 - 1, i2);
            }

            if (world.u(i0, i1, i2 - 1)) {
                this.m(world, i0, i1 + 1, i2 - 1);
            } else {
                this.m(world, i0, i1 - 1, i2 - 1);
            }

            if (world.u(i0, i1, i2 + 1)) {
                this.m(world, i0, i1 + 1, i2 + 1);
            } else {
                this.m(world, i0, i1 - 1, i2 + 1);
            }
        }
    }

    public void a(World world, int i0, int i1, int i2, int i3, int i4) {
        super.a(world, i0, i1, i2, i3, i4);
        if (!world.I) {
            world.f(i0, i1 + 1, i2, this.cz);
            world.f(i0, i1 - 1, i2, this.cz);
            world.f(i0 + 1, i1, i2, this.cz);
            world.f(i0 - 1, i1, i2, this.cz);
            world.f(i0, i1, i2 + 1, this.cz);
            world.f(i0, i1, i2 - 1, this.cz);
            this.k(world, i0, i1, i2);
            this.m(world, i0 - 1, i1, i2);
            this.m(world, i0 + 1, i1, i2);
            this.m(world, i0, i1, i2 - 1);
            this.m(world, i0, i1, i2 + 1);
            if (world.u(i0 - 1, i1, i2)) {
                this.m(world, i0 - 1, i1 + 1, i2);
            } else {
                this.m(world, i0 - 1, i1 - 1, i2);
            }

            if (world.u(i0 + 1, i1, i2)) {
                this.m(world, i0 + 1, i1 + 1, i2);
            } else {
                this.m(world, i0 + 1, i1 - 1, i2);
            }

            if (world.u(i0, i1, i2 - 1)) {
                this.m(world, i0, i1 + 1, i2 - 1);
            } else {
                this.m(world, i0, i1 - 1, i2 - 1);
            }

            if (world.u(i0, i1, i2 + 1)) {
                this.m(world, i0, i1 + 1, i2 + 1);
            } else {
                this.m(world, i0, i1 - 1, i2 + 1);
            }
        }
    }

    private int d(World world, int i0, int i1, int i2, int i3) {
        if (world.a(i0, i1, i2) != this.cz) {
            return i3;
        } else {
            int i4 = world.h(i0, i1, i2);

            return i4 > i3 ? i4 : i3;
        }
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        if (!world.I) {
            boolean flag0 = this.c(world, i0, i1, i2);

            if (flag0) {
                this.k(world, i0, i1, i2);
            } else {
                this.c(world, i0, i1, i2, 0, 0);
                world.i(i0, i1, i2);
            }

            super.a(world, i0, i1, i2, i3);
        }
    }

    public int a(int i0, Random random, int i1) {
        return Item.aD.cp;
    }

    public int c(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return !this.a ? 0 : this.b(iblockaccess, i0, i1, i2, i3);
    }

    public int b(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        if (!this.a) {
            return 0;
        } else {
            int i4 = iblockaccess.h(i0, i1, i2);

            if (i4 == 0) {
                return 0;
            } else if (i3 == 1) {
                return i4;
            } else {
                boolean flag0 = g(iblockaccess, i0 - 1, i1, i2, 1) || !iblockaccess.u(i0 - 1, i1, i2) && g(iblockaccess, i0 - 1, i1 - 1, i2, -1);
                boolean flag1 = g(iblockaccess, i0 + 1, i1, i2, 3) || !iblockaccess.u(i0 + 1, i1, i2) && g(iblockaccess, i0 + 1, i1 - 1, i2, -1);
                boolean flag2 = g(iblockaccess, i0, i1, i2 - 1, 2) || !iblockaccess.u(i0, i1, i2 - 1) && g(iblockaccess, i0, i1 - 1, i2 - 1, -1);
                boolean flag3 = g(iblockaccess, i0, i1, i2 + 1, 0) || !iblockaccess.u(i0, i1, i2 + 1) && g(iblockaccess, i0, i1 - 1, i2 + 1, -1);

                if (!iblockaccess.u(i0, i1 + 1, i2)) {
                    if (iblockaccess.u(i0 - 1, i1, i2) && g(iblockaccess, i0 - 1, i1 + 1, i2, -1)) {
                        flag0 = true;
                    }

                    if (iblockaccess.u(i0 + 1, i1, i2) && g(iblockaccess, i0 + 1, i1 + 1, i2, -1)) {
                        flag1 = true;
                    }

                    if (iblockaccess.u(i0, i1, i2 - 1) && g(iblockaccess, i0, i1 + 1, i2 - 1, -1)) {
                        flag2 = true;
                    }

                    if (iblockaccess.u(i0, i1, i2 + 1) && g(iblockaccess, i0, i1 + 1, i2 + 1, -1)) {
                        flag3 = true;
                    }
                }

                return !flag2 && !flag1 && !flag0 && !flag3 && i3 >= 2 && i3 <= 5 ? i4 : (i3 == 2 && flag2 && !flag0 && !flag1 ? i4 : (i3 == 3 && flag3 && !flag0 && !flag1 ? i4 : (i3 == 4 && flag0 && !flag2 && !flag3 ? i4 : (i3 == 5 && flag1 && !flag2 && !flag3 ? i4 : 0))));
            }
        }
    }

    public boolean f() {
        return this.a;
    }

    public static boolean f(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        int i4 = iblockaccess.a(i0, i1, i2);

        if (i4 == Block.az.cz) {
            return true;
        } else if (i4 == 0) {
            return false;
        } else if (!Block.bl.g(i4)) {
            return Block.r[i4].f() && i3 != -1;
        } else {
            int i5 = iblockaccess.h(i0, i1, i2);

            return i3 == (i5 & 3) || i3 == Direction.f[i5 & 3];
        }
    }

    public static boolean g(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        if (f(iblockaccess, i0, i1, i2, i3)) {
            return true;
        } else {
            int i4 = iblockaccess.a(i0, i1, i2);

            if (i4 == Block.bm.cz) {
                int i5 = iblockaccess.h(i0, i1, i2);

                return i3 == (i5 & 3);
            } else {
                return false;
            }
        }
    }
}
