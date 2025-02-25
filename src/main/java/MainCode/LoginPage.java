package MainCode;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    
    //pre-prod
    // String email="maksim@dealsignal.com";
    // String password="a815f4f6";
    // String url = "https://preprod-dealsignal.netlify.app/";

//     //pre-prod
//    String email="203fortest@test.com";
//     String password="suiTDeal100";
//    String url = "https://preprod-dealsignal.netlify.app/";

     //pre-prod
//    String email="fortestob66@test.com";
//    String password="suiTDeal100";
//    String url = "https://preprod-dealsignal.netlify.app/";

 //pre-prod
   String email="maksim@dealsignal.com";
   String password="a815f4f6";
   String url = "https://preprod-dealsignal.netlify.app/";


    // //staging
    // String email="maksim@dealsignal.com";
    // String password="suiTDealsignal2";
    // String url = "https://staging-dealsignal.netlify.app/";

    //staging
    // String email="fortest202@test.com";
    // String password="suiTDeal100";
    // String url = "https://staging-dealsignal.netlify.app/";

    public void login(WebDriver driver){
        driver.get(url);

        driver.manage().window().maximize();

        WebDriverWait wait120 = new WebDriverWait(driver, Duration.ofSeconds(120));

        // login to DS
        WebElement username = wait120.until(ExpectedConditions.presenceOfElementLocated(By.id("mat-input-0")));
        username.sendKeys(email);
        driver.findElement(By.id("mat-input-1")).sendKeys(password);
        driver.findElement(
                By.cssSelector(".btn-l.btn-primary")).click();
    }

}
