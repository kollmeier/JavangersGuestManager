package ckollmeier.de;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class GuestListTest {
    @Test
    void getGuests_shouldBeEmptyInitially() {
        GuestList guestList = GuestList.builder().build();

        guestList.setGuests(new ArrayList<>());

        try {
            assertTrue(guestList.getGuests().isEmpty());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getGuests_shouldReadSameGuestsAsWrittenBefore() {
        GuestList guestList = GuestList.builder().build();

        guestList.setGuests(new ArrayList<>(List.of("Karl", "Ute")));

        try {
            assertEquals(List.of("Karl", "Ute"), guestList.getGuests());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void setGuests_shouldWriteToFileSystem() {
        GuestList guestList = GuestList.builder().build();

        guestList.setGuests(new ArrayList<>(List.of("Theodor", "Anette")));

        assertTrue(Files.exists(GuestList.PATH));
        try {
            assertThat(Files.readAllLines(GuestList.PATH)).containsExactly("Theodor", "Anette");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getGuests_shouldReadFromFileSystem() {
        GuestList guestList = GuestList.builder().build();

        List<String> guests =  new ArrayList<>(List.of("Stephan", "Max"));

        try {
            Files.write(GuestList.PATH, guests);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            assertEquals(guests, guestList.getGuests());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void getGuests_shouldThrowIoExceptionWhenFileDoesNotExist() {
        GuestList guestList = GuestList.builder().build();

        assertThrows(IOException.class, guestList::getGuests);
    }
}