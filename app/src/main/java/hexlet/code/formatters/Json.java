package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Json {
    public static String json(List<Map<String, Object>> differences) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(differences).trim();
    }
}
