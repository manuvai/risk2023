package res.model.exceptions;

import res.utils.exceptions.NoStackTraceRuntimeException;

public class InvalidCardsToTradeException extends NoStackTraceRuntimeException {
    public InvalidCardsToTradeException() {
        super("Vous devez donner un ensemble de cartes valide");
    }
}
