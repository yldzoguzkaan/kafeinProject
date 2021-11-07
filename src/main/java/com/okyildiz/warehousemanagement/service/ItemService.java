package com.okyildiz.warehousemanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.okyildiz.warehousemanagement.exception.FullShelfException;
import com.okyildiz.warehousemanagement.exception.ResourceNotFoundException;
import com.okyildiz.warehousemanagement.model.Item;
import com.okyildiz.warehousemanagement.model.Shelf;
import com.okyildiz.warehousemanagement.repository.ItemRepository;
import com.okyildiz.warehousemanagement.repository.ShelfRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ItemService {
	private final ShelfRepository shelfRepository;
	private final ItemRepository itemRepository;
	
	public Item create(Item item, int shelfId) {
		Shelf shelf= shelfRepository.findById(shelfId).orElseThrow(() -> new ResourceNotFoundException("Shelf Not Found."));
		if(shelf.getItems().size()  == 4) {
			throw new FullShelfException("All the places on the shelf are full");
		}
		item.setShelf(shelf);
		log.info("Creating Item");
		return itemRepository.save(item);
	}
	
	public List<Item> getAll(){
		log.info("Get All Item");
		return itemRepository.findAll();
	}
	
	public List<Item> findByBarcode(String barcode){
		log.info("Get Requesting Items by Borcode");
		return itemRepository.findByBarcode(barcode);
	}
	
	public Item getItemById(int id) {
		Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item Not Found"));
		log.info("Find By Shelf");
		return item;
	}
	
	public Item update(Item updatedItem, int id) {
		Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item Not Found"));
		
		item.setCode(updatedItem.getCode());
		item.setDescription(updatedItem.getDescription());
		item.setBarcode(updatedItem.getBarcode());
		
		Item uptItem = itemRepository.save(item);
		log.info("Updating Item");
		return uptItem;
	}
	
	public Map<String, Boolean> delete(int id){
		Item item = itemRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Item Not Found"));
		Map<String, Boolean> response = new HashMap<>();
		itemRepository.delete(item);
		response.put("deleted", Boolean.TRUE);
		log.info("Deleting Item");
		return response;
	}
}
