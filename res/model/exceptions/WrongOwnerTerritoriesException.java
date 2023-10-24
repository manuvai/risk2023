package res.model.exceptions;

import res.utils.exceptions.NoStackTraceRuntimeException;

public class WrongOwnerTerritoriesException extends NoStackTraceRuntimeException {
    public WrongOwnerTerritoriesException() {
        super("Vous devez être propriétaire de ce territoire pour effectuer cette opération");
    }
}
