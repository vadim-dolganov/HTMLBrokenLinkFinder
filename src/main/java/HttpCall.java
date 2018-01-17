import javafx.util.Pair;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.concurrent.Callable;

public class HttpCall implements Callable<Pair<String, Integer>> {
    private String url;

    public HttpCall(String url) {
        this.url = url;
    }

    public String getUrl() { return this.url; }

    private Integer getStatusCode() throws IOException {
        final Integer MAX_TIMEOUT = 10000;
        Connection.Response response = Jsoup.connect(this.url)
                .ignoreContentType(true)
                .ignoreHttpErrors(true)
                .timeout(MAX_TIMEOUT)
                .execute();
        return response.statusCode();
    }

    public Pair<String, Integer> call() throws Exception {
        return new Pair<String, Integer>(url, getStatusCode());
    }
}
