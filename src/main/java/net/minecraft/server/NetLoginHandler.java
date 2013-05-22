package net.minecraft.server;

import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.Socket;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.crypto.SecretKey;

import net.canarymod.Canary;
import net.canarymod.hook.system.ServerListPingHook;

public class NetLoginHandler extends NetHandler {

    private static Random c = new Random();
    private byte[] d;
    private final MinecraftServer e;
    public final TcpConnection a;
    public boolean b = false;
    private int f = 0;
    private String g = null;
    private volatile boolean h = false;
    private String i = "";
    private boolean j = false;
    private SecretKey k = null;

    public NetLoginHandler(MinecraftServer minecraftserver, Socket socket, String s0) throws IOException {
        this.e = minecraftserver;
        this.a = new TcpConnection(minecraftserver.al(), socket, s0, this, minecraftserver.F().getPrivate());
        this.a.e = 0;
    }

    public void c() {
        if (this.h) {
            this.d();
        }

        if (this.f++ == 600) {
            this.a("Took too long to log in");
        } else {
            this.a.b();
        }
    }

    public void a(String s0) {
        try {
            this.e.al().a("Disconnecting " + this.e() + ": " + s0);
            this.a.a((Packet) (new Packet255KickDisconnect(s0)));
            this.a.d();
            this.b = true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void a(Packet2ClientProtocol packet2clientprotocol) {
        this.g = packet2clientprotocol.f();
        if (!this.g.equals(StringUtils.a(this.g))) {
            this.a("Invalid username!");
        } else {
            PublicKey publickey = this.e.F().getPublic();

            if (packet2clientprotocol.d() != 61) {
                if (packet2clientprotocol.d() > 61) {
                    this.a("Outdated server!");
                } else {
                    this.a("Outdated client!");
                }
            } else {
                this.i = this.e.U() ? Long.toString(c.nextLong(), 16) : "-";
                this.d = new byte[4];
                c.nextBytes(this.d);
                this.a.a((Packet) (new Packet253ServerAuthData(this.i, publickey, this.d)));
            }
        }
    }

    public void a(Packet252SharedKey packet252sharedkey) {
        PrivateKey privatekey = this.e.F().getPrivate();

        this.k = packet252sharedkey.a(privatekey);
        if (!Arrays.equals(this.d, packet252sharedkey.b(privatekey))) {
            this.a("Invalid client reply");
        }

        this.a.a((Packet) (new Packet252SharedKey()));
    }

    public void a(Packet205ClientCommand packet205clientcommand) {
        if (packet205clientcommand.a == 0) {
            if (this.j) {
                this.a("Duplicate login");
                return;
            }

            this.j = true;
            if (this.e.U()) {
                (new ThreadLoginVerifier(this)).start();
            } else {
                this.h = true;
            }
        }
    }

    public void a(Packet1Login packet1login) {}

    public void d() {
        String s0 = this.e.ad().a(this.a.c(), this.g);

        if (s0 != null) {
            this.a(s0);
        } else {
            EntityPlayerMP entityplayermp = this.e.ad().a(this.g);

            if (entityplayermp != null) {
                this.e.ad().a((INetworkManager) this.a, entityplayermp);
            }
        }

        this.b = true;
    }

    public void a(String s0, Object[] aobject) {
        this.e.al().a(this.e() + " lost connection");
        this.b = true;
    }

    public void a(Packet254ServerPing packet254serverping) {
        try {
            ServerConfigurationManager serverconfigurationmanager = this.e.ad();
            String s0 = null;

            if (packet254serverping.a == 1) {

                //CanaryMod: ServerListPingHook
                ServerListPingHook hook = new ServerListPingHook(this.e.aa(), Integer.valueOf(serverconfigurationmanager.k()), Integer.valueOf(serverconfigurationmanager.l()));
                Canary.hooks().callHook(hook);
                if (hook.isCanceled()) {
                    return;
                }

                List list = Arrays.asList(new Serializable[] { Integer.valueOf(1), Integer.valueOf(61), this.e.x(), hook.getMotd(), hook.getCurrentPlayers(), hook.getMaxPlayers()});
                //
                Object object;

                for (Iterator iterator = list.iterator(); iterator.hasNext(); s0 = s0 + object.toString().replaceAll("\u0000", "")) {
                    object = iterator.next();
                    if (s0 == null) {
                        s0 = "\u00a7";
                    } else {
                        s0 = s0 + "\u0000";
                    }
                }
            } else {
                s0 = this.e.aa() + "\u00a7" + serverconfigurationmanager.k() + "\u00a7" + serverconfigurationmanager.l();
            }

            InetAddress inetaddress = null;

            if (this.a.g() != null) {
                inetaddress = this.a.g().getInetAddress();
            }

            this.a.a((Packet) (new Packet255KickDisconnect(s0)));
            this.a.d();
            if (inetaddress != null && this.e.ae() instanceof DedicatedServerListenThread) {
                ((DedicatedServerListenThread) this.e.ae()).a(inetaddress);
            }

            this.b = true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void a(Packet packet) {
        this.a("Protocol error");
    }

    public String e() {
        return this.g != null ? this.g + " [" + this.a.c().toString() + "]" : this.a.c().toString();
    }

    public boolean a() {
        return true;
    }

    static String a(NetLoginHandler netloginhandler) {
        return netloginhandler.i;
    }

    static MinecraftServer b(NetLoginHandler netloginhandler) {
        return netloginhandler.e;
    }

    static SecretKey c(NetLoginHandler netloginhandler) {
        return netloginhandler.k;
    }

    static String d(NetLoginHandler netloginhandler) {
        return netloginhandler.g;
    }

    static boolean a(NetLoginHandler netloginhandler, boolean flag0) {
        return netloginhandler.h = flag0;
    }
}
