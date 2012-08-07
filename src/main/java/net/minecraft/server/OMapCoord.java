package net.minecraft.server;


import net.minecraft.server.OMapData;


public class OMapCoord {

    public byte a;
    public byte b;
    public byte c;
    public byte d;
    // $FF: synthetic field
    final OMapData e;

    public OMapCoord(OMapData var1, byte var2, byte var3, byte var4, byte var5) {
        super();
        this.e = var1;
        this.a = var2;
        this.b = var3;
        this.c = var4;
        this.d = var5;
    }
}
