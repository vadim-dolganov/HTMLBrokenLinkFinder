package RequestSender;

import javafx.util.Pair;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.Callable;

public class HttpCall implements Callable<Response> {
    private String url;

    public HttpCall(String url) {
        this.url = url;
    }

    public String getUrl() { return this.url; }

    public Pair<Integer, String> getStatus() throws IOException {
        final Integer MAX_TIMEOUT = 10000;
        try {
            Connection.Response response = Jsoup.connect(this.url)
                    .ignoreContentType(true)
                    .ignoreHttpErrors(true)
                    .timeout(MAX_TIMEOUT)
                    .execute();
            return new Pair<Integer, String>(response.statusCode(), response.statusMessage());
        }
        catch (SocketTimeoutException exception) {
            return new Pair<Integer, String>(408, "Connection timed out");
        }
        catch(Exception exception) {
            return new Pair<Integer, String>(400, "Bad request");
        }

    }

    public Response call() throws Exception {
        Pair<Integer, String> status = getStatus();
        return new Response(url, status.getKey(), status.getValue());
    }
}
