package net.minecraft.server;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import javax.swing.JTextArea;

import net.canarymod.config.Configuration;

public class GuiLogOutputHandler extends Handler {

    private int[] b = new int[1024];
    private int c = 0;
    Formatter a = new GuiLogFormatter(this);
    private JTextArea d;

    public GuiLogOutputHandler(JTextArea jtextarea) {
        this.setFormatter(this.a);
        this.d = jtextarea;
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
}
