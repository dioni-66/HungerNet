package com.myspring.hungernet.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myspring.hungernet.Model.entity.Item;
import com.myspring.hungernet.Model.entity.Menu;
import com.myspring.hungernet.request.CreateItemRequestDTO;
import com.myspring.hungernet.request.DeleteItemRequestDTO;
import com.myspring.hungernet.request.UpdateItemRequestDTO;

@Service
@Transactional
public class ItemService {

	@PersistenceContext
	private EntityManager entityManager;

	public ResponseEntity<String> addNewItem(String userLoggedIn, CreateItemRequestDTO itemDto) {
		Menu menu = entityManager.find(Menu.class, itemDto.getMenuId());
		if (userLoggedIn.equals("Manager")) {
			if (menu != null) {
				Item item = new Item();
				item.setItemName(itemDto.getItemName());
				item.setMenu(menu);
				entityManager.persist(item);
				return new ResponseEntity<String>("Item added!", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Cannot find a valid menu to add this item", HttpStatus.NOT_FOUND);
			}

		} else {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	public ResponseEntity<String> deleteItemById(String userLoggedIn, DeleteItemRequestDTO itemDto) {
		Item item = entityManager.find(Item.class, itemDto.getItemId());
		if (userLoggedIn.equals("Manager")) {
			if (item != null) {
				entityManager.remove(item);
				return new ResponseEntity<String>("Item deleted!", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Item not found!", HttpStatus.NOT_FOUND);
			}

		} else {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> updateItemById(String userLoggedIn, UpdateItemRequestDTO itemDto) {
		Item item = entityManager.find(Item.class, itemDto.getItemId());
		if (userLoggedIn.equals("Manager")) {
			if (item != null) {
				item.setItemName(itemDto.getItemName());
				return new ResponseEntity<String>("Item name updated", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Item not found", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	public ResponseEntity<List<Item>> findAllItems(String userLoggedIn) {
		if (userLoggedIn.equals("Manager")) {
			TypedQuery<Item> query = entityManager.createQuery(
					"SELECT i FROM Item i LEFT JOIN" + " Menu m ON i.id=m.id WHERE menu_id <> 'NULL'", Item.class);
			List<Item> items = query.getResultList();
			if (items != null) {
				return ResponseEntity.ok(items);
			} else {
				return new ResponseEntity("No items found", HttpStatus.NOT_FOUND);
			}

		} else {
			return new ResponseEntity("Unauthorized", HttpStatus.FORBIDDEN);
		}
	}

}
