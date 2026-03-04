package cat.politecnicllevant.prova.exception;

import java.time.Instant;

public record ApiError(Instant timestamp, int statusCode, String error, String message, String path) {
}
