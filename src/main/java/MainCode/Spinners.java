package MainCode;

import java.time.Duration;
//import java.util.concurrent.TimeoutException;

import org.openqa.selenium.TimeoutException;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Spinners {
    
    // waiting until the spinner in the Lists table disappears
public static void spinnerListsTable(WebDriver driver){

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector( // changed Presence to Visibilityof
        ".loader.show-border")));
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(
        ".loader.show-border")));
        
}


//waiting until the global loader in the AD wizard disappears
public static void spinnerGlobalCriteria(WebDriver driver){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebDriverWait wait60 = new WebDriverWait(driver, Duration.ofSeconds(60));
try{
    // wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
    //     ".criteria-loader")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
            "//div[@class='criteria-loader']")));
    wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(
        ".criteria-loader")));
} catch(TimeoutException e){
    System.out.println("global criteria spinner hasn't displayed");
}
}


// Wait until the spinner in AD preview disappears
public static void spinnerJobPreview(WebDriver driver){

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
WebDriverWait wait60 = new WebDriverWait(driver, Duration.ofSeconds(60));
wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
    ".spinner-section.ng-star-inserted")));
wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(
    ".spinner-section.ng-star-inserted")));
}


//waiting until the spinner disappears from the AE wizard
public static void spinnerGlobalEnrichWizard(WebDriver driver){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    
    try{
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//loader//div[contains(@class, 'ng-star-inserted')]//mat-spinner")));
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//loader//div[contains(@class, 'ng-star-inserted')]//mat-spinner")));
    
    }catch(TimeoutException e){
        System.out.println("TimeoutException recieved");
    }
}


    public static void spinnerIntentPageFull(WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".loader")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader")));

    }

}
