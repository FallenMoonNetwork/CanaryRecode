package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;
import net.minecraft.server.OPotionEffect;

public class OPacket42RemoveEntityEffect extends OPacket {

    public int a;
    public byte b;

    public OPacket42RemoveEntityEffect() {
        super();
    }

    public OPacket42RemoveEntityEffect(int var1, OPotionEffect var2) {
        super();
        this.a = var1;
        this.b = (byte) (var2.a() & 255);
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = var1.readByte();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        var1.writeByte(this.b);
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 5;
    }
}
