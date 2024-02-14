package mallbasic.infra;

import java.util.function.Consumer;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mallbasic.domain.*;

@Service
@Transactional
public class PolicyHandler {

    @Bean
    public Consumer<Message<OrderPlaced>> wheneverOrderPlaced_StartDelivery() {
        return (event) -> {
            OrderPlaced orderPlaced = event.getPayload();
            Delivery.startDelivery(orderPlaced);
        };
    }

    @Bean
    public Consumer<Message<OrderCancelled>> wheneverOrderCancelled_CancelDelivery() {
        return (event) -> {
                OrderCancelled orderCancelled = event.getPayload();
                Delivery.cancelDelivery(orderCancelled);
        };
    }
}
