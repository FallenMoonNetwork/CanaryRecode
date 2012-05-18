package net.minecraft.server;

import net.minecraft.server.ONetworkManager;

class ONetworkMasterThread extends Thread {

    // $FF: synthetic field
    final ONetworkManager a;

    ONetworkMasterThread(ONetworkManager var1) {
        super();
        this.a = var1;
    }

    public void run() {
        try {
            Thread.sleep(5000L);
            if (ONetworkManager.g(this.a).isAlive()) {
                try {
                    ONetworkManager.g(this.a).stop();
                } catch (Throwable var3) {
                    ;
                }
            }

            if (ONetworkManager.h(this.a).isAlive()) {
                try {
                    ONetworkManager.h(this.a).stop();
                } catch (Throwable var2) {
                    ;
                }
            }
        } catch (InterruptedException var4) {
            var4.printStackTrace();
        }

    }
}
