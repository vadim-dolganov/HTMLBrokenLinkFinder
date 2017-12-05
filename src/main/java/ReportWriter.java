import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;

public class ReportWriter {
    public ReportWriter(String filename) throws FileNotFoundException {
        this.writer = new PrintWriter(new File(filename));
        this.builder = new StringBuilder();
    }
    public void append(Map<String, Integer> brokenLinks) {
        for(Map.Entry<String, Integer> brokenLink : brokenLinks.entrySet()) {
          //  System.out.println(brokenLink.getKey() + " :" + brokenLink.getValue());
            builder.append(brokenLink.getKey());
            builder.append(';');
            builder.append(brokenLink.getValue());
            builder.append(';');
            builder.append("Code");
            builder.append('\n');
            this.writer.write(builder.toString());
            this.writer.flush();
        }
    }
    protected void finalize() {
        this.writer.close();
    }
    private PrintWriter writer;
    private StringBuilder builder;
}
