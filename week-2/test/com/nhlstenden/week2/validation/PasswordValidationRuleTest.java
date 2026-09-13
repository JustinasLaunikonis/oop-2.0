package com.nhlstenden.week2.validation;

import com.nhlstenden.week2.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidationRuleTest
{
    private PasswordValidationRule strictRule;
    private PasswordValidationRule lenientRule;
    private User user;

    @BeforeEach
    void setup()
    {
        this.strictRule = new PasswordValidationRule(false, true, true, true, true);
        this.lenientRule = new PasswordValidationRule(true, false, false, false, false);
        this.user = new User("Justinas", "Secret1!", "justinas@example.com", LocalDate.of(2000, 1, 15));
    }

    @Test
    void isValid_withNull_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.strictRule.isValid(null));
    }

    @Test
    void isValid_withPasswordMeetingAllRequirements_returnsTrue()
    {
        assertTrue(this.strictRule.isValid(this.user));
    }

    @Test
    void isValid_withSpaceWhenSpacesNotAllowed_returnsFalse()
    {
        this.user.setPassword("Secret 1!");
        assertFalse(this.strictRule.isValid(this.user));
    }

    @Test
    void isValid_withoutSpecialCharacterWhenRequired_returnsFalse()
    {
        this.user.setPassword("Secret1");
        assertFalse(this.strictRule.isValid(this.user));
    }

    @Test
    void isValid_withAnyPasswordWhenNothingRequired_returnsTrue()
    {
        this.user.setPassword("just words");
        assertTrue(this.lenientRule.isValid(this.user));
    }
}
