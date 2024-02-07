package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

class Differ {
    public static String generate(File file1, File file2) throws IOException {
        Map<String, Object> map1 = Parser.parse(file1);
        Map<String, Object> map2 = Parser.parse(file2);
        Map<String, Object> resultMap = new TreeMap<>(map1);
        resultMap.putAll(map2);
        StringBuilder result = new StringBuilder("{\n");

        for (String key : resultMap.keySet()) {
            if (map1.containsKey(key)) {
                if (!map2.containsKey(key)) {
                    result.append("- ").append(key).append(": ").append(map1.get(key)).append("\n");
                } else if (resultMap.get(key).equals(map1.get(key))) {
                    result.append("  ").append(key).append(": ").append(resultMap.get(key)).append("\n");
                } else {
                    result.append("- ").append(key).append(": ").append(map1.get(key)).append("\n");
                    result.append("+ ").append(key).append(": ").append(resultMap.get(key)).append("\n");
                }
            } else {
                result.append("+ ").append(key).append(": ").append(resultMap.get(key)).append("\n");
            }
        }

        result.append("}");
        return result.toString();
    }
}
