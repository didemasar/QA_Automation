package Pages;

import Base.BaseLibrary;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage extends BaseLibrary {
    public MainPage KabulEtTikla() throws InterruptedException {
        sleep(4000);
        WebElement element= driver.findElement(By.id("onetrust-accept-btn-handler"));
        if(element.isDisplayed())
        driver.findElement(By.id("onetrust-accept-btn-handler")).click();
        return this;
    }
    @Step("Istenen item arama cubuguna yazilir")
    public MainPage searchItem(String ItemName){
        driver.findElement(By.className("V8wbcUhU")).sendKeys(ItemName);
        return this;
    }

    @Step("Arama butonuna tiklanir")
    public MainPage clickSearchButton(){
        driver.findElement(By.className("cyrzo7gC")).click();
        return this;
    }


    @Step("Urun tiklama")
    public MainPage clickProduct(){
        driver.findElement(By.className("prdct-desc-cntnr-ttl-w")).click();
        return this;
    }

    @Step("Urun fiyati ogrenme arama sayfasi")
    public String getUrunFiyat(){
        String urunFiyat;

        urunFiyat = driver.findElement(By.className("prc-box-dscntd")).getText();
        return urunFiyat;
    }


    //@Step("")

}
