package mallbasic.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import mallbasic.domain.*;
import mallbasic.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class OrderPlaced extends AbstractEvent {

    private Long id;
    private String userId;
    private Long productId;
    private String productName;
    private Integer qty;
    private String address;
    private String status;
    private Date orderDt;

    public OrderPlaced(Order aggregate) {
        super(aggregate);
    }

    public OrderPlaced() {
        super();
    }
}
//>>> DDD / Domain Event
