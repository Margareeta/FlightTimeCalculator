import lombok.SneakyThrows;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketStatistics {
    private  final List<Duration> durations;

    public TicketStatistics() {
        durations = durations();
    }

    @SneakyThrows
    private List<Duration> durations() {
        TicketParser parser = new TicketParser();
        List<Ticket> tickets = parser.getTicketList();
        List<Duration> res = new ArrayList<>();
        for (Ticket t : tickets) {
            Duration flightDuration = Duration.
                    between(t.getDepartureTime(), t.getArrivalTime());
            res.add(flightDuration);
        }
        Collections.sort(res);
        return res;
    }

    @SneakyThrows
    public Duration averageDuration()  {
        List<Long> durationsInSeconds = new ArrayList<>();
        durations.stream().forEach(d -> durationsInSeconds.add(d.getSeconds()));
        long averageInSeconds =
                (long) durationsInSeconds.stream().mapToLong(d -> d).average().getAsDouble();

        return Duration.ofSeconds(averageInSeconds);
    }

    @SneakyThrows
    public Duration procentile90th()  {
        return durations.get((int) Math.ceil(durations.size() * 0.9));
    }
}
