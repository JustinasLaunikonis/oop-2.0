package com.nhlstenden.week2.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserTest
{
    private User user;

    @BeforeEach
    void setup()
    {
        this.user = new User("Justinas", "Secret1!", "justinas@example.com", LocalDate.of(2000, 1, 15));
    }

    @Test
    void setName_withNull_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.user.setName(null));
    }

    @Test
    void setName_withBlank_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.user.setName("   "));
    }

    @Test
    void setDateOfBirth_withFutureDate_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.user.setDateOfBirth(LocalDate.now().plusDays(1)));
    }

    @Test
    void getAgeInYears_withDateTwentyYearsAgo_returnsTwenty()
    {
        assertEquals(20, this.user.getAgeInYears(LocalDate.now().minusYears(20)));
    }

    @Test
    void getAgeInYears_withDateTwentyYearsAgoTomorrow_returnsNineteen()
    {
        assertEquals(19, this.user.getAgeInYears(LocalDate.now().minusYears(20).plusDays(1)));
    }
}
