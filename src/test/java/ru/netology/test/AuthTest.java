package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.RegistrationInfo;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.Registration.getRandomLogin;
import static ru.netology.data.DataGenerator.Registration.getRandomPassword;

public class AuthTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void validTestData () {
        RegistrationInfo userValid  = DataGenerator.Registration.userValid();
        $("[name='login']").setValue(userValid.getLogin());
        $("[name='password']").setValue(userValid.getPassword());
        $("[data-test-id='action-login'] .button__content").click();
        $(withText("Личный кабинет")).shouldBe(Condition.visible);
      }

    @Test
    void blockedUserTest () {
        RegistrationInfo userNoValid  = DataGenerator.Registration.userNoValid();
        $("[name='login']").setValue(userNoValid.getLogin());
        $("[name='password']").setValue(userNoValid.getPassword());
        $("[data-test-id='action-login'] .button__content").click();
        $(".notification__content").shouldHave(text("Пользователь заблокирован"));
    }

    @Test
    void userNoValidLoginTest () {
        RegistrationInfo userNoValidLogin  = DataGenerator.Registration.userNoValidLogin();
        $("[name='login']").setValue(getRandomLogin());
        $("[name='password']").setValue(userNoValidLogin.getPassword());
        $("[data-test-id='action-login'] .button__content").click();
        $(".notification__content").shouldHave(text("Неверно указан логин или пароль"));
    }

    @Test
    void userNoValidPasswordTest () {
        RegistrationInfo userNoValidPassword  = DataGenerator.Registration.userNoValidPassword();
        $("[name='login']").setValue(userNoValidPassword.getLogin());
        $("[name='password']").setValue(getRandomPassword());
        $("[data-test-id='action-login'] .button__content").click();
        $(".notification__content").shouldHave(text("Неверно указан логин или пароль"));
    }

}
