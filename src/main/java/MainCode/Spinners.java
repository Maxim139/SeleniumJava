package MainCode;

import java.time.Duration;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Spinners {
    
    // waiting until the spinner in the Lists table disappears
public static void spinnerListsTable(WebDriver driver){

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
        ".loader.show-border")));
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(
        ".loader.show-border")));
        
}


//waiting until the global loader in the AD wizard disappears
public static void spinnerGlobalCriteria(WebDriver driver){
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    WebDriverWait wait60 = new WebDriverWait(driver, Duration.ofSeconds(60));

    wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
        ".criteria-loader")));
    wait60.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(
        ".criteria-loader")));
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
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//loader//div[contains(@class, 'ng-star-inserted')]//mat-spinner")));
    wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//loader//div[contains(@class, 'ng-star-inserted')]//mat-spinner")));
}

}
