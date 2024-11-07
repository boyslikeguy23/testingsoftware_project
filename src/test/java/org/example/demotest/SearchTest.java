package org.example.demotest;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchTest {
    private WebDriver webDriver;
    private String homePageURL = "https://ticketbox.vn/";

    @Before
    public void setUp() throws Exception {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.navigate().to(homePageURL);
    }

    @Test
    public void searchNothing() {
        By searchBtnLocation = RelativeLocator.with(By.tagName("button")).toRightOf(By.id("search-input"));
        WebElement btnSearch = webDriver.findElement(searchBtnLocation);
        btnSearch.click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("https://ticketbox.vn/search?q="));

        String urlCurrent = webDriver.getCurrentUrl();
        Assert.assertEquals(homePageURL, urlCurrent);
    }

    @Test
    public void searchNonExistingEvent(){
        WebElement txtSearch = webDriver.findElement(By.id("search-input"));
        txtSearch.sendKeys("XYZ123");
        By searchBtnLocation = RelativeLocator.with(By.tagName("button")).toRightOf(By.id("search-input"));
        WebElement btnSearch = webDriver.findElement(searchBtnLocation);
        btnSearch.click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("https://ticketbox.vn/search?q=XYZ123"));

        WebElement noResultMessage = webDriver.findElement(By.xpath("//div[contains(text(), 'Rất tiếc! Không tìm thấy kết quả nào')]"));
        Assert.assertNotNull(noResultMessage);
    }

    @Test
    public void searchExistingEventWithKeyword() {
        WebElement txtSearch = webDriver.findElement(By.id("search-input"));
        txtSearch.sendKeys("Hanoi");
        By searchBtnLocation = RelativeLocator.with(By.tagName("button")).toRightOf(By.id("search-input"));
        WebElement btnSearch = webDriver.findElement(searchBtnLocation);
        btnSearch.click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("https://ticketbox.vn/search?q=Hanoi"));

        // Tìm các thẻ chứa title là "Hanoi"
        List<WebElement> results = webDriver.findElements(By.xpath("//*[contains(text(), 'Hanoi')]"));

        Assert.assertFalse("Ít nhất 1 thẻ sự kiện chứa chữ Hà Nội", results.isEmpty());
    }

    @After
    public void tearDown() throws Exception {
        try {
            Thread.sleep(2000);
            webDriver.close();
            webDriver.quit();
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi: " + e);
        }
    }
}