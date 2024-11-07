package org.example.demotest;

import org.checkerframework.checker.units.qual.A;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TicketActionTest {
//    private String homePageURL = "https://ticketbox.vn/";
    private String eventWithPaymentURL = "https://ticketbox.vn/da-nang-nhung-thanh-pho-mo-mang-2024-22981?utm_medium=trending-events&utm_source=tkb-homepage";
    private String eventWithSoldOutTicket = "https://ticketbox.vn/anh-trai-say-hi-concert-2024-day-3-23254?utm_medium=hero-banner&utm_source=tkb-homepage";
    private WebDriver webDriver;

    @Before
    public void setUp() throws Exception {
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testTicketPayment() throws InterruptedException {
        webDriver.navigate().to(eventWithPaymentURL);
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
        Thread.sleep(200);

        WebElement buyNowBtn = webDriver.findElement(By.id("buynow-btn"));
        buyNowBtn.click();

        WebElement plusButton = webDriver.findElement(By.xpath("//button[contains(@class, 'ant-btn') and span[text()='+']]"));
        plusButton.click();
        Thread.sleep(2000);

        WebElement continueButton = webDriver.findElement(By.xpath("//button[contains(@class, 'style__ContinueBtnWrapper') and contains(text(), 'Tiếp tục - 699.000 đ')]"));
        continueButton.click();

        WebElement phoneNumberInput = webDriver.findElement(By.id("question_0"));
        phoneNumberInput.sendKeys("09123123123");
        WebElement emailInput = webDriver.findElement(By.id("question_1"));
        emailInput.sendKeys("tungxuanmai2003@gmail.com");
        WebElement addressInput = webDriver.findElement(By.id("question_2"));
        addressInput.sendKeys("Ha Noi");

        // Find all radio buttons on the page
        List<WebElement> radioButtons = webDriver.findElements(By.xpath("//input[@type='radio']"));
        for (WebElement radioButton : radioButtons) {
            if (!radioButton.isSelected()) {
                radioButton.click();
            }
        }
        Thread.sleep(2000);

        WebElement continueButtonAfterFormFilling = webDriver.findElement(By.id("continue-btn"));
        continueButtonAfterFormFilling.click();

        Thread.sleep(2000);
        WebElement thanhToanButton = webDriver.findElement(By.id("continue-btn"));
        thanhToanButton.click();

        WebElement qrCode = webDriver.findElement(By.xpath("//div[contains(@class, 'qrcode-content')]"));
        Assert.assertNotNull("Đã hiện thành công qrCode để thực hiện thanh toán",qrCode);
    }

    @Test
    public void testTicketPaymentWithSoldOutTicket() throws InterruptedException {
        webDriver.navigate().to(eventWithSoldOutTicket);
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
        Thread.sleep(200);

        WebElement buyNowBtn = webDriver.findElement(By.id("buynow-btn"));
        buyNowBtn.click();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        List<WebElement> canvases = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName("canvas")));
        WebElement mainCanvas = canvases.get(1);

//        // Get the canvas dimensions
//        int canvasWidth = mainCanvas.getSize().getWidth();
//        int canvasHeight = mainCanvas.getSize().getHeight();
//
//        // Set proportional coordinates for the gray area (CAT 1A as an example)
//        // Adjust these values based on where the gray areas are located in the canvas image
//        double proportionX = 0.5; // 50% from the left (adjust as necessary)
//        double proportionY = 0.2; // 20% from the top (adjust as necessary)
//
//        // Convert proportions to pixel coordinates
//        int clickX = (int) (canvasWidth * proportionX);
//        int clickY = (int) (canvasHeight * proportionY);
//
//        // Perform the click action
//        new Actions(webDriver)
//                .moveToElement(mainCanvas, clickX - canvasWidth / 2, clickY - canvasHeight / 2) // center the offset
//                .click()
//                .perform();

        //Đợi cho đến khi nào nó hiện thông báo lỗi
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".ant-notification-notice.ant-notification-notice-error")
        ));
        WebElement message = notification.findElement(By.cssSelector(".ant-notification-notice-description"));

        String notificationText = message.getText();
        System.out.println("Đã bắt được thông báo lỗi với thông điệp: " + notificationText);
        Assert.assertNotNull("Đã hiện thông báo lỗi khi chọn vé đã hết", notificationText);
    }

    @After
    public void tearDown() throws Exception {
        try {
            Thread.sleep(15000);
            webDriver.close();
            webDriver.quit();
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi: " + e);
        }
    }
}
