package net.minecraft.server;

import java.net.DatagramPacket;
import java.util.Date;
import java.util.Random;
import net.minecraft.server.ORConThreadQuery;

class ORConThreadQueryAuth {

    private long b;
    private int c;
    private byte[] d;
    private byte[] e;
    private String f;
    // $FF: synthetic field
    final ORConThreadQuery a;

    public ORConThreadQueryAuth(ORConThreadQuery var1, DatagramPacket var2) {
        super();
        this.a = var1;
        this.b = (new Date()).getTime();
        byte[] var3 = var2.getData();
        this.d = new byte[4];
        this.d[0] = var3[3];
        this.d[1] = var3[4];
        this.d[2] = var3[5];
        this.d[3] = var3[6];
        this.f = new String(this.d);
        this.c = (new Random()).nextInt(16777216);
        this.e = String.format("\t%s%d\u0000", new Object[] { this.f, Integer.valueOf(this.c) }).getBytes();
    }

    public Boolean a(long var1) {
        return Boolean.valueOf(this.b < var1);
    }

    public int a() {
        return this.c;
    }

    public byte[] b() {
        return this.e;
    }

    public byte[] c() {
        return this.d;
    }
}
