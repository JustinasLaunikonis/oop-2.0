package com.nhlstenden.week2.validation;

import com.nhlstenden.week2.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MinimumAgeValidationRuleTest
{
    private MinimumAgeValidationRule rule;
    private User user;

    @BeforeEach
    void setup()
    {
        this.rule = new MinimumAgeValidationRule(18);
        this.user = new User("Justinas", "Secret1!", "justinas@example.com", LocalDate.now().minusYears(18));
    }

    @Test
    void setMinimumAgeInYears_withMinusOne_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.rule.setMinimumAgeInYears(-1));
    }

    @Test
    void setMinimumAgeInYears_withZero_setsMinimumAge()
    {
        this.rule.setMinimumAgeInYears(0);
        assertEquals(0, this.rule.getMinimumAgeInYears());
    }

    @Test
    void isValid_withNull_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.rule.isValid(null));
    }

    @Test
    void isValid_withUserExactlyMinimumAge_returnsTrue()
    {
        assertTrue(this.rule.isValid(this.user));
    }

    @Test
    void isValid_withUserOneDayTooYoung_returnsFalse()
    {
        this.user.setDateOfBirth(LocalDate.now().minusYears(18).plusDays(1));
        assertFalse(this.rule.isValid(this.user));
    }
}
