package com.myspring.hungernet.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.myspring.hungernet.Model.entity.Restaurant;
import com.myspring.hungernet.Model.entity.User;
import com.myspring.hungernet.Model.pojo.RestaurantPojo;
import com.myspring.hungernet.request.CreateRestaurantRequestDTO;
import com.myspring.hungernet.request.DeleteRestaurantRequestDTO;
import com.myspring.hungernet.request.UpdateRestaurantRequestDTO;

@Service
@Transactional
public class RestaurantService {

	@PersistenceContext
	private EntityManager entityManager;

	// Add new restaurant
	public ResponseEntity<String> addNewRestaurant(String userLoggedIn, CreateRestaurantRequestDTO requestDto) {
		if (userLoggedIn.equals("Admin")) {
			User user = entityManager.find(User.class, requestDto.getManagerId());
			if (user.getRole().equals("Manager")) {
				Restaurant restaurant = new Restaurant();
				restaurant.setUser(user);
				restaurant.setRestaurantName(requestDto.getRestaurantName());
				entityManager.persist(restaurant);
				return new ResponseEntity<String>("Restaurant added!", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("You are not a MANAGER", HttpStatus.NOT_ACCEPTABLE);
			}
		} else {
			return new ResponseEntity<String>("Unauthorized", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	// delete a restaurant by id
	public ResponseEntity<String> deleteRestaurantById(String userLoggedIn, DeleteRestaurantRequestDTO deleteDto) {
		Restaurant restaurant = entityManager.find(Restaurant.class, deleteDto.getId());
		if (userLoggedIn.equals("Admin")) {
			if (restaurant != null) {
				entityManager.remove(restaurant);
				return new ResponseEntity<>("Restaurant deleted.", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<>("Restaurant not found.", HttpStatus.NOT_FOUND);
			}

		} else {
			return new ResponseEntity<>("Unauthorized access", HttpStatus.REQUEST_TIMEOUT);
		}

	}

	public ResponseEntity<List<Restaurant>> findAllRestaurants(String userLoggedIn) {
		if (userLoggedIn.equals("Admin")) {
			TypedQuery<Restaurant> query = entityManager.createQuery("SELECT r FROM Restaurant r", Restaurant.class);
			List<Restaurant> restaurants = query.getResultList();
			if (restaurants != null) {
				return ResponseEntity.ok(restaurants);
			} else {
				return new ResponseEntity("No restaurant found", HttpStatus.NOT_FOUND);
			}

		} else {
			return new ResponseEntity("Unauthorized", HttpStatus.BAD_REQUEST);
		}

	}

	public ResponseEntity<String> updateRestaurantById(String userLoggedIn, UpdateRestaurantRequestDTO update) {
		if (userLoggedIn.equals("Admin")) {
			Restaurant restaurant = entityManager.find(Restaurant.class, update.getId());
			if (restaurant != null) {
				restaurant.setRestaurantName(update.getRestaurantName());
				return new ResponseEntity<>("Restaurant name modified", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Restaurant not found", HttpStatus.NOT_FOUND);
			}
		} else {
			return new ResponseEntity<>("Unauthorized", HttpStatus.REQUEST_TIMEOUT);
		}
	}
}
