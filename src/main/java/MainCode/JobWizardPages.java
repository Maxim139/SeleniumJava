package MainCode;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class JobWizardPages {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverWait wait60;
    //private Properties properties;

public JobWizardPages(WebDriver driver){
    this.driver = driver;
    wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait60 = new WebDriverWait(driver, Duration.ofSeconds(60));
}


// changing the job name
    public void changeJobName(String jobName){
    
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    String dateTimeFormattedString = currentDateTime.format(formatter);
    driver.findElement(By.name("pencil-simple")).click();
    WebElement nameField = driver.findElement(By.xpath("//div[contains(@class, 'cdk-overlay-connected-position-bounding-box')]//div[contains(@class, 'mat-form-field-infix')]//input"));
    wait.until(ExpectedConditions.elementToBeClickable(nameField)).click();
    // nameField.click();
    nameField.clear();
    nameField.sendKeys(jobName + dateTimeFormattedString);
    driver.findElement(By.xpath(
        "//div[contains(@class, 'actions')]//app-button//button[contains(@class, 'btn-primary')]//span[text()=' Save ']"))
        .click();
    }


    // Selecting some value in HQ Location filter
public void selectHQLocation(String filterName) throws InterruptedException{
    // driver.findElement(By.xpath("//div[@class='header']//span[text()='HQ Location']")).click();
    driver.findElement(By.xpath("//div[@class='header']//span[text()='HQ Location']")).click();

    Actions actions = new Actions(driver);

    WebElement filterNameElement = wait60.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Company Location ']/../../..//div[text()=' " + filterName + " ']")));

    actions.moveToElement(filterNameElement).build().perform();
    
    WebElement includeButton = driver.findElement(By.xpath("//span[text()=' Company Location ']/../../..//div[text()=' " + filterName + " ']/.."));

    actions.moveToElement(includeButton).click().build().perform();

    //Thread.sleep(1000);


    //wait60.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Company Location ']/../../..//div[text()=' " + filterName + " ']"))).click();
}


// Selecting some value in Contact Location filter
public void selectContactLocation(String contLocationVlaue){

    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='header']//span[text()='Contact Location']"))).click();
        wait60.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//span[text()=' Contact Location ']/../../..//div[text()=' "+ contLocationVlaue +" ']")))
                .click();
}


// Disable Mock Data
public void disableMockDataToggle(){

WebElement toggleElement = driver.findElement(By.cssSelector(".mat-slide-toggle-input.cdk-visually-hidden"));
Boolean toggleState = toggleElement.isSelected();
System.out.println(toggleState);
if (toggleState) {
  driver.findElement(By.cssSelector(".mat-slide-toggle-thumb")).click();
}

}


// Selecting some value in the "Type" filter
public void selectType(String filterValue){

driver.findElement(By.xpath("//div[@class='header']//span[text()='Type']")).click();
wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
    "//span[text()=' Type ']/../../..//div[text()=' "+ filterValue +" ']")))
    .click();

}


// Clicking on the "Apply Filters" button
public void clickOnApplyFilters(){

    driver.findElement(By.xpath("//div//app-button[@type='button']//button[contains(@class,'btn-m btn-secondary-blue')]")).click();
}

//wait while "Next Step" button is available
public void clickNextStep() throws InterruptedException{

    int counter = 0;
    while (counter < 6) {
    try{
    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[righticon='arrow-right']"))).click();
    break;
    } catch (StaleElementReferenceException | ElementClickInterceptedException e){
        counter++;
        Thread.sleep(300);
    }
    }
}

//clicking on the "Verify Companies Only" boxed radiobox in Intent job Wizard
public void clickOnVerifyCompaniesOnly(){

    driver.findElement(By.xpath("//mat-radio-group[@role='radiogroup']//mat-radio-button//div[contains(text(),'Companies Only')]")).click();
}


//Selecting some value in the Intent Topics
public void selectIntentTopic(String filterValue){

driver.findElement(By.xpath("//div[@class='header']//span[text()='Intent']")).click();
//Thread.sleep(200);
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Intent Topics ']/../../..//div[text()=' "+ filterValue +" ']"))).click();

}


 // Selecting some value in the "Intent Score" filter
public void selectIntentScore(String filterValue) throws InterruptedException{

                String showMoreString = driver.findElement(By.cssSelector(".show-more-btn.ng-star-inserted")).getText();
                if (showMoreString.equals("Show Additional Settings")) {
                        driver.findElement(By.cssSelector(".show-more-btn.ng-star-inserted")).click();
                }

                Thread.sleep(500);

                WebElement sliderPointer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                                "//ngx-slider[contains(@class, 'ngx-slider ng-star-inserted animate with-legend')]//span[@ngxsliderhandle][contains(@aria-valuetext,'>')]")));
                WebElement sliderValue = driver.findElement(
                                By.xpath("//app-range-slider[@formcontrolname='intent_score']//span[text()='"+ filterValue +"']"));
                Actions actions = new Actions(driver);
                actions.clickAndHold(sliderPointer).moveToElement(sliderValue).release().perform();
}


//Selecting some value in "Seniority" accordion
public void selectSeniority(String filterValue){

        driver.findElement(By.xpath("//div[@class='header']//span[text()='Seniority']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()=' Seniority ']/../../..//div[text()=' "+ filterValue +" ']"))).click();
}


//selecting the "From Dealsignal List" option in AE/LE wizards
public void selectFromDealsignalList() throws InterruptedException{

    //clicking on the Three Dots button
wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='button-bar']//button[contains(@class, 'mat-menu-trigger') and contains(@class, 'custom-btn-bar') and contains(@class, 'custom-btn-bar-s') and contains(@class, 'icon-btn-bar')]"))).click();

Thread.sleep(500);

//clicking on the "From Dealsignal list" button
driver.findElement(By.cssSelector(".font-components-button-s.grayscale-c9")).click();        
}


//Searching for on some list from dropdown menu in Job Wizard
public void selectListFromDropdown(String listName) throws InterruptedException{

    // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-form-field[contains(@class, 'mat-primary ng-star-inserted size-l mat-form-field-type-mat-input mat-form-field-appearance-fill')]"))).click();
    
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//app-multi-tab-dropdown//mat-form-field[contains(@class, 'mat-primary')][contains(@class, 'ng-star-inserted')][contains(@class, 'size-l')][contains(@class, 'mat-form-field-type-mat-input')][contains(@class, 'mat-form-field-appearance-fill')]"))).click();


    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//app-button-bar//button//span[text()='All']"))).click();

    //Actions actions = new Actions(driver);



    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@class, 'mat-input-element mat-form-field-autofill-control mat-autocomplete-trigger')][contains(@class, 'ng-valid')][contains(@class, 'cdk-text-field-autofill-monitored ng-star-inserted ng-touched')]"))).sendKeys(listName);

    // Actions actions = new Actions(driver);

    // actions.moveToElement(inputElement).click().sendKeys(listName).build().perform();

    //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//mat-form-field[contains(@class, 'mat-primary ng-star-inserted size-l mat-form-field-type-mat-input mat-form-field-appearance-fill')]"))).sendKeys(listName);
    //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@class, 'mat-input-element mat-form-field-autofill-control mat-autocomplete-trigger ng-untouched ng-pristine')]"))).click(); 
    // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[contains(@class, 'mat-input-element mat-form-field-autofill-control mat-autocomplete-trigger ng-untouched ng-pristine')]"))).sendKeys(listName);
    
    Thread.sleep(300);



    //clicking on the list name from the dropdown menu
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'tab-list-container')]//span[text()='"+ listName +"']"))).click();
}


// clicking on the "Save ICP/Persona/Intent Criteria" button
public static void clickOnSaveCriteria(WebDriver driver){

WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    wait.until(ExpectedConditions.elementToBeClickable(
        By.xpath("//div[@class='wizard-right-section']//app-button//button//span[contains(text(), 'Save')]")))
        .click();
}


//Selecting the "From CSV" option in LD/AD job wizards
public void selectFromCSV() throws InterruptedException{

    driver.findElement(By.xpath("//div[@class='criteria-dropdown-container']//button")).click();

    Thread.sleep(500);

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//a//span[text() = 'From CSV']"))).click();


    // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//a//span[contains(text(),'From CSV')]"))).click();

}


    //Selecting the "From List" option in LD/AD job wizards
public void selectFromList() throws InterruptedException{

    driver.findElement(By.xpath("//div[@class='criteria-dropdown-container']//button")).click();

    Thread.sleep(500);

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//a//span[text() = 'From List']"))).click();


    // wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div//a//span[contains(text(),'From CSV')]"))).click();

}


// //uploading CSV file from OS file system
// public void uploadCSV(String csvType) throws IOException{

//     String csvPath = null;
//     properties = new Properties();
//     FileInputStream input = new FileInputStream("E:/VS Code Workspace/seleniumjava/src/config.properties");
//     properties.load(input);
//     input.close();

//     if (csvType.equals("Contact")){
//     csvPath = properties.getProperty("file.path.contact.csv");
//     }

//     if (csvType.equals("Company")){
//         csvPath = properties.getProperty("file.path.company.csv");
//         }

//     driver.findElement(By.cssSelector("[type='file']")).sendKeys(csvPath);

// }




//uploading CSV file from classpath
public void uploadCSV(String csvType) throws IOException{

     String csvPath = null;
    // properties = new Properties();
    // FileInputStream input = new FileInputStream("E:/VS Code Workspace/seleniumjava/src/config.properties");
    // properties.load(input);
    // input.close();

    if (csvType.equals("Contact")){
        csvPath = "3_contacts_for_ATests.csv";
   // csvPath = properties.getProperty("file.path.contact.csv");
    }

    if (csvType.equals("Company")){
        csvPath = "3_companies_for_ATests.csv";
        //csvPath = properties.getProperty("file.path.company.csv");
        }

        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(csvPath);

        File tempFile = File.createTempFile("csv_file_for_ATests", ".csv");
        tempFile.deleteOnExit();


        try(FileOutputStream outputStream = new FileOutputStream(tempFile)){

            byte[] buffer = new byte[1024];
            int byteRead;

            while ((byteRead = inputStream.read(buffer)) != -1){
                outputStream.write(buffer, 0, byteRead);
            }

        }

        String csvFilePath = tempFile.getAbsolutePath();


    //driver.findElement(By.cssSelector("[type='file']")).sendKeys(csvFilePath);

    if (csvType.equals("Contact")){
        driver.findElement(By.xpath("//app-file-uploader[@formcontrolname='contactCsv']//input[@type='file']")).sendKeys(csvFilePath);
        // driver.findElement(By.xpath("//app-file-uploader[formcontrolname='contactCsv']//input[type='file']")).sendKeys(csvFilePath);

    } else{
        driver.findElement(By.cssSelector("[type='file']")).sendKeys(csvFilePath);
    }

}




public void clickOnSaveMapping(){

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button//span[contains(text(), 'Save Mapping')]"))).click();

}

}
