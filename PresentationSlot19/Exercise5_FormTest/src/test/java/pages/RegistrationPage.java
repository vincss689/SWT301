package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage extends BasePage {
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private By firstNameField = By.id("firstName");
    private By lastNameField = By.id("lastName");
    private By emailField = By.id("userEmail");
    private By mobileField = By.id("userNumber");
    private By dobInput = By.id("dateOfBirthInput");
    private By subjectsInput = By.id("subjectsInput");
    private By uploadPicture = By.id("uploadPicture");
    private By addressField = By.id("currentAddress");
    private By stateDropdown = By.id("react-select-3-input");
    private By cityDropdown = By.id("react-select-4-input");
    private By submitButton = By.id("submit");
    private By modalTitle = By.id("example-modal-sizes-title-lg");
    private By modalContent = By.className("table-responsive");
    private By closeModalButton = By.id("closeLargeModal");
    // Dynamic locators
    private By genderRadio(String gender) { return By.xpath("//label[text()='" + gender + "']"); }
    private By hobbiesCheckbox(String hobby) { return By.xpath("//label[text()='" + hobby + "']"); }

    // Actions
    public void navigate() {
        navigateTo("https://demoqa.com/automation-practice-form");
    }

    public void enterFirstName(String firstName) {
        type(firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        type(lastNameField, lastName);
    }

    public void enterEmail(String email) {
        type(emailField, email);
    }

    public void selectGender(String gender) {
        scrollToElement(genderRadio(gender));
        click(genderRadio(gender));
    }

    public void enterMobile(String mobile) {
        type(mobileField, mobile);
    }

    public void setDateOfBirth(String dob) {
        scrollToElement(dobInput);
        click(dobInput);
        WebElement input = waitForVisibility(dobInput);
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(dob);
        input.sendKeys(Keys.ENTER);
    }

    public void enterSubject(String subject) {
        type(subjectsInput, subject);
        driver.findElement(subjectsInput).sendKeys(Keys.ENTER);
    }

    public void selectHobby(String hobby) {
        scrollToElement(hobbiesCheckbox(hobby));
        click(hobbiesCheckbox(hobby));
    }

    public void uploadPicture(String filePath) {
        driver.findElement(uploadPicture).sendKeys(filePath);
    }

    public void enterAddress(String address) {
        type(addressField, address);
    }

    public void selectState(String state) {
        scrollToElement(stateDropdown);
        type(stateDropdown, state);
        driver.findElement(stateDropdown).sendKeys(Keys.ENTER);
    }

    public void selectCity(String city) {
        scrollToElement(cityDropdown);
        type(cityDropdown, city);
        driver.findElement(cityDropdown).sendKeys(Keys.ENTER);
    }

    public void submitForm() {
        scrollToElement(submitButton);
        click(submitButton);
    }

    public String getModalTitle() {
        return getText(modalTitle);
    }

    public String getModalContent() {
        return getText(modalContent);
    }

    public void closeModal() {
        if (isElementVisible(closeModalButton)) {
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript(
                "document.getElementById('closeLargeModal').click();"
            );
            // Chờ cho đến khi toàn bộ modal dialog biến mất
            new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(3))
                .until(org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated(
                    By.cssSelector("div[role='dialog']")));
            try {
                Thread.sleep(300); // Chờ DOM cập nhật hoàn toàn
            } catch (InterruptedException e) {
                // ignore
            }
        }
    }
} 