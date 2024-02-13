package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparisonTest {
    @Test
    public void testGenerate() throws Exception {
        String filePath1 = "src/test/resources/filepath3.json";
        String filePath2 = "src/test/resources/filepath4.json";
        String formatName = "stylish";

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

        String result = Differ.generate(filePath1, filePath2, formatName);

        assertEquals(expected, result);
    }

    @Test
    public void testDiffer() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("numbers1", "[1, 2, 3, 4]");
        map1.put("numbers2", "[5, 6, 7, 8]");
        map1.put("numbers3", "[9, 10, 11, 12]");

        Map<String, Object> map2 = new HashMap<>();
        map2.put("numbers1", "[2, 3, 4, 5]");
        map2.put("numbers2", "[5, 6, 7, 8]");
        map2.put("numbers4", "[13, 14, 15, 16]");

        List<Map<String, Object>> expected = new ArrayList<>();
        Map<String, Object> diffMap1 = new LinkedHashMap<>();
        diffMap1.put("key", "numbers1");
        diffMap1.put("oldValue", "[1, 2, 3, 4]");
        diffMap1.put("newValue", "[2, 3, 4, 5]");
        diffMap1.put("status", "updated");
        diffMap1.put("type", "updated");
        expected.add(diffMap1);

        Map<String, Object> diffMap2 = new LinkedHashMap<>();
        diffMap2.put("key", "numbers2");
        diffMap2.put("oldValue", "[5, 6, 7, 8]");
        diffMap2.put("status", "unchanged");
        diffMap2.put("type", "unchanged");
        expected.add(diffMap2);

        Map<String, Object> diffMap3 = new LinkedHashMap<>();
        diffMap3.put("key", "numbers3");
        diffMap3.put("oldValue", "[9, 10, 11, 12]");
        diffMap3.put("status", "removed");
        diffMap3.put("type", "removed");
        expected.add(diffMap3);

        Map<String, Object> diffMap4 = new LinkedHashMap<>();
        diffMap4.put("key", "numbers4");
        diffMap4.put("newValue", "[13, 14, 15, 16]");
        diffMap4.put("status", "added");
        diffMap4.put("type", "added");
        expected.add(diffMap4);

        List<Map<String, Object>> result = Differ.differ(map1, map2);

        assertEquals(expected, result);
    }


    @Test
    public void testGetData() {
        String filePath = "src/test/resources/filepath3.json";
        String expected = "filepath3.json";

        String result = Differ.getData(filePath);

        assertEquals(expected, result);
    }

    @Test
    public void testGetFileType() {
        String filePath = "filepath3.yml";
        String expected = "yml";

        String result = Differ.getFileType(filePath);

        assertEquals(expected, result);
    }
}
