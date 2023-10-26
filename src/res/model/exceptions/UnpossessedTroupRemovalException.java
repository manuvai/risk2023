package res.model.exceptions;

import res.utils.exceptions.NoStackTraceRuntimeException;

public class UnpossessedTroupRemovalException extends NoStackTraceRuntimeException {

    public UnpossessedTroupRemovalException() {
        super("Vous ne pouvez pas retirer des troupes que vous n'avez pas");
    }
}
