import hexlet.code.Differ;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestJson {
    @Test
    public void testDifferOne() throws IOException {
        String path1 = "./src/test/resources/json1.json";
        String path2 = "./src/test/resources/json2.json";
        String result = "{\n"
                + "- follow: false\n"
                + "  host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "+ timeout: 20\n"
                + "+ verbose: true\n"
                + "}";
        File file1 = new File(path1);
        File file2 = new File(path2);
        Assertions.assertEquals(Differ.generate(file1, file2), result);
    }

    @Test
    public void testDifferTwo() throws IOException {
        String path1 = "./src/test/resources/json1.json";
        String pathEmpty = "./src/test/resources/empty.json";
        String result = "{\n"
                + "- follow: false\n"
                + "- host: hexlet.io\n"
                + "- proxy: 123.234.53.22\n"
                + "- timeout: 50\n"
                + "}";
        File file1 = new File(path1);
        File fileEmpty = new File(pathEmpty);
        assertEquals(Differ.generate(file1, fileEmpty), result);
    }

    @Test
    public void testDifferThree() throws IOException {
        String path1 = "./src/test/resources/json1.json";
        String pathEmpty = "./src/test/resources/empty.json";
        String result = "{\n"
                + "+ follow: false\n"
                + "+ host: hexlet.io\n"
                + "+ proxy: 123.234.53.22\n"
                + "+ timeout: 50\n"
                + "}";
        File file1 = new File(path1);
        File fileEmpty = new File(pathEmpty);
        assertEquals(Differ.generate(fileEmpty, file1), result);
    }
}
