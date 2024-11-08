package org.example.demotest;

import org.junit.After;
import org.junit.Assert;
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
    private String homePageURL = "https://ticketbox.vn/";

    @Before
    public void setUp() throws Exception {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.navigate().to(homePageURL);
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
        txtUsername.sendKeys("test@gmail.com");
        Thread.sleep(20);
        txtUsername.sendKeys(Keys.CONTROL + "a");
        txtUsername.sendKeys(Keys.DELETE);        //Thread.sleep(5000);
        WebElement txtPassword = webDriver.findElement(By.id("normal_login_password"));
        txtPassword.sendKeys("1234567");

        //phân biệt với text trong placeholder của thẻ input normal_login_password
        WebElement emailRequiredMessage = webDriver.findElement(By.xpath("//div[contains(@class, 'ant-form-item-explain-error') and contains(text(), 'Nhập email hoặc số điện thoại')]"));
        Assert.assertNotNull(emailRequiredMessage);
    }

    @Test
    public void loginNoPassword() throws InterruptedException {
        WebElement loginButton = webDriver.findElement(By.xpath("//span[contains(text(), 'Đăng nhập | Đăng ký')]"));
        loginButton.click();
        Thread.sleep(2000);
        WebElement txtUsername = webDriver.findElement(By.id("normal_login_username"));
        txtUsername.sendKeys("tungxuanmai2003@gmail.com");
        Thread.sleep(20);
        WebElement txtPassword = webDriver.findElement(By.id("normal_login_password"));
        // O duoi la to hop de khien cho textbox trong boi vi
        txtPassword.sendKeys("1234567");
        txtPassword.sendKeys(Keys.CONTROL + "a");
        txtPassword.sendKeys(Keys.DELETE);

        WebElement passwordRequiredMessage = webDriver.findElement(By.xpath("//div[contains(@class, 'ant-form-item-explain-error') and contains(text(), 'Nhập mật khẩu')]"));
        Assert.assertNotNull(passwordRequiredMessage);
    }

    @Test
    public void testWrongUsernameAndPassword() throws InterruptedException {
        WebElement loginButton = webDriver.findElement(By.xpath("//span[contains(text(), 'Đăng nhập | Đăng ký')]"));
        loginButton.click();
        Thread.sleep(2000);

        WebElement txtUsername = webDriver.findElement(By.id("normal_login_username"));
        txtUsername.sendKeys("tungxuanmai2@gmail.com");
        Thread.sleep(20);
        WebElement txtPassword = webDriver.findElement(By.id("normal_login_password"));
        txtPassword.sendKeys("abcdfdfd");
        Thread.sleep(20);
        WebElement buttonLogin = webDriver.findElement(By.xpath("//button[span[text()='Tiếp tục']]"));
        buttonLogin.click();

        WebElement accountDoesntExistMessage = webDriver.findElement(By.xpath("//div[contains(@class, 'ant-form-item-explain-error') and contains(text(), 'Tài khoản chưa tồn tại. Vui lòng đăng ký tài khoản mới')]"));
        Assert.assertNotNull(accountDoesntExistMessage);
    }

    @Test
    public void loginRightUsernameWrongPass() throws InterruptedException {
        WebElement loginButton = webDriver.findElement(By.xpath("//span[contains(text(), 'Đăng nhập | Đăng ký')]"));
        loginButton.click();
        Thread.sleep(2000);
        WebElement txtUsername = webDriver.findElement(By.id("normal_login_username"));
        txtUsername.sendKeys("tungxuanmai2003@gmail.com");
        WebElement txtPassword = webDriver.findElement(By.id("normal_login_password"));
        txtPassword.sendKeys("312312312");
        Thread.sleep(20);
        WebElement buttonLogin = webDriver.findElement(By.xpath("//button[span[text()='Tiếp tục']]"));
        buttonLogin.click();

        WebElement accountDoesntExistMessage = webDriver.findElement(By.xpath("//div[contains(@class, 'ant-form-item-explain-error') and contains(text(), 'Thông tin đăng nhập không hợp lệ. Bạn còn 2 lần thử lại')]"));
        Assert.assertNotNull(accountDoesntExistMessage);
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
        WebElement buttonLogin = webDriver.findElement(By.xpath("//button[span[text()='Tiếp tục']]"));
        buttonLogin.click();

        //kiểm tra về trang chủ chưa
        String urlCurrent = webDriver.getCurrentUrl();
        Assert.assertEquals(homePageURL, urlCurrent);
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
