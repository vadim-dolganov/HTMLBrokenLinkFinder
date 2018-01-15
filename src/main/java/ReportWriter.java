import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ReportWriter {
    public ReportWriter(String filename) throws FileNotFoundException {
        this.writer = new PrintWriter(new File(filename));
    }
    public void append(HashMap<String, Integer> brokenLinks) {
        for(HashMap.Entry<String, Integer> brokenLink : brokenLinks.entrySet()) {
            StringBuilder builder = new StringBuilder();
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
}
