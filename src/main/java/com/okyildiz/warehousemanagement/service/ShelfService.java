package com.okyildiz.warehousemanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okyildiz.warehousemanagement.exception.AssociatedDataException;
import com.okyildiz.warehousemanagement.exception.ResourceNotFoundException;
import com.okyildiz.warehousemanagement.model.Shelf;
import com.okyildiz.warehousemanagement.model.Warehouse;
import com.okyildiz.warehousemanagement.repository.ShelfRepository;
import com.okyildiz.warehousemanagement.repository.WarehouseRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShelfService {
	
	private final WarehouseRepository warehouseRepository;
	private final ShelfRepository shelfRepository;
	
	
	public Shelf create(Shelf shelf, int warehouseId) {
		Warehouse warehouse = warehouseRepository.findById(warehouseId).orElseThrow(() -> new ResourceNotFoundException("Warehouse Not Found."));
//		if(shelf.getId() == null) {
//			throw new ResourceNotFoundException("Shelf Not Found");
//		}
		shelf.setWarehouse(warehouse);
		log.info("Creating Shelf");
		return shelfRepository.save(shelf);
	}
	
	public List<Shelf> getAll(){
		log.info("Get All Shelf");
		return shelfRepository.findAll();
	}
	
	public Shelf getShelfById(int id) {
		Shelf shelf = shelfRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Shelf Not Found"));
		log.info("Find By Shelf");
		return shelf;
	}
	
	public Shelf update(Shelf updatedShelf, int id) {
		Shelf shelf = shelfRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Shelf Not Found"));
		
		shelf.setCode(updatedShelf.getCode());
		shelf.setDescription(updatedShelf.getDescription());
		
		Shelf uptShelf= shelfRepository.save(shelf);
		log.info("Updating Shelf");
		return uptShelf;
	}
	
	public Map<String, Boolean> delete(int id){
		Shelf shelf = shelfRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Shelf Not Found"));
		if(shelf.getItems().size() > 0) {
			throw new AssociatedDataException("Associated data cannot be deleted");
		}
		Map<String, Boolean> response = new HashMap<>();
		shelfRepository.delete(shelf);
		response.put("deleted", Boolean.TRUE);
		log.info("Deleting Shelf");
		return response;
	}
}
