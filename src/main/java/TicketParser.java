import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TicketParser {
    private static String path =
            "C:\\IdeaProjects\\FlightTimeCalculator\\src\\main\\resources\\tickets.json";

    public List<Ticket> getTicketList() {

        List<Ticket> res = new ArrayList<>();
        JsonNode ticketsNode = null;
        try {
            ticketsNode = parseTickets();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            t.setDepartureTime(ZonedDateTime.of(deptDateTime, ZoneId.of("Asia/Vladivostok")));
            t.setArrivalTime(ZonedDateTime.of(arrDateTime, ZoneId.of("Asia/Tel_Aviv")));
            res.add(t);
        }
        return res;
    }

    private LocalDate convertJsonNodeToDate(JsonNode jsonNode) {

        return LocalDate.parse(jsonNode.asText(), DateTimeFormatter.ofPattern("dd.MM.yy"));
    }

    private LocalTime convertJsonNodeToTime(JsonNode jsonNode) {

        return LocalTime.parse(jsonNode.asText(), DateTimeFormatter.ofPattern("H:mm"));
    }


    private ArrayNode parseTickets() throws IOException {

        return (ArrayNode) new ObjectMapper().readTree(new File(path)).get("tickets");
    }
}
