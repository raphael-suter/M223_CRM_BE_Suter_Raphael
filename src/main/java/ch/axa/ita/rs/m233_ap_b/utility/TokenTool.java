package ch.axa.ita.rs.m233_ap_b.utility;

import org.springframework.http.HttpHeaders;

import java.util.Objects;
import java.util.UUID;

public class TokenTool {
    public static String generateToken() {
        String token = UUID
                .randomUUID()
                .toString();

        return token;
    }

    public static String extractTokenFromHttpHeaders(HttpHeaders httpHeaders) {
        return Objects.requireNonNull(httpHeaders
                .get("authorization"))
                .get(0)
                .substring(7);
    }
}
