package ckollmeier.de;

import lombok.Builder;
import lombok.With;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Builder
@With
public class GuestList {
    public static final Path PATH = Path.of("guests.txt");

    private final List<String> guests = new ArrayList<>();

    public void setGuests(List<String> guests) {
        try {
            Files.write(PATH, guests);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> getGuests() throws IOException {
        try {
            guests.addAll(Files.readAllLines(PATH));
        } catch (IOException io) {
            throw new IOException(io);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return guests;
    }

    public void addGuest(String guest) {
    }
}
