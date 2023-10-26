package res.model.exceptions;

import res.utils.exceptions.NoStackTraceRuntimeException;

public class DiceValueOutOfBoundsException extends NoStackTraceRuntimeException {

    public DiceValueOutOfBoundsException() {
        super("La valeur du dé est en dehors de la plage donnée");
    }

}
