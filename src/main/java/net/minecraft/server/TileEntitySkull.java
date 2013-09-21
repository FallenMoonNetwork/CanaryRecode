package net.minecraft.server;

import net.canarymod.api.world.blocks.CanarySkull;

public class TileEntitySkull extends TileEntity {

    private int a;
    private int b;
    private String c = "";

    public TileEntitySkull() {
        this.complexBlock = new CanarySkull(this); // CanaryMod: wrap tile entity
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("SkullType", (byte)(this.a & 255));
        nbttagcompound.a("Rot", (byte)(this.b & 255));
        nbttagcompound.a("ExtraType", this.c);
    }

    public void a(NBTTagCompound nbttagcompound) {
        super.a(nbttagcompound);
        this.a = nbttagcompound.c("SkullType");
        this.b = nbttagcompound.c("Rot");
        if (nbttagcompound.b("ExtraType")) {
            this.c = nbttagcompound.i("ExtraType");
        }
    }

    public Packet m() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        this.b(nbttagcompound);
        return new Packet132TileEntityData(this.l, this.m, this.n, 4, nbttagcompound);
    }

    public void a(int i0, String s0) {
        this.a = i0;
        this.c = s0;
    }

    public int a() {
        return this.a;
    }

    public void a(int i0) {
        this.b = i0;
    }

    public String c() {
        return this.c;
    }

    // CanaryMod
    public int getRotation() {
        return this.b;
    }

    public CanarySkull getCanarySkull() {
        return (CanarySkull)complexBlock;
    }
}
