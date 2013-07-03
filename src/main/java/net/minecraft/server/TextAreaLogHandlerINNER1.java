package net.minecraft.server;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

class TextAreaLogHandlerINNER1 extends Formatter {

    final TextAreaLogHandler a;

    TextAreaLogHandlerINNER1(TextAreaLogHandler textarealoghandler) {
        this.a = textarealoghandler;
    }

    public String format(LogRecord logrecord) {
        StringBuilder stringbuilder = new StringBuilder();

        stringbuilder.append(" [").append(logrecord.getLevel().getName()).append("] ");
        stringbuilder.append(this.formatMessage(logrecord));
        stringbuilder.append('\n');
        Throwable throwable = logrecord.getThrown();

        if (throwable != null) {
            StringWriter stringwriter = new StringWriter();

            throwable.printStackTrace(new PrintWriter(stringwriter));
            stringbuilder.append(stringwriter.toString());
        }

        return stringbuilder.toString();
    }
}
