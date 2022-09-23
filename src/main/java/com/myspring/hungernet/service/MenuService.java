package com.myspring.hungernet.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.myspring.hungernet.Model.entity.Menu;
import com.myspring.hungernet.Model.entity.Restaurant;
import com.myspring.hungernet.request.CreateMenuRequestDTO;
import com.myspring.hungernet.request.DeleteMenuRequestDTO;
import com.myspring.hungernet.request.FindAllMenusRequestDTO;
import com.myspring.hungernet.request.UpdateMenuRequestDTO;

@Service
@Transactional
public class MenuService {
	@PersistenceContext
	private EntityManager entityManager;

	public ResponseEntity<String> addNewMenu(String userLoggedIn, CreateMenuRequestDTO menuDto) {
		if (userLoggedIn.equals("Manager")) {
			Restaurant restaurant = entityManager.find(Restaurant.class, menuDto.getRestaurantId());
			if (restaurant != null) {
				Menu menu = new Menu();
				menu.setType(menuDto.getType());
				menu.setRestaurant(restaurant);
				entityManager.persist(menu);
				return new ResponseEntity<>("Menu added!", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Restaurant missing!", HttpStatus.NOT_FOUND);
			}

		} else {
			return new ResponseEntity<>("Unauthorized access!", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	public ResponseEntity<String> deleteMenuById(String userLoggedIn, DeleteMenuRequestDTO menuDto) {
		Menu menu = entityManager.find(Menu.class, menuDto.getId());
		if (userLoggedIn.equals("Manager")) {
			if (menu != null) {
				entityManager.remove(menu);
				return new ResponseEntity<>("Menu deleted.", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>("Menu not found.", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>("Unauthorized", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<List<Menu>> findAllMenus(String userLoggedIn, FindAllMenusRequestDTO menuDto) {
		if (userLoggedIn.equals("Manager")) {
			TypedQuery<Menu> query = entityManager
					.createQuery("SELECT m FROM Menu m LEFT JOIN " + " m.restaurant r WHERE r.id = :id", Menu.class);
			query.setParameter("id", menuDto.getRestaurantId());
			List<Menu> menu = query.getResultList();
			if (menu != null) {
				return ResponseEntity.ok(menu);
			} else if (menu == null) {
				return new ResponseEntity("No Restaurant/Menu found", HttpStatus.NOT_FOUND);
				} else {
					return new ResponseEntity("No Menu found", HttpStatus.NOT_FOUND);
				}
			
		} else {
			return new ResponseEntity("Unauthorized", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> updateMenuById(String userLoggedIn, UpdateMenuRequestDTO menuDto) {
		if (userLoggedIn.equals("Manager")) {
			Menu menu= entityManager.find(Menu.class, menuDto.getId());
			if (menu != null) {
				menu.setType(menuDto.getType());
				return new ResponseEntity<>("Menu modified", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Menu not found", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>("Unauthorized", HttpStatus.BAD_REQUEST);
		}
	}
}
