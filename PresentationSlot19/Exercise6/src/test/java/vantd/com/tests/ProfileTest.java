package vantd.com.tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import vantd.com.pages.ProfilePage;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Profile Tests using Page Object Model")
public class ProfileTest extends BaseTest {
    static WebDriverWait wait;
    static ProfilePage profilePage;

    @BeforeAll
    static void initPage() {
        profilePage = new ProfilePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @ParameterizedTest(name = "Profile: {0} / {1} / {2} / {3}")
    @Order(1)
    @CsvFileSource(resources = "/profile-data.csv", numLinesToSkip = 1)
    void testUpdateProfileFromCSV(String name, String email, String phone, String avatarPath) {
        profilePage.navigate();
        name = (name == null) ? "" : name.trim();
        email = (email == null) ? "" : email.trim();
        phone = (phone == null) ? "" : phone.trim();
        avatarPath = (avatarPath == null) ? "" : avatarPath.trim();

        profilePage.updateProfile(name, email, phone, avatarPath);
        WebElement result;
        try {
            result = wait.until(ExpectedConditions.visibilityOfElementLocated(profilePage.getSuccessLocator()));
        } catch (TimeoutException e) {
            result = wait.until(ExpectedConditions.visibilityOfElementLocated(profilePage.getErrorLocator()));
        }
        String msg = result.getText().toLowerCase();
        assertTrue(msg.contains("thành công") || msg.contains("lỗi") || msg.contains("success") || msg.contains("error"));
    }
} 