package vantd.com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) { super(driver); }

    private By addButton = By.id("addProduct");
    private By nameField = By.id("productName");
    private By priceField = By.id("productPrice");
    private By descField = By.id("productDesc");
    private By imageUpload = By.id("imageFile");
    private By saveButton = By.cssSelector("button[type='submit']");
    private By productList = By.cssSelector(".product-list");
    private By successMsg = By.cssSelector(".alert-info");
    private By errorMsg = By.cssSelector(".alert-info");

    public void navigate() {
        navigateTo("http://localhost:8080/products");
    }

    public void addProduct(String name, String price, String desc, String imagePath) {
        // click(addButton); // Bỏ bước này vì form luôn hiển thị
        type(nameField, name);
        type(priceField, price);
        type(descField, desc);
        if (imagePath != null && !imagePath.isEmpty()) {
            String absPath = new java.io.File(imagePath).getAbsolutePath();
            driver.findElement(imageUpload).sendKeys(absPath);
        }
        click(saveButton);
    }

    public boolean isProductInList(String name) {
        return getText(productList).contains(name);
    }

    public By getSuccessLocator() { return successMsg; }
    public By getErrorLocator() { return errorMsg; }
} 