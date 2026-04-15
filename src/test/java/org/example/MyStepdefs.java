package org.example;

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

import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MyStepdefs {


    protected WebDriver driver;
    @After
    public void tearDown() {
        driver.quit();
    }

    protected void clicker(By by) {
        WebElement element = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(by));
        element.click();
    }

    protected void picker(By by, String text) {
        WebElement element = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(by));
        element.clear();
        element.sendKeys(text);
    }

    protected void submiter(By by) {
        WebElement element = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(by));
        element.submit();
    }


    protected static String firstName() {        return "John";}
    protected static String lastName() {      return "John";}
    protected static String password() {        return "Johnny";    }
    protected static String passwordShort() {        return "John";    }
    protected static String passwordLong() {        return "JohnnyIsAMenace2Society";    }
    protected static String email() {        return "john@john.se";    }
    protected static String misMatchEmail() {        return "johnny@john.se";    }
    protected static String invalidEmail() {        return "johnse";    }
    protected static String emptyField() {        return "";    }

    protected static void tabAndType(WebDriver driver, String text) {
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(text);
    }

    protected static void tab(WebDriver driver) {
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(Keys.SPACE);
    }

    protected void fillRegistrationForm(String firstname,
                                        String lastname,
                                        String emailValue,
                                        String comfirmEmail,
                                        String pwd,
                                        String confirm) {

        driver.findElement(By.cssSelector("[id='member_firstname'][name='Forename'][type='text']")).sendKeys(firstname);

        driver.findElement(By.cssSelector("[id='member_lastname'][name='Surname'][type='text']")).sendKeys(lastname);

        driver.findElement(By.cssSelector("[id='member_emailaddress'][name='EmailAddress'][type='text']")).sendKeys(emailValue);

        driver.findElement(By.cssSelector("[id='member_confirmemailaddress'][name='ConfirmEmailAddress'][type='text']")).sendKeys(comfirmEmail);

        driver.findElement(By.cssSelector("[id='signupunlicenced_password'][name='Password'][type='password']")).sendKeys(pwd);

        driver.findElement(By.cssSelector("[id='signupunlicenced_confirmpassword'][name='ConfirmPassword'][type='password']")).sendKeys(confirm);
    }



    @When("User has entered valid inputs")
    public void user_has_entered_valid_inputs() {
        fillRegistrationForm(firstName(), lastName(), email(), email(), password(), password());

    }

    @Then("I get a membersnumber")
    public void iGetAMembersnumber() {
        driver.getCurrentUrl();
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
            //options.addArguments("--headless=new");
            driver = new ChromeDriver(options);
        } else {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--headless");
            driver = new FirefoxDriver(options);
        }
        URL url= getClass().getClassLoader().getResource("registrering.html");
        driver.get(url.toString());
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


