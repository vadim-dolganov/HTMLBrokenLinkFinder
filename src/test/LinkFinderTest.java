import org.junit.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class LinkFinderTest {

    @Test
    public void checkGetTagsWorkWithUrl() throws IOException {
        LinkFinder finder = new LinkFinder();
        List<String> returnValue = finder.getLinks("https://jsoup.org/apidocs/org/jsoup/select/Elements.html");
        assertFalse(returnValue.isEmpty());
    };

    @Test
    public void checkGetTagsFunctional() throws IOException {
        LinkFinder finder = new LinkFinder();
        List<String> returnValue = finder.getLinks("http://bebras.ru/bebras17/solving_issues/go/p");
        assertEquals(returnValue.get(0), "http://bebras.ru/assets/images/favicon.bebras.png?24");
        assertEquals(returnValue.get(1), "http://bebras.ru/assets/stylesheets/reset.css?24");
        assertEquals(returnValue.get(2), "http://bebras.ru/assets/stylesheets/bebras/main_with_menu.css?24");
        assertEquals(returnValue.get(3), "http://bebras.ru/assets/stylesheets/html-block.css?24");
        assertEquals(returnValue.get(4), "http://www.enable-javascript.com/ru/");
        assertEquals(returnValue.get(5), "http://bebras.ru/bebras17/login");
    }

    @Test
    public void checkGetTagsFunctionalWithFileProtocol() throws IOException {
        LinkFinder finder = new LinkFinder();
        List<String> returnValue = finder.getLinks("testFiles\\test.html");
        assertEquals(returnValue.get(0), "http://bebras.ru/assets/images/favicon.bebras.png?24");
        assertEquals(returnValue.get(1), "http://bebras.ru/assets/stylesheets/reset.css?24");
    }

    @Test
    public void getTagsCheckWithEmptyInput() throws IOException {
        LinkFinder finder = new LinkFinder();
        List<String> returnValue = finder.getLinks("testFiles\\empty.html");
        assertTrue(returnValue.isEmpty());
    }
}
