package com.nhlstenden.week2.validation;

import com.nhlstenden.week2.storage.InMemoryUserStorage;
import com.nhlstenden.week2.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UniqueUsernameValidationRuleTest
{
    private InMemoryUserStorage storage;
    private UniqueUsernameValidationRule rule;
    private User user;

    @BeforeEach
    void setup()
    {
        this.storage = new InMemoryUserStorage();
        this.rule = new UniqueUsernameValidationRule(this.storage);
        this.user = new User("Justinas", "Secret1!", "justinas@example.com", LocalDate.of(2000, 1, 15));
    }

    @Test
    void constructor_withNullStorage_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> new UniqueUsernameValidationRule(null));
    }

    @Test
    void isValid_withNull_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.rule.isValid(null));
    }

    @Test
    void isValid_withEmptyStorage_returnsTrue()
    {
        assertTrue(this.rule.isValid(this.user));
    }

    @Test
    void isValid_withNameAlreadyStored_returnsFalse()
    {
        this.storage.save(new User("Justinas", "Other1!", "other@example.com", LocalDate.of(1999, 5, 5)));
        assertFalse(this.rule.isValid(this.user));
    }

    @Test
    void isValid_withDifferentCaseName_returnsTrue()
    {
        this.storage.save(new User("justinas", "Other1!", "other@example.com", LocalDate.of(1999, 5, 5)));
        assertTrue(this.rule.isValid(this.user));
    }
}
