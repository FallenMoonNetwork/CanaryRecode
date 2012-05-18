package net.minecraft.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import net.minecraft.server.ODataWatcher;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;

public class OPacket40EntityMetadata extends OPacket {

    public int a;
    private List b;

    public OPacket40EntityMetadata() {
        super();
    }

    public OPacket40EntityMetadata(int var1, ODataWatcher var2) {
        super();
        this.a = var1;
        this.b = var2.b();
    }

    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readInt();
        this.b = ODataWatcher.a(var1);
    }

    public void a(DataOutputStream var1) throws IOException {
        var1.writeInt(this.a);
        ODataWatcher.a(this.b, var1);
    }

    public void a(ONetHandler var1) {
        var1.a(this);
    }

    public int a() {
        return 5;
    }
}
