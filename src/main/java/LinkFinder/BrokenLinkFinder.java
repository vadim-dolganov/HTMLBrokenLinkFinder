package LinkFinder;
import RequestSender.Response;
import RequestSender.HttpCall;
import RequestSender.Response;
import RequestSender.SuccessfulStatusCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    public List<Response> getBrokenLinks() throws IOException, InterruptedException, ExecutionException {
        final ExecutorService service = Executors.newFixedThreadPool(THREADS_COUNT);
        List<Response> brokenLinks = new ArrayList<Response>();

        List<HttpCall> httpCalls = new ArrayList<HttpCall>();

        for (String link : links) {
            httpCalls.add(new HttpCall(link));
        }

        List<Future<Response>> httpCallsResult = service.invokeAll(httpCalls);
        service.shutdown();

        for (final Future<Response> callResult : httpCallsResult) {
            Response httpResult = callResult.get();
            if(isBrokenLink(httpResult.getStatusCode())) {
                brokenLinks.add(httpResult);
            }
        }
        return brokenLinks;
    }
}