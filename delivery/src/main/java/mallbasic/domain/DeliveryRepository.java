package mallbasic.domain;

import mallbasic.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// @RepositoryRestResource(collectionResourceRel = "deliveries", path = "deliveries")
// public interface DeliveryRepository extends PagingAndSortingRepository<Delivery, Long> {

// }

@RepositoryRestResource(collectionResourceRel = "deliveries", path = "deliveries")
public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

    java.util.Optional<Delivery> findByOrderId(Long id);

}
