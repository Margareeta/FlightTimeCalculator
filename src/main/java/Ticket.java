import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {
    private ZonedDateTime departure;
    private ZonedDateTime arrival;



    private List<Ticket> tickets(){return new ArrayList<>();}

    public List<Duration> durations() {
        List<Ticket> tickets = tickets();
        List<Duration> res = new ArrayList<>();
        for (Ticket t : tickets) {
            Duration flightDuration = Duration.
                    between(t.getDeparture(), t.getArrival());
            res.add(flightDuration);
        }
        Collections.sort(res);
        return res;
    }
    public Duration averageDuration(){
        List<Duration> durations = durations();
        List<Long> durationsInSeconds = new ArrayList<>();
        durations.stream().forEach(d -> durationsInSeconds.add(d.getSeconds()));
        long averageInSeconds =
                (long)durationsInSeconds.stream().mapToLong(d -> d).average().getAsDouble();

        return Duration.ofSeconds(averageInSeconds) ;
    }

    public Duration procentile90th(){
        List<Duration> durations = durations();
        return  durations.get((int) Math.ceil(durations.size() * 0.9));
    }
}
