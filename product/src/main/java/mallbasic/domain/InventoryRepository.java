package mallbasic.domain;

import mallbasic.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// @RepositoryRestResource(collectionResourceRel = "inventories", path = "inventories")
// public interface InventoryRepository extends PagingAndSortingRepository<Inventory, Long> {

// }

@RepositoryRestResource(collectionResourceRel = "inventories", path = "inventories")
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

}
