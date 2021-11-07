package com.okyildiz.warehousemanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.okyildiz.warehousemanagement.model.Warehouse;
import com.okyildiz.warehousemanagement.service.WarehouseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/warehouse")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WarehouseController {
	
	private final WarehouseService warehouseService;
	
	@GetMapping("/getAll")
	public List<Warehouse> getAllWarehouse(){
		return warehouseService.getAll();
	}
	
	@PostMapping("/add")
	public Warehouse createWarehouse(@RequestBody Warehouse warehouse) {
		return warehouseService.create(warehouse);
	}
	
	@GetMapping("/get/{warehouseId}")
	public ResponseEntity<Warehouse> getWarehouseById(@PathVariable Integer warehouseId) {
		return ResponseEntity.ok(warehouseService.getWarehouseById(warehouseId));
	}

	@PutMapping("/update/{warehouseId}")
	public ResponseEntity<Warehouse> updateWarehouse(@PathVariable Integer warehouseId, @RequestBody Warehouse warehouseDetails) {
		return ResponseEntity.ok(warehouseService.update(warehouseDetails, warehouseId));
	}

	@DeleteMapping("/delete/{warehouseId}")
	public ResponseEntity<Map<String, Boolean>> deleteWarehouse(@PathVariable Integer warehouseId) {
		return ResponseEntity.ok(warehouseService.delete(warehouseId));
	}
}
