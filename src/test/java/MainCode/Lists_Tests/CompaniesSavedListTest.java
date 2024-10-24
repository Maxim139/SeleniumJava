package MainCode.Lists_Tests;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import MainCode.AdvancedSearchPage;
import MainCode.BrowserOptions;
import MainCode.ListDetails;
import MainCode.ListsPages;
import MainCode.LoginPage;
import MainCode.NewDirCreationAndRemove;
import MainCode.SidebarMenu;
import MainCode.Spinners;
import MainCode.Assertions.AssertionsListDetailsPage;


@TestMethodOrder(OrderAnnotation.class)
public class CompaniesSavedListTest {

    static WebDriver driver;
    static String filePath;
   // static String filePath = "E:\\DealSignal\\FilesFromAutoTests";
    static String csvNamePattern = "Company-List-For-ATests.csv";
    static String listName = "Company List For ATests";
    static AssertionsListDetailsPage assertionsListDetailsPage;

    @BeforeAll
    public static void setup(){
        
        filePath =  NewDirCreationAndRemove.createDir();

        ChromeOptions options = BrowserOptions.csvDownloadChromeOptionsAndGetAllFiles(filePath);

        driver = new ChromeDriver(options);

        assertionsListDetailsPage = new AssertionsListDetailsPage(driver);
    }

    @Test
    @Order(1)
    public void companiesSavedListTest() throws InterruptedException, IOException {


         ListDetails listDetails = new ListDetails(driver);

       //  login to DS
        LoginPage loginPage = new LoginPage();
        loginPage.login(driver);

        // waiting until the "Search" button will be available and clicking on it
        SidebarMenu sidebarMenu = new SidebarMenu();
        sidebarMenu.searchButton(driver);

        // waiting until the "Lists" button available and clicking on it
        AdvancedSearchPage.clickListsButton(driver);

        // waiting until the gloabal table spinner disappears
        Spinners.spinnerListsTable(driver);

        // clicking on the "Company Lists" button
        ListsPages.switchToCompanyLists(driver);

        // waiting until the gloabal table spinner disappears
        Spinners.spinnerListsTable(driver);

        Thread.sleep(500);
    

        // waiting until the search input field is available and entering the search phrase
        ListsPages lists = new ListsPages(driver);
        lists.enterSearchPhrase(listName);

        // waiting until the spinner in the Lists table disappears
        Spinners.spinnerListsTable(driver);

        // waiting until the expected list is available and clicking on it
        ListsPages.clickOnListName(driver);

        // checking if company name is displayed in the table
        AssertionsListDetailsPage.assertName(driver);

        //Downloading the company CSV file
        listDetails.clickOnDownload();

        Thread.sleep(3000);

        assertionsListDetailsPage.assertIsCSVDownloaded(csvNamePattern, filePath);

        driver.quit();

    }

    // @Test
    // @Order(2)
    // public void checkIfCSVDownloaded() throws IOException, InterruptedException{

    //     Thread.sleep(3000);

    //     assertionsListDetailsPage.assertIsCSVDownloaded(csvNamePattern, filePath);

    // }

    
    @AfterAll
    public static void cleanResources(){

        NewDirCreationAndRemove.deleteDir(filePath);
        
    }


}
