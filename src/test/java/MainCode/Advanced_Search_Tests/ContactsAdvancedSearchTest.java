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

public class ContactsAdvancedSearchTest {

    @Test
    public void contactsAdvancedSearch() throws InterruptedException {

        String recordsperPage = "100";

        WebDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage();
        SidebarMenu sidebarMenu = new SidebarMenu();
        JobWizardPages wizard = new JobWizardPages(driver);
        AssertionsAdvancedSearch assertionsAdvancedSearch = new AssertionsAdvancedSearch(driver);
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage(driver);

        //Login to DS
        loginPage.login(driver);

        // waiting until the "Search" button will be available
        sidebarMenu.searchButton(driver);

        // Selecting the "Canada" in Contact Location
        wizard.selectContactLocation("Canada");

        // Selecting the "Canada" in HQ Location
        wizard.selectHQLocation("Canada");


        // waiting until the spinner in the Contacts Advanced Search disappeares
        Spinners.spinnerJobPreview(driver);


        // waiting until Coresignal loader disappears
         //       advancedSearchPage.tp_criteria_search_loader();

        // checking if Advanced Search table contains contact name ! Rechecked
        assertionsAdvancedSearch.assertName();


        // checking if number from Coresignal is displayed
        assertionsAdvancedSearch.assert_tp_criteria_search();


        // checking if "Nothing Found" is displayed in the Contacts Advanced Search
        // table
        assertionsAdvancedSearch.assertNothingFoundNotDisplayed();

        // // checking if not the same contacts are displayed on every pagination page
        assertionsAdvancedSearch.assertIfSameNameOnPages();

        // // selecting the '100 Records per page'
                advancedSearchPage.selectRecordsPerPage(recordsperPage);

         // waiting until the spinner in the Advanced Search table disappears
         Spinners.spinnerJobPreview(driver);

        // checking the "100 Records per page" functionality
        assertionsAdvancedSearch.assertRecordsPerPage();

        driver.quit();
    }

}
