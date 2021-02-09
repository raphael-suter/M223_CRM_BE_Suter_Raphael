package ch.axa.ita.rs.m233_ap_b;

import ch.axa.ita.rs.m233_ap_b.model.Message;
import ch.axa.ita.rs.m233_ap_b.utility.HashGenerator;
import ch.axa.ita.rs.m233_ap_b.utility.JsonTool;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class M233ApBApplicationTests {
    @Test
    @DisplayName("HashGenerator.hash(): Should return correct hash.")
    void hashCorrect() {
        assertEquals(HashGenerator.hash("123"), "a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3");
    }

    @Test
    @DisplayName("JsonTool.toJson(): Should return correct json.")
    void jsonCorrect() throws JsonProcessingException {
        assertEquals(JsonTool.toJson(new Message("Test.")), "{\"message\":\"Test.\"}");
    }
}
