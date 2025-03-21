package MainCode.JobsLaunchTests.AD_With_Intent;

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

public class AD_With_Intent_By_Name {

   // Upgrade plan to add more available jobs to account before executing the tests to overcome the job limit
  @BeforeAll
    public static void upgradePlanBefore() throws IOException, InterruptedException{
    UpgradePlan upgradePlanObject = new UpgradePlan();
    upgradePlanObject.upgradePlan();
}


    @Test
    void adWithIntentByName() throws InterruptedException, IOException {
         WebDriver driver = new ChromeDriver();

        //login to DS
        LoginPage loginPage = new LoginPage();
        loginPage.login(driver);


        //clicking on the "Intent" button
        SidebarMenu sidebarMenu = new SidebarMenu();
        sidebarMenu.intentButton(driver);

        // waiting until the spinner in the Lists table disappears
        // Spinners.spinnerListsTable(driver);

        Spinners.spinnerIntentPageFull(driver);


      //Clicking on the "+Discover With Intent" button
      ListsPages.clickToOpenWizard(driver);

      //waiting until the global loader in the AD with intent wizard disappears
      Spinners.spinnerGlobalCriteria(driver);

        
        //Changing the job name
        JobWizardPages wizard = new JobWizardPages(driver);
        wizard.changeJobName("AD with Intent ");

        //clicking on the "Verify Companies Only" boxed radiobox
        wizard.clickOnVerifyCompaniesOnly();

      

      //Disable Mock Data
      wizard.disableMockDataToggle();

       //selecting the "By Name" source
       wizard.selectByName();

       //selecting the first X Company Names from dropdown
       wizard.selectCompanyName(3);

        //Selecting the "1Password" in Intent Topics
        wizard.selectIntentTopic("1Password");

        //Selectint the "70" on the Intent Score range slider
        wizard.selectIntentScore("70");

        Thread.sleep(1000);

        //Clicking on the "Apply Filters" button
        wizard.clickOnApplyFilters();

       //Wait until the spinner in AD with intent preview disappears
       //Spinners.spinnerJobPreview(driver);


       //checking if the AD with intent preview table contains company name
       AssertionsJobWizard.assertName(driver);


        //checking if the number of estimated companies in preview is not "0"
        AssertionsJobWizard.assertPreviewCompanyCount(driver);

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
