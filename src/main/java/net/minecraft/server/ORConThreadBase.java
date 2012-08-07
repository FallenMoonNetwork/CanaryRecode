package net.minecraft.server;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.server.OIServer;

public abstract class ORConThreadBase implements Runnable {

    protected boolean a = false;
    protected OIServer b;
    protected Thread c;
    protected int d = 5;
    protected List<DatagramSocket> e = new ArrayList<DatagramSocket>();
    protected List<ServerSocket> f = new ArrayList<ServerSocket>();

    ORConThreadBase(OIServer var1) {
        super();
        this.b = var1;
        if (this.b.p()) {
            this.c("Debugging is enabled, performance maybe reduced!");
        }

    }

    public synchronized void a() {
        this.c = new Thread(this);
        this.c.start();
        this.a = true;
    }

    public boolean b() {
        return this.a;
    }

    protected void a(String var1) {
        this.b.f(var1);
    }

    protected void b(String var1) {
        this.b.b(var1);
    }

    protected void c(String var1) {
        this.b.c(var1);
    }

    protected void d(String var1) {
        this.b.e(var1);
    }

    protected int c() {
        return this.b.j();
    }

    protected void a(DatagramSocket var1) {
        this.a("registerSocket: " + var1);
        this.e.add(var1);
    }

    protected boolean a(DatagramSocket var1, boolean var2) {
        this.a("closeSocket: " + var1);
        if (null == var1) {
            return false;
        } else {
            boolean var3 = false;
            if (!var1.isClosed()) {
                var1.close();
                var3 = true;
            }

            if (var2) {
                this.e.remove(var1);
            }

            return var3;
        }
    }

    protected boolean a(ServerSocket var1) {
        return this.a(var1, true);
    }

    protected boolean a(ServerSocket var1, boolean var2) {
        this.a("closeSocket: " + var1);
        if (null == var1) {
            return false;
        } else {
            boolean var3 = false;

            try {
                if (!var1.isClosed()) {
                    var1.close();
                    var3 = true;
                }
            } catch (IOException var5) {
                this.c("IO: " + var5.getMessage());
            }

            if (var2) {
                this.f.remove(var1);
            }

            return var3;
        }
    }

    protected void d() {
        this.a(false);
    }

    protected void a(boolean var1) {
        int var2 = 0;
        Iterator var3 = this.e.iterator();

        while (var3.hasNext()) {
            DatagramSocket var4 = (DatagramSocket) var3.next();
            if (this.a(var4, false)) {
                ++var2;
            }
        }

        this.e.clear();
        var3 = this.f.iterator();

        while (var3.hasNext()) {
            ServerSocket var5 = (ServerSocket) var3.next();
            if (this.a(var5, false)) {
                ++var2;
            }
        }

        this.f.clear();
        if (var1 && 0 < var2) {
            this.c("Force closed " + var2 + " sockets");
        }

    }
}
