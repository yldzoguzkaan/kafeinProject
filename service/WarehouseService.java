package com.okyildiz.warehousemanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okyildiz.warehousemanagement.exception.AssociatedDataException;
import com.okyildiz.warehousemanagement.exception.ResourceNotFoundException;
import com.okyildiz.warehousemanagement.model.Warehouse;
import com.okyildiz.warehousemanagement.repository.WarehouseRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WarehouseService {
	
	private final WarehouseRepository warehouseRepository;
	
//	@Autowired //Constructor Injection
//	public WarehouseService(WarehouseRepository warehouseRepository) {
//		this.warehouseRepository = warehouseRepository;
//	}
	
	public Warehouse create(Warehouse warehouse) {
//		if(warehouse.getId() == null) {
//			throw new ResourceNotFoundException("Warehouse not found");		
//		}
		log.info("Inserting Warehouse");
		return warehouseRepository.save(warehouse);
	}
	
	public List<Warehouse> getAll(){
		log.info("Get All Warehouse");
		return warehouseRepository.findAll();
	}
	
	public Warehouse getWarehouseById(int id) {
		Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Warehouse Not Found"));
		log.info("Find By Warehouse");
		return warehouse;
	}
	
	public Warehouse update(Warehouse updatedWarehouse, int id) {
		Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Warehouse Not Found"));
		
		warehouse.setName(updatedWarehouse.getName());
		warehouse.setCode(updatedWarehouse.getCode());
		warehouse.setDescription(updatedWarehouse.getDescription());
		warehouse.setAddress(updatedWarehouse.getAddress());
		
		Warehouse uptWarehouse = warehouseRepository.save(warehouse);
		log.info("Updating Warehouse");
		return uptWarehouse;
	}
	
	public Map<String, Boolean> delete(int id){
		Warehouse warehouse = warehouseRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Warehouse Not Found"));
		if(warehouse.getShelfs().size() > 0) {
			throw new AssociatedDataException("Associated data cannot be deleted");
		}
		Map<String, Boolean> response = new HashMap<>();
		warehouseRepository.delete(warehouse);
		response.put("deleted", Boolean.TRUE);
		log.info("Deleting Warehouse");
		return response;
	}
}
