package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ComparisonTest {

    private static final String FILE_PATH_1 = "./src/test/resources/fixtures/file1.json";
    private static final String FILE_PATH_2 = "./src/test/resources/fixtures/file2.json";
    private static final String FILE_PATH_3 = "./src/test/resources/fixtures/file1.yml";
    private static final String FILE_PATH_4 = "./src/test/resources/fixtures/file2.yml";

    private static String expectedResultStylish;
    private static String expectedResultPlain;
    private static String expectedResultJson;

    @BeforeEach
    public final void beforeEach() throws Exception {
        expectedResultStylish = Differ.getData("./src/test/resources/fixtures/expectedResultStylish.txt");
        expectedResultPlain = Differ.getData("./src/test/resources/fixtures/expectedResultPlain.txt");
        expectedResultJson = Differ.getData("./src/test/resources/fixtures/expectedResultJson.json");
    }

    @Test
    public void testJSONtoStylish() throws Exception {
        String result = Differ.generate(FILE_PATH_1, FILE_PATH_2, "stylish");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultStylish);
    }

    @Test
    public void testYMLtoStylish2() throws Exception {
        String result = Differ.generate(FILE_PATH_3, FILE_PATH_4, "stylish");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultStylish);
    }

    @Test
    public void testJSONtoPlain() throws Exception {
        String result = Differ.generate(FILE_PATH_1, FILE_PATH_2, "plain");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultPlain);
    }

    @Test
    public void testYMLtoPlain() throws Exception {
        String result = Differ.generate(FILE_PATH_3, FILE_PATH_4, "plain");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultPlain);
    }

    @Test
    public void testJSONtoJson() throws Exception {
        String result = Differ.generate(FILE_PATH_1, FILE_PATH_2, "json");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultJson);
    }

    @Test
    public void testYMLtoJson() throws Exception {
        String result = Differ.generate(FILE_PATH_3, FILE_PATH_4, "json");
        assertThat(result).isEqualToIgnoringWhitespace(expectedResultJson);
    }

    @Test
    public void testGenerateDefault() throws Exception {
        String result1 = Differ.generate(FILE_PATH_1, FILE_PATH_2);
        String result2 = Differ.generate(FILE_PATH_3, FILE_PATH_4);
        assertThat(result1).isEqualToIgnoringWhitespace(expectedResultStylish);
        assertThat(result2).isEqualToIgnoringWhitespace(expectedResultStylish);
    }

    @Test
    public void testGenerate() throws Exception {
        String result1 = Differ.generate(FILE_PATH_1, FILE_PATH_2);
        String result2 = Differ.generate(FILE_PATH_3, FILE_PATH_4);
        assertNotNull(result1);
        assertNotNull(result2);
        assertFalse(result1.isEmpty());
        assertFalse(result2.isEmpty());
    }

    @Test
    public void testGetData() throws IOException {
        String actualContent = Differ.getData(FILE_PATH_1);
        String expectedContent = Files.readString(Paths.get(FILE_PATH_3));
        assertThat(expectedContent).isEqualToIgnoringWhitespace(actualContent);
    }

    @Test
    public void testGetFileType() {
        String expectedType = "json";
        String actualType = Differ.getFileType(FILE_PATH_1);
        assertEquals(expectedType, actualType);
    }
}
