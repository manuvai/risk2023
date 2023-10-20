package res.utils.exceptions;

public class NoDbDriverFoundException extends NoStackTraceRuntimeException {
    public NoDbDriverFoundException(ClassNotFoundException e) {
        super("Aucun driver trouvé : ".concat(e.getMessage()));
    }
}
