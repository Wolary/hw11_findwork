import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byAttribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestForm {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void fillForm() {
        String site = "https://sberhealth.ru/",
                doctor = "Кардиолог",
                metro = "Курская";

        open(site);
        $(".help-to-get-better__title").shouldHave(text("Помогаем быть здоровыми"));

        $(".download-mobile-app__qr-code").shouldBe(visible);

        $(".med-service").$(byText("Выбрать врача")).click();

        switchTo().window(1);
        $(".doctors-list-page-search-form").shouldHave(text("Запишитесь на приём к врачу онлайн"));

        $(byText("Врач, клиника, болезнь, услуга")).click();
        $(".v-autocomplete").find(byText(doctor)).click();

        $(byText("Метро, город МО")).click();
        $(".v-autocomplete-list").find(byText(metro)).click();

        $(".doctor-list-page").$(byText("Найти")).click();

        $(".doctor__near-metro-title").shouldHave(text("Врачи на м. " + metro));

        switchTo().window(0);
        $(".med-service__container").find(byText("Онлайн-аптека")).click();

        switchTo().window(2);
        $(byAttribute("href", "/goods/zootovary/")).click();
        $(".filter__widget-inner").$(byText("Зоэтис")).click();
        //sleep(10000);
        $(byText("Бренды: Зоэтис")).shouldBe(visible);
        $(".cc-item--group").find(byText("Купить")).click();
        $(byAttribute("href", "/personal/cart/")).click();
        $(byAttribute("href", "/personal/order/make/")).shouldBe(visible);
    }

}
