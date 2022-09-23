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
import com.myspring.hungernet.Model.entity.Restaurant;
import com.myspring.hungernet.Model.pojo.RestaurantPojo;
import com.myspring.hungernet.request.CreateRestaurantRequestDTO;
import com.myspring.hungernet.request.DeleteRestaurantRequestDTO;
import com.myspring.hungernet.request.UpdateRestaurantRequestDTO;
import com.myspring.hungernet.service.RestaurantService;

@RestController
@RequestMapping(path = "/hungernet")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@PostMapping(path = "/addRestaurant")
	// add new restaurant to hungernetdb.user
	public ResponseEntity<String> addNewRestaurant(@RequestParam String userLoggedIn,
			@RequestBody CreateRestaurantRequestDTO requestDto) {
		return restaurantService.addNewRestaurant(userLoggedIn, requestDto);
	}

	@DeleteMapping(path = "/deleteRestaurant")
	public ResponseEntity<String> deleteRestaurantByName(@RequestParam String userLoggedIn,
			@RequestBody DeleteRestaurantRequestDTO deleteDto) {
		return restaurantService.deleteRestaurantById(userLoggedIn, deleteDto);
	}

	@GetMapping(path = "/findAllRestaurants")
	public ResponseEntity<List<Restaurant>> findAllRestaurants(@RequestParam String userLoggedIn) {
		return restaurantService.findAllRestaurants(userLoggedIn);
	}

	@PutMapping(path = "/updateRestaurants")
	public ResponseEntity<String> updateRestaurantById(@RequestParam String userLoggedIn,
			@RequestBody UpdateRestaurantRequestDTO update) {
		return restaurantService.updateRestaurantById(userLoggedIn, update);
	}

}
