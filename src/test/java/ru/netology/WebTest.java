package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class WebTest {
    @Test
    void shouldSubmitRequest() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $("[form-field form-field_size_m form-field_theme_alfa-on-white]");
//        $(byText("Фамилия и имя")).click();
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
//        $(byText("Мобильный телефон")).click();
        form.$("[data-test-id=phone] input").setValue("+79111111111");
        form.$("[data-test-id=agreement]").click();
        form.$("[data-test-id=submit]").click();
//        $(".alert-success").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        $(".order-success").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        Thread.sleep(500_000_000);

    }
}