import io.cucumber.java.After;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HelperMethods {
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
}
