package com.nhlstenden.week2.validation;

import com.nhlstenden.week2.storage.InMemoryUserStorage;
import com.nhlstenden.week2.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest
{
    private InMemoryUserStorage storage;
    private UserValidator validator;
    private User adult;
    private User child;

    @BeforeEach
    void setup()
    {
        this.storage = new InMemoryUserStorage();
        this.validator = new UserValidator(this.storage);
        this.validator.addRule(new EmailValidationRule());
        this.validator.addRule(new MinimumAgeValidationRule(18));
        this.adult = new User("Justinas", "Secret1!", "justinas@example.com", LocalDate.now().minusYears(20));
        this.child = new User("Timmy", "Secret1!", "timmy@example.com", LocalDate.now().minusYears(10));
    }

    @Test
    void constructor_withNullStorage_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> new UserValidator(null));
    }

    @Test
    void addRule_withNull_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.validator.addRule(null));
    }

    @Test
    void failedRules_withChild_returnsOnlyMinimumAgeRule()
    {
        assertEquals(1, this.validator.failedRules(this.child).size());
        assertInstanceOf(MinimumAgeValidationRule.class, this.validator.failedRules(this.child).get(0));
    }

    @Test
    void register_withValidUser_savesUserAndReturnsTrue()
    {
        assertTrue(this.validator.register(this.adult));
        assertTrue(this.storage.containsName("Justinas"));
    }

    @Test
    void register_withInvalidUser_doesNotSaveAndReturnsFalse()
    {
        assertFalse(this.validator.register(this.child));
        assertFalse(this.storage.containsName("Timmy"));
    }
}
