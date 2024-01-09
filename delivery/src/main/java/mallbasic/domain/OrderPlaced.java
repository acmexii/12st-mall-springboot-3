package mallbasic.domain;

import java.util.*;
import lombok.*;
import mallbasic.domain.*;
import mallbasic.infra.AbstractEvent;

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
}
