package hexlet.code;

import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class Parser {
    public static Map<String, Object> parse(String data, String formatType) throws IOException {
        ObjectMapper objectMapper;
        switch (formatType) {
            case "json":
                objectMapper = new ObjectMapper();
                break;
            case "yml":
                objectMapper = new ObjectMapper(new YAMLFactory());
                break;
            default:
                throw new RuntimeException("Unknown format");
        }
        return objectMapper.readValue(data, new TypeReference<Map<String, Object>>() { });
    }
}
