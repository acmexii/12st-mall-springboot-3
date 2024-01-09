package mallbasic.domain;

import java.util.*;
import lombok.*;
import mallbasic.domain.*;
import mallbasic.infra.AbstractEvent;

@Data
@ToString
public class DeliveryStarted extends AbstractEvent {

    private Long id;
    private Long orderId;
    private Long productId;
    private String userId;
    private String productName;
    private Integer qty;
    private String address;
    private String status;
    private Date deliveryDt;
}
