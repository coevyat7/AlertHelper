import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public record AlertHelper(WebDriver driver) {

    private Alert switchToAlert() {
        Alert alert = null;
        try {
            alert = driver.switchTo().alert();

        } catch (NoAlertPresentException e) {
            e.printStackTrace();
        }
        return alert;
    }

    public void acceptAlert() {
        switchToAlert().accept();
    }

    public void dismissAlert() {
        switchToAlert().dismiss();
    }

    public String getTextFromPrompt() {
        String str = switchToAlert().getText();
        return str;
    }

    public void writeIntoPrompt(String value) {
        switchToAlert().sendKeys(value);
    }

    public boolean isAlertPresent() {
        try {
            switchToAlert();
            return true;
        } catch (NoAlertPresentException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        WebDriver driver = invokeBrowser();
        AlertHelper alertHelper = new AlertHelper(driver);
        //Simple Alert Popup with 1 Option
        driver.findElement(By.name("alertbox")).click();
        alertHelper.acceptAlert();

        //Confirm & Cancel AlertPop.
        driver.findElement(By.name("confirmalertbox")).click(); //Confirm Alert Box
        alertHelper.dismissAlert();
        WebElement confirmAlertBoxMsg = driver.findElement(By.id("demo"));
         waitForElement(driver, confirmAlertBoxMsg, 10);
        System.out.println(confirmAlertBoxMsg.getText());

        //Prompt Alert Box
        driver.findElement(By.name("promptalertbox1234")).click();
        System.out.println(alertHelper.getTextFromPrompt());
        alertHelper.writeIntoPrompt("Hello World");
        alertHelper.acceptAlert();
        WebElement promptMsg = driver.findElement(By.id("demoone"));
        waitForElement(driver, promptMsg, 10);
        System.out.println(promptMsg.getText());

        //isAlertPresent
        driver.findElement(By.name("confirmalertbox")).click();
        boolean alertFlag = alertHelper.isAlertPresent();
        System.out.println(alertFlag);
        if (alertFlag) {
            alertHelper.acceptAlert();


        }
    }

    public static WebDriver invokeBrowser() {
        WebDriver driver;
        try {
            driver = WebDriverManager.chromedriver().create();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            driver.manage().deleteAllCookies();
            driver.get("https://nxtgenaiacademy.com/alertandpopup/");
        } catch (Exception e) {
            e.printStackTrace();
            driver = null;
        }
        return driver;


    }

    public static void waitForElement(WebDriver driver, WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }

}