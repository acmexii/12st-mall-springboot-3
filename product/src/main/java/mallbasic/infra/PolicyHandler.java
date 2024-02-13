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
    public Consumer<Message<DeliveryStarted>> wheneverDeliveryStarted_DescreaseStock() {
        return event -> {
            if (!AbstractEvent.isMe(event, "DeliveryStarted")) return;

            DeliveryStarted deliveryStarted = event.getPayload();
            Inventory.decreaseStock(deliveryStarted);
        };
    }

    @Bean
    public Consumer<Message<DeliveryCancelled>> wheneverDeliveryCancelled_IncreaseStock() {
        return event -> {
            if (!AbstractEvent.isMe(event, "DeliveryCancelled")) return;

            DeliveryCancelled deliveryCancelled = event.getPayload();
            Inventory.increaseStock(deliveryCancelled);
        };
    }
}