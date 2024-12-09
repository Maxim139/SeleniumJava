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

public class LDLaunchTest {

  // Upgrade plan to add more available jobs to account before executing the tests
  // to overcome the job limit
  @BeforeAll
  public static void upgradePlanBefore() throws IOException, InterruptedException {
    UpgradePlan upgradePlanObject = new UpgradePlan();
    upgradePlanObject.upgradePlan();
  }

  @Test
  // @RepeatedTest()
  public void LDLaunch() throws InterruptedException {

    WebDriver driver = new ChromeDriver();

    // login to DS
    LoginPage loginPage = new LoginPage();
    loginPage.login(driver);

    // clicking on the "Discovery" sidebar menu button
    SidebarMenu sidebarMenu = new SidebarMenu();
    sidebarMenu.discoveryButton(driver);

    // waiting until the spinner in the Lists table disappears
    Spinners.spinnerListsTable(driver);

    // clicking on the "+Discover & Verify Contacts" button
    ListsPages.clickToOpenWizard(driver);

    // waiting until the global loader in the LD wizard disappears
    Spinners.spinnerGlobalCriteria(driver);

    // Changing the job name
    JobWizardPages wizard = new JobWizardPages(driver);
    wizard.changeJobName("LD ");

    // Selecting "Other" in "Seniority" accordion
    wizard.selectSeniority("Other");

    Thread.sleep(1000);

    // Selecting "Argentina" in HQ Location
    wizard.selectHQLocation("Argentina");

    // Wait until the spinner in LD preview disappears
    Spinners.spinnerJobPreview(driver);

    // Disable Mock Data
    wizard.disableMockDataToggle();

    // Selecting the "Public Company" type
    wizard.selectType("Public Company");

    // checking if the LD preview table contains contact name
    AssertionsJobWizard.assertName(driver);

    // checking if the number of contacts/companies in LD preview is not "0"
    AssertionsJobWizard.assertPreviewCompanyCount(driver);
    AssertionsJobWizard.assertPreviewContactCount(driver);

    // wait while "Next Step" button is available
    wizard.clickNextStep();

    // clicking on the "Launch Job" button
    VerificationSettingsPage verificationSettingsPage = new VerificationSettingsPage(driver);
    verificationSettingsPage.clickLaunchJob();

    // checking if the Success snackbar received after launching the job
    AssertionsJobWizard.assertDiscoveryJobLaunched(driver);

     driver.quit();
  }

}
