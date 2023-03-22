import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TicketStatistics {

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

    public Duration averageDuration() {

        long averageInSeconds =
                (long) durations().stream().mapToLong(d -> d.getSeconds()).average().getAsDouble();

        return Duration.ofSeconds(averageInSeconds);
    }


    public Duration procentile90th() {
        int index = (int) (durations().size() * 0.9);
        if (index > 0) {
            index--;
        }
        return durations().get(index);
    }
}
