package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.Set;
import java.util.Objects;
import java.util.LinkedHashMap;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatName) throws Exception {
        String data1 = getData(filePath1);
        String data2 = getData(filePath2);

        String fileType1 = getFileType(filePath1);
        String fileType2 = getFileType(filePath2);

        Map<String, Object> map1 = Parser.parse(data1, fileType1);
        Map<String, Object> map2 = Parser.parse(data2, fileType2);

        List<Map<String, Object>> result = comparator(map1, map2);
        return Formatter.formatName(result, formatName);
    }

    public static List<Map<String, Object>> comparator(Map<String, Object> map1, Map<String, Object> map2) {
        List<Map<String, Object>> result = new ArrayList<>();
        Set<String> keysSet = new TreeSet<>(map1.keySet());
        keysSet.addAll(map2.keySet());
        for (String key : keysSet) {
            Map<String, Object> map = new LinkedHashMap<>();
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("type", "removed");
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                map.put("key", key);
                map.put("newValue", map2.get(key));
                map.put("type", "added");
            } else if (!Objects.equals(map1.get(key), map2.get(key))) {
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("newValue", map2.get(key));
                map.put("type", "updated");
            } else {
                map.put("key", key);
                map.put("oldValue", map1.get(key));
                map.put("type", "unchanged");
            }
            result.add(map);
        }
        return result;
    }

    public static String getData(String filepath) {
        Path path = Paths.get(filepath);
        return path.getFileName().toString();
    }

    public static String getFileType(String filepath) {
        String fileName = getData(filepath);
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index + 1);
    }
}
