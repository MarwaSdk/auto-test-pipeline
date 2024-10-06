package com.automationpanda.example.stepdefs;

import com.automationpanda.example.pages.GooglePage;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleSearchSteps {

    private WebDriver driver;
    private GooglePage googlePage;

    @Before(value = "@web", order = 1)
    public void initWebDriver() throws Throwable {
        //System.setProperty("webdriver.gecko.driver", "C:/Users/User/Documents/cucumber-selenium/geckodriver.exe");
        //FirefoxOptions firefoxOptions = new FirefoxOptions();
        //firefoxOptions.addArguments("--headless");
        WebDriverManager.firefoxdriver().setup();

        driver = new FirefoxDriver();
    }

    @Before(value = "@google", order = 10)
    public void initGooglePage() throws Throwable {
        googlePage = new GooglePage(driver);
    }

    @Given("^a web browser is on the Google page$")
    public void aWebBrowserIsOnTheGooglePage() throws Throwable {
        googlePage.navigateToHomePage();
    }

    @When("^the search phrase \"([^\"]*)\" is entered$")
    public void theSearchPhraseIsEntered(String phrase) throws Throwable {
        googlePage.enterSearchPhrase(phrase,driver);
    }

    @Then("^results for \"([^\"]*)\" are shown$")
    public void resultsForAreShown(String phrase) throws Throwable {
        assertThat(googlePage.pageTitleContains(phrase)).isTrue();
    }

    @After(value = "@web")
    public void disposeWebDriver() throws Throwable {
        driver.quit();
    }
}
