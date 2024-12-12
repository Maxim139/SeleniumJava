package MainCode.Utilities;

import java.util.HashMap;

import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserOptions {
    

    public static ChromeOptions csvDownloadChromeOptionsAndGetAllFiles(String filePath){
                
        ChromeOptions options = new ChromeOptions();

        HashMap<String, Object> chromePrefs = new HashMap<>();
       chromePrefs.put("profile.default_content_settings.popups", 0);
       chromePrefs.put("download.default_directory", filePath);
       chromePrefs.put("safebrowsing.enabled", "true");
       options.setExperimentalOption("prefs", chromePrefs);

        return options;
    }
}
