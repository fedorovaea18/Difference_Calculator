package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Differ {
    public static String generate(File file1, File file2) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(file1, new TypeReference<>() { });
        Map<String, Object> map2 = objectMapper.readValue(file2, new TypeReference<>() { });
        Map<String, Object> resultMap = new TreeMap<>();
        resultMap.putAll(map);
        resultMap.putAll(map2);
        String result = "{\n";
        for (String key: resultMap.keySet()) {
            if (map.containsKey(key)) {
                if (!map2.containsKey(key)) {
                    result += "- " + key + ": " + resultMap.get(key) + "\n";
                } else if (resultMap.get(key).equals(map.get(key))) {
                    result += "  " + key + ": " + resultMap.get(key) + "\n";
                } else {
                    result += "- " + key + ": " + map.get(key) + "\n";
                    result += "+ " + key + ": " + resultMap.get(key) + "\n";
                }
            } else if (map2.containsKey(key)) {
                result += "+ " + key + ": " + resultMap.get(key) + "\n";
            } else {
                result += "- " + key + ": " + resultMap.get(key) + "\n";
            }
        }
        result += "}";
        return result;
    }
}
