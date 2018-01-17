
import static org.junit.Assert.*;

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
        Pair<String, Integer> response = new Pair<String, Integer>(url, 200);
        HttpCall httpCall = new HttpCall(url);
        assertEquals(response, httpCall.call());
    }

    @Test(expected = UnknownHostException.class)
    public void checkStatusCodeFromNotExistHost() throws Exception {
        String url = "http://notexistsite.ru/";
        HttpCall httpCall = new HttpCall(url);
        httpCall.call();
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkStatusCodeFromEmptyUrl() throws Exception {
        String url = "";
        HttpCall httpCall = new HttpCall(url);
        httpCall.call();
    }

    @Test
    public void check404StatusCode() throws Exception {
        String url = "http://google.com/test/";
        Pair<String, Integer> response = new Pair<String, Integer>(url, 404);
        HttpCall httpCall = new HttpCall(url);
        assertEquals(response, httpCall.call());
    }

}
