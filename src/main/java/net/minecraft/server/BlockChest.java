package net.minecraft.server;

import java.util.Iterator;
import java.util.Random;
import net.canarymod.hook.world.RedstoneChangeHook;

public class BlockChest extends BlockContainer {

    private final Random b = new Random();
    public final int a;
    private int oldLvl; // CanaryMod: store old

    protected BlockChest(int i0, int i1) {
        super(i0, Material.d);
        this.a = i1;
        this.a(CreativeTabs.c);
        this.a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
    }

    public boolean c() {
        return false;
    }

    public boolean b() {
        return false;
    }

    public int d() {
        return 22;
    }

    public void a(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        if (iblockaccess.a(i0, i1, i2 - 1) == this.cF) {
            this.a(0.0625F, 0.0F, 0.0F, 0.9375F, 0.875F, 0.9375F);
        } else if (iblockaccess.a(i0, i1, i2 + 1) == this.cF) {
            this.a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 1.0F);
        } else if (iblockaccess.a(i0 - 1, i1, i2) == this.cF) {
            this.a(0.0F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        } else if (iblockaccess.a(i0 + 1, i1, i2) == this.cF) {
            this.a(0.0625F, 0.0F, 0.0625F, 1.0F, 0.875F, 0.9375F);
        } else {
            this.a(0.0625F, 0.0F, 0.0625F, 0.9375F, 0.875F, 0.9375F);
        }
    }

    public void a(World world, int i0, int i1, int i2) {
        super.a(world, i0, i1, i2);
        this.f_(world, i0, i1, i2);
        int i3 = world.a(i0, i1, i2 - 1);
        int i4 = world.a(i0, i1, i2 + 1);
        int i5 = world.a(i0 - 1, i1, i2);
        int i6 = world.a(i0 + 1, i1, i2);

        if (i3 == this.cF) {
            this.f_(world, i0, i1, i2 - 1);
        }

        if (i4 == this.cF) {
            this.f_(world, i0, i1, i2 + 1);
        }

        if (i5 == this.cF) {
            this.f_(world, i0 - 1, i1, i2);
        }

        if (i6 == this.cF) {
            this.f_(world, i0 + 1, i1, i2);
        }
    }

    public void a(World world, int i0, int i1, int i2, EntityLivingBase entitylivingbase, ItemStack itemstack) {
        int i3 = world.a(i0, i1, i2 - 1);
        int i4 = world.a(i0, i1, i2 + 1);
        int i5 = world.a(i0 - 1, i1, i2);
        int i6 = world.a(i0 + 1, i1, i2);
        byte b0 = 0;
        int i7 = MathHelper.c((double) (entitylivingbase.A * 4.0F / 360.0F) + 0.5D) & 3;

        if (i7 == 0) {
            b0 = 2;
        }

        if (i7 == 1) {
            b0 = 5;
        }

        if (i7 == 2) {
            b0 = 3;
        }

        if (i7 == 3) {
            b0 = 4;
        }

        if (i3 != this.cF && i4 != this.cF && i5 != this.cF && i6 != this.cF) {
            world.b(i0, i1, i2, b0, 3);
        } else {
            if ((i3 == this.cF || i4 == this.cF) && (b0 == 4 || b0 == 5)) {
                if (i3 == this.cF) {
                    world.b(i0, i1, i2 - 1, b0, 3);
                } else {
                    world.b(i0, i1, i2 + 1, b0, 3);
                }

                world.b(i0, i1, i2, b0, 3);
            }

            if ((i5 == this.cF || i6 == this.cF) && (b0 == 2 || b0 == 3)) {
                if (i5 == this.cF) {
                    world.b(i0 - 1, i1, i2, b0, 3);
                } else {
                    world.b(i0 + 1, i1, i2, b0, 3);
                }

                world.b(i0, i1, i2, b0, 3);
            }
        }

        if (itemstack.u()) {
            ((TileEntityChest) world.r(i0, i1, i2)).a(itemstack.s());
        }
    }

    public void f_(World world, int i0, int i1, int i2) {
        if (!world.I) {
            int i3 = world.a(i0, i1, i2 - 1);
            int i4 = world.a(i0, i1, i2 + 1);
            int i5 = world.a(i0 - 1, i1, i2);
            int i6 = world.a(i0 + 1, i1, i2);
            boolean flag0 = true;
            int i7;
            int i8;
            boolean flag1;
            byte b0;
            int i9;

            if (i3 != this.cF && i4 != this.cF) {
                if (i5 != this.cF && i6 != this.cF) {
                    b0 = 3;
                    if (Block.t[i3] && !Block.t[i4]) {
                        b0 = 3;
                    }

                    if (Block.t[i4] && !Block.t[i3]) {
                        b0 = 2;
                    }

                    if (Block.t[i5] && !Block.t[i6]) {
                        b0 = 5;
                    }

                    if (Block.t[i6] && !Block.t[i5]) {
                        b0 = 4;
                    }
                } else {
                    i7 = world.a(i5 == this.cF ? i0 - 1 : i0 + 1, i1, i2 - 1);
                    i8 = world.a(i5 == this.cF ? i0 - 1 : i0 + 1, i1, i2 + 1);
                    b0 = 3;
                    flag1 = true;
                    if (i5 == this.cF) {
                        i9 = world.h(i0 - 1, i1, i2);
                    } else {
                        i9 = world.h(i0 + 1, i1, i2);
                    }

                    if (i9 == 2) {
                        b0 = 2;
                    }

                    if ((Block.t[i3] || Block.t[i7]) && !Block.t[i4] && !Block.t[i8]) {
                        b0 = 3;
                    }

                    if ((Block.t[i4] || Block.t[i8]) && !Block.t[i3] && !Block.t[i7]) {
                        b0 = 2;
                    }
                }
            } else {
                i7 = world.a(i0 - 1, i1, i3 == this.cF ? i2 - 1 : i2 + 1);
                i8 = world.a(i0 + 1, i1, i3 == this.cF ? i2 - 1 : i2 + 1);
                b0 = 5;
                flag1 = true;
                if (i3 == this.cF) {
                    i9 = world.h(i0, i1, i2 - 1);
                } else {
                    i9 = world.h(i0, i1, i2 + 1);
                }

                if (i9 == 4) {
                    b0 = 4;
                }

                if ((Block.t[i5] || Block.t[i7]) && !Block.t[i6] && !Block.t[i8]) {
                    b0 = 5;
                }

                if ((Block.t[i6] || Block.t[i8]) && !Block.t[i5] && !Block.t[i7]) {
                    b0 = 4;
                }
            }

            world.b(i0, i1, i2, b0, 3);
        }
    }

    public boolean c(World world, int i0, int i1, int i2) {
        int i3 = 0;

        if (world.a(i0 - 1, i1, i2) == this.cF) {
            ++i3;
        }

        if (world.a(i0 + 1, i1, i2) == this.cF) {
            ++i3;
        }

        if (world.a(i0, i1, i2 - 1) == this.cF) {
            ++i3;
        }

        if (world.a(i0, i1, i2 + 1) == this.cF) {
            ++i3;
        }

        return i3 > 1 ? false : (this.k(world, i0 - 1, i1, i2) ? false : (this.k(world, i0 + 1, i1, i2) ? false : (this.k(world, i0, i1, i2 - 1) ? false : !this.k(world, i0, i1, i2 + 1))));
    }

    private boolean k(World world, int i0, int i1, int i2) {
        return world.a(i0, i1, i2) != this.cF ? false : (world.a(i0 - 1, i1, i2) == this.cF ? true : (world.a(i0 + 1, i1, i2) == this.cF ? true : (world.a(i0, i1, i2 - 1) == this.cF ? true : world.a(i0, i1, i2 + 1) == this.cF)));
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        super.a(world, i0, i1, i2, i3);
        TileEntityChest tileentitychest = (TileEntityChest) world.r(i0, i1, i2);

        if (tileentitychest != null) {
            tileentitychest.i();
        }
    }

    public void a(World world, int i0, int i1, int i2, int i3, int i4) {
        TileEntityChest tileentitychest = (TileEntityChest) world.r(i0, i1, i2);

        if (tileentitychest != null) {
            for (int i5 = 0; i5 < tileentitychest.j_(); ++i5) {
                ItemStack itemstack = tileentitychest.a(i5);

                if (itemstack != null) {
                    float f0 = this.b.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.b.nextFloat() * 0.8F + 0.1F;

                    EntityItem entityitem;

                    for (float f2 = this.b.nextFloat() * 0.8F + 0.1F; itemstack.b > 0; world.d((Entity) entityitem)) {
                        int i6 = this.b.nextInt(21) + 10;

                        if (i6 > itemstack.b) {
                            i6 = itemstack.b;
                        }

                        itemstack.b -= i6;
                        entityitem = new EntityItem(world, (double) ((float) i0 + f0), (double) ((float) i1 + f1), (double) ((float) i2 + f2), new ItemStack(itemstack.d, i6, itemstack.k()));
                        float f3 = 0.05F;

                        entityitem.x = (double) ((float) this.b.nextGaussian() * f3);
                        entityitem.y = (double) ((float) this.b.nextGaussian() * f3 + 0.2F);
                        entityitem.z = (double) ((float) this.b.nextGaussian() * f3);
                        if (itemstack.p()) {
                            entityitem.d().d((NBTTagCompound) itemstack.q().b());
                        }
                    }
                }
            }

            world.m(i0, i1, i2, i3);
        }

        super.a(world, i0, i1, i2, i3, i4);
    }

    public boolean a(World world, int i0, int i1, int i2, EntityPlayer entityplayer, int i3, float f0, float f1, float f2) {
        if (world.I) {
            return true;
        } else {
            IInventory iinventory = this.g_(world, i0, i1, i2);

            if (iinventory != null) {
                entityplayer.a(iinventory);
            }

            return true;
        }
    }

    public IInventory g_(World world, int i0, int i1, int i2) {
        Object object = (TileEntityChest) world.r(i0, i1, i2);

        if (object == null) {
            return null;
        } else if (world.u(i0, i1 + 1, i2)) {
            return null;
        } else if (m(world, i0, i1, i2)) {
            return null;
        } else if (world.a(i0 - 1, i1, i2) == this.cF && (world.u(i0 - 1, i1 + 1, i2) || m(world, i0 - 1, i1, i2))) {
            return null;
        } else if (world.a(i0 + 1, i1, i2) == this.cF && (world.u(i0 + 1, i1 + 1, i2) || m(world, i0 + 1, i1, i2))) {
            return null;
        } else if (world.a(i0, i1, i2 - 1) == this.cF && (world.u(i0, i1 + 1, i2 - 1) || m(world, i0, i1, i2 - 1))) {
            return null;
        } else if (world.a(i0, i1, i2 + 1) == this.cF && (world.u(i0, i1 + 1, i2 + 1) || m(world, i0, i1, i2 + 1))) {
            return null;
        } else {
            if (world.a(i0 - 1, i1, i2) == this.cF) {
                object = new InventoryLargeChest("container.chestDouble", (TileEntityChest) world.r(i0 - 1, i1, i2), (IInventory) object);
            }

            if (world.a(i0 + 1, i1, i2) == this.cF) {
                object = new InventoryLargeChest("container.chestDouble", (IInventory) object, (TileEntityChest) world.r(i0 + 1, i1, i2));
            }

            if (world.a(i0, i1, i2 - 1) == this.cF) {
                object = new InventoryLargeChest("container.chestDouble", (TileEntityChest) world.r(i0, i1, i2 - 1), (IInventory) object);
            }

            if (world.a(i0, i1, i2 + 1) == this.cF) {
                object = new InventoryLargeChest("container.chestDouble", (IInventory) object, (TileEntityChest) world.r(i0, i1, i2 + 1));
            }

            return (IInventory) object;
        }
    }

    public TileEntity b(World world) {
        TileEntityChest tileentitychest = new TileEntityChest();

        return tileentitychest;
    }

    public boolean f() {
        return this.a == 1;
    }

    public int b(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        if (!this.f()) {
            return 0;
        } else {
            int i4 = ((TileEntityChest) iblockaccess.r(i0, i1, i2)).h;
            // CanaryMod: RedstoneChange
            int newLvl = MathHelper.a(i4, 0, 15);
            if (newLvl != oldLvl) {
                RedstoneChangeHook hook = (RedstoneChangeHook) new RedstoneChangeHook(((World) iblockaccess).getCanaryWorld().getBlockAt(i0, i1, i2), oldLvl, newLvl).call();
                if (hook.isCanceled()) {
                    return oldLvl;
                }
            }
            return oldLvl = newLvl;
            //
        }
    }

    public int c(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return i3 == 1 ? this.b(iblockaccess, i0, i1, i2, i3) : 0;
    }

    private static boolean m(World world, int i0, int i1, int i2) {
        Iterator iterator = world.a(EntityOcelot.class, AxisAlignedBB.a().a((double) i0, (double) (i1 + 1), (double) i2, (double) (i0 + 1), (double) (i1 + 2), (double) (i2 + 1))).iterator();

        EntityOcelot entityocelot;

        do {
            if (!iterator.hasNext()) {
                return false;
            }

            EntityOcelot entityocelot1 = (EntityOcelot) iterator.next();

            entityocelot = (EntityOcelot) entityocelot1;
        }
        while (!entityocelot.bU());

        return true;
    }

    public boolean q_() {
        return true;
    }

    public int b_(World world, int i0, int i1, int i2, int i3) {
        return Container.b(this.g_(world, i0, i1, i2));
    }
}
