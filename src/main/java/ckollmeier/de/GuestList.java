package ckollmeier.de;

import lombok.Builder;
import lombok.With;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a utility class for managing a guest list.
 * Provides functionality to add guests, retrieve guests, and persist the guest list to a file.
 */
@Builder
@With
public class GuestList {
    /**
     * The file path where the guest list is stored.
     */
    public static final Path PATH = Path.of("guests.txt");

    /**
     * The list of guests.
     */
    private final List<String> guests = new ArrayList<>();


    /**
     * Sets the guest list by writing the provided list of names to the file.
     *
     * @param guests the list of guest names to write
     * @throws RuntimeException if an error occurs while writing to the file
     */
    public void setGuests(final List<String> guests) {
        try {
            Files.write(PATH, guests);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retrieves the list of guests by reading from the file.
     * Clears and refreshes the current guest list with the contents of the file.
     *
     * @return the list of guests
     * @throws IOException      if an error occurs while reading the file
     * @throws RuntimeException if a non-I/O error occurs during retrieval
     */
    public List<String> getGuests() throws IOException {
        try {
            guests.clear();
            guests.addAll(Files.readAllLines(PATH));
        } catch (IOException io) {
            throw new IOException(io);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return guests;
    }

    /**
     * Adds a guest to the guest list if they are not already present.
     * Updates the persistent storage file after adding the guest.
     *
     * @param guest the name of the guest to add
     * @throws IOException if an error occurs while accessing or updating the file
     */
    public void addGuest(final String guest) throws IOException {
        getGuests();

        if (guests.stream().anyMatch(g -> g.equals(guest))) {
            return;
        }
        guests.add(guest);

        setGuests(guests);
    }
}
