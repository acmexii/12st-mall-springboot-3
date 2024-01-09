package mallbasic.infra;

import java.util.function.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Transactional
public class PolicyHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    public Consumer<String> consumer() {
        return message -> {
            System.out.println("■ Received message : " + message);

            try {
                JsonNode jsonNode = objectMapper.readTree(message);
                String eventType = jsonNode.get("eventType").asText();

                Class<?> eventClass = Class.forName("mallbasic.domain." + eventType);
                Object eventObject = objectMapper.readValue(message, eventClass);

                // Business Logic here;
                processEvent(eventObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }

    private void processEvent(Object event) {
        // Add more cases for other event types as needed
    }

}