package MainCode.ICP_Persona_IntentCriteria_Tests;


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

public class IntentCriteriaTest {

       // @Test
        public void IntentCriteriaWizardTest() throws InterruptedException {

                long startTime = System.currentTimeMillis();
                String seniorityString = "C Level";
                String hqLocationString = "Canada";
                String intentTopicValue = "1Password";
                String intentScoreValue = "70";
                int panelToBeClicked = 1;

                WebDriver driver = new ChromeDriver();
                ICPPersonaPage icpPersonaPage = new ICPPersonaPage(driver);
                JobWizardPages wizard = new JobWizardPages(driver);
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

                // clicking on the "intent Criteria" tab
                icpPersonaPage.clickOnCriteriaTab("Intent Criteria");

                // waiting until the spinner on the ICP/Persona page disappears
                Spinners.spinnerGlobalEnrichWizard(driver);

                // clicking on the "+Add ICP/Persona" button
                ListsPages.clickToOpenWizard(driver);

                // waiting until the spinner in the Intent Criteria wizard disappears
                Spinners.spinnerGlobalCriteria(driver);

                // Selecting the "C-Level" in the Seniority
                wizard.selectSeniority(seniorityString);

                // Selecting the "Canada" in the HQ Location
                wizard.selectHQLocation(hqLocationString);

                // Selecting the "1Password" in Intent Topics
                wizard.selectIntentTopic(intentTopicValue);

                // Selecting the "70" on the Intent Score range slider
                wizard.selectIntentScore(intentScoreValue);

                Thread.sleep(500);

                // Clicking on the "Apply Filters" button
                wizard.clickOnApplyFilters();

                // waiting until the spinner in the Intent Criteria preview disappears
                Spinners.spinnerJobPreview(driver);

                // checking if the Intent Criteria preview table contains contact name: it is
                // displayed
                // and not blank
                AssertionsJobWizard.assertName(driver);

                // checking if the number of companies/contacts in Intent Criteria preview is
                // not "0" and not blank
                AssertionsJobWizard.assertPreviewCompanyCount(driver);
                AssertionsJobWizard.assertPreviewContactCount(driver);

                // clicking on the "Save Intent Criteria" button
                JobWizardPages.clickOnSaveCriteria(driver);

                // waiting until the spinner disappears after creating the criteria
                Spinners.spinnerGlobalEnrichWizard(driver);

                // checking if the Success message received
                AssertionsJobWizard.assertCriteriaCreated(driver);

                Thread.sleep(1000);

                // clicking on the "Run Search" button
                ICPPersonaPage.clickRunSearch(driver, panelToBeClicked);

                // checking if the HQ Location filter is applied to Advanced Search (processing
                // the "StaleElementRefException" in cycle)
                assertionsAdvancedSearch.assertTextOnBlueChip(hqLocationString);

                // checking if the Seniority filter is applied to Advanced Search
                assertionsAdvancedSearch.assertTextOnBlueChip(seniorityString);

                // checking if Contacts Advanced Search is opened
                assertionsAdvancedSearch.assertAdvSearchTabSelected("Contacts");

                // checking if Intent Topics are applied to Adv Search
                assertionsAdvancedSearch.assertTextOnBlueChip(intentTopicValue);

                // checking if intent Score is applied to Adv Search
                assertionsAdvancedSearch.assertIntentScore(intentScoreValue);

                // displaying the test duration time
                long endTime = System.currentTimeMillis();
                double duration = (endTime - startTime) / 1000.0;
                System.out.println("Intent Criteria Test - Test duration (sec.) = " + duration);

                driver.quit();

        }
}
