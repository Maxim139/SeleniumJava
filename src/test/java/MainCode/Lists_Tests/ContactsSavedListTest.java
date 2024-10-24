package MainCode.Lists_Tests;


import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

public class ContactsSavedListTest {
    

   static WebDriver driver;
  // static String filePath = "E:\\DealSignal\\FilesFromAutoTests";
   static String filePath;
   static String csvNamePattern = "contact_watchlist-";
   static String listName = "Contact List For ATests";
   static AssertionsListDetailsPage assertionsListDetailsPage;

@BeforeAll
    public static void setup(){

        filePath =  NewDirCreationAndRemove.createDir();

        ChromeOptions options = BrowserOptions.csvDownloadChromeOptionsAndGetAllFiles(filePath);

        driver = new ChromeDriver(options);

        assertionsListDetailsPage = new AssertionsListDetailsPage(driver);
    }


    @Test
    public void contactsSavedListTest() throws InterruptedException, IOException {

         ListDetails listDetails = new ListDetails(driver);

        //login to DS
        LoginPage login = new LoginPage();
        login.login(driver);
    
        //waiting until the "Search" button will be available and clicking on it
        SidebarMenu sidebarMenu = new SidebarMenu();
        sidebarMenu.searchButton(driver);

        //waiting until the "Lists" button available and clicking on it
        AdvancedSearchPage.clickListsButton(driver);
        
        //waiting until the gloabal table spinner disappears
        Spinners.spinnerListsTable(driver);

        Thread.sleep(500);

        //waiting until the search input field is available and entering the search phrase
        ListsPages listsPage = new ListsPages(driver);
        listsPage.enterSearchPhrase(listName);

        // waiting until the spinner in the Lists table disappears
        Spinners.spinnerListsTable(driver);
        
       // waiting until the expected list is available and clicking on it
       ListsPages.clickOnListName(driver);

       //checking if contact name is displayed in the table
       AssertionsListDetailsPage.assertName(driver);

        //Downloading the contact CSV file
         listDetails.clickOnDownload();

         Thread.sleep(4000);
         
         assertionsListDetailsPage.assertIsCSVDownloaded(csvNamePattern, filePath);

         driver.quit();

    }

      @AfterAll
    public static void cleanResources(){

        NewDirCreationAndRemove.deleteDir(filePath);
        
    }

}
