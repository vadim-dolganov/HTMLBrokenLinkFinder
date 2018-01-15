import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;

public class App {
    private static HashMap<String, Integer> getBrokenLinks(List<String> pages) throws Exception {
        HashMap<String, Integer> brokenLinks = new HashMap<String, Integer>();
        LinkFinder linkFinder = new LinkFinder();
        for (String page : pages) {
            List<String> links = linkFinder.getLinks(page);
            BrokenLinkFinder brokenLinkFinder = new BrokenLinkFinder(links);
            brokenLinks.putAll(brokenLinkFinder.getBrokenLinks());
        }
        return brokenLinks;
    }

    private static void printBrokenLinks(HashMap<String, Integer> brokenLinks, String outputFile) throws FileNotFoundException {
        ReportWriter writer = new ReportWriter(outputFile);
        writer.append(brokenLinks);
    }

    public static void main(String[] args) {
        try {
            InputReader reader = new InputReader(args);
            HashMap<String, Integer> brokenLinks = getBrokenLinks(reader.getPages());
            printBrokenLinks(brokenLinks, reader.getOutputFile());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
