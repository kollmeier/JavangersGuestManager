package ckollmeier.de;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class GuestListTest {
    @Test
    void getGuests_shouldBeEmptyInitially() {
        GuestList guestList = GuestList.builder().build();

        assertTrue(guestList.getGuests().isEmpty());
    }

    @Test
    void getGuests_shouldReadSameGuestsAsWrittenBefore() {
        GuestList guestList = GuestList.builder().build();

        guestList.setGuests(new ArrayList<>(List.of("Karl", "Ute")));

        assertEquals(List.of("Karl", "Ute"), guestList.getGuests());
    }

    @Test
    void setGuestsShouldWriteToFileSystem() {
        GuestList guestList = GuestList.builder().build();

        guestList.setGuests(new ArrayList<>(List.of("Theodor", "Anette")));

        assertTrue(Files.exists(GuestList.PATH));
        try {
            assertThat(Files.readAllLines(GuestList.PATH)).containsExactly("Theodor", "Anette");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}