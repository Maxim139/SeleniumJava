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

public class LE_By_Name {


     // Upgrade plan to add more available jobs to account before executing the tests to overcome the job limit
  @BeforeAll
    public static void upgradePlanBefore() throws IOException, InterruptedException{
      UpgradePlan upgradePlanObject = new UpgradePlan();
      upgradePlanObject.upgradePlan();
  }

    @Test
    void leByName() throws InterruptedException {
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

        //Selecting the "By Name" source
        wizard.selectByNameAELE();
       
        //Searching and selecting the provided contact
        wizard.selectContactName("Williams Katrina");
  
       //checking if the LE preview table contains contact name
       AssertionsJobWizard.assertName(driver);
   

        //checking if the number of contacts in LE preview is not "0"
        AssertionsJobWizard.assertPreviewContactCount(driver);

        //Changing the job name
        wizard.changeJobName("LE From List ");

        //wait while "Next Step" button is available
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
