package net.minecraft.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import net.canarymod.Canary;

public class ServerListenThread extends Thread {

    private final List a = Collections.synchronizedList(new ArrayList());
    private final HashMap b = new HashMap();
    private int c = 0;
    private final ServerSocket d;
    private NetworkListenThread e;
    private final InetAddress f;
    private final int g;

    public ServerListenThread(NetworkListenThread networklistenthread, InetAddress inetaddress, int i0) throws IOException {
        super("Listen thread");
        this.e = networklistenthread;
        this.g = i0;
        this.d = new ServerSocket(i0, 0, inetaddress);
        this.f = inetaddress == null ? this.d.getInetAddress() : inetaddress;
        this.d.setPerformancePreferences(0, 2, 1);
    }

    public void a() {
        List list = this.a;

        synchronized (this.a) {
            for (int i0 = 0; i0 < this.a.size(); ++i0) {
                NetLoginHandler netloginhandler = (NetLoginHandler) this.a.get(i0);

                try {
                    netloginhandler.d();
                } catch (Exception exception) {
                    netloginhandler.a("Internal server error");
                    this.e.d().an().b("Failed to handle packet for " + netloginhandler.f() + ": " + exception, (Throwable) exception);
                }

                if (netloginhandler.b) {
                    this.a.remove(i0--);
                }

                netloginhandler.a.a();
            }
        }
    }

    public void run() {
        while (this.e.a) {
            try {
                Socket socket = this.d.accept();
                NetLoginHandler netloginhandler = new NetLoginHandler(this.e.d(), socket, "Connection #" + this.c++);

                this.a(netloginhandler);
            } catch (IOException ioexception) {
                // ioexception.printStackTrace(); // CanaryMod: Don't stacktrace
                Canary.logWarning("Server Listening Thread inturrupted");
            }
        }

        this.e.d().an().a("Closing listening thread");
    }

    private void a(NetLoginHandler netloginhandler) {
        if (netloginhandler == null) {
            throw new IllegalArgumentException("Got null pendingconnection!");
        } else {
            List list = this.a;

            synchronized (this.a) {
                this.a.add(netloginhandler);
            }
        }
    }

    public void a(InetAddress inetaddress) {
        if (inetaddress != null) {
            HashMap hashmap = this.b;

            synchronized (this.b) {
                this.b.remove(inetaddress);
            }
        }
    }

    public void b() {
        try {
            this.d.close();
        } catch (Throwable throwable) {
            ;
        }
    }
}
