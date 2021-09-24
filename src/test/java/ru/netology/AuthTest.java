package ru.netology;

import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {

        RegistrationInfo info = DataGenerator
                .Registration
                .generateByLogin("en");


    @Test
    void validTestData () {
        open("http://localhost:9999");
        $("[name='login']").setValue(info.getLogin());
        $("[name='password']").setValue(info.getPassword());
        $(byText("Продолжить")).click();
    }

}
