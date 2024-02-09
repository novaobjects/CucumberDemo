package kastigen.demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GoogleDirect {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.google.de");
            var actualTitle = driver.getTitle();
            System.out.println(actualTitle);

            var cookieButtonAcceptNothing = driver.findElement(By.id("W0wltc"));
            cookieButtonAcceptNothing.click();


            var searchField = driver.findElement(By.id("APjFqb"));
            searchField.sendKeys("Hannover");

            searchField.sendKeys(Keys.ENTER);
            // Searchbutton wird durch herunter fallende Liste überdeckt
            // var searchButton = driver.findElement(By.name("btnK"));
            // searchButton.click();


            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(aDriver -> aDriver.getTitle().equals("Hannover - Google Suche"));

            var resultsStats = driver.findElement(By.id("result-stats"));

            var testStatistics = resultsStats.getText();
            System.out.println(testStatistics);
            assertTrue(testStatistics.startsWith("Ungefähr"));
        }

        finally {
            driver.quit();
        }
    }
}