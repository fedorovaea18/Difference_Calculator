package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static String generate(Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder result = new StringBuilder("{\n");
        Set<String> commonKeys = new TreeSet<>();
        commonKeys.addAll(map1.keySet());
        commonKeys.addAll(map2.keySet());

        for (var key : commonKeys) {
            String string1 = key + ": " + map1.get(key) + "\n";
            String string2 = key + ": " + map2.get(key) + "\n";
            if (!map1.containsKey(key)) {
                result.append("  + ").append(string2);
            } else if (!map2.containsKey(key)) {
                result.append("  - ").append(string1);
            } else {
                if (map1.get(key).equals(map2.get(key))) {
                    result.append("    ").append(string1);
                } else {
                    result.append("  - ").append(string1);
                    result.append("  + ").append(string2);
                }
            }
        }
        return result.append("}").toString().trim();
    }
}
