package ckollmeier.de;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.ArrayList;
import java.util.List;

@Builder
@With
public class GuestList {
    private final List<String> guests = new ArrayList<>();

    public void setGuests(List<String> guests) {
        this.guests.clear();
        this.guests.addAll(guests);
    }

    public List<String> getGuests() {
        return guests;
    }
}
