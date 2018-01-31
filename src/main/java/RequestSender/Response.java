package RequestSender;

public class Response {
    public Response(String url, Integer statusCode, String statusMessage) {
        this.url = url;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getUrl() {
        return url;
    }

    private String url;
    private String statusMessage;
    private Integer statusCode;

    @Override
    public boolean equals(Object arg) {
        Response result = (Response)arg;
        return (this.statusCode.equals(result.getStatusCode()) && this.statusMessage.equals(result.getStatusMessage()) && this.url.equals(result.getUrl()));
    }
}
