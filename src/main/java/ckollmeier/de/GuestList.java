package ckollmeier.de;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

@Builder
@With
@NoArgsConstructor
public class GuestList {
    public void setGuests(List<String> guests) {
    }

    public List<String> getGuests() {
        return null;
    }
}
