package net.minecraft.server;

import java.io.IOException;
import net.minecraft.server.ONetworkManager;

class ONetworkWriterThread extends Thread {

    // $FF: synthetic field
    final ONetworkManager a;

    ONetworkWriterThread(ONetworkManager var1, String var2) {
        super(var2);
        this.a = var1;
    }

    @Override
    public void run() {
        Object var1 = ONetworkManager.a;
        synchronized (ONetworkManager.a) {
            ++ONetworkManager.c;
        }

        while (true) {
            boolean var13 = false;

            try {
                var13 = true;
                if (!ONetworkManager.a(this.a)) {
                    var13 = false;
                    break;
                }

                while (ONetworkManager.d(this.a)) {
                    ;
                }

                try {
                    if (ONetworkManager.e(this.a) != null) {
                        ONetworkManager.e(this.a).flush();
                    }
                } catch (IOException var18) {
                    if (!ONetworkManager.f(this.a)) {
                        ONetworkManager.a(this.a, var18);
                    }

                    var18.printStackTrace();
                }

                try {
                    sleep(2L);
                } catch (InterruptedException var16) {
                    ;
                }
            } finally {
                if (var13) {
                    Object var5 = ONetworkManager.a;
                    synchronized (ONetworkManager.a) {
                        --ONetworkManager.c;
                    }
                }
            }
        }

        var1 = ONetworkManager.a;
        synchronized (ONetworkManager.a) {
            --ONetworkManager.c;
        }
    }
}
