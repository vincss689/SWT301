package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import pages.RegistrationPage;

import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Registration Tests for demoqa.com")
public class RegistrationTest extends BaseTest {
    static RegistrationPage registrationPage;

    @BeforeAll
    static void setUpPage() {
        registrationPage = new RegistrationPage(driver);
    }

    @Test
    @Order(1)
    @DisplayName("Should register successfully with object data")
    void testRegistrationWithObject() {
        registrationPage.navigate();
        registrationPage.enterFirstName("Object");
        registrationPage.enterLastName("Test");
        registrationPage.enterEmail("object.test@example.com");
        registrationPage.selectGender("Male");
        registrationPage.enterMobile("0999999999");
        registrationPage.setDateOfBirth("01 Jan 2000");
        registrationPage.enterSubject("Maths");
        registrationPage.selectHobby("Sports");
        String imagePath = Paths.get("src/test/resources/image.png").toAbsolutePath().toString();
        registrationPage.uploadPicture(imagePath);
        registrationPage.enterAddress("Object Address");
        registrationPage.selectState("NCR");
        registrationPage.selectCity("Delhi");
        registrationPage.submitForm();
        assertTrue(registrationPage.getModalTitle().contains("Thanks for submitting the form"));
        assertTrue(registrationPage.getModalContent().contains("Object Test"));
        assertTrue(registrationPage.getModalContent().contains("image.png"));
        registrationPage.closeModal();
    }

    @ParameterizedTest(name = "CSV Inline: {0} {1}")
    @CsvSource({
        "Inline, Test, inline.test@example.com, Female, 0911222333, '10 Feb 1999', English, Reading, 'Inline Address', NCR, Delhi, 'Inline Test', image.png"
    })
    @Order(2)
    @DisplayName("Registration with CSV inline data")
    void testRegistrationWithCsvInline(String firstName, String lastName, String email, String gender, String mobile,
                                       String dob, String subject, String hobby, String address, String state, String city, String expectedName, String image) {
        registrationPage.navigate();
        registrationPage.enterFirstName(firstName);
        registrationPage.enterLastName(lastName);
        registrationPage.enterEmail(email);
        registrationPage.selectGender(gender);
        registrationPage.enterMobile(mobile);
        registrationPage.setDateOfBirth(dob);
        registrationPage.enterSubject(subject);
        registrationPage.selectHobby(hobby);
        if (image != null && !image.isBlank()) {
            String imagePath = Paths.get("src/test/resources/" + image).toAbsolutePath().toString();
            registrationPage.uploadPicture(imagePath);
        }
        registrationPage.enterAddress(address);
        registrationPage.selectState(state);
        registrationPage.selectCity(city);
        registrationPage.submitForm();
        assertTrue(registrationPage.getModalTitle().contains("Thanks for submitting the form"));
        assertTrue(registrationPage.getModalContent().contains(expectedName));
        if (image != null && !image.isBlank()) {
            assertTrue(registrationPage.getModalContent().contains(image));
        }
        registrationPage.closeModal();
    }

    @ParameterizedTest(name = "Register: {0} {1} / {2}")
    @CsvFileSource(resources = "/registration-data.csv", numLinesToSkip = 1)
    @Order(3)
    @DisplayName("Registration with multiple data from CSV file")
    void testRegistrationWithCSV(
            String firstName, String lastName, String email, String gender, String mobile,
            String dob, String subject, String hobby, String address, String state, String city, String expectedName, String image
    ) {
        registrationPage.navigate();
        registrationPage.enterFirstName(firstName);
        registrationPage.enterLastName(lastName);
        registrationPage.enterEmail(email);
        registrationPage.selectGender(gender);
        registrationPage.enterMobile(mobile);
        registrationPage.setDateOfBirth(dob);
        registrationPage.enterSubject(subject);
        registrationPage.selectHobby(hobby);
        if (image != null && !image.isBlank()) {
            String imagePath = Paths.get("src/test/resources/" + image).toAbsolutePath().toString();
            registrationPage.uploadPicture(imagePath);
        }
        registrationPage.enterAddress(address);
        registrationPage.selectState(state);
        registrationPage.selectCity(city);
        registrationPage.submitForm();
        assertTrue(registrationPage.getModalTitle().contains("Thanks for submitting the form"));
        assertTrue(registrationPage.getModalContent().contains(expectedName));
        if (image != null && !image.isBlank()) {
            assertTrue(registrationPage.getModalContent().contains(image));
        }
        registrationPage.closeModal();
    }
} 