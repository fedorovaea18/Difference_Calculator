package hexlet.code;

import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(
            List<Map<String, Object>> differences, String formatType) throws IOException {
        switch (formatType) {
            case "stylish":
                return Stylish.stylish(differences);
            case "plain":
                return Plain.plain(differences);
            case "json":
                return Json.json(differences);
            default:
                System.out.println("Format" + formatType + "is not correct!");
        }
        return Stylish.stylish(differences);
    }
}
