package net.minecraft.server;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;

import net.canarymod.config.Configuration;
import net.minecraft.server.OIServer;
import net.minecraft.server.ORConThreadBase;
import net.minecraft.server.ORConUtils;

public class ORConThreadClient extends ORConThreadBase {

    private boolean g = false;
    private Socket h;
    private byte[] i = new byte[1460];
    private String j;

    ORConThreadClient(OIServer var1, Socket var2) {
        super(var1);
        this.h = var2;
        this.j = Configuration.getNetConfig().getRconPassword();
        this.b("Rcon connection from: " + var2.getInetAddress());
    }

    public void run() {
        while (true) {
            boolean var16 = false;

            label141: {
                label151: {
                    label152: {
                        try {
                            var16 = true;
                            if (this.a) {
                                try {
                                    BufferedInputStream var1 = new BufferedInputStream(this.h.getInputStream());
                                    int var2 = var1.read(this.i, 0, 1460);
                                    if (10 > var2) {
                                        var16 = false;
                                        break label141;
                                    }

                                    byte var3 = 0;
                                    int var4 = ORConUtils.b(this.i, 0, var2);
                                    if (var4 != var2 - 4) {
                                        var16 = false;
                                        break label151;
                                    }

                                    int var22 = var3 + 4;
                                    int var5 = ORConUtils.b(this.i, var22, var2);
                                    var22 += 4;
                                    int var6 = ORConUtils.a(this.i, var22);
                                    var22 += 4;
                                    switch (var6) {
                                    case 2:
                                        if (this.g) {
                                            String var8 = ORConUtils.a(this.i, var22, var2);

                                            try {
                                                this.a(var5, this.b.d(var8));
                                            } catch (Exception var17) {
                                                this.a(var5, "Error executing: " + var8 + " (" + var17.getMessage() + ")");
                                            }
                                            continue;
                                        }

                                        this.e();
                                        continue;
                                    case 3:
                                        String var7 = ORConUtils.a(this.i, var22, var2);
                                        int var10000 = var22 + var7.length();
                                        if (0 != var7.length() && var7.equals(this.j)) {
                                            this.g = true;
                                            this.a(var5, 2, "");
                                            continue;
                                        }

                                        this.g = false;
                                        this.e();
                                        continue;
                                    default:
                                        this.a(var5, String.format("Unknown request %s", new Object[] { Integer.toHexString(var6) }));
                                    }
                                } catch (SocketTimeoutException var18) {
                                    ;
                                } catch (IOException var19) {
                                    if (this.a) {
                                        this.b("IO: " + var19.getMessage());
                                    }
                                }
                                continue;
                            }

                            var16 = false;
                            break label152;
                        } catch (Exception var20) {
                            System.out.println(var20);
                            var16 = false;
                        } finally {
                            if (var16) {
                                this.f();
                            }
                        }

                        this.f();
                        return;
                    }

                    this.f();
                    return;
                }

                this.f();
                return;
            }

            this.f();
            return;
        }
    }

    private void a(int var1, int var2, String var3) throws IOException {
        ByteArrayOutputStream var4 = new ByteArrayOutputStream(1248);
        DataOutputStream var5 = new DataOutputStream(var4);
        var5.writeInt(Integer.reverseBytes(var3.length() + 10));
        var5.writeInt(Integer.reverseBytes(var1));
        var5.writeInt(Integer.reverseBytes(var2));
        var5.writeBytes(var3);
        var5.write(0);
        var5.write(0);
        this.h.getOutputStream().write(var4.toByteArray());
    }

    private void e() throws IOException {
        this.a(-1, 2, "");
    }

    private void a(int var1, String var2) throws IOException {
        int var3 = var2.length();

        do {
            int var4 = 4096 <= var3 ? 4096 : var3;
            this.a(var1, 0, var2.substring(0, var4));
            var2 = var2.substring(var4);
            var3 = var2.length();
        } while (0 != var3);

    }

    private void f() {
        if (null != this.h) {
            try {
                this.h.close();
            } catch (IOException var2) {
                this.c("IO: " + var2.getMessage());
            }

            this.h = null;
        }
    }
}
