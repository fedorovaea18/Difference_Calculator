package hexlet.code;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import hexlet.code.formatter.Stylish;

public class Differ {
    public static String generate(String file1Path, String file2Path) throws IOException {
        String file1Extension = getFileExtension(file1Path);
        String file2Extension = getFileExtension(file2Path);
        Map<String, Object> data1 = Parser.parse(file1Path, file1Extension);
        Map<String, Object> data2 = Parser.parse(file2Path, file2Extension);

        List<Map<String, Object>> differences = new ArrayList<>();
        compareMaps(data1, data2, differences, "");
        return Stylish.stylish(differences);
    }

    static String getFileExtension(String filePath) {
        Path path = Paths.get(filePath);
        String fileName = path.getFileName().toString();
        int lastDotIndex = fileName.lastIndexOf(".");
        return lastDotIndex == -1 ? "" : fileName.substring(lastDotIndex + 1);
    }

    static void compareMaps(Map<String, Object> data1, Map<String, Object> data2,
                            List<Map<String, Object>> differences, String path) {
        for (Map.Entry<String, Object> entry : data1.entrySet()) {
            String key = entry.getKey();
            Object value1 = entry.getValue();
            if (data2.containsKey(key)) {
                Object value2 = data2.get(key);

                if (Objects.equals(value1, value2) && value1 != null && value2 instanceof List) {
                    List<Object> list1 = (List<Object>) value1;
                    List<Object> list2 = (List<Object>) value2;
                    if (list1.size() == list2.size()) {
                        boolean allElementsEqual = true;
                        for (int i = 0; i < list1.size(); i++) {
                            if (!Objects.equals(list1.get(i), list2.get(i))) {
                                allElementsEqual = false;
                                break;
                            }
                        }
                        if (allElementsEqual) {
                            Map<String, Object> unchangedMap = new LinkedHashMap<>();
                            unchangedMap.put("key", path + key);
                            unchangedMap.put("type", "unchanged");
                            unchangedMap.put("value", value1);
                            differences.add(unchangedMap);
                            continue;
                        }
                    }
                }

                if (Objects.equals(value1, value2)) {
                    Map<String, Object> unchangedMap = new LinkedHashMap<>();
                    unchangedMap.put("key", path + key);
                    unchangedMap.put("type", "unchanged");
                    differences.add(unchangedMap);
                } else if (value1 == null || !value1.equals(value2)) {
                    if (value1 instanceof Map && value2 instanceof Map) {
                        compareMaps((Map<String, Object>) value1, (Map<String, Object>) value2, differences, path + key + ".");
                    } else {
                        Map<String, Object> changedMap = new LinkedHashMap<>();
                        changedMap.put("key", path + key);
                        changedMap.put("type", "changed");
                        changedMap.put("oldValue", value1);
                        changedMap.put("newValue", value2);
                        differences.add(changedMap);
                    }
                }
            } else {
                Map<String, Object> removedMap = new LinkedHashMap<>();
                removedMap.put("key", path + key);
                removedMap.put("type", "removed");
                removedMap.put("value", value1);
                differences.add(removedMap);
            }
        }

        for (Map.Entry<String, Object> entry : data2.entrySet()) {
            String key = entry.getKey();
            if (!data1.containsKey(key)) {
                Object value2 = entry.getValue();

                Map<String, Object> addedMap = new LinkedHashMap<>();
                addedMap.put("key", path + key);
                addedMap.put("type", "added");
                addedMap.put("value", value2);
                differences.add(addedMap);
            }
        }
    }
}