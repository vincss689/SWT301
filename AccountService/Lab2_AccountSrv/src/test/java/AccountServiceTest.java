import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import vantd.com.AccountService;

public class AccountServiceTest {

    @ParameterizedTest(name = "Test {index}: registerAccount({0}, {1}, {2}) = {3}")
    @CsvFileSource(resources = "/test-data.csv", numLinesToSkip = 0)  
    void testRegisterAccountFromCSV(String username, String password, String email, boolean expected) {
        AccountService service = new AccountService("user1", "user1@example.com", "Password1");

        boolean actual = service.registerAccount(username, password, email);

        assertEquals(expected, actual,
                () -> "Failed for input: username=" + username + ", password=" + password + ", email=" + email);
    }

    @Test
    @DisplayName("Kiểm tra isValidEmail với các email hợp lệ và không hợp lệ")
    void testIsValidEmail() {
        AccountService service = new AccountService();
        assertTrue(service.isValidEmail("john.doe@example.com"));
        assertTrue(service.isValidEmail("user_123@mail.co"));
        assertFalse(service.isValidEmail("invalid-email"));
        assertFalse(service.isValidEmail("abc@.com"));
        assertFalse(service.isValidEmail(null));
        assertFalse(service.isValidEmail("abc@mail"));
    }

    @Test
    @DisplayName("Kiểm tra constructor với dữ liệu hợp lệ")
    void testConstructorValidData() {
        assertDoesNotThrow(() -> {
            new AccountService("john123", "john@example.com", "Password1");
        });
    }

    @Test
    @DisplayName("Kiểm tra constructor với dữ liệu không hợp lệ")
    void testConstructorInvalidData() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AccountService(null, "john@example.com", "P123456");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new AccountService("john123", "john@example.com", "");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new AccountService("john123", "john.com", "123456");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new AccountService("john123", null, "P123456");
        });
    }

    @Test
    @DisplayName("Kiểm tra isStrongPassword với các mật khẩu mạnh và yếu")
    void testIsStrongPassword() {
        AccountService service = new AccountService();

        assertTrue(service.isStrongPassword("Password123"));
        assertFalse(service.isStrongPassword("abcdefg1"));  // thiếu chữ hoa
        assertFalse(service.isStrongPassword("ABCDEFG1"));  // thiếu chữ thường
        assertFalse(service.isStrongPassword("Abcdefgh"));  // thiếu số
        assertFalse(service.isStrongPassword("Ab1"));       // quá ngắn
        assertFalse(service.isStrongPassword(null));        // null input
    }


}
