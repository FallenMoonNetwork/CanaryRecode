package net.minecraft.server;

import net.canarymod.hook.world.DispenseHook;

import java.util.Random;

public class BlockDispenser extends BlockContainer {

    public static final IRegistry a = new RegistryDefaulted(new BehaviorDefaultDispenseItem());
    protected Random b = new Random();

    protected BlockDispenser(int i0) {
        super(i0, Material.e);
        this.a(CreativeTabs.d);
    }

    public int a(World world) {
        return 4;
    }

    public void a(World world, int i0, int i1, int i2) {
        super.a(world, i0, i1, i2);
        this.k(world, i0, i1, i2);
    }

    private void k(World world, int i0, int i1, int i2) {
        if (!world.I) {
            int i3 = world.a(i0, i1, i2 - 1);
            int i4 = world.a(i0, i1, i2 + 1);
            int i5 = world.a(i0 - 1, i1, i2);
            int i6 = world.a(i0 + 1, i1, i2);
            byte b0 = 3;

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

            world.b(i0, i1, i2, b0, 2);
        }
    }

    public boolean a(World world, int i0, int i1, int i2, EntityPlayer entityplayer, int i3, float f0, float f1, float f2) {
        if (world.I) {
            return true;
        }
        else {
            TileEntityDispenser tileentitydispenser = (TileEntityDispenser)world.r(i0, i1, i2);

            if (tileentitydispenser != null) {
                entityplayer.a(tileentitydispenser);
            }

            return true;
        }
    }

    protected void j_(World world, int i0, int i1, int i2) {
        BlockSourceImpl blocksourceimpl = new BlockSourceImpl(world, i0, i1, i2);
        TileEntityDispenser tileentitydispenser = (TileEntityDispenser)blocksourceimpl.j();

        if (tileentitydispenser != null) {
            int i3 = tileentitydispenser.j();

            if (i3 < 0) {
                // CanaryMod: Dispense Smoke
                DispenseHook hook = (DispenseHook)new DispenseHook(tileentitydispenser.getCanaryDispenser(), null).call();
                if (!hook.isCanceled()) {
                    world.e(1001, i0, i1, i2, 0);
                }
                //
            }
            else {
                ItemStack itemstack = tileentitydispenser.a(i3);
                IBehaviorDispenseItem ibehaviordispenseitem = this.a(itemstack);

                if (ibehaviordispenseitem != IBehaviorDispenseItem.a) {
                    ItemStack itemstack1 = ibehaviordispenseitem.a(blocksourceimpl, itemstack);

                    tileentitydispenser.a(i3, itemstack1.b == 0 ? null : itemstack1);
                }
            }
        }
    }

    protected IBehaviorDispenseItem a(ItemStack itemstack) {
        return (IBehaviorDispenseItem)a.a(itemstack.b());
    }

    public void a(World world, int i0, int i1, int i2, int i3) {
        boolean flag0 = world.C(i0, i1, i2) || world.C(i0, i1 + 1, i2);
        int i4 = world.h(i0, i1, i2);
        boolean flag1 = (i4 & 8) != 0;

        if (flag0 && !flag1) {
            world.a(i0, i1, i2, this.cF, this.a(world));
            world.b(i0, i1, i2, i4 | 8, 4);
        }
        else if (!flag0 && flag1) {
            world.b(i0, i1, i2, i4 & -9, 4);
        }
    }

    public void a(World world, int i0, int i1, int i2, Random random) {
        if (!world.I) {
            this.j_(world, i0, i1, i2);
        }
    }

    public TileEntity b(World world) {
        return new TileEntityDispenser();
    }

    public void a(World world, int i0, int i1, int i2, EntityLivingBase entitylivingbase, ItemStack itemstack) {
        int i3 = BlockPistonBase.a(world, i0, i1, i2, entitylivingbase);

        world.b(i0, i1, i2, i3, 2);
        if (itemstack.u()) {
            ((TileEntityDispenser)world.r(i0, i1, i2)).a(itemstack.s());
        }
    }

    public void a(World world, int i0, int i1, int i2, int i3, int i4) {
        TileEntityDispenser tileentitydispenser = (TileEntityDispenser)world.r(i0, i1, i2);

        if (tileentitydispenser != null) {
            for (int i5 = 0; i5 < tileentitydispenser.j_(); ++i5) {
                ItemStack itemstack = tileentitydispenser.a(i5);

                if (itemstack != null) {
                    float f0 = this.b.nextFloat() * 0.8F + 0.1F;
                    float f1 = this.b.nextFloat() * 0.8F + 0.1F;
                    float f2 = this.b.nextFloat() * 0.8F + 0.1F;

                    while (itemstack.b > 0) {
                        int i6 = this.b.nextInt(21) + 10;

                        if (i6 > itemstack.b) {
                            i6 = itemstack.b;
                        }

                        itemstack.b -= i6;
                        EntityItem entityitem = new EntityItem(world, (double)((float)i0 + f0), (double)((float)i1 + f1), (double)((float)i2 + f2), new ItemStack(itemstack.d, i6, itemstack.k()));

                        if (itemstack.p()) {
                            entityitem.d().d((NBTTagCompound)itemstack.q().b());
                        }

                        float f3 = 0.05F;

                        entityitem.x = (double)((float)this.b.nextGaussian() * f3);
                        entityitem.y = (double)((float)this.b.nextGaussian() * f3 + 0.2F);
                        entityitem.z = (double)((float)this.b.nextGaussian() * f3);
                        world.d((Entity)entityitem);
                    }
                }
            }

            world.m(i0, i1, i2, i3);
        }

        super.a(world, i0, i1, i2, i3, i4);
    }

    public static IPosition a(IBlockSource iblocksource) {
        EnumFacing enumfacing = l_(iblocksource.h());
        double d0 = iblocksource.a() + 0.7D * (double)enumfacing.c();
        double d1 = iblocksource.b() + 0.7D * (double)enumfacing.d();
        double d2 = iblocksource.c() + 0.7D * (double)enumfacing.e();

        return new PositionImpl(d0, d1, d2);
    }

    public static EnumFacing l_(int i0) {
        return EnumFacing.a(i0 & 7);
    }

    public boolean q_() {
        return true;
    }

    public int b_(World world, int i0, int i1, int i2, int i3) {
        return Container.b((IInventory)world.r(i0, i1, i2));
    }
}
