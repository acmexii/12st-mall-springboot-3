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
        return message -> {
            DeliveryStarted deliveryStarted = message.getPayload();
            System.out.println("\n\n##### listener DecreaseStock : " + deliveryStarted + "\n\n");

            // Sample Logic //
            Inventory.decreaseStock(deliveryStarted);
        };
    }

    @Bean
    public Consumer<Message<DeliveryCancelled>> wheneverDeliveryCancelled_IncreaseStock() {
        return message -> {
            DeliveryCancelled deliveryCancelled = message.getPayload();
            System.out.println("\n\n##### listener IncreaseStock : " + deliveryCancelled + "\n\n");

            // Sample Logic //
            Inventory.increaseStock(deliveryCancelled);
        };
    }
}