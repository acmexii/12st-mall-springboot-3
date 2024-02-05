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
    public Consumer<Message<OrderPlaced>> wheneverOrderPlaced_StartDelivery() {
        return message -> {
            OrderPlaced orderPlaced = message.getPayload();
            System.out.println("\n\n##### listener StartDelivery : " + orderPlaced + "\n\n");

            // Sample Logic //
            Delivery.startDelivery(orderPlaced);
        };
    }

    @Bean
    public Consumer<Message<OrderCancelled>> wheneverOrderCancelled_CancelDelivery() {
        return message -> {
            OrderCancelled orderCancelled = message.getPayload();
            System.out.println("\n\n##### listener CancelDelivery : " + orderCancelled + "\n\n");

            // Sample Logic //
            Delivery.cancelDelivery(orderCancelled);
        };
    }
}
