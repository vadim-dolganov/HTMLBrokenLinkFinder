import java.util.ArrayList;
import java.util.List;

public class App {
    public static void printLinks(List<String> links) {
        for(String link : links) {
            System.out.println(link);
        }
    }
    public static void main(String[] args) {
        try {
            LinkFinder linkFinder = new LinkFinder();
            List<String> links = linkFinder.getLinks("index.html");
            printLinks(links);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
