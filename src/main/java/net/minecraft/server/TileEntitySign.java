package net.minecraft.server;

import net.canarymod.api.world.blocks.CanarySign;

public class TileEntitySign extends TileEntity {

    public String[] a = new String[] { "", "", "", ""};
    public int b = -1;
    private boolean c = true;
    private CanarySign sign; // CanaryMod: sign instance

    public TileEntitySign() {
        this.sign = new CanarySign(this); // CanaryMod: wrap sign
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Text1", this.a[0]);
        nbttagcompound.a("Text2", this.a[1]);
        nbttagcompound.a("Text3", this.a[2]);
        nbttagcompound.a("Text4", this.a[3]);
    }

    public void a(NBTTagCompound nbttagcompound) {
        this.c = false;
        super.a(nbttagcompound);

        for (int i0 = 0; i0 < 4; ++i0) {
            this.a[i0] = nbttagcompound.i("Text" + (i0 + 1));
            if (this.a[i0].length() > 15) {
                this.a[i0] = this.a[i0].substring(0, 15);
            }
        }
    }

    public Packet m() {
        String[] astring = new String[4];

        System.arraycopy(this.a, 0, astring, 0, 4);
        return new Packet130UpdateSign(this.l, this.m, this.n, astring);
    }

    public boolean a() {
        return this.c;
    }

    // CanaryMod
    public CanarySign getCanarySign() {
        return sign;
    }
    //
}
