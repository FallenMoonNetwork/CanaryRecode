package net.minecraft.server;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogAgent implements ILogAgent {

    private final Logger a;
    private final String b;
    private final String c;
    private final String d;

    public LogAgent(String s0, String s1, String s2) {
        this.a = Logger.getLogger(s0);
        this.c = s0;
        this.d = s1;
        this.b = s2;
        this.b();
    }

    private void b() {
        this.a.setUseParentHandlers(false);
        Handler[] ahandler = this.a.getHandlers();
        int i0 = ahandler.length;

        for (int i1 = 0; i1 < i0; ++i1) {
            Handler handler = ahandler[i1];

            this.a.removeHandler(handler);
        }

        LogFormatter logformatter = new LogFormatter(this, (LogAgentINNER1) null);
        ConsoleHandler consolehandler = new ConsoleHandler();

        consolehandler.setFormatter(logformatter);
        consolehandler.setLevel(Level.ALL); // CanaryMod: All levels output to Console!
        this.a.addHandler(consolehandler);

        try {
            FileHandler filehandler = new FileHandler(this.b, true);

            filehandler.setFormatter(logformatter);
            this.a.addHandler(filehandler);
        } catch (Exception exception) {
            this.a.log(Level.WARNING, "Failed to log " + this.c + " to " + this.b, exception);
        }
    }

    public Logger a() {
        return this.a;
    }

    public void a(String s0) {
        this.a.log(Level.INFO, s0);
    }

    public void b(String s0) {
        this.a.log(Level.WARNING, s0);
    }

    public void b(String s0, Object... aobject) {
        this.a.log(Level.WARNING, s0, aobject);
    }

    public void b(String s0, Throwable throwable) {
        this.a.log(Level.WARNING, s0, throwable);
    }

    public void c(String s0) {
        this.a.log(Level.SEVERE, s0);
    }

    public void c(String s0, Throwable throwable) {
        this.a.log(Level.SEVERE, s0, throwable);
    }

    static String a(LogAgent logagent) {
        return logagent.d;
    }
}
