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

import com.okyildiz.warehousemanagement.model.Shelf;
import com.okyildiz.warehousemanagement.service.ShelfService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/shelf")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShelfController {
	
	private final ShelfService shelfService;
	
	@GetMapping("/getAll")
	public List<Shelf> getAllShelf(){
		return shelfService.getAll();
	}
	
	@PostMapping("/add/{warehouseId}")
	public Shelf createShelf(@RequestBody Shelf shelf, @PathVariable Integer warehouseId ) {
		return shelfService.create(shelf,warehouseId);
	}
	
	@GetMapping("/get/{shelfId}")
	public ResponseEntity<Shelf> getShelfById(@PathVariable Integer shelfId) {
		return ResponseEntity.ok(shelfService.getShelfById(shelfId));
	}

	@PutMapping("/update/{shelfId}")
	public ResponseEntity<Shelf> updateShelf(@PathVariable Integer shelfId, @RequestBody Shelf shelfDetails) {
		return ResponseEntity.ok(shelfService.update(shelfDetails, shelfId));
	}

	@DeleteMapping("/delete/{shelfId}")
	public ResponseEntity<Map<String, Boolean>> deleteShelf(@PathVariable Integer shelfId) {
		return ResponseEntity.ok(shelfService.delete(shelfId));
	}
}
