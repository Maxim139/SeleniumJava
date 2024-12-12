package MainCode.JobsLaunchTests.LE;

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
import MainCode.VerificationSettingsPage;
import MainCode.Assertions.AssertionsJobWizard;
import MainCode.Utilities.UpgradePlan;

public class LEFromCSVLaunchTest {


     // Upgrade plan to add more available jobs to account before executing the tests to overcome the job limit
  @BeforeAll
    public static void upgradePlanBefore() throws IOException, InterruptedException{
      UpgradePlan upgradePlanObject = new UpgradePlan();
      upgradePlanObject.upgradePlan();
  }

    @Test
    void leFromCSV() throws InterruptedException, IOException {
         WebDriver driver = new ChromeDriver();
         JobWizardPages wizard = new JobWizardPages(driver);

        //login to DS
        LoginPage loginPage = new LoginPage();
        loginPage.login(driver);

        //clicking on the "Enrich" button
        SidebarMenu sidebarMenu = new SidebarMenu();
        sidebarMenu.enrichButton(driver);

      // waiting until the spinner in the Lists table disappears
      Spinners.spinnerListsTable(driver);

        //Clicking on the "+Enrich Contacts" button
        ListsPages.clickToOpenWizard(driver);

        
      //uploading CSV file
       wizard.uploadCSV("Contact");
   
       
       //checking if the LE preview table contains contact name
     //  AssertionsJobWizard.assertName(driver);
   

        //checking if the number of contacts in LE preview is not "0"
        AssertionsJobWizard.assertPreviewCSVRowsCount(driver);

        //Changing the job name
        wizard.changeJobName("LE From List ");

        //wait while "Next Step" button is available
        wizard.clickNextStep();

        Thread.sleep(300);

       //clicking on "Next Step" button on Mapping page
       wizard.clickNextStep();

        //clicking on the "Launch Job" button
        VerificationSettingsPage verificationSettingsPage = new VerificationSettingsPage(driver);
        verificationSettingsPage.clickLaunchJob();

         //checking the Success message
         AssertionsJobWizard.assertEnrichmentJobLaunched(driver);

        //close Browser window
        driver.quit();
    }
}
