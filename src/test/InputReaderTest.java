import org.junit.Test;

public class InputReaderTest {
    @Test(expected = IllegalArgumentException.class)
    public void checkEmptyInput() {
        String[] input = new String[0];
        InputReader reader = new InputReader(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkInvalidInput() {
        String[] input = new String[1];
        input[0] = "hello";
        InputReader reader = new InputReader(input);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkNotFullInput() {
        String[] input = new String[2];
        input[0] = "--files";
        input[1] = "input.txt";
        InputReader reader = new InputReader(input);
    }

    @Test
    public void checkFullInput() {
        String[] input = new String[4];
        input[0] = "--files";
        input[1] = "input.txt";
        input[2] = "--out";
        input[3] = "output.txt";
        InputReader reader = new InputReader(input);
    }
}
