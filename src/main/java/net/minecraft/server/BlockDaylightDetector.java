package net.minecraft.server;

import java.util.Random;
import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.world.RedstoneChangeHook;

public class BlockDaylightDetector extends BlockContainer {

    private Icon[] a = new Icon[2];

    public BlockDaylightDetector(int i0) {
        super(i0, Material.d);
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.375F, 1.0F);
        this.a(CreativeTabs.d);
    }

    public void a(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.375F, 1.0F);
    }

    public int b(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return iblockaccess.h(i0, i1, i2);
    }

    public void a(World world, int i0, int i1, int i2, Random random) {}

    public void a(World world, int i0, int i1, int i2, int i3) {}

    public void a(World world, int i0, int i1, int i2) {}

    // CanaryMod: include break method so we can do a redstone change on destruction
    public void a(World world, int i0, int i1, int i2, int i3, int i4) {
        // CanaryMod: Redstone Change; broken
        int oldLvl = world.b(EnumSkyBlock.a, i0, i1, i2) - world.j;
        float f0 = world.d(1.0F);
        if (f0 < 3.1415927F) {
            f0 += (0.0F - f0) * 0.2F;
        } else {
            f0 += (6.2831855F - f0) * 0.2F;
        }
        oldLvl = Math.round((float) i4 * MathHelper.b(f0));
        if (oldLvl < 0) {
            oldLvl = 0;
        }
        if (oldLvl > 15) {
            oldLvl = 15;
        }
        if (oldLvl != 0) {
            new RedstoneChangeHook(new CanaryBlock(BlockType.DaylightSensor.getId(), (short) 2, i0, i1, i2, world.getCanaryWorld()), oldLvl, 0).call();
        }
        //
        super.a(world, i0, i1, i2, i3, i4); // CanaryMod: call super
    }

    public void i_(World world, int i0, int i1, int i2) {
        if (!world.t.g) {
            int i3 = world.h(i0, i1, i2);
            int i4 = world.b(EnumSkyBlock.a, i0, i1, i2) - world.j;
            float f0 = world.d(1.0F);

            if (f0 < 3.1415927F) {
                f0 += (0.0F - f0) * 0.2F;
            } else {
                f0 += (6.2831855F - f0) * 0.2F;
            }

            i4 = Math.round((float) i4 * MathHelper.b(f0));
            if (i4 < 0) {
                i4 = 0;
            }

            if (i4 > 15) {
                i4 = 15;
            }

            if (i3 != i4) {
                // CanaryMod: RedstoneChange; Comparator change
                RedstoneChangeHook hook = (RedstoneChangeHook) new RedstoneChangeHook(world.getCanaryWorld().getBlockAt(i0, i1, i2), i3, i4).call();
                if (hook.isCanceled()) {
                    return;
                }
                //
                world.b(i0, i1, i2, i4, 3);
            }
        }
    }

    public boolean b() {
        return false;
    }

    public boolean c() {
        return false;
    }

    public boolean f() {
        return true;
    }

    public TileEntity b(World world) {
        return new TileEntityDaylightDetector();
    }
}
