package net.minecraft.server;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.minecraft.server.ONetHandler;
import net.minecraft.server.ONetworkMasterThread;
import net.minecraft.server.ONetworkReaderThread;
import net.minecraft.server.ONetworkWriterThread;
import net.minecraft.server.OPacket;
import net.minecraft.server.OThreadMonitorConnection;

public class ONetworkManager {

    public static final Object a = new Object();
    public static int b;
    public static int c;
    private Object g = new Object();
    private Socket h;
    private final SocketAddress i;
    private DataInputStream j;
    private DataOutputStream k;
    private boolean l = true;
    private List m = Collections.synchronizedList(new ArrayList());
    private List n = Collections.synchronizedList(new ArrayList());
    private List o = Collections.synchronizedList(new ArrayList());
    private ONetHandler p;
    private boolean q = false;
    private Thread r;
    private Thread s;
    private boolean t = false;
    private String u = "";
    private Object[] v;
    private int w = 0;
    private int x = 0;
    public static int[] d = new int[256];
    public static int[] e = new int[256];
    public int f = 0;
    private int y = 50;

    public ONetworkManager(Socket var1, String var2, ONetHandler var3) throws IOException {
        super();
        this.h = var1;
        this.i = var1.getRemoteSocketAddress();
        this.p = var3;

        try {
            var1.setSoTimeout(30000);
            var1.setTrafficClass(24);
        } catch (SocketException var5) {
            System.err.println(var5.getMessage());
        }

        this.j = new DataInputStream(var1.getInputStream());
        this.k = new DataOutputStream(new BufferedOutputStream(var1.getOutputStream(), 5120));
        this.s = new ONetworkReaderThread(this, var2 + " read thread");
        this.r = new ONetworkWriterThread(this, var2 + " write thread");
        this.s.start();
        this.r.start();
    }

    public void a(ONetHandler var1) {
        this.p = var1;
    }

    public void a(OPacket var1) {
        if (!this.q) {
            Object var2 = this.g;
            synchronized (this.g) {
                this.x += var1.a() + 1;
                if (var1.p) {
                    this.o.add(var1);
                } else {
                    this.n.add(var1);
                }

            }
        }
    }

    private boolean g() {
        boolean var1 = false;

        try {
            Object var2;
            OPacket var3;
            int var10001;
            int[] var10000;
            if (!this.n.isEmpty() && (this.f == 0 || System.currentTimeMillis() - ((OPacket) this.n.get(0)).k >= this.f)) {
                var2 = this.g;
                synchronized (this.g) {
                    var3 = (OPacket) this.n.remove(0);
                    this.x -= var3.a() + 1;
                }

                OPacket.a(var3, this.k);
                var10000 = e;
                var10001 = var3.b();
                var10000[var10001] += var3.a() + 1;
                var1 = true;
            }

            if (this.y-- <= 0 && !this.o.isEmpty() && (this.f == 0 || System.currentTimeMillis() - ((OPacket) this.o.get(0)).k >= this.f)) {
                var2 = this.g;
                synchronized (this.g) {
                    var3 = (OPacket) this.o.remove(0);
                    this.x -= var3.a() + 1;
                }

                OPacket.a(var3, this.k);
                var10000 = e;
                var10001 = var3.b();
                var10000[var10001] += var3.a() + 1;
                this.y = 0;
                var1 = true;
            }

            return var1;
        } catch (Exception var8) {
            if (!this.t) {
                this.a(var8);
            }

            return false;
        }
    }

    public void a() {
        this.s.interrupt();
        this.r.interrupt();
    }

    private boolean h() {
        boolean var1 = false;

        try {
            OPacket var2 = OPacket.a(this.j, this.p.c());
            if (var2 != null) {
                int[] var10000 = d;
                int var10001 = var2.b();
                var10000[var10001] += var2.a() + 1;
                if (!this.q) {
                    this.m.add(var2);
                }

                var1 = true;
            } else {
                this.a("disconnect.endOfStream", new Object[0]);
            }

            return var1;
        } catch (Exception var3) {
            if (!this.t) {
                this.a(var3);
            }

            return false;
        }
    }

    private void a(Exception var1) {
        var1.printStackTrace();
        this.a("disconnect.genericReason", new Object[] { "Internal exception: " + var1.toString() });
    }

    public void a(String var1, Object... var2) {
        if (this.l) {
            this.t = true;
            this.u = var1;
            this.v = var2;
            (new ONetworkMasterThread(this)).start();
            this.l = false;

            try {
                this.j.close();
                this.j = null;
            } catch (Throwable var6) {
                ;
            }

            try {
                this.k.close();
                this.k = null;
            } catch (Throwable var5) {
                ;
            }

            try {
                this.h.close();
                this.h = null;
            } catch (Throwable var4) {
                ;
            }

        }
    }

    public void b() {
        if (this.x > 1048576) {
            this.a("disconnect.overflow", new Object[0]);
        }

        if (this.m.isEmpty()) {
            if (this.w++ == 1200) {
                this.a("disconnect.timeout", new Object[0]);
            }
        } else {
            this.w = 0;
        }

        int var1 = 1000;

        while (!this.m.isEmpty() && var1-- >= 0) {
            OPacket var2 = (OPacket) this.m.remove(0);
            var2.a(this.p);
        }

        this.a();
        if (this.t && this.m.isEmpty()) {
            this.p.a(this.u, this.v);
        }

    }

    public SocketAddress c() {
        return this.i;
    }

    public void d() {
        if (!this.q) {
            this.a();
            this.q = true;
            this.s.interrupt();
            (new OThreadMonitorConnection(this)).start();
        }
    }

    public int e() {
        return this.o.size();
    }

    public Socket f() {
        return this.h;
    }

    // $FF: synthetic method
    static boolean a(ONetworkManager var0) {
        return var0.l;
    }

    // $FF: synthetic method
    static boolean b(ONetworkManager var0) {
        return var0.q;
    }

    // $FF: synthetic method
    static boolean c(ONetworkManager var0) {
        return var0.h();
    }

    // $FF: synthetic method
    static boolean d(ONetworkManager var0) {
        return var0.g();
    }

    // $FF: synthetic method
    static DataOutputStream e(ONetworkManager var0) {
        return var0.k;
    }

    // $FF: synthetic method
    static boolean f(ONetworkManager var0) {
        return var0.t;
    }

    // $FF: synthetic method
    static void a(ONetworkManager var0, Exception var1) {
        var0.a(var1);
    }

    // $FF: synthetic method
    static Thread g(ONetworkManager var0) {
        return var0.s;
    }

    // $FF: synthetic method
    static Thread h(ONetworkManager var0) {
        return var0.r;
    }

}
