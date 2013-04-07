package net.minecraft.server;


import net.canarymod.api.world.blocks.CanaryComparator;


public class TileEntityComparator extends TileEntity {

    private int a = 0;

    public TileEntityComparator() {
        this.complexBlock = new CanaryComparator(this); // CanaryMod: wrap tile entity
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("OutputSignal", this.a);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a = nbttagcompound.e("OutputSignal");
    }

    public int a() {
        return this.a;
    }

    public void a(int i0) {
        this.a = i0;
    }

    // CanaryMod
    public CanaryComparator getCanaryComparator() {
        return (CanaryComparator) complexBlock;
    }
}
