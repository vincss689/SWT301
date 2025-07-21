package vantd.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePage {
    public ProfilePage(WebDriver driver) { super(driver); }

    private By nameField = By.id("name");
    private By emailField = By.id("email");
    private By phoneField = By.id("phone");
    private By avatarUpload = By.id("avatarFile");
    private By saveButton = By.cssSelector("button[type='submit']");
    private By successMsg = By.cssSelector(".alert-info");
    private By errorMsg = By.cssSelector(".alert-info");

    public void navigate() {
        navigateTo("http://localhost:8080/profile");
    }

    public void updateProfile(String name, String email, String phone, String avatarPath) {
        type(nameField, name);
        type(emailField, email);
        type(phoneField, phone);
        if (avatarPath != null && !avatarPath.isEmpty()) {
            String absPath = new java.io.File(avatarPath).getAbsolutePath();
            driver.findElement(avatarUpload).sendKeys(absPath);
        }
        click(saveButton);
    }

    public By getSuccessLocator() { return successMsg; }
    public By getErrorLocator() { return errorMsg; }
} 