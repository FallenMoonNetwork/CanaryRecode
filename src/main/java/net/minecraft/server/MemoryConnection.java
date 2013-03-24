package net.minecraft.server;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoryConnection implements INetworkManager{

    private static final SocketAddress a = new InetSocketAddress("127.0.0.1", 0);
    private final List b = Collections.synchronizedList(new ArrayList()); // CanaryMod: fix
    private final ILogAgent c;
    private MemoryConnection d;
    private NetHandler e;
    private boolean f;
    private String g;
    private Object[] h;

    // CanaryMod: Add missing constructor
    public MemoryConnection(ILogAgent ilogagent, NetHandler nethandler){
        this.e = nethandler;
        this.c = ilogagent;
    }

    //

    public void a(NetHandler nethandler){
        this.e = nethandler;
    }

    public void a(Packet packet){
        if (!this.f) {
            this.d.b(packet);
        }
    }

    public void a(){}

    public void b(){
        int i0 = 2500;

        while (i0-- >= 0 && !this.b.isEmpty()) {
            Packet packet = (Packet) this.b.remove(0);

            packet.a(this.e);
        }

        if (this.b.size() > i0) {
            this.c.b("Memory connection overburdened; after processing 2500 packets, we still have " + this.b.size() + " to go!");
        }

        if (this.f && this.b.isEmpty()) {
            this.e.a(this.g, this.h);
        }
    }

    public SocketAddress c(){
        return a;
    }

    public void d(){
        this.f = true;
    }

    public void a(String s0, Object... aobject){
        this.f = true;
        this.g = s0;
        this.h = aobject;
    }

    public int e(){
        return 0;
    }

    public void b(Packet packet){
        if (packet.a_() && this.e.b()) {
            packet.a(this.e);
        }
        else {
            this.b.add(packet);
        }
    }
}
