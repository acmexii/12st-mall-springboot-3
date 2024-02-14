package mallbasic.infra;

import java.util.function.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.messaging.Message;
import mallbasic.domain.*;

@Service
@Transactional
public class PolicyHandler {

    @Bean
    public Consumer<Message<?>> discardFunction() {
        return message -> {
            // Ingore unnecessary message
            System.out.println("Discarded message: " + message);
        };
    }
    
    @Bean
    public Consumer<Message<DeliveryStarted>> wheneverDeliveryStarted_DescreaseStock() {
        return event -> {
            DeliveryStarted deliveryStarted = event.getPayload();
            Inventory.decreaseStock(deliveryStarted);
        };
    }

    @Bean
    public Consumer<Message<DeliveryCancelled>> wheneverDeliveryCancelled_IncreaseStock() {
        return event -> {
            DeliveryCancelled deliveryCancelled = event.getPayload();
            Inventory.increaseStock(deliveryCancelled);
        };
    }
}