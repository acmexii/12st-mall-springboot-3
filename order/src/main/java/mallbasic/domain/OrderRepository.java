package mallbasic.domain;

import mallbasic.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// @RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
// public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
    
// }

@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderRepository extends JpaRepository<Order, Long> {

}
