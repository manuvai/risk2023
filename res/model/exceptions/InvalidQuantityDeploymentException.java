package res.model.exceptions;

import res.utils.exceptions.NoStackTraceRuntimeException;

public class InvalidQuantityDeploymentException extends NoStackTraceRuntimeException {
    public InvalidQuantityDeploymentException() {
        super("La quantité fournie n'est pas valide.");
    }
}
