package hexlet.code;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {
    public static Map<String, Object> parse(String filePath, String fileType) throws IOException {
        File file = new File(filePath);
        ObjectMapper objectMapper;
        switch (fileType) {
            case "json":
                objectMapper = new ObjectMapper();
                break;
            case "yml":
            case "yaml":
                objectMapper = new ObjectMapper(new YAMLFactory());
                break;
            default:
                throw new Error("Unknown format");
        }

        return objectMapper.readValue(file, new TypeReference<Map<String, Object>>() { });
    }
}
