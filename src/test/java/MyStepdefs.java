import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
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
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class MyStepdefs {

    private WebDriver driver;

    @Before
    public void chromeDriver() {
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
    }
    @Before
    public void firefoxDriver() {
        driver = new FirefoxDriver();
    }
    @After
    public void tearDown() {
        driver.quit();
    }

    @When("User has entered valid inputs")
    public void user_has_entered_valid_inputs() {
        fillRegistrationForm(firstName(), lastName(), email(), email(), password(), password());

    }

    private void clicker(By by) {
        WebElement element = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(by));
        element.click();
    }

    private void picker(By by, String text) {
        WebElement element = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(by));
        element.clear();
        element.sendKeys(text);
    }

    private void submiter(By by) {
        WebElement element = new WebDriverWait(this.driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(by));
        element.submit();
    }


    protected static String firstName() {        return "John";}

    protected static String lastName() {      return "John";}

    protected static String password() {
        return "Johnny";
    }

    protected static String passwordShort() {
        return "John";
    }

    protected static String passwordLong() {
        return "JohnnyIsAMenace2Society";
    }

    protected static String email() {
        return "john@john.se";
    }

    protected static String misMatchEmail() {
        return "johnny@john.se";
    }

    protected static String invalidEmail() {
        return "johnse";
    }

    protected static String emptyField() {
        return "";
    }

    protected static String validNumber() {
        return "12/12/1990";
    }

    protected static String invalidChars() {
        return "@@!!Räksmörgås";
    }

    protected static String underEighteen() {
        return "12/12/2022";
    }

    protected static void tabAndType(WebDriver driver, String text) {
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
        driver.switchTo().activeElement().sendKeys(text);
    }

    protected static void tab(WebDriver driver) {
        driver.switchTo().activeElement().sendKeys(Keys.TAB);
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
    public void acceptAllTerms() throws InterruptedException {
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

    @Then("User does not reach the radiobuttons by tabbing")
    public void userDoesNotReachTheRadiobuttonsByTabbing() {
        boolean reachedRadio = false;

        WebElement current = driver.switchTo().activeElement();


        for (int i = 0; i < 20; i++) {

            current.sendKeys(Keys.TAB);


            current = driver.switchTo().activeElement();


            String tag = current.getTagName();
            String type = current.getAttribute("type");


            if ("input".equalsIgnoreCase(tag) && "radio".equalsIgnoreCase(type)) {
                reachedRadio = true;
                break;
            }
        }

        assertFalse(reachedRadio);
    }

    private void fillRegistrationForm(String firstname,
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

    private void fillRegistrationFormParental(String underEighteen,
                                              String firstname,
                                              String lastname,
                                              String emailValue,
                                              String comfirmEmail) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.findElement(By.name("DateOfBirth")).clear();
        driver.findElement(By.name("DateOfBirth")).sendKeys(underEighteen);

        WebElement parentName = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[id='sign_up_6'][name='ParentForename'][type='text']")));
        parentName.sendKeys(firstname);

        driver.findElement(By.cssSelector("[id='sign_up_8'][name='ParentSurname'][type='text']")).sendKeys(lastname);

        driver.findElement(By.cssSelector("[id='signup_supporteraccount_parentemail'][name='ParentEmailAddress'][type='text']")).sendKeys(emailValue);

        driver.findElement(By.cssSelector("[id='signup_supporteraccount_parentemailconfirm'][name='ParentConfirmEmailAddress'][type='text']")).sendKeys(comfirmEmail);
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

    @Given("User is on registrationpage")
    public void userIsOnRegistrationpage() {
        driver.get("file:///Users/johan/Downloads/Register/registrering.html");
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
                // Account confirmation
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

    @Given("The uses uses {string}")
    public void theUsesUses(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        driver.get("file:///Users/johan/Downloads/Register/registrering.html");
    }
}


