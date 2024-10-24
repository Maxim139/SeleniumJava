package MainCode;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ICPPersonaPage {
    

    WebDriver driver;
    
    public ICPPersonaPage(WebDriver driver){

        this.driver = driver;

    }



    //  // clicking on the "Run Search" button
    // public static void clickRunSearch(WebDriver driver){
        
    //     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    //     WebElement divListElement;
    //     WebElement targetDiv;
    //     try {
    //        divListElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
    //                 "//persona-list/div[@class='persona-list']")));
    //         List<WebElement> list = divListElement.findElements(By.xpath(".//span[contains(text(), 'Run Search')]"));
    //         targetDiv = list.get(0);
    //         targetDiv.click();
    //     } catch (StaleElementReferenceException e) {
    //         divListElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
    //                 "//persona-list/div[@class='persona-list']")));
    //         List<WebElement> list = divListElement.findElements(By.xpath(".//span[contains(text(), 'Run Search')]"));
    //         targetDiv = list.get(0);
    //         targetDiv.click();
    //     }
    // }

    
     // clicking on the "Run Search" button
     public static void clickRunSearch(WebDriver driver, int elementPosition){  //TO DO: incorrect index is taken
        
        int index = 0;
        if (elementPosition != 0){
        index = --elementPosition;
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement divListElement;
        WebElement targetDiv;
        WebElement runSearchButton;
        Actions actions = new Actions(driver);
        List<WebElement> buttonsList;

        try {
            //find the parent element for all criteria panels
           divListElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//persona-list/div[@class='persona-list']")));

            //adding all criteria panels elements to list
            List<WebElement> list = divListElement.findElements(By.xpath(".//persona-list-item"));

            //check if only 1 criteria exist in list, if so, then clicking on the 1st panel
            if (list.size() >= 2){
            targetDiv = list.get(index); // giving errors
            actions.moveToElement(targetDiv).perform();
            } else {
                targetDiv = list.get(0); // giving errors
                actions.moveToElement(targetDiv).perform();
            }

            //wait until the "Run Search" button will be visible
            buttonsList = wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(targetDiv, By.xpath(".//span[contains(text(), 'Run Search')]")));

            if (!buttonsList.isEmpty()){
                runSearchButton = buttonsList.get(0);
                runSearchButton.click();
            } else{
                throw new RuntimeException("Run Search button is not found");
            }

        } catch (StaleElementReferenceException e) {
            divListElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                    "//persona-list/div[@class='persona-list']")));
            List<WebElement> list = divListElement.findElements(By.xpath(".//persona-list-item"));
            targetDiv = list.get(index); // giving errors
            actions.moveToElement(targetDiv).perform();
            buttonsList = wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(targetDiv, By.xpath(".//span[contains(text(), 'Run Search')]")));
            if (!buttonsList.isEmpty()){
                runSearchButton = buttonsList.get(0);
                runSearchButton.click();
            } else{
                throw new RuntimeException("Run Search button is not found");
            }
        }
    }


    //  // clicking on the "Run Search" button
    //  public static void clickRunSearch(WebDriver driver){
        
    //     // int index = 0;
    //     // if (elementPosition != 0){
    //     // index = --elementPosition;
    //     // }

    //     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    //     WebElement divListElement;
    //     WebElement targetDiv;
    //     WebElement runSearchButton;
    //     Actions actions = new Actions(driver);
    //     List<WebElement> buttonsList;

    //     try {
    //         //find the parent element for all criteria panels
    //        divListElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
    //                 "//persona-list/div[@class='persona-list']")));

    //         //adding all criteria panels elements to list
    //         List<WebElement> list = divListElement.findElements(By.xpath(".//persona-list-item"));
    //         targetDiv = list.get(index); // giving errors
    //         actions.moveToElement(targetDiv).perform();
            
    //         //wait until the "Run Search" button will be visible
    //         buttonsList = wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(targetDiv, By.xpath(".//span[contains(text(), 'Run Search')]")));

    //         if (!buttonsList.isEmpty()){
    //             runSearchButton = buttonsList.get(0);
    //             runSearchButton.click();
    //         } else{
    //             throw new RuntimeException("Run Search button is not found");
    //         }
    //     } catch (StaleElementReferenceException e) {
    //         divListElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
    //                 "//persona-list/div[@class='persona-list']")));
    //         List<WebElement> list = divListElement.findElements(By.xpath(".//persona-list-item"));
    //         targetDiv = list.get(index); // giving errors
    //         actions.moveToElement(targetDiv).perform();

    //         buttonsList = wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(targetDiv, By.xpath(".//span[contains(text(), 'Run Search')]")));

    //         if (!buttonsList.isEmpty()){
    //             runSearchButton = buttonsList.get(0);
    //             runSearchButton.click();
    //         } else{
    //             throw new RuntimeException("Run Search button is not found");
    //         }
    //     }
    // }



    //     try {
    //         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
    //                 "//persona-list/div[@class='persona-list']//div[@class='glass']//span[text()='Run Search']")))
    //                 .click();
    //     } catch (StaleElementReferenceException e) {
    //         wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
    //                 "//persona-list/div[@class='persona-list']//div[@class='glass']//span[text()='Run Search']")))
    //                 .click();
    //                 System.out.println("Hello from catch in clickRunSearch method");
    //     }
    // }


    // clicking on the "intent Criteria" tab
    public void clickOnCriteriaTab(String tabName) {

        driver.findElement(By.xpath("//span[text()=' "+ tabName +" ']")).click();

    }

}
