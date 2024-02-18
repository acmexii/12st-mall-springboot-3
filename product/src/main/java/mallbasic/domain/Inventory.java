package mallbasic.domain;

import jakarta.persistence.*;
import lombok.Data;
import mallbasic.ProductApplication;

@Entity
@Table(name = "Inventory_table")
@Data
//<<< DDD / Aggregate Root
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    private Integer stock;

    public static InventoryRepository repository() {
        InventoryRepository inventoryRepository = ProductApplication.applicationContext.getBean(
            InventoryRepository.class
        );
        return inventoryRepository;
    }

    //<<< Clean Arch / Port Method
    public static void decreaseStock(DeliveryStarted deliveryStarted) {
        //implement business logic here:

        repository().findById(deliveryStarted.getProductId()).ifPresent(inventory->{
            inventory.setStock(inventory.getStock() - deliveryStarted.getQty()); 
            repository().save(inventory);

            StockDecreased stockDecreased = new StockDecreased(inventory);
            stockDecreased.publishAfterCommit();
         });
    }

    //<<< Clean Arch / Port Method
    public static void increaseStock(DeliveryCancelled deliveryCancelled) {
        repository().findById(deliveryCancelled.getProductId()).ifPresent(inventory->{
            
            inventory.setStock(inventory.getStock() - deliveryCancelled.getQty()); 
            repository().save(inventory);

            StockIncreased stockIncreased = new StockIncreased(inventory);
            stockIncreased.publishAfterCommit();

         });
    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
