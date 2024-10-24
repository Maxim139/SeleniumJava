package MainCode.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import MainCode.AdvancedSearchPage;
import MainCode.DevToolsNetworkListener;
import MainCode.Spinners;

public class AssertionsAdvancedSearch {
    
    WebDriver driver;
    WebDriverWait wait;
    WebDriverWait wait60;

    public AssertionsAdvancedSearch(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait60 = new WebDriverWait(driver, Duration.ofSeconds(60));
    }


             // checking if the HQ Location filter is applied to Advanced Search (processing the "StaleElementRefException" in cycle)
        public void assertTextOnBlueChip(String chipValue){

                WebElement ChipElement = null;
                try{
                ChipElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'chip chip-s primary-c3-chip square-chip with-right-icon has-label ellipsis')]//span[text()=' "+ chipValue +" ']")));
                } catch(TimeoutException e){
                        assertTrue(false, "Filters are not applied to Advanced Search");
                }

               int repeat = 0;
               while(true){
               try {
                        @SuppressWarnings("null")
                        String chipActualValueString = ChipElement.getText(); 
                        assertEquals(chipValue, chipActualValueString,
                                        "Filters are not applied to Advanced Search");
                                        break;
                }  catch(StaleElementReferenceException e){
                        ChipElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'chip chip-s primary-c3-chip square-chip with-right-icon has-label ellipsis')]//span[text()=' "+ chipValue +" ']")));
                }
                repeat++;
        }
        System.out.println(repeat);
    }


    // checking if Contacts Advanced Search is opened
    public void assertAdvSearchTabSelected(String expectedTabName){
    
        String selectedTabName = driver.findElement(By.xpath(
            "//div[contains(@class,'switch-button-bar-container')]//button[contains(@class, 'selected')]//span"))
            .getText();
        assertEquals(expectedTabName, selectedTabName);
    }


// checking if intent Score is applied to Adv Search
    public void assertIntentScore(String expectedScoreValue) throws InterruptedException{
                
                Thread.sleep(300);
                driver.findElement(By.xpath("//div[@class='header']//span[text()='Intent']")).click();
                Thread.sleep(500);
                driver.findElement(By.cssSelector(".show-more-btn.ng-star-inserted")).click();
                Thread.sleep(300);
                try {
                        String sliderPointerAdvSearch = wait.until(ExpectedConditions.visibilityOfElementLocated(By
                                        .xpath("//ngx-slider[contains(@class, 'ngx-slider ng-star-inserted animate with-legend')]//span[@ngxsliderhandle][contains(@aria-valuetext,'>"+ expectedScoreValue +"')]")))
                                        .getAttribute("aria-valuetext");
                        assertEquals(">"+ expectedScoreValue, sliderPointerAdvSearch, "intent Score is not applied");
                } catch (TimeoutException elementException) {
                        fail("intent Score is not applied");
                }

    }



    // checking if Advanced Search table contains contact name ! Rechecked
    public void assertName(){

        try {
            driver.findElement(By.cssSelector(
                    ".txt-btn-regular.txt-btn-s"))
                    .getAttribute("class");
        } catch (NoSuchElementException e) {
            fail("No record name in the table");
        }

    }



    
// checking if number from Coresignal is displayed
    public void assert_tp_criteria_search(){
    
        // try {
        //    WebElement tp_criteri_searchElement = wait.until(ExpectedConditions
        //             .visibilityOfElementLocated(By.cssSelector(".font-base-copy-bold-s.ng-star-inserted")));
        //             assertTrue(tp_criteri_searchElement.isDisplayed(), "No data from 'tp_criteria_search' API");
        // } catch (TimeoutException e) {
        //     assertTrue(false, "No data from 'tp_criteria_search' API");
        // }

        int apiStatus = DevToolsNetworkListener.getAPIStatus(driver);

        System.out.println("Status tp_criteria_search API: " + apiStatus);

        if(apiStatus != 200){
            fail("tp_criteria_search failed, Status code: "+apiStatus);
        }

    }


// checking if "Nothing Found" is displayed in the Contacts Advanced Search
        // table
    public void assertNothingFoundNotDisplayed(){
       
        WebElement nothingFound = null;
        WebElement moreContacts = null;
        try {
            nothingFound = driver.findElement(By.cssSelector(".empty-result.ng-star-inserted"));
            if (nothingFound.isDisplayed()) {
                fail("Nothing Found in DS database");
            }
            
            moreContacts = driver.findElement(By.cssSelector(".empty-message"));
            if (moreContacts.isDisplayed()) {
                fail("Nothing Found in DS database");
            }

        } catch (NoSuchElementException e) {
        }

    }


     // checking if not the same contacts are displayed on every pagination page
    public void assertIfSameNameOnPages(){

        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage(driver);

        //taking the contact/company name
        String NameFirstPageString = advancedSearchPage.takeRecordName();
        // driver.findElement(By.xpath(
        //         "//table//tbody/tr//div[@class='name-column']//span"))
        //         .getText();

        System.out.println(NameFirstPageString);

        //clicking on the second pagination page
        advancedSearchPage.selectPaginationPage("2");
        // wait.until(ExpectedConditions.presenceOfElementLocated(
        //         By.xpath("//button[@class='btn-ghost-gray btn-s']//span[text()=' 2 ']")))
        //         .click();

        Spinners.spinnerJobPreview(driver);
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".spinner-section.ng-star-inserted")));
        // wait60.until(
        //         ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".spinner-section.ng-star-inserted")));

        String NameSecondPageString = advancedSearchPage.takeRecordName();
        // String contactNameSecondPageString = driver.findElement(By.xpath(
        //         "//table//tbody/tr//div[@class='name-column']//span"))
        //         .getText();

        System.out.println(NameSecondPageString);

        // checking if the first company name on the 2nd pagination page exists
        if (NameSecondPageString.isBlank()) {
            fail("Pagination pages are not working");
        }

        assertNotEquals(NameFirstPageString, NameSecondPageString);
    }


// checking the "Records per page" functionality
public void assertRecordsPerPage(){

wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
    "//div[@class='select-header']//input")))
    .click();

String selectedContactsString = wait
    .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class, 'header-row')]//div//span")))
    .getText();

    if(selectedContactsString.contains("95") || selectedContactsString.contains("96") || selectedContactsString.contains("97") || selectedContactsString.contains("98") || selectedContactsString.contains("99") || selectedContactsString.contains("100"))
    {
        assertTrue(selectedContactsString.contains("selected"), "The 'Records per page' is not working");
    } else {
        fail("Records per Page is not working correctly");
    }

   // assertTrue(selectedContactsString.contains(expectedRecordsPerPage), "The 'Records per page' is not working");
   // assertTrue(selectedContactsString.contains("selected"), "The 'Records per page' is not working");

//assertEquals(recordsPerPage + " Contacts selected", selectedContactsString, "The 'Records per page' is not working");
}
}
