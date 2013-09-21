package net.minecraft.server;

import net.canarymod.api.world.blocks.BlockType;
import net.canarymod.api.world.blocks.CanaryBlock;
import net.canarymod.hook.world.RedstoneChangeHook;

import java.util.Random;

public class BlockRedstoneRepeater extends BlockRedstoneLogic {

    public static final double[] b = new double[]{-0.0625D, 0.0625D, 0.1875D, 0.3125D};
    private static final int[] c = new int[]{1, 2, 3, 4};

    protected BlockRedstoneRepeater(int i0, boolean flag0) {
        super(i0, flag0);
    }

    public boolean a(World world, int i0, int i1, int i2, EntityPlayer entityplayer, int i3, float f0, float f1, float f2) {
        int i4 = world.h(i0, i1, i2);
        int i5 = (i4 & 12) >> 2;

        i5 = i5 + 1 << 2 & 12;
        world.b(i0, i1, i2, i5 | i4 & 3, 3);
        return true;
    }

    protected int k_(int i0) {
        return c[(i0 & 12) >> 2] * 2;
    }

    protected BlockRedstoneLogic i() {
        return Block.bn;
    }

    protected BlockRedstoneLogic j() {
        return Block.bm;
    }

    public int a(int i0, Random random, int i1) {
        return Item.bd.cv;
    }

    public int d() {
        return 15;
    }

    public boolean e(IBlockAccess iblockaccess, int i0, int i1, int i2, int i3) {
        return this.f(iblockaccess, i0, i1, i2, i3) > 0;
    }

    protected boolean e(int i0) {
        return f(i0);
    }

    public void a(World world, int i0, int i1, int i2, int i3, int i4) {
        // CanaryMod: RedstoneChange
        if (this.a) {
            new RedstoneChangeHook(new CanaryBlock(BlockType.RedstoneRepeaterOn.getId(), (short)i3, i0, i1, i2, world.getCanaryWorld()), 15, 0).call();
        }
        // CanaryMod: end
        super.a(world, i0, i1, i2, i3, i4);
        this.h_(world, i0, i1, i2);
    }
}
