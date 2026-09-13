package com.nhlstenden.week2.validation;

import com.nhlstenden.week2.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EmailValidationRuleTest
{
    private EmailValidationRule rule;
    private User user;

    @BeforeEach
    void setup()
    {
        this.rule = new EmailValidationRule();
        this.user = new User("Justinas", "Secret1!", "justinas.launikonis@student.nhlstenden.com", LocalDate.of(2000, 1, 15));
    }

    @Test
    void isValid_withNull_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.rule.isValid(null));
    }

    @Test
    void isValid_withValidEmail_returnsTrue()
    {
        assertTrue(this.rule.isValid(this.user));
    }

    @Test
    void isValid_withoutAtSign_returnsFalse()
    {
        this.user.setEmail("justinas.example.com");
        assertFalse(this.rule.isValid(this.user));
    }

    @Test
    void isValid_withoutTopLevelDomain_returnsFalse()
    {
        this.user.setEmail("justinas@example");
        assertFalse(this.rule.isValid(this.user));
    }

    @Test
    void isValid_withEmptyLocalPart_returnsFalse()
    {
        this.user.setEmail("@example.com");
        assertFalse(this.rule.isValid(this.user));
    }
}
