package net.minecraft.server;

public class BlockWall extends Block {

    public static final String[] a = new String[]{ "normal", "mossy" };

    public BlockWall(int i0, Block block) {
        super(i0, block.cU);
        this.c(block.cG);
        this.b(block.cH / 3.0F);
        this.a(block.cS);
        this.a(CreativeTabs.b);
    }

    public int d() {
        return 32;
    }

    public boolean b() {
        return false;
    }

    public boolean b(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        return false;
    }

    public boolean c() {
        return false;
    }

    public void a(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        boolean flag0 = this.d(iblockaccess, i0, i1, i2 - 1);
        boolean flag1 = this.d(iblockaccess, i0, i1, i2 + 1);
        boolean flag2 = this.d(iblockaccess, i0 - 1, i1, i2);
        boolean flag3 = this.d(iblockaccess, i0 + 1, i1, i2);
        float f0 = 0.25F;
        float f1 = 0.75F;
        float f2 = 0.25F;
        float f3 = 0.75F;
        float f4 = 1.0F;

        if (flag0) {
            f2 = 0.0F;
        }

        if (flag1) {
            f3 = 1.0F;
        }

        if (flag2) {
            f0 = 0.0F;
        }

        if (flag3) {
            f1 = 1.0F;
        }

        if (flag0 && flag1 && !flag2 && !flag3) {
            f4 = 0.8125F;
            f0 = 0.3125F;
            f1 = 0.6875F;
        } else if (!flag0 && !flag1 && flag2 && flag3) {
            f4 = 0.8125F;
            f2 = 0.3125F;
            f3 = 0.6875F;
        }

        this.a(f0, 0.0F, f2, f1, f4, f3);
    }

    public AxisAlignedBB b(World world, int i0, int i1, int i2) {
        this.a((IBlockAccess) world, i0, i1, i2); // CanaryMod Cast to IBlockAccess
        this.cQ = 1.5D;
        return super.b(world, i0, i1, i2);
    }

    public boolean d(IBlockAccess iblockaccess, int i0, int i1, int i2) {
        int i3 = iblockaccess.a(i0, i1, i2);

        if (i3 != this.cF && i3 != Block.bA.cF) {
            Block block = Block.s[i3];

            return block != null && block.cU.k() && block.b() ? block.cU != Material.B : false;
        } else {
            return true;
        }
    }

    public int a(int i0) {
        return i0;
    }
}
