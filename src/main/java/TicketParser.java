import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class TicketParser {
    private static   String path =
            "C:\\IdeaProjects\\FlightTimeCalculator\\src\\main\\resources\\tickets.json";

   public static JsonNode ticketList() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File from = new File(path);
        return   objectMapper.readTree(from);
    }
}
