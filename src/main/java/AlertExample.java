import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertExample {
    static WebDriver driver;

    public static void main(String[] args) {
        driver = WebDriverManager.chromedriver().create();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://nxtgenaiacademy.com/alertandpopup/");
        AlertHelper alertHelper = new AlertHelper(driver);

        //Simple Alert Popup with 1 Option
        driver.findElement(By.name("alertbox")).click();
        alertHelper.acceptAlert();

        //Confirm & Cancel AlertPop.
        driver.findElement(By.name("confirmalertbox")).click(); //Confirm Alert Box
        alertHelper.dismissAlert();
        WebElement confirmAlertBoxMsg = driver.findElement(By.id("demo"));
        WebElement msg1 = waitForElement(driver, confirmAlertBoxMsg, 10);
        System.out.println(msg1.getText());

        //Prompt Alert Box
        driver.findElement(By.name("promptalertbox1234")).click();
        System.out.println(alertHelper.getTextFromPrompt());
        alertHelper.writeIntoPrompt("Hello World");
        alertHelper.acceptAlert();
        WebElement promptMsg = driver.findElement(By.id("demoone"));
        WebElement msg2 = waitForElement(driver, promptMsg, 10);
        System.out.println(msg2.getText());

        //isAlertPresent
        driver.findElement(By.name("confirmalertbox")).click();
        boolean alertFlag = alertHelper.isAlertPresent();
        System.out.println(alertFlag);
        if (alertFlag) {
            alertHelper.acceptAlert();


        }
    }

    public static WebElement waitForElement(WebDriver driver, WebElement element, int time) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;

    }

}