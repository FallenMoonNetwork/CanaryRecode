package net.minecraft.server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;


public class Packet20NamedEntitySpawn extends Packet {

    public int a;
    public String b;
    public int c;
    public int d;
    public int e;
    public byte f;
    public byte g;
    public int h;
    private DataWatcher i;
    private List j;

    public Packet20NamedEntitySpawn() {}

    public Packet20NamedEntitySpawn(EntityPlayer entityplayer) {
        this.a = entityplayer.k;
        this.b = entityplayer.c_();
        this.c = MathHelper.c(entityplayer.u * 32.0D);
        this.d = MathHelper.c(entityplayer.v * 32.0D);
        this.e = MathHelper.c(entityplayer.w * 32.0D);
        this.f = (byte) ((int) (entityplayer.A * 256.0F / 360.0F));
        this.g = (byte) ((int) (entityplayer.B * 256.0F / 360.0F));
        ItemStack itemstack = entityplayer.bn.h();

        this.h = itemstack == null ? 0 : itemstack.d;
        this.i = entityplayer.u();
    }

    public void a(DataInputStream datainputstream) throws IOException {
        this.a = datainputstream.readInt();
        this.b = a(datainputstream, 16);
        this.c = datainputstream.readInt();
        this.d = datainputstream.readInt();
        this.e = datainputstream.readInt();
        this.f = datainputstream.readByte();
        this.g = datainputstream.readByte();
        this.h = datainputstream.readShort();
        this.j = DataWatcher.a(datainputstream);
    }

    public void a(DataOutputStream dataoutputstream) throws IOException {
        dataoutputstream.writeInt(this.a);
        a(this.b, dataoutputstream);
        dataoutputstream.writeInt(this.c);
        dataoutputstream.writeInt(this.d);
        dataoutputstream.writeInt(this.e);
        dataoutputstream.writeByte(this.f);
        dataoutputstream.writeByte(this.g);
        dataoutputstream.writeShort(this.h);
        this.i.a(dataoutputstream);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 28;
    }
}
