package wave.practicaltest.unit.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import wave.practicaltest.unit.beverage.Beverage;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Order {

    private final LocalDateTime orderDateTime;
    private final List<Beverage> beverages;
}
