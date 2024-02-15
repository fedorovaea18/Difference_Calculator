package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ComparisonTest {

    private static final String FILE_PATH_1 = "./filepath3.";
    private static final String FILE_PATH_2 = "./filepath4.";

    private static String expectedResultStylish;
    private static String expectedResultPlain;
    private static String expectedResultJson;

    private static Path getAbsolutePath(String testResultFile) {
        return Paths.get("./src/test/resources/", testResultFile).toAbsolutePath().normalize();
    }

    private static String readFile(String testFile) throws Exception {
        Path testPath = getAbsolutePath(testFile);
        return Files.readString(testPath).trim();
    }

    public ComparisonTest() throws Exception {
        expectedResultStylish = readFile("expectedResultStylish.json");
        expectedResultPlain = readFile("expectedResultPlain.json");
        expectedResultJson = readFile("expectedResultJson.json");
    }

    @Test
    void testDefault() throws Exception {
        String result = Differ.generate(FILE_PATH_1 + "json", FILE_PATH_2 + "json", "stylish");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultStylish);
    }

    @Test
    void testStylish() throws Exception {
        String result = Differ.generate(FILE_PATH_1 + "json", FILE_PATH_2 + "json", "stylish");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultStylish);
    }

    @Test
    void testPlain() throws Exception {
        String result = Differ.generate(FILE_PATH_1 + "json", FILE_PATH_2 + "json", "plain");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultPlain);
    }

    @Test
    void testJson() throws Exception {
        String result = Differ.generate(FILE_PATH_1 + "json", FILE_PATH_2 + "json", "json");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultJson);
    }
}
