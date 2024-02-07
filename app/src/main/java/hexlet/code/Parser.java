package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(File file) throws IOException {
        if (file.length() == 0) {
            return Collections.emptyMap();
        } else {
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            return objectMapper.readValue(file, new TypeReference<>() { });
        }
    }
}
