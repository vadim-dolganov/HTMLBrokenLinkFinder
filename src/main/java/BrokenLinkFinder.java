import javafx.util.Pair;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BrokenLinkFinder {
    public BrokenLinkFinder(List<String> links) {
        this.links = links;
    }

    private List<String> links;
    private final Integer THREADS_COUNT = 30;


    private Boolean isBrokenLink(Integer statusCode) {
        return ((statusCode < SuccessfulStatusCode.MIN_VALUE) || (statusCode > SuccessfulStatusCode.MAX_VALUE));
    }

    public List<String> getLinks() {
        return this.links;
    }

    public HashMap<String, Integer> getBrokenLinks() throws IOException, InterruptedException, ExecutionException {
        final ExecutorService service = Executors.newFixedThreadPool(THREADS_COUNT);
        HashMap<String, Integer> brokenLinks = new HashMap<String, Integer>();

        List<HttpCall> httpCalls = new ArrayList<HttpCall>();

        for (String link : links) {
            httpCalls.add(new HttpCall(link));
        }

        List<Future<Pair<String, Integer>>> httpCallsResult = service.invokeAll(httpCalls);
        service.shutdown();

        for (final Future<Pair<String, Integer>> callResult : httpCallsResult) {
            Pair<String, Integer> httpResult = callResult.get();
            if(isBrokenLink(httpResult.getValue())) {
                brokenLinks.put(httpResult.getKey(), httpResult.getValue());
            }
        }
        return brokenLinks;
    }
}
