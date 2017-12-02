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

enum ATTRIBUTE {
    SRC,
    HREF
}
public class LinkFinder {
    public LinkFinder() {

    }
    private Document document;
    private final Map<ATTRIBUTE, String> ATTRIBUTES = new LinkedHashMap<ATTRIBUTE, String>(){{
        put(ATTRIBUTE.HREF, "href");
        put(ATTRIBUTE.SRC, "src");
    }};

    private void openDocument(String htmlFileName) throws IOException {
        File file = new File(htmlFileName);
        document = Jsoup.parse(file, null);
    }

    private Map<ATTRIBUTE, Elements> getTags() {
        Map<ATTRIBUTE, Elements> mapTags = new LinkedHashMap<ATTRIBUTE, Elements>();
        for(Map.Entry<ATTRIBUTE, String> attribute : ATTRIBUTES.entrySet()) {
            mapTags.put(attribute.getKey(), document.getElementsByAttribute(attribute.getValue()));
        }
        return mapTags;
    }

    public List<String> getLinks(String htmlFileName) throws IOException {
        List<String> links = new ArrayList<String>();
        openDocument(htmlFileName);
        Map<ATTRIBUTE, Elements> mapTags = getTags();
        for(Map.Entry<ATTRIBUTE, Elements> entry : mapTags.entrySet()) {
            for(Element tag : entry.getValue()) {
                links.add(tag.attr(ATTRIBUTES.get(entry.getKey())));
            }
        }
        return links;
    }
}
