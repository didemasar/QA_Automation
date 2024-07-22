package Pages;

import Base.BaseLibrary;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;

public class LoginPage extends BaseLibrary {
    @Step("Email Bilgisi Girilir ")
    public LoginPage writeEmail(String email) {
        driver.findElement(By.name("login email")).sendKeys(email);
        return this;
    }

    @Step("Parola Bilgisi Girilir")
    public LoginPage writePassword (String password) {
        driver.findElement(By.name("login-password")).sendKeys(password);
        return this;
    }

    @Step("Login Butonuna Tıklanır")
    public LoginPage clickLogin(){
        driver.findElements(By.xpath("//*[contains(text(),'Giriş Yap')]")).get(3).click();
        return this;
    }

    public LoginPage hataMesajiKontrolu(String text) {
        String value = driver.findElement(By.cssSelector("[class='message']")).getText();
        Assert.assertEquals(text, value);
        return this;
    }

    public LoginPage emailTemizle() {
        driver.findElement(By.name("login email")).clear();
        return this;
    }

    public LoginPage passwordTemizle() {
        driver.findElement(By.name("login-password")).clear();
        return this;
    }


}
