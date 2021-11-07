package com.okyildiz.warehousemanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.okyildiz.warehousemanagement.model.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
	List<Item> findByBarcode(String barcode); //JPA Data budur.
	
	@Query("Select a from Item a where a.barcode = ?1")
	List<Item> findByBarcodeJPQL(String barcode);
	
	@Query(value = "Select * from Item a where a.barcode = ?1",nativeQuery = true)
	List<Item> findByBarcodeSQL(String barcode);
}
