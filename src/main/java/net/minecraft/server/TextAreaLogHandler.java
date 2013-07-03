package net.minecraft.server;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import javax.swing.JTextArea;

public class TextAreaLogHandler extends Handler {

    private int[] b = new int[1024];
    private int c;
    Formatter a = new TextAreaLogHandlerINNER1(this);
    private JTextArea d;

    public TextAreaLogHandler(JTextArea jtextarea) {
        this.setFormatter(this.a);
        this.d = jtextarea;
    }

    public void close() {}

    public void flush() {}

    public void publish(LogRecord logrecord) {
        int i0 = this.d.getDocument().getLength();

        this.d.append(this.a.format(logrecord));
        this.d.setCaretPosition(this.d.getDocument().getLength());
        int i1 = this.d.getDocument().getLength() - i0;

        if (this.b[this.c] != 0) {
            this.d.replaceRange("", 0, this.b[this.c]);
        }

        this.b[this.c] = i1;
        this.c = (this.c + 1) % 1024;
    }
}
