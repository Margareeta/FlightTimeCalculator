import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.time.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TicketParser {
    private static String path =
            "C:\\IdeaProjects\\FlightTimeCalculator\\src\\main\\resources\\tickets.json";

    @SneakyThrows
    public List<Ticket> getTicketList() {

        List<Ticket> res = new ArrayList<>();
        JsonNode ticketsNode = parseTickets();
        Iterator<JsonNode> elements = ticketsNode.elements();

        while (elements.hasNext()) {
            JsonNode nextNode = elements.next();

            LocalDate deptDate =
                    convertJsonNodeToDate(nextNode.get("departure_date"));
            LocalTime deptTime =
                    convertJsonNodeToTime(nextNode.get("departure_time"));

            LocalDateTime deptDateTime = LocalDateTime.of(deptDate, deptTime);

            LocalDate arrDate = convertJsonNodeToDate(nextNode.get("arrival_date"));
            LocalTime arrTime = convertJsonNodeToTime(nextNode.get("arrival_time"));

            LocalDateTime arrDateTime = LocalDateTime.of(arrDate, arrTime);

            Ticket t = new Ticket();
            t.setDepartureTime(ZonedDateTime.of(deptDateTime, ZoneId.of("Asia/Sakhalin")));
            t.setArrivalTime(ZonedDateTime.of(arrDateTime, ZoneId.of("Israel")));
            res.add(t);
        }
        return res;
    }

    private LocalDate convertJsonNodeToDate(JsonNode jsonNode) {
        String[] split = jsonNode.asText().split("\\.");
        return LocalDate.of(Integer.parseInt(split[2]), Integer.parseInt(split[1]), Integer.parseInt(split[0]));
    }

    private LocalTime convertJsonNodeToTime(JsonNode jsonNode) {
        String[] split = jsonNode.asText().split(":");
        return LocalTime.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
    }


    private ArrayNode parseTickets() throws IOException {

        return (ArrayNode) new ObjectMapper().readTree(new File(path)).get("tickets");
    }
}
