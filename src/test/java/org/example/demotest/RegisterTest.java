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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class RegisterTest {
    private WebDriver webDriver;

    @Before
    public void setUp() throws Exception {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.navigate().to("https://ticketbox.vn/");
    }

    @Test
    public void registerNoBoth() throws InterruptedException {
        WebElement loginButton = webDriver.findElement(By.xpath("//span[contains(text(), 'Đăng nhập | Đăng ký')]"));
        loginButton.click();
        Thread.sleep(200);
        WebElement txtRegister = webDriver.findElement(By.xpath("//div[contains(text(), 'Tạo tài khoản ngay')]"));
        txtRegister.click();
        Thread.sleep(200);
        WebElement txtEmail = webDriver.findElement(By.id("normal_login_email"));
        txtEmail.sendKeys("1");
        txtEmail.sendKeys(Keys.CONTROL + "a");
        txtEmail.sendKeys(Keys.DELETE);
        Thread.sleep(20);
        WebElement txtPassword = webDriver.findElement(By.id("normal_login_password"));
        txtPassword.sendKeys("1");
        txtPassword.sendKeys(Keys.CONTROL + "a");
        txtPassword.sendKeys(Keys.DELETE);
        Thread.sleep(20);
        WebElement txtRePassword = webDriver.findElement(By.id("normal_login_re_password"));
        txtRePassword.sendKeys("1");
        txtRePassword.sendKeys(Keys.CONTROL + "a");
        txtRePassword.sendKeys(Keys.DELETE);
        Thread.sleep(20);

        WebElement emailRequiredMessage = webDriver.findElement(By.xpath("//div[contains(text(), 'Nhập email của bạn')]"));
        WebElement passwordRequiredMessage = webDriver.findElement(By.xpath("//div[contains(text(), 'Nhập mật khẩu')]"));

        Assert.assertNotNull(emailRequiredMessage);
        //Assert.assertNotNull(passwordRequiredMessage);

    }

    @Test
    public void registerNoPassword() throws InterruptedException {
        WebElement loginButton = webDriver.findElement(By.xpath("//span[contains(text(), 'Đăng nhập | Đăng ký')]"));
        loginButton.click();
        Thread.sleep(200);
        WebElement txtRegister = webDriver.findElement(By.xpath("//div[contains(text(), 'Tạo tài khoản ngay')]"));
        txtRegister.click();
        Thread.sleep(200);
        WebElement txtEmail = webDriver.findElement(By.id("normal_login_email"));
        txtEmail.sendKeys("perlanguyen.ba@gmail.com");
        Thread.sleep(20);
        WebElement txtPassword = webDriver.findElement(By.id("normal_login_password"));
        txtPassword.sendKeys("1");
        txtPassword.sendKeys(Keys.CONTROL + "a");
        txtPassword.sendKeys(Keys.DELETE);
        Thread.sleep(20);
        WebElement txtRePassword = webDriver.findElement(By.id("normal_login_re_password"));
        txtRePassword.sendKeys("1");
        txtRePassword.sendKeys(Keys.CONTROL + "a");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        WebElement passwordRequiredMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Nhập mật khẩu')]")));
        Assert.assertNotNull(passwordRequiredMessage);

    }

    @Test
    public void registerEmailExisted() throws InterruptedException {
        WebElement loginButton = webDriver.findElement(By.xpath("//span[contains(text(), 'Đăng nhập | Đăng ký')]"));
        loginButton.click();
        Thread.sleep(200);
        WebElement txtRegister = webDriver.findElement(By.xpath("//div[contains(text(), 'Tạo tài khoản ngay')]"));
        txtRegister.click();
        Thread.sleep(200);
        WebElement txtEmail = webDriver.findElement(By.id("normal_login_email"));
        txtEmail.sendKeys("perlanguyen.ba@gmail.com");
        Thread.sleep(20);
        WebElement txtPassword = webDriver.findElement(By.id("normal_login_password"));
        txtPassword.sendKeys("123456");
        Thread.sleep(20);
        WebElement txtRePassword = webDriver.findElement(By.id("normal_login_re_password"));
        txtRePassword.sendKeys("123456");
        Thread.sleep(20);
        WebElement loginContinue = webDriver.findElement(By.xpath("//span[contains(text(), 'Tiếp tục')]"));
        loginContinue.click();
        WebElement emailExistedMessage = webDriver.findElement(By.xpath("//div[contains(text(), 'Email đã tồn tại. Vui lòng nhập email khác')]"));
        emailExistedMessage.getText();
        Assert.assertEquals("Email đã tồn tại. Vui lòng nhập email khác", emailExistedMessage.getText());

    }

    @Test
    public void emailNotValid() throws InterruptedException {
        WebElement loginButton = webDriver.findElement(By.xpath("//span[contains(text(), 'Đăng nhập | Đăng ký')]"));
        loginButton.click();
        Thread.sleep(200);
        WebElement txtRegister = webDriver.findElement(By.xpath("//div[contains(text(), 'Tạo tài khoản ngay')]"));
        txtRegister.click();
        Thread.sleep(200);
        WebElement txtEmail = webDriver.findElement(By.id("normal_login_email"));
        txtEmail.sendKeys("perla@com");
        Thread.sleep(20);
        WebElement txtPassword = webDriver.findElement(By.id("normal_login_password"));
        txtPassword.sendKeys("123456");
        Thread.sleep(20);
        WebElement txtRePassword = webDriver.findElement(By.id("normal_login_re_password"));
        txtRePassword.sendKeys("123456");
        Thread.sleep(20);
        WebElement emailNotValidMessage = webDriver.findElement(By.xpath("//div[contains(text(), 'Email sai định dạng')]"));
        emailNotValidMessage.getText();
        Assert.assertEquals("Email sai định dạng", emailNotValidMessage.getText());
    }

    @Test
    public void registerSuccessfully() throws InterruptedException {
        WebElement loginButton = webDriver.findElement(By.xpath("//span[contains(text(), 'Đăng nhập | Đăng ký')]"));
        loginButton.click();
        Thread.sleep(200);
        WebElement txtRegister = webDriver.findElement(By.xpath("//div[contains(text(), 'Tạo tài khoản ngay')]"));
        txtRegister.click();
        Thread.sleep(200);
        WebElement txtEmail = webDriver.findElement(By.id("normal_login_email"));
        txtEmail.sendKeys("thaophuongnguyen865@gmail.com");
        Thread.sleep(20);
        WebElement txtPassword = webDriver.findElement(By.id("normal_login_password"));
        txtPassword.sendKeys("123456");
        Thread.sleep(20);
        WebElement txtRePassword = webDriver.findElement(By.id("normal_login_re_password"));
        txtRePassword.sendKeys("123456");
        Thread.sleep(20);
        WebElement loginContinue = webDriver.findElement(By.xpath("//span[contains(text(), 'Tiếp tục')]"));
        loginContinue.click();
        Thread.sleep(2000);
        WebElement popupOTP = webDriver.findElement(By.xpath("//span[contains(text(), 'Xác thực OTP')]"));
        Assert.assertNotNull(popupOTP);
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
