package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Differ {

    public static String generate(String file1, String file2, String formatType) throws IOException {
        String data1 = getData(file1);
        String data2 = getData(file2);

        Map<String, Object> map1 = Parser.parse(data1, getFormatType(file1));
        Map<String, Object> map2 = Parser.parse(data2, getFormatType(file2));

        List<Map<String, Object>> result = TreeBuilder.generateTree(map1, map2);
        return Formatter.format(result, formatType);
    }

    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, "stylish");
    }

    public static String getData(String file) throws IOException {
        Path path = Paths.get(file).toAbsolutePath().normalize();
        return Files.readString(path);
    }

    public static String getFormatType(String filepath) {
        return filepath.substring(filepath.lastIndexOf(".") + 1);
    }
}
