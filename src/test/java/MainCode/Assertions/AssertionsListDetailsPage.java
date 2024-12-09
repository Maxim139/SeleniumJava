package MainCode.Assertions;


import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AssertionsListDetailsPage {
    
    WebDriver driver;
    WebDriverWait wait;

    
    public AssertionsListDetailsPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


 // checking if record name is displayed in the table on the Lists Details page
    public static void assertName(WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            //WebElement recordName = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".txt-btn-regular.txt-btn-s")));
            WebElement recordName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div//table//tbody//a[contains(@class, 'txt-btn-regular') and contains(@class, 'txt-btn-s')]")));

            if (recordName.isDisplayed()) {
            } else {
                fail("No record name in the table");
            }
        } catch (NoSuchElementException e) {
            fail("No record name in the table");
        }
    }


    public void assertIsCSVDownloaded(String namePattern, String filePath) throws IOException{



        File file = new File(filePath);
        File[] AllFiles = file.listFiles();

        Boolean fileExist = false;
        for (File fileObject: AllFiles){
            if(fileObject.getName().contains(namePattern)){
           //     Path path = Paths.get(fileObject.getPath());
            //    Files.delete(path);
                fileExist = true;
                System.out.println("File has been found!");
                break;
            }
            
        } 
        
        if(!fileExist){
        fail("CSV file has not been downloaded");
        }

    }


}
