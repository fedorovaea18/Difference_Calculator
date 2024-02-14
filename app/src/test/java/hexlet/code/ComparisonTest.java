package hexlet.code;

import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparisonTest {
    private final String path1 = "src/test/resources/filepath1.json";
    private final String path2 = "src/test/resources/filepath2.json";
    private final String format = "json";

    @Test
    public void testGenerate() throws Exception {
        // Read the expected result from the file
        String expected = Files.readString(getExpectedPath());

        // Generate the diff using the Differ.generate() method
        String diff = Differ.generate(path1, path2, format);

        // Compare the generated diff with the expected result
        assertEquals(expected, diff);
    }

    private Path getExpectedPath() {
        String expectedFilename = "expectedResult." + format;
        return Paths.get("src/test/resources/", expectedFilename).toAbsolutePath().normalize();
    }
}
