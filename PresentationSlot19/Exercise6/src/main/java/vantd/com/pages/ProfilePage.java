package vantd.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {
    public ProfilePage(WebDriver driver) { super(driver); }

    private By nameField = By.id("name");
    private By emailField = By.id("email");
    private By phoneField = By.id("phone");
    private By avatarUpload = By.id("avatar");
    private By saveButton = By.id("saveProfile");
    private By successMsg = By.cssSelector(".alert-success");
    private By errorMsg = By.cssSelector(".alert-danger");

    public void navigate() {
        navigateTo("http://localhost:8080/profile");
    }

    public void updateProfile(String name, String email, String phone, String avatarPath) {
        type(nameField, name);
        type(emailField, email);
        type(phoneField, phone);
        if (avatarPath != null && !avatarPath.isEmpty()) {
            driver.findElement(avatarUpload).sendKeys(avatarPath);
        }
        click(saveButton);
    }

    public By getSuccessLocator() { return successMsg; }
    public By getErrorLocator() { return errorMsg; }
} 