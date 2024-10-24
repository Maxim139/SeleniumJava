package MainCode.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class AssertionsJobWizard {
    
// checking if the preview table contains the contact/company name: it is displayed
        // and not blank
public static void assertName(WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
                WebElement NameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".font-base-copy-bold-s.grayscale-c10.text-capitalize.one-liner-text")));
                    String Name = NameElement.getText();
           // String Name = driver
             //       .findElement(By.cssSelector(".font-base-copy-bold-s.grayscale-c10.text-capitalize.one-liner-text"))
                 //   .getText();
            assertFalse(Name.isBlank(), "No name in the table (blank)"); // name is not blank
          //  WebElement NameElement = driver
         //           .findElement(By.cssSelector(".font-base-copy-bold-s.grayscale-c10.text-capitalize.one-liner-text"));
            assertTrue(NameElement.isDisplayed(), "No name in the table (not displayed)"); // contact
                                                                                                          // name is
                                                                                                          // displayed
        } catch (NoSuchElementException | TimeoutException e) {
            fail("No name in the table");
        }

}
    

// checking if the CSV preview table contains some data (not empty)
        public static void assertNameInTableFromCSV(WebDriver driver){

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
                try {
                        WebElement NameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".font-base-copy-body-s.grayscale-c10.text-capitalize.one-liner-text")));
                            String Name = NameElement.getText();
                   // String Name = driver
                     //       .findElement(By.cssSelector(".font-base-copy-bold-s.grayscale-c10.text-capitalize.one-liner-text"))
                         //   .getText();
                    assertFalse(Name.isBlank(), "No data in the table (blank)"); // name is not blank
                  //  WebElement NameElement = driver
                 //           .findElement(By.cssSelector(".font-base-copy-bold-s.grayscale-c10.text-capitalize.one-liner-text"));
                    assertTrue(NameElement.isDisplayed(), "No data in the table (not displayed)"); // contact
                                                                                                                  // name is
                                                                                                                  // displayed
                } catch (NoSuchElementException | TimeoutException e) {
                    fail("No data in the table");
                }
        
        }


// checking if the number of companies in Intent Criteria preview is
                // not "0" and not blank
                public static void assertPreviewCompanyCount(WebDriver driver){

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                try {
                    String companyNumber = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                                    "//div[contains(@class, 'metric-section')]//app-metric//div//span[text()=' Estimated companies ']/preceding-sibling::span[@class='font-base-header-4 black value']")))
                                    .getText();
                    assertFalse(companyNumber.isBlank(), "No number of companies");
                    assertNotEquals("0", companyNumber, "The number of companies in preview is 0");
            } catch (TimeoutException e) {
                    fail("No number of companies in preview");
            }

}


// checking if the number of rows in CSV preview is
                // not "0" and not blank
                public static void assertPreviewCSVRowsCount(WebDriver driver){

                        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
                        try {
                            String rowsNumberString = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                                            "//div[contains(@class, 'metric-section')]//app-metric//div//span[text()=' Rows Total ']/preceding-sibling::span[@class='font-base-header-4 black value']")))
                                            .getText();
                            assertFalse(rowsNumberString.isBlank(), "No number of Rows");
                            assertNotEquals("0", rowsNumberString, "The number of Rows in preview is 0");
                    } catch (TimeoutException e) {
                            fail("No number of Rows in preview");
                    }
        
        }

// checking if the number of contacts in Intent Criteria preview is
                // not "0" and not blank
public static void assertPreviewContactCount(WebDriver driver){

                try {
                    String contactNumber = driver.findElement(By.xpath(
                                    "//div[contains(@class, 'metric-section')]//app-metric//div//span[text()=' Estimated contacts ']/preceding-sibling::span[@class='font-base-header-4 black value']"))
                                    .getText();
                    assertFalse(contactNumber.isBlank(), "No number of contacts");
                    assertNotEquals("0", contactNumber, "The number of contacts in preview is 0");
            } catch (NoSuchElementException e) {
                    fail("No number of contacts in preview");
            }

}


// checking if the Success snackbar received
public static void assertDiscoveryJobLaunched(WebDriver driver){

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    String snackText = wait.until(ExpectedConditions
    .presenceOfElementLocated(By.cssSelector(".font-base-copy-body-s.description.ng-star-inserted"))).getText();
assertEquals("Discovery job is launched successfully!", snackText, "Checking successful snackbar");
}


// checking if the Success snackbar received after launching Discovery from CSV
public static void assertDiscoveryJobFromCSVLaunched(WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        
        String snackText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'description')][contains(text(), 'File uploaded successfully!!')]"))).getText();
        assertEquals("File uploaded successfully!!", snackText, "Checking 'Intent Criteria Saved' snackbar");
       // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".mat-progress-spinner.mat-spinner.mat-primary.mat-progress-spinner-indeterminate-animation")));
       // Thread.sleep(1000);
        String snackText2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'description')][contains(text(), 'Discovery job is launched successfully!')]"))).getText();
        assertEquals("Discovery job is launched successfully!", snackText2, "Checking 'Intent Job Launched Successfully' snackbar");
        }


//checking the Success messages
public static void assertDiscoverWithIntentJobLaunched(WebDriver driver) throws InterruptedException{

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(35));
        
        String snackText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'description')][contains(text(), 'Intent criteria saved successfully!')]"))).getText();
        assertEquals("Intent criteria saved successfully!", snackText, "Checking 'Intent Criteria Saved' snackbar");
       // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".mat-progress-spinner.mat-spinner.mat-primary.mat-progress-spinner-indeterminate-animation")));
       // Thread.sleep(1000);
        String snackText2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'description')][contains(text(), 'Intent job is launched successfully!')]"))).getText();
        assertEquals("Intent job is launched successfully!", snackText2, "Checking 'Intent Job Launched Successfully' snackbar");
}


//checking the Success messages
public static void assertEnrichmentJobLaunched(WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String snackText = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".font-base-copy-body-s.description.ng-star-inserted"))).getText();
        assertEquals("Enrichment launched successfully!", snackText, "Checking 'Success' snackbar");
}


 // checking if the Success message received after creating ICP/Persona/Intent
public static void assertCriteriaCreated(WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        String snackbarString = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector(".font-base-copy-body-s.description.ng-star-inserted")))
                .getText();
                assertTrue(snackbarString.contains("created successfully"), "Failed to create Criteria");
        //assertEquals("created successfully", snackbarString, "Failed to create ICP");
}

}
