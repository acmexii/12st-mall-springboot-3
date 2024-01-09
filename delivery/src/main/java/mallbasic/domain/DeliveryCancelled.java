package mallbasic.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import mallbasic.domain.*;
import mallbasic.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class DeliveryCancelled extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long productId;
    private String userId;
    private String productName;
    private Integer qty;
    private String address;
    private String status;
    private Date deliveryDt;

    public DeliveryCancelled(Delivery aggregate) {
        super(aggregate);
    }

    public DeliveryCancelled() {
        super();
    }
}
//>>> DDD / Domain Event
