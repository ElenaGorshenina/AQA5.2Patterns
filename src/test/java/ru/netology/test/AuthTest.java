package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataGenerator.Registration.*;

public class AuthTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void validTestData () {

        var RegistrationInfo  = userValid();
        $("[name='login']").setValue(userValid().getLogin());
        $("[name='password']").setValue(userValid().getPassword());
        $("[data-test-id='action-login'] .button__content").click();
      }

}
