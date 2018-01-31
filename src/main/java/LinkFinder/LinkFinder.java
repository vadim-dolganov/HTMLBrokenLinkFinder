package LinkFinder;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LinkFinder {
    public LinkFinder() {
    }

    private Document document;

    private final Map<Attribute, String> ATTRIBUTES = new LinkedHashMap<Attribute, String>(){{
        put(Attribute.HREF, "href");
        put(Attribute.SRC, "src");
    }};

        private void openDocument(String link) throws IOException {
        try {
            final Integer TIMEOUT = 10000;
            Connection connection = Jsoup.connect(link)
                    .ignoreContentType(true)
                    .timeout(TIMEOUT)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .ignoreHttpErrors(true);
            document = connection.get();
        }
        catch(Exception exc) {
            document = Jsoup.parse(new File(link), null);
        }
    }

    private Map<Attribute, Elements> getTags() {
        Map<Attribute, Elements> mapTags = new LinkedHashMap<Attribute, Elements>();
        for(Map.Entry<Attribute, String> attribute : ATTRIBUTES.entrySet()) {
            mapTags.put(attribute.getKey(), document.getElementsByAttribute(attribute.getValue()));
        }
        return mapTags;
    }

    public List<String> getLinks(String htmlFileName) throws IOException {
        List<String> links = new ArrayList<String>();
        openDocument(htmlFileName);
        Map<Attribute, Elements> mapTags = getTags();
        for(Map.Entry<Attribute, Elements> entry : mapTags.entrySet()) {
            for(Element tag : entry.getValue()) {
                links.add(tag.attr("abs:" + ATTRIBUTES.get(entry.getKey())));
            }
        }
        return links;
    }
}
