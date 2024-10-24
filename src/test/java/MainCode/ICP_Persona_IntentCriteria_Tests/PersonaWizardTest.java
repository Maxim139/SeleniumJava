package MainCode.ICP_Persona_IntentCriteria_Tests;

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

public class PersonaWizardTest {

    @Test
    public void personaTest() throws InterruptedException {

        long startTime = System.currentTimeMillis();

        String seniorityValue = "C Level";
        String hqLocationValue = "Canada";
        int panelToBeClicked = 2;

        WebDriver driver = new ChromeDriver();
        JobWizardPages wizard = new JobWizardPages(driver);
        AssertionsAdvancedSearch assertionsAdvancedSearch = new AssertionsAdvancedSearch(driver);
        SidebarMenu sidebarMenu = new SidebarMenu();
        LoginPage loginPage = new LoginPage();

        // login to DS
        loginPage.login(driver);

        // waiting until the "ICP & Personas" button will be available and clicking on
        // it
        sidebarMenu.icpPersonaButton(driver);
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.name("users-three"))).click();

        // waiting until the spinner on the ICP/Persona page disappears
        Spinners.spinnerGlobalEnrichWizard(driver);

        ListsPages.clickToOpenWizard(driver);
        // driver.findElement(By.cssSelector("[lefticon='plus']")).click();

        // waiting until the spinner in the Persona wizard disappears
        Spinners.spinnerGlobalCriteria(driver);

        // Selecting the "C-Level" in the Seniority
        wizard.selectSeniority(seniorityValue);

        // Selecting the "Canada" in the HQ Location
        wizard.selectHQLocation(hqLocationValue);

        // waiting until the spinner in the Persona preview disappears
        Spinners.spinnerJobPreview(driver);

        // checking if the Persona preview table contains contact name: it is displayed
        // and not blank
        AssertionsJobWizard.assertName(driver);

        // checking if the number of companies/contacts in Persona preview is not "0"
        // and
        // checking if numbers from tp_criteria_search are received
        AssertionsJobWizard.assertPreviewCompanyCount(driver);
        AssertionsJobWizard.assertPreviewContactCount(driver);

        // clicking on the "Save Persona" button
        JobWizardPages.clickOnSaveCriteria(driver);

        // waiting until the spinner after launching the job disappears
        Spinners.spinnerGlobalEnrichWizard(driver);

        // checking if the Success message received
        AssertionsJobWizard.assertCriteriaCreated(driver);

        Thread.sleep(1000);

        // clicking on the "Run Search" button
        ICPPersonaPage.clickRunSearch(driver, panelToBeClicked); 

        // checking if the Seniority filter is applied to Advanced Search
        assertionsAdvancedSearch.assertTextOnBlueChip(seniorityValue);

        // checking if the HQ Location filter is applied to Advanced Search (processing
        // the "StaleElementRefException" in cycle)
        assertionsAdvancedSearch.assertTextOnBlueChip(hqLocationValue);

        // displaying the test duration time
        long endTime = System.currentTimeMillis();
        double duration = (endTime - startTime) / 1000.0;
        System.out.println("Test duration = " + duration);

        driver.quit();

    }
}
