package net.minecraft.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import net.canarymod.config.Configuration;
import net.minecraft.server.OIServer;
import net.minecraft.server.ORConOutputStream;
import net.minecraft.server.ORConThreadBase;
import net.minecraft.server.ORConThreadQueryAuth;
import net.minecraft.server.ORConUtils;

public class ORConThreadQuery extends ORConThreadBase {

    private long g;
    private int h;
    private int i;
    private int j;
    private String k;
    private String l;
    private DatagramSocket m = null;
    private byte[] n = new byte[1460];
    private DatagramPacket o = null;
    private HashMap p;
    private String q;
    private String r;
    private HashMap s;
    private long t;
    private ORConOutputStream u;
    private long v;

    public ORConThreadQuery(OIServer var1) {
        super(var1);
        // CanaryMod: changing configuration
        this.h = Configuration.getNetConfig().getQueryPort();
        this.r = var1.f();
        this.i = var1.g();
        this.k = var1.h();
        this.j = var1.k();
        this.l = var1.getDefaultWorld();
        this.v = 0L;
        this.q = "0.0.0.0";
        if (0 != this.r.length() && !this.q.equals(this.r)) {
            this.q = this.r;
        } else {
            this.r = "0.0.0.0";

            try {
                InetAddress var2 = InetAddress.getLocalHost();
                this.q = var2.getHostAddress();
            } catch (UnknownHostException var3) {
                this.c("Unable to determine local host IP, please set server-ip in \'" + Configuration.getNetConfig().getFile().getPath() + "\' : " + var3.getMessage());
            }
        }

        if (0 == this.h) {
            this.h = this.i;
            this.b("Setting default query port to " + this.h);
            // CanaryMod: changing configuration
            try {
            	Configuration.getNetConfig().getFile().setInt("query.port", Integer.valueOf(this.h));
            	Configuration.getNetConfig().getFile().save();
            	Configuration.getServerConfig().getFile().setBoolean("debug", false);
            	Configuration.getServerConfig().getFile().save();
            } catch(IOException ioe) {
            	this.b("Failed to save configuration.");
            }
        }

        this.p = new HashMap();
        this.u = new ORConOutputStream(1460);
        this.s = new HashMap();
        this.t = (new Date()).getTime();
    }

    private void a(byte[] var1, DatagramPacket var2) throws SocketException, IOException {
        this.m.send(new DatagramPacket(var1, var1.length, var2.getSocketAddress()));
    }

    private boolean a(DatagramPacket var1) throws IOException {
        byte[] var2 = var1.getData();
        int var3 = var1.getLength();
        SocketAddress var4 = var1.getSocketAddress();
        this.a("Packet len " + var3 + " [" + var4 + "]");
        if (3 <= var3 && -2 == var2[0] && -3 == var2[1]) {
            this.a("Packet \'" + ORConUtils.a(var2[2]) + "\' [" + var4 + "]");
            switch (var2[2]) {
            case 0:
                if (!this.c(var1).booleanValue()) {
                    this.a("Invalid challenge [" + var4 + "]");
                    return false;
                } else if (15 != var3) {
                    ORConOutputStream var5 = new ORConOutputStream(1460);
                    var5.a(0);
                    var5.a(this.a(var1.getSocketAddress()));
                    var5.a(this.k);
                    var5.a("SMP");
                    var5.a(this.l);
                    var5.a(Integer.toString(this.c()));
                    var5.a(Integer.toString(this.j));
                    var5.a((short) this.i);
                    var5.a(this.q);
                    this.a(var5.a(), var1);
                    this.a("Status [" + var4 + "]");
                } else {
                    this.a(this.b(var1), var1);
                    this.a("Rules [" + var4 + "]");
                }
            case 9:
                this.d(var1);
                this.a("Challenge [" + var4 + "]");
                return true;
            default:
                return true;
            }
        } else {
            this.a("Invalid packet [" + var4 + "]");
            return false;
        }
    }

    private byte[] b(DatagramPacket var1) throws IOException {
        long var2 = System.currentTimeMillis();
        if (var2 < this.v + 5000L) {
            byte[] var7 = this.u.a();
            byte[] var8 = this.a(var1.getSocketAddress());
            var7[1] = var8[0];
            var7[2] = var8[1];
            var7[3] = var8[2];
            var7[4] = var8[3];
            return var7;
        } else {
            this.v = var2;
            this.u.b();
            this.u.a(0);
            this.u.a(this.a(var1.getSocketAddress()));
            this.u.a("splitnum");
            this.u.a(128);
            this.u.a(0);
            this.u.a("hostname");
            this.u.a(this.k);
            this.u.a("gametype");
            this.u.a("SMP");
            this.u.a("game_id");
            this.u.a("MINECRAFT");
            this.u.a("version");
            this.u.a(this.b.i());
            this.u.a("plugins");
            this.u.a(this.b.n());
            this.u.a("map");
            this.u.a(this.l);
            this.u.a("numplayers");
            this.u.a("" + this.c());
            this.u.a("maxplayers");
            this.u.a("" + this.j);
            this.u.a("hostport");
            this.u.a("" + this.i);
            this.u.a("hostip");
            this.u.a(this.q);
            this.u.a(0);
            this.u.a(1);
            this.u.a("player_");
            this.u.a(0);
            String[] var4 = this.b.l();
            byte var5 = (byte) var4.length;

            for (byte var6 = (byte) (var5 - 1); var6 >= 0; --var6) {
                this.u.a(var4[var6]);
            }

            this.u.a(0);
            return this.u.a();
        }
    }

    private byte[] a(SocketAddress var1) {
        return ((ORConThreadQueryAuth) this.s.get(var1)).c();
    }

    private Boolean c(DatagramPacket var1) {
        SocketAddress var2 = var1.getSocketAddress();
        if (!this.s.containsKey(var2)) {
            return Boolean.valueOf(false);
        } else {
            byte[] var3 = var1.getData();
            return ((ORConThreadQueryAuth) this.s.get(var2)).a() != ORConUtils.c(var3, 7, var1.getLength()) ? Boolean.valueOf(false) : Boolean.valueOf(true);
        }
    }

    private void d(DatagramPacket var1) throws SocketException, IOException {
        ORConThreadQueryAuth var2 = new ORConThreadQueryAuth(this, var1);
        this.s.put(var1.getSocketAddress(), var2);
        this.a(var2.b(), var1);
    }

    private void e() {
        if (this.a) {
            long var1 = System.currentTimeMillis();
            if (var1 >= this.g + 30000L) {
                this.g = var1;
                Iterator var3 = this.s.entrySet().iterator();

                while (var3.hasNext()) {
                    Entry var4 = (Entry) var3.next();
                    if (((ORConThreadQueryAuth) var4.getValue()).a(var1).booleanValue()) {
                        var3.remove();
                    }
                }

            }
        }
    }

    @Override
    public void run() {
        this.b("Query running on " + this.r + ":" + this.h);
        this.g = System.currentTimeMillis();
        this.o = new DatagramPacket(this.n, this.n.length);

        while (true) {
            boolean var7 = false;

            try {
                var7 = true;
                if (!this.a) {
                    var7 = false;
                    break;
                }

                try {
                    this.m.receive(this.o);
                    this.e();
                    this.a(this.o);
                } catch (SocketTimeoutException var8) {
                    this.e();
                } catch (PortUnreachableException var9) {
                    ;
                } catch (IOException var10) {
                    this.a(var10);
                }
            } finally {
                if (var7) {
                    this.d();
                }
            }
        }

        this.d();
    }

    @Override
    public void a() {
        if (!this.a) {
            if (0 < this.h && '\uffff' >= this.h) {
                if (this.f()) {
                    super.a();
                }

            } else {
                this.c("Invalid query port " + this.h + " found in \'" + Configuration.getNetConfig().getFile().getPath() + "\' (queries disabled)");
            }
        }
    }

    private void a(Exception var1) {
        if (this.a) {
            this.c("Unexpected exception, buggy JRE? (" + var1.toString() + ")");
            if (!this.f()) {
                this.d("Failed to recover from buggy JRE, shutting down!");
                this.a = false;
                this.b.o();
            }

        }
    }

    private boolean f() {
        try {
            this.m = new DatagramSocket(this.h, InetAddress.getByName(this.r));
            this.a(this.m);
            this.m.setSoTimeout(500);
            return true;
        } catch (SocketException var2) {
            this.c("Unable to initialise query system on " + this.r + ":" + this.h + " (Socket): " + var2.getMessage());
        } catch (UnknownHostException var3) {
            this.c("Unable to initialise query system on " + this.r + ":" + this.h + " (Unknown Host): " + var3.getMessage());
        } catch (Exception var4) {
            this.c("Unable to initialise query system on " + this.r + ":" + this.h + " (E): " + var4.getMessage());
        }

        return false;
    }
}
