package net.minecraft.server;

import net.minecraft.server.OPlayerUsageSnooper;
import net.minecraft.server.OPostHttp;

class OPlayerUsageSnooperThread extends Thread {

    // $FF: synthetic field
    final OPlayerUsageSnooper a;

    OPlayerUsageSnooperThread(OPlayerUsageSnooper var1, String var2) {
        super(var2);
        this.a = var1;
    }

    public void run() {
        OPostHttp.a(OPlayerUsageSnooper.a(this.a), OPlayerUsageSnooper.b(this.a), true);
    }
}
