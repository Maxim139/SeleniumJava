package MainCode;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        
      //JUnitTests test = new JUnitTests();

        WebDriver driver = new ChromeDriver();

        driver.get("https://preprod-dealsignal.netlify.app/");

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        WebElement username = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("mat-input-0")));

        username.sendKeys("maksim@dealsignal.com");
        driver.findElement(By.id("mat-input-1")).sendKeys("a815f4f6");
        driver.findElement(By.xpath("/html/body/app-root/app-login/div/div[1]/div/div[2]/form/div[3]/app-button/button")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("verified-list"))).click();

        driver.findElement(By.xpath("/html/body/app-root/app-layout/div/div[2]/div/app-verification-discovery/div/div/lists-pages/div/div[1]/app-button-bar/div/button[2]")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/app-layout/div/div[2]/div/app-verification-discovery/div/div/lists-pages/div/div[2]/div[2]/list-results/div/div[1]/app-table/div/loader/div/div")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/app-root/app-layout/div/div[2]/div/app-verification-discovery/div/div/lists-pages/div/div[2]/div[2]/list-results/div/div[1]/app-table/div/loader/div/div")));


        driver.findElement(By.xpath("/html/body/app-root/app-layout/div/div[2]/div/app-verification-discovery/div/div/lists-pages/div/div[2]/div[2]/list-results/div/div[1]/app-table/div/div/div[1]/div/div[2]/app-button/button")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"mat-dialog-0\"]/app-discovery-modal/div/mat-dialog-content/app-discovery-criteria/div[2]/div")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"mat-dialog-0\"]/app-discovery-modal/div/mat-dialog-content/app-discovery-criteria/div[2]/div")));

        //Selecting "Argentina" in HQ Location
        driver.findElement(By.id("mat-expansion-panel-header-8")).click();

        Thread.sleep(200);
       
        driver.findElement(By.xpath("//*[@id='cdk-accordion-child-8']/div/div/div/app-location-criteria/multi-selector-dropdown-inline/div/div[2]/div[1]")).click();

      //Disable Mock Data
        WebElement toggleElement = driver.findElement(By.id("mat-slide-toggle-11-input"));


        Boolean toggleState = toggleElement.isSelected();

        System.out.println(toggleState);

        if (toggleState){
            driver.findElement(By.xpath("//*[@id='mat-slide-toggle-11']/label/span[1]/span/span[1]")).click();
        }

        //Selecting the "Education" type
       driver.findElement(By.id("mat-expansion-panel-header-10")).click();

       Thread.sleep(200);
       
       driver.findElement(By.xpath("//*[@id='cdk-accordion-child-10']/div/div/div/multi-dropdown-inline/div/div[2]/mat-selection-list/mat-list-option[1]/div/div[2]")).click();

       //Wait until the spinner in AD preview disappears
       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='mat-dialog-0']/app-discovery-modal/div/mat-dialog-content/app-discovery-criteria/div/div[2]/div[2]")));
       wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id='mat-dialog-0']/app-discovery-modal/div/mat-dialog-content/app-discovery-criteria/div/div[2]/div[2]")));

        //wait while "Next Step" button is available
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='mat-dialog-title-0']/div/div[3]/app-button[2]/button"))).click();

        //clicking on the "Launch Job" button
      driver.findElement(By.xpath("//*[@id='mat-dialog-title-0']/div/div[3]/app-button[3]/button/span")).click();
        
      // String snackText = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".font-base-copy-body-s.description.ng-star-inserted"))).getText();


    // test.adJobLaunch(snackText);

   //driver.quit();
    }



}