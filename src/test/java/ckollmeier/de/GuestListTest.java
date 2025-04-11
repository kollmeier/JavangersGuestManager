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
    void getGuests_shouldBeEmptyInitially() throws IOException {
        GuestList guestList = GuestList.builder().build();

        guestList.setGuests(new ArrayList<>());

        assertTrue(guestList.getGuests().isEmpty());
    }

    @Test
    void getGuests_shouldReadSameGuestsAsWrittenBefore() throws IOException {
        GuestList guestList = GuestList.builder().build();

        guestList.setGuests(new ArrayList<>(List.of("Karl", "Ute")));

        assertEquals(List.of("Karl", "Ute"), guestList.getGuests());
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
    void getGuests_shouldReadFromFileSystem() throws IOException {
        GuestList guestList = GuestList.builder().build();

        List<String> guests =  new ArrayList<>(List.of("Stephan", "Max"));

        Files.write(GuestList.PATH, guests);

        assertEquals(guests, guestList.getGuests());
    }

    @Test
    void getGuests_shouldThrowIoExceptionWhenFileDoesNotExist() throws IOException {
        GuestList guestList = GuestList.builder().build();

        Files.deleteIfExists(GuestList.PATH);
        Files.deleteIfExists(GuestList.PATH);

        assertThrows(IOException.class, guestList::getGuests);
    }

    @Test
    void addGuest_shouldAddGuestToGuestList() throws IOException {
        GuestList guestList = GuestList.builder().build();

        guestList.setGuests(new ArrayList<>(List.of("Karl", "Uschi")));

        guestList.addGuest("Marleen");

        assertEquals(List.of("Karl", "Uschi", "Marleen"), guestList.getGuests());
    }

    @Test
    void addGuest_shouldNotAddDuplicateGuestToGuestList() throws IOException {
        GuestList guestList = GuestList.builder().build();

        guestList.setGuests(new ArrayList<>(List.of("Karl", "Uschi")));

        guestList.addGuest("Karl");

        assertEquals(List.of("Karl", "Uschi"), guestList.getGuests());
    }

    @Test
    void addGuest_shouldThrowIOExceptionWhenGuestNotExists() {
        GuestList guestList = GuestList.builder().build();

        try {
            Files.deleteIfExists(GuestList.PATH);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        assertThrows(IOException.class, () -> guestList.addGuest("Karl"));
    }
}