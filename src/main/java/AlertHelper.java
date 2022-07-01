import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class AlertHelper {

        private WebDriver driver;
        public AlertHelper(WebDriver driver){
            this.driver=driver;
        }
        private Alert switchToAlert(){
            Alert alert=null;
            try{
                alert=driver.switchTo().alert();

            }catch (NoAlertPresentException e){
                e.printStackTrace();
            }return alert;
        }
        public void acceptAlert(){
            switchToAlert().accept();
        }
        public void dismissAlert(){
            switchToAlert().dismiss();
        }
        public  String getTextFromPrompt(){
            String str= switchToAlert().getText();
            return str;
        }
        public void writeIntoPrompt(String value){
            switchToAlert().sendKeys(value);
        }
        public boolean isAlertPresent(){
            try{
                switchToAlert();
                return true;
            }catch (NoAlertPresentException e){
                e.printStackTrace();
                return false;
            }
        }



    }

