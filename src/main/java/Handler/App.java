package Handler;

import RequestSender.Response;
import LinkFinder.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static List<Response> getBrokenLinks(List<String> pages) throws Exception {
        List<Response> brokenLinks = new ArrayList<Response>();
        LinkFinder linkFinder = new LinkFinder();
        for (String page : pages) {
            List<String> links = linkFinder.getLinks(page);
            BrokenLinkFinder brokenLinkFinder = new BrokenLinkFinder(links);
            brokenLinks.addAll(brokenLinkFinder.getBrokenLinks()); }
        return brokenLinks;
    }

    private static void printBrokenLinks(List<Response> brokenLinks, String outputFile) throws FileNotFoundException {
        ReportWriter writer = new ReportWriter(outputFile);
        writer.append(brokenLinks);
    }

    public static void main(String[] args) {
        try {
            InputReader reader = new InputReader(args);
            List<Response> brokenLinks = getBrokenLinks(reader.getPages());
            printBrokenLinks(brokenLinks, reader.getOutputFile());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
