package res.utils.exceptions;

import java.io.IOException;

public class ConfigFileNotFoundException extends NoStackTraceRuntimeException {
    public ConfigFileNotFoundException(IOException e) {
        super("Le fichier de configuration est introuvable : ".concat(e.getMessage()));
    }
}
