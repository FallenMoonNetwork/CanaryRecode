package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket16BlockItemSwitch extends OPacket {

    public int a;

    public OPacket16BlockItemSwitch() {
        super();
    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readShort();
    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeShort(this.a);
    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 2;
    }
}
