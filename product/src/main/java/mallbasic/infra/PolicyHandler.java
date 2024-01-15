package mallbasic.infra;

import java.util.function.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import mallbasic.domain.*;

@Service
@Transactional
public class PolicyHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    // @Bean
    // public Consumer<String> consumer() {
    //     return message -> {
    //         try {
    //             JsonNode jsonNode = objectMapper.readTree(message);
    //             String eventType = jsonNode.get("eventType").asText();

    //             switch (eventType) {
    //                 case "DeliveryStarted":
    //                     DeliveryStarted deliveryStarted = objectMapper.readValue(message, DeliveryStarted.class);
    //                     // Business Logic here;
    //                     Inventory.decreaseStock(deliveryStarted);
    //                     break;
                        
    //                 case "DeliveryCancelled":
    //                     DeliveryCancelled deliveryCancelled = objectMapper.readValue(message, DeliveryCancelled.class);
    //                     // Business Logic here;
    //                     Inventory.increaseStock(deliveryCancelled);
    //                     break;    

    //                 default:
    //                     break;
    //             }
    //         } catch (Exception e) {
    //             e.printStackTrace();
    //         }
    //     };
    // } 

    @Bean
    public Consumer<String> consumer() {
        return message -> {
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
        if (event instanceof DeliveryStarted) {
            Inventory.decreaseStock((DeliveryStarted) event);
        } else if (event instanceof DeliveryCancelled) {
            Inventory.increaseStock((DeliveryCancelled) event);
        }
        // Add more cases for other event types as needed
    }

}
