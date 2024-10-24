package MainCode.JobsLaunchTests;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import MainCode.JobWizardPages;
import MainCode.ListsPages;
import MainCode.LoginPage;
import MainCode.SidebarMenu;
import MainCode.Spinners;
import MainCode.UpgradePlan;
import MainCode.VerificationSettingsPage;
import MainCode.Assertions.AssertionsJobWizard;

public class LDWithIntentLaunchFromCSV {

     // Upgrade plan to add more available jobs to account before executing the tests to overcome the job limit
  @BeforeAll
  public static void upgradePlanBefore() throws IOException, InterruptedException{
      UpgradePlan upgradePlanObject = new UpgradePlan();
      upgradePlanObject.upgradePlan();
  }

    @Test
    void ldWithIntentFromCSV() throws InterruptedException, IOException {
         WebDriver driver = new ChromeDriver();

        //login to DS
        LoginPage loginPage = new LoginPage();
        loginPage.login(driver);

        //clicking on the "Intent" button
        SidebarMenu sidebarMenu = new SidebarMenu();
        sidebarMenu.intentButton(driver);

        Spinners.spinnerListsTable(driver);

        //Clicking on the "+Discover With Intent" button
        ListsPages.clickToOpenWizard(driver);
        //driver.findElement(By.cssSelector("[lefticon='plus']")).click();

        //waiting until the global loader in the LD with intent wizard disappears
        Spinners.spinnerGlobalCriteria(driver);
 

        //Changing the job name
        JobWizardPages wizard = new JobWizardPages(driver);
        wizard.changeJobName("LD with Intent ");

     
        //Selecting "C-Level" Seniority
        wizard.selectSeniority("C Level");

        //selecting the "From CSV" option
        wizard.selectFromCSV();

        //uploading Companies CSV file
        wizard.uploadCSV("Company");

        //clicking on the "Save mapping" button on the Mapping pop-up
        wizard.clickOnSaveMapping();

        //Disable Mock Data
        wizard.disableMockDataToggle();

        //Selecting the "1Password" in Intent Topics
        wizard.selectIntentTopic("1Password");

        //Selecting the "50" on the Intent Score range slider
        wizard.selectIntentScore("70");

       Thread.sleep(500);

       //Clicking on the "Apply Filters" button
       wizard.clickOnApplyFilters();


       //Wait until the spinner in LD with intent preview disappears
       Spinners.spinnerJobPreview(driver);

       //checking if the LD with intent preview table contains contact name
       AssertionsJobWizard.assertName(driver);

        //checking if the number of companies/contacts in LD with intent preview is not "0"
        AssertionsJobWizard.assertPreviewCompanyCount(driver);
        AssertionsJobWizard.assertPreviewContactCount(driver);
   
        //wait while "Next Step" button is available
        wizard.clickNextStep();

        //clicking on the "Launch Job" button
        VerificationSettingsPage verificationSettingsPage = new VerificationSettingsPage(driver);
        verificationSettingsPage.clickLaunchJob();
        
        //checking the Success messages
        AssertionsJobWizard.assertDiscoverWithIntentJobLaunched(driver);

        //close Browser window
        driver.quit();
    }
}
