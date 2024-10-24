package MainCode.Advanced_Search_Tests;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;


import MainCode.AdvancedSearchPage;
import MainCode.JobWizardPages;
import MainCode.LoginPage;
import MainCode.SidebarMenu;
import MainCode.Spinners;
import MainCode.Assertions.AssertionsAdvancedSearch;

public class CompaniesAdvancedSearchTest {

    @Test
    public void companiesAdvancedSearch() throws InterruptedException {

        String recordsPerPage = "100"; //assertion that the records on page more than this selected value

        WebDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage();
        SidebarMenu sidebarMenu = new SidebarMenu();
        JobWizardPages wizard = new JobWizardPages(driver);
        AssertionsAdvancedSearch assertionsAdvancedSearch = new AssertionsAdvancedSearch(driver);
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage(driver);

        //login to DS
        loginPage.login(driver);

        // waiting until the "Search" button will be available
        sidebarMenu.searchButton(driver);

        // Selecting the Companies tab
        AdvancedSearchPage.switchToCompaniesTab(driver);

        // Selecting the "Canada" in HQ Location
        wizard.selectHQLocation("Canada");

        // waiting until the spinner in the Companies Advanced Search disappeares
        Spinners.spinnerJobPreview(driver);

        // checking if Advanced Search table contains company name
        assertionsAdvancedSearch.assertName();

        // waiting until Coresignal loader disappears
      //  advancedSearchPage.tp_criteria_search_loader();

        // checking if number from Coresignal is displayed
        assertionsAdvancedSearch.assert_tp_criteria_search();

        // checking if "Nothing Found" is displayed in the Companies Advanced Search
        // table
        assertionsAdvancedSearch.assertNothingFoundNotDisplayed();

        // checking if not the same companies are displayed on every pagination page
        assertionsAdvancedSearch.assertIfSameNameOnPages();

        // clicking on the '100 Records per page' button
        advancedSearchPage.selectRecordsPerPage(recordsPerPage);

        // waiting until the spinner in the Advanced Search table disappears
        Spinners.spinnerJobPreview(driver);


        // checking the "100 Records per page" functionality
        assertionsAdvancedSearch.assertRecordsPerPage();

        driver.quit();

    }

}
