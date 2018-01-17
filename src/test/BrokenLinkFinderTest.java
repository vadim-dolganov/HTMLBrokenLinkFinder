import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BrokenLinkFinderTest {
    @Test
    public void checkConstructor() {
        List<String> links = new ArrayList<String>();
        links.add("abc");
        links.add("def");
        BrokenLinkFinder finder = new BrokenLinkFinder(links);
        assertEquals(finder.getLinks(), links);
    }

    @Test
    public void checkGetBrokenLinksWithEmptyList() throws InterruptedException, ExecutionException, IOException {
        List<String> links = new ArrayList<String>();
        BrokenLinkFinder finder = new BrokenLinkFinder(links);
        assertTrue(finder.getBrokenLinks().isEmpty());
    }

    @Test
    public void checkGetBrokenLinks() throws InterruptedException, ExecutionException, IOException {
        List<String> links = new ArrayList<String>();
        links.add("https://jsoup.org/apidocs/org/jsoup/Jsoup.html");
        links.add("https://jsoup.org/apidocs/org/jsoup/hello.html");
        BrokenLinkFinder finder = new BrokenLinkFinder(links);
        HashMap<String, Integer> expected = new HashMap<String, Integer>();
        expected.put("https://jsoup.org/apidocs/org/jsoup/hello.html", 404);
        assertEquals(finder.getBrokenLinks(), expected );
    }
}
