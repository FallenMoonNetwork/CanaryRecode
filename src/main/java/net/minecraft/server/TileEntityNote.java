package net.minecraft.server;


import net.canarymod.api.world.blocks.CanaryNoteBlock;


public class TileEntityNote extends TileEntity {

    public byte a = 0;
    public boolean b = false;

    public TileEntityNote() {
        this.complexBlock = new CanaryNoteBlock(this); // CanaryMod: wrap tile entity
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("note", this.a);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a = nbttagcompound.c("note");
        if (this.a < 0) {
            this.a = 0;
        }

        if (this.a > 24) {
            this.a = 24;
        }
    }

    public void a() {
        this.a = (byte) ((this.a + 1) % 25);
        this.e();
    }

    public void a(World world, int i0, int i1, int i2) {
        if (world.g(i0, i1 + 1, i2) == Material.a) {
            Material material = world.g(i0, i1 - 1, i2);
            byte b0 = 0;

            if (material == Material.e) {
                b0 = 1;
            }

            if (material == Material.p) {
                b0 = 2;
            }

            if (material == Material.s) {
                b0 = 3;
            }

            if (material == Material.d) {
                b0 = 4;
            }

            world.d(i0, i1, i2, Block.W.cF, b0, this.a);
        }
    }

    // CanaryMod
    public CanaryNoteBlock getCanaryNoteBlock() {
        return (CanaryNoteBlock) complexBlock;
    }
}
