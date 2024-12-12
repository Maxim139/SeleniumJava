package MainCode.JobsLaunchTests.AE;


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

public class AEFromListLaunchTest {


     // Upgrade plan to add more available jobs to account before executing the tests to overcome the job limit
  @BeforeAll
  public static void upgradePlanBefore() throws IOException, InterruptedException{
      UpgradePlan upgradePlanObject = new UpgradePlan();
      upgradePlanObject.upgradePlan();
  }

    @Test
    // @RepeatedTest(10)
    void aeFromList() throws InterruptedException {
         WebDriver driver = new ChromeDriver();
         JobWizardPages wizard = new JobWizardPages(driver);

        //login to DS
        LoginPage loginPage = new LoginPage();
        loginPage.login(driver);


        //clicking on the "Enrich" button
        SidebarMenu sidebarMenu = new SidebarMenu();
        sidebarMenu.enrichButton(driver);

        //clicking on the "Company Lists" button
        ListsPages.switchToCompanyLists(driver);

        // waiting until the spinner in the Lists table disappears
        Spinners.spinnerListsTable(driver);
  

        //Clicking on the "+Enrich Companies" button
        ListsPages.clickToOpenWizard(driver);

        //slecting the "From Dealsignal List" option
        wizard.selectFromDealsignalList();
       

        //waiting until the spinner disappears from the AE wizard
        Spinners.spinnerGlobalEnrichWizard(driver);

        //Searching "31.03 Company List For Tests" list from dropdown menu
        wizard.selectListFromDropdown("Company List For Atests");


 
       //Wait until the spinner in AE wizard disappears
       Spinners.spinnerGlobalEnrichWizard(driver);

       //checking if the AE preview table contains company name
       AssertionsJobWizard.assertName(driver);

        //checking if the number of companies in AE preview is not "0"
        AssertionsJobWizard.assertPreviewCompanyCount(driver);
        
        //Changing the job name
        wizard.changeJobName("AE from List ");

       
        //wait while "Next Step" button is available
       wizard.clickNextStep();

        //clicking on the "Launch Job" button
        VerificationSettingsPage verificationSettingsPage = new VerificationSettingsPage(driver);
        verificationSettingsPage.clickLaunchJob();
        
        //checking the Success messages
        AssertionsJobWizard.assertEnrichmentJobLaunched(driver);

        //close Browser window
        driver.quit();
    }
}
