import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class App {
    public static void printLinks(List<String> links) {
        System.out.println("LINKS");
        for(String link : links) {
            System.out.println(link);
        }
    }
    public static void printBrokenLinks(Map<String, Integer> brokenLinks) {
        System.out.println("BROKEN-LINKS");
        for(Map.Entry<String, Integer> brokenLink : brokenLinks.entrySet()) {
            System.out.println(brokenLink.getKey());
        }
    }
    public static void main(String[] args) {
        try {
            LinkFinder linkFinder = new LinkFinder();
            List<String> links = linkFinder.getLinks("http://testingcourse.ru/docs/site-status-check");
            BrokenLinkFinder brokenLinkFinder = new BrokenLinkFinder(links);
            Map<String, Integer> brokenLinks = brokenLinkFinder.getBrokenLinks();
            printLinks(links);
            printBrokenLinks(brokenLinks);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
