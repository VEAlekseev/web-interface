package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class WebTest {
    @Test
    void shouldSubmitRequest() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $("[class=\"form form_size_m form_theme_alfa-on-white\"]");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79111111111");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=\"button button_view_extra button_size_m button_theme_alfa-on-white\"]").click();
        $("data-test-id=\"order-success\"").shouldHave(exactText("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
        Thread.sleep(500_000_000);

    }
}