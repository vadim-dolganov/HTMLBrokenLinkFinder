import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ReportTest {
    @Test
    public void checkOutput() {
        try {
            final File expected = new File("testFiles\\expected.txt");
            ReportWriter reportWriter = new ReportWriter("testFiles\\output.txt");
            HashMap<String, Integer> links = new HashMap<String, Integer>();
            links.put("test.ru", 404);
            links.put("test.com", 500);
            reportWriter.append(links);
            File output = new File("testFiles\\output.txt");
            Scanner scanner1 = new Scanner(expected);
            Scanner scanner2 = new Scanner(output);
            while(scanner1.hasNextLine()) {
                assertEquals(scanner1.nextLine(), scanner2.nextLine());
            }
        }
        catch(Exception exc) {
            exc.getMessage();
        }
    }
}
