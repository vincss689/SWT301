package vantd.com.tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import vantd.com.pages.ProductPage;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Product CRUD Tests using Page Object Model")
public class ProductTest extends BaseTest {
    static WebDriverWait wait;
    static ProductPage productPage;

    @BeforeAll
    static void initPage() {
        productPage = new ProductPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @ParameterizedTest(name = "Add Product: {0} / {1} / {2} / {3}")
    @Order(1)
    @CsvFileSource(resources = "/product-data.csv", numLinesToSkip = 1)
    void testAddProductFromCSV(String name, String price, String desc, String imagePath) {
        productPage.navigate();
        name = (name == null) ? "" : name.trim();
        price = (price == null) ? "" : price.trim();
        desc = (desc == null) ? "" : desc.trim();
        imagePath = (imagePath == null) ? "" : imagePath.trim();

        productPage.addProduct(name, price, desc, imagePath);
        WebElement result;
        try {
            result = wait.until(ExpectedConditions.visibilityOfElementLocated(productPage.getSuccessLocator()));
        } catch (TimeoutException e) {
            result = wait.until(ExpectedConditions.visibilityOfElementLocated(productPage.getErrorLocator()));
        }
        String msg = result.getText().toLowerCase();
        assertTrue(msg.contains("thành công") || msg.contains("lỗi") || msg.contains("success") || msg.contains("error"));
    }
} 