package MainCode;

import java.time.Duration;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AdvancedSearchPage {

    WebDriver driver;
    WebDriverWait wait;
    WebDriverWait wait60;


    public AdvancedSearchPage(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait60 = new WebDriverWait(driver, Duration.ofSeconds(60));
    }
    
// waiting until the "Lists" button available and clicking on it
public static void clickListsButton(WebDriver driver){
      
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

   wait.until(ExpectedConditions.presenceOfElementLocated(
    By.xpath("//div[@class='mat-tab-links']//div//span[contains(text(),'Lists')]"))).click();
   }


   // Selecting the Companies tab on Advanced Search page
   public static void switchToCompaniesTab(WebDriver driver){

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                "//div[contains(@class, 'button-bar-container switch-button-bar-container')]//button//span[contains(text(),'Companies')]")))
                .click();

   }


   // waiting until Coresignal loader disappears
   public void tp_criteria_search_loader(){
    
   wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".pdl-loader.ng-star-inserted")));
   }


//clicking on the second pagination page
   public void selectPaginationPage(String pageNumber){

    wait.until(ExpectedConditions.presenceOfElementLocated(
        By.xpath("//button[@class='btn-ghost-gray btn-s']//span[text()=' "+ pageNumber +" ']")))
        .click();
   }

   //returning the contact/company name from the Advanced Search table
   public String takeRecordName(){

    String recordName = driver.findElement(By.xpath(
                "//table//tbody/tr//div[@class='name-column']//span"))
                .getText();
        return recordName;
   }


   // selecting the '100 Records per page'
   public void selectRecordsPerPage(String recordsPerPage){

    wait.until(ExpectedConditions.presenceOfElementLocated(
        By.xpath("//app-paginator[@id='tablePaginator']//button[@class='btn-s btn-secondary-white']"))).click();
driver.findElement(By.xpath("//div[@class='cdk-overlay-pane']//span[text()='"+ recordsPerPage +"']")).click();

   }

}
