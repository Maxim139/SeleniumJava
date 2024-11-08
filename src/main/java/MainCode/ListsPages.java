package MainCode;


import java.time.Duration;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ListsPages {

WebDriver driver;

public ListsPages(WebDriver driver){
    this.driver = driver;
}

    
public static void switchToCompanyLists(WebDriver driver) throws InterruptedException{
    // clicking on the "Company lists" button

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

   // Thread.sleep(2500);

    // wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".loader.show-border")));
    // wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".loader.show-border")));

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
        "//div[contains(@class, 'button-bar-container list-btn-bar switch-button-bar-container')]//button//span[contains(text(),'Company Lists')]")))
        .click();

    // driver.findElement(By.xpath(
    //     "//div[contains(@class, 'button-bar-container list-btn-bar switch-button-bar-container')]//button//span[contains(text(),'Company Lists')]"))
    //     .click();
}

    // clicking on the "+Discover/Enrich" button on lists pages
public static void clickToOpenWizard(WebDriver driver) throws InterruptedException{

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[lefticon='plus']"))).click();

    //Thread.sleep(600);

    // driver.findElement(By.cssSelector("[lefticon='plus']")).click();
}

    

// waiting until the search input field is available and entering the search
        // phrase
        public void enterSearchPhrase(String listName){

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(
                ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@placeholder='Search lists by name']")))
                .sendKeys(listName);
        }

// waiting until the expected list is available and clicking on it
public static void clickOnListName(WebDriver driver){

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
                ".txt-btn-regular.txt-btn-s")))
                .click();
}



}
