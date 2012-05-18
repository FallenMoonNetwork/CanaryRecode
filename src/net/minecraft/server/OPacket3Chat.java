package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket3Chat extends OPacket {

    public static int b = 119;
    public String a;

    public OPacket3Chat() {
        super();
    }

    public OPacket3Chat(String var1) {
        super();
        if (var1.length() > b) {
            var1 = var1.substring(0, b);
        }

        this.a = var1;
    }

    public void a(DataInputStream var1) throws IOException {
        this.a = a(var1, b);
    }

    public void a(DataOutputStream var1) throws IOException {
        a(this.a, var1);
    }

    public void a(ONetHandler var1) {
        var1.a(this);
    }

    public int a() {
        return 2 + this.a.length() * 2;
    }

}
