package net.minecraft.server;

import net.canarymod.api.world.blocks.CanaryDaylightDetector;

public class TileEntityDaylightDetector extends TileEntity {

    public TileEntityDaylightDetector() {
        this.complexBlock = new CanaryDaylightDetector(this); // CanaryMod: wrap tile entity
    }

    public void h() {
        if (this.k != null && !this.k.I && this.k.I() % 20L == 0L) {
            this.q = this.q();
            if (this.q != null && this.q instanceof BlockDaylightDetector) {
                ((BlockDaylightDetector)this.q).i_(this.k, this.l, this.m, this.n);
            }
        }
    }

    // CanaryMod
    public CanaryDaylightDetector getCanaryDaylightDetector() {
        return (CanaryDaylightDetector)complexBlock;
    }
}
