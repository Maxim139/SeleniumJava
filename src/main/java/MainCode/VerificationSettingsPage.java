package MainCode;

import java.time.Duration;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerificationSettingsPage {
    
   WebDriver driver;
   WebDriverWait wait;


    public VerificationSettingsPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

//clicking on the "Launch Job" button
public void clickLaunchJob(){
    
driver.findElement(By.xpath("//div[@class='wizard-right-section']//button//span[text()=' Launch Verification ']")).click();
}

}
