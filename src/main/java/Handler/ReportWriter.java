package Handler;

import RequestSender.Response;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class ReportWriter {
    public ReportWriter(String filename) throws FileNotFoundException {
        this.writer = new PrintWriter(new File(filename));
    }
    public void append(List<Response> brokenLinks) {
        for(Response brokenLink : brokenLinks) {
            StringBuilder builder = new StringBuilder();
            builder.append(brokenLink.getUrl());
            builder.append(';');
            builder.append(brokenLink.getStatusCode());
            builder.append(';');
            builder.append(brokenLink.getStatusMessage());
            builder.append('\n');
            this.writer.write(builder.toString());
            this.writer.flush();
        }
        finalize();
    }
    protected void finalize() {
        this.writer.close();
    }
    private PrintWriter writer;
}
