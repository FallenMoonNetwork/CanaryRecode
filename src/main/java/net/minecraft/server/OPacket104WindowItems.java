package net.minecraft.server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;
import net.minecraft.server.OItemStack;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.OPacket;


public class OPacket104WindowItems extends OPacket {

    public int a;
    public OItemStack[] b;

    public OPacket104WindowItems() {
        super();
    }

    public OPacket104WindowItems(int var1, List var2) {
        super();
        this.a = var1;
        this.b = new OItemStack[var2.size()];

        for (int var3 = 0; var3 < this.b.length; ++var3) {
            OItemStack var4 = (OItemStack) var2.get(var3);

            this.b[var3] = var4 == null ? null : var4.j();
        }

    }

    @Override
    public void a(DataInputStream var1) throws IOException {
        this.a = var1.readByte();
        short var2 = var1.readShort();

        this.b = new OItemStack[var2];

        for (int var3 = 0; var3 < var2; ++var3) {
            this.b[var3] = this.b(var1);
        }

    }

    @Override
    public void a(DataOutputStream var1) throws IOException {
        var1.writeByte(this.a);
        var1.writeShort(this.b.length);

        for (int var2 = 0; var2 < this.b.length; ++var2) {
            this.a(this.b[var2], var1);
        }

    }

    @Override
    public void a(ONetHandler var1) {
        var1.a(this);
    }

    @Override
    public int a() {
        return 3 + this.b.length * 5;
    }
}
