package net.minecraft.server;

import net.minecraft.server.ONetworkManager;

class OThreadMonitorConnection extends Thread {

    // $FF: synthetic field
    final ONetworkManager a;

    OThreadMonitorConnection(ONetworkManager var1) {
        super();
        this.a = var1;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000L);
            if (ONetworkManager.a(this.a)) {
                ONetworkManager.h(this.a).interrupt();
                this.a.a("disconnect.closed", new Object[0]);
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}
