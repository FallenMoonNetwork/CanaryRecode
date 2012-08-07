package net.minecraft.server;


import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import javax.swing.JTextArea;
import net.minecraft.server.OGuiLogFormatter;


public class OGuiLogOutputHandler extends Handler {

    private int[] b = new int[1024];
    private int c = 0;
    Formatter a = new OGuiLogFormatter(this);
    private JTextArea d;

    public OGuiLogOutputHandler(JTextArea var1) {
        super();
        this.setFormatter(this.a);
        this.d = var1;
    }

    @Override
    public void close() {}

    @Override
    public void flush() {}

    @Override
    public void publish(LogRecord var1) {
        int var2 = this.d.getDocument().getLength();

        this.d.append(this.a.format(var1));
        this.d.setCaretPosition(this.d.getDocument().getLength());
        int var3 = this.d.getDocument().getLength() - var2;

        if (this.b[this.c] != 0) {
            this.d.replaceRange("", 0, this.b[this.c]);
        }

        this.b[this.c] = var3;
        this.c = (this.c + 1) % 1024;
    }
}
