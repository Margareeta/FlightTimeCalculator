public class Main {

    public static void main(String[] args) {
        TicketStatistics t = new TicketStatistics();
        System.out.println("Average flight duration is: " + t.averageDuration());
        System.out.println("Ninetieth percentile of flight duration is: " + t.procentile90th());
    }
}
