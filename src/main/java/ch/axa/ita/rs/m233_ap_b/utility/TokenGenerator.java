package ch.axa.ita.rs.m233_ap_b.utility;

import ch.axa.ita.rs.m233_ap_b.model.Token;

import java.util.UUID;

public class TokenGenerator {
    public static Token token() {
        String uuid = UUID
                .randomUUID()
                .toString();

        return new Token(uuid);
    }
}
