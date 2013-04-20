package net.minecraft.server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Iterator;

import net.canarymod.Canary;
import net.canarymod.config.Configuration;

public class DedicatedPlayerList extends ServerConfigurationManager {

//  CanaryMod removed whitelist
    private File d;
    public DedicatedPlayerList(DedicatedServer dedicatedserver) {
        super(dedicatedserver);
      //CanaryMod removed whitelist settings
        this.d = dedicatedserver.e("ops.txt");
        this.c = Configuration.getServerConfig().getViewDistance();
        this.b = Configuration.getServerConfig().getMaxPlayers();
        if (!dedicatedserver.I()) {
            this.e().a(true);
            this.f().a(true);
        }

        this.e().e();
        this.e().f();
        this.f().e();
        this.f().f();
        this.t();
        this.v();
        this.u();
    }

    public void b(String s0) {
        super.b(s0);
        this.u();
    }

    public void c(String s0) {
        super.c(s0);
        this.u();
    }

    public void i(String s0) {
        //CanaryMod re-route to our whitelist
        Canary.whitelist().removePlayer(s0);
    }

    public void h(String s0) {
        //Canary, re-route to our whitelist
        Canary.whitelist().addPlayer(s0);
    }

    public void j() {
        //Load whitelist
        throw new UnsupportedOperationException("Minecraft whitelist is disabled! Cannot load");
    }

    private void t() {
        try {
            this.i().clear();
            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.d));
            String s0 = "";

            while ((s0 = bufferedreader.readLine()) != null) {
                this.i().add(s0.trim().toLowerCase());
            }

            bufferedreader.close();
        } catch (Exception exception) {
            this.s().al().b("Failed to load operators list: " + exception);
        }
    }

    private void u() {
        try {
            PrintWriter printwriter = new PrintWriter(new FileWriter(this.d, false));
            Iterator iterator = this.i().iterator();

            while (iterator.hasNext()) {
                String s0 = (String) iterator.next();

                printwriter.println(s0);
            }

            printwriter.close();
        } catch (Exception exception) {
            this.s().al().b("Failed to save operators list: " + exception);
        }
    }

    private void v() {
//        try {
//            this.h().clear();
//            BufferedReader bufferedreader = new BufferedReader(new FileReader(this.e));
//            String s0 = "";
//
//            while ((s0 = bufferedreader.readLine()) != null) {
//                this.h().add(s0.trim().toLowerCase());
//            }
//
//            bufferedreader.close();
//        } catch (Exception exception) {
//            this.s().al().b("Failed to load white-list: " + exception);
//        }
    }

    private void w() {
//        try {
//            PrintWriter printwriter = new PrintWriter(new FileWriter(this.e, false));
//            Iterator iterator = this.h().iterator();
//
//            while (iterator.hasNext()) {
//                String s0 = (String) iterator.next();
//
//                printwriter.println(s0);
//            }
//
//            printwriter.close();
//        } catch (Exception exception) {
//            this.s().al().b("Failed to save white-list: " + exception);
//        }
    }

    public boolean d(String s0) {
        s0 = s0.trim().toLowerCase();
        return !this.n() || this.e(s0) || this.h().contains(s0);
    }

    public DedicatedServer s() {
        return (DedicatedServer) super.p();
    }

    public MinecraftServer p() {
        return this.s();
    }
}
