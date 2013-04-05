package net.minecraft.server;

import net.canarymod.api.world.blocks.CanaryDropper;

public class TileEntityDropper extends TileEntityDispenser {

    public TileEntityDropper() {
        this.complexBlock = new CanaryDropper(this); // CanaryMod: wrap tile entity
    }

    public String b() {
        return this.c() ? this.a : "container.dropper";
    }

    // CanaryMod
    public CanaryDropper getCanaryDropper() {
        return (CanaryDropper) complexBlock;
    }
}
