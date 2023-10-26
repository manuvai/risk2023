package res.utils.exceptions;

import java.sql.SQLException;

public class DbConnectionException extends NoStackTraceRuntimeException {
    public DbConnectionException(SQLException e) {
        super("Une erreur est survenue lors de la création de la connexion à la base de données : ".concat(e.getMessage()));
    }
}
