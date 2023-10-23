package res.utils.logger;

import java.util.Arrays;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

/**
 * Classe permettant de créer des logs.
 *
 * Pour l'utiliser : Logger.log(Level, String)
 */
public class Logger {

    private static final java.util.logging.Logger _LOGGER = java.util.logging.Logger
            .getLogger(Logger.class.getName());

    private Logger() {

    }

    public static void main(String[] args) {
        Logger.info("Salut");
        Logger.warning("Attention !!!");
        Logger.severe("Attention !!!");
    }

    /**
     * Permet d'afficher des messages de type WARNING
     *
     * @param message
     */
    public static void warning(String message) {

        log(Level.WARNING, message);
    }

    /**
     * Permet d'afficher des messages de type INFO
     *
     * @param message
     */
    public static void info(String message) {

        log(Level.INFO, message);
    }

    /**
     * Permet d'afficher des messages de type SEVERE
     *
     * @param message
     */
    public static void severe(String message) {

        log(Level.SEVERE, message);
    }

    /**
     * Permet de créer des logs avec des messages de niveau de LOG différent
     * @param level
     * @param message
     * @param args
     */
    public static void log(Level level, String message, Object... args) {

        _LOGGER.setUseParentHandlers(false);
        boolean isAlreadyFormatted = Arrays.stream(_LOGGER.getHandlers())
                .anyMatch(handler -> CustomFormatter.class.equals(handler.getFormatter().getClass()));

        if (!isAlreadyFormatted) {
            ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setFormatter(new CustomFormatter());


            _LOGGER.addHandler(consoleHandler);

        }
        _LOGGER.log(level, message, args);

    }
}
