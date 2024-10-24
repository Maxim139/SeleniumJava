package MainCode.ICP_Persona_IntentCriteria_Tests;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import MainCode.ICPPersonaPage;
import MainCode.JobWizardPages;
import MainCode.ListsPages;
import MainCode.LoginPage;
import MainCode.SidebarMenu;
import MainCode.Spinners;
import MainCode.Assertions.AssertionsAdvancedSearch;
import MainCode.Assertions.AssertionsJobWizard;

public class ICPWizardTest {

    @Test
    //@RepeatedTest(5)
    public void ICPTest() throws InterruptedException {

        long startTime = System.currentTimeMillis();
        String hqLocationValue = "Canada";
        int panelToBeClicked = 2;

        WebDriver driver = new ChromeDriver();
        JobWizardPages wizard = new JobWizardPages(driver);
        ICPPersonaPage icpPersonaPage = new ICPPersonaPage(driver);
        AssertionsAdvancedSearch assertionsAdvancedSearch = new AssertionsAdvancedSearch(driver);
        LoginPage loginPage = new LoginPage();
        SidebarMenu sidebarMenu = new SidebarMenu();

        // login to DS
        loginPage.login(driver);

        // waiting until the "ICP & Personas" button will be available and clicking on
        // it
        sidebarMenu.icpPersonaButton(driver);

        // waiting until the spinner on the ICP/Persona page disappears
        Spinners.spinnerGlobalEnrichWizard(driver);

        // clicking on the "ICP" tab
        icpPersonaPage.clickOnCriteriaTab("ICP");

        // waiting until the spinner on the ICP/Persona page disappears
        Spinners.spinnerGlobalEnrichWizard(driver);

        // clicking on the "+Add ICP/Persona" button
        ListsPages.clickToOpenWizard(driver);

        // waiting until the spinner in the ICP wizard disappears
        Spinners.spinnerGlobalCriteria(driver);

        // Selecting the "Canada" in the HQ Location
        wizard.selectHQLocation(hqLocationValue);

        // waiting until the spinner in the ICP preview disappears
        Spinners.spinnerJobPreview(driver);

        // checking if the ICP preview table contains company name: it is displayed
        // and not blank
        AssertionsJobWizard.assertName(driver);

        // checking if the number of companies in ICP preview is not "0" and not blank
        AssertionsJobWizard.assertPreviewCompanyCount(driver);

        // clicking on the "Save ICP" button
        JobWizardPages.clickOnSaveCriteria(driver);

        // waiting until the spinner disappears after creating the criteria
        Spinners.spinnerGlobalEnrichWizard(driver);

        // checking if the Success message received
        AssertionsJobWizard.assertCriteriaCreated(driver);

        Thread.sleep(1000);

        // clicking on the "Run Search" button
        ICPPersonaPage.clickRunSearch(driver, panelToBeClicked);

        // checking if the HQ Location filter is applied to Advanced Search
        assertionsAdvancedSearch.assertTextOnBlueChip(hqLocationValue);

        // checking if Companies Advanced Search is opened
        assertionsAdvancedSearch.assertAdvSearchTabSelected("Companies");

        // displaying the test duration time
        long endTime = System.currentTimeMillis();
        double duration = (endTime - startTime) / 1000.0;
        System.out.println("Test duration = " + duration);

        driver.quit();

    }
}
