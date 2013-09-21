package net.minecraft.server;

import javax.swing.*;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class TextAreaLogHandler extends Handler {

    // CanaryMod
    static private TextAreaLogHandler textarealoghandler = new TextAreaLogHandler(new JTextArea());

    static {
        Logger.getLogger("Minecraft-Server").addHandler(textarealoghandler);
    }
    //

    private int[] b = new int[1024];
    private int c;
    Formatter a = new TextAreaLogHandlerINNER1(this);
    private JTextArea d;

    public TextAreaLogHandler(JTextArea jtextarea) {
        this.setFormatter(this.a);
        this.d = jtextarea;
    }

    public void close() {
    }

    public void flush() {
    }

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

    /**
     * Gets the textarea used by the handler
     *
     * @return used JTextArea
     */
    public JTextArea getJTextArea() {
        return this.d;
    }

    /**
     * Gets the Contents of the log up to this point
     *
     * @return current log
     */
    public String getLog() {
        return this.d.getText();
    }

    /**
     * Does nothing but makes sure that the LogHandler is handling Logs
     */
    public void poke() {
    }

    /**
     * Gets the LogHandler
     *
     * @return the LogHandler
     */
    static public TextAreaLogHandler getLogHandler() {
        return textarealoghandler;
    }
}
