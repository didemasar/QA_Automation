import Base.BaseTest;
import Pages.HomePage;
import Pages.LoginPage;

import org.testng.annotations.Test;

public class UserTests extends BaseTest {

    LoginPage loginPage = new LoginPage();
    HomePage homePage = new HomePage();

    @Test(description = "Başarılı Login Kullanıcı Giriş Kontrol")
    public void loginSuccesful() throws InterruptedException {
        loginPage
                .writeEmail(email)
                .writePassword(password)
                .clickLogin();

        sleep(5000);
        homePage.hesabimKontrol();
    }

    @Test(description = "Hatalı Login Kullanıcı Giriş Kontrol")
    public void notValidLogin() throws InterruptedException {
        loginPage
                .writeEmail(email)
                .writePassword("aaaaaaaaa")
                .clickLogin();

        sleep(3000);
        loginPage.hataMesajiKontrolu(errorMessage);
    }

    @Test(description = "Sistemde Kayıtlı Olmayan Email Kullanıcı Girişi")
    public void notValidWithEmail() throws InterruptedException {
        loginPage.writeEmail("aaaaaaaaaaa@mail.com")
                .writePassword(password)
                .clickLogin();
        sleep(3000);
        loginPage.hataMesajiKontrolu(errorMessage);
    }

    @Test(description = "Bos Karakter Kontrolu")
    public void requiredBlankControl() throws InterruptedException {
        loginPage.writeEmail("")
                .writePassword("")
                .clickLogin();
        sleep(3000);
        loginPage.hataMesajiKontrolu(errorMessage2);
        loginPage.writeEmail(email)
                .clickLogin();
        sleep(3000);
        loginPage
                .hataMesajiKontrolu("Lütfen şifrenizi giriniz.")
                .writePassword(password)
                .clickLogin();
        sleep(3000);
        homePage.hesabimKontrol();
    }

    @Test(description = "Minumum Karakter Kontrolü")
    public void minControl() throws InterruptedException {
        loginPage.writeEmail("1")
                .writePassword("2")
                .clickLogin();
        sleep(3000);
        loginPage.hataMesajiKontrolu(errorMessage2);
        loginPage.writeEmail(email)
                .writePassword("2")
                .clickLogin();
        sleep(3000);
        ;
        loginPage.hataMesajiKontrolu(errorMessage);
    }

    @Test(description = "Maximum Karakter Kontrolü")
    public void maxControl() throws InterruptedException {
        loginPage.writeEmail("111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111")
                .writePassword(password);
        sleep(3000);
        loginPage.clickLogin();
        Thread.sleep(3000);
        loginPage.hataMesajiKontrolu(errorMessage2);
        sleep(3000);
        loginPage.emailTemizle()
                .passwordTemizle()
                .writeEmail(email)
                .clickLogin();
        sleep(3000);
        homePage.hesabimKontrol();
    }

}