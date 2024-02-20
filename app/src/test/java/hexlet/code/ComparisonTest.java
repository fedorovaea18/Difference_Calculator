package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ComparisonTest {

    private static final String FILE_PATH_1 = "/file1.json";
    private static final String FILE_PATH_2 = "/file2.json";
    private static final String FILE_PATH_3 = "/file1.yml";
    private static final String FILE_PATH_4 = "/file2.yml";

    private static String expectedResultStylish;
    private static String expectedResultPlain;
    private static String expectedResultJson;

    private static String readFile(String testFile) throws Exception {
        Path testPath = Paths.get("./src/test/resources/fixtures/", testFile).toAbsolutePath().normalize();
        return Files.readString(testPath).trim();
    }

    @BeforeEach
    public void beforeEach() throws Exception {
        expectedResultStylish = readFile("expectedResultStylish.txt");
        expectedResultPlain = readFile("expectedResultPlain.txt");
        expectedResultJson = readFile("expectedResultJson.json");
    }

    @Test
    void testJSONtoStylish() throws Exception {
        String result = Differ.generate(FILE_PATH_1, FILE_PATH_2, "stylish");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultStylish);
    }

    @Test
    void testYMLtoStylish2() throws Exception {
        String result = Differ.generate(FILE_PATH_3, FILE_PATH_4, "stylish");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultStylish);
    }

    @Test
    void testJSONtoPlain() throws Exception {
        String result = Differ.generate(FILE_PATH_1, FILE_PATH_2, "plain");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultPlain);
    }

    @Test
    void testYMLtoPlain() throws Exception {
        String result = Differ.generate(FILE_PATH_3, FILE_PATH_4, "plain");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultPlain);
    }

    @Test
    void testJSONtoJson() throws Exception {
        String result = Differ.generate(FILE_PATH_1, FILE_PATH_2, "json");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultJson);
    }

    @Test
    void testYMLtoJson() throws Exception {
        String result = Differ.generate(FILE_PATH_3, FILE_PATH_4, "json");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultJson);
    }

    @Test
    void testGenerateDefault() throws Exception {
        String result1 = Differ.generate(FILE_PATH_1, FILE_PATH_2);
        String result2 = Differ.generate(FILE_PATH_3, FILE_PATH_4);
        assertThat(result1).isEqualToIgnoringWhitespace(expectedResultStylish);
        assertThat(result2).isEqualToIgnoringWhitespace(expectedResultStylish);
    }
}
