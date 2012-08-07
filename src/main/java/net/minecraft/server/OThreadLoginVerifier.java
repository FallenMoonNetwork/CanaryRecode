package net.minecraft.server;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import net.minecraft.server.ONetLoginHandler;
import net.minecraft.server.OPacket1Login;


class OThreadLoginVerifier extends Thread {

    // $FF: synthetic field
    final OPacket1Login a;
    // $FF: synthetic field
    final ONetLoginHandler b;

    OThreadLoginVerifier(ONetLoginHandler var1, OPacket1Login var2) {
        super();
        this.b = var1;
        this.a = var2;
    }

    @Override
    public void run() {
        try {
            String var1 = ONetLoginHandler.a(this.b);
            URL var2 = new URL("http://session.minecraft.net/game/checkserver.jsp?user=" + URLEncoder.encode(this.a.b, "UTF-8") + "&serverId=" + URLEncoder.encode(var1, "UTF-8"));
            BufferedReader var3 = new BufferedReader(new InputStreamReader(var2.openStream()));
            String var4 = var3.readLine();

            var3.close();
            if (var4.equals("YES")) {
                ONetLoginHandler.a(this.b, this.a);
            } else {
                this.b.a("Failed to verify username!");
            }
        } catch (Exception var5) {
            this.b.a("Failed to verify username! [internal error " + var5 + "]");
            var5.printStackTrace();
        }

    }
}
