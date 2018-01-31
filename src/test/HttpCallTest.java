
import static org.junit.Assert.*;

import RequestSender.HttpCall;
import RequestSender.Response;
import javafx.util.Pair;
import org.junit.Test;

import javafx.util.Pair;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.net.UnknownHostException;

public class HttpCallTest {
    @Test
    public void checkConstructor() {
        String url = "test.com";
        HttpCall httpCall = new HttpCall(url);
        assertEquals(httpCall.getUrl(), url);
    }

    @Test
    public void checkStatusCodeFromValidUrl() throws Exception {
        String url = "https://www.google.ru";
        Response response = new Response(url, 200, "OK");
        HttpCall httpCall = new HttpCall(url);
        assertEquals(response, httpCall.call());
    }

    @Test()
    public void checkStatusCodeFromNotExistHost() throws Exception {
        String url = "http://notexistsite.ru/";
        HttpCall httpCall = new HttpCall(url);
        assertEquals(httpCall.call(), new Response(url, 400, "Bad request"));
    }

    @Test()
    public void checkStatusCodeFromEmptyUrl() throws Exception {
        String url = "";
        HttpCall httpCall = new HttpCall(url);
        assertEquals(httpCall.call(), new Response(url, 400, "Bad request"));
    }
}
