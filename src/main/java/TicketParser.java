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

    private ArrayNode ticketList() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File from = new File(path);
        ArrayNode tickets = (ArrayNode) objectMapper.readTree(from).get("tickets");
        return tickets;
    }

    @SneakyThrows
    public List<Ticket> tickets() {

        ArrayList<Ticket> res = new ArrayList<>();
        TicketParser ticketParser = new TicketParser();
        JsonNode ticketsNode = ticketParser.ticketList();
        Iterator<JsonNode> elements = ticketsNode.elements();

        while (elements.hasNext()) {
            JsonNode next = elements.next();

            String[] split = next.get("departure_date").asText().split("\\.");
            LocalDate deptDate = LocalDate.of(Integer.parseInt(split[2]), Integer.parseInt(split[1]), Integer.parseInt(split[0]));

            String[] split1 = next.get("departure_time").asText().split(":");
            LocalTime deptTime
                    = LocalTime.of(Integer.parseInt(split1[0]), Integer.parseInt(split1[1]));
            LocalDateTime deptDateTime = LocalDateTime.of(deptDate, deptTime);

            String[] split2 = next.get("arrival_date").asText().split("\\.");
            LocalDate arrDate =
                    LocalDate.of(Integer.parseInt(split2[2]), Integer.parseInt(split2[1]), Integer.parseInt(split2[0]));

            String[] split3 = next.get("arrival_time").asText().split(":");
            LocalTime arrTime =
                    LocalTime.of(Integer.parseInt(split3[0]), Integer.parseInt(split3[1]));
            LocalDateTime arrDateTime = LocalDateTime.of(arrDate, arrTime);

            Ticket t = new Ticket();
            t.setDepartureTime(ZonedDateTime.of(deptDateTime, ZoneId.of("Asia/Sakhalin")));
            t.setArrivalTime(ZonedDateTime.of(arrDateTime, ZoneId.of("Israel")));
            res.add(t);
        }
        return res;
    }
}
