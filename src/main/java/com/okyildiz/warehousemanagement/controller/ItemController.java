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

import com.okyildiz.warehousemanagement.model.Item;
import com.okyildiz.warehousemanagement.service.ItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ItemController {
	
	private final ItemService ItemService;
	
	@GetMapping("/getAll")
	public List<Item> getAllItem(){
		return ItemService.getAll();
	}
	
	@PostMapping("/add/{shelfId}")
	public Item createItem(@RequestBody Item item, @PathVariable Integer shelfId ) {
		return ItemService.create(item,shelfId);
	}
	
	@GetMapping("/get/{itemId}")
	public ResponseEntity<Item> getItemById(@PathVariable Integer itemId) {
		return ResponseEntity.ok(ItemService.getItemById(itemId));
	}

	@PutMapping("/update/{itemId}")
	public ResponseEntity<Item> updateItem(@PathVariable Integer itemId, @RequestBody Item itemDetails) {
		return ResponseEntity.ok(ItemService.update(itemDetails, itemId));
	}

	@DeleteMapping("/delete/{itemId}")
	public ResponseEntity<Map<String, Boolean>> deleteItem(@PathVariable Integer itemId) {
		return ResponseEntity.ok(ItemService.delete(itemId));
	}
	
	@GetMapping("/{barcode}")
	public List<Item> getAllItem(@PathVariable String barcode){
		return ItemService.findByBarcode(barcode);
	}
}
