package validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationTest {

    @Test
    void emailValido() {
        String emailValido = "jose@gmail.com";
        assertTrue(Validation.emailValido(emailValido));
        String emailInvalido = "djaskldjasldj";
        assertFalse(Validation.emailValido(emailInvalido));
    }
}