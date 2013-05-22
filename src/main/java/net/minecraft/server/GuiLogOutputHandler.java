package net.minecraft.server;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.swing.JTextArea;
import javax.swing.UIManager;

import net.canarymod.Canary;
import net.canarymod.config.Configuration;

public class GuiLogOutputHandler extends Handler {

    private static GuiLogOutputHandler guiLogOutputHandler = new GuiLogOutputHandler();

    static {
        Logger.getLogger("Minecraft-Server").addHandler(guiLogOutputHandler);
    }

    private int[] b = new int[1024];
    private int c = 0;
    Formatter a = new GuiLogFormatter(this);
    private static JTextArea d;

    public GuiLogOutputHandler() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception exception) {
            ;
        }
        this.setFormatter(this.a);
        d = new JTextArea();
    }

    @Override
    public void close() {}

    @Override
    public void flush() {}

    @Override
    public void publish(LogRecord logrecord) {
        int i0 = this.d.getDocument().getLength();

        try {
            this.d.append(this.a.format(logrecord));
        } catch (Error error) {
            if (Configuration.getServerConfig().isDebugMode()) {
                error.printStackTrace();
            }
        }
        this.d.setCaretPosition(this.d.getDocument().getLength());
        int i1 = this.d.getDocument().getLength() - i0;

        if (this.b[this.c] != 0) {
            this.d.replaceRange("", 0, this.b[this.c]);
        }

        this.b[this.c] = i1;
        this.c = (this.c + 1) % 1024;
    }

    public JTextArea getTextArea() {
        return this.d;
    }

    public static  GuiLogOutputHandler getOutputHandler() {
        return GuiLogOutputHandler.guiLogOutputHandler;
    }

    public static JTextArea getJTextArea() {
        return GuiLogOutputHandler.d;
    }

    public static String getLog() {
        return GuiLogOutputHandler.d.getText();
    }

    public void poke() {
        Canary.println("TEST");
    }
}
