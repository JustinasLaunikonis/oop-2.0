package com.nhlstenden.week2.storage;

import com.nhlstenden.week2.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryUserStorageTest
{
    private InMemoryUserStorage storage;
    private User user;

    @BeforeEach
    void setup()
    {
        this.storage = new InMemoryUserStorage();
        this.user = new User("Justinas", "Secret1!", "justinas@example.com", LocalDate.of(2000, 1, 15));
    }

    @Test
    void save_withNull_throwsIllegalArgumentException()
    {
        assertThrows(IllegalArgumentException.class, () -> this.storage.save(null));
    }

    @Test
    void save_withNewUser_addsUserToList()
    {
        this.storage.save(this.user);
        assertEquals(1, this.storage.getUsers().size());
        assertTrue(this.storage.getUsers().contains(this.user));
    }

    @Test
    void save_withSameNameTwice_throwsIllegalArgumentException()
    {
        this.storage.save(this.user);
        User duplicate = new User("Justinas", "Other1!", "other@example.com", LocalDate.of(1999, 5, 5));
        assertThrows(IllegalArgumentException.class, () -> this.storage.save(duplicate));
    }

    @Test
    void containsName_withStoredName_returnsTrue()
    {
        this.storage.save(this.user);
        assertTrue(this.storage.containsName("Justinas"));
    }

    @Test
    void findByName_withUnknownName_returnsNull()
    {
        assertNull(this.storage.findByName("Nobody"));
    }
}
