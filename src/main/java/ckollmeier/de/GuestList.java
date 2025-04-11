package ckollmeier.de;

import lombok.Builder;
import lombok.With;

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

    public List<String> getGuests() {
        return guests;
    }
}
