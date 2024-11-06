package org.example.demotest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginTest {
    private WebDriver webDriver;

    @Before
    public void setUp() throws Exception {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.navigate().to("https://ticketbox.vn/");
    }

    @Test
    public void loginNoUsername() throws InterruptedException {
        WebElement loginButton = webDriver.findElement(By.xpath("//span[contains(text(), 'Đăng nhập | Đăng ký')]"));
        loginButton.click();
        Thread.sleep(2000);
        //By loginBtnLocation = RelativeLocator.with(By.tagName("button")).toRightOf(By.className("Container__AlignItemsCenter-sc-17x1kk6-4 Guest__LinkWrapper-sc-17ybgyr-0 fYSkyu fEYkZS"));
//        WebElement btnLogin = webDriver.findElement(loginBtnLocation);
//        System.out.println(btnLogin);
//        btnLogin.click();
//        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.urlToBe("https://ticketbox.vn/"));
        WebElement txtUsername = webDriver.findElement(By.id("normal_login_username"));
        txtUsername.sendKeys("2");
        Thread.sleep(20);
        txtUsername.sendKeys(Keys.CONTROL + "a");
        txtUsername.sendKeys(Keys.DELETE);        //Thread.sleep(5000);
        WebElement txtPassword = webDriver.findElement(By.id("normal_login_password"));
        txtPassword.sendKeys("1234567");
    }

    @Test
    public void loginNoPassword() throws InterruptedException {
        WebElement loginButton = webDriver.findElement(By.xpath("//span[contains(text(), 'Đăng nhập | Đăng ký')]"));
        loginButton.click();
        Thread.sleep(2000);
        WebElement txtUsername = webDriver.findElement(By.id("normal_login_username"));
        txtUsername.sendKeys("tungxuanmai2003@gmail.com");
        Thread.sleep(20);
//        txtUsername.sendKeys(Keys.CONTROL + "a");
//        txtUsername.sendKeys(Keys.DELETE);
        WebElement txtPassword = webDriver.findElement(By.id("normal_login_password"));
        txtPassword.sendKeys("1");
        txtPassword.sendKeys(Keys.CONTROL + "a");
        txtPassword.sendKeys(Keys.DELETE);
    }

    @Test
    public void loginWrongBoth() throws InterruptedException {
        WebElement loginButton = webDriver.findElement(By.xpath("//span[contains(text(), 'Đăng nhập | Đăng ký')]"));
        loginButton.click();
        Thread.sleep(2000);
        WebElement txtUsername = webDriver.findElement(By.id("normal_login_username"));
        txtUsername.sendKeys("tungxuanmai2@gmail.com");
        WebElement txtPassword = webDriver.findElement(By.id("normal_login_password"));
        txtPassword.sendKeys("1");
        Thread.sleep(20);
        WebElement loginContinue = webDriver.findElement(By.xpath("//button[contains(text(), 'Tiếp tục')]"));
        loginButton.click();
    }

    @Test
    public void loginRightUsernameWrongPass() throws InterruptedException {
        WebElement loginButton = webDriver.findElement(By.xpath("//span[contains(text(), 'Đăng nhập | Đăng ký')]"));
        loginButton.click();
        Thread.sleep(2000);
        WebElement txtUsername = webDriver.findElement(By.id("normal_login_username"));
        txtUsername.sendKeys("tungxuanmai2003@gmail.com");
        WebElement txtPassword = webDriver.findElement(By.id("normal_login_password"));
        txtPassword.sendKeys("1");
        Thread.sleep(20);
        WebElement loginContinue = webDriver.findElement(By.xpath("//span[contains(text(), 'Tiếp tục')]"));
        loginContinue.click();
    }

    @Test
    public void loginSuccessfully() throws InterruptedException {
        WebElement loginButton = webDriver.findElement(By.xpath("//span[contains(text(), 'Đăng nhập | Đăng ký')]"));
        loginButton.click();
        Thread.sleep(2000);
        WebElement txtUsername = webDriver.findElement(By.id("normal_login_username"));
        txtUsername.sendKeys("tungxuanmai2003@gmail.com");
        WebElement txtPassword = webDriver.findElement(By.id("normal_login_password"));
        txtPassword.sendKeys("Springter2003");
        Thread.sleep(20);
        WebElement loginContinue = webDriver.findElement(By.xpath("//span[contains(text(), 'Tiếp tục')]"));
        Thread.sleep(200);
        loginContinue.click();
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
