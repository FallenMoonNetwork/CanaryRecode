package net.minecraft.server;

import net.canarymod.api.world.blocks.CanarySign;

public class TileEntitySign extends TileEntity {

    public String[] a = new String[]{"", "", "", ""};
    public int b = -1;
    public boolean c = true; // CanaryMod: private => public; editable
    private EntityPlayer d;
    private String owner_name; // CanaryMod: Track owner name

    public TileEntitySign() {
        this.complexBlock = new CanarySign(this); // CanaryMod: wrap sign
    }

    public void b(NBTTagCompound nbttagcompound) {
        super.b(nbttagcompound);
        nbttagcompound.a("Text1", this.a[0]);
        nbttagcompound.a("Text2", this.a[1]);
        nbttagcompound.a("Text3", this.a[2]);
        nbttagcompound.a("Text4", this.a[3]);
        nbttagcompound.a("Owner", this.d != null ? d.c_() : "");
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
        this.owner_name = nbttagcompound.i("OwnerName");
    }

    public Packet m() {
        String[] astring = new String[4];

        System.arraycopy(this.a, 0, astring, 0, 4);
        return new Packet130UpdateSign(this.l, this.m, this.n, astring);
    }

    public boolean a() {
        return this.c;
    }

    public void a(EntityPlayer entityplayer) {
        this.d = entityplayer;
    }

    public EntityPlayer b() {
        // CanaryMod: Set Player owner if not already set and if they are available to be set
        if (this.d == null) {
            if (this.owner_name.isEmpty()) {
                return null;
            }
            this.d = MinecraftServer.F().af().f(owner_name);
        }
        //
        return this.d;
    }

    // CanaryMod
    public CanarySign getCanarySign() {
        return (CanarySign)complexBlock;
    }

    public String getOwnerName() {
        return owner_name;
    }
    //
}
