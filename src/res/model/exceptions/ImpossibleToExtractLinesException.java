package res.model.exceptions;

import res.utils.exceptions.NoStackTraceRuntimeException;

public class ImpossibleToExtractLinesException extends NoStackTraceRuntimeException {
    public ImpossibleToExtractLinesException() {
        super("Il est impossible d'extraire les lignes du fichier");
    }
}
