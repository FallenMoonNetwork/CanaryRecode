package net.minecraft.server;

import java.util.Random;

public class BlockDoor extends Block {

    protected BlockDoor(int i0, Material material) {
        super(i0, material);
        float f0 = 0.5F;
        float f1 = 1.0F;

        this.a(0.5F - f0, 0.0F, 0.5F - f0, 0.5F + f0, f1, 0.5F + f0);
    }

    public boolean c() {
        return false;
    }

    public boolean b(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        int i3 = this.c_(iblockaccess, i0, i1, i2);

        return (i3 & 4) != 0;
    }

    public boolean b() {
        return false;
    }

    public int d() {
        return 7;
    }

    public AxisAlignedBB b(World world, int i0, int i1, int i2) {
        this.a((IBlockAccess) world, i0, i1, i2); // CanaryMod: casting fix
        return super.b(world, i0, i1, i2);
    }

    public void a(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        this.d(this.c_(iblockaccess, i0, i1, i2));
    }

    public int d(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        return this.c_(iblockaccess, i0, i1, i2) & 3;
    }

    public boolean b_(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        return (this.c_(iblockaccess, i0, i1, i2) & 4) != 0;
    }

    private void d(int i0) {
        float f0 = 0.1875F;

        this.a(0.0F, 0.0F, 0.0F, 1.0F, 2.0F, 1.0F);
        int i1 = i0 & 3;
        boolean flag0 = (i0 & 4) != 0;
        boolean flag1 = (i0 & 16) != 0;

        if (i1 == 0) {
            if (flag0) {
                if (!flag1) {
                    this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f0);
                } else {
                    this.a(0.0F, 0.0F, 1.0F - f0, 1.0F, 1.0F, 1.0F);
                }
            } else {
                this.a(0.0F, 0.0F, 0.0F, f0, 1.0F, 1.0F);
            }
        } else if (i1 == 1) {
            if (flag0) {
                if (!flag1) {
                    this.a(1.0F - f0, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                } else {
                    this.a(0.0F, 0.0F, 0.0F, f0, 1.0F, 1.0F);
                }
            } else {
                this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f0);
            }
        } else if (i1 == 2) {
            if (flag0) {
                if (!flag1) {
                    this.a(0.0F, 0.0F, 1.0F - f0, 1.0F, 1.0F, 1.0F);
                } else {
                    this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, f0);
                }
            } else {
                this.a(1.0F - f0, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            }
        } else if (i1 == 3) {
            if (flag0) {
                if (!flag1) {
                    this.a(0.0F, 0.0F, 0.0F, f0, 1.0F, 1.0F);
                } else {
                    this.a(1.0F - f0, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
                }
            } else {
                this.a(0.0F, 0.0F, 1.0F - f0, 1.0F, 1.0F, 1.0F);
            }
        }
    }

    public void a(World world, int i0, int i1, int i2, EntityPlayer entityplayer) {}

    public boolean a(World world, int i0, int i1, int i2, EntityPlayer entityplayer, int i3, float f0, float f1, float f2) {
        if (this.cU == Material.f) {
            return true;
        } else {
            int i4 = this.c_(world, i0, i1, i2);
            int i5 = i4 & 7;

            i5 ^= 4;
            if ((i4 & 8) == 0) {
                world.b(i0, i1, i2, i5, 2);
                world.g(i0, i1, i2, i0, i1, i2);
            } else {
                world.b(i0, i1 - 1, i2, i5, 2);
                world.g(i0, i1 - 1, i2, i0, i1, i2);
            }

            world.a(entityplayer, 1003, i0, i1, i2, 0);
            return true;
        }
    }

    public void a(World world, int i0, int i1, int i2, boolean flag0) {
        int i3 = this.c_(world, i0, i1, i2);
        boolean flag1 = (i3 & 4) != 0;

        if (flag1 != flag0) {
            int i4 = i3 & 7;

            i4 ^= 4;
            if ((i3 & 8) == 0) {
                world.b(i0, i1, i2, i4, 2);
                world.g(i0, i1, i2, i0, i1, i2);
            } else {
                world.b(i0, i1 - 1, i2, i4, 2);
                world.g(i0, i1 - 1, i2, i0, i1, i2);
            }

            world.a((EntityPlayer) null, 1003, i0, i1, i2, 0);
        }
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        int i4 = world.h(i0, i1, i2);

        if ((i4 & 8) == 0) {
            boolean flag0 = false;

            if (world.a(i0, i1 + 1, i2) != this.cF) {
                world.i(i0, i1, i2);
                flag0 = true;
            }

            if (!world.w(i0, i1 - 1, i2)) {
                world.i(i0, i1, i2);
                flag0 = true;
                if (world.a(i0, i1 + 1, i2) == this.cF) {
                    world.i(i0, i1 + 1, i2);
                }
            }

            if (flag0) {
                if (!world.I) {
                    this.c(world, i0, i1, i2, i4, 0);
                }
            } else {
                boolean flag1 = world.C(i0, i1, i2) || world.C(i0, i1 + 1, i2);

                if ((flag1 || i3 > 0 && Block.s[i3].f()) && i3 != this.cF) {
                    this.a(world, i0, i1, i2, flag1);
                }
            }
        } else {
            if (world.a(i0, i1 - 1, i2) != this.cF) {
                world.i(i0, i1, i2);
            }

            if (i3 > 0 && i3 != this.cF) {
                this.a(world, i0, i1 - 1, i2, i3);
            }
        }
    }

    public int a(int i0, Random random, int i1) {
        return (i0 & 8) != 0 ? 0 : (this.cU == Material.f ? Item.aD.cv : Item.ax.cv);
    }

    public MovingObjectPosition a(World world, int i0, int i1, int i2, Vec3 vec3, Vec3 vec31) {
        this.a(world, i0, i1, i2);
        return super.a(world, i0, i1, i2, vec3, vec31);
    }

    public boolean c(World world, int i0, int i1, int i2) {
        return i1 >= 255 ? false : world.w(i0, i1 - 1, i2) && super.c(world, i0, i1, i2) && super.c(world, i0, i1 + 1, i2);
    }

    public int h() {
        return 1;
    }

    public int c_(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        int i3 = iblockaccess.h(i0, i1, i2);
        boolean flag0 = (i3 & 8) != 0;
        int i4;
        int i5;

        if (flag0) {
            i4 = iblockaccess.h(i0, i1 - 1, i2);
            i5 = i3;
        } else {
            i4 = i3;
            i5 = iblockaccess.h(i0, i1 + 1, i2);
        }

        boolean flag1 = (i5 & 1) != 0;

        return i4 & 7 | (flag0 ? 8 : 0) | (flag1 ? 16 : 0);
    }

    public void a(World world, int i0, int i1, int i2, int i3, EntityPlayer entityplayer) {
        if (entityplayer.bG.d && (i3 & 8) != 0 && world.a(i0, i1 - 1, i2) == this.cF) {
            world.i(i0, i1 - 1, i2);
        }
    }
}
