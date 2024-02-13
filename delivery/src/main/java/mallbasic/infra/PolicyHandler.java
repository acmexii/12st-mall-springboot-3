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
            if (!AbstractEvent.isMe(event, "OrderPlaced")) return;

            OrderPlaced orderPlaced = event.getPayload();
            Delivery.startDelivery(orderPlaced);
        };
    }

    @Bean
    public Consumer<Message<OrderCancelled>> wheneverOrderCancelled_CancelDelivery() {
        return (event) -> {
            if (!AbstractEvent.isMe(event, "OrderCancelled")) return;
            
                OrderCancelled orderCancelled = event.getPayload();
                Delivery.cancelDelivery(orderCancelled);
        };
    }
}
