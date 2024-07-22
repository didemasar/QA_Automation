import Base.BaseTest;
import Pages.HomePage;
import Pages.MainPage;
import Pages.LoginPage;
import Pages.ProductPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.Assert;
import java.util.ArrayList;
import org.openqa.selenium.WebElement;

public class DidemTest extends BaseTest {

    LoginPage loginPage = new LoginPage();
    MainPage mainPage = new MainPage();
    HomePage homePage = new HomePage();
    ProductPage productPage = new ProductPage();

    @Test(description = "Başarılı Giriş Kontrolü")
    public void loginSuccesful() throws InterruptedException {
        loginPage
                .writeEmail(email)
                .writePassword(password)
                .clickLogin();

        sleep(5000);
        homePage.hesabimKontrol();
    }

    @Test(description = "Hatalı Kullanıcı Girişi")
    public void notValidLogin() throws InterruptedException {
        loginPage
                .writeEmail(email)
                .writePassword("dfjkghdkhfgkdf")
                .clickLogin();

        sleep(4000);
        loginPage.hataMesajiKontrolu(errorMessage);
    }

    @Test(description = "Bos Karakter Kontrolu")
    public void blankCharachterControl() throws InterruptedException {
        loginPage.writeEmail("")
                .writePassword("")
                .clickLogin();
        sleep(4000);
        loginPage.hataMesajiKontrolu(errorMessage2);
        homePage.hesabimKontrol();
    }

    @Test(description = "Item arama")
    public void searchItem () throws InterruptedException {
        loginPage
                .writeEmail(email)
                .writePassword(password)
                .clickLogin();

        sleep(5000);
        homePage.hesabimKontrol();

        mainPage.searchItem("Berskha Tshirt");
        mainPage.clickSearchButton();
        sleep(3000);
    }
    @Test(description = "Urun fiyatlarının kontrolu")
    public void controlItemPrice () throws InterruptedException {
        loginPage
                .writeEmail(email)
                .writePassword(password)
                .clickLogin();

        sleep(5000);
        homePage.hesabimKontrol();

        mainPage.searchItem("Berskha Tshirt");
        mainPage.clickSearchButton();
        sleep(3000);
        driver.navigate().refresh();
        String urunFiyat = mainPage.getUrunFiyat();
        mainPage.clickProduct();

        sleep(5000);

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles()); /// Diğer sekmeye geçmek için aktif tüm sekmeleri bir listeye aldım
        driver.switchTo().window(tabs.get(1)); /// Diğer sekmeye geçiş yaptım

        WebElement elementInNewTab = driver.findElement(By.className("prc-dsc")); ///Normalde bunu MainPage içerisinde yapmıştım ama sekme değiştirmem gerektiği için aynı şekilde yazamadım
        String urunFiyatDetay = elementInNewTab.getText();

        Assert.assertEquals( urunFiyat, urunFiyatDetay, "Fiyatlar aynı değil!");
    }

    @Test(description = "Sepete Ekleme")
    public void AddBasket () throws InterruptedException {
        loginPage
                .writeEmail(email)
                .writePassword(password)
                .clickLogin();

        sleep(5000);
        homePage.hesabimKontrol();

        mainPage.searchItem("Erkek Pantolon");
        mainPage.clickSearchButton();
        sleep(3000);
        driver.navigate().refresh();
        String urunFiyat = mainPage.getUrunFiyat();
        mainPage.clickProduct();

        sleep(5000);

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles()); /// Diğer sekmeye geçmek için aktif tüm sekmeleri bir listeye aldım
        driver.switchTo().window(tabs.get(1)); /// Diğer sekmeye geçiş yaptım

        WebElement elementInNewTab = driver.findElement(By.className("prc-dsc")); ///Normalde bunu ProductPage içerisinde yapmıştım ama sekme değiştirmem gerektiği için aynı şekilde yazamadım
        String urunFiyatDetay = elementInNewTab.getText();
        driver.navigate().refresh();
        productPage.selectSize();
        productPage.addToBasket();


    }

    @Test(description = "Sepete Ekleme Kontrol")
    public void controlAddBasket () throws InterruptedException {
        loginPage
                .writeEmail(email)
                .writePassword(password)
                .clickLogin();

        sleep(5000);
        homePage.hesabimKontrol();

        mainPage.searchItem("ElitWear Erkek Kargo Cepli Gabardin Baggy Pantolon");
        mainPage.clickSearchButton();
        sleep(3000);
        driver.navigate().refresh();
        String urunFiyat = mainPage.getUrunFiyat();
        mainPage.clickProduct();

        sleep(5000);

        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles()); /// Diğer sekmeye geçmek için aktif tüm sekmeleri bir listeye aldım
        driver.switchTo().window(tabs.get(1)); /// Diğer sekmeye geçiş yaptım

        WebElement elementInNewTab = driver.findElement(By.className("prc-dsc")); ///Normalde bunu ProductPage içerisinde yapmıştım ama sekme değiştirmem gerektiği için aynı şekilde yazamadım
        String urunFiyatDetay = elementInNewTab.getText();
        driver.navigate().refresh();
        productPage.selectSize();
        productPage.addToBasket();
        productPage.addToBasketPopupControl("Siparişi Tamamla");


    }


}
