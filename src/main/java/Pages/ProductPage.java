package Pages;

import Base.BaseLibrary;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ProductPage extends BaseLibrary {
    @Step("Urun fiyati ogrenme detay sayfasi")
    public String getUrunFiyatDetay(){
        String urunFiyat;

        urunFiyat = driver.findElement(By.className("prc-dsc")).getText();
        return urunFiyat;
    }

    @Step("Beden secme")
    public ProductPage selectSize(){
        driver.findElement(By.xpath("//*[@id=\"product-detail-app\"]/div/div[2]/div/div[2]/div[2]/div/div[1]/div[3]/div[2]/div/div/div/div[2]")).click();
        return this;
    }

    @Step("Sepete Ekleme")
    public ProductPage addToBasket(){
        driver.findElement(By.className("add-to-basket")).click();
        return this;
    }

    @Step("Sepete Ekleme popup Kontrol")
    public ProductPage addToBasketPopupControl(String text){

        String value = driver.findElement(By.className("basket-preview-popup")).getText();
        Assert.assertEquals(text, value);
        return this;
    }



}
