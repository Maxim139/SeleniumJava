package MainCode;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListDetails {
    

    WebDriver driver;
    WebDriverWait wait;
    public ListDetails(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }


    public void clickOnDownload(){
       
        WebElement statusLabelElement = driver.findElement(By.cssSelector("div.status-chip"));
        String statusString = statusLabelElement.getText();

        driver.findElement(By.xpath("//button[contains(@class, 'btn-ghost')]//span[contains(text(), 'CSV')]")).click();

        if (statusString.equals("Unverified") || statusString.equals("Partially Verified") || statusString.equals("Outdated")){

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button//span[contains(text(), 'Download Anyway')]"))).click();

        }

        // try {
        //     Thread.sleep(4000);
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'btn-ghost-gray')]//span[contains(text(), 'Downloading..')]")));
        // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[contains(@class, 'btn-ghost-gray')]//span[contains(text(), 'Downloading..')]")));
        
    }
    


}
