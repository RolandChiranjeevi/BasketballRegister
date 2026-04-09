import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class MyStepdefs extends HelperMethods {

    @When("User has entered valid inputs")
    public void user_has_entered_valid_inputs() {
        fillRegistrationForm(firstName(), lastName(), email(), email(), password(), password());

    }

    @Then("I get a membersnumber")
    public void iGetAMembersnumber() {
        driver.getCurrentUrl();
        System.out.println(driver.getCurrentUrl());
        // Verifying
        String expected = "A104955";
        String actual = driver.findElement(By.cssSelector("[class='bold  margin-bottom-40']")).getText();
        assertEquals(expected, actual);
    }


    @When("User has entered valid inputs by tabbing")
    public void userHasEnteredValidInputsByTabbing() {
        tabAndType(driver, firstName());
        tabAndType(driver, lastName());
        tabAndType(driver, email());
        tabAndType(driver, email());
        tabAndType(driver, password());
        tabAndType(driver, password());
    }

    @When("Accept all terms")
    public void acceptAllTerms() {
        // Account confirmation
        clicker(By.cssSelector("label[for='sign_up_25']"));
        clicker(By.cssSelector("label[for='sign_up_26']"));
        // Code of ethics and conduct
        clicker(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']"));
        }

    @Given("focus is on the first field date of birth")
    public void focusIsOnTheFirstField() {
        picker(By.cssSelector("input#dp[name='DateOfBirth'][type='datetime']"),"");
    }

    @When("User has entered invalid inputs {string}")
    public void userHasEnteredInvalidInputs(String caseName) {
        System.out.println("caseName = '" + caseName + "'");
        switch (caseName) {

            case "invalidEmail":
                fillRegistrationForm(firstName(),lastName(),invalidEmail(), email(), password(), password());
                break;
            case "misMatchEmail":
                fillRegistrationForm(firstName(),lastName(),email(),misMatchEmail(),password(), password());;
                break;

            case "missingPwd":
                fillRegistrationForm(firstName(), lastName(), email(), email(), emptyField(), emptyField());
                break;
            case "misMatchPwd":
                fillRegistrationForm(firstName(), lastName(), email(), email(), password(), invalidEmail());
                break;
            case "pwdShort":
                fillRegistrationForm(firstName(), lastName(), email(), email(), passwordShort(), passwordShort());
                break;
            case "pwdLong":
                fillRegistrationForm(firstName(), lastName(), email(), email(), passwordLong(), passwordShort());
                break;
            case "missingFirstName":
                fillRegistrationForm(emptyField(), lastName(), email(), email(), password(), password());
                break;
            case "missingLastName":
                fillRegistrationForm(firstName(), emptyField(), email(), email(), password(), password());
                break;
        }
    }

    @When("User click on join button")
    public void userClickOnJoinButton()  {
                submiter(By.cssSelector("[class='btn btn-big red']"));
    }

    @Then("User should get an {string}")
    public void userShouldGetAn(String errorMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class='warning field-validation-error']")));
        String actual = error.getText();
        assertEquals(errorMessage, actual);
    }

    @When("Not accepting all {string}")
    public void notAcceptingAll(String terms) {
        switch (terms) {
            case "understood":
                // Account confirmations
                clicker(By.cssSelector("label[for='sign_up_26']"));
                // Code of ethics and conduct
                clicker(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']"));
                break;
            case "overEighteen":
                // Account confirmation
                clicker(By.cssSelector("label[for='sign_up_25']"));
                // Code of ethics and conduct
                clicker(By.cssSelector("label[for='fanmembersignup_agreetocodeofethicsandconduct']"));
                break;
            case "codeOfConduct":
                // Account confirmation
                clicker(By.cssSelector("label[for='sign_up_25']"));
                clicker(By.cssSelector("label[for='sign_up_26']"));
                break;
        }

    }

    @Given("The user uses {string}")
    public void theUserUses(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            driver = new ChromeDriver(options);
        } else {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        }
        driver.get("file:///Users/johan/Downloads/Register/registrering.html");
    }

    @When("Accepts all terms by using keyboard only")
    public void acceptsAllTermsByUsingKeyboardOnly() {
        tab(driver);
        tab(driver);
        tab(driver);

    }

    @When("Navigates by tabbing and joins")
    public void navigatesByTabbingAndJoins() {
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.ENTER);
            }
}


