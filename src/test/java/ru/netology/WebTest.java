package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class WebTest {
    @Test
    void verificationFormSubmit() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $("[class=\"form form_size_m form_theme_alfa-on-white\"]");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79111111111");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=\"button button_view_extra button_size_m button_theme_alfa-on-white\"]").click();
        $("[data-test-id=order-success]").shouldHave(exactText(" Ваша заявка успешно отправлена! Наш " +
                "менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void verificationEntryToLatinLetters() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $("[class= \"form form_size_m form_theme_alfa-on-white\"]");
        form.$("[data-test-id=name] input").setValue("Ivanov Ivan");
        form.$("[data-test-id=phone] input").setValue("+79111111111");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=\"button button_view_extra button_size_m button_theme_alfa-on-white\"]").click();
        $(".input_invalid .input__sub").shouldHave(text("Имя и Фамилия указаные неверно. " +
                "Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void verificationEntryWrongNumber() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $("[class= \"form form_size_m form_theme_alfa-on-white\"]");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+791111111");
        form.$("[data-test-id=agreement]").click();
        form.$("[class=\"button button_view_extra button_size_m button_theme_alfa-on-white\"]").click();
        $(".input_invalid .input__sub").shouldHave(text("Телефон указан неверно. Должно быть 11 цифр," +
                " например, +79012345678."));
    }

    @Test
    void verificationNotAgreement() throws InterruptedException {
        open("http://localhost:9999");
        SelenideElement form = $("[class= \"form form_size_m form_theme_alfa-on-white\"]");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79012345678");
        form.$("[class=\"button button_view_extra button_size_m button_theme_alfa-on-white\"]").click();
        $(".input_invalid .checkbox__text").shouldHave(text("Я соглашаюсь с условиями обработки и " +
                "использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}