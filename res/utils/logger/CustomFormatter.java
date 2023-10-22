package res.utils.logger;

import java.util.Date;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

public class CustomFormatter extends SimpleFormatter {
    private static final String FORMAT = "[%1$tF %1$tT] [%2$-7s] %3$s %n";

    @Override
    public synchronized String format(LogRecord logRecord) {
        return String.format(FORMAT,
                new Date(logRecord.getMillis()),
                logRecord.getLevel().getLocalizedName(),
                logRecord.getMessage());
    }
}
