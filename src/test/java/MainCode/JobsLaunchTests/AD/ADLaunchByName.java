package MainCode.JobsLaunchTests.AD;

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

public class ADLaunchByName {

// Upgrade plan to add more available jobs to account before executing the tests to overcome the job limit
@BeforeAll
public static void upgradePlanBefore() throws IOException, InterruptedException{
    UpgradePlan upgradePlanObject = new UpgradePlan();
    upgradePlanObject.upgradePlan();
}

 // @Test
  //@RepeatedTest(10)
  void adLaunchByName() throws InterruptedException {
    WebDriver driver = new ChromeDriver();

    // login to DS
    LoginPage loginPage = new LoginPage();
    loginPage.login(driver);

    // clicking on the "Discovery" sidebar menu button
    SidebarMenu sidebarMenu = new SidebarMenu();
    sidebarMenu.discoveryButton(driver);

    // waiting until the spinner in the Lists table disappears
    Spinners.spinnerListsTable(driver);

    // clicking on the "Company lists" button
    ListsPages.switchToCompanyLists(driver);

    // waiting until the spinner in the Lists table disappears
    //Spinners.spinnerListsTable(driver);

    // clicking on the "+Discover & Verify Companies" button
    ListsPages.clickToOpenWizard(driver);

    //waiting until the global loader in the AD wizard disappears
    Spinners.spinnerGlobalCriteria(driver);

    // Changing the job name
    JobWizardPages wizard = new JobWizardPages(driver);
    wizard.changeJobName("AD ");

    //selecting the "By Name" source option
    wizard.selectByName();

    //selecting the first X Company Name filter values
    wizard.selectCompanyName(3);

    // Disable Mock Data
    wizard.disableMockDataToggle();

    // checking if the AD preview table contains company name
    AssertionsJobWizard.assertName(driver);

    // checking if the number of companies in AD preview is not "0"
    AssertionsJobWizard.assertPreviewCompanyCount(driver);

    // wait while "Next Step" button is available
    wizard.clickNextStep();

    // clicking on the "Launch Job" button
    VerificationSettingsPage verificationSettingsPage = new VerificationSettingsPage(driver);
    verificationSettingsPage.clickLaunchJob();

    // checking if the Success snackbar received
    AssertionsJobWizard.assertDiscoveryJobLaunched(driver);

     driver.quit();
  }
}
