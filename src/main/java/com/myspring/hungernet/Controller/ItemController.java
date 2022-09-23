package com.myspring.hungernet.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.myspring.hungernet.Model.entity.Item;
import com.myspring.hungernet.request.CreateItemRequestDTO;
import com.myspring.hungernet.request.DeleteItemRequestDTO;
import com.myspring.hungernet.request.UpdateItemRequestDTO;
import com.myspring.hungernet.service.ItemService;

@RestController
@RequestMapping(path = "/hungernet")
public class ItemController {
	@Autowired
	ItemService itemService;

	// add new Item
	@PostMapping(path = "/addItem")
	public ResponseEntity<String> addNewItem(@RequestParam String userLoggedIn,
			@RequestBody CreateItemRequestDTO itemDto) {
		return itemService.addNewItem(userLoggedIn, itemDto);
	}

	// delete an Item
	@DeleteMapping(path = "/deleteItem")
	public ResponseEntity<String> deleteItemById(@RequestParam String userLoggedIn,
			@RequestBody DeleteItemRequestDTO itemDto) {
		return itemService.deleteItemById(userLoggedIn, itemDto);
	}

	// update an Item name
	@PutMapping(path = "/updateItem")
	public ResponseEntity<String> updateItemById(@RequestParam String userLoggedIn,
			@RequestBody UpdateItemRequestDTO itemDto) {
		return itemService.updateItemById(userLoggedIn, itemDto);
	}
	
	// find all items of any menu
	@GetMapping(path = "/findAllItems")
	public ResponseEntity<List<Item>> findAllItems(@RequestParam String userLoggedIn){
		return itemService.findAllItems(userLoggedIn);
	}
}
