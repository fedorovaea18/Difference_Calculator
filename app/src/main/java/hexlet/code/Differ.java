package hexlet.code;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2, String formatType) throws Exception {
        String data1 = getData(filePath1);
        String data2 = getData(filePath2);

        String fileType1 = getFileType(filePath1);
        String fileType2 = getFileType(filePath2);

        Map<String, Object> map1 = Parser.parse(data1, fileType1);
        Map<String, Object> map2 = Parser.parse(data2, fileType2);

        List<Map<String, Object>> result = CompareMaps.comparator(map1, map2);
        return Formatter.format(result, formatType);
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
