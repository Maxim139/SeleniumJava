package MainCode;


import java.time.Duration;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SidebarMenu {

// clicking on the "Discovery" sidebar menu button
    public void discoveryButton(WebDriver driver){

       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

       wait.until(ExpectedConditions.presenceOfElementLocated(By.name("verified-list"))).click();
    }

    // clicking on the "Enrich" sidebar menu button
    public void enrichButton(WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
 
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("stack-simple"))).click();
     }

     // clicking on the "intent" sidebar menu button
    public void intentButton(WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
 
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("lightning"))).click();
     }

     // clicking on the "Search" sidebar menu button
    public void searchButton(WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
 
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("magnifying-glass"))).click();
     }

     // clicking on the "ICP&Persona" sidebar menu button
    public void icpPersonaButton(WebDriver driver){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
 
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("users-three"))).click();
     }


   
}
