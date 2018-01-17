import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

enum READ_MODE {
    NOTHING,
    FILES,
    OUT
}

public class InputReader {
    public InputReader(String args[]) {
        READ_MODE currentMode = READ_MODE.NOTHING;
        Boolean isNotFullInput = true;
        for (String element : args) {
            if (element.equals("--files")) {
                currentMode = READ_MODE.FILES;
                continue;
            }
            else if (element.equals("--out")) {
                currentMode = READ_MODE.OUT;
                continue;
            }
            switch (currentMode) {
                case FILES: {
                    this.pages.add(element);
                    continue;
                }
                case OUT: {
                    this.outputFile = element;
                    isNotFullInput = false;
                    continue;
                }
                case NOTHING: {
                    throw new IllegalArgumentException("Invalid input string");
                }
            }
        }
        if (isNotFullInput) {
            throw new IllegalArgumentException("Not full input");
        }
    }
    public List<String> getPages() {
        return this.pages;
    }
    public String getOutputFile() {
        return outputFile;
    }
    private List<String> pages = new ArrayList<String>();
    private String outputFile;
}
