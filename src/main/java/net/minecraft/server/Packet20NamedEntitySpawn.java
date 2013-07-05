package net.minecraft.server;

import java.io.DataInput;
import java.io.DataOutput;
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

    public void a(DataInput datainput) throws IOException {
        this.a = datainput.readInt();
        this.b = a(datainput, 16);
        this.c = datainput.readInt();
        this.d = datainput.readInt();
        this.e = datainput.readInt();
        this.f = datainput.readByte();
        this.g = datainput.readByte();
        this.h = datainput.readShort();
        this.j = DataWatcher.a(datainput);
    }

    public void a(DataOutput dataoutput) throws IOException {
        dataoutput.writeInt(this.a);
        a(this.b, dataoutput);
        dataoutput.writeInt(this.c);
        dataoutput.writeInt(this.d);
        dataoutput.writeInt(this.e);
        dataoutput.writeByte(this.f);
        dataoutput.writeByte(this.g);
        dataoutput.writeShort(this.h);
        this.i.a(dataoutput);
    }

    public void a(NetHandler nethandler) {
        nethandler.a(this);
    }

    public int a() {
        return 28;
    }
}
