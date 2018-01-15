import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BrokenLinkFinder {
    public BrokenLinkFinder(List<String> links) {
        this.links = links;
    }

    private List<String> links;

    private static Integer getStatusCode(String url) throws IOException {
        Response response = Jsoup.connect(url)
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .followRedirects(false)
                .execute();
        return response.statusCode();
    }

    private Boolean isBrokenLink(Integer statusCode) {
        return ((statusCode < SuccessfulStatusCode.MIN_VALUE) || (statusCode > SuccessfulStatusCode.MAX_VALUE));
    }

    public HashMap<String, Integer> getBrokenLinks() throws IOException {
        HashMap<String, Integer> brokenLinks = new HashMap<String, Integer>();
        for (String link : links) {
            Integer statusCode = getStatusCode(link);
            if (isBrokenLink(statusCode)) {
                brokenLinks.put(link, statusCode);
            }
        }
        return brokenLinks;
    }
}
