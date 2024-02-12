package hexlet.code.formatter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Stylish {
    private static final String ADDED_SYMBOL = "  + ";
    private static final String REMOVED_SYMBOL = "  - ";
    private static final String UNCHANGED_SYMBOL = "    ";

    public static String stylish(List<Map<String, Object>> differences) {
        List<String> keys = new ArrayList<>();

        for (Map<String, Object> diff : differences) {
            String key = diff.get("key").toString();
            if (!keys.contains(key)) {
                keys.add(key);
            }
        }
        Collections.sort(keys);

        StringBuilder result = new StringBuilder("{\n");

        for (String key : keys) {
            for (Map<String, Object> diff : differences) {
                if (diff.get("key").toString().equals(key)) {
                    String type = diff.get("type").toString();

                    switch (type) {
                        case "changed":
                            Object oldValue = diff.get("oldValue");
                            Object newValue = diff.get("newValue");

                            result.append(REMOVED_SYMBOL).append(key).append(": ").append(oldValue).append("\n");
                            result.append(ADDED_SYMBOL).append(key).append(": ").append(newValue).append("\n");
                            break;

                        case "added":
                            result.append(ADDED_SYMBOL).append(key).append(": ").append(diff.get("value")).append("\n");
                            break;

                        case "removed":
                            result.append(REMOVED_SYMBOL)
                                    .append(key).append(": ")
                                    .append(diff.get("value"))
                                    .append("\n");
                            break;

                        case "unchanged":
                            result.append(UNCHANGED_SYMBOL)
                                    .append(key).append(": ")
                                    .append(diff.get("value"))
                                    .append("\n");
                            break;
                        default:
                            throw new Error("Unknown type");
                    }
                }
            }
        }

        return result.append("}").toString().trim();
    }
}
