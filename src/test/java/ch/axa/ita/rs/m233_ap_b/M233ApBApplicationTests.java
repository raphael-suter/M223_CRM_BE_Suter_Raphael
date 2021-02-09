package ch.axa.ita.rs.m233_ap_b;

import ch.axa.ita.rs.m233_ap_b.model.Message;
import ch.axa.ita.rs.m233_ap_b.rest.AuthenticationFilter;
import ch.axa.ita.rs.m233_ap_b.utility.HashGenerator;
import ch.axa.ita.rs.m233_ap_b.utility.JsonTool;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class M233ApBApplicationTests {
    @Test
    @DisplayName("HashGenerator.hash(): Should return correct hash.")
    void hashCorrect() {
        assertEquals("a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3", HashGenerator.hash("123"));
    }

    @Test
    @DisplayName("JsonTool.toJson(): Should return correct json.")
    void jsonCorrect() throws JsonProcessingException {
        assertEquals("{\"message\":\"Test.\"}", JsonTool.toJson(new Message("Test.")));
    }

    @Test
    @DisplayName("Mock")
    void mockTest() throws ServletException, IOException {
        AuthenticationFilter authenticationFilter = mock(AuthenticationFilter.class);
        HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);

        when(authenticationFilter.isAuthorized(null)).thenReturn(false);
        authenticationFilter.doFilterInternal(null, httpServletResponse, null);

        verify(httpServletResponse).setStatus(401);
    }
}
