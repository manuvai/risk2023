package res.model.exceptions;

import res.utils.exceptions.NoStackTraceRuntimeException;

public class CanNotLaunchNegativeAmountOfDiceException extends NoStackTraceRuntimeException {
    public CanNotLaunchNegativeAmountOfDiceException() {
        super("Vous ne pouvez pas lancer un nombre négatif de dés");
    }
}
