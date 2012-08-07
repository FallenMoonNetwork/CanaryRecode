package net.minecraft.server;


import java.io.ByteArrayOutputStream;
import net.minecraft.server.ORegionFile;


class ORegionFileChunkBuffer extends ByteArrayOutputStream {

    private int b;
    private int c;
    // $FF: synthetic field
    final ORegionFile a;

    public ORegionFileChunkBuffer(ORegionFile var1, int var2, int var3) {
        super(8096);
        this.a = var1;
        this.b = var2;
        this.c = var3;
    }

    @Override
    public void close() {
        this.a.a(this.b, this.c, this.buf, this.count);
    }
}
