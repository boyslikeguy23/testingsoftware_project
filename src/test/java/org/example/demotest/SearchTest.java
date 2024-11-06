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

public class SearchTest {
    private WebDriver webDriver;

    @Before
    public void setUp() throws Exception {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.navigate().to("https://ticketbox.vn/");
    }

    @Test
    public void searchNothing() {
        By searchBtnLocation = RelativeLocator.with(By.tagName("button")).toRightOf(By.id("search-input"));
        WebElement btnSearch = webDriver.findElement(searchBtnLocation);
        System.out.println(btnSearch);
        btnSearch.click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlToBe("https://ticketbox.vn/search?q="));

        String urlCurrent = webDriver.getCurrentUrl();
        System.out.println(urlCurrent);
        Assert.assertEquals("https://ticketbox.vn/search?q=", urlCurrent);
    }

    @Test
    public void searchSomething() {
        WebElement txtSearch = webDriver.findElement(By.id("search-input"));
        txtSearch.sendKeys("Hà Nội");
        By searchBtnLocation = RelativeLocator.with(By.tagName("button")).toRightOf(By.id("search-input"));
        WebElement btnSearch = webDriver.findElement(searchBtnLocation);
        btnSearch.click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.urlContains("https://ticketbox.vn/search?q=Hà+Nội"));

        String urlCurrent = webDriver.getCurrentUrl();
        System.out.println(urlCurrent);
        Assert.assertEquals("https://ticketbox.vn/search?q=Hà+Nội", urlCurrent);
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