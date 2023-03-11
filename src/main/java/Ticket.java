import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {
    private LocalDateTime departure;
    private LocalDateTime arrival;
    //разница во времени между ТА и Владивостоком
    private final int TIME_DIFFERENCE = 8;

    private List<Ticket> tickets(){


        return new ArrayList<>();
    }


    public List<Duration> durations() {
        List<Ticket> tickets = tickets();
        List<Duration> res = new ArrayList<>();
        for (Ticket t : tickets) {
            Duration flightDuration = Duration.
                    between(t.getDeparture().minusHours(8), t.getArrival());
            res.add(flightDuration);
        }
        Collections.sort(res);
        return res;
    }
}
