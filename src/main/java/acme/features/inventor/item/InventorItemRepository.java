package acme.features.inventor.item;


import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.entities.quantities.Quantity;
import acme.entities.toolkits.Toolkit;
import acme.framework.repositories.AbstractRepository;


@Repository
public interface InventorItemRepository extends AbstractRepository {
	
	@Query("select i from Item i where i.inventor.id = :id")
	Collection<Item> findItemsByInventorId(int id);
	
	@Query("select i from Item i where i.id = :id")
	Item findItemById(int id);
	
	@Query("select t from Toolkit t where t.id = :masterId")
	Toolkit findOneToolkitById(int masterId);

	@Query("select distinct(q.item) from Quantity q where q.toolkit.id = :masterId")
	Collection<Item> findItemsByToolkitId(int masterId);
	
	@Query("select q.toolkit from Quantity q where q.item.id = :id")
	Toolkit findOneToolkitByItemId(int id);
	
	@Query("select i from Item i where i.code = :code")
	Item findOneItemByCode(String code);
	
	@Query("select q from Quantity q where q.item.id = :id")
	Quantity findQuantityByItemId(int id);
	
	@Query("select sc.currency from SystemConfiguration sc")
	String findSystemCurrency();
}
