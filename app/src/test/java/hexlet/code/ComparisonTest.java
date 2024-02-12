package hexlet.code;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparisonTest {
    @Test
    public void testGenerate() throws IOException {
        String file1Path = "src/test/resources/filepath1.json";
        String file2Path = "src/test/resources/filepath2.json";

        String expected = "{\n    "
                 + "chars1: [a, b, c]\n  "
                 + "- chars2: [d, e, f]\n  "
                 + "+ chars2: false\n  "
                 + "- checked: false\n  "
                 + "+ checked: true\n  "
                 + "- default: null\n  "
                 + "+ default: [value1, value2]\n  "
                 + "- id: 45\n  "
                 + "+ id: null\n  "
                 + "- key1: value1\n  "
                 + "+ key2: value2\n  "
                 + "  numbers1: [1, 2, 3, 4]\n  "
                 + "- numbers2: [2, 3, 4, 5]\n  "
                 + "+ numbers2: [22, 33, 44, 55]\n  "
                 + "- numbers3: [3, 4, 5]\n  "
                 + "+ numbers4: [4, 5, 6]\n  "
                 + "+ obj1: {nestedKey=value, isNested=true}\n  "
                 + "- setting1: Some value\n  "
                 + "+ setting1: Another value\n  "
                 + "- setting2: 200\n  "
                 + "+ setting2: 300\n  "
                 + "- setting3: true\n  "
                 + "+ setting3: none\n"
                 + "}";

        String result = Differ.generate(file1Path, file2Path);

        assertEquals(expected, result);
    }

    @Test
    public void testGetFileExtension() {
        String filePath = "src/test/resources/filepath1.json";

        String result = Differ.getFileExtension(filePath);

        assertEquals("json", result);
    }
    @Test
    public void testCompareMaps() {
        Map<String, Object> data1 = new LinkedHashMap<>();
        data1.put("key1", "value1");
        data1.put("key2", "value2");

        Map<String, Object> data2 = new LinkedHashMap<>();
        data2.put("key1", "value1");
        data2.put("key2", "value3");

        List<Map<String, Object>> differences = new ArrayList<>();

        Differ.compareMaps(data1, data2, differences, "");
    }
}
