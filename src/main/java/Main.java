import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalUnit;

public class Main {

    public static void main(String[] args) {
        ZonedDateTime dpt =
                ZonedDateTime.of(LocalDateTime.of(2018, 05, 12, 16, 29), ZoneId.of("Asia/Sakhalin"));
        ZonedDateTime arrv =
                ZonedDateTime.of(LocalDateTime.of(2018, 05, 12, 22, 10), ZoneId.of("Israel"));
        Duration d = Duration.between(dpt, arrv);
        ZonedDateTime dpt1 =
                ZonedDateTime.of(LocalDateTime.of(2018, 05, 12, 17, 20), ZoneId.of("Asia/Sakhalin"));
        ZonedDateTime arrv1 =
                ZonedDateTime.of(LocalDateTime.of(2018, 05, 12, 23, 50), ZoneId.of("Israel"));
        Duration d1 = Duration.between(dpt1, arrv1);
        Long  avg = (d.getSeconds() + d1.getSeconds())/2;
        Duration avgDur = Duration.ofSeconds(avg);


        System.out.println(avgDur);





    }
}
